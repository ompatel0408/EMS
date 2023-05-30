package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.bean.ProjectBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProjectDao {

	public static ProjectDao instance = null;

	public static ProjectDao getInstance() {

		if (instance == null) {
			instance = new ProjectDao();
		}
		return instance;
	}

	public ArrayList<String> getOnlyProjectId() {
		try {
			ArrayList<String> projectIds = new ArrayList<String>();
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select projectid from projects");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				projectIds.add(rs.getString("projectid"));
			}
			return projectIds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addProject(ProjectBean projectBean,HttpServletRequest request,HttpServletResponse response) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement(
					"insert into projects(ProjectId,QuotationId,PODATE,AdvancePayPercent,AfterPayPercent,ClientId) values (?,?,?,?,?,?)");
			pstmt.setString(1, projectBean.getProjectId());
			pstmt.setString(2, projectBean.getClientPoId());
			pstmt.setString(3, projectBean.getPoDate());
			pstmt.setInt(4, projectBean.getAdvancePayPercent());
			pstmt.setInt(5, projectBean.getAfterPayPercent());
			pstmt.setInt(6, projectBean.getClientId());
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			try {
				ExceptionHandler.handleException(request, response, e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

		}
		return false;
	}

	public ArrayList<ProjectBean> getAllProject() {
		ArrayList<ProjectBean> projects = new ArrayList<ProjectBean>();
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement(
					"select projectid,QUOTATIONid,podate,p.clientid,advancepaypercent,afterpaypercent,clientname,progress from projects p join clients c on p.clientid = c.clientid");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ProjectBean project = new ProjectBean();
				project.setProjectId(rs.getString(1));
				project.setClientPoId(rs.getString(2));
				project.setPoDate(rs.getString(3));
				project.setClientId(rs.getInt(4));
				project.setAdvancePayPercent(rs.getInt(5));
				project.setAfterPayPercent(rs.getInt(6));
				project.setClientName(rs.getString(7));
				project.setProgress(rs.getFloat(8));
				projects.add(project);
			}
			return projects;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteProject(String projectId,HttpServletRequest request,HttpServletResponse response) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("delete from projects where projectid = ?");
			pstmt.setString(1, projectId);
			pstmt.executeUpdate();

		} catch (Exception e) {
			try {
				ExceptionHandler.handleException(request, response, e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

		}
	}

	public void updateProject(String newData, String changeField, String projectId,HttpServletRequest request,HttpServletResponse response) {
		try {
			Connection con = MySqlConnection.getInstance();
			if(changeField.equals("advancepaypercent")) {
				PreparedStatement pstmt = con
						.prepareStatement("update projects set " + changeField + "= ? , AfterPayPercent = 100 - ? where projectid = ? ");

				pstmt.setString(1, newData);
				pstmt.setString(2, newData);
				pstmt.setString(3, projectId);

				pstmt.executeUpdate();
			}else {
				PreparedStatement pstmt = con
						.prepareStatement("update projects set " + changeField + "= ? where projectid = ? ");
				pstmt.setString(1, newData);
				pstmt.setString(2, projectId);

				pstmt.executeUpdate();
			}
			

		} catch (Exception e) {
			try {
				ExceptionHandler.handleException(request, response, e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

		}
	}

	public String getClientName(int clientId) {
		
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select clientname from clients where clientid= ?");
			System.out.println("client id in dao :" + clientId);
			pstmt.setInt(1, clientId);
			ResultSet rs = pstmt.executeQuery();
			String clientName = "";
			while (rs.next()) {
				clientName = rs.getString("clientname");
			}
			return clientName;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getProjectId() {
		String projectId = null;
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement(
					"select projectid from projects where projectid = (select max(projectid) from projects)");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				projectId = rs.getString("projectid");
			}
			System.out.println("projectId in dao:" + projectId);
			if (projectId == null) {
				System.out.println("In null");
				return 0;
			}
			return Integer.parseInt(String.valueOf(projectId.charAt(projectId.length() - 1)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean updatePrPurchase(int clientId, String projectId,HttpServletRequest request,HttpServletResponse response) {

		String updateQuery = "UPDATE PrePurchase SET PROJECTID = ? WHERE ClientId = ?";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(updateQuery);
				stmt.setString(1, projectId);
				stmt.setInt(2, clientId);
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

	public ArrayList<ProjectBean> getAllProjectUsingClientId(String clientId) {
		ArrayList<ProjectBean> projects = new ArrayList<ProjectBean>();
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement(
					"select projectid,advancepaypercent,afterpaypercent,clientName from projects join clients using (clientId) where clientId = ?");
			pstmt.setString(1, clientId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ProjectBean project = new ProjectBean();
				project.setProjectId(rs.getString(1));
				project.setAdvancePayPercent(rs.getInt(2));
				project.setAfterPayPercent(rs.getInt(3));
				project.setClientName(rs.getString(4));
				projects.add(project);
			}
			return projects;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getClientNameFormDatabase(String clientid) {

		String selectQuery = "SELECT ClientName From Clients WHERE Clientid = ?";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, clientid);
				ResultSet rs = stmt.executeQuery();

				String clientName = "";
				while (rs.next()) {
					clientName = rs.getString(1);
				}
				return clientName;
			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}

		return null;
	}

	public boolean setLoss(String projectId) {

		String updateQuery = "UPDATE PROJECTS SET profitLoss = 0 WHERE projectId = ?";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(updateQuery);
				stmt.setString(1, projectId);
				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return false;
	}
	
	public ArrayList<ProjectBean> getProjectInLoss() {
		ArrayList<ProjectBean> projects = new ArrayList<ProjectBean>();
		try {
			Connection con =MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select projectid,advancepaypercent,afterpaypercent,clientName from projects join clients using (clientId) where profitLoss = 1");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProjectBean project = new ProjectBean();
				project.setProjectId(rs.getString(1));
				project.setAdvancePayPercent(rs.getInt(2));
				project.setAfterPayPercent(rs.getInt(3));
				project.setClientName(rs.getString(4));
				projects.add(project);
			}
			return projects;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateItemsWithProjectId(String projectId,HttpServletRequest request,HttpServletResponse response) {
		String updateQuery = "UPDATE items SET projectId = ? WHERE projectId = 'notassigned'";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(updateQuery);
				stmt.setString(1, projectId);
				stmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
	}
	
	

}