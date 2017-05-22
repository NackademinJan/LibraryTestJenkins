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
public class EditBookPage extends BookPage{
    private static final Logger LOG =  Logger.getLogger(EditBookPage.class.getName());
    @FindBy(css = "#gwt-uid-3")
    private SelenideElement titleField;
    // editing a book's list of authors would require implementing a new helper class for handling the system, so not including a reference to the field here
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
    @FindBy(css = "#save-book-button")
    private SelenideElement saveBookButton;
    
    
    public String getNbrOfPages(){
        LOG.log(Level.INFO, "Getting the book's pagecount");
        return nbrOfPagesField.getText();
    }
    public void setNbrOfPages(String pagecount){
        setTextFieldValue("the book's pagecount", pagecount, nbrOfPagesField);
    }
    public void setDatePublished(String date){
        setTextFieldValue("the book's publishing date", date, datePublishedField);
    }
    
    
    public void clickSaveBookButton(){
        clickButton("save book button", saveBookButton);
    }
    
    
}
