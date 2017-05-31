/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author testautomatisering
 */
public class EditAuthorPage extends AuthorPage {
    private static final Logger LOG =  Logger.getLogger(EditAuthorPage.class.getName());
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement firstNameField;
    @FindBy(css = "#gwt-uid-9")
    private SelenideElement lastNameField;
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement countryField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement biographyField;
    @FindBy(css = "#save-author-button")
    private SelenideElement saveAuthorButton;
    @FindBy(css = ".v-label-undef-w")
    private SelenideElement message;
    
    
    public void setFirstName(String firstName){
        setTextFieldValue("author's first name field", firstName, firstNameField);
    }
    
    public void setLastName(String lastName){
        setTextFieldValue("author's last name field", lastName, lastNameField);
    }
    
    public void setCountry(String country){
        setTextFieldValue("author's home country", country, countryField);
    }
    
    public void setBiography(String biography){
        setTextFieldValue("author's biography", biography, biographyField);
    }
    
    public void clickSaveAuthorButton(){
        clickButton("add author button", saveAuthorButton);
    }
    
    public String getMessage(){
        LOG.log(Level.INFO, "Getting message produced when adding author");
        return message.getText();
    }
}
