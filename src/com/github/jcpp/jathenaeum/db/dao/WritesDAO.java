/**
 * 
 */
package com.github.jcpp.jathenaeum.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.jcpp.jathenaeum.Author;
import com.github.jcpp.jathenaeum.Book;
import com.github.jcpp.jathenaeum.Writes;
import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.AuthorNotFoundException;
import com.github.jcpp.jathenaeum.exceptions.BookNotFoundException;
import com.github.jcpp.jathenaeum.exceptions.WriteNotFoundException;

/**
 * DAO of Writes.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class WritesDAO {
	
	private static Database db = Database.getInstance();
	
	
	/**
	 * Get all Writes instances.
	 * @return Returns all Writes instances in an ArrayList<Writes>.
	 */
	public static ArrayList<Writes> getAll(){
		Connection con = db.getConnection();
		ArrayList<Writes> writeArray = new ArrayList<Writes>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Writes";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			Writes writes;
			
			while(resultSet.next()){
				writes = new Writes();
				writes.setId(resultSet.getInt(1));
				writes.setBookId(resultSet.getInt(2));
				writes.setAuthorId(resultSet.getInt(3));
				writeArray.add(writes);
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
		return writeArray;
	}
	
	
	/**
	 * Get all Writes instances.
	 * @param bookId The Book ID.
	 * @return Returns all Writes instances of a Book in an ArrayList<Writes>.
	 */
	public static ArrayList<Writes> getAllByBookId(int bookId){
		Connection con = db.getConnection();
		ArrayList<Writes> writeArray = new ArrayList<Writes>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Writes WHERE BookID = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, bookId);
			ResultSet resultSet = stmt.executeQuery();
			Writes writes;
			
			while(resultSet.next()){
				writes = new Writes();
				writes.setId(resultSet.getInt(1));
				writes.setBookId(resultSet.getInt(2));
				writes.setAuthorId(resultSet.getInt(3));
				writeArray.add(writes);
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
		return writeArray;
	}
	
	
	/**
	 * Get the Writes by id.
	 * @param id the id of the Writes.
	 * @return Returns the Writes instance.
	 * @throws WriteNotFoundException Throws an exception if the Writes is not found.
	 */
	public static Writes getById(int id) throws WriteNotFoundException{
		Connection con = db.getConnection();
		Writes writes;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Writes WHERE WritesID = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				writes = new Writes();
				writes.setId(resultSet.getInt(1));
				writes.setBookId(resultSet.getInt(2));
				writes.setAuthorId(resultSet.getInt(3));
			}
			else{
				throw new WriteNotFoundException();
			}
			
			return writes;
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
	 * Insert a new Writes.
	 * @param writes the Writes object to insert. 
	 * @return
	 */
	public static boolean insert(Writes writes){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		boolean workIt = false;

		try {
			con.setAutoCommit(false);
			String insert = "INSERT INTO Writes (BookID, AuthorID)"
					+ " VALUES (?, ?)";
			stmt = con.prepareStatement(insert);
			stmt.setLong(1, writes.getBookId());
			stmt.setInt(2, writes.getAuthorId());
			
			int result = stmt.executeUpdate();
			con.commit();
			
			if(result == 1 || result== 0){
				workIt = true;
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
		return workIt;
	}
	
	
	/**
	 * Delete a Writes.
	 * @param writes the Writes object to delete. 
	 * @return
	 */
	public static boolean delete(Writes writes){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		boolean workIt = false;

		try {
			con.setAutoCommit(false);
			final String delete = "DELETE FROM Writes WHERE WritesID = ?";
			stmt = con.prepareStatement(delete);
			stmt.setInt(1, writes.getId());
			
			int result = stmt.executeUpdate();
			con.commit();
			
			if(result == 1 || result== 0){
				workIt = true;
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
		return workIt;
	}

}
