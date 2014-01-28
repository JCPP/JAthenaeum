/**
 * 
 */
package com.github.jcpp.jathenaeum.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.jcpp.jathenaeum.Libro;
import com.github.jcpp.jathenaeum.db.Database;
import com.github.jcpp.jathenaeum.exceptions.LibroNotFoundException;

/**
 * DAO of Libro.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class LibroDAO {
	
	private static Database db = Database.getInstance();
	
	/**
	 * Get all Libro instances.
	 * @return Returns all Libro instances in an ArrayList<Libro>.
	 */
	public static ArrayList<Libro> getAll(){
		Connection con = db.getConnection();
		ArrayList<Libro> libri = new ArrayList<Libro>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Libro";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			Libro libro;
			
			while(resultSet.next()){
				libro = new Libro();
				libro.setId(resultSet.getInt(1));
				libro.setTitolo(resultSet.getString(2));
				libro.setCopertina(resultSet.getString(3));
				libro.setGenere(resultSet.getString(4));
				libro.setCodiceIsbn(resultSet.getString(5));
				libro.setDescrizione(resultSet.getString(6));
				libri.add(libro);
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
		return libri;
	}
	
	/**
	 * Get the Libro by id.
	 * @param id the id of the Libro.
	 * @return Returns the Libro instance.
	 * @throws LibroNotFoundException Throws an exception if the Libro is not found.
	 */
	public static Libro getById(int id) throws LibroNotFoundException{
		Connection con = db.getConnection();
		Libro libro;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Libro WHERE IDLibro = ?";
			stmt = con.prepareStatement(select);
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			con.commit();
			
			if(resultSet.next()){
				libro = new Libro();
				libro.setId(resultSet.getInt(1));
				libro.setTitolo(resultSet.getString(2));
				libro.setCopertina(resultSet.getString(3));
				libro.setGenere(resultSet.getString(4));
				libro.setCodiceIsbn(resultSet.getString(5));
				libro.setDescrizione(resultSet.getString(6));
			}
			else{
				throw new LibroNotFoundException();
			}
			
			return libro;
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
	 * Get the number of the Libro instances.
	 * @param isbn the isbn code.
	 * @return Returns the number of the Libro instances.
	 */
	public static int getNumberByIsbn(String isbn){
		Connection con = db.getConnection();
		int number = 0;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT COUNT(*) FROM Libro WHERE CodiceIsbnLibro = ?";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			
			number = resultSet.getInt(1);
			
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
		return number;
	}

}
