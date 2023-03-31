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
	
	public ArrayList<String> getProjects(){
		
		String selectQuery = "SELECT projectId FROM projects";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> a = new ArrayList<String>();	
		if(conn != null) {
			
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(selectQuery);
				String projectId = "";
				while(rs.next()) {
					projectId = rs.getString(1);
					a.add(projectId);
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
	
		String selectQuery = "select ItemName,itemcode,quantity from projects P JOIN Items I ON P.quotationId = I.QuotationId WHERE ProjectId = ?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<ItemBean> a = new ArrayList<ItemBean>();	
		
		if(conn != null) {
			
			try {
				PreparedStatement pstmt = conn.prepareStatement(selectQuery);
				pstmt.setString(1, projectId);
				ResultSet rs=pstmt.executeQuery();
				
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
		
		String selectQuery = "select ItemName from projects P JOIN Items I ON P.quotationId = I.QuotationId WHERE ProjectId = ?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> a = new ArrayList<String>();	
		
		if(conn != null) {
			
			try {
				PreparedStatement pstmt = conn.prepareStatement(selectQuery);
				pstmt.setString(1, projectId);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String name = rs.getString(1);
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
	
	public ItemBean getQuotationIdFromdatabase(String clientName) {
		
		String selectQuery = "select ClientId,QuotationId,CLIENTNAME from items I JOIN Clients C Using(ClientId) group by clientId,QuotationId,CLIENTNAME HAVING CLIENTNAME = ?";
		Connection conn = MySqlConnection.getInstance();
		ItemBean ib = null;
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, clientName);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					ib = new ItemBean(rs.getInt(1), rs.getInt(2));
				}
				return ib;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
	
	
	public ArrayList<ItemBean> getAllItems() {
		ArrayList<ItemBean> items = new ArrayList<ItemBean>();
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select itemcode,itemname,clientname,tagno,quantity,deliverydate from items join clients using (clientid);");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemBean item = new ItemBean();
				item.setItemCode(rs.getString("itemCode"));
				item.setItemName(rs.getString("itemname"));
				item.setClientName(rs.getString("clientname"));
				item.setTagNo(rs.getString("tagno"));
				item.setQuantity(rs.getInt("quantity"));
				item.setDeliveryDate(rs.getString("deliveryDate"));
				items.add(item);
			}
			return items;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteItem(String itemCode) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("delete from items where itemcode = ?");
			pstmt.setString(1, itemCode);
			pstmt.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateItem(String newData, String changeField, String itemCode) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("update items set " + changeField + "= ? where itemcode = ? ");
			pstmt.setString(1, newData);
			pstmt.setString(2, itemCode);
			pstmt.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}


