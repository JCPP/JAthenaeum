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
import org.apache.struts.actions.ForwardAction;

import com.github.jcpp.jathenaeum.Book;
import com.github.jcpp.jathenaeum.db.dao.BookDAO;

/**
 * Index action. This action retrieves all the books.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class IndexAction extends ForwardAction {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;

		ArrayList<Book> books = BookDAO.getAll();
		request.setAttribute("books", books);
		actionTarget = "success";
		//return mapping.findForward(action_target);
		return super.execute(mapping, form, request, response);
	}

}
