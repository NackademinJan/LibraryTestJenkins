/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import se.nackademin.librarytest.pages.AddBookPage;
import se.nackademin.librarytest.pages.AuthorPage;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.BrowseAuthorsPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.EditMyProfilePage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.MyProfilePage;
import se.nackademin.librarytest.pages.SignInPage;

/**
 *
 * @author testautomatisering
 */
public class NoFindHelper {
    
    public static int cantFindAddBook(){
        MenuPage menuPage = page(MenuPage.class);
        int a = menuPage.cantNavigateToAddBook();
        return a;
    }
    
    public static int cantFindAddAuthor(){
        MenuPage menuPage = page(MenuPage.class);
        int a = menuPage.cantNavigateToAddAuthor();
        return a;
    }
    public static int cantFindAddUser(){
        MenuPage menuPage = page(MenuPage.class);
        int a = menuPage.cantNavigateToAddUser();
        return a;
    }
    public static int cantFindMyProfile(){
        MenuPage menuPage = page(MenuPage.class);
        int a = menuPage.cantNavigateToMyProfile();
        return a;
    }
    public static int cantFindEditBookButton(String searchQuery){
        BookPage bookPage = page(BookPage.class);
        bookPage.navigateToBook(searchQuery);
        int a = bookPage.cantFindEditBookButton();
        return a;
    }
    public static int cantFindBorrowBookButton(String searchQuery){
        BookPage bookPage = page(BookPage.class);
        bookPage.navigateToBook(searchQuery);
        int a = bookPage.cantFindBorrowBookButton();
        return a;
    }
    public static int cantFindReturnBookButton(String searchQuery){
        BookPage bookPage = page(BookPage.class);
        bookPage.navigateToBook(searchQuery);
        int a = bookPage.cantFindReturnBookButton();
        return a;
    }
    public static int cantFindDeleteBookButton(String searchQuery){
        BookPage bookPage = page(BookPage.class);
        bookPage.navigateToBook(searchQuery);
        int a = bookPage.cantFindDeleteBookButton();
        return a;
    }
    public static int cantFindEditAuthorButton(String searchQuery){
        AuthorPage authorPage = page(AuthorPage.class);
        authorPage.navigateToAuthor(searchQuery);
        int a = authorPage.cantFindEditAuthorButton();
        return a;
    }
    public static int cantFindDeleteAuthorButton(String searchQuery){
        AuthorPage authorPage = page(AuthorPage.class);
        authorPage.navigateToAuthor(searchQuery);
        int a = authorPage.cantFindDeleteAuthorButton();
        return a;
    }
    public static int cantFindDeleteUserButton(){
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.navigateToMyProfile();
        int a = myProfilePage.cantFindDeleteUserButton();
        return a;
    }
    public static int cantFindSetRoleButtons(){
        EditMyProfilePage editMyProfilePage = page(EditMyProfilePage.class);
        editMyProfilePage.navigateToMyProfile();
        editMyProfilePage.clickEditUserButton();
        int a = editMyProfilePage.cantFindSetRoleButtons();
        return a;
    }
    
}
