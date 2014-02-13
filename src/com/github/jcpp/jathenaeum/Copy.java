/**
 * 
 */
package com.github.jcpp.jathenaeum;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Copy {
	
	private int id;
	private int bookId;
	
	/**
	 * Create a Copy instance.
	 */
	public Copy(){
		
	}
	
	/**
	 * Create a Copy instance from a ResultSet.
	 * @param resultSet
	 * @throws SQLException 
	 */
	public Copy(ResultSet resultSet) throws SQLException {
		id = resultSet.getInt(1);
		bookId = resultSet.getInt(2);
	}
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
	
	

}
