/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.page;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.pages.AddAuthorPage;
import se.nackademin.librarytest.pages.AuthorPage;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.MenuPage;

/**
 *
 * @author testautomatisering
 */
public class AuthorHelper {
    
    
    public static void createNewAuthor(String firstName, String lastName, String country, String biography){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddAuthor();
        AddAuthorPage addAuthorPage = page(AddAuthorPage.class);
        addAuthorPage.setFirstName(firstName);
        addAuthorPage.setLastName(lastName);
        addAuthorPage.setCountry(country);
        addAuthorPage.setBiography(biography);
        addAuthorPage.clickAddAuthorButton();
    }
    
    public static Author fetchAnAuthorThroughBook(String searchQuery, String fetchlist){
        BookPage bookPage = page(BookPage.class);
        bookPage.navigateToBook(searchQuery);
        bookPage.clickFirstAuthorOfBook();
        
        AuthorPage authorPage = page(AuthorPage.class);
        Author author = new Author();
        
        if("all".equals(fetchlist)){
            author.setFullName(authorPage.getFullName());
            author.setCountry(authorPage.getCountry());
            author.setBiography(authorPage.getBiography());
            return author;
        } 
        if("name".equals(fetchlist)){
            author.setFullName(authorPage.getFullName());
            return author;
        }
        return null;
    }
    public static Author fetchAuthor(String searchQuery, String fetchlist){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAuthor(searchQuery);
        
        AuthorPage authorPage = page(AuthorPage.class);
        Author author = new Author();
        
        if("all".equals(fetchlist)){
            author.setFullName(authorPage.getFullName());
            author.setCountry(authorPage.getCountry());
            author.setBiography(authorPage.getBiography());
            return author;
        } 
        if("name".equals(fetchlist)){
            author.setFullName(authorPage.getFullName());
            return author;
        }
        return null;
    }
    
    public static void removeAuthor(String searchQuery){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAuthor(searchQuery);
        AuthorPage authorPage = page(AuthorPage.class);
        authorPage.clickDeleteAuthorButton();
        
    }
    
    public static String getErrorMessage(){
        AddAuthorPage addAuthorPage = page(AddAuthorPage.class);
        String errorMessage = addAuthorPage.getErrorMessage();
        return errorMessage;
    }
}
