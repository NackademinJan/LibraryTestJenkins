/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import static com.codeborne.selenide.Selenide.$$;
import com.codeborne.selenide.SelenideElement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author testautomatisering
 */
public class AuthorPage extends MenuPage{
    private static final Logger LOG =  Logger.getLogger(AuthorPage.class.getName());
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement fullNameField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement countryField;
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement biographyField;
    @FindBy(css = ".v-grid-cell-focused > a:nth-child(1)")
    private SelenideElement firstBookInList;
    @FindBy(css = "#edit-author-button")
    private SelenideElement editAuthorButton;
    @FindBy(css = "#delete-author-button")
    private SelenideElement deleteAuthorButton;
    @FindBy(css = "#confirmdialog-ok-button")
    private SelenideElement okButton;
    
    public String getFullName(){
        LOG.log(Level.INFO, "Getting the Author's full name");
        return fullNameField.getText();
    }
    
    public String getCountry(){
        LOG.log(Level.INFO, "Getting Author's home country");
        return countryField.getText();
    }
    
    public String getBiography(){
        LOG.log(Level.INFO, "Getting Author's biography");
        return biographyField.getText();
    }
    
    public void clickfirstBookInList(){
        clickButton("first book title in list", firstBookInList);
    }
    
    public void clickEditAuthorButton(){
        clickButton("edit Author button", editAuthorButton);
    }
    
    public void clickDeleteAuthorButton(){
        clickButton("delete author button", deleteAuthorButton);
        clickButton("yes button", okButton);
    }
    public int cantFindEditAuthorButton(){
        LOG.log(Level.INFO, "Checking that Edit Aytgir Button is not visible");
        int n = $$("#edit-author-button").size();
        return n;
    }
    public int cantFindDeleteAuthorButton(){
        LOG.log(Level.INFO, "Checking that Delete Author Button is not visible");
        int n = $$("#delete-author-button").size();
        return n;
    }
}
