package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.ClientBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClientDao {
	
	public static ClientDao instance = null;
	
	public static ClientDao getInstance() {
		
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}

	
	public boolean addClient(ClientBean clientBean,HttpServletRequest request,HttpServletResponse response) {
		
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("insert into clients(clientname,gstno,phonenumber,email,panno,address,PhoneNumber2,Email2) values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1,clientBean.getClientName());
			pstmt.setString(2, clientBean.getGstNo());
			pstmt.setLong(3, clientBean.getPhoneNumber());
			pstmt.setString(4, clientBean.getEmail());
			pstmt.setString(5, clientBean.getPanNo());
			pstmt.setString(6, clientBean.getAddress());
			pstmt.setLong(7, clientBean.getPhoneNumber1());
			System.out.println(clientBean.getEmail1());
			pstmt.setString(8, clientBean.getEmail1());
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception e) {
			try {
				ExceptionHandler.handleException(request, response, e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		return false;
	}
	public ArrayList<ClientBean> getClientList(){
		ArrayList<ClientBean> users = new ArrayList<ClientBean>();
		
		try {
			Connection con = MySqlConnection.getInstance();
			
			PreparedStatement pstmt = con.prepareStatement("select * from clients");
			// now pstmt will not return single value , it will return all the records of the relation
			
			ResultSet rs = pstmt.executeQuery(); // easy concept hi hai
			
			while(rs.next()) {
				ClientBean user = new ClientBean();
				user.setClientId(rs.getInt("CLIENTID"));
				user.setClientName(rs.getString("CLIENTNAME"));
				user.setAddress(rs.getString("ADDRESS"));
				user.setEmail(rs.getString("EMAIL"));
				user.setGstNo(rs.getString("GSTNO"));
				user.setPhoneNumber(rs.getLong("PHONENUMBER"));
				user.setPanNo(rs.getString("PANNO"));
				user.setPhoneNumber1(rs.getLong("PhoneNumber2"));
				user.setEmail1(rs.getString("email2"));
				users.add(user);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	public boolean deleteClient(int clientId,HttpServletRequest request,HttpServletResponse response) {
		try {
			Connection con = MySqlConnection.getInstance();
			
			PreparedStatement pstmt = con.prepareStatement("delete from clients where clientid = ?");
			pstmt.setInt(1, clientId);
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception e) {
			try {
				ExceptionHandler.handleException(request, response, e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

		}
		return false;
	}
	public boolean updateClient(int clientId, String editedColumn, String newValue,HttpServletRequest request,HttpServletResponse response) {
		String updateQuery = "UPDATE Clients SET "+editedColumn+" = ? WHERE clientId = ?";
		
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement(updateQuery);
			pstmt.setString(1, newValue);
			pstmt.setInt(2, clientId);
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception e) {
			try {
				ExceptionHandler.handleException(request, response, e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

		}
		return false;
	}
	
	public int getClientIdFormDatabase(String clientName) {
		
		String selectQuery = "SELECT ClientId From Clients WHERE ClientName = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, clientName);
				ResultSet rs = stmt.executeQuery();
				
				int clientId = 0;
				while(rs.next()) {
					clientId = rs.getInt(1);
				}
				return clientId;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		return 0;
	}
	
	public String getClientNameFormDatabase(int clientId) {
		
		String selectQuery = "SELECT clientName From Clients WHERE clientId = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setInt(1, clientId);
				ResultSet rs = stmt.executeQuery();
				
				String clientName = "";
				while(rs.next()) {
					clientName = rs.getString(1);
				}
				return clientName;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		return null;
	}
	
	
}
