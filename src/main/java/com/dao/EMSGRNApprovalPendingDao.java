package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSGRNPendingBean;
import com.dbConnection.MySqlConnection;

public class EMSGRNApprovalPendingDao {
	
	private static EMSGRNApprovalPendingDao instance;
	private static boolean isAffected = false;
	
	public static EMSGRNApprovalPendingDao getInstance() {
		
		if(instance == null) {
			instance = new EMSGRNApprovalPendingDao();
		}
		return instance;
	}
	
	public boolean addGRNApprovalPending(ArrayList<EMSGRNPendingBean> AEGPB) {
		
		String insertQuery = "INSERT INTO GRNAPPROVALPENDING(PROJECTID,MaterialCategory,CategoryId,gradeId,sizeId,Units,Quantity,RemainingQuantity) VALUES(?,?,?,?,?,?,?,?)";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				
				for(EMSGRNPendingBean EGPB:AEGPB) {
					stmt.setString(1, EGPB.getProjectId());
					stmt.setString(2, EGPB.getMaterialCategory());
					stmt.setInt(3, EGPB.getCategory());
					stmt.setInt(4, EGPB.getGrade());
					stmt.setInt(5, EGPB.getSize());
					stmt.setString(6, EGPB.getUnits());
					stmt.setInt(7, EGPB.getQuantity());
					int remQuantity = EMSGRNApprovalPendingDao.getInstance().getRemainingQuantity(EGPB.getProjectId(), EGPB.getCategoryName().concat(" ").concat(EGPB.getGradeName()), EGPB.getSizeName()) - EGPB.getQuantity();
					if(remQuantity == 0) {
						EMSGRNApprovalPendingDao.getInstance().updateRecievedItems(EGPB.getProjectId(),EGPB.getCategoryName().concat(" ").concat(EGPB.getGradeName()),EGPB.getSizeName());
					}
					stmt.setInt(8, remQuantity);
					stmt.addBatch();
				}
				stmt.executeBatch();
				return true;
			} catch(SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		
		return false;
	}
	
	public boolean isPresentAllElements(ArrayList<EMSGRNPendingBean> AEGPB) {
		
		String selectQuery = "SELECT count(*) from postpurchase WHERE (ProductDescription,Size) IN ";
		
		Connection conn  = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				for() {
					stmt.setString(1, );
					stmt.setString(2, );
				}
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getInt(1) == 0) {
						return true;
					}
				}
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return false;
	}
	
	
	public void updateRecievedItems(String projectId,String ProductDescription,String Size) {
		
		String updateQuery = " UPDATE POSTPURCHASE JOIN INDENT USING (INDENTID) SET isReceived = 1 WHERE PROJECTID = ? AND ProductDescription = ? AND Size = ?";
		Connection conn  = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(updateQuery);
				stmt.setString(1,projectId);
				stmt.setString(2, ProductDescription);
				stmt.setString(3, Size);
				stmt.executeUpdate();
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
	}
	public int getRemainingQuantity(String projectId,String ProductDescription,String Size) {
		
		String selectQuery = "select PUR.Quantity from postpurchase PUR JOIN INDENT I ON PUR.INDENTID = I.INDENTID WHERE ProjectId = ? AND ProductDescription = ? AND Size = ?";
		Connection conn  = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1,projectId );
				stmt.setString(2, ProductDescription);
				stmt.setString(3, Size);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()){
					return rs.getInt(1);
				}
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		return 0;
	}
	
	
	public ArrayList<EMSGRNPendingBean> getAllData(){
		
		String selectQuery = "SELECT * FROM GRNAPPROVALPENDING";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSGRNPendingBean> arr = new ArrayList<EMSGRNPendingBean>();
		
		if(conn != null) {
			
			try {
				
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					arr.add(new EMSGRNPendingBean(rs.getString(2),rs.getString(1), QuotationPerItemDao.getInstance().getCatagoryName(rs.getInt(3)), QuotationPerItemDao.getInstance().getGradeName(rs.getInt(4)), QuotationPerItemDao.getInstance().getSizeName(rs.getInt(5)), rs.getString(6), rs.getInt(7),rs.getInt(8)));
				}
				return arr;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}	
}
