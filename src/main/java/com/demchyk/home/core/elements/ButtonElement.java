package com.demchyk.home.core.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class ButtonElement extends ExtendableElement {

    private static final Logger log = Logger.getLogger(ButtonElement.class);

    public ButtonElement(WebElement webElement) {
        super(webElement);
    }

    public void pressButton(){
        webElement.click();
        log.info("Clicked on Button");
    }
}
