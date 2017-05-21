/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import se.nackademin.rest.test.model.Book;
import se.nackademin.rest.test.model.User;

/**
 *
 * @author testautomatisering
 */
public class GlobVar {
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
    public static String bSecondDummyBookPublicationDate = "1982-04-22";
    public static String bSecondDummyBookTitle = "anotherDummyTestDescription";
    public static Integer bSecondDummyBookTotalNbrCopies = 42;
    
    public static Integer dummyAuthorId;
    
    public static String dummyAuthorBio = "dummyAuthorBio";
    public static String dummyAuthorCountry = "dummyAuthorCountry";
    public static String dummyAuthorFirstName = "dummyAuthorFirstName";
    public static String dummyAuthorLastName = "dummyAuthorLastName";
    
    public static String secondDummyAuthorBio = "anotherDummyAuthorBio";
    public static String secondDummyAuthorCountry = "anotherDummyAuthorCountry";
    public static String secondDummyAuthorFirstName = "anotherDummyAuthorFirstName";
    public static String secondDummyAuthorLastName = "anotherDummyAuthorLastName";
    
    public static Integer thirdDummyAuthorId;
    
    public static String thirdDummyAuthorBio = "andYetAThirdDummyAuthorBio";
    public static String thirdDummyAuthorCountry = "andYetAThirdDummyAuthorCountry";
    public static String thirdDummyAuthorFirstName = "andYetAThirdDummyAuthorFirstName";
    public static String thirdDummyAuthorLastName = "andYetAThirdDummyAuthorLastName";
            
    public static Integer aDummyUserId;
    
    public static String aDummyUserDisplayName = "aDummyUserDisplayName";
    public static String aDummyUserEmail = "aDummyUserEmail";
    public static String aDummyUserFirstName = "aDummyUserFirstName";
    public static String aDummyUserLastName = "aDummyUserLastName";
    public static String aDummyUserPassword = "aDummyUserPassword";
    public static String aDummyUserPhone = "aDummyUserPhone";
    public static String aDummyUserRole = "LOANER";
    
    public static Integer bDummyUserId;
    
    public static String bDummyUserDisplayName = "anotherDummyUserDisplayName";
    public static String bDummyUserEmail = "anotherDummyUserEmail";
    public static String bDummyUserFirstName = "anotherDummyUserFirstName";
    public static String bDummyUserLastName = "anotherDummyUserLastName";
    public static String bDummyUserPassword = "anotherDummyUserPassword";
    public static String bDummyUserPhone = "anotherDummyUserPhone";
    public static String bDummyUserRole = "LIBRARIAN";
    
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
