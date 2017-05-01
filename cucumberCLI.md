Run feature files from **cucumber.api.cli.Main**;

```commandline
java -cp "/Users/stevemckinney/Documents/Workspace/jars/*" cucumber.api.cli.Main -p pretty src/test/resources/features
```

The *classpath* must contain the four following jars;
```text
cucumber-core-1.2.5.jar
cucumber-java-1.2.5.jar
cucumber-jvm-deps-1.0.5.jar
gherkin-2.12.2.jar
```
The above works fine when the Step Definition code contains just calls to the *PendingException()* method.
Once you add test code to the Step Definitions then the test code needs to be compiled into the a 'test-jar' using the maven jar plugin.
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <version>3.0.2</version>
    <executions>
        <execution>
            <goals>
                <goal>test-jar</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
Any test code dependencies as well as the path to the 'test-jar' must now be the added to the classpath declaration.
```commandline
java -cp "/Users/stevemckinney/Documents/Workspace/jars/*:/Users/stevemckinney/Documents/Workspace/34-protons-java-automation/target/34-protons-java-automation-1.0-SNAPSHOT-tests.jar"
cucumber.api.cli.Main
-p progress
--snippets camelcase
--glue classpath:com._34protons.tests.login
src/test/resources/features/LoginTests.feature
```

Note the ```--glue``` flag. This is the path to the step definition code specified in the form of a class path (hence the need to compile the test code to a 'test-jar' and add it to the classpath definition).

Two more jars are now required to satisfy the test dependencies;
```text
junit-4.12.jar
hamcrest-core-1.3.jar
```

Cucumber plugins allow the tool's behaviour to be customized. Details on the options can be obtained by using the ```help``` flag.
```commandline
cucumber.api.cli.Main --help
Usage: java cucumber.api.cli.Main [options] [[[FILE|DIR][:LINE[:LINE]*] ]+ | @FILE ]

Options:

  -g, --glue PATH                        Where glue code (step definitions, hooks
                                         and plugins) are loaded from.
  -p, --[add-]plugin PLUGIN[:PATH_OR_URL]
                                         Register a plugin.
                                         Built-in formatter PLUGIN types: junit,
                                         html, pretty, progress, json, usage, rerun,
                                         testng. Built-in summary PLUGIN types:
                                         default_summary, null_summary. PLUGIN can
                                         also be a fully qualified class name, allowing
                                         registration of 3rd party plugins.
                                         --add-plugin does not clobber plugins of that
                                         type defined from a different source.
  -f, --format FORMAT[:PATH_OR_URL]      Deprecated. Use --plugin instead.
  -t, --tags TAG_EXPRESSION              Only run scenarios tagged with tags matching
                                         TAG_EXPRESSION.
  -n, --name REGEXP                      Only run scenarios whose names match REGEXP.
  -d, --[no-]-dry-run                    Skip execution of glue code.
  -m, --[no-]-monochrome                 Don't colour terminal output.
  -s, --[no-]-strict                     Treat undefined and pending steps as errors.
      --snippets [underscore|camelcase]  Naming convention for generated snippets.
                                         Defaults to underscore.
  -v, --version                          Print version.
  -h, --help                             You're looking at it.
  --i18n LANG                            List keywords for in a particular language
                                         Run with "--i18n help" to see all languages
  --junit,OPTION[,OPTION]*               Pass the OPTION(s) to the JUnit module.
                                         Use --junit,-h or --junit,--help to print the
                                         options of the JUnit module.

Feature path examples:
  <path>                                 Load the files with the extension ".feature"
                                         for the directory <path>
                                         and its sub directories.
  <path>/<name>.feature                  Load the feature file <path>/<name>.feature
                                         from the file system.
  classpath:<path>/<name>.feature        Load the feature file <path>/<name>.feature
                                         from the classpath.
  <path>/<name>.feature:3:9              Load the scenarios on line 3 and line 9 in
                                         the file <path>/<name>.feature.
  @<path>/<file>                         Parse <path>/<file> for feature paths generated
                                         by the rerun formatter.
```
