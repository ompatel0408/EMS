package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSGetpassOutwordBean;
import com.dbConnection.MySqlConnection;
import com.mysql.cj.protocol.Resultset;

public class EMSGetpassOutwordDao {
	
	private static EMSGetpassOutwordDao instance;
	
	public static EMSGetpassOutwordDao getInstance()
	{
		if(instance == null)
		{
			instance = new EMSGetpassOutwordDao();
		}
		return instance;
	}
	
	public int insertGetpassOut(EMSGetpassOutwordBean egob) {
		Connection conn = MySqlConnection.getInstance();
		String insertQuery = "INSERT INTO GATEPASSOUTWORD (MATERIALMACHINENAME,SIZE,QUNTITY,ISSUEDATE,ISSUEVENDORID,VENDORADDRESS,REMARK,WILLRECIEVE,VEHICLENUMBER) VALUES (?,?,?,?,?,?,?,?,?)";
		EMSVendorsDao vd = EMSVendorsDao.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement(insertQuery);
			stmt.setString(1, egob.getMatName());
			stmt.setString(2, egob.getSize());
			stmt.setInt(3, egob.getQty());
			stmt.setString(4, egob.getIssueDate());
			stmt.setInt(5, vd.getVendorIdfromDba(egob.getVendorName()));
			stmt.setString(6, egob.getAddress());
			stmt.setString(7, egob.getRemark());
			stmt.setBoolean(8, egob.getReceive() == 0 ? false : true);
			stmt.setString(9, egob.getVehicleNo());
			stmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			if(e.getMessage().substring(0,15).equals("Duplicate entry"))
			{
				return -101;
			}
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<EMSGetpassOutwordBean> getItems()
	{
		Connection conn = MySqlConnection.getInstance();
		EMSVendorsDao vd = EMSVendorsDao.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("Select * from GATEPASSOUTWORD where RECIEVED = '0'");
			ResultSet rs = stmt.executeQuery();
			ArrayList<EMSGetpassOutwordBean> aleg = new ArrayList<EMSGetpassOutwordBean>();
			
			while(rs.next())
			{
				EMSGetpassOutwordBean db = new EMSGetpassOutwordBean(vd.getVendorNamefromDba(rs.getInt("ISSUEVENDORID")), rs.getString("MATERIALMACHINENAME"), rs.getInt("QUNTITY"), rs.getString("ISSUEDATE"), rs.getString("SIZE"), rs.getString("VEHICLENUMBER"), rs.getString("VENDORADDRESS"), rs.getString("REMARK"), rs.getBoolean("WILLRECIEVE") == true ? 1 : 0 , rs.getBoolean("RECIEVED") == true ? 1 : 0);
				aleg.add(db);
			}
			return aleg;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean updateInValues(int client, String itemName)
	{
		Connection conn = MySqlConnection.getInstance();
		try {
			PreparedStatement stmt  = conn.prepareStatement("update gatepassoutword set RECIEVED = '1' where ISSUEVENDORID = ? and MATERIALMACHINENAME = ?");
			stmt.setInt(1, client);
			stmt.setString(2, itemName);
			System.out.println("Update item : " + stmt.executeUpdate());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
}
