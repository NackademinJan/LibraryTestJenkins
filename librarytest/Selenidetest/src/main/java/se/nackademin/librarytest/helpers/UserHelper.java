/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import se.nackademin.librarytest.model.User;
import se.nackademin.librarytest.pages.AddUserPage;
import se.nackademin.librarytest.pages.EditMyProfilePage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.MyProfilePage;
import se.nackademin.librarytest.pages.SignInPage;
import se.nackademin.librarytest.pages.SignOutPage;

/**
 *
 * @author testautomatisering
 */
public class UserHelper {
    
    public static void createNewUserAsLibrarian(String username, String password, String firstName, String lastName, String phone, String email){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddUser();
        
        AddUserPage addUserPage = page(AddUserPage.class);
        addUserPage.setDisplayName(username);
        addUserPage.setPassword(password);
        addUserPage.setFirstName(firstName);
        addUserPage.setLastName(lastName);
        addUserPage.setPhone(phone);
        addUserPage.setEmail(email);
        addUserPage.clickSetRoleLoanerRadioButton();
        addUserPage.clickAddUserButton();
    }
    
    public static void createNewMinimal(String username, String password){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddUser();
        
        AddUserPage addUserPage = page(AddUserPage.class);
        addUserPage.setDisplayName(username);
        addUserPage.setPassword(password);
        addUserPage.clickAddUserButton();
    }
    
    public static void createNewLibrarianUserAsLibrarian(String username, String password, String firstName, String lastName, String phone, String email){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddUser();
        
        AddUserPage addUserPage = page(AddUserPage.class);
        addUserPage.setDisplayName(username);
        addUserPage.setPassword(password);
        addUserPage.setFirstName(firstName);
        addUserPage.setLastName(lastName);
        addUserPage.setPhone(phone);
        addUserPage.setEmail(email);
        addUserPage.clickSetRoleLibrarianRadioButton();
        addUserPage.clickAddUserButton();
    }
    
    public static User fetchUser(String searchQuery, String password, String fetchlist){
        MyProfilePage userPage = page(MyProfilePage.class);
        userPage.navigateToSignIn();
        UserHelper.logInAsUser(searchQuery, password);
        userPage.navigateToMyProfile();
        User user = new User();
        
        if(fetchlist == "all"){
            user.setDisplayName(userPage.getUserName());
            user.setFirstName(userPage.getFirstName());
            user.setLastName(userPage.getLastName());
            user.setPhone(userPage.getUserPhone());
            user.setEmail(userPage.getUserEmail());
            return user;
        } 
        
        return null;
    }
    
    public static void goToMyProfilePage(){
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.navigateToMyProfile();
    }
    
    public static void goCurrentUserFirstBorrowedBook(){
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.clickFirstBorrowedBookTitle();
    }
    
    public static String logOut(){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToSignOut();
        SignOutPage signOutPage = page(SignOutPage.class);
        return signOutPage.getSignOutMessage();
    }
    
    public static String logInAsUser(String username, String password){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToSignIn();
        
        SignInPage signInPage = page(SignInPage.class);
        signInPage.setUsername(username);
        signInPage.setPassword(password);
        signInPage.clickLogin();
        sleep(1000);
        String signedInAs = menuPage.getSignInMessage();
        return signedInAs;
    }
    
    public static String logInAsAdmin(){
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToSignIn();
        
        SignInPage signInPage = page(SignInPage.class);
        signInPage.setUsername("admin");
        signInPage.setPassword("1234567890");
        signInPage.clickLogin();
        String signedInAs = menuPage.getSignInMessage();
        return signedInAs;
    }
    
    public static void editCurrentUserProfile(String displayName, String password, String firstName, String lastName, String phone, String email){
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.navigateToMyProfile();
        myProfilePage.clickEditUserButton();
        
        EditMyProfilePage editMyProfilePage = page(EditMyProfilePage.class);
        editMyProfilePage.setDisplayName(displayName);
        editMyProfilePage.setPassword(password);
        editMyProfilePage.setFirstName(firstName);
        editMyProfilePage.setLastName(lastName);
        editMyProfilePage.setPhone(phone);
        editMyProfilePage.setUserEmail(email);
        editMyProfilePage.clicksaveUserButton();
    }
    
    public static String getCurrentUserProfileEmail(){
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.navigateToMyProfile();
        String userEmail = myProfilePage.getUserEmail();
        return userEmail;
    }
    
    public static String getCurrentUserFirstBorrowedBookTitle(){
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.navigateToMyProfile();
        String usersFirstBorrowedBookTitle = myProfilePage.getFirstBorrowedBookTitle();
        return usersFirstBorrowedBookTitle;
    }
    
    public static void editCurrentUserProfileEmail(String email){
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.navigateToMyProfile();
        myProfilePage.clickEditUserButton();
        
        EditMyProfilePage editMyProfilePage = page(EditMyProfilePage.class);
        editMyProfilePage.setUserEmail(email);
        editMyProfilePage.clicksaveUserButton();
    }
    
    
    public static String getErrorMessage(){
        AddUserPage addUserPage = page(AddUserPage.class);
        String errorMessage = addUserPage.getErrorMessage();
        return errorMessage;
    }
    
    public static void removeCurrentUserProfile(){
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.navigateToMyProfile();
        
        myProfilePage.clickDeleteUserButton();
        
    }
    
}
