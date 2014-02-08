/**
 * 
 */
package com.github.jcpp.jathenaeum.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.jcpp.jathenaeum.Copy;
import com.github.jcpp.jathenaeum.db.Database;

/**
 * DAO of Copy.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class CopyDAO {
	
	private static Database db = Database.getInstance();
	
	
	/**
	 * Get all Copy instances.
	 * @return Returns all Copy instances in an ArrayList<Copy>.
	 */
	public static ArrayList<Copy> getAll(){
		Connection con = db.getConnection();
		ArrayList<Copy> copies = new ArrayList<Copy>();
		PreparedStatement stmt = null;
		try {
			con.setAutoCommit(false);
			final String select = "SELECT * FROM Copy";
			stmt = con.prepareStatement(select);
			ResultSet resultSet = stmt.executeQuery();
			Copy copy;
			
			while(resultSet.next()){
				copy = new Copy();
				copy.setId(resultSet.getInt(1));
				copy.setBookId(resultSet.getInt(2));
				
				copies.add(copy);
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
		return copies;
	}

}
