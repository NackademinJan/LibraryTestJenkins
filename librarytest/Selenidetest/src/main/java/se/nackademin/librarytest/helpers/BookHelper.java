/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import java.util.concurrent.ThreadLocalRandom;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.pages.AddBookPage;
import se.nackademin.librarytest.pages.AuthorPage;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.EditBookPage;
import se.nackademin.librarytest.pages.MenuPage;

/**
 *
 * @author testautomatisering
 */
public class BookHelper {
    
    
    
    public static Integer browseBook(String searchQuery){
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.navigateToBrowseBooks();
        browseBooksPage.setTitleField(searchQuery);
        browseBooksPage.clickSearchBooksButton();
        browseBooksPage.clickFirstResultTitle();
        
        BookPage bookPage = page(BookPage.class);
        Integer beforeBorrowing = bookPage.getNbrOfCopiesAvailable();
        bookPage.clickBorrowBookButton();
        return beforeBorrowing;
    }
    
    public static void deleteBook(){
        BookPage deleteBookPage = page(BookPage.class);
        deleteBookPage.clickDeleteBookButton();
    }
    
    public static Integer borrowBook(String searchQuery){
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.navigateToBrowseBooks();
        browseBooksPage.setTitleField(searchQuery);
        browseBooksPage.clickSearchBooksButton();
        browseBooksPage.clickFirstResultTitle();
        
        BookPage bookPage = page(BookPage.class);
        Integer beforeBorrowing = bookPage.getNbrOfCopiesAvailable();
        bookPage.clickBorrowBookButton();
        return beforeBorrowing;
    }
    
    public static Book fetchABookThroughAuthor(String searchQuery, String fetchlist){
        AuthorPage authorPage = page(AuthorPage.class);
        authorPage.navigateToAuthor(searchQuery);
        authorPage.clickfirstBookInList();
        
        BookPage bookPage = page(BookPage.class);
        Book book = new Book();
        
        if(fetchlist == "all"){
            book.setTitle(bookPage.getTitle());
            book.setAuthor(bookPage.getAuthor());
            book.setIsbn(bookPage.getIsbn());
            book.setDescription(bookPage.getDescription());
            book.setDatePublished(bookPage.getDatePublished());
            book.setNbrAvailable(bookPage.getNbrOfCopiesAvailable());
            return book;
        } 
        if(fetchlist == "date"){
        book.setDatePublished(bookPage.getDatePublished());
        return book;
        }
        return null;
    }
    
    public static Book fetchBook(String searchQuery, String fetchlist){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBook(searchQuery);
        
        BookPage bookPage = page(BookPage.class);
        Book book = new Book();
        
        if(fetchlist == "all"){
            book.setTitle(bookPage.getTitle());
            book.setAuthor(bookPage.getAuthor());
            book.setIsbn(bookPage.getIsbn());
            book.setDescription(bookPage.getDescription());
            book.setDatePublished(bookPage.getDatePublished());
            book.setNbrAvailable(bookPage.getNbrOfCopiesAvailable());
            return book;
        } 
        if(fetchlist == "date"){
        book.setDatePublished(bookPage.getDatePublished());
        return book;
        }
        return null;
    }
    
    
    // presently the authorName string doesnt get used cause I cant figure out a good way to tell selenide what option in the list of the left collum to select, not knowing in advance how many authors will be in the system
    public static void createNewBookWithNewestAuthor(String bookTitle, String authorName, String bookDescription, String bookIsbn, String bookPublicationDate, Integer bookTotalNbrCopies, Integer BookNbrPages){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddBook();
        AddBookPage addBookPage = page(AddBookPage.class);
        addBookPage.setTitleField(bookTitle);
        AuthorToBookTable table =  new AuthorToBookTable($(".v-select-twincol-options"));
        table.SearchAndClick(authorName);
        addBookPage.clickAddSelectedAuthorToBookButton();
        addBookPage.setDescriptionField(bookDescription);
        addBookPage.setIsbnField(bookIsbn);
        addBookPage.setDatePublishedField(bookPublicationDate);
        addBookPage.setNbrOfCopiesInInventoryField(bookTotalNbrCopies.toString());
        addBookPage.setNbrOfPagesField(BookNbrPages.toString());
        addBookPage.clickAddBookButton();
        
    }
    
    public static void editBookDatePublished(String searchQuery, String datePublished){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBook(searchQuery);
        EditBookPage editBookPage = page(EditBookPage.class);
        editBookPage.clickEditBookButton();
        editBookPage.setDatePublished(datePublished);
        editBookPage.clickSaveBookButton();
        
    }
    
    public static String makeRandomPublishDate(){
        //small generator of random date-strings of appropriate format but that will not generate dates with days above 28:
        Integer randomYear = ThreadLocalRandom.current().nextInt(1500, 2018);
        String year = String.valueOf(randomYear);
        Integer randomMonth = ThreadLocalRandom.current().nextInt(1, 13);
        String month;
        if(randomMonth < 10){
            month = "0" + String.valueOf(randomMonth);
        } else {
            month = String.valueOf(randomMonth);
        }
        int daylimit;
        switch(month){
            case "01": daylimit = 32;
            break;
            case "02": daylimit = 29;
            break;
            case "03": daylimit = 32;
            break;
            case "04": daylimit = 31;
            break;
            case "05": daylimit = 32;
            break;
            case "06": daylimit = 31;
            break;
            case "07": daylimit = 32;
            break;
            case "08": daylimit = 32;
            break;
            case "09": daylimit = 31;
            break;
            case "10": daylimit = 32;
            break;
            case "11": daylimit = 31;
            break;
            case "12": daylimit = 32;
            break;
            default: daylimit = 29;   
        }
        Integer randomDay = ThreadLocalRandom.current().nextInt(1, daylimit);
        String day;
        if(randomDay < 10){
            day = "0" + String.valueOf(randomDay);
        } else {
            day = String.valueOf(randomDay);
        }
        String randomPublishedDate = year+"-"+month+"-"+day;
        return randomPublishedDate;
    }
    
}
