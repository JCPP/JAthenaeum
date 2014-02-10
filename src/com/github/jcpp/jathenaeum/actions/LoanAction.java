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
import org.apache.struts.actions.DispatchAction;

import com.github.jcpp.jathenaeum.Book;
import com.github.jcpp.jathenaeum.Customer;
import com.github.jcpp.jathenaeum.beans.LoanForm;
import com.github.jcpp.jathenaeum.db.dao.BookDAO;
import com.github.jcpp.jathenaeum.db.dao.CustomerDAO;

/**
 * 
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class LoanAction extends DispatchAction {
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;

		ArrayList<Book> books = BookDAO.getAllWithAtLeastOneCopy();
		ArrayList<Customer> customers = CustomerDAO.getAll();
		
		HttpSession session = request.getSession();
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		LoanForm loanForm = (LoanForm) session.getAttribute("form");
		
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		/* Add the already selected book and and customer */
		if(loanForm != null){
			
		}
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		
		
		if(books.isEmpty()){
			actionTarget = "noBooks";
		}
		else{
			if(customers.isEmpty()){
				actionTarget = "noCustomers";
			}
			else{
				//Set the request
				request.setAttribute("addLoanForm", loanForm);
				request.setAttribute("books", books);
				request.setAttribute("customers", customers);
				actionTarget = "add";
			}
		}
		
		
		return mapping.findForward(actionTarget);
	}

}
