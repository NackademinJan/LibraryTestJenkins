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
public class EditMyProfilePage extends MenuPage{
    private static final Logger LOG =  Logger.getLogger(EditMyProfilePage.class.getName());
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement userDisplayName;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement userPassword;
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement userFirstName;
    @FindBy(css = "#gwt-uid-9")
    private SelenideElement userLastName;
    @FindBy(css = "#gwt-uid-11")
    private SelenideElement userPhone;
    @FindBy(css = "#gwt-uid-13")
    private SelenideElement userEmail;
    @FindBy(css = "#save-user-button")
    private SelenideElement saveUserButton;
    
    public void setDisplayName(String username){
        setTextFieldValue("display name field", username, userDisplayName);
    }
    public void setPassword(String password){
        setTextFieldValue("password field", password, userPassword);
    }
    public void setFirstName(String firstName){
        setTextFieldValue("first name field", firstName, userFirstName);
    }
    public void setLastName(String lastName){
        setTextFieldValue("last name field", lastName, userLastName);
    }
    public void setPhone(String phoneNumber){
        setTextFieldValue("phone field", phoneNumber, userPhone);
    }
    public void setUserEmail(String emailAdress){
        setTextFieldValue("profile email adress", emailAdress, userEmail);
    }
    public void clicksaveUserButton(){
        clickButton("save user button", saveUserButton);
    }
    
}
