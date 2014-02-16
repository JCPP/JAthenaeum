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
import org.apache.struts.actions.DispatchAction;

import com.github.jcpp.jathenaeum.beans.UserForm;
import com.github.jcpp.jathenaeum.utils.Redirector;
import com.github.jcpp.jathenaeum.utils.Validator;

/**
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class UserAction extends DispatchAction {
	
	public ActionForward signup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(Validator.isLogged(session)){
			return mapping.findForward("alreadyLogged");
		}

		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		UserForm userForm = (UserForm) session.getAttribute("form");
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		

		//Set the request
		request.setAttribute("signupForm", userForm);
		actionTarget = "signup";
		
		return mapping.findForward(actionTarget);
	}
	
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(Validator.isLogged(session)){
			return mapping.findForward("alreadyLogged");
		}

		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		UserForm userForm = (UserForm) session.getAttribute("form");
		
		if(actionErrors != null){
			//Save the errors in this action
			System.out.println("Saving errors in login!");
			saveErrors(request, actionErrors);
		}
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		

		//Set the request
		request.setAttribute("loginForm", userForm);
		actionTarget = "login";
		
		return mapping.findForward(actionTarget);
	}
	
	
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(!Validator.isLogged(session)){
			saveErrors(request, Validator.getLoginRequiredError());
			return mapping.findForward("loginRequired");
		}

		session.removeAttribute("user");
		actionTarget = "logoutSuccess";
		return mapping.findForward(actionTarget);
	}

}
