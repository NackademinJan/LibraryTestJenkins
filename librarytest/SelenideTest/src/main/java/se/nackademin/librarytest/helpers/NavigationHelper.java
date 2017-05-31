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

/**
 *
 * @author testautomatisering
 */
public class NavigationHelper {
    
    public static void goToBook(String bookTitle){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleField(bookTitle);
        browseBooksPage.clickSearchBooksButton();
        sleep(500);
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
    
    public static void goToAuthorOfBook(String bookTitle, String authorFullName){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleField(bookTitle);
        browseBooksPage.clickSearchBooksButton();
        sleep(500);
        Table table =  new Table($(".v-grid-tablewrapper"));
        table.SearchAndClick(authorFullName, 1);
    }
    
}
