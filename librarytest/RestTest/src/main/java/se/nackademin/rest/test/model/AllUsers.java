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
    private ArrayList<User> users; 

    /**
     * @return the books
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * @param users
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    
    public void addUser(User user) {
        ArrayList<User> temp = this.users;
        temp.add(user);
        this.users = temp;
    }
    
    public void removeUser(User user) {
        ArrayList<User> temp = this.users;
        boolean removedUser = temp.remove(user);
        if(removedUser)this.users = temp;
    }
    
    public User getUserfromUsers(Integer id){
        User wantedUser = null;
        for(User n: users){
            Integer userId = n.getId();
            
            if(Objects.equals(userId, id)){
                
                wantedUser = n;
            }
            
        }
        return wantedUser;
    }
}
