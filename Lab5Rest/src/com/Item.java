package com;

import java.sql.*;

public class Item {
	
	public Connection connect() {

		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/itemdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			//  spring.datasource.url=jdbc:mysql://localhost:3301/student?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
			// For Testing
			System.out.println("Successfully Connected");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Unsuccessful!!!!");
		}
		return con;

	}
	
public String insertString(String code, String name, String price,String desc) {
		
		String output ="";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the databace";
			}
			
			String query = " insert into items"
					+"(`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"
					+ " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
			
			preparedStatement.setInt(1,0 );
			preparedStatement.setString(2, code);
			preparedStatement.setString(3, name);
			preparedStatement.setDouble(4, Double.parseDouble(price));
			preparedStatement.setString(5, desc);
			
			preparedStatement.execute();
			con.close();
			
			output = "Insert Successfull";
			System.out.println(output);
			
		} catch (Exception e) {
			// TODO: handle exception
			output="Error Inserting data";
			System.err.println(e.getMessage());
		}
		
		return output;
		
	}
	
	public String readItems() {
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con== null) {
				return "Error while connecting to the database";
			}
			
			output = "<table border=\"1\">"
					+ "<tr><th>Item Code</th><th>Item Name</th><th>Item Price</th><th>Item Description</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from items";
			Statement statement = con.createStatement();
			ResultSet set = statement.executeQuery(query);
			
			while (set.next()) {
				
				String itemId = Integer.toString(set.getInt("itemId"));
				String itemCode = set.getString("itemCode");
				String itemName = set.getString("itemName");
				String itemPrice = Double.toString(set.getDouble("itemPrice"));
				String itemDesc = set.getString("itemDesc");
				
				output += "<tr><th>" + itemCode + "</th>";
				output += "<th>" + itemName + "</th>";
				output += "<th>" + itemPrice +"</th>";
				output += "<th>" +itemDesc + "</th>";
				
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"update\"></th>"
						  +"<td><form method=\"post\" action=\"item.jsp\">"
						  + "<input name=\"btnRemove\" "
						  + " type=\"submit\" value=\"Remove\">"
						  + "<input name=\"itemId\" type=\"hidden\" "
						  + " value=\"" + itemId + "\">" + "</form></td></tr>";
			}
			
			con.close();
			
			output += "</table>";
			System.out.println("successfully print all the data");
		} catch (Exception e) {
			// TODO: handle exception
			output = "Cannot read items";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String deleteItem(String itemId)
	{
		String output="";
		
		try {
			
			Connection con = connect();
			
			if(con == null)
			{
				return "Error while connecting to the dataabase";
			}
			
			String query= "delete from items where itemID=?";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setInt(1, Integer.parseInt(itemId));
			preparedStatement.execute();
			con.close();
			
			output="Delete successfull";
			System.out.println(output);
			
		} catch (Exception e) {
			// TODO: handle exception
			output= "error while deleting items";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

	public String updateItem(String ID, String code, String name, String price, String desc)
	{
				String output = "";
					try
					{
								Connection con = connect();
									if (con == null)
									{
										return "Error while connecting to the database for updating."; 
									}
	
									// create a prepared statement
									String query = "UPDATE items SET itemCode=?,itemName=?,itemPrice=?,itemDesc=?"
													+ "WHERE itemId=?";
	
									PreparedStatement preparedStmt = con.prepareStatement(query);
									// binding values
									preparedStmt.setString(1, code);
									preparedStmt.setString(2, name);
									preparedStmt.setDouble(3, Double.parseDouble(price));
									preparedStmt.setString(4, desc);
									preparedStmt.setInt(5, Integer.parseInt(ID));
									// execute the statement
									preparedStmt.execute();
									con.close();
									
									output = "Updated successfully";
									System.out.println(output);
							}
					catch (Exception e)
					{
	
						output = "Error while updating the item.";
						System.err.println(e.getMessage());

					}
	
					return output;
	}
	
	
}
