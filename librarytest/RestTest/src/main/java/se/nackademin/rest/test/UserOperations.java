/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.UUID;
import se.nackademin.rest.test.model.AllUsers;
import se.nackademin.rest.test.model.User;

/**
 *
 * @author testautomatisering
 */
public class UserOperations {
    private String jsonString = "";
    
    
    
    //this method gets all users in the system and returns a json style response of the result
    public Response getAllUsers(){
        String resourceName = "users";
        Response getResponse = given().accept(ContentType.JSON).get(GlobVar.BASE_URL + resourceName);
        return getResponse;
    }
    
    //this method gets all users in the system and returns an instance of my helplcass AllUsers with the result
    public AllUsers fetchAllUsers(){
        AllUsers users = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+"users").jsonPath().getObject("users", AllUsers.class);
        return users;
    }
    
    
    //this method gets a specific user by his userId and reutrns a json style response of the result
    public Response getUser(Integer userId){
        String resourceName = "users/"+userId;
        Response response = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+resourceName);
        return response;
    }
    
    //this method gets a specific user by his userId and reutrns an instance of my helplcass User with of the result. 
    public User fetchUser(Integer userId){
        User user = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+"users/"+userId).jsonPath().getObject("user", User.class);
        return user;
    }
    
    //this method gets the last user in the list and returns it as an instance of the User.class
    public User fetchLastUser(){
        Response getResponse = new UserOperations().getAllUsers();
        Integer fetchLastUserId = getResponse.jsonPath().getInt("users.user[-1].id");
        User fetchUser = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+"users/"+fetchLastUserId).jsonPath().getObject("user", User.class);
        return  fetchUser;
    }
    //this method gets the last user in the list and returns it as a json style response
    public Response getLastUser(){
        Response getResponse = new UserOperations().getAllUsers();
        Integer lastUserId = getResponse.jsonPath().getInt("users.user[-1].id");
        Response lastUserResponse = new UserOperations().getUser(lastUserId);
        return  lastUserResponse;
    }
    
    //this method attempts to create a new author with input for a display name, email, user's first and last name, password, phone number and their user-role and then returns a json style response of the result
    public Response createUser(String displayName, String email, String firstName, String lastName,  String password, String phone, String role){
        String resourceName = "users";

        String postBodyTemplate = 
                        "{\n" 
                    +   "  \"user\": {\n" 
                    +   "    \"displayName\": \"%s\",\n" 
                    +   "    \"email\": \"%s\",\n" 
                    +   "    \"firstName\": \"%s\",\n" 
                    +   "    \"lastName\": \"%s\",\n" 
                    +   "    \"password\": \"%s\",\n" 
                    +   "    \"phone\": \"%s\", \n" 
                    +   "    \"role\":\"%s\"\n" 
                    +   "  }\n" 
                    +   "}";
        
        String postBody= String.format(postBodyTemplate, displayName, email, firstName, lastName,  password, phone, role);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(GlobVar.BASE_URL + resourceName);
        return postResponse;
    }
    
    //this method attempts to create a new user with input for userId, a display name, email, user's first and last name, password, phone number and their user-role and then returns a json style response of the result
    public Response createUserWithId(Integer userId, String displayName, String email, String firstName, String lastName,  String password, String phone, String role){
        String resourceName = "users";

        String postBodyTemplate = 
                        "{\n" 
                    +   "  \"user\": {\n" 
                    +   "    \"id\": %s,\n"
                    +   "    \"displayName\": \"%s\",\n" 
                    +   "    \"email\": \"%s\",\n" 
                    +   "    \"firstName\": \"%s\",\n" 
                    +   "    \"lastName\": \"%s\",\n" 
                    +   "    \"password\": \"%s\",\n" 
                    +   "    \"phone\": \"%s\", \n" 
                    +   "    \"role\":\"%s\"\n" 
                    +   "  }\n" 
                    +   "}";
        
        String postBody= String.format(postBodyTemplate, userId, displayName, email, firstName, lastName,  password, phone, role);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(GlobVar.BASE_URL + resourceName);
        return postResponse;
    }
    
    
    //this method attempts to update an existing user specified by its displayName, Then returns a json style response of the result
    public Response updateUser(String displayName, String email, String firstName, String lastName,  String password, String phone, String role){
        String resourceName = "users";

        String postBodyTemplate = 
                        "{\n" 
                    +   "  \"user\": {\n" 
                    +   "    \"displayName\": \"%s\",\n" 
                    +   "    \"email\": \"%s\",\n" 
                    +   "    \"firstName\": \"%s\",\n" 
                    +   "    \"lastName\": \"%s\",\n" 
                    +   "    \"password\": \"%s\",\n" 
                    +   "    \"phone\": \"%s\", \n" 
                    +   "    \"role\":\"%s\"\n" 
                    +   "  }\n" 
                    +   "}";
        
        String putBody= String.format(postBodyTemplate, displayName, email, firstName, lastName,  password, phone, role);
        jsonString = putBody;
        Response putResponse = given().contentType(ContentType.JSON).body(putBody).put(GlobVar.BASE_URL + resourceName);
        return putResponse;
    }
    
    // this method should always return status code 400 and should not update a user because it does not include a users displayname in its request. it also returns a json style response of the result
    public Response invalidUpdateUserWithoutUserDisplayName(String email, String firstName, String lastName,  String password, String phone, String role){
        String resourceName = "users";

        String postBodyTemplate = 
                        "{\n" 
                    +   "  \"user\": {\n"  
                    +   "    \"email\": \"%s\",\n" 
                    +   "    \"firstName\": \"%s\",\n" 
                    +   "    \"lastName\": \"%s\",\n" 
                    +   "    \"password\": \"%s\",\n" 
                    +   "    \"phone\": \"%s\", \n" 
                    +   "    \"role\":\"%s\"\n" 
                    +   "  }\n" 
                    +   "}";
        
        String putBody= String.format(postBodyTemplate, email, firstName, lastName,  password, phone, role);
        jsonString = putBody;
        Response putResponse = given().contentType(ContentType.JSON).body(putBody).put(GlobVar.BASE_URL + resourceName);
        return putResponse;
    }
    
    
    
    
    // this method deletes the last user in the system list of users and then returns a json style response of the result
    public Response deleteLastUser(){   
        Response getResponse = new UserOperations().getAllUsers();
        int fetchedId = getResponse.jsonPath().getInt("users.user[-1].id");
        Response deleteResponse = new UserOperations().deleteUser(fetchedId);
        return deleteResponse;
    }
    
    //this method deletes a specific user based on its userId and then returns a json style response of the result
    public Response deleteUser(Integer userId){
        String deleteResourceName = "users/"+userId;
        
        Response deleteResponse = delete(GlobVar.BASE_URL + deleteResourceName);
        return deleteResponse;
    }
    
    //this method sets/updates a user specified by the userId with base values for all fields taken from the global variables in the GlobVar class
    public Response unPutUser(Integer userId){
        Response unPutAuthorResponse = new UserOperations().updateUser(GlobVar.aDummyUserDisplayName, GlobVar.aDummyUserEmail, GlobVar.aDummyUserFirstName, GlobVar.aDummyUserLastName, GlobVar.aDummyUserPassword, GlobVar.aDummyUserPhone, GlobVar.aDummyUserRole);

        return unPutAuthorResponse;
    }
    
    //this method returns the jsonString variable as a String
    public String getLatestJsonString(){
        return jsonString;
    }
    
    
    
}
