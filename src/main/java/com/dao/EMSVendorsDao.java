package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.EMSPurchaseBean;
import com.bean.EMSVendorsBean;
import com.dbConnection.MySqlConnection;
public class EMSVendorsDao {
	
	
		
		public static EMSVendorsDao instance = null;

		public static EMSVendorsDao getInstance() {

			if (instance == null) {
				instance = new EMSVendorsDao();
			}
			return instance;
		}

		
		public boolean addVendor(ArrayList<EMSVendorsBean> vendor) {

			String insertQuery = "INSERT INTO vendors(vendorName,email,address,phonenumber) VALUES(?,?,?,?)";

			Connection con = MySqlConnection.getInstance();

			try {
				PreparedStatement pstmt = con.prepareStatement(insertQuery);

				for (EMSVendorsBean qb : vendor) {
					pstmt.setString(1, qb.getVendorName());
					pstmt.setString(2, qb.getEmail());
					pstmt.setString(3, qb.getAddress());
					pstmt.setString(4, qb.getMobile());
					pstmt.addBatch();
				}
				int[] result = pstmt.executeBatch();
				System.out.println("Result -------->" + result);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return false;
		}
		
		public ArrayList<EMSVendorsBean> getAllVendor() {
			ArrayList<EMSVendorsBean> vendors = new ArrayList<EMSVendorsBean>();
			try {
				Connection con = MySqlConnection.getInstance();
				PreparedStatement pstmt = con.prepareStatement("select vendorName,email,address,phonenumber,vendorId from vendors");
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					EMSVendorsBean vendor = new EMSVendorsBean();
					vendor.setVendorName(rs.getString(1));
					vendor.setEmail(rs.getString(2));
					vendor.setAddress(rs.getString(3));
					vendor.setMobile(rs.getString(4));
					vendor.setVendorId(rs.getInt(5));
					vendors.add(vendor);
				}
				return vendors;
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public boolean deleteVendor(int vendorId) {
			try {
				Connection con = MySqlConnection.getInstance();
				PreparedStatement pstmt = con.prepareStatement("delete from vendors where vendorid = ?");
				pstmt.setInt(1, vendorId);
				pstmt.executeUpdate();
				return true;
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return false;
			
		}
		
		
		
		public boolean updateVendor(String newData, String changeField, int vendorId) {
			try {
				Connection con = MySqlConnection.getInstance();
				PreparedStatement pstmt = con.prepareStatement("update vendors set " + changeField + "= ? where vendorid = ? ");
				pstmt.setString(1, newData);
				pstmt.setInt(2, vendorId);
				pstmt.executeUpdate();
				return true;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		
		public EMSVendorsBean getVendor(String vendorName) {

			try {
				Connection con = MySqlConnection.getInstance();
				PreparedStatement pstmt = con.prepareStatement(
						"select vendorName,email,address,phonenumber,vendorId from vendors where vendorname=?");
				pstmt.setString(1, vendorName);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					EMSVendorsBean vendor = new EMSVendorsBean();
					vendor.setVendorName(rs.getString(1));
					vendor.setEmail(rs.getString(2));
					vendor.setAddress(rs.getString(3));
					vendor.setMobile(rs.getString(4));
					vendor.setVendorId(rs.getInt(5));
					return vendor;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public EMSPurchaseBean getPoDet(String projectName) {
			try {
				Connection con = MySqlConnection.getInstance();
				EMSPurchaseBean podet  = null;
				PreparedStatement pstmt = con.prepareStatement(
						"select ponumber,indentid,currentdate from postpurchase join indent using(indentid)  where projectid=?");
				pstmt.setString(1, projectName);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					podet = new EMSPurchaseBean();
					podet.setPONumber(rs.getString("ponumber"));
					podet.setIndentId(rs.getInt("indentid"));
					podet.setCurrentDate(rs.getString("currentdate"));
				}
				return podet;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public ArrayList<EMSPurchaseBean> getPOVendorDet(String vendorName,String project) {
			ArrayList<EMSPurchaseBean> vendors = new ArrayList<EMSPurchaseBean>();
			try {
				Connection con = MySqlConnection.getInstance();
				PreparedStatement pstmt = con.prepareStatement(
						"select productdescription,size,quantity,units,rateperkg,discount,rateperkg*quantity netAmuount,sgst,cgst,round((totalAmount*(sgst+cgst))/(100),2) taxableValue,totalAmount,PaymentTerms from postpurchase join indent using(indentid) where vendorname=? and projectid=?");
				pstmt.setString(1, vendorName);
				pstmt.setString(2, project);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					EMSPurchaseBean vendor = new EMSPurchaseBean();
					vendor.setProductDescription(rs.getString(1));
					vendor.setSize(rs.getString(2));
					vendor.setQuantity(rs.getInt(3));
					vendor.setUom(rs.getString(4));
					vendor.setRatePerKg(rs.getString(5));
					vendor.setDiscount(rs.getString(6));
					vendor.setNetAmount(rs.getString(7));
					vendor.setSGST(rs.getDouble(8));
					vendor.setCGST(rs.getDouble(9));
					vendor.setTaxableValue(rs.getDouble(10));
					vendor.setTotalAmount(rs.getDouble(11));
					vendor.setPaymentTerms(rs.getString(12));
					vendors.add(vendor);
				}
				return vendors;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public String getVendorMailFromDatabase(String vendorName) {
			
			String selectQuery = "SELECT Email from vendors WHERE VendorName = ?";
			Connection conn = MySqlConnection.getInstance();
			
			if(conn != null) {
				
				try {
					
					PreparedStatement stmt = conn.prepareStatement(selectQuery);
					stmt.setString(1, vendorName);
					ResultSet rs = stmt.executeQuery();
					String vendorEmail = "";
					while(rs.next()) {
						vendorEmail = rs.getString(1);
					}
					return vendorEmail;
					
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}else {
				System.out.println("Connection is not establised!");
			}
			return null;
		}
		
		
	}