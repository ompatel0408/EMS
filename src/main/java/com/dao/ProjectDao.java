package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.bean.ProjectBean;
import com.dbConnection.MySqlConnection;

public class ProjectDao {
	
public static ProjectDao instance = null;
	
	public static ProjectDao getInstance() {
		
		if(instance == null) {
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
			while(rs.next()) {
				
				projectIds.add(rs.getString("projectid"));
			}
			return projectIds;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addProject(ProjectBean projectBean) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("insert into projects values (?,?,?,?,?,?)");
			pstmt.setString(1,projectBean.getProjectId());
			pstmt.setString(2, projectBean.getClientPoId());
			pstmt.setString(3, projectBean.getPoDate());
			pstmt.setInt(4,projectBean.getAdvancePayPercent());
			pstmt.setInt(5,projectBean.getAfterPayPercent());
			pstmt.setInt(6,projectBean.getClientId());
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ProjectBean> getAllProject() {
		ArrayList<ProjectBean> projects = new ArrayList<ProjectBean>();
		try {
			Connection con =MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select projectid,QUOTATIONid,podate,p.clientid,advancepaypercent,afterpaypercent,clientname from projects p join clients c on p.clientid = c.clientid");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProjectBean project = new ProjectBean();
				project.setProjectId(rs.getString(1));
				project.setClientPoId(rs.getString(2));
				project.setPoDate(rs.getString(3));
				project.setClientId(rs.getInt(4));
				project.setAdvancePayPercent(rs.getInt(5));
				project.setAfterPayPercent(rs.getInt(6));
				project.setClientName(rs.getString(7));
				projects.add(project);
			}
			return projects;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteProject(String projectId) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("delete from projects where projectid = ?");
			pstmt.setString(1, projectId);
			pstmt.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void updateProject(String newData, String changeField, String projectId) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("update projects set " + changeField + "= ? where projectid = ? ");
			pstmt.setString(1, newData);
			pstmt.setString(2, projectId);
			pstmt.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getClientName(int clientId) {
		try {
			Connection con =MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select clientname from clients where clientid= ?");
			System.out.println("client id in dao :"+clientId);
			pstmt.setInt(1, clientId);
			ResultSet rs = pstmt.executeQuery();
			String clientName="";
			while(rs.next()) {
				clientName=rs.getString("clientname");
			}
			return clientName;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int getProjectId() {
		String projectId = null;
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select projectid from projects where projectid = (select max(projectid) from projects)");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				projectId = rs.getString("projectid");
			}
			System.out.println("projectId in dao:"+projectId);
			if(projectId==null){
				System.out.println("In null");
				return 0;
			}
			return Integer.parseInt(String.valueOf(projectId.charAt(projectId.length()-1)));
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}