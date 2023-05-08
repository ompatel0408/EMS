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
		String updateQuery = "UPDATE GRNAPPROVALPENDING SET Quantity = quantity + ? , RemainingQuantity = ? WHERE PROJECTID = ? AND CategoryId = ? AND gradeId = ? AND sizeId = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			PreparedStatement stmt  = null;
			
			try {
				if(isPresentAllElements(AEGPB.get(0))) {
					
					stmt = conn.prepareStatement(updateQuery);
					for(EMSGRNPendingBean EGPB:AEGPB) {
						stmt.setInt(1,EGPB.getQuantity());

						if(EGPB.getOriginalQuantity() == 0) {
							EMSGRNApprovalPendingDao.getInstance().updateRecievedItems(EGPB.getProjectId(),EGPB.getCategoryName().concat(" ").concat(EGPB.getGradeName()),EGPB.getSizeName(),EGPB.getCategory(),EGPB.getGrade(),EGPB.getSize());
						}
						stmt.setInt(2,EGPB.getOriginalQuantity());
						stmt.setString(3,EGPB.getProjectId());
						stmt.setInt(4,EGPB.getCategory());
						stmt.setInt(5,EGPB.getGrade());
						stmt.setInt(6,EGPB.getSize());
						stmt.addBatch();
					}
					stmt.executeBatch();
				}else {
					
					stmt = conn.prepareStatement(insertQuery);
					for(EMSGRNPendingBean EGPB:AEGPB) {
						stmt.setString(1, EGPB.getProjectId());
						stmt.setString(2, EGPB.getMaterialCategory());
						stmt.setInt(3, EGPB.getCategory());
						stmt.setInt(4, EGPB.getGrade());
						stmt.setInt(5, EGPB.getSize());
						stmt.setString(6, EGPB.getUnits());
						stmt.setInt(7, EGPB.getQuantity());
						if(EGPB.getOriginalQuantity()  == 0) {
							EMSGRNApprovalPendingDao.getInstance().updateRecievedItems(EGPB.getProjectId(),EGPB.getCategoryName().concat(" ").concat(EGPB.getGradeName()),EGPB.getSizeName(),EGPB.getCategory(),EGPB.getGrade(),EGPB.getSize());
						}
						stmt.setInt(8, EGPB.getOriginalQuantity());
						stmt.addBatch();
					}
					stmt.executeBatch();
				}
				return true;
			} catch(SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		
		return false;
	}
	
	public boolean isPresentAllElements(EMSGRNPendingBean EGPB) {
		
		String selectQuery = "SELECT count(*) from GRNApprovalPending WHERE CategoryId = ? AND gradeId = ? AND SizeId = ?";
		
		Connection conn  = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setInt(1,EGPB.getCategory());
				stmt.setInt(2,EGPB.getGrade());
				stmt.setInt(3, EGPB.getSize());
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getInt(1) != 0) {
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
	
	
	public void updateRecievedItems(String projectId,String ProductDescription,String Size,int categoryId,int gradeId,int sizeId) {
		
		String updateQuery = "UPDATE POSTPURCHASE JOIN INDENT USING (INDENTID) SET isReceived = 1 WHERE PROJECTID = ? AND ProductDescription = ? AND Size = ?";
		String updateQuery1 = "UPDATE GRNAPPROVALPENDING SET isReceived = 1 WHERE PROJECTID = ? AND categoryId = ? AND gradeId = ? AND sizeId = ?";
		Connection conn  = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				PreparedStatement stmt = conn.prepareStatement(updateQuery);
				PreparedStatement stmt1 = conn.prepareStatement(updateQuery1);
				stmt.setString(1,projectId);
				stmt.setString(2, ProductDescription);
				stmt.setString(3, Size);
				stmt1.setString(1, projectId);
				stmt1.setInt(2, categoryId);
				stmt1.setInt(3, gradeId);
				stmt1.setInt(4, sizeId);
				stmt.executeUpdate();
				stmt1.executeUpdate();
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
