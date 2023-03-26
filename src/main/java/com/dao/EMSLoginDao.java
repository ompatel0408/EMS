

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSLoginBean;
import com.bean.UserBean;
import com.dbConnection.MySqlConnection;

public class EMSLoginDao {
	
	public static EMSLoginDao instance = null;
	
	public static EMSLoginDao getInstance() {
		
		if(instance == null) {
			instance = new EMSLoginDao();
		}
		return instance;
	}
	
	public EMSLoginBean getPasswordFromDb(String email) {
		
		String selectQuery = "SELECT Password FROM user WHERE email = ?";
		Connection conn = MySqlConnection.getInstance();
		
		EMSLoginBean Eb = new EMSLoginBean();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(selectQuery);
			stmt.setString(1, email);
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1));
				Eb.setPassword(resultSet.getString(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return Eb;
	}
	
	public String getEmailFromDatabase(String password) {
		
		String selectQuery = "SELECT Email FROM USER WHERE password = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, password);
				ResultSet resultSet = stmt.executeQuery();
				String email = "";
				if(resultSet.next()) {
					email = resultSet.getString(1);
				}
				return email;
			}catch(Exception e) {
				e.printStackTrace();
			}		
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
	
	public ArrayList<EMSLoginBean> getData(){
		
		String selectQuery = "SELECT NAME,EMAIL,ROLE FROM USER";
		Connection conn = MySqlConnection.getInstance();
		EMSLoginBean ELB = null;
		ArrayList<EMSLoginBean> ar  = new ArrayList<EMSLoginBean>();
		if(conn != null) {
			
			try {
				
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					ELB = new EMSLoginBean(rs.getString(1), rs.getString(2),rs.getInt(3));
					ar.add(ELB);
				}
				return ar;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		return null;
	}
	
	public boolean updateDataToDatabase(ArrayList<EMSLoginBean> AELB) {
		
		String updateQuery = "UPDATE user SET Password = ?, secretKey = ? WHERE EMAIL = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				PreparedStatement stmt = conn.prepareStatement(updateQuery);
				for(EMSLoginBean ELB:AELB) {
					stmt.setString(1, ELB.getPassword());
					stmt.setString(2, ELB.getSecretKey());
					stmt.setString(3, ELB.getEmail());
					stmt.addBatch();
				}
				stmt.executeBatch();
				return true;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		return false;
	}
	
	
	public EMSLoginBean getAllDetails(String password) {
		
		String selectQuery = "SELECT userId,secretKey FROM USER WHERE PASSWORD = ?";
		Connection conn = MySqlConnection.getInstance();
		EMSLoginBean ELB = null;
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, password);
				ResultSet rs= stmt.executeQuery();
				
				if(rs.next()) {
					ELB = new EMSLoginBean(rs.getInt(1), rs.getString(2));
				}
				return ELB;
				
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		return null;
	}
	
}
