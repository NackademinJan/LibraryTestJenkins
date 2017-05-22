/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import se.nackademin.librarytest.pages.BrowseAuthorsPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.MenuPage;

/**
 *
 * @author testautomatisering
 */
public class PreparationHelper {
    
    public static void MakeDummyUserAuthorBookAndAdmin(){
        UserHelper.logInAsAdmin();
        
        UserHelper.createNewAdmin(GlobVar.aDummyUserDisplayName, GlobVar.aDummyUserPassword);
        
        //UserHelper.createNewUser(GlobVar.bDummyUserDisplayName, GlobVar.bDummyUserPassword);
        
        AuthorHelper.createNewAuthor(GlobVar.dummyAuthorFirstName, GlobVar.dummyAuthorLastName, GlobVar.dummyAuthorCountry, GlobVar.dummyAuthorBio);
        
        BookHelper.createNewBookWithSomeAuthor(GlobVar.aDummyBookTitle, GlobVar.dummyAuthorFullName, GlobVar.aDummyBookDescription, GlobVar.aDummyBookIsbn, GlobVar.aDummyBookPublicationDate, GlobVar.aDummyBookTotalNbrCopies, GlobVar.aDummyBookNbrPages);
        
        //the part below simply makes sure we log out of the admin profile to work from a proper baseline for future tests)
        MenuPage menupage = page(MenuPage.class);
        menupage.navigateToSignOut();
    }
    
    public static void RemoveDummyAuthorBookAndAdmin(){
        UserHelper.logInAsUser(GlobVar.aDummyUserDisplayName, GlobVar.aDummyUserPassword);
        
        NavigationHelper.goToBook(GlobVar.aDummyBookTitle);
        
        BookHelper.deleteBook();
        
        AuthorHelper.removeAuthor(GlobVar.dummyAuthorFullName);
        
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
