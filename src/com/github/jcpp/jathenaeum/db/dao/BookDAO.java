/**
 * 
 */
package com.github.jcpp.jathenaeum.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.github.jcpp.jathenaeum.Book;
import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.BookNotFoundException;

/**
 * DAO of Book.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class BookDAO {
	
	private static Database db = Database.getInstance();
	
	/**
	 * Get all Book instances.
	 * @return Returns all Book instances in an ArrayList<Book>.
	 */
	public static ArrayList<Book> getAll(){
		Connection con = db.getConnection();
		ArrayList<Book> books = new ArrayList<Book>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Book";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			Book book;
			
			while(resultSet.next()){
				book = new Book();
				book.setId(resultSet.getInt(1));
				book.setTitle(resultSet.getString(2));
				book.setCover(resultSet.getString(3));
				book.setGenre(resultSet.getString(4));
				book.setIsbnCode(resultSet.getString(5));
				book.setDescription(resultSet.getString(6));
				books.add(book);
			}
			
			con.commit();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (stmt!=null) {
					stmt.close();
					stmt=null;
				}
				db.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return books;
	}
	
	/**
	 * Get the Book by id.
	 * @param bookId the id of the Book.
	 * @return Returns the Book instance.
	 * @throws BookNotFoundException Throws an exception if the Book is not found.
	 */
	public static Book getById(int bookId) throws BookNotFoundException{
		Connection con = db.getConnection();
		Book book;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Book WHERE BookID = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, bookId);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				book = new Book();
				book.setId(resultSet.getInt(1));
				book.setTitle(resultSet.getString(2));
				book.setCover(resultSet.getString(3));
				book.setGenre(resultSet.getString(4));
				book.setIsbnCode(resultSet.getString(5));
				book.setDescription(resultSet.getString(6));
			}
			else{
				throw new BookNotFoundException();
			}
			
			return book;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				db.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Get the number of the Book instances.
	 * @param isbn the isbn code.
	 * @return Returns the number of the Book instances.
	 */
	public static int getNumberByIsbn(String isbn){
		Connection con = db.getConnection();
		int number = 0;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT COUNT(*) FROM Book WHERE BookIsbnCode = ?";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			
			number = resultSet.getInt(1);
			
			con.commit();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (stmt!=null) {
					stmt.close();
					stmt=null;
				}
				db.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return number;
	}
	
	/**
	 * Insert a new book.
	 * @param book the book to insert. 
	 * @return Returns the id of the inserted book. 
	 */
	public static long insert(Book book){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		long result = 0;

		try {
			con.setAutoCommit(false);
			String insert = "INSERT INTO Book (BookTitle, BookCover, BookGenre, BookIsbnCode, BookDescription)"
					+ " VALUES (?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getCover());
			stmt.setString(3, book.getGenre());
			stmt.setString(4, book.getIsbnCode());
			stmt.setString(5, book.getDescription());
			
			result = stmt.executeUpdate();
			
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			
			if (generatedKeys.next()) {
	            result = generatedKeys.getLong(1);
	        }
			
			con.commit();
			

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				db.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
