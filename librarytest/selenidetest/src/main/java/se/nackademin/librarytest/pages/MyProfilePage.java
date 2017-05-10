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
public class MyProfilePage extends MenuPage{
    private static final Logger LOG =  Logger.getLogger(MyProfilePage.class.getName());
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement userName;
    @FindBy(css= "#gwt-uid-13")
    private SelenideElement userEmail;
    @FindBy(css = "#edit-user")
    private SelenideElement editUserButton;
    @FindBy(css = "#delete-user-button")
    private SelenideElement deleteUserButton;
    @FindBy(css = "td.v-grid-cell:nth-child(1) > a:nth-child(1)")
    private SelenideElement firstBorrowedBookTitle;
    
    
    public String getUserName(){
        LOG.log(Level.INFO, "Getting the profile's username");
        return userName.getText();
    }
    
    public String getUserEmail(){
        LOG.log(Level.INFO, "Getting the profile's email adress");
        return userEmail.getText();
    }
    
    public String getFirstBorrowedBookTitle(){
        LOG.log(Level.INFO, "Getting the title of the first book in the user's borrowed book list");
        return firstBorrowedBookTitle.getText();
    }
    
    public void clickFirstBorrowedBookTitle(){
        clickButton("title of the first book in the user's borrowed book list", firstBorrowedBookTitle);
    }
    
    public void clickEditUserButton(){
        clickButton("edit user button", editUserButton);
    }
    /* this method is commented out because it could only be used by the admin-account to remove their own profile which we never want to do as part of a test
    public void clickDeleteUserButton(){
        clickButton("delete user button", deleteUserButton);
        clickButton("yes button", okButton);
    }
    */
}
