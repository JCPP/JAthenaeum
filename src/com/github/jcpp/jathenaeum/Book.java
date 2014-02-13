/**
 * 
 */
package com.github.jcpp.jathenaeum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.jcpp.jathenaeum.beans.BookForm;

/**
 * Book class.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Book {
	private int id;
	private String title;
	private String cover;
	private String genre;
	private String isbnCode;
	private String description;
	private ArrayList<Author> authors;
	private int numberOfCopies;
	private int numberOfFreeCopies;
	
	/**
	 * Create a Book instance.
	 */
	public Book(){
		
	}
	
	
	/**
	 * Create a Book instance from a ResultSet.
	 * @param resultSet
	 * @throws SQLException 
	 */
	public Book(ResultSet resultSet) throws SQLException {
		id = resultSet.getInt(1);
		title = resultSet.getString(2);
		cover = resultSet.getString(3);
		genre = resultSet.getString(4);
		isbnCode = resultSet.getString(5);
		description = resultSet.getString(6);
	}
	
	/**
	 * Create a Book instance from an BookForm.
	 * @param bookForm
	 */
	public Book(BookForm bookForm) {
		cover = bookForm.getCover();
		description = bookForm.getDescription();
		genre = bookForm.getGenre();
		isbnCode = bookForm.getIsbn();
		title = bookForm.getTitle();
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the cover
	 */
	public String getCover() {
		return cover;
	}
	/**
	 * @param cover the cover to set
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}
	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**
	 * @return the isbnCode
	 */
	public String getIsbnCode() {
		return isbnCode;
	}
	/**
	 * @param isbnCode the isbnCode to set
	 */
	public void setIsbnCode(String isbnCode) {
		this.isbnCode = isbnCode;
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
	 * @return the authors
	 */
	public ArrayList<Author> getAuthors() {
		return authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}
	/**
	 * @return the numberOfCopies
	 */
	public int getNumberOfCopies() {
		return numberOfCopies;
	}
	/**
	 * @param numberOfCopies the numberOfCopies to set
	 */
	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}
	/**
	 * @return the numberOfFreeCopies
	 */
	public int getNumberOfFreeCopies() {
		return numberOfFreeCopies;
	}
	/**
	 * @param numberOfFreeCopies the numberOfFreeCopies to set
	 */
	public void setNumberOfFreeCopies(int numberOfFreeCopies) {
		this.numberOfFreeCopies = numberOfFreeCopies;
	}
	
	
}
