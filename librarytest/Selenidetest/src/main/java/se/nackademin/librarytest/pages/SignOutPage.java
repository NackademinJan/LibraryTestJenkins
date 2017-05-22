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
public class SignOutPage extends MenuPage {
    private static final Logger LOG =  Logger.getLogger(SignOutPage.class.getName());
    @FindBy(css = ".v-label")
    private SelenideElement signOutMessageField;
    
   public String getSignOutMessage(){
        LOG.log(Level.INFO, "Getting the signout message");
        return signOutMessageField.getText();
    }
    
}
