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
import com.github.jcpp.jathenaeum.utils.Redirector;
import com.github.jcpp.jathenaeum.utils.Validator;

/**
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class CustomerActionDo extends DispatchAction {
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		HttpSession session = request.getSession();
		if(!Validator.isLogged(session)){
			return Redirector.loginRequiredRedirect(mapping, session);
		}
		
		Customer customer = new Customer();
		CustomerForm uf = (CustomerForm) form;
		
		ActionErrors actionErrors = uf.validate(mapping, request);
		actionErrors.add(uf.validateEmailInDb(mapping, request));
		
		
		//If there are some errors, redirect to the form page
		if(!actionErrors.isEmpty()){
			actionTarget = "addErrors";
			saveErrors(request, actionErrors); //Save the errors
			
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			return redirect;
		}
		
		
		if(form != null){
			customer = new Customer(uf);

			try{
				CustomerDAO.insert(customer);
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
		
		HttpSession session = request.getSession();
		if(!Validator.isLogged(session)){
			return Redirector.loginRequiredRedirect(mapping, session);
		}
		
		Customer customer = new Customer();
		CustomerForm uf = (CustomerForm) form;
		
		//Check the id
		if(!uf.validateId(mapping, request).isEmpty()){
			actionTarget = "invalidId";
			
    		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
    		return redirect;
		}
		
		ActionErrors actionErrors = uf.validate(mapping, request);
		int id = Integer.parseInt(request.getParameter("id"));
		
		customer = CustomerDAO.getById(id);
		
		//If the customer set a different email it need to check it
		if(!customer.getEmail().equals(uf.getEmail())){
			actionErrors.add(uf.validateEmailInDb(mapping, request));
		}
		
		//If there are some errors, redirect to the form page
		if(!actionErrors.isEmpty()){
			actionTarget = "editErrors";
			saveErrors(request, actionErrors); //Save the errors
			
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			redirect.addParameter("id", Integer.toString(id));
			return redirect;
		}

		if(form != null){
			customer = new Customer(uf);
			customer.setCardNumber(id);

			try{
				CustomerDAO.update(customer);
				actionTarget = "editSuccess";

			}catch(Exception e){
				actionTarget = "editFailed";
			}
		}

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
		
		CustomerForm uf = (CustomerForm) form;
		
		//Check the id
		if(!uf.validateId(mapping, request).isEmpty()){
			actionTarget = "invalidId";
			
    		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
    		return redirect;
		}
		
		int id = Integer.parseInt(request.getParameter("id"));

		if(form != null){
			try{
				CustomerDAO.delete(id);
				actionTarget = "deleteSuccess";

			}catch(Exception e){
				actionTarget = "deleteFailed";
			}
		}

		return mapping.findForward(actionTarget);
	}

}
