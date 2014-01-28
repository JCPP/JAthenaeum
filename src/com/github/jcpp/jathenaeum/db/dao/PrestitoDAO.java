/**
 * 
 */
package com.github.jcpp.jathenaeum.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.jcpp.jathenaeum.Prestito;
import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.PrestitoNotFoundException;

/**
 * DAO of Prestito.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class PrestitoDAO {
	
	private static Database db = Database.getInstance();
	
	/**
	 * Get all Prestito instances.
	 * @return Returns all Prestito instances in an ArrayList<Prestito>.
	 */
	public static ArrayList<Prestito> getAll(){
		Connection con = db.getConnection();
		ArrayList<Prestito> prestiti = new ArrayList<Prestito>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Prestito";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			Prestito prestito;
			
			while(resultSet.next()){
				prestito = new Prestito();
				prestito.setId(resultSet.getInt(1));
				prestito.setNumeroTesseraUtente(resultSet.getInt(2));
				prestito.setIdLibro(resultSet.getInt(3));
				prestito.setDataInizio(resultSet.getDate(4));
				prestito.setDataFine(resultSet.getDate(5));
				prestiti.add(prestito);
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
		return prestiti;
	}
	
	/**
	 * Get the Prestito by id.
	 * @param id the id of the Prestito.
	 * @return Returns the Prestito instance.
	 * @throws PrestitoNotFoundException Throws an exception if the Prestito is not found.
	 */
	public static Prestito getById(int id) throws PrestitoNotFoundException{
		Connection con = db.getConnection();
		Prestito prestito;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Prestito WHERE IDPrestito = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				prestito = new Prestito();
				prestito.setId(resultSet.getInt(1));
				prestito.setNumeroTesseraUtente(resultSet.getInt(2));
				prestito.setIdLibro(resultSet.getInt(3));
				prestito.setDataInizio(resultSet.getDate(4));
				prestito.setDataFine(resultSet.getDate(5));
			}
			else{
				throw new PrestitoNotFoundException();
			}
			
			return prestito;
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
	 * Get all Prestito instances of an Utente.
	 * @param numeroTesseraUtente the numeroTesseraUtente.
	 * @return Returns all Prestito instances in an ArrayList<Prestito> of an Utente.
	 */
	public static ArrayList<Prestito> getAllByIdUtente(int numeroTesseraUtente){
		Connection con = db.getConnection();
		ArrayList<Prestito> prestiti = new ArrayList<Prestito>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Prestito WHERE NumeroTesseraUtente = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, numeroTesseraUtente);
			ResultSet resultSet = stmt.executeQuery();
			Prestito prestito;
			
			while(resultSet.next()){
				prestito = new Prestito();
				prestito.setId(resultSet.getInt(1));
				prestito.setNumeroTesseraUtente(resultSet.getInt(2));
				prestito.setIdLibro(resultSet.getInt(3));
				prestito.setDataInizio(resultSet.getDate(4));
				prestito.setDataFine(resultSet.getDate(5));
				prestiti.add(prestito);
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
		return prestiti;
	}
	
	
	/**
	 * Get all Prestito instances of an Libro.
	 * @param idLibro the idLibro.
	 * @return Returns all Prestito instances in an ArrayList<Prestito> of an Libro.
	 */
	public static ArrayList<Prestito> getAllByIdLibro(int idLibro){
		Connection con = db.getConnection();
		ArrayList<Prestito> prestiti = new ArrayList<Prestito>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Prestito WHERE IDLibro = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, idLibro);
			ResultSet resultSet = stmt.executeQuery();
			Prestito prestito;
			
			while(resultSet.next()){
				prestito = new Prestito();
				prestito.setId(resultSet.getInt(1));
				prestito.setNumeroTesseraUtente(resultSet.getInt(2));
				prestito.setIdLibro(resultSet.getInt(3));
				prestito.setDataInizio(resultSet.getDate(4));
				prestito.setDataFine(resultSet.getDate(5));
				prestiti.add(prestito);
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
		return prestiti;
	}

}
