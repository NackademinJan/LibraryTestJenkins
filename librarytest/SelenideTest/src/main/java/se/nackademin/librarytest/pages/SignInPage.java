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
public class SignInPage extends MenuPage {
    private static final Logger LOG =  Logger.getLogger(SignInPage.class.getName());
    @FindBy(css = "#input-username")
    private SelenideElement userNameField;
    @FindBy(css = "#input-password")
    private SelenideElement passwordField;
    @FindBy(css = "#login-button")
    private SelenideElement logInButton;
    @FindBy(css = ".v-label")
    private SelenideElement responseLabel;
    
    
    public void setUsername(String username){
        setTextFieldValue("username", username, userNameField);
    }
    
    public void setPassword(String password){
        setTextFieldValue("password", password, passwordField);
    }
    
    public String getresponseLabel(){
        LOG.log(Level.INFO, "Getting the sign in page's response message");
        return responseLabel.getText();
    }
    
    public void clickLogin(){
        clickButton("submit button", logInButton);
    }
}
