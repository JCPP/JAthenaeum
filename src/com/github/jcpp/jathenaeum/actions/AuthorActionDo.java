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
import com.github.jcpp.jathenaeum.Writes;
import com.github.jcpp.jathenaeum.beans.AuthorForm;
import com.github.jcpp.jathenaeum.beans.BookForm;
import com.github.jcpp.jathenaeum.db.dao.AuthorDAO;
import com.github.jcpp.jathenaeum.db.dao.BookDAO;
import com.github.jcpp.jathenaeum.db.dao.WritesDAO;
import com.github.jcpp.jathenaeum.utils.Converter;

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
		
		Author author = new Author();
		AuthorForm uf = (AuthorForm) form;
		
		ActionErrors actionErrors = uf.validate(mapping, request);
		
		//If there are some errors, redirect to the form page
		if(!actionErrors.isEmpty()){
			actionTarget = "addErrors";
			saveErrors(request, actionErrors); //Save the errors
			
			HttpSession session = request.getSession();
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			return redirect;
		}
		
		
		if(form != null){
			author.setName(uf.getName());
			author.setSurname(uf.getSurname());
			author.setPhoto(uf.getPhoto());
			
			if(Converter.checkStringToDate(uf.getBornDate())){
				author.setBornDate(Converter.fromStringToDate(uf.getBornDate()));
			}
			
			author.setBiography(uf.getBiography());


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
		
		Author author = new Author();
		AuthorForm uf = (AuthorForm) form;
		int id = Integer.parseInt(request.getParameter("id"));
		
		ActionErrors actionErrors = uf.validate(mapping, request);
		
		//If there are some errors, redirect to the form page
		if(!actionErrors.isEmpty()){
			actionTarget = "editErrors";
			saveErrors(request, actionErrors); //Save the errors
			
			HttpSession session = request.getSession();
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			redirect.addParameter("id", Integer.toString(id));
			return redirect;
		}

		if(form != null){
			author.setId(id);
			author.setName(uf.getName());
			author.setSurname(uf.getSurname());
			author.setPhoto(uf.getPhoto());
			
			if(Converter.checkStringToDate(uf.getBornDate())){
				author.setBornDate(Converter.fromStringToDate(uf.getBornDate()));
			}
			
			author.setBiography(uf.getBiography());


			try{
				AuthorDAO.update(author);
				actionTarget = "editSuccess";

			}catch(Exception e){
				actionTarget = "editFailed";
			}
		}
		
		

		return mapping.findForward(actionTarget);
	}

}
