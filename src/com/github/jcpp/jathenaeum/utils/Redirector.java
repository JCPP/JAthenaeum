/**
 * 
 */
package com.github.jcpp.jathenaeum.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Class for utils methods to redirect.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Redirector {
	
	/**
	 * Redirect the user to the sign in page.
	 * @param mapping the ActionMapping object.
	 * @param session the HttpSession object.
	 * @return The ActionForward object to the sign in page.
	 */
	public static ActionForward loginRequiredRedirect(ActionMapping mapping, HttpSession session){
		ActionErrors actionErrors = Validator.getLoginRequiredError();
		session.setAttribute("errors", actionErrors);
		return mapping.findForward("loginRequired");
	}
	
	
	/**
	 * Redirect the user to the error page.
	 * @param mapping the ActionMapping object.
	 * @param session the HttpSession object.
	 * @return The ActionForward object to the error page.
	 */
	public static ActionForward alreadyLoggedRedirect(ActionMapping mapping, HttpSession session){
		ActionErrors actionErrors = Validator.getAlreadyLoggedError();
		session.setAttribute("errors", actionErrors);
		return mapping.findForward("alreadyLogged");
	}

}
