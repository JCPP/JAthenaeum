/**
 * 
 */
package com.github.jcpp.jathenaeum.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.jcpp.jathenaeum.Loan;
import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.LoanNotFoundException;

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
				loan.setUserCardNumber(resultSet.getInt(2));
				loan.setBookId(resultSet.getInt(3));
				loan.setStartDate(resultSet.getDate(4));
				loan.setEndDate(resultSet.getDate(5));
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
				loan.setUserCardNumber(resultSet.getInt(2));
				loan.setBookId(resultSet.getInt(3));
				loan.setStartDate(resultSet.getDate(4));
				loan.setEndDate(resultSet.getDate(5));
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
	 * @param userCardNumber the userCardNumber.
	 * @return Returns all Loan instances in an ArrayList<Loan> of an User.
	 */
	public static ArrayList<Loan> getAllByIdUtente(int userCardNumber){
		Connection con = db.getConnection();
		ArrayList<Loan> loans = new ArrayList<Loan>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Loan WHERE UserCardNumber = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, userCardNumber);
			ResultSet resultSet = stmt.executeQuery();
			Loan loan;
			
			while(resultSet.next()){
				loan = new Loan();
				loan.setId(resultSet.getInt(1));
				loan.setUserCardNumber(resultSet.getInt(2));
				loan.setBookId(resultSet.getInt(3));
				loan.setStartDate(resultSet.getDate(4));
				loan.setEndDate(resultSet.getDate(5));
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
	 * Get all Loan instances of an Book.
	 * @param bookId the bookId.
	 * @return Returns all Loan instances in an ArrayList<Loan> of an Book.
	 */
	public static ArrayList<Loan> getAllByIdLibro(int bookId){
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
				loan.setUserCardNumber(resultSet.getInt(2));
				loan.setBookId(resultSet.getInt(3));
				loan.setStartDate(resultSet.getDate(4));
				loan.setEndDate(resultSet.getDate(5));
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

}
