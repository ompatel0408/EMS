package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	}