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

import com.github.jcpp.jathenaeum.Copy;
import com.github.jcpp.jathenaeum.Loan;
import com.github.jcpp.jathenaeum.beans.LoanForm;
import com.github.jcpp.jathenaeum.db.dao.CopyDAO;
import com.github.jcpp.jathenaeum.db.dao.LoanDAO;

/**
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class LoanActionDo extends DispatchAction {
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		Loan loan = new Loan();
		LoanForm uf = (LoanForm) form;
		
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
			//Get a copy
			Copy copy = CopyDAO.getOneFreeByBookId(Integer.parseInt(uf.getBookId()));
			
			//Set the loan
			loan = new Loan(uf);
			loan.setCopyId(copy.getId());
			
			//Insert the loan
			LoanDAO.insert(loan);
			actionTarget = "addSuccess";
		}

		return mapping.findForward(actionTarget);
	}
	
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		Loan loan = new Loan();
		LoanForm uf = (LoanForm) form;
		
		//Check the id
		if(!uf.validateId(mapping, request).isEmpty()){
			actionTarget = "invalidId";
			
    		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
    		return redirect;
		}
		
		ActionErrors actionErrors = uf.validate(mapping, request);
		int id = Integer.parseInt(request.getParameter("id"));
		
		//If there are some errors, redirect to the form page
		if(!actionErrors.isEmpty()){
			actionTarget = "editErrors";
			saveErrors(request, actionErrors); //Save the errors
			
			HttpSession session = request.getSession();
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			redirect.addParameter("id", Integer.toString(id));
			return redirect;
		}

		if(form != null){
			//Get a copy
			Copy copy = CopyDAO.getOneFreeByBookId(Integer.parseInt(uf.getBookId()));
			
			//Set the loan
			loan = new Loan(uf);
			loan.setId(id);
			loan.setCopyId(copy.getId());
			
			//Update the loan
			LoanDAO.update(loan);
			actionTarget = "editSuccess";
		}

		return mapping.findForward(actionTarget);
	}
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		LoanForm loanForm = (LoanForm) form;
		
		//Check the id
		if(!loanForm.validateId(mapping, request).isEmpty()){
			actionTarget = "invalidId";
			
    		ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
    		return redirect;
		}
		
		
		int id = Integer.parseInt(request.getParameter("id"));

		if(form != null){
			try{
				LoanDAO.delete(id);
				actionTarget = "deleteSuccess";

			}catch(Exception e){
				actionTarget = "deleteFailed";
			}
		}

		return mapping.findForward(actionTarget);
	}
	
	
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		String actionTarget = null;
		
		LoanForm uf = (LoanForm) form;
		
		//ActionErrors actionErrors = uf.validate(mapping, request);
		
		//If there are some errors, redirect to the form page
		/*
		if(!actionErrors.isEmpty()){
			actionTarget = "deleteErrors";
			saveErrors(request, actionErrors); //Save the errors
			
			HttpSession session = request.getSession();
    		session.setAttribute("errors", actionErrors);
    		session.setAttribute("form", uf);
			
			ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
			redirect.addParameter("id", Integer.toString(id));
			return redirect;
		}
		*/
		
		ArrayList<Loan> loans = new ArrayList<Loan>();
		
		if(form != null){
			try{
				loans = LoanDAO.search(uf);
				
				actionTarget = "search";
				
				HttpSession session = request.getSession();
	    		session.setAttribute("loans", loans);
	    		session.setAttribute("form", uf);
				
				ActionRedirect redirect = new ActionRedirect(mapping.findForward(actionTarget));
				return redirect;

			}catch(Exception e){
				e.printStackTrace();
				actionTarget = "searchFailed";
			}
		}
		
		

		return mapping.findForward(actionTarget);
	}

}
