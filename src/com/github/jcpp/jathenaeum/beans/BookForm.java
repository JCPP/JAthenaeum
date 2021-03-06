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

import com.github.jcpp.jathenaeum.db.dao.BookDAO;
import com.github.jcpp.jathenaeum.db.dao.LoanDAO;
import com.github.jcpp.jathenaeum.utils.Validator;


/**
 * Add a book form.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class BookForm extends ActionForm {
	
	private String title;
	private String cover;
	private String genre;
	private String isbn;
	private String description;
	private String[] authors;
	private String numberOfCopies;
	
	public BookForm(){
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

	/**
	 * @return the numberOfCopies
	 */
	public String getNumberOfCopies() {
		return numberOfCopies;
	}

	/**
	 * @param numberOfCopies the numberOfCopies to set
	 */
	public void setNumberOfCopies(String numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
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
	
	
	public ActionErrors validateNumberOfCopies(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		/*
		System.out.println("numberOfCopies: " + numberOfCopies);
		*/
		
		//Check number of copies
		if(numberOfCopies == null || numberOfCopies.isEmpty()){
			errors.add("numberOfCopies", new ActionMessage("book.numberofcopies.empty"));
		}
		
		if(!Validator.isValidInt(numberOfCopies) || Integer.parseInt(numberOfCopies) < 0){
			errors.add("numberOfCopies", new ActionMessage("book.numberofcopies.invalid"));
		}
		
		return errors;
	}
	
	
	public ActionErrors validateId(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		if(request.getParameter("id") == null){
			errors.add("id", new ActionMessage("book.id.empty"));
		}
		else if(!Validator.isValidInt(request.getParameter("id"))){
			errors.add("id", new ActionMessage("book.id.invalid"));
		}
		else if(!BookDAO.exists(Integer.parseInt(request.getParameter("id")))){
			errors.add("id", new ActionMessage("book.id.invalid"));
		}
		
		return errors;
	}

}
