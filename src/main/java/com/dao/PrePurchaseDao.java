package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bean.PrePurchaseBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PrePurchaseDao {
	
	private static PrePurchaseDao instance = null;
	
	public static PrePurchaseDao getInstance() {
		
		if(instance == null) {
			instance = new PrePurchaseDao();
		}
		return instance;
	}
	
	public boolean addPrePurchase(PrePurchaseBean Pb) {
		
		String insertQuery = "INSERT INTO PrePurchase(ProjectId,DrawingId) VALUES(?,?)";
		Connection conn = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement(insertQuery);
			stmt.setString(1, Pb.getProjectId());
			stmt.setString(2, Pb.getDrawingId());
			stmt.executeUpdate();
			return true;
		}catch(SQLException E) {
			E.printStackTrace();
		}
		return false;
		
	}
	public boolean addPrePurchaseInOffers(PrePurchaseBean Pb,HttpServletRequest request,HttpServletResponse response) {
		
		String insertQuery = "INSERT INTO PrePurchase(DrawingId,ClientId) VALUES(?,?)";
		Connection conn = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement(insertQuery);
			stmt.setString(1, Pb.getDrawingId());
			stmt.setInt(2, Pb.getClientId());
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
		return false;
		
	}
}
