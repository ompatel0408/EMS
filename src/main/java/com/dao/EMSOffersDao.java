package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSOffersBean;
import com.bean.ItemBean;
import com.dbConnection.*;

public class EMSOffersDao {
	private static EMSOffersDao instance = null;

	private EMSOffersDao() {}

	public static EMSOffersDao getInstance() {
		if (instance == null) {
			instance = new EMSOffersDao();
		}
		return instance;
	}

	
	
	
//	public ArrayList<EMSOffersBean> getAllOffer() {
//		ArrayList<EMSOffersBean> offers = new ArrayList<EMSOffersBean>();
//		try {
//			Connection con = MySqlConnection.getInstance();
//			PreparedStatement pstmt = con.prepareStatement("select * from offer");
//			ResultSet rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				EMSOffersBean offer = new EMSOffersBean();
////				offer.setDeliveryDate(rs.getString("deliverydate"));
////				offer.setItemName(rs.getString("itemname"));
//				offer.setQuantity(rs.getInt("quantity"));
//				offer.setRemarks(rs.getString("remarks"));
//				offer.setClientId(rs.getInt("CLIENTID"));
//				offers.add(offer);
//			}
//			return offers;
//			
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public boolean addOffer(ArrayList<EMSOffersBean> indent) {
		
		String insertQuery = "insert into offer(ClientId,OfferCode,OfferName,TotalPrice,Quantity,QuotationId,DrawingId,Remarks) values (?,?,?,?,?,?,?,?)";
		
		Connection con = MySqlConnection.getInstance();		
		try {
			PreparedStatement pstmt = con.prepareStatement(insertQuery);
			for(EMSOffersBean qb:indent) {
				pstmt.setInt(1,qb.getClientId());
				pstmt.setString(2, qb.getOfferCode());
				pstmt.setString(3,qb.getOfferName());
				pstmt.setString(4, qb.getTotalPrice());
				pstmt.setInt(5,qb.getQuantity());
				pstmt.setInt(6,qb.getQuotationId());
				pstmt.setString(7,qb.getDrawingId());
				pstmt.setString(8,qb.getRemarks());
				pstmt.addBatch();
			}
			int[] result = pstmt.executeBatch();
			System.out.println("Result -------->"+result);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public ArrayList<EMSOffersBean> getAllData(String ClientName) {
		String selectQuery = "SELECT OFFERName,OfferCode,quantity,remarks from clients c join offer o ON c.ClientId = o.ClientId  WHERE ClientName = ?";
		Connection conn = MySqlConnection.getInstance();
		EMSOffersBean EOB = null;
		ArrayList<EMSOffersBean> arr = new ArrayList<EMSOffersBean>();
		if(conn != null) {
			
			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, ClientName);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					EOB = new EMSOffersBean(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
					arr.add(EOB);
				}
				return arr;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
	
	public EMSOffersBean getOfferId() {
		String selectQuery = "SELECT offerCode FROM offer ORDER BY offerCode DESC LIMIT 1";
		Connection conn = MySqlConnection.getInstance();
		EMSOffersBean Qb = new EMSOffersBean();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);
			
			if(rs.next()) { 
				Qb.setOfferCode(rs.getString(1));
			}
			return Qb;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<EMSOffersBean> getAllOffer() {
		ArrayList<EMSOffersBean> offers = new ArrayList<EMSOffersBean>();
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select * from offer");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EMSOffersBean offer = new EMSOffersBean();
				offer.setOfferCode(rs.getString("offercode"));
				offer.setOfferName(rs.getString("offername"));
				offer.setQuantity(rs.getInt("quantity"));
				offer.setRemarks(rs.getString("remarks"));
				offer.setDrawingId(rs.getString("drawingid"));
				offer.setClientId(rs.getInt("CLIENTID"));
				offers.add(offer);
			}
			return offers;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList<String> getOfferCodeFromdatabase(String clientName){
		
		String selectQuery = "SELECT OFFERCODE FROM OFFER JOIN CLIENTS USING(CLIENTID) WHERE CLIENTNAME = ?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> a = new ArrayList<String>();
		if(conn != null) {
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, clientName);
				ResultSet rs= stmt.executeQuery();
				while(rs.next()) {
					a.add(rs.getString(1));
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
	
	public void updateStatus(ArrayList<String> offerCode) {
		
		String updateQuery = "UPDATE offer SET STATUS = 'ACCEPTED' WHERE OFFERCODE = ?";
		Connection con = MySqlConnection.getInstance();		
		
		if(con != null) {
			
			try {
				
				PreparedStatement stmt = con.prepareStatement(updateQuery);
				
				for(String s:offerCode) {
					stmt.setString(1,s);
					stmt.addBatch();
				}
				stmt.executeBatch();
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
	}
	
	public void updateStatus(int clientId) {
		
		String updateQuery = "UPDATE offer SET STATUS = 'REJECTED' WHERE ClientId = ?";
		Connection con = MySqlConnection.getInstance();		
		
		if(con != null) {
			
			try {
				
				PreparedStatement stmt = con.prepareStatement(updateQuery);	
				stmt.setInt(1, clientId);
				stmt.executeUpdate();
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		} 
	}
	
	
}