/**
 * 
 */
package com.github.jcpp.jathenaeum.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.jcpp.jathenaeum.Autore;
import com.github.jcpp.jathenaeum.Scrivere;
import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.AutoreNotFoundException;
import com.github.jcpp.jathenaeum.exceptions.LibroNotFoundException;
import com.github.jcpp.jathenaeum.exceptions.ScrivereNotFoundException;

/**
 * DAO of Scrivere.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class ScrivereDAO {
	
	private Database db = Database.getInstance();
	
	
	/**
	 * Get all Scrivere instances.
	 * @return Returns all Scrivere instances in an ArrayList<Scrivere>.
	 */
	public ArrayList<Scrivere> getAll(){
		Connection con = db.getConnection();
		ArrayList<Scrivere> scrivereArray = new ArrayList<Scrivere>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Scrivere";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			Scrivere scrivere;
			
			while(resultSet.next()){
				scrivere = new Scrivere();
				scrivere.setId(resultSet.getInt(1));
				scrivere.setIdLibro(resultSet.getInt(2));
				scrivere.setIdAutore(resultSet.getInt(3));
				scrivereArray.add(scrivere);
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
		return scrivereArray;
	}
	
	
	/**
	 * Get the Scrivere by id.
	 * @param id the id of the Scrivere.
	 * @return Returns the Scrivere instance.
	 * @throws ScrivereNotFoundException Throws an exception if the Scrivere is not found.
	 */
	public Scrivere getById(int id) throws ScrivereNotFoundException{
		Connection con = db.getConnection();
		Scrivere scrivere;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Scrivere WHERE IDScrivere = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				scrivere = new Scrivere();
				scrivere.setId(resultSet.getInt(1));
				scrivere.setIdLibro(resultSet.getInt(2));
				scrivere.setIdAutore(resultSet.getInt(3));
			}
			else{
				throw new ScrivereNotFoundException();
			}
			
			return scrivere;
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
