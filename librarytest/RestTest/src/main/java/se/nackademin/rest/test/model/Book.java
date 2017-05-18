    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test.model;

/**
 *
 * @author testautomatisering
 */
public class Book {
    private Integer id;
    private Object author;
    private String description;
    private String isbn;
    private Integer nbrPages;
    private String publicationDate;
    private String title;
    private Integer totalNbrCopies;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the nbrPages
     */
    public Integer getNbrPages() {
        return nbrPages;
    }

    /**
     * @param nbrPages the nbrPages to set
     */
    public void setNbrPages(Integer nbrPages) {
        this.nbrPages = nbrPages;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the author
     */
    public Object getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Object author) {
        this.author = author;
    }

    /**
     * @return the totalNbrCopies
     */
    public Integer getTotalNbrCopies() {
        return totalNbrCopies;
    }

    /**
     * @param totalNbrCopies the totalNbrCopies to set
     */
    public void setTotalNbrCopies(Integer totalNbrCopies) {
        this.totalNbrCopies = totalNbrCopies;
    }

    /**
     * @return the publicationDate
     */
    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * @param publicationDate the publicationDate to set
     */
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

}
