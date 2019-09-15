package com.demchyk.home.pageobject;

import com.demchyk.home.core.DriverProvider;
import com.demchyk.home.core.decorator.ExtendedDecorator;
import com.demchyk.home.core.elements.ButtonElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoQaButtonPage extends AbstractPage{

    private final WebDriver driver;

    @FindBy(xpath = "//*[@id='content']/div[2]/div/button" )
    public ButtonElement buttonElement;

    @FindBy(xpath = "//*[@id='content']/div[2]/input")
    public WebElement cssbuttonElement;



    public DemoQaButtonPage(){
        this.driver = DriverProvider.getDriver();
        PageFactory.initElements(new ExtendedDecorator(driver), this);
    }

    @Override
    public void openPage() {
         driver.get("https://demoqa.com/button/");
    }
}
