    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.model;

import se.nackademin.librarytest.helpers.GlobVarSelenide;

/**
 *
 * @author testautomatisering
 */
public class User {
    private Integer id;
    private String displayName;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String role;
    
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
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    

    public String EqualsADummyUser(User sample){
        String isNotEquals = "";
        if(!(GlobVarSelenide.aDummyUserDisplayName == null ? sample.getDisplayName() == null : GlobVarSelenide.aDummyUserDisplayName.equals(sample.getDisplayName()))){
            isNotEquals = isNotEquals + "User's displayName was not the same. ";
        }
        if(!(GlobVarSelenide.aDummyUserEmail == null ? sample.getEmail() == null : GlobVarSelenide.aDummyUserEmail.equals(sample.getEmail()))){
            isNotEquals = isNotEquals + "User's email was not the same. ";
        }
        if(!(GlobVarSelenide.aDummyUserFirstName == null ? sample.getFirstName() == null : GlobVarSelenide.aDummyUserFirstName.equals(sample.getFirstName()))){
            isNotEquals = isNotEquals + "User's firstName was not the same. ";
        }
        if(!(GlobVarSelenide.aDummyUserLastName == null ? sample.getLastName() == null : GlobVarSelenide.aDummyUserLastName.equals(sample.getLastName()))){
            isNotEquals = isNotEquals + "User's lastName was not the same. ";
        }
        if(!(GlobVarSelenide.aDummyUserPassword == null ? sample.getPassword() == null : GlobVarSelenide.aDummyUserPassword.equals(sample.getPassword()))){
            isNotEquals = isNotEquals + "User's password was not the same. ";
        }
        if(!(GlobVarSelenide.aDummyUserPhone == null ? sample.getPhone() == null : GlobVarSelenide.aDummyUserPhone.equals(sample.getPhone()))){
            isNotEquals = isNotEquals + "User's phone number was not the same. ";
        }
        if(!(GlobVarSelenide.aDummyUserRole == null ? sample.getRole() == null : GlobVarSelenide.aDummyUserRole.equals(sample.getRole()))){
            isNotEquals = isNotEquals + "User's role was not the same. ";
        }
        return isNotEquals;
    }
    
    public String EqualsBDummyUser(User sample){
        String isNotEquals = "";
        if(!(GlobVarSelenide.bDummyUserDisplayName == null ? sample.getDisplayName() == null : GlobVarSelenide.bDummyUserDisplayName.equals(sample.getDisplayName()))){
            isNotEquals = isNotEquals + "User's displayName was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserEmail == null ? sample.getEmail() == null : GlobVarSelenide.bDummyUserEmail.equals(sample.getEmail()))){
            isNotEquals = isNotEquals + "User's email was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserFirstName == null ? sample.getFirstName() == null : GlobVarSelenide.bDummyUserFirstName.equals(sample.getFirstName()))){
            isNotEquals = isNotEquals + "User's firstName was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserLastName == null ? sample.getLastName() == null : GlobVarSelenide.bDummyUserLastName.equals(sample.getLastName()))){
            isNotEquals = isNotEquals + "User's lastName was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserPassword == null ? sample.getPassword() == null : GlobVarSelenide.bDummyUserPassword.equals(sample.getPassword()))){
            isNotEquals = isNotEquals + "User's password was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserPhone == null ? sample.getPhone() == null : GlobVarSelenide.bDummyUserPhone.equals(sample.getPhone()))){
            isNotEquals = isNotEquals + "User's phone number was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserRole == null ? sample.getRole() == null : GlobVarSelenide.bDummyUserRole.equals(sample.getRole()))){
            isNotEquals = isNotEquals + "User's role was not the same. ";
        }
        return isNotEquals;
    }
    
    public String EqualsBDummyUserPutToADummyUserId(User sample){
        String isNotEquals = "";
        if(!(GlobVarSelenide.bDummyUserDisplayName == null ? sample.getDisplayName() == null : GlobVarSelenide.bDummyUserDisplayName.equals(sample.getDisplayName()))){
            isNotEquals = isNotEquals + "User's displayName was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserEmail == null ? sample.getEmail() == null : GlobVarSelenide.bDummyUserEmail.equals(sample.getEmail()))){
            isNotEquals = isNotEquals + "User's email was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserFirstName == null ? sample.getFirstName() == null : GlobVarSelenide.bDummyUserFirstName.equals(sample.getFirstName()))){
            isNotEquals = isNotEquals + "User's firstName was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserLastName == null ? sample.getLastName() == null : GlobVarSelenide.bDummyUserLastName.equals(sample.getLastName()))){
            isNotEquals = isNotEquals + "User's lastName was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserPassword == null ? sample.getPassword() == null : GlobVarSelenide.bDummyUserPassword.equals(sample.getPassword()))){
            isNotEquals = isNotEquals + "User's password was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserPhone == null ? sample.getPhone() == null : GlobVarSelenide.bDummyUserPhone.equals(sample.getPhone()))){
            isNotEquals = isNotEquals + "User's phone number was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserRole == null ? sample.getRole() == null : GlobVarSelenide.bDummyUserRole.equals(sample.getRole()))){
            isNotEquals = isNotEquals + "User's role was not the same. ";
        }
        return isNotEquals;
    }
    
    public String EqualsBDummyMinimalUser(User sample){
        String isNotEquals = "";
        if(!(GlobVarSelenide.bDummyUserDisplayName == null ? sample.getDisplayName() == null : GlobVarSelenide.bDummyUserDisplayName.equals(sample.getDisplayName()))){
            isNotEquals = isNotEquals + "User's displayName was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserPassword == null ? sample.getPassword() == null : GlobVarSelenide.bDummyUserPassword.equals(sample.getPassword()))){
            isNotEquals = isNotEquals + "User's password was not the same. ";
        }
        if(!(GlobVarSelenide.bDummyUserRole == null ? sample.getRole() == null : GlobVarSelenide.bDummyUserRole.equals(sample.getRole()))){
            isNotEquals = isNotEquals + "User's role was not the same. ";
        }
        return isNotEquals;
    }

}
