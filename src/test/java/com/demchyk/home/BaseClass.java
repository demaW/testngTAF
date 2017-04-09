package com.demchyk.home;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseClass {
    @BeforeSuite
    public void cleanUp(){
        Path root = Paths.get("resources/");
        try {
            FileUtils.cleanDirectory(new File(String.valueOf(root)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
