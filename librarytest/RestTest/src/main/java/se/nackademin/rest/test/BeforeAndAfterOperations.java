/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import se.nackademin.rest.test.model.Book;
import se.nackademin.rest.test.model.SingleBook;

/**
 *
 * @author testautomatisering
 */
public class BeforeAndAfterOperations {

    
    public static Response makeDummyBookAndDummyAuthor(){
        Response postBookResponse = new BookOperations().createBookWithInput(GlobVar.dummyBookDescription, GlobVar.dummyBookIsbn, GlobVar.dummyBookNbrPages, GlobVar.dummyBookPublicationDate, GlobVar.dummyBookTitle, GlobVar.dummyBookTotalNbrCopies);
        if(!( 201 == postBookResponse.getStatusCode() )) return postBookResponse;
        Response lastBookResponse = new BookOperations().getAllBooks();
        GlobVar.dummyBookId = lastBookResponse.jsonPath().getInt("books.book[-1].id");
        
        Response postAuthorResponse = new AuthorOperations().createAuthor(GlobVar.dummyAuthorBio, GlobVar.dummyAuthorCountry, GlobVar.dummyAuthorFirstName, GlobVar.dummyAuthorLastName);
        if(!( 201 == postAuthorResponse.getStatusCode() )) return postAuthorResponse;
        Response authorResponse = new AuthorOperations().getAllAuthors();
        GlobVar.dummyAuthorId = authorResponse.jsonPath().getInt("authors.author[-1].id");
        return postAuthorResponse;
        
    } 
    public static Response addDummyAuthorToDummyBook(){
        Response authorOfBookResponse = new BookOperations().addAuthorToBook(GlobVar.dummyBookId, GlobVar.dummyAuthorId, GlobVar.dummyAuthorBio, GlobVar.dummyAuthorCountry, GlobVar.dummyAuthorFirstName, GlobVar.dummyAuthorLastName);  
        return authorOfBookResponse; 
    }
   
    public static Response removeDummyBookAndDummyAuthor(){
        Response deleteBookResponse = new BookOperations().deleteBook(GlobVar.dummyBookId);
        if(!( 204 == deleteBookResponse.getStatusCode() )) return deleteBookResponse;
        
        Response deleteAuthorResponse = new AuthorOperations().deleteAuthor(GlobVar.dummyAuthorId);
        return deleteAuthorResponse;
    }
    
    public static Response makeDummyUser(){
        Response postUserResponse = new UserOperations().createUser(GlobVar.aDummyUserDisplayName, GlobVar.aDummyUserEmail, GlobVar.aDummyUserFirstName, GlobVar.aDummyUserLastName, GlobVar.aDummyUserPassword, GlobVar.aDummyUserPhone, GlobVar.aDummyUserRole);
        if(( 201 == postUserResponse.getStatusCode())){
            Response lastUserResponse = new UserOperations().getAllUsers();
            GlobVar.aDummyUserId = lastUserResponse.jsonPath().getInt("users.user[-1].id");
        }
        return postUserResponse;
    }
    
    public static Response removeDummyUser(){
        Response deleteUserResponse = new UserOperations().deleteUser(GlobVar.aDummyUserId);
        return deleteUserResponse;
        
        
    }
    
}
