/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest;

import static com.codeborne.selenide.Selenide.open;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author testautomatisering
 */
public class TestBase {
    
    @Before
    public void Setup(){
        System.setProperty("webdriver.chrome.driver", "/home/testautomatisering/seleniumdrivers/chromedriver");
        System.setProperty("selenide.browser", "Chrome");
        open("http://localhost:8080/librarytest");
    }
    @After
    public void teardown(){
        
        
    }
    public static void littleUp(){
        System.setProperty("webdriver.chrome.driver", "/home/testautomatisering/seleniumdrivers/chromedriver");
        System.setProperty("selenide.browser", "Chrome");
        open("http://localhost:8080/librarytest");
    }


}

