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
import se.nackademin.librarytest.helpers.GlobVar;
import se.nackademin.librarytest.helpers.NavigationHelper;
import se.nackademin.librarytest.helpers.PreparationHelper;
import se.nackademin.librarytest.helpers.UserHelper;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.MyProfilePage;


public class SelenideTest extends TestBase{
    private static final Logger LOG =  Logger.getLogger(SelenideTest.class.getName());

    public SelenideTest(){
    
    }
    
    @BeforeClass //this method creates a dummy book, dummy author and adds the author to the book to be used during test executions
    public static void MakeDummyUserAuthorBookAndAdmin(){
        System.setProperty("webdriver.chrome.driver", "/home/testautomatisering/seleniumdrivers/chromedriver");
        System.setProperty("selenide.browser", "Chrome");
        open("http://localhost:8080/librarytest");
        
        LOG.log(Level.INFO, "*** Starting Preparing library dummies needed for tests ***");
        PreparationHelper.MakeDummyUserAuthorBookAndAdmin();
        
        Book verifyBook = BookHelper.fetchBook(GlobVar.aDummyBookTitle, "all");
        assertEquals("The Book's publishing date should be " +GlobVar.aDummyBookTitle, GlobVar.aDummyBookTitle, verifyBook.getTitle());
        //assertEquals("The Book's publishing date should be " +GlobVar.dummyAuthorFullName, GlobVar.dummyAuthorFullName, verifyBook.getAuthor());
        assertEquals("The Book's publishing date should be " +GlobVar.aDummyBookDescription, GlobVar.aDummyBookDescription, verifyBook.getDescription());
        assertEquals("The Book's publishing date should be " +GlobVar.aDummyBookIsbn, GlobVar.aDummyBookIsbn, verifyBook.getIsbn());
        assertEquals("The Book's publishing date should be " +GlobVar.aDummyBookTotalNbrCopies, GlobVar.aDummyBookTotalNbrCopies, verifyBook.getNbrAvailable());
        
        Author verifyAuthor = AuthorHelper.fetchAuthor(GlobVar.dummyAuthorFullName, "all");
        assertEquals("The Book's publishing date should be " +GlobVar.dummyAuthorFullName, GlobVar.dummyAuthorFullName, verifyAuthor.getFullName());
        assertEquals("The Book's publishing date should be " +GlobVar.dummyAuthorCountry, GlobVar.dummyAuthorCountry, verifyAuthor.getCountry());
        assertEquals("The Book's publishing date should be " +GlobVar.dummyAuthorBio, GlobVar.dummyAuthorBio, verifyAuthor.getBiography());
        
        LOG.log(Level.INFO, "*** Finished preparing library dummies needed for tests ***");
    }
    
    @AfterClass //this method removes the dummies created by the previous method
    public static void RemoveDummyAuthorBookAndAdmin(){
        LOG.log(Level.INFO, "*** Starting to remove library dummies no longer needed ***");
        PreparationHelper.RemoveDummyAuthorBookAndAdmin();
        LOG.log(Level.INFO, "*** Finished removing library dummies no longer needed ***");
    }
    
    @Test
    public void testAnonymousSiteBrowse(){
        LOG.log(Level.INFO, "*** Starting testAnonymousSiteBrowse() test ***");
        UserHelper.logOut();
        String signOutMessage = UserHelper.logOut();
        assertEquals("Signout message should be: Not signed in.",  "Not signed in.", signOutMessage);
        
        Book verifyBook = BookHelper.fetchBook(GlobVar.aDummyBookTitle, "all");
        assertEquals("The Book's publishing date should be " +GlobVar.aDummyBookTitle, GlobVar.aDummyBookTitle, verifyBook.getTitle());
        //assertEquals("The Book's publishing date should be " +GlobVar.dummyAuthorFullName, GlobVar.dummyAuthorFullName, verifyBook.getAuthor());
        assertEquals("The Book's publishing date should be " +GlobVar.aDummyBookDescription, GlobVar.aDummyBookDescription, verifyBook.getDescription());
        assertEquals("The Book's publishing date should be " +GlobVar.aDummyBookIsbn, GlobVar.aDummyBookIsbn, verifyBook.getIsbn());
        assertEquals("The Book's publishing date should be " +GlobVar.aDummyBookTotalNbrCopies, GlobVar.aDummyBookTotalNbrCopies, verifyBook.getNbrAvailable());
        
        Author verifyAuthor = AuthorHelper.fetchAuthor(GlobVar.dummyAuthorFullName, "all");
        assertEquals("The Book's publishing date should be " +GlobVar.dummyAuthorFullName, GlobVar.dummyAuthorFullName, verifyAuthor.getFullName());
        assertEquals("The Book's publishing date should be " +GlobVar.dummyAuthorCountry, GlobVar.dummyAuthorCountry, verifyAuthor.getCountry());
        assertEquals("The Book's publishing date should be " +GlobVar.dummyAuthorBio, GlobVar.dummyAuthorBio, verifyAuthor.getBiography());
        
        LOG.log(Level.INFO, "*** Finished testCreateNewAuthor() test ***");
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
        
        UserHelper.createNewUser(uuid, uuid);
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
        
        UserHelper.createNewUser(uuid, uuid);
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
