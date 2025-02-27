package com.comcast.CRM.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection conn;
	
	public void getConnection(String url, String username, String password) throws SQLException
	{
		try {
		Driver driverdef = new Driver();
		DriverManager.registerDriver(driverdef);
		
		conn = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public ResultSet executeConSelectQuery(String query) throws SQLException
	{
		ResultSet result = null;
		try {
		Statement stat = conn.createStatement();
		result = stat.executeQuery(query);
		}
		catch(Exception e)
		{
			
		}
		return result;
		
	}
	
	public void closeDBConnection()
	{
		try {
			conn.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public int executeNonSelectQuery(String query)
	{
		int result = 0;
		try {
			Statement stat = conn.createStatement();
			result = stat.executeUpdate(query);
		}
		catch(Exception e)
		{
		     }
		
		return result;

	}
	
	public void getDbConnection() throws SQLException
	{
		try {
		Driver driverdef = new Driver();
		DriverManager.registerDriver(driverdef);
		DriverManager.getConnection("jdbc:mysql://localhost:3306/VIVEK","root","Vivek@12");
		}
		catch(Exception e)
		{
			
		}
	}

}
