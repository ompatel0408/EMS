package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSGRNBean;
import com.bean.EMSGRNPendingBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSGRNDao {

	private static EMSGRNDao instance = null;

	public static EMSGRNDao getInstance() {

		if (instance == null) {
			instance = new EMSGRNDao();
		}
		return instance;
	}

	public boolean addGRN(EMSGRNBean EGB,HttpServletRequest request,HttpServletResponse response) {

		String insertQuery = "INSERT INTO GRN(VENDORNAME,ReceiveDate,PATH1,PATH2,InvoiceNumber) VALUES(?,?,?,?,?)";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				stmt.setString(1, EGB.getVendorName());
				stmt.setString(2, EGB.getReceiveDate());
				stmt.setString(3, EGB.getPath1());
				stmt.setString(4, EGB.getPath2());
				stmt.setString(5, EGB.getInvoiceNumber());
				stmt.executeUpdate();
				return true;

			} catch (SQLException e) {
				try {
					ExceptionHandler.handleException(request, response, e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return false;
	}

	public ArrayList<EMSGRNBean> getAllData() {

		String selectQuery = "SELECT * FROM GRN";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSGRNBean> a = new ArrayList<EMSGRNBean>();
		EMSGRNBean EGB = null;
		if (conn != null) {

			try {

				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);

				while (rs.next()) {
					EGB = new EMSGRNBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
					a.add(EGB);
				}
				return a;

			} catch (SQLException E) {
				E.printStackTrace();
			}

		} else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}

	public boolean deleteGRN(int grnId,HttpServletRequest request,HttpServletResponse response) {

		String deleteQuery = "DELETE FROM GRN WHERE GRNID = ?";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(deleteQuery);
				stmt.setInt(1, grnId);
				stmt.executeUpdate();
				return true;

			} catch (SQLException e) {
				try {
					ExceptionHandler.handleException(request, response, e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return false;
	}

	public ArrayList<EMSGRNBean> getAllPurchaseDetails(String projectId) {

		String selectQuery = "select P.ProductDescription,P.Size,sum(P.Quantity) Quantity,P.UNITS FROM Indent I JOIN POSTPURCHASE P ON I.INDENTID = P.INDENTID  WHERE ProjectId = ? AND isReceived = 0 group by p.productDescription,p.size,P.units";
		
		Connection conn = MySqlConnection.getInstance();
		
		ArrayList<EMSGRNBean> arr = new ArrayList<EMSGRNBean>();
		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, projectId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					String[] a = rs.getString(1).split(" ");
					arr.add(new EMSGRNBean(a[0], a[1], rs.getString(2), rs.getInt(3),rs.getString(4)));
				}
				return arr;
			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
	
	public ArrayList<EMSGRNBean> getAllDrtailsFromGRNApprovalPending(String projectId) {

		String selectQuery = "select catagory,grade,size,RemainingQuantity,units from grnapprovalpending GAP JOIN EMScatagory E ON E.CatagoryId = GAP.categoryId join catagoryGrade using(gradeId) join catagoryGradeSize using(SizeId) WHERE ProjectId = ? AND isReceived = 0";
		
		Connection conn = MySqlConnection.getInstance();
		
		ArrayList<EMSGRNBean> arr = new ArrayList<EMSGRNBean>();
		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, projectId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					arr.add(new EMSGRNBean(rs.getString(1),rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5)));
				}
				return arr;
			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
	
	public ArrayList<EMSGRNPendingBean> getAllDetails(String projectId){
		
		String selectQuery = "";
		
		Connection conn = MySqlConnection.getInstance();
		
		ArrayList<EMSGRNPendingBean> arr = new ArrayList<EMSGRNPendingBean>();
		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, projectId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					
//					arr.add();
				}
				return arr;
			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		
		return null;
	}
}
