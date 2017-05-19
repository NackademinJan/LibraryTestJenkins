/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test.model;

/**
 *
 * @author testautomatisering
 */
public class SingleLoan {
    private Loan loan;

    public SingleLoan(Loan loan){
        this.loan = loan;
    }
    /**
     * @return the book
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * @param book the book to set
     */
    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
