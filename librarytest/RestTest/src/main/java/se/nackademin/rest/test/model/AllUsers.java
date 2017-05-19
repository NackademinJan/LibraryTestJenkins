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
public class AllUsers {
    private ArrayList<User> user; 

    /**
     * @return the books
     */
    public ArrayList<User> getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(ArrayList<User> user) {
        this.user = user;
    }
    
    public void addUser(User user) {
        ArrayList<User> temp = this.user;
        temp.add(user);
        this.user = temp;
    }
    
    public void removeUser(User user) {
        ArrayList<User> temp = this.user;
        boolean removedUser = temp.remove(user);
        if(removedUser)this.user = temp;
    }
    
    public User getUserfromUsers(Integer id){
        User wantedUser = null;
        for(User n: user){
            Integer userId = n.getId();
            
            if(Objects.equals(userId, id)){
                
                wantedUser = n;
            }
            
        }
        return wantedUser;
    }
}
