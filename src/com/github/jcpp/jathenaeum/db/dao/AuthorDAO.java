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

import com.github.jcpp.jathenaeum.Author;
import com.github.jcpp.jathenaeum.Book;
import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.AuthorNotFoundException;
import com.github.jcpp.jathenaeum.exceptions.RegistrationException;
import com.github.jcpp.jathenaeum.utils.Converter;

/**
 * DAO of Author.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class AuthorDAO {
	
	private static Database db = Database.getInstance();
	
	/**
	 * Get all Author instances.
	 * @return Returns all Author instances in an ArrayList<Author>.
	 */
	public static ArrayList<Author> getAll(){
		Connection con = db.getConnection();
		ArrayList<Author> authors = new ArrayList<Author>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Author";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			Author author;
			
			while(resultSet.next()){
				author = new Author();
				author.setId(resultSet.getInt(1));
				author.setName(resultSet.getString(2));
				author.setSurname(resultSet.getString(3));
				author.setPhoto(resultSet.getString(4));
				author.setBornDate(resultSet.getDate(5));
				author.setBiography(resultSet.getString(6));
				authors.add(author);
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
		return authors;
	}
	
	
	/**
	 * Get the Author by id.
	 * @param id the id of the Author.
	 * @return Returns the Author instance.
	 * @throws AuthorNotFoundException Throws an exception if the Author is not found.
	 */
	public static Author getById(int id) throws AuthorNotFoundException{
		Connection con = db.getConnection();
		Author author;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Author WHERE AuthorID = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				author = new Author();
				author.setId(resultSet.getInt(1));
				author.setName(resultSet.getString(2));
				author.setSurname(resultSet.getString(3));
				author.setPhoto(resultSet.getString(4));
				author.setBornDate(resultSet.getDate(5));
				author.setBiography(resultSet.getString(6));
			}
			else{
				throw new AuthorNotFoundException();
			}
			
			return author;
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
	 * Get all Author by bookId.
	 * @param bookId the bookId.
	 * @return Returns the Author instances who write the bookId in an ArrayList<Author>.
	 */
	public static ArrayList<Author> getAllByLibroId(int bookId){
		Connection con = db.getConnection();
		ArrayList<Author> authors = new ArrayList<Author>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT A.* FROM Author A, Book B, Writes W WHERE B.BookID = ? AND B.BookID = W.BookID AND W.AuthorID = A.AuthorID";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, bookId);
			ResultSet resultSet = stmt.executeQuery();
			Author author;
			
			while(resultSet.next()){
				author = new Author();
				author.setId(resultSet.getInt(1));
				author.setName(resultSet.getString(2));
				author.setSurname(resultSet.getString(3));
				author.setPhoto(resultSet.getString(4));
				author.setBornDate(resultSet.getDate(5));
				author.setBiography(resultSet.getString(6));
				authors.add(author);
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
		return authors;
	}

	/**
	 * Insert a new author.
	 * @param author the author to insert.
	 * @return Returns the id of the inserted author. 
	 */
	public static long insert(Author author){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		long result = 0;

		try {
			con.setAutoCommit(false);
			final String insert = "INSERT INTO Author (AuthorName, AuthorSurname, AuthorPhoto, AuthorBornDate, AuthorBiography)"
					+ " VALUES (?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, author.getName());
			stmt.setString(2, author.getSurname());
			stmt.setString(3, author.getPhoto());
			
			if(author.getBornDate() == null){
				stmt.setNull(4, Types.DATE);
			}
			else{
				stmt.setDate(4, Converter.fromUtilDateToSqlDate(author.getBornDate()));
			}
			
			stmt.setString(5, author.getBiography());
			
			
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
	 * Update an author.
	 * @param author the author to update. 
	 * @return Returns the id of the updated author. 
	 */
	public static long update(Author author){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		long result = 0;

		try {
			con.setAutoCommit(false);
			String insert = "UPDATE Author SET AuthorName = ?, AuthorSurname = ?, AuthorPhoto = ?, AuthorBornDate = ?, AuthorBiography = ? "
					+ " WHERE AuthorID = ?";
			stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, author.getName());
			stmt.setString(2, author.getSurname());
			stmt.setString(3, author.getPhoto());
			
			if(author.getBornDate() == null){
				stmt.setNull(4, Types.DATE);
			}
			else{
				stmt.setDate(4, Converter.fromUtilDateToSqlDate(author.getBornDate()));
			}
			
			stmt.setString(5, author.getBiography());
			stmt.setInt(6, author.getId());
			
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
		return author.getId();
	}

}
