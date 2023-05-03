package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.bean.DailyProgressReportBean;
import com.dbConnection.MySqlConnection;

public class DailyProgressReportDao {
public static DailyProgressReportDao instance = null;
	
	public static DailyProgressReportDao getInstance() {
		
		if(instance == null) {
			instance = new DailyProgressReportDao();
		}
		return instance;
	}
	
	
	public boolean addDPR(ArrayList<DailyProgressReportBean> indent) {

		String insertQuery = "INSERT INTO dpr(PROJECTID,ITEMname,subitemname,category,grade,size,quantity,uom,date) VALUES(?,?,?,?,?,?,?,?,?)";

		Connection con = MySqlConnection.getInstance();

		try {
			PreparedStatement pstmt = con.prepareStatement(insertQuery);

			for (DailyProgressReportBean qb : indent) {
				pstmt.setString(1, qb.getProjectId());
				pstmt.setString(4, qb.getCategoryId());
				pstmt.setString(2, qb.getItemCode());
				pstmt.setInt(7, qb.getQuantity());
				pstmt.setString(8, qb.getUnitId());
				pstmt.setString(9, qb.getDateId());
				pstmt.setString(5, qb.getGradeId());
				pstmt.setString(6, qb.getSizeId());
				pstmt.setString(3, qb.getSubItemId());
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
	
	public ArrayList<DailyProgressReportBean> getAllDpr() {
		ArrayList<DailyProgressReportBean> dprs = new ArrayList<DailyProgressReportBean>();
		try {
			Connection con =MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select * from dpr order by date");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DailyProgressReportBean dpr = new DailyProgressReportBean();
				dpr.setProjectId(rs.getString(1));
				dpr.setItemCode(rs.getString(2));
				dpr.setSubItemId(rs.getString(3));
				dpr.setCategoryId(rs.getString(4));
				dpr.setGradeId(rs.getString(5));
				dpr.setSizeId(rs.getString(6));
				dpr.setQuantity(rs.getInt(7));
				dpr.setUnitId(rs.getString(8));
				dpr.setDateId(rs.getString(9));
				dprs.add(dpr);
			}
			return dprs;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public ArrayList<String> getPhase(String projectId, String itemCode) {
		String selectQuery = " select phasename from phase where projectid=? and itemcode=?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> arr = new ArrayList<String>();
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1,projectId);
				stmt.setString(2, itemCode);
				ResultSet rs=stmt.executeQuery();
				
				while(rs.next()) {
					arr.add(rs.getString(1));
				}
				return arr;
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		return null;
	}


	public void updatePhase(String projectId, String subItemId,String editedColumn,String itemCode) {
		// TODO Auto-generated method stub
		String updateQuery = "UPDATE subItems SET phase = ? WHERE itemCode = ? and subItemCode = ?";
		
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement(updateQuery);
			pstmt.setString(1, editedColumn);
			pstmt.setString(2, itemCode);
			pstmt.setString(3, subItemId);
			pstmt.executeUpdate();
			updateProjectStatus(projectId,itemCode);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	private void updateProjectStatus(String projectId,String itemCode) {
		// TODO Auto-generated method stub
		String getTotal= "select count(subitemcode)*(select count(phasename) from phase where projectid=?) from subitems where itemcode = ?";
		String updateQuery = "UPDATE projects SET progress = progress + ? WHERE projectId = ?";
		
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement(getTotal);
			pstmt.setString(1, projectId);
			pstmt.setString(2, itemCode);
			ResultSet rs = pstmt.executeQuery();
			float total=0;
			while(rs.next()) {
				total=rs.getFloat(1);
			}
			PreparedStatement pstmt1 = con.prepareStatement(updateQuery);
			System.out.println(100.00/total);
			pstmt1.setDouble(1,100/total);
			pstmt1.setString(2, projectId);
			pstmt1.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
