package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bean.PrePurchaseBean;
import com.dbConnection.MySqlConnection;

public class PrePurchaseDao {
	
	public static boolean addPrePurchase(PrePurchaseBean Pb) {
		
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
}
