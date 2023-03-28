package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bean.UserBean;
import com.dbConnection.MySqlConnection;


public class UserDao {
	public ArrayList<UserBean> getAllUsers() {
		ArrayList<UserBean> users = new ArrayList<UserBean>();
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select * from user");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("In........");
				UserBean user = new UserBean();
				user.setUserName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPhNum(rs.getLong(5));
				user.setDepartmentName(rs.getString(6));
				user.setUserId(rs.getInt(1));
				users.add(user);
			}
			return users;
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}