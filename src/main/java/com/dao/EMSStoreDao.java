package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSStoreBean;
import com.dbConnection.MySqlConnection;

public class EMSStoreDao {

	private static EMSStoreDao instance = null;

	public static EMSStoreDao getInstance() {

		if (instance == null) {
			instance = new EMSStoreDao();
		}
		return instance;
	}

	public boolean addItems(ArrayList<EMSStoreBean> sb) {
		String insertQuery = "INSERT INTO STORE (projectId, categoryId, gradeId, sizeId, quantity) VALUES(?,?,?,?,?)";

		Connection conn = MySqlConnection.getInstance();
		if (conn == null) {
			System.out.println("connection is not connected..");
		} else {
			try {
				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				for (EMSStoreBean s : sb) {
					stmt.setString(1, s.getProjectId());
					stmt.setInt(2, s.getCategory());
					stmt.setInt(3, s.getGrade());
					stmt.setInt(4, s.getSize());
					stmt.setInt(5, s.getQuantity());
					stmt.addBatch();
				}
				int[] result = stmt.executeBatch();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<String> getProjects() {

		String selectQuery = "select projectId from projects";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> a = new ArrayList<String>();

		if (conn != null) {

			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);

				while (rs.next()) {
					a.add(rs.getString(1));
				}

				return a;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}

	public ArrayList<String> getCategory() {

		String selectQuery = "select catagory from emscatagory";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> a = new ArrayList<String>();

		if (conn != null) {

			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);

				while (rs.next()) {
					a.add(rs.getString(1));
				}
				return a;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}

	public ArrayList<String> getGradeFromDatabase(String category) {
		String selectQuery = "SELECT grade FROM EMSCatagory INNER JOIN catagoryGrade ON EMSCatagory.catagoryId = catagoryGrade.catagoryId WHERE Catagory = ?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> ar = new ArrayList<String>();
		if (conn != null) {

			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, category);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					ar.add(rs.getString(1));
				}
				return ar;
			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not Establised!");
		}

		return null;
	}

	public ArrayList<String> getSizeFromDatabase(String category, String grade) {

		String selectQuery = "SELECT SIZE FROM EMSCatagory EC JOIN catagoryGrade CG ON EC.catagoryId = CG.catagoryId JOIN catagoryGradeSize CGS ON CG.GRADEid = CGS.GRADEid where catagory = ? and grade = ?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> ar = new ArrayList<String>();
		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, category);
				stmt.setString(2, grade);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					System.out.println(rs.getString(1));
					ar.add(rs.getString(1));
				}
				return ar;
			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not Establised!");
		}
		return null;
	}

	public int getGradeIdFromDatabase(int category, String grade) {

		String selectQuery = "SELECT gradeId FROM catagorygrade WHERE CatagoryId = ? && grade = ?";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setInt(1, category);
				stmt.setString(2, grade);
				ResultSet rs = stmt.executeQuery();
				int rows = 0;
				if (rs.next()) {
					rows = rs.getInt(1);
				}
				return rows;

			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return 0;
	}
	
	public int getSizeIdFromDatabase(int category,int grade, String size) {

		System.out.println("in getSizeId = category : " + category + ", grade : " + grade + ", size : " + size);
		String selectQuery = "select sizeId from EMScatagory EC join catagoryGrade cg ON EC.catagoryId = cg.catagoryId join catagorygradesize cs on cg.gradeId = cs.gradeId where EC.catagoryid = ? and cg.gradeId = ? and cs.size = ?";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setInt(1, category);
				stmt.setInt(2, grade);
				stmt.setString(3, size);
				ResultSet rs = stmt.executeQuery();
				int rows = 0;
				if (rs.next()) {
					rows = rs.getInt(1);
				}
				System.out.println("Row : " + rows);
				return rows;

			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return 0;
	}

}