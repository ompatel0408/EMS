package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.UserBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


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
	
	public int addUser(UserBean ub,HttpServletRequest request,HttpServletResponse response)
	{
		Connection conn = MySqlConnection.getInstance();
		if(conn != null)
		{
			try {
				PreparedStatement stmt = conn.prepareStatement("insert into user(name,email,phonenumber,departmentname,role) values (?,?,?,?,?)");
				stmt.setString(1, ub.getUserName());
				stmt.setString(2, ub.getEmail());
				stmt.setLong(3, ub.getPhNum());
				stmt.setString(4, ub.getDepartmentName());
				stmt.setInt(5, ub.getRole());
				int data = stmt.executeUpdate();
				return data;
			}
			catch (SQLException e) 
			{
				try {
					ExceptionHandler.handleException(request, response, e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
		}
		return 0;
	}
	
}