/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import java.util.logging.Logger;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author testautomatisering
 */
public class BrowseAuthorsPage extends MenuPage{
    private static final Logger LOG =  Logger.getLogger(BrowseAuthorsPage.class.getName());
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement fullNameField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement countryField;
    @FindBy(css = "#search-authors-button")
    private SelenideElement searchAuthorsButton;
    @FindBy(css = ".v-grid-cell-focused > a:nth-child(1)")
    private SelenideElement firstResultName;
    
    public void clickFirstResultName(){
        clickButton("first name in list resulting from search", firstResultName);
    }
    
    public void setNameField(String name){
        setTextFieldValue("name field", name, fullNameField);
    }
    
    public void setCountryField(String country){
        setTextFieldValue("country field", country, countryField);
    }
    
    public void clickSearchAuthorsButton(){
        clickButton("search button", searchAuthorsButton);
    }
}
