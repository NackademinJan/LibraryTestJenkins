/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author testautomatisering
 */
public class AllLoans {
    private ArrayList<Loan> loan; 

    /**
     * @return the books
     */
    public ArrayList<Loan> getLoan() {
        return loan;
    }

    /**
     * @param user
     */
    public void setLoan(ArrayList<Loan> loan) {
        this.loan = loan;
    }
    
    public void addLoan(Loan loan) {
        ArrayList<Loan> temp = this.loan;
        temp.add(loan);
        this.loan = temp;
    }
    
    public void removeLoan(Loan loan) {
        ArrayList<Loan> temp = this.loan;
        boolean removedLoan = temp.remove(loan);
        if(removedLoan)this.loan = temp;
    }
    
    public Loan getLoanfromLoans(Integer id){
        Loan wantedLoan = null;
        for(Loan n: loan){
            Integer loanId = n.getId();
            
            if(Objects.equals(loanId, id)){
                
                wantedLoan = n;
            }
            
        }
        return wantedLoan;
    }
}
