package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bean.ClientBean;
import com.dbConnection.MySqlConnection;

public class ClientDao {
	
	public static ClientDao instance = null;
	
	public static ClientDao getInstance() {
		
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}

	public void addClient(ClientBean clientBean) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("insert into clients(clientname,gstno,phonenumber,email,panno,address) values(?,?,?,?,?,?)");
			pstmt.setString(1,clientBean.getClientName());
			pstmt.setString(2, clientBean.getGstNo());
			pstmt.setLong(3, clientBean.getPhoneNumber());
			pstmt.setString(4, clientBean.getEmail());
			pstmt.setString(5, clientBean.getPanNo());
			pstmt.setString(6, clientBean.getAddress());
			
			pstmt.executeUpdate();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
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
				System.out.println(rs.getString("CLIENTNAME"));
				users.add(user);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	public void deleteClient(int clientId) {
		try {
			Connection con = MySqlConnection.getInstance();
			
			PreparedStatement pstmt = con.prepareStatement("delete from clients where clientid = ?");
			pstmt.setInt(1, clientId);
			// now pstmt will not return single value , it will return all the records of the relation
			
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateClient(int clientId, String editedColumn, String newValue) {
		String updateQuery = "UPDATE Clients SET "+editedColumn+" = ? WHERE clientId = ?";
		
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement(updateQuery);
			pstmt.setString(1, newValue);
			pstmt.setInt(2, clientId);
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
