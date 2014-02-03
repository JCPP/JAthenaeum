/**
 * 
 */
package com.github.jcpp.jathenaeum.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.jcpp.jathenaeum.Customer;
import com.github.jcpp.jathenaeum.db.Database;

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

}
