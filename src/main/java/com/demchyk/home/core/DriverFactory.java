package com.demchyk.home.core;

import com.demchyk.home.utils.PropertyController;
import com.demchyk.home.utils.exceptions.NoSuchPropertyException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

class DriverFactory {
    private Logger logger = Logger.getLogger(DriverFactory.class);

    WebDriver getDriverVersion() {
            WebDriver driver;
            String driverVersion = readDriverVersion();
            switch (driverVersion) {
                case "chrome":
                    try {
                        System.setProperty(PropertyController.CHROMEPATH, PropertyController.readProperty(PropertyController.CHROMEPATH));
                    } catch (NoSuchPropertyException e) {
                        logger.error(e.getMessage());
                        e.printStackTrace();
                    }
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    try {
                        System.setProperty(PropertyController.CHROMEPATH, PropertyController.readProperty(PropertyController.CHROMEPATH));
                    } catch (NoSuchPropertyException e) {
                        logger.error(e.getMessage());
                        e.printStackTrace();
                    }
                    driver = new ChromeDriver();
            }
        return driver;
    }

    private String readDriverVersion() {
        String version = null;
        try {
            version = PropertyController.readProperty(PropertyController.WEBRDIVER);
        } catch (NoSuchPropertyException e) {
            e.printStackTrace();
        }
        return version;
    }

}
