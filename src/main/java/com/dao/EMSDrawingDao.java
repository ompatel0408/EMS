package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSDrawingBean;
import com.bean.EMSGRNBean;
import com.dbConnection.MySqlConnection;

public class EMSDrawingDao {
	
	private static EMSDrawingDao instance = null;
	
	public static EMSDrawingDao getInstance() {
		if(instance == null) {
			instance = new EMSDrawingDao();
		}
		return instance;
	}
	
	
	public ArrayList<String> getOfferNameFromDatabase(String projectId) {
		
		String selectQuery = "select ItemCode from items I JOIN Projects P ON I.QuotationId = P.QuotationId WHERE projectId = ?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> arr = new ArrayList<String>();
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1,projectId);
				ResultSet rs=stmt.executeQuery();
				
				while(rs.next()) {
					arr.add(rs.getString(1));
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
	
	public ArrayList<String> getDrawingIdFromdatabase(String projectId){
		
		String selectQuery = "SELECT DrawingId FROM Prepurchase WHERE ProjectId = ?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> a=new ArrayList<String>();
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, projectId);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					a.add(rs.getString(1));
				}
				return a;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
	
	public boolean isDrawingIdPresent(String drawingId) {
		
		String selectQuery = "SELECT * FROM DRAWING WHERE DRAWINGID = ?";
		Connection conn = MySqlConnection.getInstance();
		if(conn != null) {
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1,drawingId);
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()) {
					return true;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		return false;
	}
	
	public boolean addDrawingDetails(String drawingId,EMSDrawingBean EDB) {
		
		String insertQuery = "INSERT INTO Drawing(DrawingId,ClientDrawing,EMSDrawing) Values(?,?,?)";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				stmt.setString(1,drawingId);
				stmt.setString(2, EDB.getClientDrawing());
				stmt.setString(3, EDB.getEMSDrawing());
				stmt.executeUpdate();
				return true;
			}catch(SQLException e) {
				if(EMSDrawingDao.getInstance().updateDrawing(drawingId,EDB)) {
					return true;
				}
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		return false;
	}
	
	
	public boolean updateDrawing(String drawingId,EMSDrawingBean EDB) {
		

		String insertQuery = "UPDATE Drawing SET ClientDrawing = ? , EMSDrawing = ? WHERE DRAWINGID = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				stmt.setString(3,drawingId);
				stmt.setString(1, EDB.getClientDrawing());
				stmt.setString(2, EDB.getEMSDrawing());
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
	
	public ArrayList<EMSDrawingBean> getAllData(){
		
		String selectQuery = "SELECT D.*,P.projectId,O.offerCode FROM drawing D JOIN Prepurchase P on D.DrawingId = P.DrawingId JOIN OFFER O ON P.DrawingId = O.DrawingId";
		Connection conn =MySqlConnection.getInstance();
		ArrayList<EMSDrawingBean> a = new ArrayList<EMSDrawingBean>();
		EMSDrawingBean EGB = null;
		if(conn != null) {
			
			try {
				
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					EGB = new EMSDrawingBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
					a.add(EGB);
				}
				return a;
				
			}catch(SQLException E)
			{
				E.printStackTrace();	
			}
			
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
	
	
}
