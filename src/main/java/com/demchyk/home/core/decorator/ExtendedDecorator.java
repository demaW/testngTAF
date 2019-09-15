package com.demchyk.home.core.decorator;

import com.demchyk.home.core.elements.ExtendableElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.List;

public class ExtendedDecorator extends DefaultFieldDecorator {

    public ExtendedDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<ExtendableElement> decoratableClass = decoratableClass(field);
        //check if decoratable class
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            //check if locator present
            if (locator == null) {
                return null;
            }
            //Check if we work with list of WebElements
            if (List.class.isAssignableFrom(field.getType())) {
               return createList(loader, locator, decoratableClass);
            }
            return createElement(loader, locator, decoratableClass);
        }
        //return default WebElement decoration
        return super.decorate(loader, field);
    }

    //Check if or filed can be used for decorator
    private Class<ExtendableElement> decoratableClass(Field field) {
        Class<?> clazz = field.getType();
        if (List.class.isAssignableFrom(clazz)) {
            //Check annotations for List
            if (field.getAnnotation(FindBy.class) == null && field.getAnnotation(FindBys.class) == null) {
                return null;
            }
            Type genericType = field.getGenericType();
            //List must be parameterized
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            //Extracting Class for list
            clazz = (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
        }
        if (ExtendableElement.class.isAssignableFrom(clazz)) {
            return (Class<ExtendableElement>) clazz;
        } else return null;
    }

    protected ExtendableElement createElement(ClassLoader loader,
                                              ElementLocator locator, Class<ExtendableElement> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy);
    }

    @SuppressWarnings("unchecked")
    protected List<ExtendableElement> createList(ClassLoader loader, ElementLocator locator, Class<ExtendableElement> clazz) {
        InvocationHandler handler = new LocatingCustomElementListHandler(locator, clazz);
        return (List<ExtendableElement>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }
}
