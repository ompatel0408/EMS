package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSPurchaseBean;
import com.dbConnection.MySqlConnection;

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
	
	public boolean addPurchase(ArrayList<EMSPurchaseBean> AEPB) {
		
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
				
			}catch(SQLException E) {
				E.printStackTrace();
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
	
	
public EMSPurchaseBean getAllPurchaseOrderByUsingProjectId(String projectId,EMSPurchaseBean EPB) {
		
		String selectQuery = "SELECT I.PROJECTID,EC.Catagory,I.Quantity,I.UOM,I.REMARKS,I.ITEMNAME,CG.GRADE,CGS.SIZE FROM INDENT I JOIN EMSCatagory EC ON I.ITEMCATAGORY = EC.catagoryId JOIN CatagoryGrade CG ON CG.catagoryId = EC.catagoryId JOIN CATAGORYGRADESIZE CGS ON CG.gradeId = CGS.gradeId WHERE PROJECTId = ? AND EC.CatagoryId = ? and CG.gradeId = ? and CGS.sizeId  = ? AND isPurchased = 0";
		Connection conn = MySqlConnection.getInstance();
		EMSPurchaseBean EPB1 = null;
		if(conn != null) {
			
			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, projectId);
				stmt.setInt(2, EPB.getITEMCATAGORY());
				stmt.setInt(3, EPB.getGradeId());
				stmt.setInt(4, EPB.getSIZEId());
				ResultSet rs =stmt.executeQuery();
				while(rs.next()) {
					EPB1 = new EMSPurchaseBean(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8));
				}
				return EPB1;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection Not Esablised!");
		}
		
		return null;
	}
	
	
	public void deleteParticularPO(int poid) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("delete from postpurchase where poid = ?");
			pstmt.setInt(1, poid);
			int result=pstmt.executeUpdate();
			if(result == 1) System.out.println("Delete Purchase order Particular");
			else System.out.println("Not deleted Error Che!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public double getTotalQuotation(String projct)
	{
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select QuotationAmount from quotations join projects using (QuotationId) where projectid=?");
			pstmt.setString(1, projct);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				return (rs.getDouble("QuotationAmount"));
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
	
	
}
