package com.demchyk.home;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @BeforeClass
    public void setUp(){
        System.out.println("In regression");
    }

    @Test
    public void method1Test(){
        System.out.println("Method 1");
    }

    @Test void method2Test(){
        System.out.println("Method 2");
    }
}
