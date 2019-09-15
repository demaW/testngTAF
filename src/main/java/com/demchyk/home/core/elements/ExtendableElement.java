package com.demchyk.home.core.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class ExtendableElement {

    protected WebElement webElement;
    protected WebDriver webDriver;

    public ExtendableElement(WebElement webElement) {
        this.webElement = webElement;
    }


    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
