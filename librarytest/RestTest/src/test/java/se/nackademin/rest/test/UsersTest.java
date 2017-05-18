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
    public static void MaketheDummyUser(){
        Response makeDummyUserResponse = BeforeAndAfterOperations.makeDummyUser();
        assertEquals("The status code should be: 201",  201, makeDummyUserResponse.statusCode());
        assertEquals("response body should be blank",  "", makeDummyUserResponse.body().asString());
        
    }
    
    @AfterClass //this method removes the dummies created by the previous method
    public static void RemovetheDummyUser(){
        Response removeResponse = BeforeAndAfterOperations.removeDummyUser();
        assertEquals("The status code should be: 204",  204, removeResponse.statusCode());
        assertEquals("response body should be blank",  "", removeResponse.body().asString());
    }
    
    
    //@Test //this test tries to perform a get-request on the api for a list of all users in the system and then verifies that we get the right statuscode (200), the response body is not blank and our dummy user is in the response we get
    public void testGetAllUsers(){
        
        //this part fetches a list of all books
        Response response = new UserOperations().getAllUsers();
        assertEquals("The status code should be: 200",  200, response.statusCode());
        assertNotEquals("The book list should not be empty", "", response.body().asString());
        
        //the next parts fetches the Dummyuser from the list as a User.class object and verifies that it has the right attributes
        AllUsers users = response.jsonPath().getObject("users", AllUsers.class);
        User DummyUser = users.getUserfromUsers(GlobVar.aDummyUserId);
        String isNotEquals = DummyUser.EqualsADummyUser(DummyUser);
        assertEquals("The String should be empty", "", isNotEquals);
    }
    
    //@Test //this test uses my help class AllBooks to fetch all books in the list, then find a single specific book from the response and verify that it has the correct data
    public void testFetchAllUsers(){
        AllUsers users = new UserOperations().fetchAllUsers();
        User DummyUser = users.getUserfromUsers(GlobVar.aDummyUserId);
        String isNotEquals = DummyUser.EqualsADummyUser(DummyUser);
        assertEquals("The String should be empty", "", isNotEquals);     
        
    }
    
    @Test
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
        
    }
    
    
}
