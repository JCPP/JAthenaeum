/**
 * 
 */
package com.github.jcpp.jathenaeum.db.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.github.jcpp.jathenaeum.Book;
import com.github.jcpp.jathenaeum.Writes;
import com.github.jcpp.jathenaeum.beans.BookForm;
import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.BookNotFoundException;
import com.github.jcpp.jathenaeum.utils.Converter;

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
	 * Get all Book instances with the associated authors.
	 * @return Returns all Book instances in an ArrayList<Book> with all the authors.
	 */
	public static ArrayList<Book> getAllWithAuthors(){
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
				book.setAuthors(AuthorDAO.getAllByLibroId(book.getId()));
				
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
	 * Get all Book instances with at least one copy.
	 * @return Returns all Book instances in an ArrayList<Book> with at least one copy.
	 */
	public static ArrayList<Book> getAllWithAtLeastOneCopy(){
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
				int bookId = resultSet.getInt(1);
				int numberOfCopies = CopyDAO.getNumberByBookId(bookId);
				
				if(numberOfCopies > 0){
					book = new Book();
					book.setId(bookId);
					book.setTitle(resultSet.getString(2));
					book.setCover(resultSet.getString(3));
					book.setGenre(resultSet.getString(4));
					book.setIsbnCode(resultSet.getString(5));
					book.setDescription(resultSet.getString(6));
					book.setNumberOfCopies(numberOfCopies);
					books.add(book);
				}
				
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
	 * Get all Book instances with at least one <b>free</b> copy.
	 * @return Returns all Book instances in an ArrayList<Book> with at least one <b>free</b> copy.
	 */
	public static ArrayList<Book> getAllWithAtLeastOneFreeCopy(){
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
				int bookId = resultSet.getInt(1);
				int numberOfCopies = CopyDAO.getFreeNumberByBookId(bookId);
				
				System.out.printf("Free copy for %d: %d\n", bookId, numberOfCopies);
				
				if(numberOfCopies > 0){
					book = new Book();
					book.setId(bookId);
					book.setTitle(resultSet.getString(2));
					book.setCover(resultSet.getString(3));
					book.setGenre(resultSet.getString(4));
					book.setIsbnCode(resultSet.getString(5));
					book.setDescription(resultSet.getString(6));
					book.setNumberOfCopies(numberOfCopies);
					books.add(book);
				}
				
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
	 * Get all Book instances with the associated authors and number of copies.
	 * @return Returns all Book instances in an ArrayList<Book> with all the authors and number of copies.
	 */
	public static ArrayList<Book> getAllWithAuthorsAndCopies(){
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
				book.setAuthors(AuthorDAO.getAllByLibroId(book.getId()));
				book.setNumberOfCopies(CopyDAO.getNumberByBookId(book.getId()));
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
	 * Get the number of the Book instances
	 * @return Returns the number of the Book instances.
	 */
	public static int getNumber(){
		Connection con = db.getConnection();
		int number = 0;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT COUNT(*) FROM Book";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
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
			resultSet.next();
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
	
	
	/**
	 * Update a book.
	 * @param book the book to update. 
	 * @return Returns the id of the updated book. 
	 */
	public static long update(Book book){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		long result = 0;

		try {
			con.setAutoCommit(false);
			String insert = "UPDATE Book SET BookTitle = ?, BookCover = ?, BookGenre = ?, BookIsbnCode = ?, BookDescription = ? "
					+ " WHERE BookID = ?";
			stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getCover());
			stmt.setString(3, book.getGenre());
			stmt.setString(4, book.getIsbnCode());
			stmt.setString(5, book.getDescription());
			stmt.setInt(6, book.getId());
			
			result = stmt.executeUpdate();
			
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
		return book.getId();
	}
	
	
	/**
	 * Delete a book.
	 * @param bookId the ID of the book to delete. 
	 * @return Returns the id of the deleted book. 
	 */
	public static long delete(int bookId){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		long result = 0;

		try {
			con.setAutoCommit(false);
			final String delete = "DELETE FROM Book WHERE BookID = ?";
			stmt = con.prepareStatement(delete);
			stmt.setInt(1, bookId);
			
			result = stmt.executeUpdate();
			
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
		return bookId;
	}
	
	
	/**
	 * Delete all the books that are orphans of authors.
	 */
	public static void deleteAllOrphansByAuthorId(){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		long result = 0;

		try {
			con.setAutoCommit(false);
			
			final String select = "SELECT B.BookID FROM Book B WHERE NOT EXISTS (SELECT * FROM Writes W WHERE W.BookID = B.BookID)";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			//Delete all the orphans books
			if(resultSet.next()){
				BookDAO.delete(resultSet.getInt(1));
			}

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
	}
	
	
	/**
	 * Search all the books that has the form fields.
	 * @param bookForm the form with all the fields.
	 * @return Returns an ArrayList<Book> with all the found books.
	 */
	public static ArrayList<Book> searchWithAuthors(BookForm bookForm){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		ArrayList<Book> books = new ArrayList<Book>();

		try {
			con.setAutoCommit(false);
			
			//final String select = "SELECT B.* FROM Book B, Writes W, Author A WHERE (B.BookTitle = ? OR ? = '') AND (B.BookCover = ? OR ? = '') AND (B.BookGenre = ? OR ? = '') AND (B.BookIsbnCode = ? OR ? = '') AND B.BookID = W.BookID AND A.AuthorID = W.AuthorID";
			final String select = "SELECT B.* FROM Book B WHERE (B.BookTitle = ? OR ? = '') AND (B.BookCover = ? OR ? = '') AND (B.BookGenre = ? OR ? = '') AND (B.BookIsbnCode = ? OR ? = '')";
			
			stmt = con.prepareStatement(select);
			
			//Title
			stmt.setString(1, bookForm.getTitle());
			stmt.setString(2, bookForm.getTitle());
			
			//Cover
			stmt.setString(3, bookForm.getCover());
			stmt.setString(4, bookForm.getCover());
			
			//Genre
			stmt.setString(5, bookForm.getGenre());
			stmt.setString(6, bookForm.getGenre());
			
			//Isbn
			stmt.setString(7, bookForm.getIsbn());
			stmt.setString(8, bookForm.getIsbn());
			
			//Authors
			//Integer[] arrayAuthors = Converter.fromStringArrayToIntegerArray(bookForm.getAuthors());
			//Array sqlArray = con.createArrayOf("INTEGER", arrayAuthors);
			
			//stmt.setArray(9, sqlArray);
			//stmt.setArray(10, sqlArray);
			
			System.out.println("Query: " + stmt.toString());
			
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			Book book;
			
			while(resultSet.next()){
				book = new Book();
				book.setId(resultSet.getInt(1));
				book.setTitle(resultSet.getString(2));
				book.setCover(resultSet.getString(3));
				book.setGenre(resultSet.getString(4));
				book.setIsbnCode(resultSet.getString(5));
				book.setDescription(resultSet.getString(6));
				book.setAuthors(AuthorDAO.getAllByLibroId(book.getId()));
				
				books.add(book);
				
				System.out.println("Found: " + book.getTitle());
			}

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
		
		return books;
	}

}
