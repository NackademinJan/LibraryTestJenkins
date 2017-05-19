    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test.model;

import se.nackademin.rest.test.GlobVar;

/**
 *
 * @author testautomatisering
 */
public class Loan {
    private Integer id;
    private Book book;
    private String dateBorrowed;
    private String dateDue;
    private User user;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return the dateBorrowed
     */
    public String getDateBorrowed() {
        return dateBorrowed;
    }

    /**
     * @param dateBorrowed the dateBorrowed to set
     */
    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    /**
     * @return the dateDue
     */
    public String getDateDue() {
        return dateDue;
    }

    /**
     * @param dateDue the dateDue to set
     */
    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    public String EqualsADummyLoan(Loan sample){
        String isNotEquals = "";
        if(!(GlobVar.aDummyLoanBook.getTitle() == null ? sample.getBook().getTitle() == null : GlobVar.aDummyLoanBook.getTitle().equals(sample.getBook().getTitle()))){
            isNotEquals = isNotEquals + "Loan's book was not the same. ";
        }
        if(!(GlobVar.aDummyDateBorrowed == null ? sample.getDateBorrowed() == null : GlobVar.aDummyDateBorrowed.equals(sample.getDateBorrowed()))){
            isNotEquals = isNotEquals + "Loan's dateBorrowed was not the same. ";
        }
        if(!(GlobVar.aDummyDateDue == null ? sample.getDateDue() == null : GlobVar.aDummyDateDue.equals(sample.getDateDue()))){
            isNotEquals = isNotEquals + "Loan's dateDue was not the same. ";
        }
        if(!(GlobVar.aDummyLoanUser.getDisplayName() == null ? sample.getUser().getDisplayName() == null : GlobVar.aDummyLoanUser.getDisplayName().equals(sample.getUser().getDisplayName()))){
            isNotEquals = isNotEquals + "Loan's User was not the same. ";
        }
        return isNotEquals;
    }
    
    public String EqualsBDummyLoan(Loan sample){
        String isNotEquals = "";
        if(!(GlobVar.bDummyLoanBook.getTitle() == null ? sample.getBook().getTitle() == null : GlobVar.bDummyLoanBook.getTitle().equals(sample.getBook().getTitle()))){
            isNotEquals = isNotEquals + "Loan's book was not the same. ";
        }
        if(!(GlobVar.bDummyDateBorrowed == null ? sample.getDateBorrowed() == null : GlobVar.bDummyDateBorrowed.equals(sample.getDateBorrowed()))){
            isNotEquals = isNotEquals + "Loan's dateBorrowed was not the same. ";
        }
        if(!(GlobVar.bDummyDateDue == null ? sample.getDateDue() == null : GlobVar.bDummyDateDue.equals(sample.getDateDue()))){
            isNotEquals = isNotEquals + "Loan's dateDue was not the same. ";
        }
        if(!(GlobVar.bDummyLoanUser.getDisplayName() == null ? sample.getUser().getDisplayName() == null : GlobVar.bDummyLoanUser.getDisplayName().equals(sample.getUser().getDisplayName()))){
            isNotEquals = isNotEquals + "Loan's User was not the same. ";
        }
        return isNotEquals;
    }
    
    public String EqualsMixedADummyLoanWithBBook(Loan sample){
        String isNotEquals = "";
        if(!(GlobVar.bDummyLoanBook.getTitle() == null ? sample.getBook().getTitle() == null : GlobVar.bDummyLoanBook.getTitle().equals(sample.getBook().getTitle()))){
            isNotEquals = isNotEquals + "Loan's book was not the same. ";
        }
        if(!(GlobVar.aDummyDateBorrowed == null ? sample.getDateBorrowed() == null : GlobVar.aDummyDateBorrowed.equals(sample.getDateBorrowed()))){
            isNotEquals = isNotEquals + "Loan's dateBorrowed was not the same. ";
        }
        if(!(GlobVar.aDummyDateDue == null ? sample.getDateDue() == null : GlobVar.aDummyDateDue.equals(sample.getDateDue()))){
            isNotEquals = isNotEquals + "Loan's dateDue was not the same. ";
        }
        if(!(GlobVar.aDummyLoanUser.getDisplayName() == null ? sample.getUser().getDisplayName() == null : GlobVar.aDummyLoanUser.getDisplayName().equals(sample.getUser().getDisplayName()))){
            isNotEquals = isNotEquals + "Loan's User was not the same. ";
        }
        return isNotEquals;
    }
    
    public String EqualsMixedADummyLoanWithBUser(Loan sample){
        String isNotEquals = "";
        if(!(GlobVar.aDummyLoanBook.getTitle() == null ? sample.getBook().getTitle() == null : GlobVar.aDummyLoanBook.getTitle().equals(sample.getBook().getTitle()))){
            isNotEquals = isNotEquals + "Loan's book was not the same. ";
        }
        if(!(GlobVar.aDummyDateBorrowed == null ? sample.getDateBorrowed() == null : GlobVar.aDummyDateBorrowed.equals(sample.getDateBorrowed()))){
            isNotEquals = isNotEquals + "Loan's dateBorrowed was not the same. ";
        }
        if(!(GlobVar.aDummyDateDue == null ? sample.getDateDue() == null : GlobVar.aDummyDateDue.equals(sample.getDateDue()))){
            isNotEquals = isNotEquals + "Loan's dateDue was not the same. ";
        }
        if(!(GlobVar.bDummyLoanUser.getDisplayName() == null ? sample.getUser().getDisplayName() == null : GlobVar.bDummyLoanUser.getDisplayName().equals(sample.getUser().getDisplayName()))){
            isNotEquals = isNotEquals + "Loan's User was not the same. ";
        }
        return isNotEquals;
    }
    
}
