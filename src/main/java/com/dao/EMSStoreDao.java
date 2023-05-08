package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSStoreBean;
import com.dbConnection.MySqlConnection;
import com.google.gson.JsonElement;

public class EMSStoreDao {

	private static EMSStoreDao instance = null;

	public static EMSStoreDao getInstance() {

		if (instance == null) {
			instance = new EMSStoreDao();
		}
		return instance;
	}

	
	public void updateQ(int catid,int gig,int sig,int q)
	{
		System.out.println("qu"+q);
		System.out.println("qu"+catid);
		System.out.println("qu"+gig);
		System.out.println("qu"+sig);
		String uq="update store set quantity=quantity+? where categoryId=? and gradeId=? and sizeId=?";
Connection conn = MySqlConnection.getInstance();
		
		if (conn == null) {
			System.out.println("connection is not connected..");
		} else {
			try {
				PreparedStatement stmt1 = conn.prepareStatement(uq);
				stmt1.setInt(1, q);
				stmt1.setInt(2, catid);
				stmt1.setInt(3, gig);
				stmt1.setInt(4, sig);
				stmt1.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean addItems(ArrayList<EMSStoreBean> sb) {
		String insertQuery = "INSERT INTO STORE (categoryId, gradeId, sizeId, quantity) VALUES(?,?,?,?)";
		String checkQuery="select storeid from store where categoryId=? and gradeId=? and sizeId=?";
		Connection conn = MySqlConnection.getInstance();
		
		if (conn == null) {
			System.out.println("connection is not connected..");
		} else {
			try {
				for (EMSStoreBean s : sb) {
					PreparedStatement stmt1 = conn.prepareStatement(checkQuery);
					stmt1.setInt(1, s.getCategoryId());
					stmt1.setInt(2, s.getGradeId());
					stmt1.setInt(3, s.getSizeId());
					ResultSet rs = stmt1.executeQuery();
					if(rs.next())
					{
						System.out.println("update");
						updateQ(s.getCategoryId(),s.getGradeId(),s.getSizeId(),s.getQuantity());
					}
					else {
						System.out.println("insert");
						PreparedStatement stmt = conn.prepareStatement(insertQuery);
						stmt.setInt(1, s.getCategoryId());
						stmt.setInt(2, s.getGradeId());
						stmt.setInt(3, s.getSizeId());
						stmt.setInt(4, s.getQuantity());
						stmt.executeUpdate();
					}
				}
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

		String selectQuery = "select catagory from grnapprovalpending grn join emscatagory es on es.catagoryid=grn.categoryid";
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
		String selectQuery = "select grade from grnapprovalpending grn join emscatagory es on es.catagoryid=grn.categoryid join catagorygrade using(gradeid) WHERE Catagory = ?";
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

		String selectQuery = " select size from grnapprovalpending grn join emscatagory es on es.catagoryid=grn.categoryid join catagorygrade using(gradeid) join catagorygradesize using(sizeid) where catagory = ? and grade = ?";
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
	
	
	public ArrayList<EMSStoreBean> getAllData() {

		ArrayList<EMSStoreBean> store =  new ArrayList<EMSStoreBean>();
		String selectQuery = "select catagory,grade,size,quantity from store s join emscatagory ec on s.categoryid=ec.catagoryid  join catagorygrade cg on s.gradeid=cg.gradeid join catagorygradesize using (sizeid) order by StoreId DESC";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					EMSStoreBean esb = new EMSStoreBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
					store.add(esb);
				}
				return store;

			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return null;
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
	
	public ArrayList<EMSStoreBean> getAllStoreList() {

		ArrayList<EMSStoreBean> store =  new ArrayList<EMSStoreBean>();
		String selectQuery = "select catagory,grade,size,quantity from store s join emscatagory ec on s.categoryid = ec.catagoryid join catagorygrade using (gradeid) join catagorygradesize using (sizeid) order by storeId DESC";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					EMSStoreBean esb = new EMSStoreBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
					store.add(esb);
				}
				return store;

			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}


	public int getQuantFromDatabase(String ctgry, String grd, String size) {
		
		String selectQuery = "select quantity from grnapprovalpending grn join emscatagory es on es.catagoryid=grn.categoryid join catagorygrade using(gradeid) join catagorygradesize using(sizeid) WHERE Catagory = ? and grade=? and size=? ";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, ctgry);
				stmt.setString(2, grd);
				stmt.setString(3, size);
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

}