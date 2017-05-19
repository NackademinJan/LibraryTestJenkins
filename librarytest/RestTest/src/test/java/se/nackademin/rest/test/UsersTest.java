/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import org.junit.Test;

import com.jayway.restassured.response.Response;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import se.nackademin.rest.test.model.AllUsers;
import se.nackademin.rest.test.model.Book;
import se.nackademin.rest.test.model.SingleBook;
import se.nackademin.rest.test.model.SingleUser;
import se.nackademin.rest.test.model.User;

/**
 *
 * @author testautomatisering
 */
public class UsersTest {
    
    @BeforeClass //this method creates a dummy book, dummy author and adds the author to the book to be used during test executions
    public static void MakeADummyUser(){
        Response makeDummyUserResponse = BeforeAndAfterOperations.makeADummyUser();
        assertEquals("The status code should be: 201",  201, makeDummyUserResponse.statusCode());
        assertEquals("response body should be blank",  "", makeDummyUserResponse.body().asString());
        
    }
    
    @AfterClass //this method removes the dummies created by the previous method
    public static void RemoveADummyUser(){
        Response removeResponse = BeforeAndAfterOperations.removeADummyUser();
        assertEquals("The status code should be: 204",  204, removeResponse.statusCode());
        assertEquals("response body should be blank",  "", removeResponse.body().asString());
    }
    
    
    @Test //this test tries to perform a get-request on the api for a list of all users in the system and then verifies that we get the right statuscode (200), the response body is not blank and our dummy user is in the response we get
    public void testGetAllUsers(){
        
        //this part fetches a list of all books
        Response response = new UserOperations().getAllUsers();
        assertEquals("The status code should be: 200",  200, response.statusCode());
        assertNotEquals("The resulting list should not be empty", "", response.body().asString());
        
        Response grabUserResponse = new UserOperations().getUser(GlobVar.aDummyUserId);
        String userDisplayName = GlobVar.aDummyUserDisplayName;
        assertNotEquals("The list should not be empty", "", grabUserResponse.body().asString()); 
        assertTrue("The list should contain the displayName of our user", grabUserResponse.body().asString().contains(userDisplayName)); 
        
    }
    
    @Test //this test uses my help class AllBooks to fetch all books in the list, then find a single specific book from the response and verify that it has the correct data
    public void testFetchAllUsers(){
        AllUsers users = new UserOperations().fetchAllUsers();
        System.out.println(users.toString());
        User DummyUser = users.getUserfromUsers(GlobVar.aDummyUserId);
        String isNotEquals = DummyUser.EqualsADummyUser(DummyUser);
        assertEquals("The String isNotEquals should be empty", "", isNotEquals);
        
    }
    
    @Test //this test creates a new alternate dummy user using my User class, verifies that we get the right response code (201), a blank response body and then fetches the resulting user to verify that its data matches what we entered. It then deletes the new user to keep things tidy
    public void testPostUser(){
        User user = new User();
        user.setDisplayName(GlobVar.bDummyUserDisplayName);
        user.setEmail(GlobVar.bDummyUserEmail);
        user.setFirstName(GlobVar.bDummyUserFirstName);
        user.setLastName(GlobVar.bDummyUserLastName);
        user.setPassword(GlobVar.bDummyUserPassword);
        user.setPhone(GlobVar.bDummyUserPhone);
        user.setRole(GlobVar.bDummyUserRole);
        SingleUser singleUser = new SingleUser(user);
        
        Response response = given().contentType(ContentType.JSON).body(singleUser).post(GlobVar.BASE_URL+"users");
        assertEquals("The status code should be: 201",  201, response.statusCode());
        assertEquals("response body should be blank", "", response.body().asString());
        
        User postedUser = new UserOperations().fetchLastUser();
        String isNotEquals = postedUser.EqualsBDummyUser(user);
        assertEquals("The String isNotEquals should be empty", "", isNotEquals);
        
        //this part removes our alternate dummy user to keep things tidy if we have successfully created a user to remove
        if(response.statusCode() == 201){
            Response removeResponse = new UserOperations().deleteLastUser();
            assertEquals("The status code should be: 204",  204, removeResponse.statusCode());
            assertEquals("response body should be blank", "", response.body().asString());
        }
    }
    
    //@Test
    public void testPutUser(){
        User user = new User();
        user.setId(GlobVar.aDummyUserId);
        user.setDisplayName(GlobVar.bDummyUserDisplayName);
        user.setEmail(GlobVar.bDummyUserEmail);
        user.setFirstName(GlobVar.bDummyUserFirstName);
        user.setLastName(GlobVar.bDummyUserLastName);
        user.setPassword(GlobVar.bDummyUserPassword);
        user.setPhone(GlobVar.bDummyUserPhone);
        user.setRole(GlobVar.bDummyUserRole);
        SingleUser singleUser = new SingleUser(user);
        
        Response response = given().contentType(ContentType.JSON).body(singleUser).put(GlobVar.BASE_URL+"users");
        assertEquals("The status code should be: 200",  200, response.statusCode());
        assertEquals("response body should be blank", "", response.body().asString());
        
        User postedUser = new UserOperations().fetchLastUser();
        String isNotEquals = postedUser.EqualsBDummyUser(user);
        assertEquals("The String isNotEquals should be empty", "", isNotEquals);
        
        Response removeResponse = new UserOperations().unPutUser(GlobVar.aDummyUserId);
        assertEquals("The status code should be: 200",  200, removeResponse.statusCode());
        assertEquals("response body should be blank", "", response.body().asString());
    }
    
    
    @Test //this test verifies that you cannot perform a delete request to delete all users
    public void testForbiddenDeleteUsers(){
        String resourceName = "users";
        Response response = given().accept(ContentType.JSON).delete(GlobVar.BASE_URL+resourceName);
        assertEquals("The status code should be: 405, method not allowed",  405, response.statusCode());  
        assertEquals("response body should be blank",  "", response.body().asString());
    }
    
    
    @Test //this test simply retrieves our dummyUser by their userId and verifies that it has the correct, unique, displayname without using my book-class objects. it also verifies that we got the appropriate responce statuscode.
    public void testGetUser(){
        Response getUserResponse = new UserOperations().getUser(GlobVar.aDummyUserId);
        assertEquals("The status code should be: 200",  200, getUserResponse.statusCode());
        assertNotEquals("The response body should not be empty", "", getUserResponse.body().asString());
        assertEquals("User DisplayName should be " + GlobVar.aDummyUserDisplayName,  GlobVar.aDummyUserDisplayName, getUserResponse.body().jsonPath().getString("user.displayName"));
        
    }
    
    @Test //this test retrieves our dummyUser by their userId and verifies that it has all the correct data, aka that we got the right one
    public void testFetchUser(){
        User user = new UserOperations().fetchUser(GlobVar.aDummyUserId);
        String isNotEquals = user.EqualsADummyUser(user);
        assertEquals("The String isNotEquals should be empty", "", isNotEquals);
        
    }
    
    
    @Test //this test verifies that you cannot perform a post request to a given user by their userId at "users/{userId}"
    public void testForbiddenPostToUserId(){
        String resourceName = "users/"+GlobVar.aDummyUserId;
        Response response = given().accept(ContentType.JSON).post(GlobVar.BASE_URL+resourceName);
        assertEquals("The status code should be: 405, method not allowed",  405, response.statusCode());  
        assertEquals("response body should be blank",  "", response.body().asString());
    }
    
    @Test //this test verifies that you cannot perform a put request to a given user by their userId at "users/{userId}"
    public void testForbiddenPutToUserId(){
        String resourceName = "users/"+GlobVar.aDummyUserId;
        Response response = given().accept(ContentType.JSON).put(GlobVar.BASE_URL+resourceName);
        assertEquals("The status code should be: 405, method not allowed",  405, response.statusCode());  
        assertEquals("response body should be blank",  "", response.body().asString());
    }
    
    
    @Test
    public void testDeleteUser(){
        // this part creates an alternate dummy user for us to try to delete
        User user = new User();
        user.setDisplayName(GlobVar.bDummyUserDisplayName);
        user.setPassword(GlobVar.bDummyUserPassword);
        user.setRole(GlobVar.bDummyUserRole);
        SingleUser singleUser = new SingleUser(user);
        
        Response response = given().contentType(ContentType.JSON).body(singleUser).post(GlobVar.BASE_URL+"users");
        assertEquals("The status code should be: 201",  201, response.statusCode());
        assertEquals("response body should be blank", "", response.body().asString());
        
        User postedUser = new UserOperations().fetchLastUser();
        String isNotEquals = postedUser.EqualsBDummyUser(user);
        assertEquals("The String isNotEquals should be empty", "", isNotEquals);
        
        //this part removes our alternate dummy user to keep things tidy if we have successfully created a user to remove
        if(response.statusCode() == 201){
            Response removeResponse = new UserOperations().deleteLastUser();
            assertEquals("The status code should be: 204",  204, removeResponse.statusCode());
            assertEquals("response body should be blank", "", response.body().asString());
        }
        
        
        
    }
    
}
