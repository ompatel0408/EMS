package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSDirectorsDashboardBean;
import com.dbConnection.MySqlConnection;


public class EMSDirectorsDashboardDao {
	
	private static EMSDirectorsDashboardDao instance = null;
	
	public static EMSDirectorsDashboardDao getInstacne() {
		
		if(instance == null) {
			instance = new EMSDirectorsDashboardDao();
		}
		return instance;
	}
	
	public ArrayList<EMSDirectorsDashboardBean> getDataOfLiveProjects(){
		
		
		String selectQuery = "select ClientName,p.projectId,pr.workdonepercentage from clients c join projects p ON  c.ClientId = p.ClientId join production pr on pr.projectId=p.projectId";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSDirectorsDashboardBean> ar = new  ArrayList<EMSDirectorsDashboardBean>();
		EMSDirectorsDashboardBean EDDB = null;
		
		if(conn != null) {
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					EDDB = new EMSDirectorsDashboardBean(rs.getString(1), rs.getString(2),rs.getInt(3));
					ar.add(EDDB);
				}
				return ar;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection not establised!");
		}
		return null;
	}
	
	public int getTotalUsers() {
		
		String selectQuery = "SELECT count(*) FROM USER";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			try{
				
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(selectQuery);
				int users = 0;
				if(rs.next()) {
					users = rs.getInt(1);
				}
				return users;
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}else {
			System.out.println("Connection is not establised!");
		}
		return 0;
	}
	
	public ArrayList<EMSDirectorsDashboardBean> getAllNotifications() {
		
		String selectQuery = "SELECT VENDORNAME,SUM(TotalAmount) FROM POSTPURCHASE WHERE ((PaymentTerms - CurrentDate) < 7) AND (isPaid = 'FALSE')  GROUP BY VENDORNAME";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSDirectorsDashboardBean> arr = new ArrayList<EMSDirectorsDashboardBean>();
		if(conn != null) {
			
			try{
				
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					arr.add(new EMSDirectorsDashboardBean(rs.getString(1),rs.getString(2)));
				}
				return arr;
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		return null;
	}
	
	public boolean updateisPaid(String vendorName) {
		
		String updateQuery = "UPDATE POSTPURCHASE SET isPaid = 'TRUE' WHERE VendorName = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				PreparedStatement stmt = conn.prepareStatement(updateQuery);
				stmt.setString(1, vendorName);
				stmt.executeUpdate();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		
		return false;
	}
	
	
}
