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
import org.openqa.selenium.support.How;

/**
 *
 * @author testautomatisering
 */
public class AddUserPage extends MenuPage {
    private static final Logger LOG =  Logger.getLogger(AddUserPage.class.getName());
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement displayNameField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement passwordField;
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement firstNameField;
    @FindBy(css = "#gwt-uid-9")
    private SelenideElement lastNameField;
    @FindBy(css = "#gwt-uid-11")
    private SelenideElement phoneField;
    @FindBy(css = "#gwt-uid-13")
    private SelenideElement emailField;
    @FindBy(css = "#add-user-button")
    private SelenideElement addUserButton;
    @FindBy(css = "span.v-radiobutton:nth-child(1) > label:nth-child(2)")       
    private SelenideElement setRoleLibrarianRadioButton;
    @FindBy(css = "span.v-radiobutton:nth-child(2) > label:nth-child(2)")
    private SelenideElement setRoleLoanerRadioButton;
    @FindBy(css = ".v-label-undef-w")
    private SelenideElement errorMessage;
    
    
    public void setDisplayName(String username){
        setTextFieldValue("display name field", username, displayNameField);
    }
    
    public void setPassword(String password){
        setTextFieldValue("password field", password, passwordField);
    }
    public void setFirstName(String firstName){
        setTextFieldValue("first name field", firstName, firstNameField);
    }
    public void setLastName(String lastName){
        setTextFieldValue("last name field", lastName, lastNameField);
    }
    public void setPhone(String phoneNumber){
        setTextFieldValue("phone field", phoneNumber, phoneField);
    }
    public void setEmail(String email){
        setTextFieldValue("email field", email, emailField);
    }
    public void clickAddUserButton(){
        clickButton("add user button", addUserButton);
    }
    public void clickSetRoleLibrarianRadioButton(){
        clickButton("set role to librarian radiobutton", setRoleLibrarianRadioButton);
    }
    public void clickSetRoleLoanerRadioButton(){
        clickButton("set role to loaner radiobutton", setRoleLoanerRadioButton);
    }
    
    public String getMessage(){
        LOG.log(Level.INFO, "Getting message produced when adding user");
        return errorMessage.getText();
    }
}