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
import com.github.jcpp.jathenaeum.beans.AuthorForm;
import com.github.jcpp.jathenaeum.db.dao.AuthorDAO;
import com.github.jcpp.jathenaeum.utils.Redirector;
import com.github.jcpp.jathenaeum.utils.Validator;

/**
 * 
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class AuthorActionDo extends DispatchAction {
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(!Validator.isLogged(session)){
			return Redirector.loginRequiredRedirect(mapping, session);
		}
		
		Author author = new Author();
		AuthorForm uf = (AuthorForm) form;
		
		ActionErrors actionErrors = uf.validate(mapping, request);
		
		//If there are some errors, redirect to the form page
		if(!actionErrors.isEmpty()){
			actionTarget = "addErrors";
			
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			return redirect;
		}
		
		
		if(form != null){
			author = new Author(uf);
			
			try{
				AuthorDAO.insert(author);
				actionTarget = "addSuccess";

			}catch(Exception e){
				actionTarget = "addFailed";
			}
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
		
		Author author = new Author();
		AuthorForm uf = (AuthorForm) form;
		
		//Check the id
		if(!uf.validateId(mapping, request).isEmpty()){
			actionTarget = "invalidId";
			
    		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
    		return redirect;
		}
				
		ActionErrors actionErrors = uf.validate(mapping, request);
		int id = Integer.parseInt(request.getParameter("id"));
		
		//If there are some errors, redirect to the form page
		if(!actionErrors.isEmpty()){
			actionTarget = "editErrors";
			
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			redirect.addParameter("id", Integer.toString(id));
			return redirect;
		}
		

		if(form != null){
			author = new Author(uf);
			author.setId(id);

			try{
				AuthorDAO.update(author);
				actionTarget = "editSuccess";

			}catch(Exception e){
				actionTarget = "editFailed";
			}
		}

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
		
		AuthorForm uf = (AuthorForm) form;
		
		//Check the id
		if(!uf.validateId(mapping, request).isEmpty()){
			actionTarget = "invalidId";
			
    		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
    		return redirect;
		}
		
		ActionErrors actionErrors = uf.validate(mapping, request);
		int id = Integer.parseInt(request.getParameter("id"));
		
		//If there are some errors, redirect to the form page
		if(!actionErrors.isEmpty()){
			actionTarget = "deleteErrors";
			
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			redirect.addParameter("id", Integer.toString(id));
			return redirect;
		}

		if(form != null){
			try{
				AuthorDAO.delete(id);
				actionTarget = "deleteSuccess";

			}catch(Exception e){
				actionTarget = "deleteFailed";
			}
		}

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
		
		AuthorForm uf = (AuthorForm) form;
		
		//ActionErrors actionErrors = uf.validate(mapping, request);
		
		//If there are some errors, redirect to the form page
		/*
		if(!actionErrors.isEmpty()){
			actionTarget = "deleteErrors";
			
			HttpSession session = request.getSession();
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			redirect.addParameter("id", Integer.toString(id));
			return redirect;
		}
		*/
		
		ArrayList<Author> authors = new ArrayList<Author>();
		
		if(form != null){
			try{
				authors = AuthorDAO.search(uf);
				
				actionTarget = "search";
				
	    		session.setAttribute("authors", authors);
	    		session.setAttribute("form", uf);
				
				ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
				return redirect;

			}catch(Exception e){
				e.printStackTrace();
				actionTarget = "searchFailed";
			}
		}
		
		

		return mapping.findForward(actionTarget);
	}

}
