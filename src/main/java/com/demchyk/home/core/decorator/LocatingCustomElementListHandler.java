package com.demchyk.home.core.decorator;

import com.demchyk.home.core.elements.ExtendableElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class LocatingCustomElementListHandler implements InvocationHandler {

    private final ElementLocator locator;
    private final Class<ExtendableElement> clazz;

    LocatingCustomElementListHandler(ElementLocator locator, Class<ExtendableElement> clazz) {
        this.locator = locator;
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<WebElement> elements = locator.findElements();
        List<ExtendableElement> extendableElementList = new ArrayList<>();
        elements.forEach((elem) -> extendableElementList.add(WrapperFactory.createInstance(clazz, elem)));
        try {
            return method.invoke(extendableElementList, args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}