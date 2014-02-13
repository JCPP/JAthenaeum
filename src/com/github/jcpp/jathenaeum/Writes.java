/**
 * 
 */
package com.github.jcpp.jathenaeum;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Write class.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Writes {
	
	private int id;
	private long bookId;
	private int authorId;
	
	/**
	 * Create a Writes instance.
	 */
	public Writes(){
		
	}
	
	
	/**
	 * Create a Writes instance from a ResultSet.
	 * @param resultSet
	 * @throws SQLException 
	 */
	public Writes(ResultSet resultSet) throws SQLException {
		id = resultSet.getInt(1);
		bookId = resultSet.getInt(2);
		authorId = resultSet.getInt(3);
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
	public long getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the authorId
	 */
	public int getAuthorId() {
		return authorId;
	}
	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

}
