package com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSDrawingBean;
import com.bean.EMSGRNBean;
import com.bean.SubItemBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSDrawingDao {

	private static EMSDrawingDao instance = null;

	public static EMSDrawingDao getInstance() {
		if (instance == null) {
			instance = new EMSDrawingDao();
		}
		return instance;
	}

	public ArrayList<String> getOfferNameFromDatabase(String projectId) {

		String selectQuery = "select ItemCode from items WHERE projectId = ?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> arr = new ArrayList<String>();
		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, projectId);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					arr.add(rs.getString(1));
				}
				return arr;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}

		return null;
	}

	public ArrayList<String> getDrawingIdFromdatabase(String projectId) {

		String selectQuery = "SELECT DrawingId FROM Prepurchase WHERE ProjectId = ?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> a = new ArrayList<String>();
		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, projectId);
				ResultSet rs = stmt.executeQuery();

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

	public boolean isDrawingIdPresent(String drawingId) {

		String selectQuery = "SELECT * FROM DRAWING WHERE DRAWINGID = ?";
		Connection conn = MySqlConnection.getInstance();
		if (conn != null) {
			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, drawingId);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}

		return false;
	}

	public boolean addDrawingDetails(String drawingId, EMSDrawingBean EDB, HttpServletRequest request,
			HttpServletResponse response) {

		String insertQuery = "INSERT INTO Drawing(DrawingId,ClientDrawing,EMSDrawing) Values(?,?,?)";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				stmt.setString(1, drawingId);
				stmt.setString(2, EDB.getClientDrawing());
				stmt.setString(3, EDB.getEMSDrawing());
				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				try {
					if (EMSDrawingDao.getInstance().updateDrawing(drawingId, EDB, request, response)) {
						return true;
					}
					ExceptionHandler.handleException(request, response, e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		} else {
			System.out.println("Connection is not establised!");
		}

		return false;
	}

	public boolean updateDrawing(String drawingId, EMSDrawingBean EDB, HttpServletRequest request,
			HttpServletResponse response) {

		String insertQuery = "UPDATE Drawing SET ClientDrawing = ? , EMSDrawing = ? WHERE DRAWINGID = ?";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				stmt.setString(3, drawingId);
				stmt.setString(1, EDB.getClientDrawing());
				stmt.setString(2, EDB.getEMSDrawing());
				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				try {
					ExceptionHandler.handleException(request, response, e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else {
			System.out.println("Connection is not establised!");
		}

		return false;
	}

	public ArrayList<SubItemBean> getAllData() {

		String selectQuery = "select items.projectid,itemcode,subitemcode from clients join projects using(clientid) join items using(clientid) join subitems using(itemcode)";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<SubItemBean> a = new ArrayList<SubItemBean>();
		SubItemBean EGB = null;
		if (conn != null) {

			try {

				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);

				while (rs.next()) {
					EGB = new SubItemBean(rs.getString(1), rs.getString(2), rs.getString(3));
					a.add(EGB);
				}
				return a;

			} catch (SQLException E) {
				E.printStackTrace();
			}

		} else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
	
	public ArrayList<SubItemBean> getOriginalDrawing() {

		String selectQuery = "select * from originaldrawing order by 1,2,3";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<SubItemBean> a = new ArrayList<SubItemBean>();
		SubItemBean EGB = null;
		if (conn != null) {

			try {

				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);

				while (rs.next()) {
					EGB = new SubItemBean(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5));
					a.add(EGB);
				}
				return a;

			} catch (SQLException E) {
				E.printStackTrace();
			}

		} else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}


	public ArrayList<SubItemBean> getsubItemFromDatabase(String asString) {
		String selectQuery = " select subitemcode from subitems where itemcode=?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<SubItemBean> arr = new ArrayList<SubItemBean>();
		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, asString);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					SubItemBean sib = new SubItemBean();
					sib.setSubitemcode(rs.getString(1));
					arr.add(sib);
				}
				return arr;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}

		return null;
	}

	public void addToDrawingHistory(String project, String offer, String subItem, String clientDrawing,
			String emsDrawing) {
		String insertQuery = "INSERT INTO Drawinghistory(projectid,itemcode,subitemcode,clientdrawing,emsdrawing,milli) Values(?,?,?,?,?,?)";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				stmt.setString(1, project);
				stmt.setString(2, offer);
				stmt.setString(3, subItem);
				stmt.setString(4, clientDrawing);
				stmt.setString(5, emsDrawing);
				stmt.setLong(6, System.currentTimeMillis());
				stmt.executeUpdate();
			} catch (SQLException e) {
//				ErrorHandler.class();
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}

	}

	public ArrayList<EMSDrawingBean> getDrawingHistory(String projectId, String itemCode, String subItemCode) {

		String selectQuery = " select clientDrawing,emsDrawing from drawingHistory where projectid=? and itemcode=? and subitemcode=?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSDrawingBean> arr = new ArrayList<EMSDrawingBean>();
		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, projectId);
				stmt.setString(2, itemCode);
				stmt.setString(3, subItemCode);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					EMSDrawingBean sib = new EMSDrawingBean();
					sib.setClientDrawing(rs.getString(1));
					sib.setEMSDrawing(rs.getString(2));
					arr.add(sib);
				}
				return arr;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}

		return null;
	}

	public int getCount(String project, String offer, String subItem) {
		String selectQuery = " select count(*) from drawingHistory where projectid=? and itemcode=? and subitemcode=?";
		Connection conn = MySqlConnection.getInstance();
		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, project);
				stmt.setString(2, offer);
				stmt.setString(3, subItem);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return 0;
	}

	public void addToDrawing(String project, String offer, String subItem, String client, String ems) {
		Connection conn = MySqlConnection.getInstance();
		String query = "select * from originalDrawing where projectid=? and offer=? and subitemcode=?";
		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, project);
				stmt.setString(2, offer);
				stmt.setString(3, subItem);
				ResultSet rs = stmt.executeQuery();		
				if (rs.next()) {
					String delete = "delete from originaldrawing where 	projectid=? and offer=? and subitemcode=?  " ;
//					String update = "update originalDrawing set clientdrawing='"+client+"' and emsdrawing='"+ems+"' where projectid=? and offer=? and subitemcode=?";
					try {

						PreparedStatement stmt1 = conn.prepareStatement(delete);
						stmt1.setString(1, project);
						stmt1.setString(2, offer);
						stmt1.setString(3, subItem);
//						stmt1.setString(1, client);
						System.out.println(client);
//						stmt1.setString(2, ems);
						System.out.println(ems);
						stmt1.executeUpdate();
						
						
						insertOriginalDrawing(project, offer, subItem, client, ems);
						
					} catch (SQLException e) {
//					ErrorHandler.class();
						e.printStackTrace();
					}
				}
				else {
					insertOriginalDrawing(project, offer, subItem, client, ems);
				}
			} catch (SQLException e) {
//			ErrorHandler.class();
				e.printStackTrace();
			}
		}
	}


public void insertOriginalDrawing (String project, String offer, String subItem, String client, String ems)
{
	Connection conn = MySqlConnection.getInstance();
	
	String insertQuery = "INSERT INTO originalDrawing(projectid,offer,subitemcode,clientdrawing,emsdrawing) Values(?,?,?,?,?)";
	try {

		PreparedStatement stmt1 = conn.prepareStatement(insertQuery);
		stmt1.setString(1, project);
		stmt1.setString(2, offer);
		stmt1.setString(3, subItem);
		stmt1.setString(4, client);
		stmt1.setString(5, ems);
		stmt1.executeUpdate();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
}
}
