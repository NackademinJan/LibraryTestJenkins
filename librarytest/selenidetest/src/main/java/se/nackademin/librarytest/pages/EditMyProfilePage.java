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
    @FindBy(css = "#gwt-uid-13")
    private SelenideElement userEmail;
    @FindBy(css = "#save-user-button")
    private SelenideElement saveUserButton;
    
    
    public void setUserEmail(String emailAdress){
        setTextFieldValue("profile email adress", emailAdress, userEmail);
    }
    
    public void clicksaveUserButton(){
        clickButton("save user button", saveUserButton);
    }
    
}
