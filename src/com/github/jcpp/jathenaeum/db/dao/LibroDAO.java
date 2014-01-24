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
import com.github.jcpp.jathenaeum.exceptions.BookNotFoundException;

/**
 * DAO of Libro.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class LibroDAO {
	
	private Database db = Database.getInstance();
	
	/**
	 * Get the Libro by id.
	 * @param id the id of the Libro.
	 * @return Returns the Libro instance.
	 * @throws BookNotFoundException Throws an exception if the book is not found.
	 */
	public Libro getById(int id) throws BookNotFoundException{
		Connection con = db.getConnection();
		Libro libro;
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			String select = "SELECT * FROM Libro(IDLibro) values (?)";
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
				throw new BookNotFoundException();
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
	 * Get all Libro instances.
	 * @return Returns all Libro instances in an ArrayList<Libro>.
	 */
	public ArrayList<Libro> getAll(){
		Connection con = db.getConnection();
		ArrayList<Libro> libri = new ArrayList<Libro>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			String insert = "SELECT * FROM students";
			stmt = con.prepareStatement(insert);
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

}
