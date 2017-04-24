Run feature files from **cucumber.api.cli.Main**;

```
java -cp "/Users/stevemckinney/Documents/Workspace/jars/*" cucumber.api.cli.Main -p pretty src/test/resources/features
```

The *classpath* must contain the four following jars;
```$xslt
cucumber-core-1.2.5.jar
cucumber-java-1.2.5.jar
cucumber-jvm-deps-1.0.5.jar
gherkin-2.12.2.jar
```
