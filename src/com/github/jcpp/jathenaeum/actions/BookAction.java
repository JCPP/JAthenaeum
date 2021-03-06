/**
 * 
 */
package com.github.jcpp.jathenaeum.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;

import com.github.jcpp.jathenaeum.Author;
import com.github.jcpp.jathenaeum.Book;
import com.github.jcpp.jathenaeum.beans.BookForm;
import com.github.jcpp.jathenaeum.db.dao.AuthorDAO;
import com.github.jcpp.jathenaeum.db.dao.BookDAO;
import com.github.jcpp.jathenaeum.db.dao.CopyDAO;
import com.github.jcpp.jathenaeum.utils.Redirector;
import com.github.jcpp.jathenaeum.utils.Validator;

/**
 * 
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class BookAction extends DispatchAction {
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(!Validator.isLogged(session)){
			return Redirector.loginRequiredRedirect(mapping, session);
		}

		ArrayList<Author> authors = AuthorDAO.getAll();
		ArrayList<Author> selectedAuthors = new ArrayList<Author>();
		
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		BookForm bookForm = (BookForm) session.getAttribute("form");
		
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		/* Add the already selected authors */
		if(bookForm != null){
			
			String[] oldAuthors = bookForm.getAuthors();
			
			if(oldAuthors != null){
				for(int i = 0; i < authors.size(); i++){
					Author author = authors.get(i);
					for(int j = 0; j < oldAuthors.length; j++){
						if(author.getId() == Integer.parseInt(oldAuthors[j]) ){
							selectedAuthors.add(author);
						}
					}
				}
			}
		}
		
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		
		
		if(authors.isEmpty()){
			actionTarget = "noAuthors";
		}
		else{
			//Set the request
			request.setAttribute("addBookForm", bookForm);
			request.setAttribute("authors", authors);
			request.setAttribute("selectedAuthors", selectedAuthors);
			actionTarget = "add";
		}
		
		return mapping.findForward(actionTarget);
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(!Validator.isLogged(session)){
			return Redirector.loginRequiredRedirect(mapping, session);
		}
		
		//Check the id
		if(!Validator.isValidBookId(request.getParameter("id"))){
			actionTarget = "invalidId";
			
    		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
    		return redirect;
		}
		
		String id = request.getParameter("id");
		
		Book book = BookDAO.getById(Integer.parseInt(id));
		ArrayList<Author> authors = AuthorDAO.getAll();
		ArrayList<Author> bookAuthors = AuthorDAO.getAllByLibroId(book.getId());
		
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		BookForm bookForm = (BookForm) session.getAttribute("form");
		
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		if(bookForm != null){
			//Overwrite the attributes
			book = new Book(bookForm);
			
			String[] oldAuthors = bookForm.getAuthors();
			
			ArrayList<Author> tempBookAuthors = new ArrayList<Author>();
			
			if(oldAuthors != null){
				for(int i = 0; i < bookAuthors.size(); i++){
					Author author = bookAuthors.get(i);
					for(int j = 0; j < oldAuthors.length; j++){
						if(author.getId() == Integer.parseInt(oldAuthors[j]) ){
							tempBookAuthors.add(author);
						}
					}
				}
			}
			
			bookAuthors = tempBookAuthors;
		}
		
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		
		//Set the request
		request.setAttribute("book", book);
		request.setAttribute("authors", authors);
		request.setAttribute("bookAuthors", bookAuthors);
		
		actionTarget = "edit";
		return mapping.findForward(actionTarget);
	}
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(!Validator.isLogged(session)){
			return Redirector.loginRequiredRedirect(mapping, session);
		}
		
		//Check the id
		if(!Validator.isValidBookId(request.getParameter("id"))){
			actionTarget = "invalidId";
			
    		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
    		return redirect;
		}
		
		String id = request.getParameter("id");
		
		Book book = BookDAO.getById(Integer.parseInt(id));
		
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		
		//Set the request
		request.setAttribute("book", book);
		
		actionTarget = "delete";
		return mapping.findForward(actionTarget);
	}
	
	
	public ActionForward viewAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(!Validator.isLogged(session)){
			return Redirector.loginRequiredRedirect(mapping, session);
		}

		ArrayList<Book> books = BookDAO.getAllWithAuthors();
		request.setAttribute("books", books);
		actionTarget = "viewAll";
		return mapping.findForward(actionTarget);
	}
	
	
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(!Validator.isLogged(session)){
			return Redirector.loginRequiredRedirect(mapping, session);
		}
		
		ArrayList<Book> books = (ArrayList<Book>) session.getAttribute("books");
		BookForm bookForm = (BookForm) session.getAttribute("form");
		
		Book book = new Book();
		
		if(bookForm != null){
			book = new Book(bookForm);
		}
			
		
		if(session.getAttribute("books") == null){
			books = BookDAO.getAllWithAuthorsAndCopies();
		}
		else{
			request.setAttribute("search", true);
		}
		
		//Remove attributes from session
		session.removeAttribute("books");
		session.removeAttribute("form");
		
		request.setAttribute("books", books);
		request.setAttribute("authors", AuthorDAO.getAll());
		request.setAttribute("book", book);
		actionTarget = "search";
		return mapping.findForward(actionTarget);
	}
	
	
	public ActionForward manageCopies(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(!Validator.isLogged(session)){
			return Redirector.loginRequiredRedirect(mapping, session);
		}
		
		//Check the id
		if(!Validator.isValidBookId(request.getParameter("id"))){
			actionTarget = "invalidId";
			
    		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
    		return redirect;
		}
		
		String id = request.getParameter("id");
		
		BookForm bookForm = (BookForm) session.getAttribute("form");
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		
		Book book = new Book();

		//Overwrite the attributes
		if(bookForm != null){
			if(Validator.isValidInt(bookForm.getNumberOfCopies())){
				book.setNumberOfCopies(Integer.parseInt(bookForm.getNumberOfCopies()));
			}
			else{
				book.setNumberOfCopies(CopyDAO.getNumberByBookId(Integer.parseInt(id)));
			}
		}
		else{
			book.setNumberOfCopies(CopyDAO.getNumberByBookId(Integer.parseInt(id)));
		}
		
		book.setId(Integer.parseInt(id));
		
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		
		
		request.setAttribute("book", book);
		actionTarget = "manageCopies";
		return mapping.findForward(actionTarget);
	}

}
