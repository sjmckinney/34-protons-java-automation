package com._34protons.config;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Code taken from WebSockets example in Cucumber JVM project
 */

public class SharedDriver extends EventFiringWebDriver {

    private static final Logger logger = LogManager.getLogger(SharedDriver.class);
    private static final DriverFactory driverFactory = new DriverFactory();
    private static final WebDriver REAL_DRIVER = driverFactory.getDefaultWebDriver();

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.close();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public SharedDriver() {
        super(REAL_DRIVER);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this Webdriver instance. It is shared and will close when the JVM exits.");
        }
    }

    @Before
    public void deleteAllCookies(){
        manage().deleteAllCookies();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        try {

        } catch (WebDriverException somePlatformsDontSupportScreenshots) {

        }
    }
}
