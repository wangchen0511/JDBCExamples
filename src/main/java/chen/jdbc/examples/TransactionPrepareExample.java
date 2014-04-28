package chen.jdbc.examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionPrepareExample {

	public void transaction(){
		CreateConnection newConn = new CreateConnection();
		Connection conn = newConn.createConnection();
		PreparedStatement stat = null;
		try {
			conn.setAutoCommit(false);
			String sql = "insert into employee values(?,?)";
			stat = conn.prepareStatement(sql);
			stat.setString(1, "4");
			stat.setString(2, "murong");
			stat.executeUpdate();
			stat.clearParameters();
			stat.setString(1, "5");
			stat.setString(2, "Mimi");
			stat.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				if(conn != null){
					conn.rollback();
				}
			} catch (SQLException e1) {
				System.out.println("Fail to rollback. " + e1.getMessage());
			}
			System.out.println("Fail to execute the transaction. " +e.getMessage());
		} finally{
			try{
				if(stat != null){
					stat.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch(SQLException e2){
				System.out.println("Fail to close the connection or the statement. " + e2.getMessage());
			}
		}
		
	}
	
	public static void main(String[] args) throws SQLException{
		TransactionPrepareExample example = new TransactionPrepareExample();
		example.transaction();
		CreateConnection myConn = new CreateConnection();
		Connection conn = myConn.createConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM employee";
		ResultSet rs = stmt.executeQuery(sql);
		// STEP 5: Extract data from result set
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
