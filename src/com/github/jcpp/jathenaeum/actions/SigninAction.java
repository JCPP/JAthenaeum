/**
 * 
 */
package com.github.jcpp.jathenaeum.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.github.jcpp.jathenaeum.User;
import com.github.jcpp.jathenaeum.beans.SigninForm;

/**
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class SigninAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String actionTarget = null;
		
		User user = new User();
		HttpSession session = request.getSession();
		
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		SigninForm uf = (SigninForm) form;
        if(form != null){
        	user.setEmail(uf.getEmail());
        	user.setPassword(uf.getPassword());
        	
        	session = request.getSession();
    		session.setAttribute("user", user);
    		
    		actionTarget = "success";
        }
        
        //Remove attributes from session
  		session.removeAttribute("errors");

		return mapping.findForward(actionTarget);
	}

}
