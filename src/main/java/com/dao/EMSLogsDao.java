package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
	
	
	public ArrayList<EMSLogsBean> getAllData(){
		
		String selectQuery = " select L.*,U.Name from logs L JOIN User U using(userId) order By CurrentTime DESC";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSLogsBean> a = new ArrayList<EMSLogsBean>();
		if(conn != null) {
			
			try{
				
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					a.add(new EMSLogsBean(rs.getString(3), rs.getInt(5),rs.getString(6) , rs.getString(2), rs.getInt(1), rs.getString(4),rs.getString(7)));
				}
				return a;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
	
}
