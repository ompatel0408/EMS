package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSFinalQuotationBean;
import com.dbConnection.MySqlConnection;

public class EMSFinalQuotationDao {
	
	private static EMSFinalQuotationDao instance = null;
	
	public static EMSFinalQuotationDao getInstance() {
		if(instance == null) {
			instance = new EMSFinalQuotationDao();
		}
		return instance;
	}
	
	public ArrayList<EMSFinalQuotationBean> getSumOfAllItemCodeOfAProject(int clientId){
		
		
		//SELECT SUM(subquery.TotalAmountWithProfit) AS Total FROM (SELECT I.ItemCode, SUM(PQI.TotalAmountWithProfit) AS TotalAmountWithProfit   FROM items I   JOIN ProfitInQuotationPerItem PQI ON I.ItemCode = PQI.ItemCode   WHERE I.projectId = ?  GROUP BY I.ItemCode) subquery
		String selectQuery = "SELECT count(*),sum(TotalAmountWithProfit) FROM offer I JOIN ProfitInQuotationPerItem PQI ON I.offerCode = PQI.offerCode WHERE clientId= ? ";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSFinalQuotationBean> a = new ArrayList<EMSFinalQuotationBean>();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setInt(1, clientId);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					a.add(new EMSFinalQuotationBean(rs.getInt(1), rs.getString(2)));
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
	
	public boolean insertFinalQuotation(EMSFinalQuotationBean EFQB) {
		
		String updateQuery = "Update Quotations SET QuotationAmount = ? , FinalDelivaryDate = ? , Quantity = ? , DiscountPercentage = ? , DiscountAmount = ?, Remarks = ? WHERE clientId = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(updateQuery);
				stmt.setString(1,EFQB.getQuotationAmount());
				stmt.setString(2,EFQB.getFinalDelivaryDate());
				stmt.setInt(3,EFQB.getQuantity());
				stmt.setString(4,EFQB.getDiscountPercentage());
				stmt.setString(5,EFQB.getDiscountAmount());
				stmt.setString(6, EFQB.getRemarks());
				stmt.setInt(7,EFQB.getClientId());
				stmt.executeUpdate();
				return true;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connction is not establised!");
		}
		return  false;
	}
	
}
