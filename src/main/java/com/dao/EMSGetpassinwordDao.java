package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import com.bean.EMSGetpassinwordBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
		HashSet<String> vendor = new HashSet<String>();
		try {
			PreparedStatement stmt = conn.prepareStatement("Select ISSUEPERSON from gatepassoutword");
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				vendor.add(PersonsDao.getInstance().getPersonsFromDatabase(rs.getInt(1)));
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

		try {
			
			PreparedStatement stmt = conn.prepareStatement("Select MATERIALMACHINENAME from gatepassoutword where ISSUEPERSON = ? and RECIEVED = '0'");
			stmt.setInt(1, PersonsDao.getInstance().getPersonIsFromDatabase(vendor));
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
	
	public boolean isnertItems(EMSGetpassinwordBean item,HttpServletRequest request,HttpServletResponse response)
	{
		Connection conn = MySqlConnection.getInstance();
		EMSGetpassOutwordDao egod = EMSGetpassOutwordDao.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement("Insert into gatepassinword(ISSUEPERSONID,ITEMRECIEVEDATE,VEHICLENUMBER,REMARK,MATERIALMACHINENAME,receivedQty) values (?,?,?,?,?,?)");
			stmt.setInt(1,PersonsDao.getInstance().getPersonIsFromDatabase(item.getVendor()));
			stmt.setString(2, item.getReceiveDate());
			stmt.setString(3, item.getVehicleNo());
			stmt.setString(4, item.getRemark());
			stmt.setString(5, item.getItemName());
			stmt.setInt(6, item.getQty());
			stmt.executeUpdate();
			egod.updateInValues(PersonsDao.getInstance().getPersonIsFromDatabase(item.getVendor()), item.getItemName());
			return true;
		} catch (SQLException e) {
			try {
				ExceptionHandler.handleException(request, response, e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

		}
		return false;
	}
	
	public ArrayList<EMSGetpassinwordBean> getItems()
	{
		Connection conn = MySqlConnection.getInstance();

		ArrayList<EMSGetpassinwordBean> al = new ArrayList<EMSGetpassinwordBean>();
		try {
			PreparedStatement stmt = conn.prepareStatement("Select * from gatepassinword");
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				EMSGetpassinwordBean eb = new EMSGetpassinwordBean(PersonsDao.getInstance().getPersonsFromDatabase(rs.getInt(2)), rs.getString("MATERIALMACHINENAME"), rs.getInt("receivedQty"), rs.getString("VEHICLENUMBER"), rs.getString("ITEMRECIEVEDATE"), rs.getString("REMARK"));
				al.add(eb);
			}
			return al;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
