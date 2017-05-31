/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.model.User;

/**
 *
 * @author testautomatisering
 */
public class GlobVarSelenide {
    //this class stores all static variables for relatively easy editing of test data and base url
    public static final String BASE_URL = "http://localhost:8080/librarytest-rest/";
    
    public static Integer aDummyBookId;
    
    public static String aDummyBookDescription = "dummyTestDescription";
    public static String aDummyBookIsbn = "dummyIsbn";
    public static Integer aDummyBookNbrPages = 7357;
    public static String aDummyBookPublicationDate = "1982-04-22";
    public static String aDummyBookTitle = "dummyTestTitle";
    public static Integer aDummyBookTotalNbrCopies = 4;

    public static Integer bSecondDummyBookId;
    
    public static String bSecondDummyBookDescription = "anotherDummyTestDescription";
    public static String bSecondDummyBookIsbn = "anotherDummyIsbn";
    public static Integer bSecondDummyBookNbrPages = 123123;
    public static String bSecondDummyBookPublicationDate = "1982-04-20";
    public static String bSecondDummyBookTitle = "anotherDummyTestDescription";
    public static Integer bSecondDummyBookTotalNbrCopies = 42;
    
    public static String cDummyBookDescription = "yetAnotherDummyTestDescription";
    public static String cDummyBookIsbn = "yetAnotherDummyIsbn";
    public static Integer cDummyBookNbrPages = 567367;
    public static String cDummyBookPublicationDate = "1984-08-12";
    public static String cDummyBookTitle = "anotherDummyTestDescription";
    public static Integer cDummyBookTotalNbrCopies = 34;
    
    
    public static Integer dummyAuthorId;
    public static String dummyAuthorFullName = "dummyAuthorFirstName dummyAuthorLastName";
    public static String dummyAuthorBio = "dummyAuthorBio";
    public static String dummyAuthorCountry = "dummyAuthorCountry";
    public static String dummyAuthorFirstName = "dummyAuthorFirstName";
    public static String dummyAuthorLastName = "dummyAuthorLastName";
    
    public static String secondDummyAuthorFullName = "anotherDummyAuthorFirstName anotherDummyAuthorLastName";
    public static String secondDummyAuthorBio = "anotherDummyAuthorBio";
    public static String secondDummyAuthorCountry = "anotherDummyAuthorCountry";
    public static String secondDummyAuthorFirstName = "anotherDummyAuthorFirstName";
    public static String secondDummyAuthorLastName = "anotherDummyAuthorLastName";
    
    public static Integer thirdDummyAuthorId;
    public static String thirdDummyAuthorFullName = "andYetAThirdDummyAuthorFirstName andYetAThirdDummyAuthorLastName";
    public static String thirdDummyAuthorBio = "andYetAThirdDummyAuthorBio";
    public static String thirdDummyAuthorCountry = "andYetAThirdDummyAuthorCountry";
    public static String thirdDummyAuthorFirstName = "andYetAThirdDummyAuthorFirstName";
    public static String thirdDummyAuthorLastName = "andYetAThirdDummyAuthorLastName";
            
    public static Integer aDummyUserId;
    
    public static String aDummyUserDisplayName = "aDummyUserDisplayName";
    public static String aDummyUserFirstName = "aDummyUserFirstName";
    public static String aDummyUserLastName = "aDummyUserLastName";
    public static String aDummyUserPassword = "aDummyUserPassword";
    public static String aDummyUserPhone = "aDummyUserPhone";
    public static String aDummyUserEmail = "aDummyUserEmail";
    public static String aDummyUserRole = "LIBRARIAN";
    
    public static Integer bDummyUserId;
    
    public static String bDummyUserDisplayName = "secondDummyUserDisplayName";
    public static String bDummyUserFirstName = "secondDummyUserFirstName";
    public static String bDummyUserLastName = "secondDummyUserLastName";
    public static String bDummyUserPassword = "secondDummyUserPassword";
    public static String bDummyUserPhone = "secondDummyUserPhone";
    public static String bDummyUserEmail = "secondDummyUserEmail";
    public static String bDummyUserRole = "LOANER";
    
    public static String cDummyUserDisplayName = "thirdDummyUserDisplayName";
    public static String cDummyUserFirstName = "thirdDummyUserFirstName";
    public static String cDummyUserLastName = "thirdDummyUserLastName";
    public static String cDummyUserPassword = "thirdDummyUserPassword";
    public static String cDummyUserPhone = "thirdDummyUserPhone";
    public static String cDummyUserEmail = "thirdDummyUserEmail";
    public static String cDummyUserRole = "LIBRARIAN";
    
    
    public static Integer aDummyLoanId;
    
    public static Book aDummyLoanBook;
    public static String aDummyDateBorrowed = "2001-04-22";
    public static String aDummyDateDue = "2002-04-22";
    public static User aDummyLoanUser;
    
    public static Integer bDummyLoanId;
    
    public static Book bDummyLoanBook;
    public static String bDummyDateBorrowed = "2011-12-12";
    public static String bDummyDateDue = "2014-12-12";
    public static User bDummyLoanUser;
    
    public static Integer cDummyLoanId;
    public static String cDummyDateBorrowed = "2005-06-06";
    public static String cDummyDateDue = "2005-06-07";
}
