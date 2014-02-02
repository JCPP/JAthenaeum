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
	private int userCardNumber;
	private int bookId;
	private Date startDate;
	private Date endDate;
	
	
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
	public int getUserCardNumber() {
		return userCardNumber;
	}
	/**
	 * @param userCardNumber the userCardNumber to set
	 */
	public void setUserCardNumber(int userCardNumber) {
		this.userCardNumber = userCardNumber;
	}
	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
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
	public Date getDataFine() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
