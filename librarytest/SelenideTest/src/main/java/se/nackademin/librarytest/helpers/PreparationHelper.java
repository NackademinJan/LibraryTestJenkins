/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import se.nackademin.librarytest.pages.BrowseAuthorsPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.SignInPage;

/**
 *
 * @author testautomatisering
 */
public class PreparationHelper {
    
    public static void MakeDummyUserAuthorBookAndAdmin(){
        sleep(500);
        UserHelper.logInAsAdmin();
        sleep(500);
        UserHelper.createNewLibrarianUserAsLibrarian(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword, GlobVarSelenide.aDummyUserFirstName, GlobVarSelenide.aDummyUserLastName, GlobVarSelenide.aDummyUserPhone, GlobVarSelenide.aDummyUserEmail);
        //since we cannot delete loaner-role users with the interface, re-runs of this test should not create duplicates of this user, attempting to do so will be tried in another test
        UserHelper.createNewUserAsLibrarian(GlobVarSelenide.bDummyUserDisplayName, GlobVarSelenide.bDummyUserPassword, GlobVarSelenide.bDummyUserFirstName, GlobVarSelenide.bDummyUserLastName, GlobVarSelenide.bDummyUserPhone, GlobVarSelenide.bDummyUserEmail);
        
        AuthorHelper.createNewAuthor(GlobVarSelenide.dummyAuthorFirstName, GlobVarSelenide.dummyAuthorLastName, GlobVarSelenide.dummyAuthorCountry, GlobVarSelenide.dummyAuthorBio);
        
        BookHelper.createNewBookWithAuthor(GlobVarSelenide.aDummyBookTitle, GlobVarSelenide.dummyAuthorFullName, GlobVarSelenide.aDummyBookDescription, GlobVarSelenide.aDummyBookIsbn, GlobVarSelenide.aDummyBookPublicationDate, GlobVarSelenide.aDummyBookTotalNbrCopies, GlobVarSelenide.aDummyBookNbrPages);
        
        //the part below simply makes sure we log out of the admin profile to work from a proper baseline for future tests)
        MenuPage menupage = page(MenuPage.class);
        menupage.navigateToSignOut();
    }
    
    public static void RemoveDummyAuthorBookAndAdmin(){
        
        UserHelper.logInAsUser(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword);
        
        BookHelper.removeBook(GlobVarSelenide.aDummyBookTitle);
        
        AuthorHelper.removeAuthor(GlobVarSelenide.dummyAuthorFullName);
        
        //unfortunately regular users cannot delete themselves and admin/librarian users cannot access them for deletion or role change through the interface, they can only access and delete themselves.
        
        UserHelper.removeCurrentUserProfile();
        //the part below simply makes sure we log out of the deleted profile to work from a proper baseline for future tests)
        MenuPage menupage = page(MenuPage.class);
        menupage.navigateToSignOut();
    }
    
    public static void goToBook(String bookTitle){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleField(bookTitle);
        browseBooksPage.clickSearchBooksButton();
        Table table =  new Table($(".v-grid-tablewrapper"));
        table.SearchAndClick(bookTitle, 0);
    }
    
    public static void goToAuthor(String authorFullName){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseAuthors();
        BrowseAuthorsPage browseAuthorsPage = page(BrowseAuthorsPage.class);
        browseAuthorsPage.setNameField(authorFullName);
        browseAuthorsPage.clickSearchAuthorsButton();
        Table table =  new Table($(".v-grid-tablewrapper"));
        table.SearchAndClick(authorFullName, 0);
    }
    
}
