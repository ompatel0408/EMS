package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSDirectorsDashboardBean;
import com.dbConnection.MySqlConnection;


public class EMSDirectorsDashboardDao {
	
	private static EMSDirectorsDashboardDao instance = null;
	
	public static EMSDirectorsDashboardDao getInstacne() {
		
		if(instance == null) {
			instance = new EMSDirectorsDashboardDao();
		}
		return instance;
	}
	
	public ArrayList<EMSDirectorsDashboardBean> getDataOfLiveProjects(){
		
		String selectQuery = "select ClientName,projectName from clients c join projects p ON  c.ClientId = p.ClientId";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<EMSDirectorsDashboardBean> ar = new  ArrayList<EMSDirectorsDashboardBean>();
		EMSDirectorsDashboardBean EDDB = null;
		
		if(conn != null) {
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					EDDB = new EMSDirectorsDashboardBean(rs.getString(1), rs.getString(2));
					ar.add(EDDB);
				}
				return ar;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection not establised!");
		}
		return null;
	}
	
}
