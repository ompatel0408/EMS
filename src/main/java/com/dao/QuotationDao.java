package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.QuotationBean;
import com.dbConnection.MySqlConnection;

public class QuotationDao {
	
	public static boolean addQuotation(QuotationBean Qb) {
		
		String insertQuery = "INSERT INTO Quotations(ProjectId,QuotationDate,QuotationAmount,FinalDelivaryDate) VALUES(?,?,?,?)";
		Connection conn = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement(insertQuery);
			stmt.setString(1, Qb.getProjectId());
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
	public static QuotationBean getQuotationIdFromDataBase(String ProjectId) {
		
		String SelectQuery = "SELECT QuotationId FROM quotations WHERE ProjectId = ?";
		Connection conn = MySqlConnection.getInstance();
		QuotationBean Qb = new QuotationBean();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = conn.prepareStatement(SelectQuery);
			stmt.setString(1, ProjectId);
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
}
