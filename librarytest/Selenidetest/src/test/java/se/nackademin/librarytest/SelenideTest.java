package se.nackademin.librarytest;

import static com.codeborne.selenide.Selenide.*;
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
import se.nackademin.librarytest.helpers.NavigationHelper;
import se.nackademin.librarytest.helpers.PreparationHelper;
import se.nackademin.librarytest.helpers.UserHelper;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.model.User;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.MyProfilePage;


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
        
        UserHelper.createNewMinimalUserAsNonLibrarian(uuid, uuid);
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
        
        UserHelper.createNewMinimalUserAsNonLibrarian(uuid, uuid);
        String loggedInAs = UserHelper.logInAsUser(uuid, uuid);
        assertEquals("sign-in-message should be " + "Logged in as " + uuid +".", "Logged in as " + uuid +".", loggedInAs);
        
        UserHelper.editCurrentUserProfile(uuid, uuid, uuid, uuid, uuid, uuid);
        
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.navigateToMyProfile();
        assertEquals("user email should be: " + uuid,  uuid, myProfilePage.getUserEmail());
        
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
        
        BookPage bookPage = page(BookPage.class);
        Integer beforeBorrowing = BookHelper.borrowBook(GlobVarSelenide.aDummyBookTitle);
        String borrowedBookTitle = bookPage.getTitle();
        Integer afterBorrowingPlusOne = bookPage.getNbrOfCopiesAvailable() +1;
        assertEquals("the number before borrowing should be the same as the number after borrowing + one", beforeBorrowing, afterBorrowingPlusOne);
        
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.navigateToMyProfile();
        assertEquals("The title of the book in the users borrowed books list should be: " + borrowedBookTitle,  borrowedBookTitle, myProfilePage.getFirstBorrowedBookTitle());
        myProfilePage.clickFirstBorrowedBookTitle();
        bookPage.clickReturnBookButton();
        bookPage.navigateToBook(GlobVarSelenide.aDummyBookTitle);
        Integer afterReturn = bookPage.getNbrOfCopiesAvailable();
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
        
        BookPage bookPage = page(BookPage.class);
        Integer beforeBorrowing = BookHelper.borrowBook(GlobVarSelenide.aDummyBookTitle);
        String borrowedBookTitle = bookPage.getTitle();
        Integer afterBorrowingPlusOne = bookPage.getNbrOfCopiesAvailable() +1;
        assertEquals("the number before borrowing should be the same as the number after borrowing + one. ", beforeBorrowing, afterBorrowingPlusOne);
        
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.navigateToMyProfile();
        assertEquals("The title of the book in the users borrowed books list should be: " + borrowedBookTitle,  borrowedBookTitle, myProfilePage.getFirstBorrowedBookTitle());
        myProfilePage.clickFirstBorrowedBookTitle();
        bookPage.clickReturnBookButton();
        bookPage.navigateToBook(GlobVarSelenide.aDummyBookTitle);
        Integer afterReturn = bookPage.getNbrOfCopiesAvailable();
        assertEquals("the number after returning the book should be the same as the number before borrowing", beforeBorrowing, afterReturn);
        
        LOG.log(Level.INFO, "*** Finished testLibrarianBorrowAndReturnBook() test ***");
    }
    
    
    
    //@Test // testcase 1
    public void testCreateNewAuthor(){
        LOG.log(Level.INFO, "*** Starting testCreateNewAuthor() test ***");
        UserHelper.logInAsAdmin();
        String uuid = UUID.randomUUID().toString().substring(0, 12);
        String Authorname = uuid + " " + uuid;
        AuthorHelper.createNewAuthor(uuid, uuid, uuid, uuid);
        Author author = AuthorHelper.fetchAuthor(Authorname, "all");
        assertEquals("Author's name should be: " + Authorname,  Authorname, author.getFullName());
        assertEquals("Author's home country should be: " + uuid,  uuid, author.getCountry());
        assertEquals("Author's biography should be: " + uuid,  uuid, author.getBiography());
        
        //this part removes the new author to keep things tidy, comment out if you want to manually verify that the author was created
        AuthorHelper.removeAuthor(Authorname);
        LOG.log(Level.INFO, "*** Finished testCreateNewAuthor() test ***");
    }
    
    //@Test //testcase 2
    public void testChangeEmailOfUser(){
        LOG.log(Level.INFO, "*** Starting testChangeEmailOfUser() test ***");
        String uuid = UUID.randomUUID().toString().substring(0, 12);
        
        UserHelper.createNewMinimalUserAsNonLibrarian(uuid, uuid);
        UserHelper.logInAsUser(uuid, uuid);
        UserHelper.editCurrentUserProfileEmail(uuid);
        
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.navigateToMyProfile();
        assertEquals("user email should be: " + uuid,  uuid, myProfilePage.getUserEmail());
        
        //removing users from the system for cleanup purposes is not currently supported with the user interface as only admins can remove users, there is no way for admins to view or alter other users thus the only user the admin account can remove is itself!
        //if something goes wrong, use the test log to find the randomised uuid generated for name and password to sign in and manually verify what the test has and has not done to the new user or if one was not created after all, etc
        
        LOG.log(Level.INFO, "*** Finished testChangeEmailOfUser() test ***");
    }
    
    //@Test // testcase 3
    public void testEditBookPublishingDate(){
        LOG.log(Level.INFO, "*** Starting testEditBookPublishingDate() test ***");
        UserHelper.logInAsAdmin();
        
        String randomPublishedDate = BookHelper.makeRandomPublishDate();
        BookHelper.editBookDatePublished("Good Omens", randomPublishedDate);
        Book book = BookHelper.fetchBook("Good Omens", "date");
        assertEquals("The Book's publishing date should be " +randomPublishedDate, randomPublishedDate, book.getDatePublished());
        
        //this row simply resets Good Omens published date to its original date for cleanlyness purposes. Comment out if you wish to manually verify that the date was changed
        BookHelper.editBookDatePublished("Good Omens", "1990-05-01");
        
        LOG.log(Level.INFO, "*** Finished testEditBookPublishingDate() test ***");
    }
    
    //@Test //testcase 4 
    public void testBorrowABookAsUser(){
        LOG.log(Level.INFO, "*** Starting testBorrowABookAsUser() test ***");
        String uuid = UUID.randomUUID().toString().substring(0, 12);
        
        UserHelper.createNewMinimalUserAsNonLibrarian(uuid, uuid);
        UserHelper.logInAsUser(uuid, uuid);
        
        BookPage bookPage = page(BookPage.class);
        Integer beforeBorrowing = BookHelper.borrowBook("");
        String borrowedBookTitle = bookPage.getTitle();
        Integer afterBorrowing = bookPage.getNbrOfCopiesAvailable() + 1;
        assertEquals("", beforeBorrowing, afterBorrowing);
        
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.navigateToMyProfile();
        assertEquals("The title of the book in the users borrowed books list should be: " + borrowedBookTitle,  borrowedBookTitle, myProfilePage.getFirstBorrowedBookTitle());
        myProfilePage.clickFirstBorrowedBookTitle();
        bookPage.clickReturnBookButton();
        
        //removing users from the system for cleanup purposes is not currently supported with the user interface as only admins can remove users, there is no way for admins to view or alter other users thus the only user the admin account can remove is itself!
        //if something goes wrong, use the test log to find the randomised uuid generated for name and password to sign in and manually verify what the test has and has not done to the new user or if one was not created after all, etc
        
        LOG.log(Level.INFO, "*** Finished testBorrowABookAsUser() test ***");
    }
    
}
