/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import com.codeborne.selenide.SelenideElement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.support.FindBy;
import se.nackademin.librarytest.helpers.NavigationHelper;

/**
 *
 * @author testautomatisering
 */
public class MenuPage extends PageBase{
    private static final Logger LOG =  Logger.getLogger(MenuPage.class.getName());
    @FindBy(css = "#side-menu-link-add-user")
    private SelenideElement addUser;
    @FindBy(css = "#side-menu-link-sign-in")
    private SelenideElement signIn;
    @FindBy(css = "#side-menu-link-sign-out")
    private SelenideElement signOut;
    @FindBy(css = "#side-menu-link-my-profile")
    private SelenideElement myProfile;
    @FindBy(css ="#side-menu-link-browse-books")
    private SelenideElement browseBooks;
    @FindBy(css ="#side-menu-link-browse-authors")
    private SelenideElement browseAuthors;
    @FindBy(css ="#side-menu-link-add-author")
    private SelenideElement addAuthor;
    @FindBy(css ="#side-menu-link-add-book")
    private SelenideElement addBook;
    @FindBy(css =".v-margin-top > div:nth-child(5) > div:nth-child(1)")
    private SelenideElement signInMessage;
    
    
    public String getSignInMessage(){
        LOG.log(Level.INFO, "Getting the main page's response message from signing in");
        return signInMessage.getText();
    }
    public void navigateToBrowseBooks(){
        clickButton("the browse books link", browseBooks);
        sleep(1000);
    }
    
    public void navigateToBrowseAuthors(){
        clickButton("the browse authors link", browseAuthors);
        sleep(1000);
    }
    
    public void navigateToAddUser(){
        clickButton("the add user link", addUser);
        sleep(1000);
    }
    
    public void navigateToSignIn(){
        clickButton("the sign in link", signIn);
        sleep(1000);
    }
    
    public void navigateToMyProfile(){
        clickButton("the my profile link", myProfile);
        sleep(1000);
    }
    
    public void navigateToAddAuthor(){
        clickButton("the add author link", addAuthor);
        sleep(1000);
    }
    public void navigateToAddBook(){
        clickButton("the add author link", addBook);
        sleep(1000);
    }   
    
    public void navigateToBook(String bookTitle){
        LOG.log(Level.INFO, "Navigating to the bookpage for book titled: {0}", bookTitle);
        NavigationHelper.goToBook(bookTitle);
        sleep(1000);
    }
    public void navigateToAuthorOfBook(String bookTitle, String authorFullname){
        LOG.log(Level.INFO, "Navigating to the authorpage for author named: {0}", authorFullname);
        NavigationHelper.goToAuthorOfBook(bookTitle, authorFullname);
        sleep(1000);
    }
    public void navigateToAuthor(String authorFullName){
        LOG.log(Level.INFO, "Navigating to the authorpage for author named: {0}", authorFullName);
        NavigationHelper.goToAuthor(authorFullName);
        sleep(1000);
    }
    
    public void navigateToSignOut(){
        clickButton("the sign out link", signOut);
        sleep(1000);
    }
    
    public int cantNavigateToAddUser(){
        LOG.log(Level.INFO, "Checking that Add User link is not visible");
        int n = $$("#side-menu-link-add-user").size();
        return n;
    }
    
    public int cantNavigateToMyProfile(){
        LOG.log(Level.INFO, "Checking that Sign In link is not visible");
        int n = $$("#side-menu-link-my-profile").size();
        return n;
    }
    
    public int cantNavigateToAddAuthor(){
        LOG.log(Level.INFO, "Checking that Add Author link is not visible");
        int n = $$("#side-menu-link-add-author").size();
        return n;
    }
    public int cantNavigateToAddBook(){
        LOG.log(Level.INFO, "Checking that Add Book link is not visible");
        int n = $$("#side-menu-link-add-book").size();
        return n;
    }  
    
}
