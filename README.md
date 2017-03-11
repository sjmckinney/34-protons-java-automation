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
      ├── java
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