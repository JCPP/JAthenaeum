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
import com.github.jcpp.jathenaeum.Loan;
import com.github.jcpp.jathenaeum.beans.LoanForm;
import com.github.jcpp.jathenaeum.db.dao.BookDAO;
import com.github.jcpp.jathenaeum.db.dao.CustomerDAO;
import com.github.jcpp.jathenaeum.db.dao.LoanDAO;
import com.github.jcpp.jathenaeum.utils.Converter;
import com.github.jcpp.jathenaeum.utils.Validator;

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

		ArrayList<Book> booksWithCopies = BookDAO.getAllWithAtLeastOneCopy();
		int numberOfBook = BookDAO.getNumber();
		ArrayList<Customer> customers = CustomerDAO.getAll();
		
		HttpSession session = request.getSession();
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		LoanForm loanForm = (LoanForm) session.getAttribute("form");
		Loan loan = new Loan();
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		/* Add the already selected book and and customer */
		if(loanForm != null){
			loan.setCustomerCardNumber(Integer.parseInt(loanForm.getCustomerCardNumber()));
			loan.setCopyId(Integer.parseInt(loanForm.getBookId()));

			if(loanForm.getReturned() != null){
				loan.setReturned(true);
			}
		}
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		
		
		if(booksWithCopies.isEmpty()){
			actionTarget = "noCopies";
		}
		else if(customers.isEmpty()){
			actionTarget = "noCustomers";
		}
		else if(numberOfBook == 0){
			actionTarget = "noBooks";
		}
		else{
			//Set the request
			request.setAttribute("loan", loan);
			request.setAttribute("books", booksWithCopies);
			request.setAttribute("customers", customers);
			actionTarget = "add";
		}
		
		
		return mapping.findForward(actionTarget);
	}
	
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		ArrayList<Book> books = BookDAO.getAllWithAtLeastOneCopy();
		ArrayList<Customer> customers = CustomerDAO.getAll();
		
		String id = request.getParameter("id");
		
		if(request.getParameter("id") == null){
			System.out.println("ID not present.");
		}
		
		Loan loan = LoanDAO.getById(Integer.parseInt(id));
		
		HttpSession session = request.getSession();
		ActionErrors actionErrors = (ActionErrors) session.getAttribute("errors");
		LoanForm loanForm = (LoanForm) session.getAttribute("form");
		
		
		if(actionErrors != null){
			//Save the errors in this action
			saveErrors(request, actionErrors);
		}
		
		if(loanForm != null){
			
			//Overwrite the attributes
			loan.setCustomerCardNumber(Integer.parseInt(loanForm.getCustomerCardNumber()));
			loan.setCopyId(Integer.parseInt(loanForm.getBookId()));
			
			if(Validator.isValidDate(loanForm.getStartDate())){
				loan.setStartDate(Converter.fromStringToDate(loanForm.getStartDate()));
			}
			
			if(Validator.isValidDate(loanForm.getEndDate())){
				loan.setEndDate(Converter.fromStringToDate(loanForm.getEndDate()));
			}
			
			if(loanForm.getReturned() != null){
				loan.setReturned(true);
			}
			
		}
		
		
		//Remove attributes from session
		session.removeAttribute("errors");
		session.removeAttribute("form");
		
		//Set the request
		request.setAttribute("loan", loan);
		request.setAttribute("books", books);
		request.setAttribute("customers", customers);
		
		actionTarget = "edit";
		return mapping.findForward(actionTarget);
	}

}
