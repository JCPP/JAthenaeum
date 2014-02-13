/**
 * 
 */
package com.github.jcpp.jathenaeum.beans;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.github.jcpp.jathenaeum.utils.Converter;
import com.github.jcpp.jathenaeum.utils.Validator;

/**
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class LoanForm extends ActionForm {
	
	private String customerCardNumber;
	private String bookId;
	private String startDate;
	private String endDate;
	private String returned;
	
	/**
	 * @return the customerCardNumber
	 */
	public String getCustomerCardNumber() {
		return customerCardNumber;
	}
	/**
	 * @param customerCardNumber the customerCardNumber to set
	 */
	public void setCustomerCardNumber(String customerCardNumber) {
		this.customerCardNumber = customerCardNumber;
	}
	/**
	 * @return the bookId
	 */
	public String getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the returned
	 */
	public String getReturned() {
		return returned;
	}
	/**
	 * @param returned the returned to set
	 */
	public void setReturned(String returned) {
		this.returned = returned;
	}
	
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		
		/**/
		System.out.println("customer card number: " + customerCardNumber);
		System.out.println("book id: " + bookId);
		System.out.println("start date: " + startDate);
		System.out.println("end date: " + endDate);
		System.out.println("returned: " + returned);
		
		
		//Check customer card number
		if(customerCardNumber == null || customerCardNumber.isEmpty()){
			errors.add("customerCardNumber", new ActionMessage("loan.customercardnumber.empty"));
		}
		
		//Check book id
		if(bookId == null || bookId.isEmpty()){
			errors.add("bookId", new ActionMessage("loan.customerbookid.empty"));
		}
		
		//Check start date
		if(!Validator.isValidDate(startDate)){
			errors.add("startDate", new ActionMessage("loan.startdate.invalid"));
		}
		
		//Check end date
		if(!Validator.isValidDate(endDate)){
			errors.add("endDate", new ActionMessage("loan.enddate.invalid"));
		}
		
		//Check if start date is greater than end date
		if(Validator.isValidDate(startDate) && Validator.isValidDate(endDate)){
			try {
				Date sqlStartDate = Converter.fromStringToDate(startDate);
				Date sqlEndDate = Converter.fromStringToDate(endDate);
				
				if(!sqlStartDate.before(sqlEndDate)){
					errors.add("startDate", new ActionMessage("loan.startdate.greater"));
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
		return errors;
	}

}
