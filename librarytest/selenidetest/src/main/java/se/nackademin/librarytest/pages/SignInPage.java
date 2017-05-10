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
public class SignInPage extends MenuPage {
    private static final Logger LOG =  Logger.getLogger(SignInPage.class.getName());
    @FindBy(css = "#input-username")
    private SelenideElement userNameField;
    @FindBy(css = "#input-password")
    private SelenideElement passwordField;
    @FindBy(css = "#login-button")
    private SelenideElement logInButton;
    
    
    public void setUsername(String username){
        setTextFieldValue("username", username, userNameField);
    }
    
    public void setPassword(String password){
        setTextFieldValue("password", password, passwordField);
    }
    
    public void clickLogin(){
        clickButton("submit button", logInButton);
    }
}
