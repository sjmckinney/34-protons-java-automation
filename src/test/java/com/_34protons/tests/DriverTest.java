package com._34protons.tests;

import com._34protons.config.DriverType;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com._34protons.config.DriverType.CHROME;
import static com._34protons.config.DriverType.valueOf;

/**
 * Simple test of driver creation code
 */
public class DriverTest extends TestCase {

    private DriverType defaultDriverType = CHROME;
    private final static Logger logger = LogManager.getLogger(DriverTest.class);
    private WebDriver webDriver = null;
    private String browser = null;
    private String expectedPageTitle = "Google";
    private String url = "https://www.google.com";
    private String pageTitle = null;

    public void testChromeDriver()
    {
        DriverType driverType = getDefaultDriverType();
        DesiredCapabilities desiredCapabilities = driverType.getDesiredCapabilities();
        webDriver = driverType.getwebDriverObject(desiredCapabilities);

        webDriver.get(url);
        pageTitle = webDriver.getTitle();
        logger.info("Browser opened url ".concat(webDriver.getCurrentUrl()));
        logger.info("Title of page is ".concat(pageTitle));
        assertEquals("Title of page is not as asserted", expectedPageTitle, pageTitle);
        webDriver.quit();
        logger.info("Tests in DriverTest class passed");
    }

    private DriverType getDefaultDriverType() {
        DriverType driverType = defaultDriverType;

        try{
            browser = System.getProperty("browser").toUpperCase();
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
