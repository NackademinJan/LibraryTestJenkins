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
public class AddUserPage extends MenuPage {
    private static final Logger LOG =  Logger.getLogger(AddUserPage.class.getName());
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement userNameField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement passwordField;
    @FindBy(css = "#add-user-button")
    private SelenideElement addUserButton;
    
    
    public void setUsername(String username){
        setTextFieldValue("user name field", username, userNameField);
    }
    
    public void setPassword(String password){
        setTextFieldValue("password field", password, passwordField);
    }
    
    public void clickAddUserButton(){
        clickButton("add user button", addUserButton);
    }
    
}
