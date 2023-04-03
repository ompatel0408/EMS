package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.QuotationBean;
import com.dbConnection.MySqlConnection;

public class QuotationDao {
	
	private static QuotationDao instance = null;
	
	public static QuotationDao getInstance() {
		
		if(instance == null) {
			instance = new QuotationDao();
		}
		return instance;
	}
	
	public static boolean addQuotation(QuotationBean Qb) {
		
		String insertQuery = "INSERT INTO Quotations(ClientId,QuotationDate,QuotationAmount,FinalDelivaryDate) VALUES(?,?,?,?)";
		Connection conn = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement(insertQuery);
			stmt.setInt(1, Qb.getClientId());
			stmt.setString(2, Qb.getQuotationDate());
			stmt.setLong(3, Qb.getQuotationAmount());
			stmt.setString(4, Qb.getFinalDeliveryDate());
			stmt.executeUpdate();
			return true;
		}catch(SQLException E) {
			E.printStackTrace();
		}
		
		return false;
		
	}
	public static QuotationBean getQuotationIdFromDataBase(int clientId) {
		
		String SelectQuery = "SELECT QuotationId FROM quotations WHERE clientId = ?";
		Connection conn = MySqlConnection.getInstance();
		QuotationBean Qb = new QuotationBean();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(SelectQuery);
			stmt.setInt(1, clientId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Qb.setQuotationId(rs.getInt(1));
				return Qb;
			}
			
		}catch(SQLException E) {
			E.printStackTrace();
		}
		return null;
	}
	
	public boolean updateQuotations(int clientId) {
		
		String updateQuery = "UPDATE Quotations SET QuotationAmount = 0,FinalDelivaryDate = null,Quantity = null,DiscountPercentage = null,DiscountAmount = null,Remarks = null WHERE ClientId = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				PreparedStatement stmt = conn.prepareStatement(updateQuery);
				stmt.setInt(1, clientId);
				stmt.executeUpdate();
				return true;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return false;
	}
	
}
