package com._34protons.tests;

import com._34protons.config.DriverType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com._34protons.config.DriverType.CHROME;
import static com._34protons.config.DriverType.valueOf;
import static org.junit.Assert.assertEquals;

/**
 * Simple test of driver creation code
 */

public class DriverTest {

    private static DriverType defaultDriverType = CHROME;
    private final static Logger logger = LogManager.getLogger(DriverTest.class);
    private static WebDriver webDriver = null;
    private String expectedPageTitle = "Adventures in automation | Thoughts about automated testing";

    @BeforeClass
    public static void beforeClass() {

        DriverType driverType = getDefaultDriverType();
        DesiredCapabilities desiredCapabilities = driverType.getDesiredCapabilities();
        webDriver = driverType.getwebDriverObject(desiredCapabilities);
        webDriver.get(System.getProperty("baseUrl", "http://www.34protons.co.uk"));

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

    private static DriverType getDefaultDriverType() {

        DriverType driverType = defaultDriverType;

        try{
            String browser = System.getProperty("browser").toUpperCase();
            driverType = valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            logger.error("Unknown driver specified. Reverting to default: ".concat(defaultDriverType.toString()));
        } catch ( NullPointerException ignored) {
            logger.error("No driver type specified. Reverting to default: ".concat(defaultDriverType.toString()));
        }
        logger.info("Value of DriverType is: ".concat(driverType.toString()));
        return driverType;
    }
}
