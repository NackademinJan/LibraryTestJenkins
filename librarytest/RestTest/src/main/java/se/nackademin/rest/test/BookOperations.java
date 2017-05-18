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
import java.util.Random;
import java.util.UUID;
import se.nackademin.rest.test.model.AllBooks;
import se.nackademin.rest.test.model.Book;
import se.nackademin.rest.test.model.SingleBook;

/**
 *
 * @author testautomatisering
 */
public class BookOperations {
    private String jsonString = "";
    
    
    
    //this method gets a list of all books as a json style response
    public Response getAllBooks(){
        String resourceName = "books";
        Response getResponse = given().accept(ContentType.JSON).get(GlobVar.BASE_URL + resourceName);
        return getResponse;
    }
    
    //this method gets a list of all books as an instance of my AllBooks.class
    public AllBooks fetchAllBooks(){
        AllBooks books = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+"books").jsonPath().getObject("books", AllBooks.class);
        return books;
    }
    
    
    //this method gets a json style response of a specific book
    public Response getBookById(Integer bookId){
        String resourceName = "books/"+bookId;
        Response response = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+resourceName);
        return response;
    }
    
    //this method is essentially the same as getBookById exept it returns an instance of book.class instead of a Response
    public Book fetchBookById(Integer bookId){
        Book fetchBook = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+"books/"+bookId).jsonPath().getObject("book", Book.class);
        return  fetchBook;
    }
    
    
    //this method gets one more books written by a specific author as a json style response
    public Response getBookByAuthor(Integer authorId){
        String resourceName = "books/byauthor/"+authorId;
        Response response = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+resourceName);
        return response;
    }
    
    //this method gets one more authors who wrote a specific book as a json style response
    public Response getAuthorByBook(Integer bookId){
        String resourceName = "books/"+bookId+"/authors";
        Response response = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+resourceName);
        return response;
    }
    
    
    
    //this method adds one specific author to one specifik book as an author of that book and returns a json style response of the results
    public Response addAuthorToBook(Integer bookId, Integer authorId, String bio, String country,  String firstName, String lastName){
        String resourceName = "books/"+bookId+"/authors";
        
        String postBodyTemplate = 
                    /*
                        "{\n" 
                    +   "    \"author\": {\n" 
                    +   "        \"id\": 1,\n" 
                    +   "        \"bio\": \"Margaret Fame.\",\n" 
                    +   "        \"country\": \"Canada\",\n" 
                    +   "        \"firstName\": \"Margaret\",\n" 
                    +   "        \"lastName\": \"Atwood\"\n" 
                    +   "    }\n" 
                    +   "}";
                    */
                        "{\n" 
                    +   "    \"author\": {\n" 
                    +   "        \"id\": %s,\n" 
                    +   "        \"bio\": \"%s\",\n" 
                    +   "        \"country\": \"%s\",\n" 
                    +   "        \"firstName\": \"%s\",\n" 
                    +   "        \"lastName\": \"%s\"\n" 
                    +   "    }\n" 
                    +   "}";
                
        String postBody= String.format(postBodyTemplate, authorId, bio, country, firstName, lastName);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(GlobVar.BASE_URL + resourceName);
        return postResponse;
    }
    
    // this method should always produce a response with statuscode 400, it should not successfully post a book because the post request does not include an authorId
    public Response invalidAddAuthorToBookWithoutAuthorId(String authorName, Integer bookId){
        String resourceName = "books/"+bookId+"/authors";
         
        String postBodyTemplate = 
                        "{\n" 
                    +   "\"author\":\n" 
                    +   "  {\n" 
                    +   "    \"name\":\"%s\",\n" 
                    +   "  }\n" 
                    +   "}";
        String postBody= String.format(postBodyTemplate, authorName);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(GlobVar.BASE_URL + resourceName);
        return postResponse;
    }
    
    
    //This method is equivalent to createBookWithInput but using Book.class and related methods, still returns a json style response
    public Response makeBookWithInput(String description, String isbn, Integer nbrPages, String title){
        Book book = new Book();
        book.setDescription(description);
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setNbrPages(nbrPages);
        SingleBook singleBook = new SingleBook(book);
        Response postBookResponse = given().contentType(ContentType.JSON).body(singleBook).post(GlobVar.BASE_URL+"books");
        return postBookResponse;
    }
    
    //this method creates a book using given values for description, isbn, pagecount and title and returns a jsonstyle response of the results
    public Response createBookWithInput(String description, String isbn, Integer nbrPages, String publicationDate, String title, Integer totalNbrCopies){
        String resourceName = "books";
        String postBodyTemplate = 
                        "{\n" 
                    +   "  \"book\": {\n"  
                    +   "    \"description\": \"%s\",\n" 
                    +   "    \"isbn\": \"%s\",\n" 
                    +   "    \"nbrPages\": %s,\n" 
                    +   "    \"publicationDate\": \"%s\",\n" 
                    +   "    \"title\": \"%s\",\n" 
                    +   "    \"totalNbrCopies\": %s\n" 
                    +   "  }\n" 
                    +   "}";
                    

        String postBody= String.format(postBodyTemplate, description, isbn, nbrPages, publicationDate, title, totalNbrCopies);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(GlobVar.BASE_URL + resourceName);
        return postResponse;
    }
    
    //this method creates a book using input values for a specified bookId, description, isbn, pagecount and title and returns a jsonstyle response of the results
    public Response createBookWithInputAndId(Integer bookId, String description, String isbn, Integer nbrPages, String publicationDate, String title, Integer totalNbrCopies){
        String resourceName = "books";
        String postBodyTemplate = 
                        
                        "{\n" 
                    +   "  \"book\": {\n"  
                    +   "    \"id\": %s,"
                    +   "    \"description\": \"%s\",\n" 
                    +   "    \"isbn\": \"%s\",\n" 
                    +   "    \"nbrPages\": %s,\n" 
                    +   "    \"publicationDate\": \"%s\",\n" 
                    +   "    \"title\": \"%s\",\n" 
                    +   "    \"totalNbrCopies\": %s\n" 
                    +   "  }\n" 
                    +   "}";    
                    

        String postBody= String.format(postBodyTemplate, bookId, description, isbn, nbrPages, publicationDate, title, totalNbrCopies);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(GlobVar.BASE_URL + resourceName);
        return postResponse;
    }
    
    //the author sent with parameters must have been created prior to running this method
    ////this method creates a book using input values for a description, isbn, pagecount, title and also an author name and authorId. Then returns a jsonstyle response of the results
    public Response createBookWithAuthor(Integer authorId, String authorBio, String authorCountry,  String AuthorFirstName, String AuthorLastName, String description, String isbn, Integer nbrPages, String publicationDate, String title, Integer totalNbrCopies){
        String resourceName = "books";
        
        
        String postBodyTemplate = 
                        "{\n" 
                    +   "  \"book\": {\n" 
                    +   "    \"author\": {\n" 
                    +   "      \"id\": %s,\n" 
                    +   "      \"bio\":\"%s\",\n"
                    +   "      \"country\":\"%s\",\n" 
                    +   "      \"firstName\": \"%s\",\n" 
                    +   "      \"lastName\": \"%s\"\n" 
                    +   "    },\n" 
                    +   "    \"description\": \"%s\",\n" 
                    +   "    \"isbn\": \"%s\",\n" 
                    +   "    \"nbrPages\": %s,\n" 
                    +   "    \"publicationDate\": \"%s\",\n" 
                    +   "    \"title\": \"%s\",\n" 
                    +   "    \"totalNbrCopies\": %s\n" 
                    +   "  }\n" 
                    +   "}";        
                    
                
        String postBody= String.format(postBodyTemplate, authorId, authorBio, authorCountry, AuthorFirstName, AuthorLastName, description, isbn, nbrPages, publicationDate, title, totalNbrCopies);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(GlobVar.BASE_URL + resourceName);
        return postResponse;        
    }
    
    // this method should always produce a response with statuscode 400, it should not successfully post a book because the post request does not include an authorId
    public Response invalidCreateBookWithAuthorNameButNoAuthorId(String authorCountry,  String AuthorFirstName, String AuthorLastName, String description, String isbn, Integer nbrPages, String publicationDate, String title, Integer totalNbrCopies){
        String resourceName = "books";
        
        
        String postBodyTemplate = 
                        "{\n" 
                    +   "  \"book\": {\n" 
                    +   "    \"author\": {\n"  
                    +   "      \"country\":\"%s\",\n" 
                    +   "      \"firstName\": \"%s\",\n" 
                    +   "      \"lastName\": \"%s\"\n" 
                    +   "    },\n" 
                    +   "    \"description\": \"%s\",\n" 
                    +   "    \"isbn\": \"%s\",\n" 
                    +   "    \"nbrPages\": %s,\n" 
                    +   "    \"publicationDate\": \"%s\",\n" 
                    +   "    \"title\": \"%s\",\n" 
                    +   "    \"totalNbrCopies\": %s\n" 
                    +   "  }\n" 
                    +   "}";        
                    
                
        String postBody= String.format(postBodyTemplate, authorCountry, AuthorFirstName, AuthorLastName, description, isbn, nbrPages, publicationDate, title, totalNbrCopies);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(GlobVar.BASE_URL + resourceName);
        return postResponse;        
    }
    
    //this method attempts to create a book with no specific id or author using randomized variables for  title, description, isbn and pagecount and returns a json style response of the results
    public Response createRandomBook(){
        String resourceName = "books";
        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String isbn = UUID.randomUUID().toString();
        Integer nbrPages = new Random().nextInt(500);
        
        String postBodyTemplate = 
                        "{\n" 
                    +   "\"book\":\n" 
                    +   "  {\n" 
                    +   "    \"description\":\"%s\",\n" 
                    +   "    \"isbn\":\"%s\",\n" 
                    +   "    \"nbOfPage\":\"%s\",\n" 
                    +   "    \"title\":\"%s\"\n" 
                    +   "  }\n" 
                    +   "}";
        String postBody= String.format(postBodyTemplate, description, isbn, nbrPages, title);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(GlobVar.BASE_URL + resourceName);
        return postResponse;
    }
    
    //this method attempts to create a book with no specific id using randomized variables for  title, description, isbn and pagecount but uses provided inputs for author name and author id because you cannot add a book with an author if the author does not exist in the system already. it then returns a json style response of the results
    public Response createRandomBookWithAuthor(String authorName, Integer authorId){
        String resourceName = "books";
        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String isbn = UUID.randomUUID().toString();
        String name = authorName;
        
        String postBodyTemplate = 
                        "{\n" 
                    +   "\"book\":\n" 
                    +   "  {\n" 
                    +   "    \"description\":\"%s\",\n" 
                    +   "    \"isbn\":\"%s\",\n" 
                    +   "    \"nbrPages\":\"%s\",\n"
                    +   "    \"title\":\"%s\",\n" 
                    +   "    \"author\":\n" 
                    +   "    {\n" 
                    +   "      \"name\":\"%s\",\n" 
                    +   "      \"id\":%s\n" 
                    +   "    }\n" 
                    +   "  }\n" 
                    +   "}";
                
        String postBody= String.format(postBodyTemplate, description, isbn, new Random().nextInt(500), title, authorName, authorId);
        jsonString = postBody;
        Response postResponse = given().contentType(ContentType.JSON).body(postBody).post(GlobVar.BASE_URL + resourceName);
        return postResponse;        
    }
    

    
    //this method attempts to do a put-request on a book specified by the bookId using input values for description, isbn, pagecount, title, author name and author id and returns a json style response of the results
    public Response updateBookWithAuthor(Integer authorId, String Bio, String country, String firstName, String lastName, Integer bookId, String description, String isbn, Integer nbrPages, String publicationDate, String title, Integer totalNbrCopies){
        String resourceName = "books";
        
        
        String putBodyTemplate = 
                        "{\n" 
                    +   "  \"book\": {\n" 
                    +   "    \"id\": %s,\n" 
                    +   "    \"author\": {\n" 
                    +   "      \"id\": %s,\n" 
                    +   "      \"bio\":\"%s\",\n"
                    +   "      \"country\":\"%s\",\n" 
                    +   "      \"firstName\": \"%s\",\n" 
                    +   "      \"lastName\": \"%s\"\n" 
                    +   "    },\n" 
                    
                    +   "    \"description\": \"%s\",\n" 
                    +   "    \"isbn\": \"%s\",\n" 
                    +   "    \"nbrPages\": %s,\n" 
                    +   "    \"publicationDate\": \"%s\",\n" 
                    +   "    \"title\": \"%s\",\n" 
                    +   "    \"totalNbrCopies\": %s\n" 
                    +   "  }\n" 
                    +   "}";
                
        String putBody= String.format(putBodyTemplate, bookId, authorId, Bio, country, firstName, lastName, description, isbn, nbrPages, publicationDate, title, totalNbrCopies);
        jsonString = putBody;
        Response putResponse = given().contentType(ContentType.JSON).body(putBody).put(GlobVar.BASE_URL + resourceName);
        return putResponse;        
    }
    
     // this method should always produce a response with statuscode 400, it should not successfully update a book because the put-request does not include an authorId
    public Response invalidUpdateBookWithAuthorButNoAuthorId(String Bio, String country, String firstName, String lastName, Integer bookId, String description, String isbn, Integer nbrPages, String publicationDate, String title, Integer totalNbrCopies){
        String resourceName = "books";
        
        
        String putBodyTemplate = 
                        "{\n" 
                    +   "  \"book\": {\n" 
                    +   "    \"id\": %s,\n" 
                    +   "    \"author\": {\n"  
                    +   "      \"bio\":\"%s\",\n"
                    +   "      \"country\":\"%s\",\n" 
                    +   "      \"firstName\": \"%s\",\n" 
                    +   "      \"lastName\": \"%s\"\n" 
                    +   "    },\n" 
                    
                    +   "    \"description\": \"%s\",\n" 
                    +   "    \"isbn\": \"%s\",\n" 
                    +   "    \"nbrPages\": %s,\n" 
                    +   "    \"publicationDate\": \"%s\",\n" 
                    +   "    \"title\": \"%s\",\n" 
                    +   "    \"totalNbrCopies\": %s\n" 
                    +   "  }\n" 
                    +   "}";
                
        String putBody= String.format(putBodyTemplate, bookId, Bio, country, firstName, lastName, description, isbn, nbrPages, publicationDate, title, totalNbrCopies);
        jsonString = putBody;
        Response putResponse = given().contentType(ContentType.JSON).body(putBody).put(GlobVar.BASE_URL + resourceName);
        return putResponse;        
    }
    
    
    //this method attempts to do a put-request on a book with inputs for authorName and authorId
    public Response updateABooksAuthors(Integer bookId, Integer authorId, String bio, String country, String authorFirstName, String authorLastName){
        String resourceName = "books/" +bookId+ "/authors";
        
        
        String putBodyTemplate = 
                        "{\n" 
                    +   "    \"authors\": {\n"  
                    +   "        \"author\": {\n" 
                    +   "            \"id\": %s,\n" 
                    +   "            \"bio\": \"%s\",\n" 
                    +   "            \"country\": \"%s\",\n" 
                    +   "            \"firstName\": \"%s\",\n" 
                    +   "            \"lastName\": \"%s\"\n" 
                    +   "        }\n" 
                    +   "    }\n" 
                    +   "}";
                
        String putBody= String.format(putBodyTemplate, authorId, bio, country, authorFirstName, authorLastName);
        jsonString = putBody;
        Response putResponse = given().contentType(ContentType.JSON).body(putBody).put(GlobVar.BASE_URL + resourceName);
        return putResponse;        
    }
    
    // this method should always produce a response with statuscode 400, it should not successfully update a book because the put-request does not include an authorId
    public Response invalidUpdateABooksAuthorsWithoutAuthorId(Integer bookId, String bio, String country, String authorFirstName, String authorLastName){
        String resourceName = "books/" +bookId+ "/authors";
        
        
        String putBodyTemplate = 
                        "{\n" 
                    +   "    \"authors\": {\n"  
                    +   "        \"author\": {\n"
                    +   "            \"bio\": \"%s\",\n" 
                    +   "            \"country\": \"%s\",\n" 
                    +   "            \"firstName\": \"%s\",\n" 
                    +   "            \"lastName\": \"%s\"\n" 
                    +   "        }\n" 
                    +   "    }\n" 
                    +   "}";
                
        String putBody= String.format(putBodyTemplate, bio, country, authorFirstName, authorLastName);
        jsonString = putBody;
        Response putResponse = given().contentType(ContentType.JSON).body(putBody).put(GlobVar.BASE_URL + resourceName);
        return putResponse;        
    }
    
    
    
    //this method gets the last book in the list and returns it as an instance of the book.class
    public Book fetchLastBook(){
        Response getResponse = new BookOperations().getAllBooks();
        Integer fetchlastBookId = getResponse.jsonPath().getInt("books.book[-1].id");
        Book fetchBook = given().accept(ContentType.JSON).get(GlobVar.BASE_URL+"books/"+fetchlastBookId).jsonPath().getObject("book", Book.class);
        return  fetchBook;
    }
    //this method gets the last book in the list and returns it as a json style response
    public Response getLastBook(){
        Response getResponse = new BookOperations().getAllBooks();
        Integer lastBookId = getResponse.jsonPath().getInt("books.book[-1].id");
        Response lastBookResponse = new BookOperations().getBookById(lastBookId);
        return  lastBookResponse;
    }
    
    
    
    //this method updates the book with the bookId  in param with dummyvariables, restoring the dummybook to default for the testexecutions and returns a json style response of the results
    public Response unPutBook(Integer bookId){
        Response unPutResponse = new BookOperations().updateBookWithAuthor(GlobVar.dummyAuthorId, GlobVar.dummyAuthorBio, GlobVar.dummyAuthorCountry, GlobVar.dummyAuthorFirstName, GlobVar.dummyAuthorLastName, bookId, GlobVar.dummyBookDescription, GlobVar.dummyBookIsbn, GlobVar.dummyBookNbrPages, GlobVar.dummyBookPublicationDate, GlobVar.dummyBookTitle, GlobVar.dummyBookTotalNbrCopies);

        return unPutResponse;
    }
    
    
    
    //this method deletes the last book in the list and returns a json style response of the results
    public Response deleteLastBook(){   
        Response getResponse = new BookOperations().getAllBooks();
        Integer deleteLastBookId = getResponse.jsonPath().getInt("books.book[-1].id");
        Response deleteLastBookResponse = new BookOperations().deleteBook(deleteLastBookId);
        return deleteLastBookResponse;
    }
    
    
    //this method deletes a book that has the specified bookId and returns a json style response of the results
    public Response deleteBook(Integer bookId){
        String deleteResourceName = "books/"+bookId;
        
        Response deleteResponse = delete(GlobVar.BASE_URL + deleteResourceName);
        return deleteResponse;
    }
    
    
    
    //this method returns the latest itteration of this bookoperation's jsonString variable as a String
    public String getLatestJsonString(){
        return jsonString;
    }
    
    
    
}
