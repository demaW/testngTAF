package com.demchyk.home.core.decorator;

import com.demchyk.home.core.elements.ExtendableElement;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;

class WrapperFactory {

    private WrapperFactory() {
    }

    static ExtendableElement createInstance(Class<ExtendableElement> clazz, WebElement element) {
        try {
            return clazz.getConstructor(WebElement.class).newInstance(element);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            throw new AssertionError("WebElement can't be represented as " + clazz);
        }
    }
}
