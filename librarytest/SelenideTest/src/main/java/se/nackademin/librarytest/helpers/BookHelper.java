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
    
    
    
    public static void browseToBook(String searchQuery){
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.navigateToBrowseBooks();
        browseBooksPage.setTitleField(searchQuery);
        browseBooksPage.clickSearchBooksButton();
        browseBooksPage.clickFirstResultTitle();
        
    }
    
    public static void removeBook(String searchQuery){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBook(searchQuery);
        BookPage bookPage = page(BookPage.class);
        bookPage.clickDeleteBookButton();
        
    }
    
    public static void returnBook(){
        BookPage returnBookPage = page(BookPage.class);
        returnBookPage.clickReturnBookButton();
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
    
    //this method is simply to let us test what happens when we enter non-integers into a textfield meant only for Integer data entry
    public static void invalidCreateNewBookWithAuthor(String bookTitle, String authorName, String bookDescription, String bookIsbn, String bookPublicationDate, String bookTotalNbrCopies, String BookNbrPages){
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
        addBookPage.setNbrOfCopiesInInventoryField(bookTotalNbrCopies);
        addBookPage.setNbrOfPagesField(BookNbrPages);
        addBookPage.clickAddBookButton();    
    }
    
    public static void createNewBook(String bookTitle, String bookDescription, String bookIsbn, String bookPublicationDate, Integer bookTotalNbrCopies, Integer BookNbrPages){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddBook();
        AddBookPage addBookPage = page(AddBookPage.class);
        addBookPage.setTitleField(bookTitle);
        addBookPage.setDescriptionField(bookDescription);
        addBookPage.setIsbnField(bookIsbn);
        addBookPage.setDatePublishedField(bookPublicationDate);
        addBookPage.setNbrOfCopiesInInventoryField(bookTotalNbrCopies.toString());
        addBookPage.setNbrOfPagesField(BookNbrPages.toString());
        addBookPage.clickAddBookButton();    
    }
    
    public static void createNewBookWithAuthor(String bookTitle, String authorName, String bookDescription, String bookIsbn, String bookPublicationDate, Integer bookTotalNbrCopies, Integer BookNbrPages){
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
    
    public static void createNewBookWithTwoAuthors(String bookTitle, String authorNameOne, String authorNameTwo, String bookDescription, String bookIsbn, String bookPublicationDate, Integer bookTotalNbrCopies, Integer BookNbrPages){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddBook();
        AddBookPage addBookPage = page(AddBookPage.class);
        addBookPage.setTitleField(bookTitle);
        AuthorToBookTable table =  new AuthorToBookTable($(".v-select-twincol-options"));
        table.SearchAndClick(authorNameOne);
        table.SearchAndClick(authorNameTwo);
        addBookPage.clickAddSelectedAuthorToBookButton();
        addBookPage.setDescriptionField(bookDescription);
        addBookPage.setIsbnField(bookIsbn);
        addBookPage.setDatePublishedField(bookPublicationDate);
        addBookPage.setNbrOfCopiesInInventoryField(bookTotalNbrCopies.toString());
        addBookPage.setNbrOfPagesField(BookNbrPages.toString());
        addBookPage.clickAddBookButton();    
    }
    
    public static void checkErrorSign(){
        AddBookPage addBookPage = page(AddBookPage.class);
        addBookPage.clickErrorSign();
    }
    public static int checkErrorSignInButton(){
        AddBookPage addBookPage = page(AddBookPage.class);
        int n = addBookPage.clickErrorSignInsideButton();
        return n;
    }
    
    
    public static String getMessage(){
        AddBookPage addBookPage = page(AddBookPage.class);
        String Message = addBookPage.getMessage();
        return Message;
    }
    
    public static void editBookDatePublished(String searchQuery, String datePublished){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBook(searchQuery);
        EditBookPage editBookPage = page(EditBookPage.class);
        editBookPage.clickEditBookButton();
        editBookPage.setDatePublishedField(datePublished);
        editBookPage.clickSaveBookButton();
        
    }
    
    public static void editBook(String bookTitleold, String bookTitle, String bookDescription, String bookIsbn, String bookPublicationDate, Integer bookTotalNbrCopies, Integer BookNbrPages){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBook(bookTitleold);
        EditBookPage editBookPage = page(EditBookPage.class);
        editBookPage.clickEditBookButton();
        editBookPage.setTitleField(bookTitle);
        editBookPage.setDescriptionField(bookDescription);
        editBookPage.setIsbnField(bookIsbn);
        editBookPage.setDatePublishedField(bookPublicationDate);
        editBookPage.setNbrOfCopiesInInventoryField(bookTotalNbrCopies.toString());
        editBookPage.setNbrOfPagesField(BookNbrPages.toString());
        editBookPage.clickSaveBookButton();
        
    }
    
    public static void unEditBookAuthor(String searchQuery, String authorFullName){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBook(searchQuery);
        EditBookPage editBookPage = page(EditBookPage.class);
        editBookPage.clickEditBookButton();
        AuthorToBookTable table =  new AuthorToBookTable($(".v-select-twincol-selections"));
        table.SearchAndClick(authorFullName);
        editBookPage.clickRemoveSelectedAuthorfromBookButton();
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
