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

import com.github.jcpp.jathenaeum.Loan;
import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.LoanNotFoundException;
import com.github.jcpp.jathenaeum.utils.Converter;

/**
 * DAO of Loan.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class LoanDAO {
	
	private static Database db = Database.getInstance();
	
	/**
	 * Get all Loan instances.
	 * @return Returns all Loan instances in an ArrayList<Loan>.
	 */
	public static ArrayList<Loan> getAll(){
		Connection con = db.getConnection();
		ArrayList<Loan> loans = new ArrayList<Loan>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Loan";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			Loan loan;
			
			while(resultSet.next()){
				loan = new Loan();
				loan.setId(resultSet.getInt(1));
				loan.setCustomerCardNumber(resultSet.getInt(2));
				loan.setCopyId(resultSet.getInt(3));
				loan.setStartDate(resultSet.getDate(4));
				loan.setEndDate(resultSet.getDate(5));
				loan.setReturned(resultSet.getBoolean(6));
				loans.add(loan);
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
		return loans;
	}
	
	/**
	 * Get the Loan by id.
	 * @param id the id of the Loan.
	 * @return Returns the Loan instance.
	 * @throws LoanNotFoundException Throws an exception if the Loan is not found.
	 */
	public static Loan getById(int id) throws LoanNotFoundException{
		Connection con = db.getConnection();
		Loan loan;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Loan WHERE LoanID = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				loan = new Loan();
				loan.setId(resultSet.getInt(1));
				loan.setCustomerCardNumber(resultSet.getInt(2));
				loan.setCopyId(resultSet.getInt(3));
				loan.setStartDate(resultSet.getDate(4));
				loan.setEndDate(resultSet.getDate(5));
				loan.setReturned(resultSet.getBoolean(6));
			}
			else{
				throw new LoanNotFoundException();
			}
			
			return loan;
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
	 * Get all Loan instances of an User.
	 * @param customerCardNumber the CustomerCardNumber.
	 * @return Returns all Loan instances in an ArrayList<Loan> of an User.
	 */
	public static ArrayList<Loan> getAllByCustomerCardNumber(int customerCardNumber){
		Connection con = db.getConnection();
		ArrayList<Loan> loans = new ArrayList<Loan>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Loan WHERE CustomerCardNumber = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, customerCardNumber);
			ResultSet resultSet = stmt.executeQuery();
			Loan loan;
			
			while(resultSet.next()){
				loan = new Loan();
				loan.setId(resultSet.getInt(1));
				loan.setCustomerCardNumber(resultSet.getInt(2));
				loan.setCopyId(resultSet.getInt(3));
				loan.setStartDate(resultSet.getDate(4));
				loan.setEndDate(resultSet.getDate(5));
				loan.setReturned(resultSet.getBoolean(6));
				loans.add(loan);
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
		return loans;
	}
	
	
	/**
	 * Get all Loan instances of a Book.
	 * @param bookId the bookId.
	 * @return Returns all Loan instances in an ArrayList<Loan> of a Book.
	 */
	public static ArrayList<Loan> getAllByBookId(int bookId){
		Connection con = db.getConnection();
		ArrayList<Loan> loans = new ArrayList<Loan>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Loan WHERE BookID = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, bookId);
			ResultSet resultSet = stmt.executeQuery();
			Loan loan;
			
			while(resultSet.next()){
				loan = new Loan();
				loan.setId(resultSet.getInt(1));
				loan.setCustomerCardNumber(resultSet.getInt(2));
				loan.setCopyId(resultSet.getInt(3));
				loan.setStartDate(resultSet.getDate(4));
				loan.setEndDate(resultSet.getDate(5));
				loan.setReturned(resultSet.getBoolean(6));
				loans.add(loan);
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
		return loans;
	}
	
	
	/**
	 * Insert a new loan.
	 * @param loan the loan to insert. 
	 * @return Returns the id of the inserted loan. 
	 */
	public static long insert(Loan loan){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		long result = 0;

		try {
			con.setAutoCommit(false);
			String insert = "INSERT INTO Loan (CustomerCardNumber, CopyID, LoanStartDate, LoanEndDate, LoanReturned) VALUES (?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, loan.getCustomerCardNumber());
			stmt.setInt(2, loan.getCopyId());
			
			if(loan.getStartDate() == null){
				stmt.setNull(3, Types.DATE);
			}
			else{
				stmt.setDate(3, Converter.fromUtilDateToSqlDate(loan.getStartDate()));
			}
			
			if(loan.getEndDate() == null){
				stmt.setNull(4, Types.DATE);
			}
			else{
				stmt.setDate(4, Converter.fromUtilDateToSqlDate(loan.getEndDate()));
			}
			
			stmt.setBoolean(5, loan.isReturned());
			
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
	 * Update a loan.
	 * @param loan the loan to update. 
	 * @return Returns the id of the updated loan. 
	 */
	public static long update(Loan loan){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		long result = 0;

		try {
			con.setAutoCommit(false);
			String insert = "UPDATE Loan SET CustomerCardNumber = ?, CopyId = ?, LoanStartDate = ?, LoanEndDate = ?, LoanReturned = ? WHERE LoanID = ?";
			stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, loan.getCustomerCardNumber());
			stmt.setInt(2, loan.getCopyId());
			
			if(loan.getStartDate() == null){
				stmt.setNull(3, Types.DATE);
			}
			else{
				stmt.setDate(3, Converter.fromUtilDateToSqlDate(loan.getStartDate()));
			}
			
			if(loan.getEndDate() == null){
				stmt.setNull(4, Types.DATE);
			}
			else{
				stmt.setDate(4, Converter.fromUtilDateToSqlDate(loan.getEndDate()));
			}
			
			stmt.setBoolean(5, loan.isReturned());
			
			stmt.setInt(6, loan.getId());
			
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
		return loan.getId();
	}
	
	
	/**
	 * Delete a loan.
	 * @param id the id of the loan to delete. 
	 * @return Returns the id of the deleted loan. 
	 */
	public static long delete(int id){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		long result = 0;

		try {
			con.setAutoCommit(false);
			final String delete = "DELETE FROM Loan WHERE LoanID = ?";
			stmt = con.prepareStatement(delete);
			stmt.setInt(1, id);
			
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
		return id;
	}

}
