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
	private String currentWarehouseID="1";		//hard-coded for current implementation

	/**
	 * Establishes a connection to our SQL DB.
	 * 
	 */
	public void connectToDB() {
		try {
			String dbURL="jdbc:mysql://localhost:3306/waresourstuff";
			String user="root";
			String pass="Skeleton";
		
			
			conn=DriverManager.getConnection(dbURL,user,pass);
		}
		catch (Exception e) {
			System.out.println("Failure connecting to DB.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Finds the associated attributes on the DB for the provided item
	 * @param itemID item to find details for
	 * @return ResultSet of item details (itemName,price,color,size,notes,conditionName,areaName)
	 */
	public ResultSet getInfo(String itemID) {
		ResultSet rs=null;
		
		try {
			CallableStatement stmt=conn.prepareCall("{call get_item_info(?)}");
			stmt.setString(1,itemID);		//set SQL-call variables ('?')
			stmt.execute();					//send query to DB
			rs=stmt.getResultSet();			//create returnable object
			
		} catch (SQLException e) {
			System.out.println("Failure retrieving item info from DB.");
			e.printStackTrace();
		}
		return rs;
		
	}
	
	/**
	 * Calls an UPDATE to the SQL database 'item' table with the provided attributes.
	 * Calls  addConditions() to handle any updated item conditions.
	 * @param name
	 * @param color
	 * @param price
	 * @param size
	 * @param condition
	 * @param location
	 */
	public void updateItem(String name, String color, String price, String size, String conditions, String location, String itemID) {
		
		addConditions(itemID, conditions);
		
		try {
			CallableStatement stmt=conn.prepareCall("{call update_item(?,?,?,?,?,?)}");
			stmt.setString(1, itemID);
			stmt.setString(2, name);
			stmt.setString(3, color);
			stmt.setString(4, price);
			stmt.setString(5, size);
			stmt.setString(6, location);
			
			stmt.execute();
			
			
		} catch(SQLException e) {
			System.out.println("Failure to update item.");
			e.printStackTrace();
		}
	}
	/**
	 * Calls an INSERT to the 'item' table with the provided attributes.
	 * @param name
	 * @param color
	 * @param price
	 * @param size
	 * @param condition
	 * @param location
	 * @return ResultSet containing itemID of newly added item
	 */
	public ResultSet addItem(String name, String color, String price, String size, String conditions, String location) {		
		ResultSet rs=null;
		try {
			CallableStatement stmt=conn.prepareCall("{call add_item(?,?,?,?,?,?)}");	//add item with given name, color, price, size, & location
			stmt.setString(1, location);
			stmt.setString(2 , name);
			stmt.setString(3 , price);
			stmt.setString(4 , color);
			stmt.setString(5 , size);
			stmt.setString(6 , "n/a");	//TODO: implement picture (BLOB)	
			stmt.execute();
			
			rs=stmt.getResultSet();
			rs.next();	
			String itemID=rs.getString(1);
			
			addConditions(itemID, conditions);
		} catch (SQLException e) {
			System.out.println("Failure adding item to DB.");
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * Calls a SELECT on itemID & itemName for all items in the currentWarehouseID
	 * @return ResultSet of warehouse inventory (itemID & itemName columns)
	 */
	public ResultSet fetchInventory() {
		ResultSet rs=null;
		
		try {
			CallableStatement stmt = conn.prepareCall("{call fetch_inventory(?)}");
			stmt.setString(1, currentWarehouseID);
			stmt.execute();
			rs=stmt.getResultSet();
		} catch (SQLException e) {
			System.out.println("Failure fetching inventory.");
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * Calls a DELETE on the row in 'item' matching the supplied itemID.
	 * @param itemID ID of item to be removed
	 */
	public void removeItem(String itemID) {
		try {
			CallableStatement stmt=conn.prepareCall("{call remove_item(?)}");
			stmt.setString(1, itemID);
			stmt.execute();
		} catch(SQLException e) {
			System.out.println("Failure removing item from DB.");
			e.printStackTrace();
		}	
	}
	
	/**
	 * Calls an INSERT to the 'itemcondition' table with itemID & any parsed condition(s) from conditions
	 * @param itemID
	 * @param conditions String of conditions separated by ', '
	 */
	private void addConditions(String itemID, String conditions) {
		if (!(conditions==null)) {
			if (!conditions.equals("") ) {
				String[] parsedConditions=conditions.split(", ");

				try {
					CallableStatement stmt=conn.prepareCall("{call remove_itemconditions(?)}");
					stmt.setString(1, itemID);
					stmt.execute();
					
					stmt=conn.prepareCall("{call add_itemcondition(?,?)}");
					int conditionCount=parsedConditions.length;
					for(int i=0; i<conditionCount; i++) {				//add conditionID & itemID entry to itemcondition where parsedConditions[i]=conditionName in conditionlist
						stmt.setString(1, itemID);
						stmt.setString(2, parsedConditions[i]);
						stmt.execute();
					}	
				} catch(SQLException e) {
					System.out.println("Failure adding condition(s) to DB.");
					e.printStackTrace();
				}
			}
		}
	}
}