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

import com.github.jcpp.jathenaeum.Customer;
import com.github.jcpp.jathenaeum.beans.CustomerForm;
import com.github.jcpp.jathenaeum.db.dao.CustomerDAO;

/**
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class CustomerActionDo extends DispatchAction {
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		Customer customer = new Customer();
		CustomerForm uf = (CustomerForm) form;
		
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
			customer.setName(uf.getName());
			customer.setSurname(uf.getSurname());
			customer.setEmail(uf.getEmail());

			try{
				CustomerDAO.insert(customer);
				actionTarget = "addSuccess";

			}catch(Exception e){
				actionTarget = "addFailed";
			}
		}

		return mapping.findForward(actionTarget);
	}

}
