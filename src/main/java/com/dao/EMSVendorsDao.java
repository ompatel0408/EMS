package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.EMSPurchaseBean;
import com.bean.EMSVendorsBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class EMSVendorsDao {
	
	
		
		public static EMSVendorsDao instance = null;

		public static EMSVendorsDao getInstance() {

			if (instance == null) {
				instance = new EMSVendorsDao();
			}
			return instance;
		}

		
		public boolean addVendor(ArrayList<EMSVendorsBean> vendor,HttpServletRequest request,HttpServletResponse response) {

			String insertQuery = "INSERT INTO vendors(vendorName,email,address,phonenumber,PhoneNumber1,Email1,GST,PanNumber,BankName,ACNumber,IFSC,Remarks) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			Connection con = MySqlConnection.getInstance();

			try {
				PreparedStatement pstmt = con.prepareStatement(insertQuery);

				for (EMSVendorsBean qb : vendor) {
					pstmt.setString(1, qb.getVendorName());
					pstmt.setString(2, qb.getEmail());
					pstmt.setString(3, qb.getAddress());
					pstmt.setString(4, qb.getMobile());
					pstmt.setString(5, qb.getMobile1());
					pstmt.setString(6, qb.getEmail1());
					pstmt.setString(7, qb.getGst());
					pstmt.setString(8, qb.getPanNumber());
					pstmt.setString(9, qb.getBankName());
					pstmt.setString(10, qb.getACNumber());
					pstmt.setString(11, qb.getIFSC());
					pstmt.setString(12, qb.getRemarks());
					pstmt.addBatch();
				}
				int[] result = pstmt.executeBatch();
				return true;
			} catch (SQLException e) {
				try {
					ExceptionHandler.handleException(request, response, e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

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
		
		public boolean deleteVendor(int vendorId,HttpServletRequest request,HttpServletResponse response) {
			try {
				Connection con = MySqlConnection.getInstance();
				PreparedStatement pstmt = con.prepareStatement("delete from vendors where vendorid = ?");
				pstmt.setInt(1, vendorId);
				pstmt.executeUpdate();
				return true;
				
			}
			catch (Exception e) {
				try {
					ExceptionHandler.handleException(request, response, e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
			return false;
			
		}
		
		
		
		public boolean updateVendor(String newData, String changeField, int vendorId,HttpServletRequest request,HttpServletResponse response) {
			try {
				Connection con = MySqlConnection.getInstance();
				PreparedStatement pstmt = con.prepareStatement("update vendors set " + changeField + "= ? where vendorid = ? ");
				pstmt.setString(1, newData);
				pstmt.setInt(2, vendorId);
				pstmt.executeUpdate();
				return true;
			}
			catch (Exception e) {
				try {
					ExceptionHandler.handleException(request, response, e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

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
						"select productdescription,size,p.quantity,units,rateperkg,discount,rateperkg*(p.quantity) netAmuount,sgst,cgst,round((totalAmount*(sgst+cgst))/(100),2) taxableValue,totalAmount,PaymentTerms,transportationprice from postpurchase p join indent i using(indentid) where vendorname=? and projectid=?");
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
					vendor.setTransportPrice(rs.getDouble(13));
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
		
		public int getVendorIdfromDba(String name)
		{
			Connection conn = MySqlConnection.getInstance();
			String query = "select vendorId from vendors where vendorname = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, name);
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next())
				{
					return rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return -1;
		}
		
		public String getVendorNamefromDba(int id)
		{
			Connection conn = MySqlConnection.getInstance();
			String query = "select vendorName from vendors where vendorId = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next())
				{
					return rs.getString(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		public Boolean updatePrice(String vendor, String projectId, String price,HttpServletRequest request,HttpServletResponse response) {
			try {
				System.out.println("GetId");
				Connection con = MySqlConnection.getInstance();
				PreparedStatement pstmt = con.prepareStatement("update postpurchase join indent using(indentid) set transportationPrice =?where projectId=? and vendorname=?");
				pstmt.setString(1, price);
				pstmt.setString(2, projectId);
				pstmt.setString(3, vendor);
				int count=pstmt.executeUpdate();
				System.out.println(count);
				return true;
			}
			catch (Exception e) {
				try {
					ExceptionHandler.handleException(request, response, e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
			return true;
			
		}
		
	}