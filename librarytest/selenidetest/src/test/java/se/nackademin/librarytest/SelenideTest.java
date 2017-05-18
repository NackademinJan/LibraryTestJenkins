package se.nackademin.librarytest;

import static com.codeborne.selenide.Selenide.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import se.nackademin.librarytest.helpers.AuthorHelper;
import se.nackademin.librarytest.helpers.BookHelper;
import se.nackademin.librarytest.helpers.UserHelper;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.MyProfilePage;


public class SelenideTest extends TestBase{
    private static final Logger LOG =  Logger.getLogger(SelenideTest.class.getName());

    public SelenideTest(){
    
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
        assertEquals("username should be: " + uuid,  uuid, myProfilePage.getUserEmail());
        
        //removing users from the system for cleanup purposes is not currently supported with the user interface as only admins can remove users, there is no way for admins to view or alter other users thus the only user the admin account can remove is itself!
        //if something goes wrong, use the test log to find the randomised uuid generated for name and password to sign in and manually verify what the test has and has not done to the new user or if one was not created after all, etc
        
        LOG.log(Level.INFO, "*** Finished testChangeEmailOfUser() test ***");
    }
    
    @Test // testcase 3
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
