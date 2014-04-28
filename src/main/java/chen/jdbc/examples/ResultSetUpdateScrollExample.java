package chen.jdbc.examples;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetUpdateScrollExample {

	public static void main(String[] args) throws SQLException {
		CreateConnection myConn = new CreateConnection();
		Connection conn = myConn.createConnection();
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		String sql = "SELECT * FROM employee";
		ResultSet rs = stmt.executeQuery(sql);
		/*
		 * print
		 */
		while (rs.next()) {
			// Retrieve by column name
			String id = rs.getString("id");
			String name = rs.getString("name");
			// Display values
			System.out.println("ID: " + id);
			System.out.println("Name: " + name);
		}
		
		/*
		 * Reverse read
		 */
		while (rs.previous()) {
			// Retrieve by column name
			String id = rs.getString("id");
			String name = rs.getString("name");
			// Display values
			System.out.println("ID: " + id);
			System.out.println("Name: " + name);
		}
		
		/*
		 * update the row
		 */
		while (rs.next()) {
			// Retrieve by column name
			String id = rs.getString("id");
			if ("1".equals(id)) {
				rs.updateString("name", "chen wang");
			}
			rs.updateRow();
		}
		
		/*
		 * print
		 */
		while (rs.previous()) {
			// Retrieve by column name
			String id = rs.getString("id");
			String name = rs.getString("name");
			// Display values
			System.out.println("ID: " + id);
			System.out.println("Name: " + name);
		}
		
		/*
		 * insert a new row
		 */
		rs.moveToInsertRow(); // moves cursor to the insert row
		rs.updateString(1, "11"); // updates the
		// first column of the insert row to be AINSWORTH
		rs.updateString(2, "xiong mao"); // updates the second column
		rs.insertRow();
		rs.moveToCurrentRow();
		
		
		/*
		 * print
		 */
		while (rs.next()) {
			// Retrieve by column name
			String id = rs.getString("id");
			String name = rs.getString("name");
			// Display values
			System.out.println("ID: " + id);
			System.out.println("Name: " + name);
		}
		rs.close();
	}

}
