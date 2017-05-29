package se.nackademin.librarytest;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import se.nackademin.librarytest.helpers.AuthorHelper;
import se.nackademin.librarytest.helpers.BookHelper;
import se.nackademin.librarytest.helpers.GlobVarSelenide;
import se.nackademin.librarytest.helpers.PreparationHelper;
import se.nackademin.librarytest.helpers.UserHelper;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.model.User;



public class SelenideTest extends TestBase{
    private static final Logger LOG =  Logger.getLogger(SelenideTest.class.getName());

    public SelenideTest(){
    
    }
    
    @BeforeClass //this method creates a dummy book, dummy author and adds the author to the book to be used during test executions
    public static void TestLibrarianCreatesAuthorAndBook(){
        LOG.log(Level.INFO, "*** Starting Preparing library dummies needed for tests ***");
        
        TestBase.littleUp();
        
        PreparationHelper.MakeDummyUserAuthorBookAndAdmin();
        //note the book and author verification below checks from the perspective of an anonymous user, the testLibrarianSiteBrowse() test will verify that a librarian type user too can see these books. However if the asserts here fail then it is likely that the book and author were not created for some reason which will make most of our other tests unable to perform properly
        Book verifyBook = BookHelper.fetchBook(GlobVarSelenide.aDummyBookTitle, "all");
        assertEquals("The book's title should be " +GlobVarSelenide.aDummyBookTitle, GlobVarSelenide.aDummyBookTitle, verifyBook.getTitle());
        assertEquals("The book's author's full name should be " +GlobVarSelenide.dummyAuthorFullName, GlobVarSelenide.dummyAuthorFullName, verifyBook.getAuthor());
        assertEquals("The book's description should be " +GlobVarSelenide.aDummyBookDescription, GlobVarSelenide.aDummyBookDescription, verifyBook.getDescription());
        assertEquals("The book's isbn should be " +GlobVarSelenide.aDummyBookIsbn, GlobVarSelenide.aDummyBookIsbn, verifyBook.getIsbn());
        assertEquals("The book's total number of available copies should be " +GlobVarSelenide.aDummyBookTotalNbrCopies, GlobVarSelenide.aDummyBookTotalNbrCopies, verifyBook.getNbrAvailable());
        
        Author verifyAuthor = AuthorHelper.fetchAuthor(GlobVarSelenide.dummyAuthorFullName, "all");
        assertEquals("The authors's full name should be " +GlobVarSelenide.dummyAuthorFullName, GlobVarSelenide.dummyAuthorFullName, verifyAuthor.getFullName());
        assertEquals("The author's country should be " +GlobVarSelenide.dummyAuthorCountry, GlobVarSelenide.dummyAuthorCountry, verifyAuthor.getCountry());
        assertEquals("The author's biography should be " +GlobVarSelenide.dummyAuthorBio, GlobVarSelenide.dummyAuthorBio, verifyAuthor.getBiography());
        
        LOG.log(Level.INFO, "*** Finished preparing library dummies needed for tests ***");
    }
    
    @AfterClass //this method removes the dummies created by the previous method
    public static void RemoveDummyAuthorBookAndAdmin(){
        LOG.log(Level.INFO, "*** Starting to remove library dummies no longer needed ***");
        
        TestBase.littleUp();
        
        PreparationHelper.RemoveDummyAuthorBookAndAdmin();
        
        LOG.log(Level.INFO, "*** Finished removing library dummies no longer needed ***");
    }
    
    //@Test //this test starts with an anonymous user creating a loaner account with randomized displayName and password. Then it verifies that you can log in as that account
    public void testAnonymousCreatesNewMinimalUser(){
        LOG.log(Level.INFO, "*** Starting testAnonymousCreatesNewMinimalUser() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String uuid = UUID.randomUUID().toString().substring(0, 12);
        
        UserHelper.createNewMinimal(uuid, uuid);
        String loggedInAs = UserHelper.logInAsUser(uuid, uuid);
        assertEquals("sign-in-message should be " + "Logged in as " + uuid +".", "Logged in as " + uuid +".", loggedInAs);
        
        //removing users from the system for cleanup purposes is not currently supported with the user interface as only admins can remove users, there is no way for admins to view or alter other users thus the only user the admin account can remove is itself!
        //if something goes wrong, use the test log to find the randomised uuid generated for name and password to sign in and manually verify what the test has and has not done to the new user or if one was not created after all, etc
        
        LOG.log(Level.INFO, "*** Finished testAnonymousCreatesNewMinimalUser() test ***");
    }
    
    //@Test //this test starts with an anonymous user creating a loaner account with randomized displayName and password. Then it logs in as that loander-role user and attempts to edit its own data and finally verifies that this has been done.
    public void testAnonymousCreatesMinimalUserThenEditsUser(){
        LOG.log(Level.INFO, "*** Starting testAnonymousCreatesMinimalUserThenEditsUser() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String uuid = UUID.randomUUID().toString().substring(0, 12);
        
        UserHelper.createNewMinimal(uuid, uuid);
        String loggedInAs = UserHelper.logInAsUser(uuid, uuid);
        assertEquals("sign-in-message should be " + "Logged in as " + uuid +".", "Logged in as " + uuid +".", loggedInAs);
        
        UserHelper.editCurrentUserProfile(uuid, uuid, uuid, uuid, uuid, uuid);
        
        assertEquals("user email should be: " + uuid,  uuid, UserHelper.getCurrentUserProfileEmail());
        
        User verifyLoaner = UserHelper.fetchUser(uuid, uuid, "all");
        assertEquals("The user's displayname should be " +uuid, uuid, verifyLoaner.getDisplayName());
        //cannot verify passwords because fetching a password from the interface returns null, aka **** 
        assertEquals("The user's firstName should be " +uuid, uuid, verifyLoaner.getFirstName());
        assertEquals("The user's lastName should be " +uuid, uuid, verifyLoaner.getLastName());
        assertEquals("The user's phone number should be " +uuid, uuid, verifyLoaner.getPhone());
        assertEquals("The user's email should be " +uuid, uuid, verifyLoaner.getEmail());
        //removing users from the system for cleanup purposes is not currently supported with the user interface as only admins can remove users, there is no way for admins to view or alter other users thus the only user the admin account can remove is itself!
        //if something goes wrong, use the test log to find the randomised uuid generated for name and password to sign in and manually verify what the test has and has not done to the new user or if one was not created after all, etc
        
        LOG.log(Level.INFO, "*** Finished testAnonymousCreatesMinimalUserThenEditsUser() test ***");
    }
    
    @Test //this test verifies that a user not signed in as anyone can browse the books and authors and can do so through the roundabout method of finding a book, clicking that book's first author, verifying the author data, and vice versa for bookdata via the author!
    public void testAnonymousSiteBrowse(){
        LOG.log(Level.INFO, "*** Starting testAnonymousSiteBrowse() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        Book verifyBook = BookHelper.fetchABookThroughAuthor(GlobVarSelenide.dummyAuthorFullName, "all");
        assertEquals("The book's title should be " +GlobVarSelenide.aDummyBookTitle, GlobVarSelenide.aDummyBookTitle, verifyBook.getTitle());
        assertEquals("The book's author's full name should be " +GlobVarSelenide.dummyAuthorFullName, GlobVarSelenide.dummyAuthorFullName, verifyBook.getAuthor());
        assertEquals("The book's description should be " +GlobVarSelenide.aDummyBookDescription, GlobVarSelenide.aDummyBookDescription, verifyBook.getDescription());
        assertEquals("The book's isbn should be " +GlobVarSelenide.aDummyBookIsbn, GlobVarSelenide.aDummyBookIsbn, verifyBook.getIsbn());
        assertEquals("The book's total number of available copies should be " +GlobVarSelenide.aDummyBookTotalNbrCopies, GlobVarSelenide.aDummyBookTotalNbrCopies, verifyBook.getNbrAvailable());
        
        Author verifyAuthor = AuthorHelper.fetchAnAuthorThroughBook(GlobVarSelenide.aDummyBookTitle, "all");
        assertEquals("The authors's full name should be " +GlobVarSelenide.dummyAuthorFullName, GlobVarSelenide.dummyAuthorFullName, verifyAuthor.getFullName());
        assertEquals("The author's country should be " +GlobVarSelenide.dummyAuthorCountry, GlobVarSelenide.dummyAuthorCountry, verifyAuthor.getCountry());
        assertEquals("The author's biography should be " +GlobVarSelenide.dummyAuthorBio, GlobVarSelenide.dummyAuthorBio, verifyAuthor.getBiography());
        
        LOG.log(Level.INFO, "*** Finished testAnonymousSiteBrowse() test ***");
    }
    
    @Test //this test verifies that we can log in as our dummy loander-type user andthat we can browse books and authors and find our dummy book and dummy author respectively.
    public void testLoanerSiteBrowse(){
        LOG.log(Level.INFO, "*** Starting testLoanerSiteBrowse() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String loggedInAs = UserHelper.logInAsUser(GlobVarSelenide.bDummyUserDisplayName, GlobVarSelenide.bDummyUserPassword);
        assertEquals("sign-in-message should be " + "Logged in as " + GlobVarSelenide.bDummyUserDisplayName +".", "Logged in as " + GlobVarSelenide.bDummyUserDisplayName +".", loggedInAs);
        
        Book verifyBook = BookHelper.fetchBook(GlobVarSelenide.aDummyBookTitle, "all");
        assertEquals("The book's title should be " +GlobVarSelenide.aDummyBookTitle, GlobVarSelenide.aDummyBookTitle, verifyBook.getTitle());
        assertEquals("The book's author's full name should be " +GlobVarSelenide.dummyAuthorFullName, GlobVarSelenide.dummyAuthorFullName, verifyBook.getAuthor());
        assertEquals("The book's description should be " +GlobVarSelenide.aDummyBookDescription, GlobVarSelenide.aDummyBookDescription, verifyBook.getDescription());
        assertEquals("The book's isbn should be " +GlobVarSelenide.aDummyBookIsbn, GlobVarSelenide.aDummyBookIsbn, verifyBook.getIsbn());
        assertEquals("The book's total number of available copies should be " +GlobVarSelenide.aDummyBookTotalNbrCopies, GlobVarSelenide.aDummyBookTotalNbrCopies, verifyBook.getNbrAvailable());
        
        Author verifyAuthor = AuthorHelper.fetchAuthor(GlobVarSelenide.dummyAuthorFullName, "all");
        assertEquals("The authors's full name should be " +GlobVarSelenide.dummyAuthorFullName, GlobVarSelenide.dummyAuthorFullName, verifyAuthor.getFullName());
        assertEquals("The author's country should be " +GlobVarSelenide.dummyAuthorCountry, GlobVarSelenide.dummyAuthorCountry, verifyAuthor.getCountry());
        assertEquals("The author's biography should be " +GlobVarSelenide.dummyAuthorBio, GlobVarSelenide.dummyAuthorBio, verifyAuthor.getBiography());
        
        User verifyLoaner = UserHelper.fetchUser(GlobVarSelenide.bDummyUserDisplayName, GlobVarSelenide.bDummyUserPassword, "all");
        assertEquals("The user's displayname should be " +GlobVarSelenide.bDummyUserDisplayName, GlobVarSelenide.bDummyUserDisplayName, verifyLoaner.getDisplayName());
        //cannot verify passwords because fetching a password from the interface returns null, aka **** 
        assertEquals("The user's firstName should be " +GlobVarSelenide.bDummyUserFirstName, GlobVarSelenide.bDummyUserFirstName, verifyLoaner.getFirstName());
        assertEquals("The user's lastName should be " +GlobVarSelenide.bDummyUserLastName, GlobVarSelenide.bDummyUserLastName, verifyLoaner.getLastName());
        assertEquals("The user's phone number should be " +GlobVarSelenide.bDummyUserPhone, GlobVarSelenide.bDummyUserPhone, verifyLoaner.getPhone());
        assertEquals("The user's email should be " +GlobVarSelenide.bDummyUserEmail, GlobVarSelenide.bDummyUserEmail, verifyLoaner.getEmail());
        
        LOG.log(Level.INFO, "*** Finished testLoanerSiteBrowse() test ***");
    }
    
    @Test//this test verifies that we can log in as our dummy Librarian-type user andthat we can browse books and authors and find our dummy book and dummy author respectively.
    public void testLibrarianSiteBrowse(){
        LOG.log(Level.INFO, "*** Starting testLibrarianSiteBrowse() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String loggedInAs = UserHelper.logInAsUser(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword);
        assertEquals("sign-in-message should be " + "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", loggedInAs);
        
        Book verifyBook = BookHelper.fetchBook(GlobVarSelenide.aDummyBookTitle, "all");
        assertEquals("The book's title should be " +GlobVarSelenide.aDummyBookTitle, GlobVarSelenide.aDummyBookTitle, verifyBook.getTitle());
        assertEquals("The book's author's full name should be " +GlobVarSelenide.dummyAuthorFullName, GlobVarSelenide.dummyAuthorFullName, verifyBook.getAuthor());
        assertEquals("The book's description should be " +GlobVarSelenide.aDummyBookDescription, GlobVarSelenide.aDummyBookDescription, verifyBook.getDescription());
        assertEquals("The book's isbn should be " +GlobVarSelenide.aDummyBookIsbn, GlobVarSelenide.aDummyBookIsbn, verifyBook.getIsbn());
        assertEquals("The book's total number of available copies should be " +GlobVarSelenide.aDummyBookTotalNbrCopies, GlobVarSelenide.aDummyBookTotalNbrCopies, verifyBook.getNbrAvailable());
        
        Author verifyAuthor = AuthorHelper.fetchAuthor(GlobVarSelenide.dummyAuthorFullName, "all");
        assertEquals("The authors's full name should be " +GlobVarSelenide.dummyAuthorFullName, GlobVarSelenide.dummyAuthorFullName, verifyAuthor.getFullName());
        assertEquals("The author's country should be " +GlobVarSelenide.dummyAuthorCountry, GlobVarSelenide.dummyAuthorCountry, verifyAuthor.getCountry());
        assertEquals("The author's biography should be " +GlobVarSelenide.dummyAuthorBio, GlobVarSelenide.dummyAuthorBio, verifyAuthor.getBiography());
        
        User verifyLoaner = UserHelper.fetchUser(GlobVarSelenide.bDummyUserDisplayName, GlobVarSelenide.bDummyUserPassword, "all");
        assertEquals("The user's displayname should be " +GlobVarSelenide.bDummyUserDisplayName, GlobVarSelenide.bDummyUserDisplayName, verifyLoaner.getDisplayName());
        //cannot verify passwords because fetching a password from the interface returns null, aka **** 
        assertEquals("The user's firstName should be " +GlobVarSelenide.bDummyUserFirstName, GlobVarSelenide.bDummyUserFirstName, verifyLoaner.getFirstName());
        assertEquals("The user's lastName should be " +GlobVarSelenide.bDummyUserLastName, GlobVarSelenide.bDummyUserLastName, verifyLoaner.getLastName());
        assertEquals("The user's phone number should be " +GlobVarSelenide.bDummyUserPhone, GlobVarSelenide.bDummyUserPhone, verifyLoaner.getPhone());
        assertEquals("The user's email should be " +GlobVarSelenide.bDummyUserEmail, GlobVarSelenide.bDummyUserEmail, verifyLoaner.getEmail());
        
        LOG.log(Level.INFO, "*** Finished testLibrarianSiteBrowse() test ***");
    }
    
    @Test
    public void testLoanerBorrowAndReturnBook(){
        LOG.log(Level.INFO, "*** Starting testLoanerBorrowAndReturnBook() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String loggedInAs = UserHelper.logInAsUser(GlobVarSelenide.bDummyUserDisplayName, GlobVarSelenide.bDummyUserPassword);
        assertEquals("sign-in-message should be " + "Logged in as " + GlobVarSelenide.bDummyUserDisplayName +".", "Logged in as " + GlobVarSelenide.bDummyUserDisplayName +".", loggedInAs);
        
        
        Integer beforeBorrowing = BookHelper.borrowBook(GlobVarSelenide.aDummyBookTitle);
        Book book = BookHelper.fetchBook(GlobVarSelenide.aDummyBookTitle, "all");
        String borrowedBookTitle = book.getTitle();
        Integer afterBorrowingPlusOne = book.getNbrAvailable() +1;
        assertEquals("the number before borrowing should be the same as the number after borrowing + one", beforeBorrowing, afterBorrowingPlusOne);
        
        UserHelper.goToMyProfilePage();
        assertEquals("The title of the first book in the users borrowed books list should be: " + borrowedBookTitle,  borrowedBookTitle, UserHelper.getCurrentUserFirstBorrowedBookTitle());
        UserHelper.goCurrentUserFirstBorrowedBook();
        BookHelper.returnBook();
        BookHelper.browseToBook(GlobVarSelenide.aDummyBookTitle);
        Integer afterReturn = BookHelper.fetchBook(GlobVarSelenide.aDummyBookTitle, "all").getNbrAvailable();
        assertEquals("the number after returning the book should be the same as the number before borrowing", beforeBorrowing, afterReturn);
        
        LOG.log(Level.INFO, "*** Finished testLoanerBorrowAndReturnBook() test ***");
    }
    
    @Test
    public void testLibrarianBorrowAndReturnBook(){
        LOG.log(Level.INFO, "*** Starting testLibrarianBorrowAndReturnBook() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String loggedInAs = UserHelper.logInAsUser(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword);
        assertEquals("sign-in-message should be " + "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", loggedInAs);
        
        Integer beforeBorrowing = BookHelper.borrowBook(GlobVarSelenide.aDummyBookTitle);
        Book book = BookHelper.fetchBook(GlobVarSelenide.aDummyBookTitle, "all");
        String borrowedBookTitle = book.getTitle();
        Integer afterBorrowingPlusOne = book.getNbrAvailable() +1;
        assertEquals("the number before borrowing should be the same as the number after borrowing + one", beforeBorrowing, afterBorrowingPlusOne);
        
        UserHelper.goToMyProfilePage();
        assertEquals("The title of the first book in the users borrowed books list should be: " + borrowedBookTitle,  borrowedBookTitle, UserHelper.getCurrentUserFirstBorrowedBookTitle());
        UserHelper.goCurrentUserFirstBorrowedBook();
        BookHelper.returnBook();
        BookHelper.browseToBook(GlobVarSelenide.aDummyBookTitle);
        Integer afterReturn = BookHelper.fetchBook(GlobVarSelenide.aDummyBookTitle, "all").getNbrAvailable();
        assertEquals("the number after returning the book should be the same as the number before borrowing", beforeBorrowing, afterReturn);
        
        LOG.log(Level.INFO, "*** Finished testLibrarianBorrowAndReturnBook() test ***");
    }
    
    //@Test //((need a way to find the ! inside the add author button))
    public void testInvalidDataEntryWhenLibrarianCreatesBook(){
        LOG.log(Level.INFO, "*** Starting testInvalidDataEntryWhenLibrarianCreatesBook() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String loggedInAs = UserHelper.logInAsUser(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword);
        assertEquals("sign-in-message should be " + "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", loggedInAs);
        
        // these tests are highly sensetive to how the site lets us know that certain data entries are invalid, some fields give us an error message, one, the number of pages, highlights the datafield, and others only give us a ! on the "add book" button (which as of writing this test actually reads "add author" but shouldnt)
        //the following Tries to create a book while providing invalid values for bookTitle (empty is invalid), Number of pages (non-integer is invalid), number of copies available (again non-integer isinvalid) and inappropriately formatted  date-string (only YYYY-MM-DD is valid)
        //empty book title is invalid, empty description and isbn is allowed
        BookHelper.createNewBookWithAuthor("", GlobVarSelenide.dummyAuthorFullName, "", "", GlobVarSelenide.bSecondDummyBookPublicationDate, GlobVarSelenide.aDummyBookTotalNbrCopies, GlobVarSelenide.aDummyBookNbrPages);
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", BookHelper.getErrorMessage());
        
        //add book title, change book publication date to an invalid entry
        BookHelper.createNewBookWithAuthor(GlobVarSelenide.bSecondDummyBookTitle, GlobVarSelenide.dummyAuthorFullName, "", "", "", GlobVarSelenide.aDummyBookTotalNbrCopies, GlobVarSelenide.aDummyBookNbrPages);
        //BookHelper.checkErrorSign(); //this will cause a test error if it fails by being abscent, 
        
        //change publication date to proper format, change number of pages to an invalid (non-integer) entry
        BookHelper.invalidCreateNewBookWithAuthor(GlobVarSelenide.bSecondDummyBookTitle, GlobVarSelenide.dummyAuthorFullName, "", "", GlobVarSelenide.bSecondDummyBookPublicationDate, GlobVarSelenide.aDummyBookTotalNbrCopies.toString(), "");
        BookHelper.checkErrorSign(); //this will cause a test error if it fails by being abscent, 
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", BookHelper.getErrorMessage());
        
        //change number of pages to valid integer, change number of books in inventory to invalid (non-integer) entry
        BookHelper.invalidCreateNewBookWithAuthor(GlobVarSelenide.bSecondDummyBookTitle, GlobVarSelenide.dummyAuthorFullName, "", "", GlobVarSelenide.bSecondDummyBookPublicationDate, "", GlobVarSelenide.aDummyBookNbrPages.toString());
        BookHelper.checkErrorSignInButton(); //this will cause a test error if it fails by being abscent, 
        
        LOG.log(Level.INFO, "*** Finished testInvalidDataEntryWhenLibrarianCreatesBook() test ***");
    }
    
    @Test 
    public void testInvalidDataEntryWhenLibrarianCreatesAuthor(){
        LOG.log(Level.INFO, "*** Starting testInvalidDataEntryWhenLibrarianCreatesAuthor() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String loggedInAs = UserHelper.logInAsUser(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword);
        assertEquals("sign-in-message should be " + "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", loggedInAs);
        
        //the following tries to create an author while providing invalid, empty String values for firstName
        AuthorHelper.createNewAuthor("", GlobVarSelenide.secondDummyAuthorLastName, GlobVarSelenide.secondDummyAuthorCountry, GlobVarSelenide.secondDummyAuthorBio);
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", AuthorHelper.getErrorMessage());
        
        //the following tries to create an author while providing invalid, empty String values for lastName
        AuthorHelper.createNewAuthor(GlobVarSelenide.secondDummyAuthorFirstName, "", GlobVarSelenide.dummyAuthorCountry, GlobVarSelenide.dummyAuthorBio);
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", AuthorHelper.getErrorMessage());
        
        //the following tries to create an author while providing invalid, empty String values for country 
        AuthorHelper.createNewAuthor(GlobVarSelenide.secondDummyAuthorFirstName, GlobVarSelenide.dummyAuthorLastName, "", GlobVarSelenide.dummyAuthorBio);
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", AuthorHelper.getErrorMessage());
        
        //the following tries to create an author while providing invalid, empty String values for biography
        AuthorHelper.createNewAuthor(GlobVarSelenide.secondDummyAuthorFirstName, GlobVarSelenide.secondDummyAuthorLastName, GlobVarSelenide.secondDummyAuthorCountry, "");
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", AuthorHelper.getErrorMessage());
        
        LOG.log(Level.INFO, "*** Finished testInvalidDataEntryWhenLibrarianCreatesAuthor() test ***");
    }
    
    @Test 
    public void testInvalidDataEntryWhenLibrarianCreatesUser(){
        LOG.log(Level.INFO, "*** Starting testInvalidDataEntryWhenLibrarianCreatesUser() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String loggedInAs = UserHelper.logInAsUser(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword);
        assertEquals("sign-in-message should be " + "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", loggedInAs);
        
        //the following tries to create a user while providing invalid, empty String values for displayName 
        UserHelper.createNewMinimal("", GlobVarSelenide.bDummyUserPassword);
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", UserHelper.getErrorMessage());
        
        //the following tries to create a user while providing invalid, empty String values for password 
        UserHelper.createNewMinimal(GlobVarSelenide.bDummyUserDisplayName, "");
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", UserHelper.getErrorMessage());
        
        LOG.log(Level.INFO, "*** Finished testInvalidDataEntryWhenLibrarianCreatesUser() test ***");
    }
    
}
