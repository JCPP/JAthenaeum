/**
 * 
 */
package com.github.jcpp.jathenaeum.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.ForwardAction;

import com.github.jcpp.jathenaeum.utils.Redirector;
import com.github.jcpp.jathenaeum.utils.Validator;

/**
 * Logout action.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class LogoutAction extends ForwardAction {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(!Validator.isLogged(session)){
			return Redirector.loginRequiredRedirect(mapping, session);
		}

		session.removeAttribute("user");
		actionTarget = "success";
		return mapping.findForward(actionTarget);
	}


}
