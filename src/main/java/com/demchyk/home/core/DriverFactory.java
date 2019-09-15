package com.demchyk.home.core;

import com.demchyk.home.utils.PropertyController;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private Logger logger = Logger.getLogger(DriverFactory.class);

    WebDriver getDriverVersion() {
        WebDriver driver;
        String driverVersion = readDriverVersion();
        switch (driverVersion) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver() ;
        }
        return driver;
    }

    private String readDriverVersion() {
        String version;
        version = PropertyController.readProperty(PropertyController.WEBRDIVER);
        return version;
    }

}
