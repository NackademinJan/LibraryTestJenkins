
package se.nackademin.librarytest.helpers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;


public class AuthorToBookTable {
    SelenideElement rootElement;
    
    public AuthorToBookTable(SelenideElement rootElement){
        this.rootElement =  rootElement;
    }
    
    public int getRowCount(){
        return rootElement.$$("option").size();
    }
    
    public void SearchAndClick(String query){
        for(int row = 0; row < getRowCount(); row++){
            if(query.equals(rootElement.$$("option").get(row).getText())){
                rootElement.$$("option").get(row).click();
                return;
            }
            
        }
        throw new NoSuchElementException("Could not find option containing " + query);
    }
    
}
