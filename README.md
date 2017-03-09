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
