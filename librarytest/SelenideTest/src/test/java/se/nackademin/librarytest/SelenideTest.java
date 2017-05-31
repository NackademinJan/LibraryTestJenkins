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
import se.nackademin.librarytest.helpers.NoFindHelper;
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
    
    @Test //This test verifies that certain links cannot be found (and thus potentially used) by users of the anonymous type, aka they have not registered
    public void testInvalidAnonymousCannotFindTheseLinks(){
        LOG.log(Level.INFO, "*** Starting testInvalidAnonymousCannotFindTheseLinks() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        int cantFindAddBook = NoFindHelper.cantFindAddBook();
        assertEquals("the count of AddBook links found should be 0.",  0, cantFindAddBook);
        
        int cantFindAddAuthor = NoFindHelper.cantFindAddAuthor();
        assertEquals("the count of AddAuthor links found should be 0.",  0, cantFindAddAuthor);
        
        int cantFindMyProfile = NoFindHelper.cantFindMyProfile();
        assertEquals("the count of MyProfile links found should be 0.",  0, cantFindMyProfile);
        
        int cantFindBorrowBookButton = NoFindHelper.cantFindBorrowBookButton(GlobVarSelenide.aDummyBookTitle);
        assertEquals("the count of Borrow Book Buttons found should be 0.",  0, cantFindBorrowBookButton);
        
        int cantFindReturnBookButton = NoFindHelper.cantFindReturnBookButton(GlobVarSelenide.aDummyBookTitle);
        assertEquals("the count of Return Book Buttons found should be 0.",  0, cantFindReturnBookButton);
        
        int cantFindEditBookButton = NoFindHelper.cantFindEditBookButton(GlobVarSelenide.aDummyBookTitle);
        assertEquals("the count of Edit Book Buttons found should be 0.",  0, cantFindEditBookButton);
        
        int cantFindDeleteBookButton = NoFindHelper.cantFindDeleteBookButton(GlobVarSelenide.aDummyBookTitle);
        assertEquals("the count of Delete Book Buttons found should be 0.",  0, cantFindDeleteBookButton);
        
        int cantFindEditAuthorButton = NoFindHelper.cantFindEditAuthorButton(GlobVarSelenide.dummyAuthorFullName);
        assertEquals("the count of Edit Author Buttons found should be 0.",  0, cantFindEditAuthorButton);
        
        int cantFindDeleteAuthorButton = NoFindHelper.cantFindDeleteAuthorButton(GlobVarSelenide.dummyAuthorFullName);
        assertEquals("the count of Delete Author Buttons found should be 0.",  0, cantFindDeleteAuthorButton);
        
        LOG.log(Level.INFO, "*** Finished testInvalidAnonymousCannotFindTheseLinks() test ***");
    }
    
     @Test //This test verifies that certain links cannot be found (and thus potentially used) by users of the loaner type
    public void testInvalidLoanerCannotFindTheseLinks(){
        LOG.log(Level.INFO, "*** Starting testInvalidLoanerCannotFindTheseLinks() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String loggedInAs = UserHelper.logInAsUser(GlobVarSelenide.bDummyUserDisplayName, GlobVarSelenide.bDummyUserPassword);
        assertEquals("sign-in-message should be " + "Logged in as " + GlobVarSelenide.bDummyUserDisplayName +".", "Logged in as " + GlobVarSelenide.bDummyUserDisplayName +".", loggedInAs);
        
        int cantFindAddBook = NoFindHelper.cantFindAddBook();
        assertEquals("the count of AddBook links found should be 0.",  0, cantFindAddBook);
        
        int cantFindAddAuthor = NoFindHelper.cantFindAddAuthor();
        assertEquals("the count of AddAuthor links found should be 0.",  0, cantFindAddAuthor);
        
        int cantFindAddUser = NoFindHelper.cantFindAddUser();
        assertEquals("the count of AddUser links found should be 0.",  0, cantFindAddUser);
        
        int cantFindReturnBookButton = NoFindHelper.cantFindReturnBookButton(GlobVarSelenide.aDummyBookTitle);
        assertEquals("the count of Return Book Buttons found should be 0.",  0, cantFindReturnBookButton);
        
        int cantFindEditBookButton = NoFindHelper.cantFindEditBookButton(GlobVarSelenide.aDummyBookTitle);
        assertEquals("the count of Edit Book Buttons found should be 0.",  0, cantFindEditBookButton);
        
        int cantFindDeleteBookButton = NoFindHelper.cantFindDeleteBookButton(GlobVarSelenide.aDummyBookTitle);
        assertEquals("the count of Delete Book Buttons found should be 0.",  0, cantFindDeleteBookButton);
        
        int cantFindEditAuthorButton = NoFindHelper.cantFindEditAuthorButton(GlobVarSelenide.dummyAuthorFullName);
        assertEquals("the count of Edit Author Buttons found should be 0.",  0, cantFindEditAuthorButton);
        
        int cantFindDeleteAuthorButton = NoFindHelper.cantFindDeleteAuthorButton(GlobVarSelenide.dummyAuthorFullName);
        assertEquals("the count of Delete Author Buttons found should be 0.",  0, cantFindDeleteAuthorButton);
        
        int cantFindDeleteUserButton = NoFindHelper.cantFindDeleteUserButton();
        assertEquals("the count of Set Role Buttons found should be 0.",  0, cantFindDeleteUserButton);
        
        int cantFindSetRoleButtons = NoFindHelper.cantFindSetRoleButtons();
        assertEquals("the count of Set Role Buttons found should be 0.",  0, cantFindSetRoleButtons);
        
        LOG.log(Level.INFO, "*** Finished testInvalidLoanerCannotFindTheseLinks() test ***");
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
        
        Author verifyAuthorThroughBook = AuthorHelper.fetchAnAuthorThroughBook(GlobVarSelenide.aDummyBookTitle, "all");
        assertEquals("The authors's full name should be " +GlobVarSelenide.dummyAuthorFullName, GlobVarSelenide.dummyAuthorFullName, verifyAuthorThroughBook.getFullName());
        assertEquals("The author's country should be " +GlobVarSelenide.dummyAuthorCountry, GlobVarSelenide.dummyAuthorCountry, verifyAuthorThroughBook.getCountry());
        assertEquals("The author's biography should be " +GlobVarSelenide.dummyAuthorBio, GlobVarSelenide.dummyAuthorBio, verifyAuthorThroughBook.getBiography());
        
        Author verifyAuthorFetchedThroughBookList = AuthorHelper.fetchAnAuthorThroughBookList(GlobVarSelenide.aDummyBookTitle, GlobVarSelenide.dummyAuthorFullName, "all");
        assertEquals("The authors's full name should be " +GlobVarSelenide.dummyAuthorFullName, GlobVarSelenide.dummyAuthorFullName, verifyAuthorFetchedThroughBookList.getFullName());
        assertEquals("The author's country should be " +GlobVarSelenide.dummyAuthorCountry, GlobVarSelenide.dummyAuthorCountry, verifyAuthorFetchedThroughBookList.getCountry());
        assertEquals("The author's biography should be " +GlobVarSelenide.dummyAuthorBio, GlobVarSelenide.dummyAuthorBio, verifyAuthorFetchedThroughBookList.getBiography());
        
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
        int cantFindBorrowBookButton = NoFindHelper.cantFindBorrowBookButton(GlobVarSelenide.aDummyBookTitle);
        assertEquals("the count of Borrow Book Buttons found should be 0.",  0, cantFindBorrowBookButton);
        
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
        int cantFindBorrowBookButton = NoFindHelper.cantFindBorrowBookButton(GlobVarSelenide.aDummyBookTitle);
        assertEquals("the count of Borrow Book Buttons found should be 0.",  0, cantFindBorrowBookButton);
        
        UserHelper.goToMyProfilePage();
        assertEquals("The title of the first book in the users borrowed books list should be: " + borrowedBookTitle,  borrowedBookTitle, UserHelper.getCurrentUserFirstBorrowedBookTitle());
        UserHelper.goCurrentUserFirstBorrowedBook();
        BookHelper.returnBook();
        BookHelper.browseToBook(GlobVarSelenide.aDummyBookTitle);
        Integer afterReturn = BookHelper.fetchBook(GlobVarSelenide.aDummyBookTitle, "all").getNbrAvailable();
        assertEquals("the number after returning the book should be the same as the number before borrowing", beforeBorrowing, afterReturn);
        
        LOG.log(Level.INFO, "*** Finished testLibrarianBorrowAndReturnBook() test ***");
    }
    
    @Test //((need a way to find the ! inside the add author button))
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
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", BookHelper.getMessage());
        
        //add book title, change book publication date to an invalid entry
        BookHelper.createNewBookWithAuthor(GlobVarSelenide.bSecondDummyBookTitle, GlobVarSelenide.dummyAuthorFullName, "", "", "", GlobVarSelenide.aDummyBookTotalNbrCopies, GlobVarSelenide.aDummyBookNbrPages);
        assertEquals("the count of error-indicators, ! should be 1", 1, BookHelper.checkErrorSignInButton());
        //BookHelper.checkErrorSignInButton(); //this will cause a test error if it fails by being abscent, 
        
        //change publication date to proper format, change number of pages to an invalid (non-integer) entry
        BookHelper.invalidCreateNewBookWithAuthor(GlobVarSelenide.bSecondDummyBookTitle, GlobVarSelenide.dummyAuthorFullName, "", "", GlobVarSelenide.bSecondDummyBookPublicationDate, GlobVarSelenide.aDummyBookTotalNbrCopies.toString(), "");
        BookHelper.checkErrorSign(); //this will cause a test error if it fails by being abscent, 
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", BookHelper.getMessage());
        
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
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", AuthorHelper.getMessage());
        
        //the following tries to create an author while providing invalid, empty String values for lastName
        AuthorHelper.createNewAuthor(GlobVarSelenide.secondDummyAuthorFirstName, "", GlobVarSelenide.dummyAuthorCountry, GlobVarSelenide.dummyAuthorBio);
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", AuthorHelper.getMessage());
        
        //the following tries to create an author while providing invalid, empty String values for country 
        AuthorHelper.createNewAuthor(GlobVarSelenide.secondDummyAuthorFirstName, GlobVarSelenide.dummyAuthorLastName, "", GlobVarSelenide.dummyAuthorBio);
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", AuthorHelper.getMessage());
        
        //the following tries to create an author while providing invalid, empty String values for biography
        AuthorHelper.createNewAuthor(GlobVarSelenide.secondDummyAuthorFirstName, GlobVarSelenide.secondDummyAuthorLastName, GlobVarSelenide.secondDummyAuthorCountry, "");
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", AuthorHelper.getMessage());
        
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
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", UserHelper.getMessage());
        
        //the following tries to create a user while providing invalid, empty String values for password 
        UserHelper.createNewMinimal(GlobVarSelenide.bDummyUserDisplayName, "");
        assertEquals("Error message should be: Invalid data, please try again.",  "Invalid data, please try again.", UserHelper.getMessage());
        
        LOG.log(Level.INFO, "*** Finished testInvalidDataEntryWhenLibrarianCreatesUser() test ***");
    }
    
    @Test 
    public void testLibrarianCreatesAnAuthorAndAddsTwoAuthorsToBook(){
        LOG.log(Level.INFO, "*** Starting testLibrarianCreatesAnAuthorAndAddsTwoAuthorsToBook() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String loggedInAs = UserHelper.logInAsUser(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword);
        assertEquals("sign-in-message should be " + "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", loggedInAs);
        
        AuthorHelper.createNewAuthor(GlobVarSelenide.secondDummyAuthorFirstName, GlobVarSelenide.secondDummyAuthorLastName, GlobVarSelenide.secondDummyAuthorCountry, GlobVarSelenide.secondDummyAuthorBio);
        assertEquals("Error message should be: Added author:",  "Added author:", AuthorHelper.getMessage());
        
        BookHelper.createNewBookWithTwoAuthors(GlobVarSelenide.bSecondDummyBookTitle, GlobVarSelenide.dummyAuthorFullName, GlobVarSelenide.secondDummyAuthorFullName, "", "", GlobVarSelenide.bSecondDummyBookPublicationDate, GlobVarSelenide.aDummyBookTotalNbrCopies, GlobVarSelenide.aDummyBookNbrPages);
        assertEquals("Error message should be: Added book:",  "Added book:", BookHelper.getMessage());
        
        BookHelper.unEditBookAuthor(GlobVarSelenide.bSecondDummyBookTitle, GlobVarSelenide.secondDummyAuthorFullName);
        
        AuthorHelper.removeAuthor(GlobVarSelenide.secondDummyAuthorFullName);
        
        
        BookHelper.removeBook(GlobVarSelenide.bSecondDummyBookTitle);
        
        LOG.log(Level.INFO, "*** Finished testLibrarianCreatesAnAuthorAndAddsTwoAuthorsToBook() test ***");
    }
    
    @Test 
    public void testLibrarianEditsABookAndAuthor(){
        LOG.log(Level.INFO, "*** Starting testLibrarianEditsABookAndAuthor() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String loggedInAs = UserHelper.logInAsUser(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword);
        assertEquals("sign-in-message should be " + "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", loggedInAs);
        
        AuthorHelper.createNewAuthor(GlobVarSelenide.secondDummyAuthorFirstName, GlobVarSelenide.secondDummyAuthorLastName, GlobVarSelenide.secondDummyAuthorCountry, GlobVarSelenide.secondDummyAuthorBio);
        assertEquals("message should be: Added author:",  "Added author:", AuthorHelper.getMessage());
        
        BookHelper.createNewBookWithAuthor(GlobVarSelenide.bSecondDummyBookTitle, GlobVarSelenide.secondDummyAuthorFullName, GlobVarSelenide.bSecondDummyBookDescription, GlobVarSelenide.bSecondDummyBookIsbn, GlobVarSelenide.bSecondDummyBookPublicationDate, GlobVarSelenide.aDummyBookTotalNbrCopies, GlobVarSelenide.aDummyBookNbrPages);
        assertEquals("message should be: Added book:",  "Added book:", BookHelper.getMessage());
        
        
        AuthorHelper.editAuthor(GlobVarSelenide.secondDummyAuthorFullName, GlobVarSelenide.thirdDummyAuthorFirstName, GlobVarSelenide.thirdDummyAuthorLastName, GlobVarSelenide.thirdDummyAuthorCountry, GlobVarSelenide.thirdDummyAuthorBio); 
        String changedAuthor = AuthorHelper.getMessage();
        assertEquals("message should be: Saved changes to author:",  "Saved changes to author:", changedAuthor);
        if(!(changedAuthor.equals("Saved changes to author:"))) AuthorHelper.removeAuthor(GlobVarSelenide.secondDummyAuthorFullName);
        
        BookHelper.editBook(GlobVarSelenide.bSecondDummyBookTitle, GlobVarSelenide.cDummyBookTitle, GlobVarSelenide.cDummyBookDescription, GlobVarSelenide.cDummyBookIsbn, GlobVarSelenide.cDummyBookPublicationDate, GlobVarSelenide.cDummyBookTotalNbrCopies, GlobVarSelenide.cDummyBookNbrPages);
        String changedBook = BookHelper.getMessage();
        assertEquals("message should be: Saved changes to book:",  "Saved changes to book:", changedBook);
        if(!(changedBook.equals("Saved changes to book:"))) BookHelper.removeBook(GlobVarSelenide.bSecondDummyBookTitle);
        
        Book verifyBook = BookHelper.fetchBook(GlobVarSelenide.cDummyBookTitle, "all");
        assertEquals("The book's title should be " +GlobVarSelenide.cDummyBookTitle, GlobVarSelenide.cDummyBookTitle, verifyBook.getTitle());
        assertEquals("The book's author's full name should be " +GlobVarSelenide.thirdDummyAuthorFullName, GlobVarSelenide.thirdDummyAuthorFullName, verifyBook.getAuthor());
        assertEquals("The book's description should be " +GlobVarSelenide.cDummyBookDescription, GlobVarSelenide.cDummyBookDescription, verifyBook.getDescription());
        assertEquals("The book's isbn should be " +GlobVarSelenide.cDummyBookIsbn, GlobVarSelenide.cDummyBookIsbn, verifyBook.getIsbn());
        assertEquals("The book's publication date should be " +GlobVarSelenide.cDummyBookPublicationDate, GlobVarSelenide.cDummyBookPublicationDate, verifyBook.getDatePublished());
        assertEquals("The book's total number of available copies should be " +GlobVarSelenide.cDummyBookTotalNbrCopies, GlobVarSelenide.cDummyBookTotalNbrCopies, verifyBook.getNbrAvailable());
        
        Author verifyAuthor = AuthorHelper.fetchAuthor(GlobVarSelenide.thirdDummyAuthorFullName, "all");
        assertEquals("The authors's full name should be " +GlobVarSelenide.thirdDummyAuthorFullName, GlobVarSelenide.thirdDummyAuthorFullName, verifyAuthor.getFullName());
        assertEquals("The author's country should be " +GlobVarSelenide.thirdDummyAuthorCountry, GlobVarSelenide.thirdDummyAuthorCountry, verifyAuthor.getCountry());
        assertEquals("The author's biography should be " +GlobVarSelenide.thirdDummyAuthorBio, GlobVarSelenide.thirdDummyAuthorBio, verifyAuthor.getBiography());
        
        BookHelper.removeBook(GlobVarSelenide.cDummyBookTitle);
        
        AuthorHelper.removeAuthor(GlobVarSelenide.thirdDummyAuthorFullName);
        
        LOG.log(Level.INFO, "*** Finished testLibrarianEditsABookAndAuthor() test ***");
    }
    
    @Test 
    public void testLibrariancreatesTheSameBookBookAndAuthorTwice(){
        LOG.log(Level.INFO, "*** Starting testLibrariancreatesTheSameBookBookAndAuthorTwice() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String loggedInAs = UserHelper.logInAsUser(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword);
        assertEquals("sign-in-message should be " + "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", loggedInAs);
        
        AuthorHelper.createNewAuthor(GlobVarSelenide.secondDummyAuthorFirstName, GlobVarSelenide.secondDummyAuthorLastName, GlobVarSelenide.secondDummyAuthorCountry, GlobVarSelenide.secondDummyAuthorBio);
        assertEquals("message should be: Added author:", "Added author:", AuthorHelper.getMessage());
        //creating the same author twice, deleting one of them if this works
        AuthorHelper.createNewAuthor(GlobVarSelenide.secondDummyAuthorFirstName, GlobVarSelenide.secondDummyAuthorLastName, GlobVarSelenide.secondDummyAuthorCountry, GlobVarSelenide.secondDummyAuthorBio);
        String madeAnotherAuthor = AuthorHelper.getMessage();
        assertEquals("message should be: Added author:", "Added author:", madeAnotherAuthor);
        if(madeAnotherAuthor.equals("Added author:")) AuthorHelper.removeAuthor(GlobVarSelenide.secondDummyAuthorFullName);
        
        BookHelper.createNewBook(GlobVarSelenide.bSecondDummyBookTitle, GlobVarSelenide.bSecondDummyBookDescription, GlobVarSelenide.bSecondDummyBookIsbn, GlobVarSelenide.bSecondDummyBookPublicationDate, GlobVarSelenide.aDummyBookTotalNbrCopies, GlobVarSelenide.aDummyBookNbrPages);
        assertEquals("message should be: Added book:", "Added book:", BookHelper.getMessage());
        
        BookHelper.createNewBook(GlobVarSelenide.bSecondDummyBookTitle, GlobVarSelenide.bSecondDummyBookDescription, GlobVarSelenide.bSecondDummyBookIsbn, GlobVarSelenide.bSecondDummyBookPublicationDate, GlobVarSelenide.aDummyBookTotalNbrCopies, GlobVarSelenide.aDummyBookNbrPages);
        String madeAnotherBook = BookHelper.getMessage();
        assertEquals("message should be: Added book:", "Added book:", madeAnotherBook);
        if(madeAnotherBook.equals("Added book:")) BookHelper.removeBook(GlobVarSelenide.bSecondDummyBookTitle);
        
        
        BookHelper.removeBook(GlobVarSelenide.bSecondDummyBookTitle);
        
        AuthorHelper.removeAuthor(GlobVarSelenide.secondDummyAuthorFullName);
        
        LOG.log(Level.INFO, "*** Finished testLibrariancreatesTheSameBookBookAndAuthorTwice() test ***");
    }
    
    @Test 
    public void testInvalidLibrarianDuplicateLibrarian(){
        LOG.log(Level.INFO, "*** Starting testInvalidLibrarianDuplicateLibrarian() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String loggedInAs = UserHelper.logInAsUser(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword);
        assertEquals("sign-in-message should be " + "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", loggedInAs);
        
        UserHelper.createNewLibrarianUserAsLibrarian(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword, GlobVarSelenide.aDummyUserFirstName, GlobVarSelenide.aDummyUserLastName, GlobVarSelenide.aDummyUserPhone, GlobVarSelenide.aDummyUserEmail);
        String madeAnotherUser = UserHelper.getMessage();
        String expectedMessage = "Unable to add user: Unable to create entity: Bad Request, User with same display name already exists.";
        assertEquals("message should be: " + expectedMessage, expectedMessage, madeAnotherUser);
        
        LOG.log(Level.INFO, "*** Finished testInvalidLibrarianDuplicateLibrarian() test ***");
    }
    
    @Test 
    public void testLibrarianEditsSelf(){
        LOG.log(Level.INFO, "*** Starting testInvalidLibrarianDuplicateLibrarian() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        String loggedInAs = UserHelper.logInAsUser(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword);
        assertEquals("sign-in-message should be " + "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", "Logged in as " + GlobVarSelenide.aDummyUserDisplayName +".", loggedInAs);
        
        
        UserHelper.editCurrentUserProfile(GlobVarSelenide.cDummyUserDisplayName, GlobVarSelenide.cDummyUserPassword, GlobVarSelenide.cDummyUserFirstName, GlobVarSelenide.cDummyUserLastName, GlobVarSelenide.cDummyUserPhone, GlobVarSelenide.cDummyUserEmail);
        //the 3 commented lines below do not operate as intended because for some reason the message line only shows after you update the user several times in a row while on the same page or if you do an update with an invalid message, then do a valid one. Checking the values for the user as is done further below should be sufficient to verify that the edit was performed.
        //String editedUser = UserHelper.getMessage();
        //String expectedMessage = "Updated user: ";
        //assertEquals("message should be: " + expectedMessage, expectedMessage, editedUser);
        
        // this part verifies that the user has been updated
        User verifyLoaner = UserHelper.fetchUser(GlobVarSelenide.cDummyUserDisplayName, GlobVarSelenide.cDummyUserPassword, "all");
        assertEquals("The user's displayname should be " +GlobVarSelenide.cDummyUserDisplayName, GlobVarSelenide.cDummyUserDisplayName, verifyLoaner.getDisplayName());
        //cannot verify passwords because fetching a password from the interface returns null, aka **** 
        assertEquals("The user's firstName should be " +GlobVarSelenide.cDummyUserFirstName, GlobVarSelenide.cDummyUserFirstName, verifyLoaner.getFirstName());
        assertEquals("The user's lastName should be " +GlobVarSelenide.cDummyUserLastName, GlobVarSelenide.cDummyUserLastName, verifyLoaner.getLastName());
        assertEquals("The user's phone number should be " +GlobVarSelenide.cDummyUserPhone, GlobVarSelenide.cDummyUserPhone, verifyLoaner.getPhone());
        assertEquals("The user's email should be " +GlobVarSelenide.cDummyUserEmail, GlobVarSelenide.cDummyUserEmail, verifyLoaner.getEmail());
        
        // this part verifies that we cannot update a user with a displayname that is already in the system
        UserHelper.editCurrentUserProfile(GlobVarSelenide.bDummyUserDisplayName, GlobVarSelenide.bDummyUserPassword, GlobVarSelenide.bDummyUserFirstName, GlobVarSelenide.bDummyUserLastName, GlobVarSelenide.bDummyUserPhone, GlobVarSelenide.bDummyUserEmail);
        String invalidEditedUser = UserHelper.getMessage();
        String InvalidexpectedMessage = "Unable to save changes to user: Unable to update entity: Bad Request, Another user with same display name already exists.";
        assertEquals("message should be: " + InvalidexpectedMessage, InvalidexpectedMessage, invalidEditedUser);
        
        UserHelper.editCurrentUserProfile(GlobVarSelenide.aDummyUserDisplayName, GlobVarSelenide.aDummyUserPassword, GlobVarSelenide.aDummyUserFirstName, GlobVarSelenide.aDummyUserLastName, GlobVarSelenide.aDummyUserPhone, GlobVarSelenide.aDummyUserEmail);
        
        
        
        LOG.log(Level.INFO, "*** Finished testInvalidLibrarianDuplicateLibrarian() test ***");
    }
    
    
    
}
