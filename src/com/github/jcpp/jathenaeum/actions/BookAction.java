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

import com.github.jcpp.jathenaeum.Author;
import com.github.jcpp.jathenaeum.db.dao.AuthorDAO;

/**
 * Book action.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class BookAction extends ForwardAction {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String action_target = null;

		ArrayList<Author> authors = AuthorDAO.getAll();
		request.setAttribute("authors", authors);
		action_target = "success";
		//return mapping.findForward(action_target);
		return super.execute(mapping, form, request, response);
	}


}
