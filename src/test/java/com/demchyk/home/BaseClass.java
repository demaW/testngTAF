package com.demchyk.home;

import com.demchyk.home.core.DriverProvider;
import com.demchyk.home.utils.PropertyController;
import com.demchyk.home.utils.exceptions.NoSuchPropertyException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    Logger logger = Logger.getLogger(BaseClass.class);

    @BeforeSuite
    public void cleanUp() {
        logger.info("resource dir about to be cleared");
        Path root = Paths.get("resources/");
        try {
            FileUtils.cleanDirectory(new File(String.valueOf(root)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("resource dir cleared");
        DriverProvider.getDriver().manage().timeouts().pageLoadTimeout(Long.parseLong(PropertyController.readProperty(
                PropertyController.DRIVER_PAGE_LOAD_WAIT)), TimeUnit.SECONDS);
        DriverProvider.getDriver().manage().timeouts().implicitlyWait(Long.parseLong(PropertyController.readProperty(
                PropertyController.DRIVER_WAIT_IMPLICITLY)), TimeUnit.SECONDS);
    }
}
