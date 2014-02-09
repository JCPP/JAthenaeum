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
import com.github.jcpp.jathenaeum.Customer;
import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.utils.Converter;

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
				customer = new Customer();
				customer.setCardNumber(resultSet.getInt(1));
				customer.setEmail(resultSet.getString(2));
				customer.setName(resultSet.getString(3));
				customer.setSurname(resultSet.getString(4));
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

}
