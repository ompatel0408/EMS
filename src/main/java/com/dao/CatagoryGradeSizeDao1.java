package com.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bean.CatagoryGradeSizeBean;
import com.bean.ClientBean;
import com.bean.ProjectBean;
import com.controller.GetGradeListServlet;
import com.dbConnection.*;


public class CatagoryGradeSizeDao1 {
	private static CatagoryGradeSizeDao1 instance = null;
	
	private CatagoryGradeSizeDao1() {}
	

	public static CatagoryGradeSizeDao1 getInstance() {
		if (instance == null) {
			instance = new CatagoryGradeSizeDao1();
		}
		return instance;
	}
	
	
	
	public ArrayList<CatagoryGradeSizeBean> getCatagoryList(){
		ArrayList<CatagoryGradeSizeBean> catagories = new ArrayList<CatagoryGradeSizeBean>();
		
		try {
			Connection con = MySqlConnection.getInstance();
			
			PreparedStatement pstmt = con.prepareStatement("select * from emscatagory");
			// now pstmt will not return single value , it will return all the records of the relation
			
			ResultSet rs = pstmt.executeQuery(); // easy concept hi hai
			
			while(rs.next()) {
				CatagoryGradeSizeBean catagory = new CatagoryGradeSizeBean();
				catagory.setCatagoryId(rs.getInt("catagoryId"));
				catagory.setCatagoryName(rs.getString("catagory"));
				catagories.add(catagory);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return catagories;
	}
	public void addCatagory(CatagoryGradeSizeBean cBean) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("insert into emscatagory(catagory) values (?)");
			pstmt.setString(1,cBean.getCatagoryName());
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addGrade(String selectedId,String gradeName ) {
		String updateQuery = "insert into catagorygrade(Grade,catagoryid) values (?,?)";
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement(updateQuery);
			pstmt.setString(1,gradeName);
			pstmt.setString(2,selectedId);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addSize(String CatagoryId,String GradeName,String size) {
		int id=0;
		try {
			Connection con = MySqlConnection.getInstance();
			
			PreparedStatement pstmt = con.prepareStatement("select gradeid from catagorygrade where grade=?");
			pstmt.setString(1,GradeName);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				id=rs.getInt(1);
			}
			
			addFinalSize(id,size);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void addFinalSize(int id,String size) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("insert into catagorygradesize(Gradeid,size) values (?,?)");
			pstmt.setInt(1,id);
			pstmt.setString(2, size);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}