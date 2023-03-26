package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.bean.ProjectBean;
import com.dbConnection.MySqlConnection;

public class ProjectDao {
	public void addProject(ProjectBean projectBean) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("insert into project values (?,?,?,?,?,?,?)");
			pstmt.setString(1,projectBean.getProjectId());
			pstmt.setString(2, projectBean.getClientPoId());
			pstmt.setString(3, projectBean.getPoDate());
			pstmt.setInt(4,projectBean.getAdvancePayPercent());
			pstmt.setInt(5,projectBean.getAfterPayPercent());
			pstmt.setString(6,projectBean.getFinalDeliveryDate());
			pstmt.setInt(7,projectBean.getClientId());
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
			PreparedStatement pstmt = con.prepareStatement("select projectid,clientpoid,podate,finaldeliverydate,clientid,advancepaypercent,afterpaypercent,clientname from project p join clients c on p.clientid = c.client_id");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProjectBean project = new ProjectBean();
				project.setProjectId(rs.getString("PROJECTID"));
				project.setClientPoId(rs.getString("CLIENTPOID"));
				project.setPoDate(rs.getString("PODATE"));
				project.setFinalDeliveryDate("FINALDELIVERYDATE");
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
			PreparedStatement pstmt = con.prepareStatement("delete from project where projectid = ?");
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
			PreparedStatement pstmt = con.prepareStatement("update project set " + changeField + "= ? where projectid = ? ");
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
			PreparedStatement pstmt = con.prepareStatement("select projectid from project where projectid = (select max(projectid) from project)");
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