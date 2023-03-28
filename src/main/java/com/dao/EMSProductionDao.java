package com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bean.EMSProductionBean;
import com.dbConnection.MySqlConnection;

public class EMSProductionDao {
	
	private static EMSProductionDao instance = null;
	
	public static EMSProductionDao getInstance() {
		
		if(instance == null) {
			instance = new EMSProductionDao();
		}
		return instance;
	}
	
	public static boolean isProjectStatusAvailable(EMSProductionBean EB) {
		
		
		String Query = "select * from production where projectId = ?";
		Connection conn= MySqlConnection.getInstance();
		if(conn != null)
		{
			
			try {
				PreparedStatement stmt = conn.prepareStatement(Query);
				stmt.setString(1, EB.getprojectId());
				ResultSet rs = stmt.executeQuery();
				if(rs.next())
				{
					return true;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
		{
			System.out.println("Connection not done....");
		}
		return false;
	}
	
	
	public boolean addDataToDatabase(EMSProductionBean EB) {
		
		Connection conn = MySqlConnection.getInstance();
		System.out.println("project---"+EB.getprojectId());
		
		if(isProjectStatusAvailable(EB) )
		{
			System.out.println("entered in if of-----");
			//update status
			String updateQuery = "update Production set Remarks=? , WorkDonePercentage=?, qualityCheck=? where projectId=?";
			
			if(conn != null) {
				
				try {
					
					PreparedStatement stmt = conn.prepareStatement(updateQuery);
					stmt.setString(1, EB.getRemark());
					stmt.setInt(2, EB.getworkDonePer());
					stmt.setString(3, EB.getqualityCheck());
					stmt.setString(4, EB.getprojectId());
					stmt.executeUpdate();
					return true;
				}catch(SQLException E) {
					E.printStackTrace();
				}
			}else {
				System.out.println("Connection is not establised!");
			}
			
		}
		else {
			
			// insert into table...
			String insertQuery = "INSERT INTO Production Values(?,?,?,?)";
			
			if(conn != null) {
				
				try {
					
					PreparedStatement stmt = conn.prepareStatement(insertQuery);
					stmt.setString(1, EB.getprojectId());
					stmt.setString(2, EB.getRemark());
					stmt.setInt(3, EB.getworkDonePer());
					stmt.setString(4, EB.getqualityCheck());
					stmt.executeUpdate();
					return true;
				}catch(SQLException E) {
					E.printStackTrace();
				}
			}else {
				System.out.println("Connection is not establised!");
			}
		}
		return false;
	}
	
}
