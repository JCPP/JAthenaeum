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
import com.github.jcpp.jathenaeum.beans.AuthorForm;
import com.github.jcpp.jathenaeum.db.dao.AuthorDAO;
import com.github.jcpp.jathenaeum.utils.Converter;

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
		
		String id = request.getParameter("id");
		
		if(request.getParameter("id") == null){
			System.out.println("ID not present.");
		}
		
		Author author = AuthorDAO.getById(Integer.parseInt(id));
		
		HttpSession session = request.getSession();
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		AuthorForm authorForm = (AuthorForm) session.getAttribute("form");
		
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		if(authorForm != null){
			
			//Overwrite the attributes
			author.setName(authorForm.getName());
			author.setSurname(authorForm.getSurname());
			author.setPhoto(authorForm.getPhoto());
			
			if(Converter.checkStringToDate(authorForm.getBornDate())){
				author.setBornDate(Converter.fromStringToDate(authorForm.getBornDate()));
			}
			
			author.setBiography(authorForm.getBiography());
			
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
		
		String id = request.getParameter("id");
		
		if(request.getParameter("id") == null){
			System.out.println("ID not present.");
		}
		
		Author author = AuthorDAO.getById(Integer.parseInt(id));
		
		HttpSession session = request.getSession();
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
		ArrayList<Author> authors = (ArrayList<Author>) session.getAttribute("authors");
		AuthorForm authorForm = (AuthorForm) session.getAttribute("form");
		
		Author author = new Author();
		
		if(authorForm != null){
			
			//Overwrite the attributes
			author.setName(authorForm.getName());
			author.setSurname(authorForm.getSurname());
			author.setPhoto(authorForm.getPhoto());
			
			if(Converter.checkStringToDate(authorForm.getBornDate())){
				author.setBornDate(Converter.fromStringToDate(authorForm.getBornDate()));
			}
			
			
			author.setBiography(authorForm.getBiography());
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
