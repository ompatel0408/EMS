package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.ClientBean;
import com.bean.ItemBean;
import com.dbConnection.MySqlConnection;

public class ItemDao {
	
	public static ItemDao instance = null;
	
	public static ItemDao getInstance() {
		
		if(instance == null) {
			instance = new ItemDao();
		}
		return instance;
	}
	
	public static boolean addItems(ArrayList<ItemBean> Aqb) {
		
		String insertQuery = "INSERT INTO ITEMS(CLIENTID,ITEMCODE,ITEMNAME,Quantity,QuotationId,TagNo,DeliveryDate,Remarks) VALUES(?,?,?,?,?,?,?,?)";
		
		Connection conn = MySqlConnection.getInstance();
		
		
		try {
			PreparedStatement stmt = conn.prepareStatement(insertQuery);
			
			for(ItemBean qb:Aqb) {
				stmt.setInt(1, qb.getClientId());
				stmt.setString(2, qb.getItemCode());
				stmt.setString(3, qb.getItemName());
				stmt.setInt(4, qb.getQuantity());
				stmt.setInt(5, qb.getQuotationId());
				stmt.setString(6, qb.getTagNo());			
				stmt.setString(7, qb.getDate());
				stmt.setString(8, qb.getRemarks());
				stmt.addBatch();
			}
			int[] result = stmt.executeBatch();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public static ItemBean getItemId() {
		String selectQuery = "SELECT ItemCode FROM Items ORDER BY ItemCode DESC LIMIT 1";
		Connection conn = MySqlConnection.getInstance();
		ItemBean Qb = new ItemBean();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);
			
			if(rs.next()) { 
				Qb.setItemCode(rs.getString(1));
			}
			return Qb;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<ClientBean> getClients(){
		
		String selectQuery = "SELECT clientid,ClientName FROM Clients";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<ClientBean> a = new ArrayList<ClientBean>();	
		ClientBean Cb = null;
		if(conn != null) {
			
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					Cb = new ClientBean(rs.getInt(1), rs.getString(2));
					a.add(Cb);
				}
				return a;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
	
	
public ArrayList<ItemBean> getItemListForIndent(String projectId){
		
		String selectQuery = "SELECT Itemname,itemcode,quantity FROM items where projectid ="+"'"+projectId+"'";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<ItemBean> a = new ArrayList<ItemBean>();	
		
		if(conn != null) {
			
			try {
				Statement pstmt = conn.createStatement();
				ResultSet rs=pstmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					ItemBean ib = new ItemBean();
					ib.setItemName(rs.getString("itemname"));
					ib.setItemCode(rs.getString("itemcode"));
					ib.setQuantity(rs.getInt("Quantity"));
					
					a.add(ib);
				}
				return a;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}

	public ArrayList<String> getItemNames(String projectId) {
		
		String selectQuery = "select itemname from items where projectId = "+"'"+projectId+"'";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> a = new ArrayList<String>();	
		
		if(conn != null) {
			
			try {
				Statement pstmt = conn.createStatement();
				ResultSet rs=pstmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					String name = rs.getString("itemname");
					
					a.add(name);
				}
				return a;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
}


