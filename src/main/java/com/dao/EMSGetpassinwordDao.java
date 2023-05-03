package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import com.bean.EMSGetpassinwordBean;
import com.dbConnection.MySqlConnection;

public class EMSGetpassinwordDao 
{
	private static EMSGetpassinwordDao instance = null;
	
	public static EMSGetpassinwordDao getInstace()
	{
		if(instance == null)
		{
			instance = new EMSGetpassinwordDao();
		}
		return instance;
	}
	
	public HashSet<String> getVendorsFromDba()
	{
		Connection conn = MySqlConnection.getInstance();
		EMSVendorsDao evd = EMSVendorsDao.getInstance();
		HashSet<String> vendor = new HashSet<String>();
		try {
			PreparedStatement stmt = conn.prepareStatement("Select ISSUEVENDORID from gatepassoutword");
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				vendor.add(evd.getVendorNamefromDba(rs.getInt(1)));
			}
			return vendor;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> getItemsVendorwiseFromDba(String vendor)
	{
		Connection conn = MySqlConnection.getInstance();
		EMSVendorsDao evd = EMSVendorsDao.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("Select MATERIALMACHINENAME from gatepassoutword where ISSUEVENDORID = ? and RECIEVED = '0'");
			stmt.setInt(1, evd.getVendorIdfromDba(vendor));
			ArrayList<String> str = new ArrayList<String>();
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				str.add(rs.getString(1));
			}
			return str;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isnertItems(EMSGetpassinwordBean item)
	{
		Connection conn = MySqlConnection.getInstance();
		EMSVendorsDao evd = EMSVendorsDao.getInstance();
		EMSGetpassOutwordDao egod = EMSGetpassOutwordDao.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("Insert into gatepassinword(ISSUEVENDORID,ITEMRECIEVEDATE,VEHICLENUMBER,REMARK,MATERIALMACHINENAME,receivedQty) values (?,?,?,?,?,?)");
			stmt.setInt(1, evd.getVendorIdfromDba(item.getVendor()));
			stmt.setString(2, item.getReceiveDate());
			stmt.setString(3, item.getVehicleNo());
			stmt.setString(4, item.getRemark());
			stmt.setString(5, item.getItemName());
			stmt.setInt(6, item.getQty());
			stmt.executeUpdate();
			egod.updateInValues(evd.getVendorIdfromDba(item.getVendor()), item.getItemName());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<EMSGetpassinwordBean> getItems()
	{
		Connection conn = MySqlConnection.getInstance();
		EMSVendorsDao evd = EMSVendorsDao.getInstance();

		ArrayList<EMSGetpassinwordBean> al = new ArrayList<EMSGetpassinwordBean>();
		try {
			PreparedStatement stmt = conn.prepareStatement("Select * from gatepassinword");
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				EMSGetpassinwordBean eb = new EMSGetpassinwordBean(evd.getVendorNamefromDba(rs.getInt("ISSUEVENDORID")), rs.getString("MATERIALMACHINENAME"), rs.getInt("receivedQty"), rs.getString("VEHICLENUMBER"), rs.getString("ITEMRECIEVEDATE"), rs.getString("REMARK"));
				al.add(eb);
			}
			return al;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
