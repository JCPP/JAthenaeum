/**
 * 
 */
package com.github.jcpp.jathenaeum.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.LoginException;
import com.github.jcpp.jathenaeum.exceptions.RegistrationException;
import com.github.jcpp.jathenaeum.exceptions.UtenteNotFound;
import com.github.jcpp.jathenaeum.Utente;

/**
 * DAO of Utente.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class UtenteDAO {
	
	private static Database db = Database.getInstance();
	
	/**
	 * Try to login.
	 * @param email the email.
	 * @param password the password.
	 * @return Return an instance of Utente if the login is successful.
	 * @throws LoginException Throws an exception if the login is failed.
	 */
	public static Utente login(String email, String password) throws LoginException {
		Connection con = db.getConnection();
		Utente utente;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			String select = "SELECT * FROM Utente WHERE EmailUtente = ? AND PasswordUtente = ?)";
			stmt = con.prepareStatement(select);
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				utente = new Utente();
				utente.setNumeroTessera(resultSet.getInt(1));
				utente.setEmail(resultSet.getString(2));
				utente.setPassword(resultSet.getString(3));
				utente.setNome(resultSet.getString(4));
				utente.setCognome(resultSet.getString(5));
				utente.setDataNascita(resultSet.getDate(6));
			}
			else{
				throw new LoginException();
			}
			
			return utente;
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
	 * Get a Utente by Numero Tessera.
	 * @param numeroTesseraUtente the numeroTessera.
	 * @return A Utente instance.
	 * @throws UtenteNotFound If the user is not found.
	 */
	public static Utente getByNumeroTessera(int numeroTesseraUtente) throws UtenteNotFound{
		Connection con = db.getConnection();
		Utente utente;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			String select = "SELECT * FROM Utente WHERE NumeroTesseraUtente = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, numeroTesseraUtente);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				utente = new Utente();
				utente.setNumeroTessera(resultSet.getInt(1));
				utente.setEmail(resultSet.getString(2));
				utente.setPassword(resultSet.getString(3));
				utente.setNome(resultSet.getString(4));
				utente.setCognome(resultSet.getString(5));
				utente.setDataNascita(resultSet.getDate(6));
			}
			else{
				throw new UtenteNotFound();
			}
			
			return utente;
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
	 * @param email The Utente email.
	 * @return a boolean value to confirm the presence
	 */
	public static boolean exists(String email){
		Connection con = db.getConnection();
		PreparedStatement stmt = null;

		try {
			con.setAutoCommit(false);
			String select = "SELECT * FROM Utente WHERE EmailUtente = ?";
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
	public static boolean register(Utente user) throws RegistrationException{
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		boolean workIt = false;

		try {
			con.setAutoCommit(false);
			String insert = "INSERT INTO Utente (EmailUtente, PasswordUtente, NomeUtente, CognomeUtente, DataNascitaUtente, NumeroTesseraUtente)"
					+ "VALUES (?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(insert);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getNome());
			stmt.setString(4, user.getCognome());
			stmt.setDate(5, new Date(user.getDataNascita().getTime()));
			ResultSet resultSet = stmt.executeQuery();
			con.commit();

			if(resultSet.next()){
				workIt = true;
			}
			else{
				throw new RegistrationException();
			}

			return workIt;
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
