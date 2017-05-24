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
import se.nackademin.rest.test.model.User;

/**
 *
 * @author testautomatisering
 */
public class BeforeAndAfterOperations {

    
    public static Response makeDummyBookAndDummyAuthor(){
        Response postBookResponse = new BookOperations().createBookWithInput(GlobVar.aDummyBookDescription, GlobVar.aDummyBookIsbn, GlobVar.aDummyBookNbrPages, GlobVar.aDummyBookPublicationDate, GlobVar.aDummyBookTitle, GlobVar.aDummyBookTotalNbrCopies);
        if(!( 201 == postBookResponse.getStatusCode() )) return postBookResponse;
        Response lastBookResponse = new BookOperations().getAllBooks();
        GlobVar.aDummyBookId = lastBookResponse.jsonPath().getInt("books.book[-1].id");
        
        Response postAuthorResponse = new AuthorOperations().createAuthor(GlobVar.dummyAuthorBio, GlobVar.dummyAuthorCountry, GlobVar.dummyAuthorFirstName, GlobVar.dummyAuthorLastName);
        if(!( 201 == postAuthorResponse.getStatusCode() )) return postAuthorResponse;
        Response authorResponse = new AuthorOperations().getAllAuthors();
        GlobVar.dummyAuthorId = authorResponse.jsonPath().getInt("authors.author[-1].id");
        return postAuthorResponse;
        
    } 
    public static Response addDummyAuthorToDummyBook(){
        Response authorOfBookResponse = new BookOperations().addAuthorToBook(GlobVar.aDummyBookId, GlobVar.dummyAuthorId, GlobVar.dummyAuthorBio, GlobVar.dummyAuthorCountry, GlobVar.dummyAuthorFirstName, GlobVar.dummyAuthorLastName);  
        return authorOfBookResponse; 
    }
   
    public static Response removeDummyBookAndDummyAuthor(){
        Response deleteBookResponse = new BookOperations().deleteBook(GlobVar.aDummyBookId);
        if(!( 204 == deleteBookResponse.getStatusCode() )) return deleteBookResponse;
        
        Response deleteAuthorResponse = new AuthorOperations().deleteAuthor(GlobVar.dummyAuthorId);
        return deleteAuthorResponse;
    }
    
    public static Response makeADummyBook(){
        Response postBookResponse = new BookOperations().createBookWithInput(GlobVar.aDummyBookDescription, GlobVar.aDummyBookIsbn, GlobVar.aDummyBookNbrPages, GlobVar.aDummyBookPublicationDate, GlobVar.aDummyBookTitle, GlobVar.aDummyBookTotalNbrCopies);
        if(!( 201 == postBookResponse.getStatusCode() )) return postBookResponse;
        Response lastBookResponse = new BookOperations().getAllBooks();
        GlobVar.aDummyBookId = lastBookResponse.jsonPath().getInt("books.book[-1].id");
        return postBookResponse;
    }
        
    public static Response removeADummyBook(){
        Response deleteBookResponse = new BookOperations().deleteBook(GlobVar.aDummyBookId);
        return deleteBookResponse;   
    }
    
    public static Response makeBDummyBook(){
        Response postBookResponse = new BookOperations().createBookWithInput(GlobVar.bSecondDummyBookDescription, GlobVar.bSecondDummyBookIsbn, GlobVar.bSecondDummyBookNbrPages, GlobVar.bSecondDummyBookPublicationDate, GlobVar.bSecondDummyBookTitle, GlobVar.bSecondDummyBookTotalNbrCopies);
        if(!( 201 == postBookResponse.getStatusCode() )) return postBookResponse;
        Response lastBookResponse = new BookOperations().getAllBooks();
        GlobVar.bSecondDummyBookId = lastBookResponse.jsonPath().getInt("books.book[-1].id");
        return postBookResponse;
    }
    
    public static Response removeBDummyBook(){
        Response deleteBookResponse = new BookOperations().deleteBook(GlobVar.bSecondDummyBookId);
        return deleteBookResponse;   
    }
    
    public static Response makeADummyUser(){
        Response postUserResponse = new UserOperations().createUser(GlobVar.aDummyUserDisplayName, GlobVar.aDummyUserEmail, GlobVar.aDummyUserFirstName, GlobVar.aDummyUserLastName, GlobVar.aDummyUserPassword, GlobVar.aDummyUserPhone, GlobVar.aDummyUserRole);
        if(( 201 == postUserResponse.getStatusCode())){
            Response lastUserResponse = new UserOperations().getAllUsers();
            GlobVar.aDummyUserId = lastUserResponse.jsonPath().getInt("users.user[-1].id");
        }
        return postUserResponse;
    }
    
    public static Response removeADummyUser(){
        Response deleteUserResponse = new UserOperations().deleteUser(GlobVar.aDummyUserId);
        return deleteUserResponse;   
    }
    
    public static Response makeBDummyUser(){
        Response postUserResponse = new UserOperations().createUser(GlobVar.bDummyUserDisplayName, GlobVar.bDummyUserEmail, GlobVar.bDummyUserFirstName, GlobVar.bDummyUserLastName, GlobVar.bDummyUserPassword, GlobVar.bDummyUserPhone, GlobVar.bDummyUserRole);
        if(( 201 == postUserResponse.getStatusCode())){
            Response lastUserResponse = new UserOperations().getAllUsers();
            GlobVar.bDummyUserId = lastUserResponse.jsonPath().getInt("users.user[-1].id");
        }
        return postUserResponse;
    }
    
    public static Response removeBDummyUser(){
        Response deleteUserResponse = new UserOperations().deleteUser(GlobVar.bDummyUserId);
        return deleteUserResponse;
    }
    
    public static Response makeCDummyUser(){
        Response postUserResponse = new UserOperations().createUser(GlobVar.cDummyUserDisplayName, GlobVar.cDummyUserEmail, GlobVar.cDummyUserFirstName, GlobVar.cDummyUserLastName, GlobVar.cDummyUserPassword, GlobVar.cDummyUserPhone, GlobVar.cDummyUserRole);
        if(( 201 == postUserResponse.getStatusCode())){
            Response lastUserResponse = new UserOperations().getAllUsers();
            GlobVar.cDummyUserId = lastUserResponse.jsonPath().getInt("users.user[-1].id");
        }
        return postUserResponse;
    }
    
    public static Response removeCDummyUser(){
        Response deleteUserResponse = new UserOperations().deleteUser(GlobVar.cDummyUserId);
        return deleteUserResponse;
    }
    
    public static Response makeADummyLoan(){
        Response postLoanResponse = new LoanOperations().createLoan(GlobVar.aDummyBookId, GlobVar.aDummyBookTitle, GlobVar.aDummyDateBorrowed, GlobVar.aDummyDateDue, GlobVar.aDummyUserId, GlobVar.aDummyUserDisplayName);
        if(( 201 == postLoanResponse.getStatusCode())){
            Response lastLoanResponse = new LoanOperations().getAllLoans();
            GlobVar.aDummyLoanId = lastLoanResponse.jsonPath().getInt("loans.loan[-1].id");
            GlobVar.aDummyLoanBook = lastLoanResponse.jsonPath().getObject("loans.loan[-1].book", Book.class);
            GlobVar.aDummyLoanUser = lastLoanResponse.jsonPath().getObject("loans.loan[-1].user", User.class);
        }
        return postLoanResponse;
    }
    
    public static Response removeADummyLoan(){
        Response deleteLoanResponse = new LoanOperations().deleteLoan(GlobVar.aDummyLoanId);
        return deleteLoanResponse;
    }
    
    public static Response makeBDummyLoan(){
        Response postLoanResponse = new LoanOperations().createLoan(GlobVar.bSecondDummyBookId, GlobVar.bSecondDummyBookTitle, GlobVar.bDummyDateBorrowed, GlobVar.bDummyDateDue, GlobVar.bDummyUserId, GlobVar.bDummyUserDisplayName);
        if(( 201 == postLoanResponse.getStatusCode())){
            Response lastLoanResponse = new LoanOperations().getAllLoans();
            GlobVar.bDummyLoanId = lastLoanResponse.jsonPath().getInt("loans.loan[-1].id");
            GlobVar.bDummyLoanBook = lastLoanResponse.jsonPath().getObject("loans.loan[-1].book", Book.class);
            GlobVar.bDummyLoanUser = lastLoanResponse.jsonPath().getObject("loans.loan[-1].user", User.class);
        }
        return postLoanResponse;
    }
    
    public static Response removeBDummyLoan(){
        Response deleteLoanResponse = new LoanOperations().deleteLoan(GlobVar.bDummyLoanId);
        return deleteLoanResponse;
    }
    
    //important, establishing loans depends on there being no prior loans in the system, if there are, comment out line 158 and 174
    public static Response createTwoDummyBooksUsersAndLoans(){
        Response posAtBookResponse = new BookOperations().createBookWithInput(GlobVar.aDummyBookDescription, GlobVar.aDummyBookIsbn, GlobVar.aDummyBookNbrPages, GlobVar.aDummyBookPublicationDate, GlobVar.aDummyBookTitle, GlobVar.aDummyBookTotalNbrCopies);
        if(!( 201 == posAtBookResponse.getStatusCode() )) return posAtBookResponse;
        Response lastABookResponse = new BookOperations().getAllBooks();
        GlobVar.aDummyBookId = lastABookResponse.jsonPath().getInt("books.book[-1].id");
        
        Response postBBookResponse = new BookOperations().createBookWithInput(GlobVar.bSecondDummyBookDescription, GlobVar.bSecondDummyBookIsbn, GlobVar.bSecondDummyBookNbrPages, GlobVar.bSecondDummyBookPublicationDate, GlobVar.bSecondDummyBookTitle, GlobVar.bSecondDummyBookTotalNbrCopies);
        if(!( 201 == postBBookResponse.getStatusCode() )) return postBBookResponse;
        Response lastBBookResponse = new BookOperations().getAllBooks();
        GlobVar.bSecondDummyBookId = lastBBookResponse.jsonPath().getInt("books.book[-1].id");
        
        Response postAUserResponse = new UserOperations().createUser(GlobVar.aDummyUserDisplayName, GlobVar.aDummyUserEmail, GlobVar.aDummyUserFirstName, GlobVar.aDummyUserLastName, GlobVar.aDummyUserPassword, GlobVar.aDummyUserPhone, GlobVar.aDummyUserRole);
        if(!( 201 == postAUserResponse.getStatusCode() )) return postAUserResponse;
        Response lastAUserResponse = new UserOperations().getAllUsers();
        GlobVar.aDummyUserId = lastAUserResponse.jsonPath().getInt("users.user[-1].id");
        
        Response postBUserResponse = new UserOperations().createUser(GlobVar.bDummyUserDisplayName, GlobVar.bDummyUserEmail, GlobVar.bDummyUserFirstName, GlobVar.bDummyUserLastName, GlobVar.bDummyUserPassword, GlobVar.bDummyUserPhone, GlobVar.bDummyUserRole);
        if(!( 201 == postBUserResponse.getStatusCode() )) return postBUserResponse;
        Response lastBUserResponse = new UserOperations().getAllUsers();
        GlobVar.bDummyUserId = lastBUserResponse.jsonPath().getInt("users.user[-1].id");
        
        Response postCTempLoanResponse = new LoanOperations().createLoan(GlobVar.bSecondDummyBookId, GlobVar.bSecondDummyBookTitle, GlobVar.cDummyDateBorrowed, GlobVar.cDummyDateDue, GlobVar.aDummyUserId, GlobVar.aDummyUserDisplayName);
        if(!( 201 == postCTempLoanResponse.getStatusCode() )) return postCTempLoanResponse;
        Response lastCTempResponse = new LoanOperations().getAllLoans();
        GlobVar.cDummyLoanId = lastCTempResponse.jsonPath().getInt("loans.loan.id"); 

        Response postALoanResponse = new LoanOperations().createLoan(GlobVar.aDummyBookId, GlobVar.aDummyBookTitle, GlobVar.aDummyDateBorrowed, GlobVar.aDummyDateDue, GlobVar.aDummyUserId, GlobVar.aDummyUserDisplayName);
        if(!( 201 == postALoanResponse.getStatusCode() )) return postALoanResponse;
        Response lastALoanResponse = new LoanOperations().getAllLoans();
        GlobVar.aDummyLoanId = lastALoanResponse.jsonPath().getInt("loans.loan[-1].id"); 
        GlobVar.aDummyLoanBook = lastALoanResponse.jsonPath().getObject("loans.loan[-1].book", Book.class); 
        GlobVar.aDummyLoanUser = lastALoanResponse.jsonPath().getObject("loans.loan[-1].user", User.class); 
        
        Response postBLoanResponse = new LoanOperations().createLoan(GlobVar.bSecondDummyBookId, GlobVar.bSecondDummyBookTitle, GlobVar.bDummyDateBorrowed, GlobVar.bDummyDateDue, GlobVar.bDummyUserId, GlobVar.bDummyUserDisplayName);
        if(!( 201 == postBLoanResponse.getStatusCode() )) return postBLoanResponse;
        Response lastBLoanResponse = new LoanOperations().getAllLoans();
        GlobVar.bDummyLoanId = lastBLoanResponse.jsonPath().getInt("loans.loan[-1].id");
        GlobVar.bDummyLoanBook = lastBLoanResponse.jsonPath().getObject("loans.loan[-1].book", Book.class);
        GlobVar.bDummyLoanUser = lastBLoanResponse.jsonPath().getObject("loans.loan[-1].user", User.class);
        
        Response deleteCLoanResponse = new LoanOperations().deleteLoan(GlobVar.cDummyLoanId);
        
        return postBLoanResponse;
    }
    
    
    public static Response removeTwoDummyBooksUsersAndLoans(){
        Response deleteABookResponse = new BookOperations().deleteBook(GlobVar.aDummyBookId);
        if(!( 204 == deleteABookResponse.getStatusCode() )) return deleteABookResponse;
        
        Response deleteBBookResponse = new BookOperations().deleteBook(GlobVar.bSecondDummyBookId);
        if(!( 204 == deleteBBookResponse.getStatusCode() )) return deleteBBookResponse;
        
        Response deleteAUserResponse = new UserOperations().deleteUser(GlobVar.aDummyUserId);
        if(!( 204 == deleteAUserResponse.getStatusCode() )) return deleteAUserResponse;
        
        Response deleteBUserResponse = new UserOperations().deleteUser(GlobVar.bDummyUserId);
        return deleteAUserResponse;
        
        /* the parts below are only needed if removing the books and users of a loan does not automatically remove the loan like it does at the time of writing these tests
        if(!( 204 == deleteBUserResponse.getStatusCode() )) return deleteAUserResponse;
        
        
        Response deleteALoanResponse = new LoanOperations().deleteLoan(GlobVar.aDummyLoanId);
        if(!( 204 == deleteALoanResponse.getStatusCode() )) return deleteALoanResponse;

        Response deleteBLoanResponse = new LoanOperations().deleteLoan(GlobVar.bDummyLoanId);
        return deleteBLoanResponse;
        */
    }
    
    
    
}
