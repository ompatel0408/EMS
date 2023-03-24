package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bean.EMSLoginBean;
import com.dbConnection.MySqlConnection;

public class EMSLoginDao {
	
	public static EMSLoginDao instance = null;
	
	public static EMSLoginDao getInstance() {
		
		if(instance == null) {
			instance = new EMSLoginDao();
		}
		return instance;
	}
	
	public static EMSLoginBean getPasswordFromDb(String email) {
		
		String selectQuery = "SELECT Password FROM user WHERE email = ?";
		Connection conn = MySqlConnection.getInstance();
		System.out.println("Email :"+email);
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
}
