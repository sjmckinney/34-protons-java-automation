# 34-protons-java-automation

An automation project that uses java and a Behaviour Driven Development (BDD) to create a test automation framework.

### Generate the project structure with maven

```
mvn archetype:generate
 -DgroupId=com._34protons
 -DartifactId=34-protons-java-automation
 -DarchetypeArtifactId=maven-archetype-quickstart
 -DinteractiveMode=false

```

This will create the following directory structure

```
├── README.md
├── pom.xml
├── src
    ├── main
    │   └── java
    │       └── com
    │           └── _34protons
    │               └── App.java
    └── test
        └── java
            └── com
                └── _34protons
                    └── AppTest.java
```
Run _mvn clean test_ to test installation.

### Maven Plugins

**driver-binary-downloader-maven-plugin** _com.lazerycode.selenium_

This plugin will download the os-specific versions of the browser driver files specified in the RepositoryMap.xml file.

The plugin places the downloaded files in the default location _test/java/resources_. This location and other behaviour are configured in the _pom.xml_.
 
```
└── test
      └─ java
         │   └── com
         │       └── _34protons
         │           └── AppTest.java
         └── resources
             ├── RepositoryMap.xml
             ├── selenium_standalone_binaries
             │   └── osx
             │       ├── googlechrome
             │       │   └── 64bit
             │       │       └── chromedriver
             │       ├── marionette
             │       │   └── 64bit
             │       │       └── geckodriver
             │       ├── operachromium
             │       │   └── 64bit
             │       │       └── operadriver
             │       └── phantomjs
             │           └── 64bit
             │               └── phantomjs
             └── selenium_standalone_zips
                 ├── chromedriver_mac64.zip
                 ├── geckodriver-v0.15.0-macos.tar.gz
                 ├── operadriver_mac64.zip
                 └── phantomjs-2.1.1-macosx.zip
 ```
The drivers versions downloaded are configured in the RespositoryMap.xml.

More information is found at the following location:

https://github.com/Ardesco/Selenium-Maven-Template

**maven-failsafe-plugin** _org.apache.maven.plugins_

The _Failsafe_ Plugin is designed to run integration tests while the _Surefire_ Plugin is designed to run unit tests.

The Maven lifecycle has four phases for running integration tests:

* pre-integration-test for setting up the integration test environment.

* integration-test for running the integration tests.

* post-integration-test for tearing down the integration test environment.

* verify for checking the results of the integration tests.

The advantage of the _Failsafe_ plugin over the _Surefire_ plugin is that it ensures the teardown steps are run in the event of a test failure. As the _Surefire_ plugin is more of a unit test runner it quits with the first test case failure whereas the Failsafe_ ensures that the teardown steps are executes to leave the test environment in a known good state.

**maven-surefire-report-plugin** _org.apache.maven.plugins_

This plugin will process the output of the _Failsafe_ and _Surefire_ plugins to produce an html test report within the _target/site_ directory either as part of the _mvn site_ or indivdually with the _mvn surefire-report:report_ command (note the required images and css files are only available if the _mvn site_ command has previously been run).

### Driver Config

```
└── test
      └─ java
         └── com
             └── _34protons
                 ├── config
                 │   ├── DriverType.java
                 │   └── DriverCreation.java
                 └── tests
                     │── AppTest.java
                     └── DriverTest.java
```     

There are many browsers in which might be used in testing. Each driver implementation can accept a DesiredCapabilities object that can be used to alter browser function.
Additionally Firefox can accept a Profile object created via the Firefox Profile Manager or programtically at runtime.
```
<path to firefox exe/app> -P, -p or -ProfileManager
```
Chrome will accept chrome options in addition to Desired Capabilites

https://sites.google.com/a/chromium.org/chromedriver/capabilities

The interface _**DriverCreation**_ defines two methods;
```
    DesiredCapabilities getDesiredCapabilities();
    
    WebDriver getwebDriverObject(DesiredCapabilities desiredCapabilities);
```
The first returns a _DesiredCapabilites_ object; the second consumes that _DesiredCapabilites_ object in the creation of a _WebDriver_ object.

The enum DriverType defines each of the Browser implementations in terms of specific _DesiredCapabities_. All Enums implement the methods defined in the **_BrowserCreation_** interface.

Creating an instance of a particular browser is a case of specifying the required **_BrowserType_** Enum and calling it _getwebDriverObject_ method.

### Adding Cucumber BDD tests

Behavior Driven Development (BDD) is a software development methodology builds on Test Driven Development (TDD) as away of describing the desired functionality from the end users point of view. These "behaviours" are captured in a Given, When, Then in a Gherkin format be automated using a Cucumber framework.

The Gherkin format allows any stakeholder to review and add scenarios to the feature files that describe the expected behaviour of the application. Once automated and passing the scenarios provide confidence that the application's functionality will satisfy the end users' needs.

#### Project structure

In a typically java-cucumber project the feature files(*LoginTests.feature*) live under the **test/resources** directory whilst the Junit runner and Step Definition files (*LoginSteps.java* & *RunLoginTests.java*) live under the **test/java/** directory.

The Junit runner file is an empty class decorated with a number of annotations that provide extra information like the format of the output file.

```
└── test
    ├─ java
    │  └── com
    │      └── _34protons
    │          ├── config
    │          │   ├── DriverType.java
    │          │   └── DriverCreation.java
    │          └── tests
    │              ├── login
    │              │   └── LoginSteps.java
    │              │   └── RunLoginTests.java
    │              ├── AppTest.java
    │              └── DriverTest.java
    └─ resources
       └── features
           └── LoginTests.feature
```

In order to find the feature file that corresponds to the Junit runner file the directory structure under the **resources** directory should mimic that found under the **test/java** directory. Alternately the path to the feature file (relative to *test* directory) can be provided in the *@CucumberOptions* anotation as the 'features' property.
```
package com._34protons.tests.login;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber"},
        features = {"classpath:features/LoginTests.feature"})

public class RunLoginTests {
}
```
 