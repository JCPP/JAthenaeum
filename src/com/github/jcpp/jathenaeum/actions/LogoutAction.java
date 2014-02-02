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
		String action_target = null;

		HttpSession session = request.getSession();
		session.removeAttribute("user");
		action_target = "success";
		return mapping.findForward(action_target);
	}


}
