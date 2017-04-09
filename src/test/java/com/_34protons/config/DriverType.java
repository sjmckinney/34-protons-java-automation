package com._34protons.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 */

public enum DriverType implements DriverCreation {

    GECKO {
        public DesiredCapabilities getDesiredCapabilities () {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("marionette", true);
            return capabilities;
        }

        public WebDriver getwebDriverObject(DesiredCapabilities desiredCapabilities) {
            return new FirefoxDriver(desiredCapabilities);
        }
    },

    /* Firefox versions prior to 48 */
    FIREFOX {
        public DesiredCapabilities getDesiredCapabilities () {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            return capabilities;
        }

        public WebDriver getwebDriverObject(DesiredCapabilities desiredCapabilities) {
            return new FirefoxDriver(desiredCapabilities);
        }
    },

    CHROME {
        public DesiredCapabilities getDesiredCapabilities () {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            HashMap<String, String> chromePreferences = new HashMap<String, String>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            capabilities.setCapability("chrome.prefs", chromePreferences);
            return capabilities;
        }

        public WebDriver getwebDriverObject(DesiredCapabilities desiredCapabilities) {
            return new ChromeDriver(desiredCapabilities);
        }
    },

    INTERNET_EXPLORER {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.edge();
            return capabilities;
        }

        public WebDriver getwebDriverObject(DesiredCapabilities desiredCapabilities) {
            return new InternetExplorerDriver(desiredCapabilities);
        }
    },

    MICROSOFT_EDGE {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            return capabilities;
        }

        public WebDriver getwebDriverObject(DesiredCapabilities desiredCapabilities) {
            return new EdgeDriver(desiredCapabilities);
        }

    },

    PHANTOMJS {

        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
            final List<String> cliArguments = new ArrayList<String>();
            cliArguments.add("--web-security=false");
            cliArguments.add("--ssl-protocol=any");
            cliArguments.add("--ignore-ssl-errors=true");
            capabilities.setCapability("phantomjs.cli.args", cliArguments);
            capabilities.setCapability("takesScreenshot", true);

            return capabilities;
        }

        public WebDriver getwebDriverObject(DesiredCapabilities capabilities) {
            return new PhantomJSDriver(capabilities);
        }
    };
}
