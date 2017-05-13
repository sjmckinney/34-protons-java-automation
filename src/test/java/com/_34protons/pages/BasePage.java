package com._34protons.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private final static Logger logger = LogManager.getLogger(BasePage.class);
    private final WebDriver driver;

    public BasePage(WebDriver sharedDriver) {
        driver = sharedDriver;
    }

    private String getUrl() {
        return System.getProperty("baseUrl", "Value of baseUrl does not appear to be defined");
    }

    public void load() {
        String baseUrl = getUrl();
        driver.get(baseUrl);
        logger.info("Opening page: ".concat(baseUrl));
    }

    public void load(String path) {
        String baseUrl, overallUrl;
        baseUrl = getUrl();
        overallUrl = baseUrl.concat(path);
        driver.get(overallUrl);
        logger.info("Opening page: ".concat(overallUrl));
        logger.info("Page title is: ".concat(driver.getTitle()));
    }

    public String getPageTitle() {
        String pageTitle = driver.getTitle();
        logger.info("Current page title is : ".concat(pageTitle));
        return pageTitle;
    }

    public Boolean elementExists(By locator) {
        Boolean exists = false;
        try {driver.findElement(locator);
            exists = true;
        } catch (NoSuchElementException ex) {
            //Do nothing
            logger.debug(String.format("Element %s does not exist.", locator.toString()));
        }
        return exists;
    }

    public Boolean waitUntilElementExists(By locator, Integer waitTime) {
        Boolean exists = false;
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            exists = true;
        } catch (NoSuchElementException | TimeoutException ex) {
            //Do nothing
            logger.debug(String.format("Element %s does not exist or wait timed out after %d seconds.", locator.toString(), waitTime));
        }
        return exists;
    }
}
