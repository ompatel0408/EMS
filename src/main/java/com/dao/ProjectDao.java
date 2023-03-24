package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select projectid,clientpoid,podate,clientid,advancepaypercent,afterpaypercent,clientname from projects join clients using(clientid)");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProjectBean project = new ProjectBean();
				project.setProjectId(rs.getString("PROJECTID"));
				project.setClientPoId(rs.getString("CLIENTPOID"));
				project.setPoDate(rs.getString("PODATE"));
				project.setClientId(rs.getInt("CLIENTID"));
				project.setAdvancePayPercent(rs.getInt("ADVANCEPAYPERCENT"));
				project.setAfterPayPercent(rs.getInt("AFTERPAYPERCENT"));
				project.setClientName(rs.getString("CLIENTNAME"));
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

	
	
	
}