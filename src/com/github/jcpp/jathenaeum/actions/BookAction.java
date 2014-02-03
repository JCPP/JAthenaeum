/**
 * 
 */
package com.github.jcpp.jathenaeum.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String action_target = null;

		ArrayList<Author> authors = AuthorDAO.getAll();
		request.setAttribute("authors", authors);
		action_target = "add";
		return mapping.findForward(action_target);
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String action_target = null;
		
		String id = request.getParameter("id");
		
		if(request.getParameter("id") == null){
			System.out.println("ID not present.");
		}

		ArrayList<Author> authors = AuthorDAO.getAll();
		Book book = BookDAO.getById(Integer.parseInt(id));
		request.setAttribute("authors", authors);
		request.setAttribute("book", book);
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
