package pcClient;

import java.sql.*;

/**
 * @author Aiden Wells
 *
 * Control class for the back-end functionality of the PC Client for the 'Ware's Our Stuff Program.'
 * Manages database connection (queries, sorting result sets, database updates, & 
 */

public class WaresOurStuffControl {
	private Connection conn;
	private int currentItemID=-1;

	/////////////////////////////
	//FOR TESTING PURPOSES ONLY//
	/////////////////////////////
	public static void main (String args[]) {
		WaresOurStuffControl ctrl=new WaresOurStuffControl();
		ctrl.connectToDB();
		ctrl.getInfoTest("1");
		
	}
	private ResultSet getInfoTest(String itemID) {
		ResultSet rs=null;
		
		try {
			CallableStatement stmt=conn.prepareCall("{call get_item_info(?)}");
			//set SQL-call variables
			stmt.setString(1,itemID);
			stmt.execute();
			rs=stmt.getResultSet();
			rs.next();
			System.out.println("Item ID:"+itemID+"\nItem Name: "+rs.getString("itemName")+"\nPrice: "+rs.getBigDecimal("price"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	/////////////////////////////
	//FOR TESTING PURPOSES ONLY//
	/////////////////////////////
	
	
	/**
	 * Establishes a connection to our SQL DB.
	 * 
	 */
	private void connectToDB() {
		try {
			String dbURL="jdbc:mysql://localhost:3306/waresourstuff";
			String user="root";
			String pass="Skeleton";
		
			
			conn=DriverManager.getConnection(dbURL,user,pass);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Calls a SELECT to the 'item' table for the selected item in the GUI list
	 * IN: item.id
	 * OUT: item.name, item.color, item.price, item.size, item.condition, item.location, item.notes
	 * 
	 */
	private void getInfo(String itemID) {
		//query database with itemID
		//return ResultSet
	}
	
	/**
	 * Calls an UPDATE to the SQL database 'item' table with the provided attributes
	 * IN: item.name,item.color,item.price,item.size,item.condition,item.location
	 * OUT: n/a
	 */
	private void updateItem(String name, String color, String price, String size, String condition, String location) {
		//update current item with given name, color, price, size, & location
		//interpret given conditions
		//update itemcondition with given condition(s)
	}
}


//Statement stmt=conn.createStatement();

//SQL Query
//ResultSet rs=stmt.executeQuery(<dynamic select query>);

//Process result set
//while (rs.next(){
//	<output to stream>
//	}

