package com._34protons.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This inteface defines the two methods required to
 * create an instance of an WebDriver object
 */

public interface DriverCreation {

    WebDriver getwebDriverObject(DesiredCapabilities desiredCapabilities);

    DesiredCapabilities getDesiredCapabilities();

}
