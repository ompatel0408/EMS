package com.dbConnection;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class MySqlConnection {

	 private static final String URLNAME="jdbc:mysql://localhost:3306/EMS?autoReconnect=true";
	 private static final String DRIVERCLASS="com.mysql.cj.jdbc.Driver";
	 private static final String USERNAME="root";
	 private static final String PASSWD="";
	 
	 	private static Connection con=null;
		private MySqlConnection() 
		{
			
		}
		
		
		public static Connection getInstance()
		{
			if(con==null)
			{
				con=connection();
			}
			return con;
		}
		private static Connection connection() 
		{
			
			Connection con=null;
			try
			{
	//			3) load driver class
				
				Class.forName(DRIVERCLASS);
				
	//			4) Pass crediantials to driverManagers getconnetions method
				
				con=DriverManager.getConnection(URLNAME,USERNAME,PASSWD);
				
	//			5) chack that raferance variable has some object or not
				
				if(con!=null)
				{
					System.out.println("Connection is establised successfully!");
				}
				else 
				{
					System.out.println("Connection isn't establised successfully!");
				}
				
			}
			catch(ClassNotFoundException E)
			{
				E.printStackTrace();
			}
			catch(SQLException E) 
			{
				E.printStackTrace();
			}
			
				return con;
				
		}
}
