/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

/**
 *
 * @author testautomatisering
 */
public class GlobVar {
    //this class stores all static variables for relatively easy editing of test data and base url
    public static final String BASE_URL = "http://localhost:8080/librarytest-rest/";
    
    public static Integer dummyBookId;
    
    public static String dummyBookDescription = "dummyTestDescription";
    public static String dummyBookIsbn = "dummyIsbn";
    public static Integer dummyBookNbrPages = 7357;
    public static String dummyBookPublicationDate = "dummyBookPublicationDate";
    public static String dummyBookTitle = "dummyTestTitle";
    public static Integer dummyBookTotalNbrCopies = 5;
    
    public static Integer dummyAuthorId;
    
    public static String dummyAuthorBio = "dummyAuthorBio";
    public static String dummyAuthorCountry = "dummyAuthorCountry";
    public static String dummyAuthorFirstName = "1971-12-12";
    public static String dummyAuthorLastName = "dummyAuthorLastName";
    
    public static String secondDummyBookDescription = "anotherDummyTestDescription";
    public static String secondDummyBookIsbn = "anotherDummyIsbn";
    public static Integer secondDummyBookNbrPages = 123123;
    public static String secondDummyBookPublicationDate = "1982-04-22";
    public static String secondDummyBookTitle = "anotherDummyTestDescription";
    public static Integer secondDummyBookTotalNbrCopies = 42;
    
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
}
