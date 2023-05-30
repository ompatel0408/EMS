package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSDirectorsDashboardBean;
import com.bean.QuotationBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EMSDirectorsDashboardDao {
	
	private static EMSDirectorsDashboardDao instance = null;
	
	public static EMSDirectorsDashboardDao getInstacne() {
		
		if(instance == null) {
			instance = new EMSDirectorsDashboardDao();
		}
		return instance;
	}
	
	public ArrayList<EMSDirectorsDashboardBean> getDataOfLiveProjects(){
		
		
		String selectQuery = "select CLIENTNAME,projectID,PROGRESS,PODATE from clients C Join projects P using(ClientId) WHERE PROGRESS < 100";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSDirectorsDashboardBean> ar = new  ArrayList<EMSDirectorsDashboardBean>();
		EMSDirectorsDashboardBean EDDB = null;
		
		if(conn != null) {
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					EDDB = new EMSDirectorsDashboardBean(rs.getString(1), rs.getString(2),rs.getInt(3),rs.getString(4));
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
	
	public boolean updateisPaid(String vendorName,HttpServletRequest request,HttpServletResponse response) {
		
		String updateQuery = "UPDATE POSTPURCHASE SET isPaid = 'TRUE' WHERE VendorName = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				PreparedStatement stmt = conn.prepareStatement(updateQuery);
				stmt.setString(1, vendorName);
				stmt.executeUpdate();
				return true;
			}catch(SQLException e) {
				try {
					ExceptionHandler.handleException(request, response, e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		
		return false;
	}
	
public ArrayList<QuotationBean> getAllNotificationsForPayment() {
		
		String selectQuery = "SELECT P.ProjectId,Q.QuotationAmount,P.isPaid,(Q.QuotationAmount  - ((Q.QuotationAmount * P.AdvancePayPercent)/100)) RemainingPayment  FROM PROJECTS P JOIN QUOTATIONS Q ON P.quotationId = Q.quotationId WHERE isPaid = 'false'";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<QuotationBean> arr = new ArrayList<QuotationBean>();
		if(conn != null) {
			
			try{
				
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					System.out.println(rs.getString(1));
					arr.add(new QuotationBean(rs.getString(1),Double.parseDouble(rs.getString(2)),Double.parseDouble(rs.getString(4))));
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
	
public boolean updateisPaidForPayment(String projectId,HttpServletRequest request,HttpServletResponse response) {
	
	String updateQuery = "UPDATE PROJECTS SET isPaid = 1 WHERE projectId = ?";
	Connection conn = MySqlConnection.getInstance();
	
	if(conn != null) {
		
		try {
			PreparedStatement stmt = conn.prepareStatement(updateQuery);
			stmt.setString(1, projectId);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			try {
				ExceptionHandler.handleException(request, response, e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

		}
	}else {
		System.out.println("Connection is not establised!");
	}
	
	
	return false;
}
	
}
