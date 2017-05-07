package com._34protons.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com._34protons.config.DriverType.CHROME;
import static com._34protons.config.DriverType.valueOf;

public class DriverFactory {

    private final static Logger logger = LogManager.getLogger(DriverFactory.class);
    private DriverType defaultDriverType = CHROME;
    WebDriver webDriver = null;

    public WebDriver getDefaultWebDriver() {

        DriverType driverType = getDefaultDriverType();
        DesiredCapabilities desiredCapabilities = driverType.getDesiredCapabilities();
        webDriver = driverType.getwebDriverObject(desiredCapabilities);
        return webDriver;
    }

    private DriverType getDefaultDriverType() {

        DriverType driverType = defaultDriverType;

        try {
            String browser = System.getProperty("browser").toUpperCase();
            driverType = valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            logger.error("Unknown driver specified. Reverting to default: ".concat(defaultDriverType.toString()));
        } catch (NullPointerException ignored) {
            logger.error("No driver type specified. Reverting to default: ".concat(defaultDriverType.toString()));
        }
        logger.info("Value of DriverType is: ".concat(driverType.toString()));
        return driverType;
    }
}
