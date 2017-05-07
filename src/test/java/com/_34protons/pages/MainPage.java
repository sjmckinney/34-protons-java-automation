package com._34protons.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    private final WebDriver driver;
    private final String pageTitle = "Demo page for selenium code";

    public MainPage(WebDriver sharedDriver) {
        super(sharedDriver);
        driver = sharedDriver;
        PageFactory.initElements(driver, this);
    }
}
