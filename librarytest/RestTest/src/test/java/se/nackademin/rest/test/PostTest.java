/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.path.json.JsonPath.*;
import se.nackademin.rest.test.model.Book;
import se.nackademin.rest.test.model.SingleBook;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.*;
import org.slf4j.*;
/**
 *
 * @author testautomatisering
 */
public class PostTest {

    private static Logger _logger;
    
    
    
    @Rule
    public TestName testName = new TestName();
    
    @BeforeClass //this method creates a dummy book, dummy author and adds the author to the book to be used during test executions
    public static void MaketheDummyBookAndDummyAuthor(){
        _logger = LoggerFactory.getLogger(LoansTest.class);
        
        Response makeDummysResponse = BeforeAndAfterOperations.makeDummyBookAndDummyAuthor();
        assertEquals("The status code should be: 201",  201, makeDummysResponse.statusCode());
        assertEquals("response body should be blank",  "", makeDummysResponse.body().asString());
        
        Response addDummyAuthorToDummyBook = BeforeAndAfterOperations.addDummyAuthorToDummyBook();
        assertEquals("The status code should be: 200",  200, addDummyAuthorToDummyBook.statusCode());
        assertEquals("response body should be blank",  "", addDummyAuthorToDummyBook.body().asString());
    }
    
    @Before
    public void before(){
        String  name = testName.getMethodName();
        _logger.info("Startar testfallet " +name+".");
    }
    
    @After
    public void after(){
        String  name = testName.getMethodName();
        _logger.info("Avslutar testfallet " +name+".");
    }
    
    @AfterClass //this method removes the dummies created by the previous method
    public static void RemovetheDummyBookAndDummyAuthor(){
        Response removeResponse = BeforeAndAfterOperations.removeDummyBookAndDummyAuthor();
        assertEquals("The status code should be: 204",  204, removeResponse.statusCode());  
        assertEquals("response body should be blank",  "", removeResponse.body().asString());
    }

    
    
    @Test //This test tries to post a new book to the system and verifies that we get the right responsecode (201), a blank responsebody and then verifies that the new book with included variables are in the system
    public void testPostBook(){
        Book book = new Book();
        book.setDescription(GlobVar.bSecondDummyBookDescription);
        book.setTitle(GlobVar.bSecondDummyBookTitle);
        book.setIsbn(GlobVar.bSecondDummyBookIsbn);
        book.setNbrPages(GlobVar.bSecondDummyBookNbrPages);
        SingleBook singleBook = new SingleBook(book);
        
        Response response = given().contentType(ContentType.JSON).body(singleBook).post(GlobVar.BASE_URL+"books");
        assertEquals("The status code should be: 201",  201, response.statusCode());
        assertEquals("response body should be blank", "", response.body().asString());
        
        Book verifyBook = new BookOperations().fetchLastBook();
        assertEquals("The books description should be: " + GlobVar.bSecondDummyBookDescription,  book.getDescription(), verifyBook.getDescription());       
        assertEquals("The books title should be: " + GlobVar.bSecondDummyBookTitle,  book.getTitle(), verifyBook.getTitle());       
        assertEquals("The books isbn should be: " + GlobVar.bSecondDummyBookIsbn,  book.getIsbn(), verifyBook.getIsbn());       
        assertEquals("The books page count should be: " + GlobVar.bSecondDummyBookNbrPages,  book.getNbrPages(), verifyBook.getNbrPages());       
        
        if(response.statusCode() == 201){
            Response deleteResponse = new BookOperations().deleteLastBook();
            assertEquals("The status code should be: 204",  204, deleteResponse.statusCode());
            assertEquals("response body should be blank", "", deleteResponse.body().asString());
        }
        
    }
    
    @Test //this test does not use the book class but is otherwise equivalent to testPostBookWithSpecificNewId and was written mainly to see if the api woulld allow calls of the text format that "BookOperations().createBookWithInputAndId" uses to be posted.
    public void testPostBookWithSpecificNewIdWithoutBookClass(){
        Response getResponse = new BookOperations().getAllBooks();
        Integer newId = GlobVar.aDummyBookId + 1;
        Response response = new BookOperations().createBookWithInputAndId(newId, GlobVar.bSecondDummyBookDescription, GlobVar.bSecondDummyBookIsbn, GlobVar.bSecondDummyBookNbrPages, GlobVar.bSecondDummyBookPublicationDate, GlobVar.bSecondDummyBookTitle, GlobVar.bSecondDummyBookTotalNbrCopies);    
        
        assertEquals("The status code should be: 201",  201, response.statusCode());
        assertEquals("response body should be blank", "", response.body().asString());
        
        Book verifyBook = new BookOperations().fetchLastBook();
        assertEquals("The books description should be: " + GlobVar.bSecondDummyBookDescription,  GlobVar.bSecondDummyBookDescription, verifyBook.getDescription());       
        assertEquals("The books title should be: " + GlobVar.bSecondDummyBookTitle,  GlobVar.bSecondDummyBookTitle, verifyBook.getTitle());       
        assertEquals("The books isbn should be: " + GlobVar.bSecondDummyBookIsbn,  GlobVar.bSecondDummyBookIsbn, verifyBook.getIsbn());       
        assertEquals("The books page count should be: " + GlobVar.bSecondDummyBookNbrPages,  GlobVar.bSecondDummyBookNbrPages, verifyBook.getNbrPages());       
        assertEquals("The books id should be: " + newId,  newId, verifyBook.getId()); 
        
        if(response.statusCode() == 201){
            Response deleteResponse = new BookOperations().deleteLastBook();
            assertEquals("The status code should be: 204",  204, deleteResponse.statusCode());
            assertEquals("response body should be blank", "", deleteResponse.body().asString());
        }
    }
    
    @Test //this test attempts to post a book with a specific bookid that does not previously exist in the system, verifies that we get the right statuscode (201) and blank response body. It then verifies that the new book is in the system
    public void testPostBookWithSpecificNewId(){
        Response getResponse = new BookOperations().getAllBooks();
        int newId = getResponse.jsonPath().getInt("books.book[-1].id") + 1;
        Book book = new Book();
        book.setDescription(GlobVar.bSecondDummyBookDescription);
        book.setTitle(GlobVar.bSecondDummyBookTitle);
        book.setIsbn(GlobVar.bSecondDummyBookIsbn);
        book.setNbrPages(GlobVar.bSecondDummyBookNbrPages);
        book.setId(newId);
        SingleBook singleBook = new SingleBook(book);
        
        Response response = given().contentType(ContentType.JSON).body(singleBook).post(GlobVar.BASE_URL+"books");
        assertEquals("The status code should be: 201",  201, response.statusCode());
        assertEquals("response body should be blank", "", response.body().asString());
        
        Book verifyBook = new BookOperations().fetchLastBook();
        assertEquals("The books description should be: " + GlobVar.bSecondDummyBookDescription,  book.getDescription(), verifyBook.getDescription());       
        assertEquals("The books title should be: " + GlobVar.bSecondDummyBookTitle,  book.getTitle(), verifyBook.getTitle());       
        assertEquals("The books isbn should be: " + GlobVar.bSecondDummyBookIsbn,  book.getIsbn(), verifyBook.getIsbn());       
        assertEquals("The books page count should be: " + GlobVar.bSecondDummyBookNbrPages,  book.getNbrPages(), verifyBook.getNbrPages());       
        assertEquals("The books page count should be: " + newId,  book.getId(), verifyBook.getId()); 
        
        if(response.statusCode() == 201){
            Response deleteResponse = new BookOperations().deleteLastBook();
            assertEquals("The status code should be: 204",  204, deleteResponse.statusCode());
            assertEquals("response body should be blank", "", deleteResponse.body().asString());
        }
    }
    
    @Test //this test verifies that trying to post a book with a bookId that already exists in the system returns the right statuscode (400) and appropriate response body message
    public void testInvalidPostBookWithExistingID(){
        Response getResponse = new BookOperations().getAllBooks();
        int lastId = getResponse.jsonPath().getInt("books.book[-1].id");
        Book book = new Book();
        book.setDescription(GlobVar.bSecondDummyBookDescription);
        book.setTitle(GlobVar.bSecondDummyBookTitle);
        book.setIsbn(GlobVar.bSecondDummyBookIsbn);
        book.setNbrPages(GlobVar.bSecondDummyBookNbrPages);
        book.setId(lastId);
        SingleBook singleBook = new SingleBook(book);
        
        Response response = given().contentType(ContentType.JSON).body(singleBook).post(GlobVar.BASE_URL+"books");
        assertEquals("The status code should be: 400",  400, response.statusCode());
        assertEquals("response body should be A book with this ID already exists.",  "A book with this ID already exists.", response.body().asString());
    }
    
    
    @Test //this test attempts to post a book with an author that exists in the system then verifies that we get the right response statuscode (201) and a blank response body. then verifies that the new book (with author data) is in the system
    public void testPostBookWithAuthor(){
        BookOperations bookOperations = new BookOperations();
        AuthorOperations authorOperations = new AuthorOperations();
        
        Response authorResponse = authorOperations.getAuthor(GlobVar.dummyAuthorId); 
        String authorBiography = authorResponse.body().jsonPath().getString("author.bio");
        String authorCountry = authorResponse.body().jsonPath().getString("author.country");
        String authorFirstName = authorResponse.body().jsonPath().getString("author.firstName");
        String authorLastName = authorResponse.body().jsonPath().getString("author.lastName");
        Integer authorId = authorResponse.body().jsonPath().getInt("author.id");
        
        Response postResponse = bookOperations.createBookWithAuthor(authorId, authorBiography, authorCountry, authorFirstName, authorLastName, GlobVar.bSecondDummyBookDescription, GlobVar.bSecondDummyBookIsbn, GlobVar.bSecondDummyBookNbrPages, GlobVar.bSecondDummyBookPublicationDate, GlobVar.bSecondDummyBookTitle, GlobVar.bSecondDummyBookTotalNbrCopies);
        assertEquals("status code should be 201",  201, postResponse.statusCode());
        assertEquals("response body should be blank", "", postResponse.body().asString());
        
        //the part below fetches all the data of the operation we just performed
        Integer expectedAuthorId = from(bookOperations.getLatestJsonString()).getInt("book.author.id");
        String expectedAuthorBio = from(bookOperations.getLatestJsonString()).getString("book.author.bio");
        String expectedAuthorCountry = from(bookOperations.getLatestJsonString()).getString("book.author.country");
        String expectedAuthorFirstName = from(bookOperations.getLatestJsonString()).getString("book.author.firstName");
        String expectedAuthorLastName = from(bookOperations.getLatestJsonString()).getString("book.author.lastName");
        
        String expectedDescription = from(bookOperations.getLatestJsonString()).getString("book.description");
        String expectedIsbn = from(bookOperations.getLatestJsonString()).getString("book.isbn");
        Integer expectedNbrPages = from(bookOperations.getLatestJsonString()).getInt("book.nbrPages");
        String expectedPublicationDate = from(bookOperations.getLatestJsonString()).getString("book.publicationDate");
        String expectedTitle = from(bookOperations.getLatestJsonString()).getString("book.title");
        Integer expectedTotalNbrCopies = from(bookOperations.getLatestJsonString()).getInt("book.totalNbrCopies");
        
        //the part below fetches all the data of the last book in the list (which should be the same as the data from the last operation we performed)
        Response verifyResponse = new BookOperations().getAllBooks();
        
        Integer verifyAuthorId = verifyResponse.jsonPath().getInt("books.book[-1].author.id");
        String verifyAuthorBio = verifyResponse.jsonPath().getString("books.book[-1].author.bio");
        String verifyAuthorCountry = verifyResponse.jsonPath().getString("books.book[-1].author.country");
        String verifyAuthorFirstName = verifyResponse.jsonPath().getString("books.book[-1].author.firstName");
        String verifyAuthorLastName = verifyResponse.jsonPath().getString("books.book[-1].author.lastName");
        
        String verifyDescription = verifyResponse.jsonPath().getString("books.book[-1].description");
        String verifyIsbn = verifyResponse.jsonPath().getString("books.book[-1].isbn");
        Integer verifyNbrPages = verifyResponse.jsonPath().getInt("books.book[-1].nbrPages");
        String verifyPublicationDate = verifyResponse.jsonPath().getString("books.book[-1].publicationDate");
        String verifyTitle = verifyResponse.jsonPath().getString("books.book[-1].title");
        Integer verifyTotalNbrCopies = verifyResponse.jsonPath().getInt("books.book[-1].totalNbrCopies");
        
        //this part finally verifies that the last operation we performed and the last book in the list have the same data, in other words that our operation succeeded
        assertEquals(expectedAuthorId, verifyAuthorId); 
        assertEquals(expectedAuthorBio, verifyAuthorBio);
        assertEquals(expectedAuthorCountry, verifyAuthorCountry);
        assertEquals(expectedAuthorFirstName, verifyAuthorFirstName);
        assertEquals(expectedAuthorLastName, verifyAuthorLastName);
        
        assertEquals(expectedDescription, verifyDescription);
        assertEquals(expectedIsbn, verifyIsbn);
        assertEquals(expectedNbrPages, verifyNbrPages); 
        assertEquals(expectedPublicationDate, verifyPublicationDate);
        assertEquals(expectedTitle, verifyTitle);
        assertEquals(expectedTotalNbrCopies, verifyTotalNbrCopies);
        
        // and when all is said and done, if we created a book, we now delete that book for cleanup purposes
        if(postResponse.statusCode() == 201){
            Response deleteResponse = new BookOperations().deleteLastBook();
            assertEquals("The status code should be: 204",  204, deleteResponse.statusCode());
            assertEquals("response body should be blank", "", deleteResponse.body().asString());
        }
    }
    
    @Test //this test verifies that trying to post a book with an author but no authorId returns the right statuscode (400) and appropriate response body message
    public void testInvalidPostBookWithAuthorNameButNoAuthorId(){
        Response authorResponse = new AuthorOperations().getAuthor(GlobVar.dummyAuthorId); 
        String authorCountry = authorResponse.body().jsonPath().getString("author.country");
        String authorFirstName = authorResponse.body().jsonPath().getString("author.firstName");
        String authorLastName = authorResponse.body().jsonPath().getString("author.lastName");
        
        Response postResponse = new BookOperations().invalidCreateBookWithAuthorNameButNoAuthorId(authorCountry, authorFirstName, authorLastName, GlobVar.bSecondDummyBookDescription, GlobVar.bSecondDummyBookIsbn, GlobVar.bSecondDummyBookNbrPages, GlobVar.bSecondDummyBookPublicationDate, GlobVar.bSecondDummyBookTitle, GlobVar.bSecondDummyBookTotalNbrCopies);
        assertEquals("The status code should be: 400",  400, postResponse.statusCode());
        assertEquals("response body should be Book contained an author with no id field set.",  "Book contained an author with no id field set.", postResponse.body().asString());
    }
    
    @Test //this test verifies that trying to post a new book with an authorId that does not already exists in the system returns the right statuscode (400) and appropriate response body message
    public void testInvalidPostBookWithAuthorNameButWrongAuthorId(){
        Response authorResponse = new AuthorOperations().getAuthor(GlobVar.dummyAuthorId); 
        
        String authorBiography = authorResponse.body().jsonPath().getString("author.bio");
        String authorCountry = authorResponse.body().jsonPath().getString("author.country");
        String authorFirstName = authorResponse.body().jsonPath().getString("author.firstName");
        String authorLastName = authorResponse.body().jsonPath().getString("author.lastName");
        Integer authorId = authorResponse.body().jsonPath().getInt("author.id") +1;
        
        Response postResponse = new BookOperations().createBookWithAuthor(authorId, authorBiography, authorCountry, authorFirstName, authorLastName, GlobVar.bSecondDummyBookDescription, GlobVar.bSecondDummyBookIsbn, GlobVar.bSecondDummyBookNbrPages, GlobVar.bSecondDummyBookPublicationDate, GlobVar.bSecondDummyBookTitle, GlobVar.bSecondDummyBookTotalNbrCopies);

        assertEquals("The status code should be: 400",  400, postResponse.statusCode()); 
        assertEquals("response body should be Author does not exist in database.",  "Author does not exist in database.", postResponse.body().asString());
    }
    
    @Test //this test verifies that trying to post a new book with a valid authorId but the wrong authorName for that id returns the right statuscode (400) and appropriate response body message
    public void testInvalidPostBookWithAuthorIDButWrongAuthorName(){
        Response authorResponse = new AuthorOperations().getAuthor(GlobVar.dummyAuthorId); 
        String authorBiography = authorResponse.body().jsonPath().getString("author.bio");
        String authorCountry = authorResponse.body().jsonPath().getString("author.country");
        String authorFirstName = authorResponse.body().jsonPath().getString("author.firstName") +"blarg";
        String authorLastName = authorResponse.body().jsonPath().getString("author.lastName") + "blargtwo";
        Integer authorId = authorResponse.body().jsonPath().getInt("author.id");
        Response postResponse = new BookOperations().createBookWithAuthor(authorId, authorBiography, authorCountry, authorFirstName, authorLastName, GlobVar.bSecondDummyBookDescription, GlobVar.bSecondDummyBookIsbn, GlobVar.bSecondDummyBookNbrPages, GlobVar.bSecondDummyBookPublicationDate, GlobVar.bSecondDummyBookTitle, GlobVar.bSecondDummyBookTotalNbrCopies);
        assertEquals("The status code should be: 400",  400, postResponse.statusCode()); 
        assertEquals("response body should be Author does not exist in database.",  "Author does not exist in database.", postResponse.body().asString());
    }
    
    
    
    @Test //this test verifies that you cannot perform a post request to a given book by its bookId
    public void testForbiddenPostToBooksId(){
        String resourceName = "books/"+GlobVar.aDummyBookId;
        Response response = given().accept(ContentType.JSON).post(GlobVar.BASE_URL+resourceName);
        assertEquals("The status code should be: 405, method not allowed",  405, response.statusCode());  
        assertEquals("response body should be blank",  "", response.body().asString());
    }
    
    
    @Test //this test verifies that you cannot perform a post request to a given book by its author's authorId
    public void testForbiddenPostToBooksByAuthorAuthorId(){
        String resourceName = "books/byauthor/"+GlobVar.dummyAuthorId;
        Response response = given().accept(ContentType.JSON).post(GlobVar.BASE_URL+resourceName);
        assertEquals("The status code should be: 405, method not allowed",  405, response.statusCode());
        assertEquals("response body should be blank",  "", response.body().asString());
    }
    
    
    
    @Test //this test tries to create a new book, then attempts to add our existing dummy author to that book and 
    public void testPostAuthorToBook(){
        BookOperations bookOperations = new BookOperations();
        AuthorOperations authorOperations = new AuthorOperations();
        
        //this part makes a new book and verifies that it was created)
        Response postResponse = bookOperations.createBookWithInput(GlobVar.bSecondDummyBookDescription, GlobVar.bSecondDummyBookIsbn, GlobVar.bSecondDummyBookNbrPages, GlobVar.bSecondDummyBookPublicationDate, GlobVar.bSecondDummyBookTitle, GlobVar.bSecondDummyBookTotalNbrCopies);
        assertEquals("status code should be 201",  201, postResponse.statusCode());
        assertEquals("response body should be blank", "", postResponse.body().asString());
        
        //the part below fetches all the data of the operation we just performed
        String expectedDescription = from(bookOperations.getLatestJsonString()).getString("book.description");
        String expectedIsbn = from(bookOperations.getLatestJsonString()).getString("book.isbn");
        Integer expectedNbrPages = from(bookOperations.getLatestJsonString()).getInt("book.nbrPages");
        String expectedPublicationDate = from(bookOperations.getLatestJsonString()).getString("book.publicationDate");
        String expectedTitle = from(bookOperations.getLatestJsonString()).getString("book.title");
        Integer expectedTotalNbrCopies = from(bookOperations.getLatestJsonString()).getInt("book.totalNbrCopies");
        
        //the part below fetches all the data of the last book in the list (which should be the same as the data from the last operation we performed)
        Response verifyResponse = new BookOperations().getAllBooks();
                
        String verifyDescription = verifyResponse.jsonPath().getString("books.book[-1].description");
        String verifyIsbn = verifyResponse.jsonPath().getString("books.book[-1].isbn");
        Integer verifyNbrPages = verifyResponse.jsonPath().getInt("books.book[-1].nbrPages");
        String verifyPublicationDate = verifyResponse.jsonPath().getString("books.book[-1].publicationDate");
        String verifyTitle = verifyResponse.jsonPath().getString("books.book[-1].title");
        Integer verifyTotalNbrCopies = verifyResponse.jsonPath().getInt("books.book[-1].totalNbrCopies");
        
        //this part finally verifies that the last operation we performed and the last book in the list have the same data, in other words that our operation succeeded
        assertEquals(expectedDescription, verifyDescription);
        assertEquals(expectedIsbn, verifyIsbn);
        assertEquals(expectedNbrPages, verifyNbrPages); 
        assertEquals(expectedPublicationDate, verifyPublicationDate);
        assertEquals(expectedTitle, verifyTitle);
        assertEquals(expectedTotalNbrCopies, verifyTotalNbrCopies);
        
        //this part adds an existing author to the new book if a new book was successfully created and verifies that the author has been added to said book and that we get the right response statuscode (200) and finally deletes the book as part of cleanup
        if(postResponse.statusCode() == 201){
            Response authorResponse = authorOperations.getAuthor(GlobVar.dummyAuthorId); 
            
            Integer authorId = authorResponse.body().jsonPath().getInt("author.id");
            String authorBiography = authorResponse.body().jsonPath().getString("author.bio");
            String authorCountry = authorResponse.body().jsonPath().getString("author.country");
            String authorFirstName = authorResponse.body().jsonPath().getString("author.firstName");
            String authorLastName = authorResponse.body().jsonPath().getString("author.lastName");
            
            Integer newBookId = new BookOperations().getLastBook().jsonPath().getInt("book.id");
        
            Response postAuthorToBookResponse = new BookOperations().addAuthorToBook(newBookId, authorId, authorBiography, authorCountry, authorFirstName, authorLastName);
            assertEquals("The status code should be: 200",  200, postAuthorToBookResponse.statusCode());
            assertEquals("response body should be blank", "", postAuthorToBookResponse.body().asString());  
        
            Response getAuthorResponse = bookOperations.getBookById(newBookId);
            Integer verifyAuthorId = getAuthorResponse.jsonPath().getInt("book.author.id");
            String verifyAuthorBio = getAuthorResponse.jsonPath().getString("book.author.bio");
            String verifyAuthorCountry = getAuthorResponse.jsonPath().getString("book.author.country");
            String verifyAuthorFirstName = getAuthorResponse.jsonPath().getString("book.author.firstName");
            String verifyAuthorLastName = getAuthorResponse.jsonPath().getString("book.author.lastName");
            
            assertEquals(authorId, verifyAuthorId);
            assertEquals(authorBiography, verifyAuthorBio);
            assertEquals(authorCountry, verifyAuthorCountry);
            assertEquals(authorFirstName, verifyAuthorFirstName);
            assertEquals(authorLastName, verifyAuthorLastName);
            
        }
        
        // this bit cleans up the mess if there is a mess to clean up
        if(postResponse.statusCode() == 201){
            Response deleteResponse = new BookOperations().deleteLastBook();
            assertEquals("The status code should be: 204",  204, deleteResponse.statusCode());
            assertEquals("response body should be blank", "", deleteResponse.body().asString());
        }
    }
    
    @Test //this test verifies that you cannot post an author to a book that already has that author on its list of authors, it first creates a new dummybook, adds our dummy author to it, then tries to add the same author again. Then verifies that we get the right response statuscode (400) and response body message. Finally it deletes the new dummybook as part of cleanup
    public void testInvalidPostAuthorToBookTwice(){
        BookOperations bookOperations = new BookOperations();
        AuthorOperations authorOperations = new AuthorOperations();
        
        //this part makes a new book and verifies that it was created)
        Response postResponse = bookOperations.createBookWithInput(GlobVar.bSecondDummyBookDescription, GlobVar.bSecondDummyBookIsbn, GlobVar.bSecondDummyBookNbrPages, GlobVar.bSecondDummyBookPublicationDate, GlobVar.bSecondDummyBookTitle, GlobVar.bSecondDummyBookTotalNbrCopies);
        assertEquals("status code should be 201",  201, postResponse.statusCode());
        assertEquals("response body should be blank", "", postResponse.body().asString());
        
        //the part below fetches all the data of the operation we just performed
        String expectedDescription = from(bookOperations.getLatestJsonString()).getString("book.description");
        String expectedIsbn = from(bookOperations.getLatestJsonString()).getString("book.isbn");
        Integer expectedNbrPages = from(bookOperations.getLatestJsonString()).getInt("book.nbrPages");
        String expectedPublicationDate = from(bookOperations.getLatestJsonString()).getString("book.publicationDate");
        String expectedTitle = from(bookOperations.getLatestJsonString()).getString("book.title");
        Integer expectedTotalNbrCopies = from(bookOperations.getLatestJsonString()).getInt("book.totalNbrCopies");
        
        //the part below fetches all the data of the last book in the list (which should be the same as the data from the last operation we performed)
        Response verifyResponse = new BookOperations().getAllBooks();
                
        String verifyDescription = verifyResponse.jsonPath().getString("books.book[-1].description");
        String verifyIsbn = verifyResponse.jsonPath().getString("books.book[-1].isbn");
        Integer verifyNbrPages = verifyResponse.jsonPath().getInt("books.book[-1].nbrPages");
        String verifyPublicationDate = verifyResponse.jsonPath().getString("books.book[-1].publicationDate");
        String verifyTitle = verifyResponse.jsonPath().getString("books.book[-1].title");
        Integer verifyTotalNbrCopies = verifyResponse.jsonPath().getInt("books.book[-1].totalNbrCopies");
        
        //this part finally verifies that the last operation we performed and the last book in the list have the same data, in other words that our operation succeeded
        assertEquals(expectedDescription, verifyDescription);
        assertEquals(expectedIsbn, verifyIsbn);
        assertEquals(expectedNbrPages, verifyNbrPages); 
        assertEquals(expectedPublicationDate, verifyPublicationDate);
        assertEquals(expectedTitle, verifyTitle);
        assertEquals(expectedTotalNbrCopies, verifyTotalNbrCopies);
        
        //this part adds an existing author to the new book if a new book was successfully created and verifies that the author has been added to said book
        if(postResponse.statusCode() == 201){
            Response authorResponse = authorOperations.getAuthor(GlobVar.dummyAuthorId); 
            
            Integer authorId = authorResponse.body().jsonPath().getInt("author.id");
            String authorBiography = authorResponse.body().jsonPath().getString("author.bio");
            String authorCountry = authorResponse.body().jsonPath().getString("author.country");
            String authorFirstName = authorResponse.body().jsonPath().getString("author.firstName");
            String authorLastName = authorResponse.body().jsonPath().getString("author.lastName");
            
            Integer newBookId = new BookOperations().getLastBook().jsonPath().getInt("book.id");
        
            Response postAuthorToBookResponse = new BookOperations().addAuthorToBook(newBookId, authorId, authorBiography, authorCountry, authorFirstName, authorLastName);
            assertEquals("The status code should be: 200",  200, postAuthorToBookResponse.statusCode());
            assertEquals("response body should be blank", "", postAuthorToBookResponse.body().asString());  
        
            Response getAuthorResponse = bookOperations.getBookById(newBookId);
            Integer verifyAuthorId = getAuthorResponse.jsonPath().getInt("book.author.id");
            String verifyAuthorBio = getAuthorResponse.jsonPath().getString("book.author.bio");
            String verifyAuthorCountry = getAuthorResponse.jsonPath().getString("book.author.country");
            String verifyAuthorFirstName = getAuthorResponse.jsonPath().getString("book.author.firstName");
            String verifyAuthorLastName = getAuthorResponse.jsonPath().getString("book.author.lastName");
            
            assertEquals(authorId, verifyAuthorId);
            assertEquals(authorBiography, verifyAuthorBio);
            assertEquals(authorCountry, verifyAuthorCountry);
            assertEquals(authorFirstName, verifyAuthorFirstName);
            assertEquals(authorLastName, verifyAuthorLastName);
            
            //this part tries to post the same author to the book again which should not work
            Response postAuthorToBookTwiceResponse = new BookOperations().addAuthorToBook(newBookId, authorId, authorBiography, authorCountry, authorFirstName, authorLastName);
            assertEquals("The status code should be: 400",  400, postAuthorToBookTwiceResponse.statusCode());
            assertEquals("response body should be Author is already author of this book.", "Author is already author of this book.", postAuthorToBookTwiceResponse.body().asString());
            
        }
        
        // this bit cleans up the mess if there is a mess to clean up
        if(postResponse.statusCode() == 201){
            Response deleteResponse = new BookOperations().deleteLastBook();
            assertEquals("The status code should be: 204",  204, deleteResponse.statusCode());
            assertEquals("response body should be blank", "", deleteResponse.body().asString());
        }
    }
    
    @Test //this test verifies that you cannot post an author to a book without including an authorId, it also verifies that we get the appropriate response statuscode (400) and body message. the new dummybook created is then deleted as part of cleanup
    public void testInvalidPostAuthorToBookWithNoAuthorId(){
        BookOperations bookOperations = new BookOperations();
        AuthorOperations authorOperations = new AuthorOperations();
        
        //this part makes a new book and verifies that it was created)
        Response postResponse = bookOperations.createBookWithInput(GlobVar.bSecondDummyBookDescription, GlobVar.bSecondDummyBookIsbn, GlobVar.bSecondDummyBookNbrPages, GlobVar.bSecondDummyBookPublicationDate, GlobVar.bSecondDummyBookTitle, GlobVar.bSecondDummyBookTotalNbrCopies);
        assertEquals("status code should be 201",  201, postResponse.statusCode());
        assertEquals("response body should be blank", "", postResponse.body().asString());
        
        //the part below fetches all the data of the operation we just performed
        String expectedDescription = from(bookOperations.getLatestJsonString()).getString("book.description");
        String expectedIsbn = from(bookOperations.getLatestJsonString()).getString("book.isbn");
        Integer expectedNbrPages = from(bookOperations.getLatestJsonString()).getInt("book.nbrPages");
        String expectedPublicationDate = from(bookOperations.getLatestJsonString()).getString("book.publicationDate");
        String expectedTitle = from(bookOperations.getLatestJsonString()).getString("book.title");
        Integer expectedTotalNbrCopies = from(bookOperations.getLatestJsonString()).getInt("book.totalNbrCopies");
        
        //the part below fetches all the data of the last book in the list (which should be the same as the data from the last operation we performed)
        Response verifyResponse = new BookOperations().getAllBooks();
                
        String verifyDescription = verifyResponse.jsonPath().getString("books.book[-1].description");
        String verifyIsbn = verifyResponse.jsonPath().getString("books.book[-1].isbn");
        Integer verifyNbrPages = verifyResponse.jsonPath().getInt("books.book[-1].nbrPages");
        String verifyPublicationDate = verifyResponse.jsonPath().getString("books.book[-1].publicationDate");
        String verifyTitle = verifyResponse.jsonPath().getString("books.book[-1].title");
        Integer verifyTotalNbrCopies = verifyResponse.jsonPath().getInt("books.book[-1].totalNbrCopies");
        
        //this part finally verifies that the last operation we performed and the last book in the list have the same data, in other words that our operation succeeded
        assertEquals(expectedDescription, verifyDescription);
        assertEquals(expectedIsbn, verifyIsbn);
        assertEquals(expectedNbrPages, verifyNbrPages); 
        assertEquals(expectedPublicationDate, verifyPublicationDate);
        assertEquals(expectedTitle, verifyTitle);
        assertEquals(expectedTotalNbrCopies, verifyTotalNbrCopies);
        
        //this part tries, if a new book was created, adding an author without his authorId to the new book and verifies that this does not work
        if(postResponse.statusCode() == 201){
            Response authorResponse = authorOperations.getAuthor(GlobVar.dummyAuthorId); 
            String authorName = authorResponse.body().jsonPath().getString("author.name");
            Integer newBookId = new BookOperations().getLastBook().jsonPath().getInt("book.id");
        
            Response postAuthorToBookResponse = new BookOperations().invalidAddAuthorToBookWithoutAuthorId(authorName, newBookId);
            assertEquals("The status code should be: 400",  400, postAuthorToBookResponse.statusCode());
            assertEquals("response body should be Author must have id field set.", "Author must have id field set.", postAuthorToBookResponse.body().asString());
        }
        // this bit cleans up the mess if there is a mess to clean up
        if(postResponse.statusCode() == 201){
            Response deleteResponse = new BookOperations().deleteLastBook();
            assertEquals("The status code should be: 204",  204, deleteResponse.statusCode());
            assertEquals("response body should be blank", "", deleteResponse.body().asString());
        }
    }
    
    
    @Test //this test verifies that you cannot add an author that exists in the system to a book that does not exist in said system, that we get the appropriate response statuscode (404) and body message
    public void testInvalidPostAuthorToBookWithNonxistingBook(){
        BookOperations bookOperations = new BookOperations();
        AuthorOperations authorOperations = new AuthorOperations();    
        
        //this part adds an existing author to a nonexisting book which should not work
        
        Response authorResponse = authorOperations.getAuthor(GlobVar.dummyAuthorId); 
            
        Integer authorId = authorResponse.body().jsonPath().getInt("author.id");
        String authorBiography = authorResponse.body().jsonPath().getString("author.bio");
        String authorCountry = authorResponse.body().jsonPath().getString("author.bio");
        String authorFirstName = authorResponse.body().jsonPath().getString("author.firstName");
        String authorLastName = authorResponse.body().jsonPath().getString("author.lastName");
            
        Integer newBookId = new BookOperations().getLastBook().jsonPath().getInt("book.id") + 1;
        
        Response postAuthorToBookResponse = new BookOperations().addAuthorToBook(newBookId, authorId, authorBiography, authorCountry, authorFirstName, authorLastName);
        assertEquals("The status code should be: 404",  404, postAuthorToBookResponse.statusCode());
        assertEquals("response body should be blank", "", postAuthorToBookResponse.body().asString());
    }
    
    
    
    @Test //this test attempts to post a new dummy author to the system, verifies that we get the right response statuscode (201) and a blank response body. it then deletes the new author
    public void testPostAuthor(){
        AuthorOperations authorOperations = new AuthorOperations();
        
        Response postResponse = authorOperations.createAuthor(GlobVar.secondDummyAuthorBio, GlobVar.secondDummyAuthorCountry, GlobVar.secondDummyAuthorFirstName, GlobVar.secondDummyAuthorLastName);
        assertEquals("status code should be 201",  201, postResponse.statusCode());
        assertEquals("response body should be blank", "", postResponse.body().asString());
        
        String expectedFirstName = from(authorOperations.getLatestJsonString()).getString("author.firstName");
        
        Response getResponse = authorOperations.getAllAuthors();
        String fetchedFirstName = getResponse.body().jsonPath().getString("authors.author[-1].firstName");
        assertEquals(expectedFirstName, fetchedFirstName);
        
        if(postResponse.statusCode() == 201){
            Response delResponse = authorOperations.deleteLastAuthor();
            assertEquals("status code should be 204",  204, delResponse.statusCode());
            assertEquals("response body should be blank", "", delResponse.body().asString());
        }
        
    }
    
    @Test //this test attempts to post a new author with a specified authorid that does not exist in the system, verifies that we get the right response statuscode (201) and that the response body is blank, it then deletes the author as part of cleanup
    public void testPostAuthorWithSpecificNewAuthorId(){
        AuthorOperations authorOperations = new AuthorOperations();
        
        Response postResponse = authorOperations.createAuthorWithId(GlobVar.dummyAuthorId + 1, GlobVar.secondDummyAuthorBio, GlobVar.secondDummyAuthorCountry, GlobVar.secondDummyAuthorFirstName, GlobVar.secondDummyAuthorLastName);
        assertEquals("status code should be 201",  201, postResponse.statusCode());
        assertEquals("response body should be blank", "", postResponse.body().asString());
        
        String expectedFirstName = from(authorOperations.getLatestJsonString()).getString("author.firstName");
        Integer expectedId = from(authorOperations.getLatestJsonString()).getInt("author.id");
        
        Response getResponse = authorOperations.getAllAuthors();
        String fetchedAuthorFirstName = getResponse.body().jsonPath().getString("authors.author[-1].firstName");
        Integer fetchedAuthorId = getResponse.jsonPath().getInt("authors.author[-1].id");
        
        assertEquals(expectedFirstName, fetchedAuthorFirstName);
        assertEquals(expectedId, fetchedAuthorId);
        
        if(postResponse.statusCode() == 201){
            Response delResponse = authorOperations.deleteLastAuthor();
            assertEquals("status code should be 204",  204, delResponse.statusCode());
            assertEquals("response body should be blank", "", delResponse.body().asString());
        }
    }
    
    @Test //this test verifies that you cannot post an author to the system using an authorId that already exists, that we get back the right response statuscode (400) and appropriate response body message
    public void testInvalidPostAuthorWithExistingAuthorId(){
        Response postResponse = new AuthorOperations().createAuthorWithId(GlobVar.dummyAuthorId, GlobVar.secondDummyAuthorBio, GlobVar.secondDummyAuthorCountry, GlobVar.secondDummyAuthorFirstName, GlobVar.secondDummyAuthorLastName);
        assertEquals("status code should be 400",  400, postResponse.statusCode());
        assertEquals("response body should be Author was null.", "Author was null.", postResponse.body().asString());
    }
    
    
    @Test //this test verifies that we cannot perform a post request to an author's authorId directly, that we get the right response statuscode (405) and response body
    public void testForbiddenPostToAuthorsId(){
        String resourceName = "books/"+GlobVar.dummyAuthorId;
        Response response = given().accept(ContentType.JSON).post(GlobVar.BASE_URL+resourceName);
        assertEquals("The status code should be: 405, method not allowed",  405, response.statusCode());
        assertEquals("response body should be blank", "", response.body().asString());
    }
    
    
    
}
