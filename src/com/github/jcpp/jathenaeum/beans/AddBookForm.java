/**
 * 
 */
package com.github.jcpp.jathenaeum.beans;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


/**
 * Add a book form.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class AddBookForm extends ActionForm {
	
	private String title;
	private String cover;
	private String genre;
	private String isbn;
	private String description;
	private String[] authors;
	
	public AddBookForm(){
		super();
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
	public String[] getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}


	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		/*
		System.out.println("title: " + title);
		System.out.println("cover: " + cover);
		System.out.println("genre: " + genre);
		System.out.println("isbn: " + isbn);
		System.out.println("description: " + description);
		System.out.println("authors: " + authors);
		*/
		
		//Check title
		if(title == null || title.isEmpty()){
			errors.add("title", new ActionMessage("book.title.empty"));
		}
		
		//Check authors
		if(authors == null || authors.length == 0){
			errors.add("authors", new ActionMessage("book.authors.empty"));
		}
		
		return errors;
	}

}
