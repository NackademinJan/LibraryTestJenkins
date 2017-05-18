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
public class SingleUser {
    private User user;

    public SingleUser(User user){
        this.user = user;
    }
    /**
     * @return the book
     */
    public User getUser() {
        return user;
    }

    /**
     * @param book the book to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
