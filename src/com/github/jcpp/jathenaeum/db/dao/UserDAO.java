/**
 * 
 */
package com.github.jcpp.jathenaeum.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.github.jcpp.jathenaeum.User;
import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.LoginException;
import com.github.jcpp.jathenaeum.exceptions.RegistrationException;
import com.github.jcpp.jathenaeum.exceptions.UserNotFound;
import com.github.jcpp.jathenaeum.utils.Converter;

/**
 * DAO of User.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class UserDAO {
	
	private static Database db = Database.getInstance();
	
	/**
	 * Try to login.
	 * @param email the email.
	 * @param password the password.
	 * @return Return an instance of User if the login is successful.
	 * @throws LoginException Throws an exception if the login is failed.
	 */
	public static User login(String email, String password) throws LoginException {
		Connection con = db.getConnection();
		User user;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			String select = "SELECT * FROM User WHERE UserEmail = ? AND UserPassword = ?";
			stmt = con.prepareStatement(select);
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setEmail(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setName(resultSet.getString(4));
				user.setSurname(resultSet.getString(5));
				user.setBornDate(resultSet.getDate(6));
			}
			else{
				throw new LoginException();
			}
			
			return user;
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
	 * Get a User by id.
	 * @param id the id.
	 * @return A User instance.
	 * @throws UserNotFound If the user is not found.
	 */
	public static User getByCardNumber(int id) throws UserNotFound{
		Connection con = db.getConnection();
		User user;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			String select = "SELECT * FROM User WHERE UserID = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setEmail(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setName(resultSet.getString(4));
				user.setSurname(resultSet.getString(5));
				user.setBornDate(resultSet.getDate(6));
			}
			else{
				throw new UserNotFound();
			}
			
			return user;
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
	 * Try to register.
	 * @param email The User email.
	 * @return a boolean value to confirm the presence
	 */
	public static boolean exists(String email){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;

		try {
			con.setAutoCommit(false);
			String select = "SELECT * FROM User WHERE UserEmail = ?";
			stmt = con.prepareStatement(select);
			stmt.setString(1, email);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();

			return resultSet.next();
			
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
		return false;
	}
	
	
	
	/**
	 * Try to register.
	 * @param user The user
	 * @return a boolean value to confirm the insert
	 * @throws RegistrationException 
	 */
	public static boolean register(User user) throws RegistrationException{
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		boolean workIt = false;

		try {
			con.setAutoCommit(false);
			String insert = "INSERT INTO User (UserEmail, UserPassword, UserName, UserSurname, UserBornDate)"
					+ " VALUES (?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(insert);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getName());
			stmt.setString(4, user.getSurname());
			
			if(user.getBornDate() == null){
				stmt.setNull(5, Types.DATE);
			}
			else{
				stmt.setDate(5, Converter.fromUtilDateToSqlDate(user.getBornDate()));
			}
			
			System.out.println(stmt);
			
			int result = stmt.executeUpdate();
			con.commit();
			
			if(result == 1 || result == 0){
				
				workIt = true;
			}
			else{
				throw new RegistrationException();
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
