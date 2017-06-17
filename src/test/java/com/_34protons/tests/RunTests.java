package com._34protons.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json", "html:target/site/cucumber"},
        features = {"classpath:features/"}
        )

public class RunTests {
}
