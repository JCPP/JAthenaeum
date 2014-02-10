/**
 * 
 */
package com.github.jcpp.jathenaeum;

import java.util.Date;

/**
 * Loan class.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Loan {
	
	private int id;
	private int customerCardNumber;
	private int copyId;
	private Date startDate;
	private Date endDate;
	private boolean returned;
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the userCardNumber
	 */
	public int getCustomerCardNumber() {
		return customerCardNumber;
	}
	/**
	 * @param customerCardNumber the customerCardNumber to set
	 */
	public void setCustomerCardNumber(int customerCardNumber) {
		this.customerCardNumber = customerCardNumber;
	}
	/**
	 * @return the copyId
	 */
	public int getCopyId() {
		return copyId;
	}
	/**
	 * @param copyId the copyId to set
	 */
	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the returned
	 */
	public boolean isReturned() {
		return returned;
	}
	/**
	 * @param returned the returned to set
	 */
	public void setReturned(boolean returned) {
		this.returned = returned;
	}

}
