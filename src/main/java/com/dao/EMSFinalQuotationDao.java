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
		if (instance == null) {
			instance = new EMSFinalQuotationDao();
		}
		return instance;
	}

	public ArrayList<EMSFinalQuotationBean> getSumOfAllItemCodeOfAProject(int clientId) {

		String selectQuery = "SELECT SUM(TotalPrice) from offer WHERE ClientId = ? AND status = 'FALSE'";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSFinalQuotationBean> a = new ArrayList<EMSFinalQuotationBean>();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setInt(1, clientId);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					a.add(new EMSFinalQuotationBean(
							EMSFinalQuotationDao.getInstance().getCountOfPerticularOffer(clientId), rs.getString(1)));
				}
				return a;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}

	public int getCountOfPerticularOffer(int clientId) {
		String selectQuery = "select count(*) from offer where ClientId = ? AND Status = 'FALSE'";
		Connection conn = MySqlConnection.getInstance();
		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setInt(1, clientId);
				ResultSet rs = stmt.executeQuery();

				int clientId1 = 0;
				if (rs.next()) {
					clientId1 = rs.getInt(1);
				}
				return clientId1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return 0;
	}

	public boolean updateInsert() {

		return false;
	}

	public boolean updateFinalQuotation(EMSFinalQuotationBean EFQB) {

		String updateQuery = "Update Quotations SET QuotationAmount = ? , FinalDelivaryDate = ? , Quantity = ? , DiscountPercentage = ? , DiscountAmount = ?, Remarks = ? WHERE clientId = ?";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(updateQuery);
				stmt.setString(1, EFQB.getQuotationAmount());
				stmt.setString(2, EFQB.getFinalDelivaryDate());
				stmt.setInt(3, EFQB.getQuantity());
				stmt.setString(4, EFQB.getDiscountPercentage());
				stmt.setString(5, EFQB.getDiscountAmount());
				stmt.setString(6, EFQB.getRemarks());
				stmt.setInt(7, EFQB.getClientId());
				stmt.executeUpdate();
				return true;
			} catch (SQLException E) {
				E.printStackTrace();

			}
		} else {
			System.out.println("Connction is not establised!");
		}
		return false;
	}

	public EMSFinalQuotationBean getData(int clientId) {

		String selectQuery = "SELECT * FROM Quotations WHERE clientId = ?";
		Connection conn = MySqlConnection.getInstance();
		EMSFinalQuotationBean EFQB = null;
		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setInt(1, clientId);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					EFQB = new EMSFinalQuotationBean(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
				}
				return EFQB;
			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connction is not establised!");
		}
		return null;
	}

	public boolean insertIntoQuotationHistory(EMSFinalQuotationBean EFQB) {

		String insertQuery = "INSERT INTO QUOTATIONHISTORY VALUES(?,?,?,?,?,?,?,?,?)";
		Connection conn = MySqlConnection.getInstance();
		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				stmt.setInt(1, EFQB.getQuotationId());
				stmt.setInt(2, EFQB.getClientId());
				stmt.setString(3, EFQB.getQuotationDate());
				stmt.setString(4, EFQB.getQuotationAmount());
				stmt.setString(5, EFQB.getFinalDelivaryDate());
				stmt.setInt(6, EFQB.getQuantity());
				stmt.setString(7, EFQB.getDiscountPercentage());
				stmt.setString(8, EFQB.getDiscountAmount());
				stmt.setString(9, EFQB.getRemarks());
				stmt.executeUpdate();
				return true;
			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connction is not establised!");
		}
		return false;
	}

	public ArrayList<EMSFinalQuotationBean> getAllFinalQuotations() {
		ArrayList<EMSFinalQuotationBean> quotations = new ArrayList<EMSFinalQuotationBean>();
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement(
					"select quotationid,quotationdate,quotationamount,clientname,quantity,finaldelivarydate,discountpercentage,discountamount from quotations join clients using (clientid);");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
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

		} catch (Exception e) {
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean updateQuotation(String newData, String changeField, int quotationId) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con
					.prepareStatement("update quotations set " + changeField + "= ? where quotationid = ? ");
			pstmt.setString(1, newData);
			pstmt.setInt(2, quotationId);
			pstmt.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<EMSFinalQuotationBean> getHistoryFinalQuotations() {
		ArrayList<EMSFinalQuotationBean> quotations = new ArrayList<EMSFinalQuotationBean>();
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement(
					"select quotationid,quotationdate,quotationamount,clientname,quantity,finaldelivarydate,discountpercentage,discountamount from quotationhistory join clients using (clientid)");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("Entered");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
