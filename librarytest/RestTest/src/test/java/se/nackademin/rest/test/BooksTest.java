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
import se.nackademin.rest.test.model.Book;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import se.nackademin.rest.test.model.AllAuthors;
import se.nackademin.rest.test.model.AllBooks;
import se.nackademin.rest.test.model.Author;
import se.nackademin.rest.test.model.SingleUser;
import se.nackademin.rest.test.model.User;

/**
 *
 * @author testautomatisering
 */
public class BooksTest {
    
    @BeforeClass //this method creates a dummy book, dummy author and adds the author to the book to be used during test executions
    public static void MaketheDummyBookAndDummyAuthor(){
        Response makeDummysResponse = BeforeAndAfterOperations.makeDummyBookAndDummyAuthor();
        assertEquals("The status code should be: 201",  201, makeDummysResponse.statusCode());
        assertEquals("response body should be blank",  "", makeDummysResponse.body().asString());
        
        Response addDummyAuthorToDummyBook = BeforeAndAfterOperations.addDummyAuthorToDummyBook();
        assertEquals("The status code should be: 200",  200, addDummyAuthorToDummyBook.statusCode());
        assertEquals("response body should be blank",  "", addDummyAuthorToDummyBook.body().asString());
    }
    
    @AfterClass //this method removes the dummies created by the previous method
    public static void RemovetheDummyBookAndDummyAuthor(){
        Response removeResponse = BeforeAndAfterOperations.removeDummyBookAndDummyAuthor();
        assertEquals("The status code should be: 204",  204, removeResponse.statusCode());
        assertEquals("response body should be blank",  "", removeResponse.body().asString());
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
    
    @Test //this test tries to perform a get-request on the api for a list of all books in the system and then verifies that we get the right statuscode (200), the response body is not blank and our dummy book is in the response we get
    public void testGetAllBooks(){
        //this part fetches a list of all books
        Response response = new BookOperations().getAllBooks();
        assertEquals("The status code should be: 200",  200, response.statusCode());
        assertNotEquals("The book list should not be empty", "", response.body().asString());
        
        //the next parts fetches the Dummybook from the list as a book.class object and verifies that it has the right attributes
        AllBooks books = response.jsonPath().getObject("books", AllBooks.class);
        Book DummyBook = books.getBookfromBooks(GlobVar.aDummyBookId);
        
        assertEquals("The books description should be: " + GlobVar.aDummyBookDescription,  GlobVar.aDummyBookDescription, DummyBook.getDescription());       
        assertEquals("The books title should be: " + GlobVar.aDummyBookTitle,  GlobVar.aDummyBookTitle, DummyBook.getTitle());       
        assertEquals("The books isbn should be: " + GlobVar.aDummyBookIsbn,  GlobVar.aDummyBookIsbn, DummyBook.getIsbn());       
        assertEquals("The books page count should be: " + GlobVar.aDummyBookNbrPages,  GlobVar.aDummyBookNbrPages, DummyBook.getNbrPages());       
        
        //the part below gets the same Dummybook as a response and verifies that it contains the name of that book's author
        Response grabAuthorResponse = new BookOperations().getBookById(GlobVar.aDummyBookId);
        String authorName = GlobVar.dummyAuthorFirstName;
        assertNotEquals("The book list should not be empty", "", response.body().asString()); 
        assertTrue("The book list should contain the name of our test author", grabAuthorResponse.body().asString().contains(authorName)); 
                
    }
    
    @Test //this test uses my help class AllBooks to fetch all books in the list, then find a single specific book from the response and verify that it has the correct data
    public void testFetchAllBooks(){
        AllBooks books = new BookOperations().fetchAllBooks();
        Book fetchedBook = books.getBookfromBooks(GlobVar.aDummyBookId);
        assertEquals("The books description should be: " + GlobVar.aDummyBookDescription,  GlobVar.aDummyBookDescription, fetchedBook.getDescription());       
        assertEquals("The books title should be: " + GlobVar.aDummyBookTitle,  GlobVar.aDummyBookTitle, fetchedBook.getTitle());       
        assertEquals("The books isbn should be: " + GlobVar.aDummyBookIsbn,  GlobVar.aDummyBookIsbn, fetchedBook.getIsbn());       
        assertEquals("The books page count should be: " + GlobVar.aDummyBookNbrPages,  GlobVar.aDummyBookNbrPages, fetchedBook.getNbrPages());       
        
    }
    
    
    
}
