package com.github.jcpp.jathenaeum.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

/**
 * Handle the connection with the database.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Database {
	private static Database database = null;

	/**
	 * Not instantiable.
	 */
	private Database() {
	}

	/**
	 * Return the singleton object.
	 * @return
	 */
	public static Database getInstance() {
		if (database == null){
			database = new Database();
		}
		return database;
	}

	/**
	 * Get the connection from the database.
	 * @return
	 */
	public Connection getConnection() {
		Connection con = null;
		Context initCtx;
		try {
			initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			BasicDataSource ds = (BasicDataSource) envCtx.lookup("jdbc/JAthenaeum");
			con = ds.getConnection();
			System.out.println("##db## - 'Pooled Connection' acquisita.");
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * Close the connection with the database.
	 * @param con
	 */
	public void closeConnection(Connection con) {

		try {
			if (con != null) {
				con.close();
				System.out.println("##db## - Connessione rilasciata.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore durante la chiusura della connessione."+
					e.getMessage());
		}

	}

}
