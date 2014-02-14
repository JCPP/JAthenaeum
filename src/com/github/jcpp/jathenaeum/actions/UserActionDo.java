/**
 * 
 */
package com.github.jcpp.jathenaeum.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.actions.DispatchAction;

import com.github.jcpp.jathenaeum.User;
import com.github.jcpp.jathenaeum.beans.LoginForm;
import com.github.jcpp.jathenaeum.beans.UserForm;
import com.github.jcpp.jathenaeum.db.dao.UserDAO;

/**
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class UserActionDo extends DispatchAction {
	
	public ActionForward signup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		
		UserForm uf = (UserForm) form;
		
		ActionErrors actionErrors = uf.validateSignUp(mapping, request);
		
		//If there are some errors, redirect to the form page
		if(!actionErrors.isEmpty()){
			actionTarget = "signupErrors";
			saveErrors(request, actionErrors); //Save the errors
			
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			return redirect;
		}
		
		User user;
		
        if(form != null){
        	user = new User(uf);
        	
        	try{
        		if(UserDAO.register(user)){
        			actionTarget = "signupSuccess";
        		}
        		
        		//Auto login
        		session.setAttribute("user", user);
        		
        	}catch(Exception e){
        		actionTarget = "signupFailed";
        	}
        }
        else{
        	actionTarget = "signupFailed";
        }

		return mapping.findForward(actionTarget);
	}
	
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();

		UserForm uf = (UserForm) form;
		
		ActionErrors actionErrors = uf.validateLogin(mapping, request);
		
		//If there are some errors, redirect to the form page
		if(!actionErrors.isEmpty()){
			actionTarget = "loginErrors";
			saveErrors(request, actionErrors); //Save the errors
			
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			return redirect;
		}
		
		User user = new User();
		
		if(form != null){
			user.setEmail(uf.getEmail());
        	user.setPassword(uf.getPassword());
        	
        	session = request.getSession();
    		session.setAttribute("user", user);
    		actionTarget = "loginSuccess";
		}
		else{
			actionTarget = "loginFailed";
		}
		
		//Remove attributes from session
  		session.removeAttribute("errors");

		return mapping.findForward(actionTarget);
	}

}
