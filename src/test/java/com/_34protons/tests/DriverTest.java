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
    private String expectedPageTitle = "Adventures in automation | Thoughts about automated testing";

    @BeforeClass
    public static void beforeClass() {

        webDriver = driverFactory.getDefaultWebDriver();
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

/*    private static DriverType getDefaultDriverType() {

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
    }*/
}
