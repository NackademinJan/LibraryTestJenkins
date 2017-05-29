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
public class BookPage extends MenuPage{
    private static final Logger LOG =  Logger.getLogger(BookPage.class.getName());
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement titleField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement authorField;
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement descriptionField;
    @FindBy(css = "#gwt-uid-9")
    private SelenideElement isbnField;
    @FindBy(css = "#gwt-uid-11")
    private SelenideElement datePublishedField;
    @FindBy(css = "#gwt-uid-13")
    private SelenideElement nbrOfCopiesAvailableField;
    @FindBy(css = "#gwt-uid-15")
    private SelenideElement totalNbrOfCopiesField;
    @FindBy(css = "#borrow-book-button")
    private SelenideElement borrowBookButton;
    @FindBy(css = "#edit-book-button")
    private SelenideElement editBookButton;
    @FindBy(css = "#return-book-button")
    private SelenideElement returnBookButton;
    @FindBy(css = "#confirmdialog-ok-button")
    private SelenideElement confirmButton;
    @FindBy(css = "#delete-book-button")
    private SelenideElement deleteBookButton;
    @FindBy(css = "#gwt-uid-5 > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > span:nth-child(1)")
    private SelenideElement firstAuthorOfBook;
    
    
    
    public String getTitle(){
        LOG.log(Level.INFO, "Getting the book's title");
        return titleField.getText();
    }
    public String getAuthor(){
        LOG.log(Level.INFO, "Getting name of the book's Author");
        return authorField.getText();
    }
    public String getDescription(){
        LOG.log(Level.INFO, "Getting the book's description");
        return descriptionField.getText();
    }
    
    public String getIsbn(){
        LOG.log(Level.INFO, "Getting the book's isbn");
        return isbnField.getText();
    }
    
    public String getDatePublished(){
        LOG.log(Level.INFO, "Getting the date the book was published");
        return datePublishedField.getText();
    }
    
    public Integer getNbrOfCopiesAvailable(){
        LOG.log(Level.INFO, "Getting the count of how many copies of the book are available");
        return Integer.valueOf(nbrOfCopiesAvailableField.getText());
    }
    
    public void clickFirstAuthorOfBook(){
        clickButton("first author of the book link", firstAuthorOfBook);
    }
    
    public void clickBorrowBookButton(){
        clickButton("borrow book button", borrowBookButton);
        clickButton("yes button", confirmButton);
    }
    
    public void clickReturnBookButton(){
        clickButton("return book button", returnBookButton);
        clickButton("yes button", confirmButton);
    }
    
    public void clickDeleteBookButton(){
        clickButton("delete book button", deleteBookButton);
        clickButton("yes button", confirmButton);
    }
    
    public void clickEditBookButton(){
        clickButton("edit book button", editBookButton);
    }
    
    
}
