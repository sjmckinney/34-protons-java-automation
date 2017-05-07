package com._34protons.tests;

import com._34protons.config.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

/**
 * Simple test of driver creation code
 */

public class DriverTest {

    private final static Logger logger = LogManager.getLogger(DriverTest.class);
    private static DriverFactory driverFactory = new DriverFactory();
    private static WebDriver webDriver = null;
    private String expectedPageTitle = "Demo page for selenium code";

    @BeforeClass
    public static void beforeClass() {

        webDriver = driverFactory.getDefaultWebDriver();
        webDriver.get(System.getProperty("baseUrl", "http://www.34protons.co.uk/demo_2_0/"));

    }

    @Test
    public void testChromeDriver()
    {
        String pageTitle = webDriver.getTitle();
        logger.info("Browser opened url ".concat(webDriver.getCurrentUrl()));
        logger.info("Title of page is ".concat(pageTitle));
        assertEquals("Title of page is not as asserted", expectedPageTitle, pageTitle);
        logger.info("Tests in DriverTest class passed");
    }

    @AfterClass
    public static void AfterClass() {

        webDriver.quit();

    }

}
