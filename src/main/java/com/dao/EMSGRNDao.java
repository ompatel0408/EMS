package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSGRNBean;
import com.dbConnection.MySqlConnection;

public class EMSGRNDao {
	
	private static EMSGRNDao instance = null;
	
	public static EMSGRNDao getInstance() {
		
		if(instance == null) {
			instance = new EMSGRNDao();
		}
		return instance;
	}
	
	public boolean addGRN(EMSGRNBean EGB) {
		
		String insertQuery = "INSERT INTO GRN(VENDORNAME,ReceiveDate,PATH1,PATH2) VALUES(?,?,?,?)";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				stmt.setString(1,EGB.getVendorName() );
				stmt.setString(2, EGB.getReceiveDate());
				stmt.setString(3, EGB.getPath1());
				stmt.setString(4, EGB.getPath2());
				stmt.executeUpdate();
				return true;
				
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return false;
	}
	
	public ArrayList<EMSGRNBean> getAllData(){
		
		String selectQuery = "SELECT * FROM GRN";
		Connection conn =MySqlConnection.getInstance();
		ArrayList<EMSGRNBean> a = new ArrayList<EMSGRNBean>();
		EMSGRNBean EGB = null;
		if(conn != null) {
			
			try {
				
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					EGB = new EMSGRNBean(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
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
	
	public boolean deleteGRN(int grnId) {
		
		String deleteQuery = "DELETE FROM GRN WHERE GRNID = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(deleteQuery);
				stmt.setInt(1, grnId);
				stmt.executeUpdate();	
				return true;
			
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return false;
	}
	
	
}
