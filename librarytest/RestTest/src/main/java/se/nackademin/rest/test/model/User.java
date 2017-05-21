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
        if(!(GlobVar.aDummyUserDisplayName == null ? sample.getDisplayName() == null : GlobVar.aDummyUserDisplayName.equals(sample.getDisplayName()))){
            isNotEquals = isNotEquals + "User's displayName was not the same. ";
        }
        if(!(GlobVar.aDummyUserEmail == null ? sample.getEmail() == null : GlobVar.aDummyUserEmail.equals(sample.getEmail()))){
            isNotEquals = isNotEquals + "User's email was not the same. ";
        }
        if(!(GlobVar.aDummyUserFirstName == null ? sample.getFirstName() == null : GlobVar.aDummyUserFirstName.equals(sample.getFirstName()))){
            isNotEquals = isNotEquals + "User's firstName was not the same. ";
        }
        if(!(GlobVar.aDummyUserLastName == null ? sample.getLastName() == null : GlobVar.aDummyUserLastName.equals(sample.getLastName()))){
            isNotEquals = isNotEquals + "User's lastName was not the same. ";
        }
        if(!(GlobVar.aDummyUserPassword == null ? sample.getPassword() == null : GlobVar.aDummyUserPassword.equals(sample.getPassword()))){
            isNotEquals = isNotEquals + "User's password was not the same. ";
        }
        if(!(GlobVar.aDummyUserPhone == null ? sample.getPhone() == null : GlobVar.aDummyUserPhone.equals(sample.getPhone()))){
            isNotEquals = isNotEquals + "User's phone number was not the same. ";
        }
        if(!(GlobVar.aDummyUserRole == null ? sample.getRole() == null : GlobVar.aDummyUserRole.equals(sample.getRole()))){
            isNotEquals = isNotEquals + "User's role was not the same. ";
        }
        return isNotEquals;
    }
    
    public String EqualsBDummyUser(User sample){
        String isNotEquals = "";
        if(!(GlobVar.bDummyUserDisplayName == null ? sample.getDisplayName() == null : GlobVar.bDummyUserDisplayName.equals(sample.getDisplayName()))){
            isNotEquals = isNotEquals + "User's displayName was not the same. ";
        }
        if(!(GlobVar.bDummyUserEmail == null ? sample.getEmail() == null : GlobVar.bDummyUserEmail.equals(sample.getEmail()))){
            isNotEquals = isNotEquals + "User's email was not the same. ";
        }
        if(!(GlobVar.bDummyUserFirstName == null ? sample.getFirstName() == null : GlobVar.bDummyUserFirstName.equals(sample.getFirstName()))){
            isNotEquals = isNotEquals + "User's firstName was not the same. ";
        }
        if(!(GlobVar.bDummyUserLastName == null ? sample.getLastName() == null : GlobVar.bDummyUserLastName.equals(sample.getLastName()))){
            isNotEquals = isNotEquals + "User's lastName was not the same. ";
        }
        if(!(GlobVar.bDummyUserPassword == null ? sample.getPassword() == null : GlobVar.bDummyUserPassword.equals(sample.getPassword()))){
            isNotEquals = isNotEquals + "User's password was not the same. ";
        }
        if(!(GlobVar.bDummyUserPhone == null ? sample.getPhone() == null : GlobVar.bDummyUserPhone.equals(sample.getPhone()))){
            isNotEquals = isNotEquals + "User's phone number was not the same. ";
        }
        if(!(GlobVar.bDummyUserRole == null ? sample.getRole() == null : GlobVar.bDummyUserRole.equals(sample.getRole()))){
            isNotEquals = isNotEquals + "User's role was not the same. ";
        }
        return isNotEquals;
    }
    
    public String EqualsBDummyUserPutToADummyUserId(User sample){
        String isNotEquals = "";
        if(!(GlobVar.bDummyUserDisplayName == null ? sample.getDisplayName() == null : GlobVar.bDummyUserDisplayName.equals(sample.getDisplayName()))){
            isNotEquals = isNotEquals + "User's displayName was not the same. ";
        }
        if(!(GlobVar.bDummyUserEmail == null ? sample.getEmail() == null : GlobVar.bDummyUserEmail.equals(sample.getEmail()))){
            isNotEquals = isNotEquals + "User's email was not the same. ";
        }
        if(!(GlobVar.bDummyUserFirstName == null ? sample.getFirstName() == null : GlobVar.bDummyUserFirstName.equals(sample.getFirstName()))){
            isNotEquals = isNotEquals + "User's firstName was not the same. ";
        }
        if(!(GlobVar.bDummyUserLastName == null ? sample.getLastName() == null : GlobVar.bDummyUserLastName.equals(sample.getLastName()))){
            isNotEquals = isNotEquals + "User's lastName was not the same. ";
        }
        if(!(GlobVar.bDummyUserPassword == null ? sample.getPassword() == null : GlobVar.bDummyUserPassword.equals(sample.getPassword()))){
            isNotEquals = isNotEquals + "User's password was not the same. ";
        }
        if(!(GlobVar.bDummyUserPhone == null ? sample.getPhone() == null : GlobVar.bDummyUserPhone.equals(sample.getPhone()))){
            isNotEquals = isNotEquals + "User's phone number was not the same. ";
        }
        if(!(GlobVar.bDummyUserRole == null ? sample.getRole() == null : GlobVar.bDummyUserRole.equals(sample.getRole()))){
            isNotEquals = isNotEquals + "User's role was not the same. ";
        }
        return isNotEquals;
    }
    
    public String EqualsBDummyMinimalUser(User sample){
        String isNotEquals = "";
        if(!(GlobVar.bDummyUserDisplayName == null ? sample.getDisplayName() == null : GlobVar.bDummyUserDisplayName.equals(sample.getDisplayName()))){
            isNotEquals = isNotEquals + "User's displayName was not the same. ";
        }
        if(!(GlobVar.bDummyUserPassword == null ? sample.getPassword() == null : GlobVar.bDummyUserPassword.equals(sample.getPassword()))){
            isNotEquals = isNotEquals + "User's password was not the same. ";
        }
        if(!(GlobVar.bDummyUserRole == null ? sample.getRole() == null : GlobVar.bDummyUserRole.equals(sample.getRole()))){
            isNotEquals = isNotEquals + "User's role was not the same. ";
        }
        return isNotEquals;
    }

}
