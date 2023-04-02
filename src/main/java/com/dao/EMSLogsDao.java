package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.bean.EMSLogsBean;
import com.dbConnection.MySqlConnection;

public class EMSLogsDao {
	
	private static EMSLogsDao instance = null;
	
	public static EMSLogsDao getInstance() {
		if(instance == null) {
			instance = new EMSLogsDao();
		}
		return instance;
	}
	
	 public String getCurrentDateTime() {
	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        return now.format(formatter);
	    }
	
	
	public boolean insertLogs(EMSLogsBean ELB) {
		
		String insertQuery = "INSERT INTO LOGS (DepartmentName,LOGS,CurrentTime,USERID,Category) VALUES (?,?,?,?,?)";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				stmt.setString(1,ELB.getDepartmentName());
				stmt.setString(2,ELB.getLOGS());
				stmt.setString(3,EMSLogsDao.getInstance().getCurrentDateTime());
				stmt.setInt(4,ELB.getUserId());
				stmt.setString(5,ELB.getCategory());
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
