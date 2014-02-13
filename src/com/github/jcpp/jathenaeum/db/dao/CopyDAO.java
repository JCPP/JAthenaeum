/**
 * 
 */
package com.github.jcpp.jathenaeum.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.jcpp.jathenaeum.Copy;
import com.github.jcpp.jathenaeum.db.Database;

/**
 * DAO of Copy.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class CopyDAO {
	
	private static Database db = Database.getInstance();
	
	
	/**
	 * Get all Copy instances.
	 * @return Returns all Copy instances in an ArrayList<Copy>.
	 */
	public static ArrayList<Copy> getAll(){
		Connection con = db.getConnection();
		ArrayList<Copy> copies = new ArrayList<Copy>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Copy";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			Copy copy;
			
			while(resultSet.next()){
				copy = new Copy(resultSet);
				
				copies.add(copy);
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
		return copies;
	}
	
	
	/**
	 * Get one <b>free</b> Copy instance by the book id.
	 * @param bookId the book ID of the copy.
	 * @return Returns one <b>free</b> Copy instance.
	 */
	public static Copy getOneFreeByBookId(int bookId){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		Copy copy = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT C.* FROM Copy C "
					+ "LEFT OUTER JOIN Loan L ON (L.CopyID = C.CopyID) "
					+ "LEFT JOIN Loan L2 ON (L.LoanID < L2.LoanID AND L.CopyID = L2.CopyID) "
					+ "WHERE L2.LoanID IS NULL AND C.BookID = ? AND COALESCE(L.LoanReturned, TRUE) != FALSE";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, bookId);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				copy = new Copy(resultSet);
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
		return copy;
	}
	
	
	/**
	 * Get one Copy instance by the book id.
	 * @param bookId the book ID of the copy.
	 * @return Returns one Copy instance.
	 */
	public static Copy getOneByBookId(int bookId){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		Copy copy = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Copy WHERE BookID = ? LIMIT 1";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, bookId);
			ResultSet resultSet = stmt.executeQuery();
			
			
			if(resultSet.next()){
				copy = new Copy(resultSet);
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
		return copy;
	}
	
	
	/**
	 * Get all Copy instances by the book id.
	 * @param bookId the book ID of the copy.
	 * @return Returns all Copy instances in an ArrayList<Copy>.
	 */
	public static ArrayList<Copy> getAllByBookId(int bookId){
		Connection con = db.getConnection();
		ArrayList<Copy> copies = new ArrayList<Copy>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Copy WHERE BookID = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, bookId);
			ResultSet resultSet = stmt.executeQuery();
			Copy copy;
			
			while(resultSet.next()){
				copy = new Copy(resultSet);
				
				copies.add(copy);
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
		return copies;
	}
	
	
	/**
	 * Get the number of the copies from the Book ID.
	 * @param bookId the Book ID.
	 * @return Returns all the number of the copies by the Book ID.
	 */
	public static int getNumberByBookId(int bookId){
		Connection con = db.getConnection();
		int copyNumber = 0;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT COUNT(*) FROM Copy WHERE BookID = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, bookId);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				copyNumber = resultSet.getInt(1);
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
		return copyNumber;
	}
	
	
	/**
	 * Get the number of the <b>free</b> copies from the Book ID.
	 * @param bookId the Book ID.
	 * @return Returns all the number of the free copies by the Book ID.
	 */
	public static int getFreeNumberByBookId(int bookId){
		Connection con = db.getConnection();
		int copyNumber = 0;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT COUNT(*) FROM Copy C LEFT OUTER JOIN Loan L ON (L.CopyID = C.CopyID) " 
								  + "LEFT JOIN Loan L2 ON (L.LoanID < L2.LoanID AND L.CopyID = L2.CopyID) "
								  + "WHERE L2.LoanID IS NULL AND C.BookID = ? AND COALESCE(L.LoanReturned, TRUE) != FALSE";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, bookId);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				copyNumber = resultSet.getInt(1);
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
		return copyNumber;
	}
	
	
	/**
	 * Insert a new copy.
	 * @param bookId the book ID to insert. 
	 * @return Returns the result of the query. 
	 */
	public static int insert(int bookId){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			con.setAutoCommit(false);
			final String insert = "INSERT INTO Copy (BookID) VALUES (?)";
			
			stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
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
				if (stmt!=null) {
					stmt.close();
					stmt=null;
				}
				db.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	/**
	 * Insert number copies of the Book ID.
	 * @param bookId the Book ID.
	 * @param number the number of copies to add.
	 */
	public static void multipleInsert(int bookId, int number){
		for(int i = 0; i < number; i++){
			insert(bookId);
		}
	}
	
	
	/**
	 * Delete a copy.
	 * @param copyId the ID of the copy to delete. 
	 * @return Returns the id of the deleted copy.
	 */
	public static long delete(int copyId){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		long result = 0;

		try {
			con.setAutoCommit(false);
			final String delete = "DELETE FROM Copy WHERE CopyID = ?";
			stmt = con.prepareStatement(delete);
			stmt.setInt(1, copyId);
			
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
		return copyId;
	}
	
	
	/**
	 * Delete number copies of the Book ID.
	 * @param bookId the Book ID.
	 * @param number the number of copies to delete.
	 */
	public static void multipleDelete(int bookId, int number){
		ArrayList<Copy> copies = getAllByBookId(bookId);
		for(int i = 0; i < number; i++){
			delete(copies.get(i).getId());
		}
	}

}
