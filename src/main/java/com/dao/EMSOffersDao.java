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
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSOffersDao {
	private static EMSOffersDao instance = null;

	private EMSOffersDao() {}

	public static EMSOffersDao getInstance() {
		if (instance == null) {
			instance = new EMSOffersDao();
		}
		return instance;
	}

	
	public boolean addOffer(ArrayList<EMSOffersBean> indent,HttpServletRequest request,HttpServletResponse response) {
		
		String insertQuery = "insert into offer(ClientId,OfferCode,OfferName,TotalPrice,Quantity,QuotationId,DrawingId,Remarks,addDate,address) values (?,?,?,?,?,?,?,?,?,?)";
		
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
				pstmt.setString(9, qb.getDate());
				pstmt.setString(10, qb.getAddess());
				pstmt.addBatch();
			}
			int[] result = pstmt.executeBatch();
			return true;
		}catch(SQLException e) {
			try {
				ExceptionHandler.handleException(request, response, e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

		}
		
		return false;
	}
	
	public ArrayList<EMSOffersBean> getAllData(String ClientName) {
		String selectQuery = "SELECT OFFERName,OfferCode,quantity,remarks from clients c join offer o ON c.ClientId = o.ClientId  WHERE ClientName = ? AND STATUS = 'FALSE'";
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
		
		
		for(String x:offerCode) {
			System.out.println("OfferCode -->"+offerCode);
		}
		System.out.println("Update status ArrayList reached!!!!!!!!!!!!!!!");
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
		
		String updateQuery = "UPDATE offer SET STATUS = 'REJECTED' WHERE ClientId = ? AND status = 'FALSE'";
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
	
	
	public String getOfferNameFromDatabase(String offerCode) {
		
		String selectQuery = "SELECT offerName from offer WHERE offerCode = ?";	
		Connection con = MySqlConnection.getInstance();		
		
		if(con != null) {
			try {
				PreparedStatement stmt = con.prepareStatement(selectQuery);	
				stmt.setString(1, offerCode);
				ResultSet rs =stmt.executeQuery();
				
				String offerName = "";
				if(rs.next()) {
					offerName = rs.getString(1);
				}
				return offerName;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		} 
		return null;
	}
	
	
}