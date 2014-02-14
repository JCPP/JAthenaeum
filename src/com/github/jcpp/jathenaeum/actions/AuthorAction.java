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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;

import com.github.jcpp.jathenaeum.Author;
import com.github.jcpp.jathenaeum.beans.AuthorForm;
import com.github.jcpp.jathenaeum.db.dao.AuthorDAO;
import com.github.jcpp.jathenaeum.utils.Redirector;
import com.github.jcpp.jathenaeum.utils.Validator;

/**
 * 
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class AuthorAction extends DispatchAction {
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		
		if(!Validator.isLogged(session)){
			return Redirector.loginRequiredRedirect(mapping, session);
		}
		
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		AuthorForm authorForm = (AuthorForm) session.getAttribute("form");
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		

		//Set the request
		request.setAttribute("addAuthorForm", authorForm);
		actionTarget = "add";
		
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
		if(!Validator.isValidAuthorId(request.getParameter("id"))){
			actionTarget = "invalidId";
			
    		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
    		return redirect;
		}
		
		String id = request.getParameter("id");
		
		Author author = AuthorDAO.getById(Integer.parseInt(id));
		
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		AuthorForm authorForm = (AuthorForm) session.getAttribute("form");
		
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		if(authorForm != null){
			//Overwrite the attributes
			author = new Author(authorForm);
		}
		
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		
		//Set the request
		request.setAttribute("id", id);
		request.setAttribute("editAuthorForm", author);
		
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
		if(!Validator.isValidAuthorId(request.getParameter("id"))){
			actionTarget = "invalidId";
			
    		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
    		return redirect;
		}
		
		String id = request.getParameter("id");
		
		Author author = AuthorDAO.getById(Integer.parseInt(id));
		
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		
		//Set the request
		request.setAttribute("author", author);
		
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

		ArrayList<Author> authors = AuthorDAO.getAll();
		request.setAttribute("authors", authors);
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
		
		ArrayList<Author> authors = (ArrayList<Author>) session.getAttribute("authors");
		AuthorForm authorForm = (AuthorForm) session.getAttribute("form");
		
		Author author = new Author();
		
		if(authorForm != null){
			//Overwrite the attributes
			author = new Author(authorForm);
		}
			
		
		if(session.getAttribute("authors") == null){
			authors = AuthorDAO.getAll();
		}
		else{
			request.setAttribute("search", true);
		}
		
		//Remove attributes from session
		session.removeAttribute("authors");
		session.removeAttribute("form");
		
		request.setAttribute("author", author);
		request.setAttribute("authors", authors);
		actionTarget = "search";
		return mapping.findForward(actionTarget);
	}

}
