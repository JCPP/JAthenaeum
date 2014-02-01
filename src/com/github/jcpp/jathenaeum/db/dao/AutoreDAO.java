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
import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.AutoreNotFoundException;
import com.github.jcpp.jathenaeum.exceptions.RegistrationException;

/**
 * DAO of Autore.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class AutoreDAO {
	
	private static Database db = Database.getInstance();
	
	/**
	 * Get all Autore instances.
	 * @return Returns all Autore instances in an ArrayList<Autore>.
	 */
	public static ArrayList<Autore> getAll(){
		Connection con = db.getConnection();
		ArrayList<Autore> autori = new ArrayList<Autore>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Autore";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			Autore autore;
			
			while(resultSet.next()){
				autore = new Autore();
				autore.setId(resultSet.getInt(1));
				autore.setNome(resultSet.getString(2));
				autore.setCognome(resultSet.getString(3));
				autore.setFoto(resultSet.getString(4));
				//autore.setDataNascita(resultSet.getDate(5));
				autore.setBiografia(resultSet.getString(6));
				autori.add(autore);
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
		return autori;
	}
	
	
	/**
	 * Get the Autore by id.
	 * @param id the id of the Autore.
	 * @return Returns the Autore instance.
	 * @throws AutoreNotFoundException Throws an exception if the Autore is not found.
	 */
	public static Autore getById(int id) throws AutoreNotFoundException{
		Connection con = db.getConnection();
		Autore autore;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Autore WHERE IDAutore = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				autore = new Autore();
				autore.setId(resultSet.getInt(1));
				autore.setNome(resultSet.getString(2));
				autore.setCognome(resultSet.getString(3));
				autore.setFoto(resultSet.getString(4));
				//autore.setDataNascita(resultSet.getDate(5));
				autore.setBiografia(resultSet.getString(6));
			}
			else{
				throw new AutoreNotFoundException();
			}
			
			return autore;
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
	 * Get all Autore by libroId.
	 * @param libroId the libroId.
	 * @return Returns the Autore instances who write the libroId in an ArrayList<Autore>.
	 */
	public static ArrayList<Autore> getAllByLibroId(int libroId){
		Connection con = db.getConnection();
		ArrayList<Autore> autori = new ArrayList<Autore>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT A.* FROM Autore A, Libro L, Scrivere S WHERE L.IDLibro = ? AND L.IDLibro = S.IDLibro AND S.IDAutore = A.IDAutore";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, libroId);
			ResultSet resultSet = stmt.executeQuery();
			Autore autore;
			
			while(resultSet.next()){
				autore = new Autore();
				autore.setId(resultSet.getInt(1));
				autore.setNome(resultSet.getString(2));
				autore.setCognome(resultSet.getString(3));
				autore.setFoto(resultSet.getString(4));
				//autore.setDataNascita(resultSet.getDate(5));
				autore.setBiografia(resultSet.getString(6));
				autori.add(autore);
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
		return autori;
	}

	public static boolean register(Autore author) throws RegistrationException{
		Connection con = db.getConnection();
		PreparedStatement stmt = null;
		boolean workIt = false;

		try {
			con.setAutoCommit(false);
			String insert = "INSERT INTO Autore (NomeAutore, CognomeAutore, FotoAutore, DataNascitaAutore, BiografiaAutore)"
					+ " VALUES (?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(insert);
			stmt.setString(1, author.getNome());
			stmt.setString(2, author.getCognome());
			stmt.setString(3, author.getFoto());
			stmt.setString(4, author.getDataNascita());
			stmt.setString(5, author.getBiografia());
			
			int result = stmt.executeUpdate();
			con.commit();
			
			if(result == 1 || result== 0){
				
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
