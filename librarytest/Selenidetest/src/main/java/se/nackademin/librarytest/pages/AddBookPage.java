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
public class AddBookPage extends BookPage{
    private static final Logger LOG =  Logger.getLogger(AddBookPage.class.getName());
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement titleField;
    @FindBy(css = ".v-select-twincol-options > option:nth-child(1)")
    private SelenideElement listOfAuthorsFirstEntry;
    @FindBy(css = ".v-select-twincol-buttons > div:nth-child(1)")
    private SelenideElement addSelectedAuthorToBookButton;
    @FindBy(css = "div.v-button:nth-child(3)")
    private SelenideElement removeSelectedAuthorfromBookButton;
    @FindBy(css = "#gwt-uid-9")
    private SelenideElement descriptionField;
    @FindBy(css = "#gwt-uid-13")
    private SelenideElement isbnField;
    @FindBy(css = "#gwt-uid-7")
    private SelenideElement datePublishedField;
    @FindBy(css = "#gwt-uid-5")
    private SelenideElement nbrOfCopiesInInventoryField;
    @FindBy(css = "#gwt-uid-11")
    private SelenideElement nbrOfPagesField;
    @FindBy(css = "#add-book-button")
    private SelenideElement addBookButton;
    
    
    public String getTitleField(){
        LOG.log(Level.INFO, "Getting the book's title");
        return titleField.getText();
    }
    public void setTitleField(String title){
        setTextFieldValue("the book's title", title, titleField);
    }
    
    public String getDescriptionField(){
        LOG.log(Level.INFO, "Getting the book's description");
        return descriptionField.getText();
    }
    public void setDescriptionField(String description){
        setTextFieldValue("the book's description", description, descriptionField);
    }
    
    public String getIsbnField(){
        LOG.log(Level.INFO, "Getting the book's isbn");
        return isbnField.getText();
    }
    public void setIsbnField(String isbn){
        setTextFieldValue("the book's isbn", isbn, isbnField);
    }
    
    public String getDatePublishedField(){
        LOG.log(Level.INFO, "Getting the book's publishing date");
        return datePublishedField.getText();
    }
    public void setDatePublishedField(String date){
        setTextFieldValue("the book's publishing date", date, datePublishedField);
    }
    
    public String getNbrOfCopiesInInventoryField(){
        LOG.log(Level.INFO, "Getting the book's pagecount");
        return nbrOfCopiesInInventoryField.getText();
    }
    public void setNbrOfCopiesInInventoryField(String pagecount){
        setTextFieldValue("the book's pagecount", pagecount, nbrOfCopiesInInventoryField);
    }
    
    public String getNbrOfPagesField(){
        LOG.log(Level.INFO, "Getting the book's pagecount");
        return nbrOfPagesField.getText();
    }
    public void setNbrOfPagesField(String pagecount){
        setTextFieldValue("the book's pagecount", pagecount, nbrOfPagesField);
    }
    
    public void clickListOfAuthorsFirstEntry(){
        clickButton("first entry in list of authors available", listOfAuthorsFirstEntry);
    }
    public void clickAddSelectedAuthorToBookButton(){
        clickButton("add selected author to book button", addSelectedAuthorToBookButton);
    }
    
    public void clickRemoveSelectedAuthorfromBookButton(){
        clickButton("remove selected author from book button", removeSelectedAuthorfromBookButton);
    }
    
    public void clickAddBookButton(){
        clickButton("add book button", addBookButton);
    }
    
    
}
