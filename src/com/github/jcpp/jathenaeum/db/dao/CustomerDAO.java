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

import com.github.jcpp.jathenaeum.Customer;
import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.CustomerNotFoundException;

/**
 * DAO of Customer.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class CustomerDAO {
	
private static Database db = Database.getInstance();
	
	/**
	 * Get all Customer instances.
	 * @return Returns all Customer instances in an ArrayList<Customer>.
	 */
	public static ArrayList<Customer> getAll(){
		Connection con = db.getConnection();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Customer";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			Customer customer;
			
			while(resultSet.next()){
				customer = new Customer(resultSet);
				customers.add(customer);
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
		return customers;
	}
	
	/**
	 * Get the Customer by id.
	 * @param cardNumber the card number of the Customer.
	 * @return Returns the Customer instance.
	 * @throws CustomerNotFoundException Throws an exception if the Customer is not found.
	 */
	public static Customer getById(int cardNumber) throws CustomerNotFoundException{
		Connection con = db.getConnection();
		Customer customer;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Customer WHERE CustomerCardNumber = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, cardNumber);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				customer = new Customer(resultSet);
			}
			else{
				throw new CustomerNotFoundException();
			}
			
			return customer;
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
	 * Returns true if it finds the customer, false otherwise.
	 * @param cardNumber the card number of the Customer.
	 * @return Returns true it if finds the customer, false otherwise.
	 */
	public static boolean exists(int cardNumber){
		Connection con = db.getConnection();
		boolean result = false;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Customer WHERE CustomerCardNumber = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, cardNumber);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				result = true;
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
		return result;
	}
	
	
	/**
	 * Insert a new customer.
	 * @param customer the customer to insert.
	 * @return Returns the id of the inserted customer. 
	 */
	public static long insert(Customer customer){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		long result = 0;

		try {
			con.setAutoCommit(false);
			final String insert = "INSERT INTO Customer (CustomerEmail, CustomerName, CustomerSurname) VALUES (?, ?, ?)";
			stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, customer.getEmail());
			stmt.setString(2, customer.getName());
			stmt.setString(3, customer.getSurname());
			
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
	 * Get a customer by the email.
	 * @param email the email.
	 * @return Returns a customer by the email.
	 * @throws CustomerNotFoundException Throws a CustomerNotFoundException if the Customer is not found.
	 */
	public static Customer getByEmail(String email) throws CustomerNotFoundException{
		Connection con = db.getConnection();
		Customer customer = null;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Customer WHERE CustomerEmail = ?";
			stmt = con.prepareStatement(select);
			stmt.setString(1, email);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				customer = new Customer(resultSet);
			}
			else{
				throw new CustomerNotFoundException();
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
		return customer;
	}
	
	
	/**
	 * Get the number of the customers by the email.
	 * @param email the email.
	 * @return Returns all the number of the customers by the email.
	 */
	public static int getNumberByEmail(String email){
		Connection con = db.getConnection();
		int customerNumber = 0;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT COUNT(*) FROM Customer WHERE CustomerEmail = ?";
			stmt = con.prepareStatement(select);
			stmt.setString(1, email);
			ResultSet resultSet = stmt.executeQuery();
			
			if(resultSet.next()){
				customerNumber = resultSet.getInt(1);
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
		return customerNumber;
	}
	
	
	/**
	 * Update a customer.
	 * @param customer the customer to update. 
	 * @return Returns the id of the updated customer. 
	 */
	public static long update(Customer customer){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		long result = 0;

		try {
			con.setAutoCommit(false);
			String insert = "UPDATE Customer SET CustomerName = ?, CustomerSurname = ?, CustomerEmail = ? WHERE CustomerCardNumber = ?";
			stmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getSurname());
			stmt.setString(3, customer.getEmail());
			stmt.setInt(4, customer.getCardNumber());

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
		return customer.getCardNumber();
	}
	
	
	/**
	 * Delete a customer.
	 * @param cardNumber the card number of the customer to delete. 
	 * @return Returns the id of the deleted customer. 
	 */
	public static long delete(int cardNumber){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		long result = 0;

		try {
			con.setAutoCommit(false);
			final String delete = "DELETE FROM Customer WHERE CustomerCardNumber = ?";
			stmt = con.prepareStatement(delete);
			stmt.setInt(1, cardNumber);
			
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
		return cardNumber;
	}

}
