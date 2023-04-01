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
		
		//String selectQuery = "SELECT sum(TotalAmountWithProfit * ) FROM offer I JOIN ProfitInQuotationPerItem PQI ON I.offerCode = PQI.offerCode WHERE clientId= ? ";
		String selectQuery = "SELECT SUM(TotalPricePerItem * QuotationPerItemQuantity ) from quotationperitem QPI JOIN Offer O ON QPI.offerCode = O.offerCode WHERE ClientId = ?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSFinalQuotationBean> a = new ArrayList<EMSFinalQuotationBean>();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setInt(1, clientId);
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()) {
					a.add(new EMSFinalQuotationBean(EMSFinalQuotationDao.getInstance().getCountOfPerticularOffer(clientId),rs.getString(1)));
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
	
	public int getCountOfPerticularOffer(int clientId) {
		String selectQuery = "select count(*) from offer where ClientId = ?";
		Connection conn = MySqlConnection.getInstance();
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setInt(1, clientId);
				ResultSet rs = stmt.executeQuery();
				
				int clientId1 = 0;
				if(rs.next()) {
					clientId1=rs.getInt(1);
				}
				return clientId1;
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return 0;
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
	
	public ArrayList<EMSFinalQuotationBean> getAllFinalQuotations() {
		ArrayList<EMSFinalQuotationBean> quotations = new ArrayList<EMSFinalQuotationBean>();
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select quotationid,quotationdate,quotationamount,clientname,quantity,finaldelivarydate,discountpercentage,discountamount from quotations join clients using (clientid);");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EMSFinalQuotationBean quotation = new EMSFinalQuotationBean();
				quotation.setQuotationId(rs.getInt("quotationid"));
				quotation.setQuotationDate(rs.getString("quotationdate"));
				quotation.setQuotationAmount(rs.getString("quotationamount"));
				quotation.setClientName(rs.getString("clientname"));
				quotation.setDiscountPercentage(rs.getString("discountpercentage"));
				quotation.setDiscountAmount(rs.getString("discountamount"));
				quotation.setQuantity(rs.getInt("quantity"));
				quotation.setFinalDelivaryDate(rs.getString("finaldelivaryDate"));
				quotations.add(quotation);
			}
			return quotations;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteQuotation(int quotationId) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("delete from quotations where quotationid = ?");
			pstmt.setInt(1, quotationId);
			pstmt.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void updateQuotation(String newData, String changeField, int quotationId) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("update quotations set " + changeField + "= ? where quotationid = ? ");
			pstmt.setString(1, newData);
			pstmt.setInt(2, quotationId);
			pstmt.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
