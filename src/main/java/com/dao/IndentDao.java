package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.bean.CatagoryGradeSizeBean;
import com.bean.ClientBean;
import com.bean.IndentBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndentDao {

	public static IndentDao instance = null;

	public static IndentDao getInstance() {

		if (instance == null) {
			instance = new IndentDao();
		}
		return instance;
	}

	public int getGradeId(String grade) {
		// ArrayList<CatagoryGradeSizeBean> grades = new
		// ArrayList<CatagoryGradeSizeBean>();
		String query1 = "select gradeid from catagorygrade where grade=?";
		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement(query1);
			pstmt.setString(1, grade);
			// now pstmt will not return single value , it will return all the records of
			// the relation

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// CatagoryGradeSizeBean grade = new CatagoryGradeSizeBean();
				return rs.getInt("gradeid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	

	public int getSizeId(String size) {
		// ArrayList<CatagoryGradeSizeBean> grades = new
		// ArrayList<CatagoryGradeSizeBean>();
		String query1 = "select sizeid from catagorygradesize where size=?";
		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement(query1);
			pstmt.setString(1, size);
			// now pstmt will not return single value , it will return all the records of
			// the relation

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// CatagoryGradeSizeBean grade = new CatagoryGradeSizeBean();
				return rs.getInt("sizeid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean addIndent(ArrayList<IndentBean> indent,HttpServletRequest request,HttpServletResponse response) {

		String insertQuery = "INSERT INTO indent(PROJECTID,ITEMCATAGORY,QUANTITY,UOM,REMARKS,ITEMNAME,gradeId,sizeId) VALUES(?,?,?,?,?,?,?,?)";

		Connection con = MySqlConnection.getInstance();

		try {
			PreparedStatement pstmt = con.prepareStatement(insertQuery);

			for (IndentBean qb : indent) {
				pstmt.setString(1, qb.getProjectId());
				pstmt.setInt(2, qb.getCategoryId());
				pstmt.setString(6, qb.getItemName());
				pstmt.setInt(3, qb.getQuantity());
				pstmt.setString(4, qb.getUOM());
				pstmt.setString(5, qb.getRemark());
				pstmt.setInt(7, qb.getGradeId());
				pstmt.setInt(8, qb.getSizeId());
				pstmt.addBatch();
			}
			int[] result = pstmt.executeBatch();
			System.out.println("Result -------->" + result);
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

	public ArrayList<IndentBean> getIndentList() {
		ArrayList<IndentBean> indents = new ArrayList<IndentBean>();

		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement("select * from indent");
			// now pstmt will not return single value , it will return all the records of
			// the relation

			ResultSet rs = pstmt.executeQuery(); // easy concept hi hai

			while (rs.next()) {
				IndentBean indent = new IndentBean();
				indent.setCategoryId(rs.getInt("itemcatagory"));
				indent.setGradeId(rs.getInt("gradeid"));
				indent.setSizeId(rs.getInt("sizeid"));
				indent.setItemName(rs.getString("itemname"));
				indent.setProjectId(rs.getString("projectId"));
				indent.setUOM(rs.getString("uom"));
				indent.setRemark(rs.getString("remarks"));

				indents.add(indent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return indents;
	}

	public ArrayList<IndentBean> getParticularIndent(String projectId, String itemname) {
		ArrayList<IndentBean> indents = new ArrayList<IndentBean>();

		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement(
					"select catagory,grade,size,uom,itemname,projectid,QUANTITY from indent i join EMScatagory ec on i.itemcatagory = ec.catagoryid join catagorygrade ecg on i.gradeid=ecg.gradeid join catagorygradesize ecgs on i.sizeId = ecgs.sizeId where projectId = ? and itemname = ?");
//			PreparedStatement pstmt=con.prepareStatement("select ")
			pstmt.setString(1, projectId);
			pstmt.setString(2, itemname);
			// now pstmt will not return single value , it will return all the records of
			// the relation

			ResultSet rs = pstmt.executeQuery(); // easy concept hi hai

			while (rs.next()) {
				IndentBean indent = new IndentBean();
				// IndentBean indent = new IndentBean();
				indent.setCatagory(rs.getString("catagory"));
				indent.setGrade(rs.getString("grade"));
				indent.setSize(rs.getString("size"));
				indent.setItemName(rs.getString("itemname"));
				indent.setProjectId(rs.getString("projectId"));
				indent.setUOM(rs.getString("uom"));
				indent.setQuantity(rs.getInt("quantity"));
				indents.add(indent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return indents;
	}
	
	public boolean setIsPurchased(String projectId,HttpServletRequest request,HttpServletResponse response) {
		System.out.println("Project Id :"+projectId);
		System.out.println("<---------Hiiiiiiii-------->");
		String updateQuery = "UPDATE Indent SET isPurchased = 1 WHERE projectId = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				PreparedStatement stmt = conn.prepareStatement(updateQuery);
				stmt.setString(1, projectId);
				stmt.executeUpdate();
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
			System.out.println("Connection is not establised!");
		}
		return false;
	}
	
}
