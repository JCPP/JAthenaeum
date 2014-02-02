/**
 * 
 */
package com.github.jcpp.jathenaeum.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
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
		//ActionMessages errors_mesg = new ActionMessages();
		SigninForm uf = (SigninForm) form;
        if(form != null){
        	user.setEmail(uf.getEmail());
        	user.setPassword(uf.getPassword());
        	
        	HttpSession session = request.getSession();
    		session.setAttribute("user", user);
    		
    		actionTarget = "success";
        }

		return mapping.findForward(actionTarget);
	}

}
