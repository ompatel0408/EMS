package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSPurchaseBean;
import com.dbConnection.MySqlConnection;

public class EMSPurchaseDao {
	
	private static EMSPurchaseDao instance = null;
	
	public static EMSPurchaseDao getInstance() {
		
		if(instance == null) {
			instance = new EMSPurchaseDao();
		}
		return instance;
	}
	
	public ArrayList<String> getCatagoryFromDataBase() {
		
		String selectQuery = "SELECT Catagory FROM EMSCatagory";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> catagory = new ArrayList<String>();
		if(conn != null) {
			
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs =stmt.executeQuery(selectQuery);

				while(rs.next()) {
					catagory.add(rs.getString(1));
				}
				return catagory;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection Not Esablised!");
		}
		
		return null;
	}
	
	public String getIndentId(String projectId) {
		
		String selectQuery = "SELECT IndentID FROM Indent WHERE ProjectId = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, projectId);
				ResultSet rs = stmt.executeQuery();
				String indentId = "";
				if(rs.next()) {
					indentId = rs.getString(1);
				}
				return indentId;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
	
	public boolean addPurchase(ArrayList<EMSPurchaseBean> AEPB) {
		
		String insertQuery = "INSERT INTO POSTPURCHASE VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				
				for(EMSPurchaseBean EPB:AEPB) {
					stmt.setString(1,EPB.getIndentId());
					stmt.setString(2, EPB.getProductDescription());
					stmt.setString(3, EPB.getSize());
					stmt.setInt(4, EPB.getQuantity());
					stmt.setString(5, EPB.getUom());
					stmt.setString(6, EPB.getRatePerKg());
					stmt.setString(7, EPB.getDiscount());
					stmt.setString(8, EPB.getNetAmount());
					stmt.setString(9, String.valueOf(EPB.getSGST()));
					stmt.setString(10, String.valueOf(EPB.getCGST()));
					stmt.setString(11, EPB.getCurrentDate());
					stmt.setString(12, EPB.getPONumber());
					stmt.setString(13, EPB.getVendorName());
					stmt.addBatch();
				}
				stmt.executeBatch();
				return true;
				
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connction is not establised!");
		}
		
		return false;
	}
	
	
}
