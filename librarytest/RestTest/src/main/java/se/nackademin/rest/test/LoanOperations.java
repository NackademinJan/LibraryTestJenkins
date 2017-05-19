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
import se.nackademin.rest.test.model.AllLoans;
import se.nackademin.rest.test.model.Loan;
import se.nackademin.rest.test.model.SingleLoan;

/**
 *
 * @author testautomatisering
 */
public class LoanOperations {
    private String jsonString = "";
    
    
    
    //this method gets all loans in the system and returns a json style response of the result
    public Response getAllLoans(){
        String resourceName = "loans";
        Response getResponse = given().accept(ContentType.JSON).get(GlobVar.BASE_URL + resourceName);
        return getResponse;
    }
    
    //this method gets all loans in the system and returns an instance of my helplcass Allloans with the result
    public AllLoans fetchAllLoans(){
        AllLoans loans = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+"loans").jsonPath().getObject("loans", AllLoans.class);
        return loans;
    }
    
    
    //this method gets a specific loan by his loanId and reutrns a json style response of the result
    public Response getLoan(Integer loanId){
        String resourceName = "loans/"+loanId;
        Response response = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+resourceName);
        return response;
    }
    
    //this method gets a specific loan by his loanId and reutrns an instance of my helplcass loan with of the result. 
    public Loan fetchLoan(Integer loanId){
        Loan loan = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+"loans/"+loanId).jsonPath().getObject("loan", Loan.class);
        return loan;
    }
    
    //this method gets the last loan in the list and returns it as an instance of the loan.class
    public Loan fetchLastLoan(){
        Response getResponse = new LoanOperations().getAllLoans();
        Integer fetchLastLoanId = getResponse.jsonPath().getInt("loans.loan[-1].id");
        System.out.println(fetchLastLoanId);
        Loan fetchLoan = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+"loans/"+fetchLastLoanId).jsonPath().getObject("loan", Loan.class);
        return  fetchLoan;
    }
    //this method gets the last loan in the list and returns it as a json style response
    public Response getLastLoan(){
        Response getResponse = new LoanOperations().getAllLoans();
        Integer lastLoanId = getResponse.jsonPath().getInt("loans.loan[-1].id");
        Response lastLoanResponse = new LoanOperations().getLoan(lastLoanId);
        return  lastLoanResponse;
    }
    
    //this method attempts to create a new author with input for a display name, email, loan's first and last name, password, phone number and their loan-role and then returns a json style response of the result
    public Response createLoan(Integer bookId, String bookTitle, String dateBorrowed, String dateDue,  Integer userId, String userDisplayName){
        String resourceName = "loans";

        String postBodyTemplate = 
                        "{\n" 
                    +   "  \"loan\": {\n" 
                    +   "    \"book\": {\n" 
                    +   "      \"id\": %s,\n" 
                    +   "      \"title\": \"%s\"\n" 
                    +   "    },\n" 
                    +   "    \"dateBorrowed\": \"%s\",\n" 
                    +   "    \"dateDue\": \"%s\",\n" 
                    +   "    \"user\": {\n" 
                    +   "      \"id\": %s,\n" 
                    +   "      \"displayName\": \"%s\",\n" 
                    +   "    }\n" 
                    +   "  }\n" 
                    +   "}";
                        
        
        String postBody= String.format(postBodyTemplate, bookId, bookTitle, dateBorrowed, dateDue,  userId, userDisplayName);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(GlobVar.BASE_URL + resourceName);
        return postResponse;
    }
    
    //this method attempts to create a new loan with input for loanId, a display name, email, loan's first and last name, password, phone number and their loan-role and then returns a json style response of the result
    public Response createLoanWithId(Integer loanId, Integer bookId, String bookTitle, String dateBorrowed, String dateDue,  Integer userId, String userDisplayName){
        String resourceName = "loans";

        String postBodyTemplate = 
                        "{\n" 
                    +   "  \"loan\": {\n" 
                    +   "    \"id\": %s,\n"
                    +   "    \"book\": {\n" 
                    +   "      \"id\": %s,\n" 
                    +   "      \"title\": \"%s\"\n" 
                    +   "    },\n" 
                    +   "    \"dateBorrowed\": \"%s\",\n" 
                    +   "    \"dateDue\": \"%s\",\n" 
                    +   "    \"user\": {\n" 
                    +   "      \"id\": %s,\n" 
                    +   "      \"displayName\": \"%s\",\n" 
                    +   "    }\n" 
                    +   "  }\n" 
                    +   "}";
                        
        
        String postBody= String.format(postBodyTemplate, loanId, bookId, bookTitle, dateBorrowed, dateDue,  userId, userDisplayName);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(GlobVar.BASE_URL + resourceName);
        return postResponse;
    }
    
    
    //this method attempts to update an existing loan specified by its displayName, Then returns a json style response of the result
    public Response updateLoan(Integer loanId, Integer bookId, String bookTitle, String dateBorrowed, String dateDue,  Integer userId, String userDisplayName){
        String resourceName = "loans";

        String postBodyTemplate = 
                        "{\n" 
                    +   "  \"loan\": {\n" 
                    +   "    \"id\": %s,\n"
                    +   "    \"book\": {\n" 
                    +   "      \"id\": %s,\n" 
                    +   "      \"title\": \"%s\"\n" 
                    +   "    },\n" 
                    +   "    \"dateBorrowed\": \"%s\",\n" 
                    +   "    \"dateDue\": \"%s\",\n" 
                    +   "    \"user\": {\n" 
                    +   "      \"id\": %s,\n" 
                    +   "      \"displayName\": \"%s\",\n" 
                    +   "    }\n" 
                    +   "  }\n" 
                    +   "}";
        
        String putBody= String.format(postBodyTemplate, loanId, bookId, bookTitle, dateBorrowed, dateDue,  userId, userDisplayName);
        jsonString = putBody;
        Response putResponse = given().contentType(ContentType.JSON).body(putBody).put(GlobVar.BASE_URL + resourceName);
        return putResponse;
    }
    
    // this method should always return status code 400 and should not update a loan because it does not include a loans displayname in its request. it also returns a json style response of the result
    public Response invalidUpdateLoanWithoutloanDisplayName(String email, String firstName, String lastName,  String password, String phone, String role){
        String resourceName = "loans";

        String postBodyTemplate = 
                        "{\n" 
                    +   "  \"loan\": {\n"  
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
    
    
    
    
    // this method deletes the last loan in the system list of loans and then returns a json style response of the result
    public Response deleteLastLoan(){   
        Response getResponse = new LoanOperations().getAllLoans();
        int fetchedId = getResponse.jsonPath().getInt("loans.loan[-1].id");
        Response deleteResponse = new LoanOperations().deleteLoan(fetchedId);
        return deleteResponse;
    }
    
    //this method deletes a specific loan based on its loanId and then returns a json style response of the result
    public Response deleteLoan(Integer loanId){
        String deleteResourceName = "loans/"+loanId;
        
        Response deleteResponse = delete(GlobVar.BASE_URL + deleteResourceName);
        return deleteResponse;
    }
    
    //this method sets/updates a loan specified by the loanId with base values for all fields taken from the global variables in the GlobVar class
    public Response unPutALoan(Integer loanId){
        Response unPutLoanAResponse = new LoanOperations().updateLoan(GlobVar.aDummyLoanId, GlobVar.aDummyBookId, GlobVar.aDummyBookTitle, GlobVar.aDummyDateBorrowed, GlobVar.aDummyDateDue,  GlobVar.aDummyUserId, GlobVar.aDummyUserDisplayName);

        return unPutLoanAResponse;
    }
    //this method sets/updates a loan specified by the loanId with base values for all fields taken from the global variables in the GlobVar class
    public Response unPutBLoan(Integer loanId){
        Response unPutLoanBResponse = new LoanOperations().updateLoan(GlobVar.bDummyLoanId, GlobVar.bSecondDummyBookId, GlobVar.bSecondDummyBookTitle, GlobVar.bDummyDateBorrowed, GlobVar.bDummyDateDue,  GlobVar.bDummyUserId, GlobVar.bDummyUserDisplayName);

        return unPutLoanBResponse;
    }
    
    
    
    //this method gets a specific loan by the loaning user's userId and returns an instance of my helplcass loan with of the result. 
    public Loan fetchLoanOfUser(Integer userId){
        SingleLoan fetchedLoan = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+"loans/ofuser/"+userId).jsonPath().getObject("loans", SingleLoan.class);
        Loan loan = fetchedLoan.getLoan();
        return loan;
    }
    
    //this method gets a specific loan by the loaning user's userId and returns a json style response of the result
    public Response getLoanOfUser(Integer userId){
        String resourceName = "loans/ofuser/"+userId;
        Response response = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+resourceName);
        return response;
    }
    
    
    
    //this method gets a specific loan by the loaning user's userId and returns an instance of my helplcass loan with of the result. 
    public Loan fetchLoanOfBook(Integer bookId){
        SingleLoan fetchedLoan = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+"loans/ofbook/"+bookId).jsonPath().getObject("loans", SingleLoan.class);
        Loan loan = fetchedLoan.getLoan();
        return loan;
    }
    
    //this method gets a specific loan by the loaning user's userId and returns a json style response of the result
    public Response getLoanOfBook(Integer bookId){
        String resourceName = "loans/ofbook/"+bookId;
        Response response = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+resourceName);
        return response;
    }
    
    
    
    //this method gets a specific loan by the loaning user's userId and returns an instance of my helplcass loan with of the result. 
    public Loan fetchLoanOfUserOfBook(Integer userId, Integer bookId){
        Loan loan = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+"loans/ofuser/"+userId+"/ofbook/"+bookId).jsonPath().getObject("loan", Loan.class);
        
        return loan;
    }
    
    //this method gets a specific loan by the loaning user's userId and returns a json style response of the result
    public Response getLoanOfUserOfBook(Integer userId,Integer bookId){
        String resourceName = "loans/ofuser/"+userId+"/ofbook/"+bookId;
        Response response = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+resourceName);
        return response;
    }
    
    //this method returns the jsonString variable as a String
    public String getLatestJsonString(){
        return jsonString;
    }
    
    
    
}
