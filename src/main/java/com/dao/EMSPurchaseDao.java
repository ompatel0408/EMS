package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSPurchaseBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSPurchaseDao {
	
	private static EMSPurchaseDao instance = null;
	
	public static EMSPurchaseDao getInstance() {
		
		if(instance == null) {
			instance = new EMSPurchaseDao();
		}
		return instance;
	}
	
	public ArrayList<String> getCatagoryFromDataBase() {
		
		String selectQuery = "SELECT Catagory FROM EMSCatagory";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> catagory = new ArrayList<String>();
		if(conn != null) {
			
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs =stmt.executeQuery(selectQuery);

				while(rs.next()) {
					catagory.add(rs.getString(1));
				}
				return catagory;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection Not Esablised!");
		}
		
		return null;
	}
	
	public int getIndentId(String projectId) {
		
		String selectQuery = "SELECT IndentID FROM Indent WHERE ProjectId = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, projectId);
				ResultSet rs = stmt.executeQuery();
				int indentId = 0;
				if(rs.next()) {
					indentId = rs.getInt(1);
				}
				return indentId;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return 0;
	}
	
	public boolean addPurchase(ArrayList<EMSPurchaseBean> AEPB,HttpServletRequest request,HttpServletResponse response) {
		
		String insertQuery = "INSERT INTO POSTPURCHASE(INDENTID,ProductDescription,Size,Quantity,UNITS,RatePerKg,discount,TotalAmount,SGST,CGST,CurrentDate,PONumber,VendorName,PaymentTerms) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				for(EMSPurchaseBean EPB:AEPB) {
					stmt.setInt(1,EPB.getIndentId());
					stmt.setString(2, EPB.getProductDescription());
					stmt.setString(3, EPB.getSize());
					stmt.setInt(4, EPB.getQuantity());
					stmt.setString(5, EPB.getUom());
					stmt.setString(6, EPB.getRatePerKg());
					stmt.setString(7, EPB.getDiscount());
					stmt.setString(8, String.valueOf(EPB.getNetAmount()));
					stmt.setString(9, String.valueOf(EPB.getSGST()));
					stmt.setString(10, String.valueOf(EPB.getCGST()));
					stmt.setString(11, EPB.getCurrentDate());
					stmt.setString(12, EPB.getPONumber());
					stmt.setString(13, EPB.getVendorName());
					stmt.setString(14, EPB.getPaymentTerms());
					stmt.addBatch();
				}
				stmt.executeBatch();
				return true;
				
			}catch(SQLException e) {
				try {
					ExceptionHandler.handleException(request, response, e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
		}else {
			System.out.println("Connction is not establised!");
		}
		
		return false;
	}
	
	public ArrayList<String> getVendorNameFromDatabase(){
		
		String selectQuery = "SELECT VENDORNAME FROM vendors";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> a = new ArrayList<String>();
		if(conn != null) {
			
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs  = stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					a.add(rs.getString(1));
				}
				return a;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establisd!");
		}
		
		return null;
	}
	
	public ArrayList<EMSPurchaseBean> getAllPurchaseOrder() {
		
		String selectQuery = "SELECT * from postpurchase";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSPurchaseBean> pos = new ArrayList<EMSPurchaseBean>();
		if(conn != null) {
			
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs =stmt.executeQuery(selectQuery);

				while(rs.next()) {
					EMSPurchaseBean ebean = new EMSPurchaseBean();
					ebean.setPoId(rs.getInt("poid"));
					ebean.setCGST(rs.getDouble("CGST"));
					ebean.setSGST(rs.getDouble("SGST"));
					ebean.setIndentId(rs.getInt("Indentid"));
					ebean.setProductDescription(rs.getString("productdescription"));
					ebean.setSize(rs.getString("SIZE"));
					ebean.setQuantity(rs.getInt("Quantity"));
					ebean.setUom(rs.getString("UNITS"));
					ebean.setRatePerKg(rs.getString("rateperkg"));
					ebean.setDiscount(rs.getString("discount"));
					ebean.setCurrentDate(rs.getString("currentDate"));
					ebean.setPONumber(rs.getString("PONumber"));
					ebean.setVendorName(rs.getString("vendorName"));
					ebean.setNetAmount(rs.getString("TotalAmount"));
					
					pos.add(ebean);
				}
				return pos;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection Not Esablised!");
		}
		
		return null;
	}
	
	
public ArrayList<EMSPurchaseBean> getAllPurchaseOrderByUsingProjectId(String projectId) {
		
		String selectQuery = "select PROJECTID,catagory,Quantity,UOM,REMARKS,ITEMNAME,grade,size,indentId from indent i join emscatagory ec on i.itemcatagory=ec.catagoryid join catagorygrade using(gradeid) join catagorygradesize using(sizeid) WHERE PROJECTID = ? AND isPurchased = 0";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSPurchaseBean> arr = new ArrayList<EMSPurchaseBean>();
		if(conn != null) {
			
			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, projectId);
				ResultSet rs =stmt.executeQuery();
				while(rs.next()) {
					arr.add(new EMSPurchaseBean(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getInt(9)));
				}
				return arr;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection Not Esablised!");
		}
		
		return null;
	}
	
	
	public void deleteParticularPO(int poid,HttpServletRequest request,HttpServletResponse response) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("delete from postpurchase where poid = ?");
			pstmt.setInt(1, poid);
			int result=pstmt.executeUpdate();
			if(result == 1) System.out.println("Delete Purchase order Particular");
			else System.out.println("Not deleted Error Che!");
		}
		catch(Exception e) {
			try {
				ExceptionHandler.handleException(request, response, e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
	}
	
	public double getTotalQuotation(String projct)
	{
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select sum(TotalPricePerItem) from quotationperitem qo join items i on i.itemcode = qo.offercode where projectid=?");
			pstmt.setString(1, projct);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				return (rs.getDouble(1));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<EMSPurchaseBean> getSpecificData(String projectId) {
		
		String selectQuery = "select ITEMCATAGORY,GradeId,SIZEId from indent where PROJECTID = ?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSPurchaseBean> AEPB = new ArrayList<EMSPurchaseBean>();
		EMSPurchaseBean EPB= null;
		if(conn != null) {
			
			
			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, projectId);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					EPB  = new EMSPurchaseBean(rs.getInt(1), rs.getInt(2), rs.getInt(3));
					AEPB.add(EPB);
				}
				return AEPB;
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
		
	}
	public Double getTotalPrice(String project, String vendor) {
		
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select distinct TransportationPrice from postpurchase join indent using(indentid) where projectId=? and vendorname=?");
			pstmt.setString(1, project);
			pstmt.setString(2, vendor);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				return (rs.getDouble(1));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0.0;
	}
	
	
}
