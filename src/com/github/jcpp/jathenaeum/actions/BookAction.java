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
import org.apache.struts.actions.DispatchAction;

import com.github.jcpp.jathenaeum.Author;
import com.github.jcpp.jathenaeum.Book;
import com.github.jcpp.jathenaeum.db.dao.AuthorDAO;
import com.github.jcpp.jathenaeum.db.dao.BookDAO;

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

		ArrayList<Author> authors = AuthorDAO.getAll();
		
		if(authors.isEmpty()){
			actionTarget = "noAuthors";
		}
		else{
			request.setAttribute("authors", authors);
			actionTarget = "add";
		}
		
		return mapping.findForward(actionTarget);
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String action_target = null;
		
		String id = request.getParameter("id");
		
		HttpSession session = request.getSession();
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		
		if(actionErrors != null){
			saveErrors(request, actionErrors); //Save the errors
		}
		
		session.removeAttribute("errors");
		
		
		if(request.getParameter("id") == null){
			System.out.println("ID not present.");
		}

		Book book = BookDAO.getById(Integer.parseInt(id));
		ArrayList<Author> authors = AuthorDAO.getAll();
		ArrayList<Author> bookAuthors = AuthorDAO.getAllByLibroId(book.getId());
		
		request.setAttribute("book", book);
		request.setAttribute("authors", authors);
		request.setAttribute("bookAuthors", bookAuthors);
		action_target = "edit";
		return mapping.findForward(action_target);
	}
	
	
	public ActionForward viewAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String action_target = null;

		ArrayList<Book> books = BookDAO.getAll();
		request.setAttribute("books", books);
		action_target = "viewAll";
		return mapping.findForward(action_target);
	}

}
