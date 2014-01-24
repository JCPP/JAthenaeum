/**
 * 
 */
package com.github.jcpp.jathenaeum.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.LoginException;
import com.github.jcpp.jathenaeum.Utente;

/**
 * DAO of Utente.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class UtenteDAO {
	
	private Database db = Database.getInstance();
	
	/**
	 * Try to login.
	 * @param email the email.
	 * @param password the password.
	 * @return Return an instance of Utente if the login is successful.
	 * @throws LoginException Throws an exception if the login is failed.
	 */
	public Utente login(String email, String password) throws LoginException {
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
				utente.setId(resultSet.getInt(1));
				utente.setEmail(resultSet.getString(2));
				utente.setPassword(resultSet.getString(3));
				utente.setNome(resultSet.getString(4));
				utente.setCognome(resultSet.getString(5));
				utente.setDataNascita(resultSet.getDate(6));
				utente.setNumeroTessera(resultSet.getInt(7));
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

}
