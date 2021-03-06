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

import com.github.jcpp.jathenaeum.Customer;
import com.github.jcpp.jathenaeum.beans.CustomerForm;
import com.github.jcpp.jathenaeum.db.dao.CustomerDAO;
import com.github.jcpp.jathenaeum.utils.Redirector;
import com.github.jcpp.jathenaeum.utils.Validator;

/**
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class CustomerAction extends DispatchAction {
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(!Validator.isLogged(session)){
			return Redirector.loginRequiredRedirect(mapping, session);
		}
		
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		CustomerForm customerForm = (CustomerForm) session.getAttribute("form");
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		

		//Set the request
		request.setAttribute("addCustomerForm", customerForm);
		actionTarget = "add";
		
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
		
		//Check the id
		if(!Validator.isValidCustomerId(request.getParameter("id"))){
			actionTarget = "invalidId";
			
    		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
    		return redirect;
		}
		
		String id = request.getParameter("id");
		
		Customer customer = CustomerDAO.getById(Integer.parseInt(id));
		
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		CustomerForm customerForm = (CustomerForm) session.getAttribute("form");
		
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		if(customerForm != null){
			//Overwrite the attributes
			customer = new Customer(customerForm);
		}
		
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		
		//Set the request
		request.setAttribute("id", id);
		request.setAttribute("editCustomerForm", customer);
		
		actionTarget = "edit";
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
		
		if(!Validator.isValidCustomerId(request.getParameter("id"))){
			actionTarget = "invalidId";
			
    		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
    		return redirect;
		}
		
		String id = request.getParameter("id");
		
		Customer customer = CustomerDAO.getById(Integer.parseInt(id));
		
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		
		//Set the request
		request.setAttribute("customer", customer);
		
		actionTarget = "delete";
		return mapping.findForward(actionTarget);
	}
	
	
	public ActionForward viewAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(!Validator.isLogged(session)){
			return Redirector.loginRequiredRedirect(mapping, session);
		}

		ArrayList<Customer> customers = CustomerDAO.getAll();
		request.setAttribute("customers", customers);
		actionTarget = "viewAll";
		return mapping.findForward(actionTarget);
	}

}
