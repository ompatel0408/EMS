package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import com.bean.EMSDispatchBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSDispatchDao {
	private static EMSDispatchDao instance = null;

	public static EMSDispatchDao getInstance() {

		if (instance == null) {
			instance = new EMSDispatchDao();
		}

		return instance;
	}

	public boolean insertDispatchItems(EMSDispatchBean edb,HttpServletRequest request,HttpServletResponse response) {
		String insertQuery = "INSERT INTO Dispatch(CLIENTID,ITEMCODE,DISPATCHDATE,DESTINATIONADDRESS,VEHICLENUMBER,TRAVELCOMPANY,TRAVELCOMPANYOWNER,CHECKEDBY) VALUES(?,?,?,?,?,?,?,?)";
		Connection conn = MySqlConnection.getInstance();
		ClientDao cd = ClientDao.getInstance();
		if (conn != null) 
		{
			try {
				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				stmt.setInt(1, cd.getClientIdFormDatabase(edb.getclientId()));
				stmt.setString(2, edb.getOfferId());
				stmt.setString(3, LocalDate.now().toString());
				stmt.setString(4, edb.getDest());
				stmt.setString(5, edb.getVehicleNo());
				stmt.setString(6, edb.getTravelCom());
				stmt.setString(7, edb.getTravelComOwnr());
				stmt.setString(8, edb.getCheckBy());
				stmt.executeUpdate();
				return true;
			} catch (SQLException E) {
				try {
					ExceptionHandler.handleException(request, response, E);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
		} else {
			System.out.println("Connction is not establised!");
		}
		return false;
	}

	public ArrayList<String> getOfferFromDba(String client) 
	{
		ClientDao cd = ClientDao.getInstance();
		String selectQuery = "select itemcode from items join clients using(clientid) where ClientId = "+cd.getClientIdFormDatabase(client);
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> ar = new ArrayList<String>();
		if (conn != null) 
		{
			try 
			{
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
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

	public ArrayList<EMSDispatchBean> getProjectFromDba() 
	{
		String query = "Select * from dispatch";
		Connection conn = MySqlConnection.getInstance();
		ClientDao cd = ClientDao.getInstance();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			ArrayList<EMSDispatchBean> aldb = new ArrayList<EMSDispatchBean>();
			while(rs.next())
			{
				EMSDispatchBean edb = new EMSDispatchBean();
				edb.setclientId(cd.getClientNameFormDatabase(Integer.parseInt(rs.getString("clientId"))));
				edb.setOfferId(rs.getString("ITEMCODE"));
				edb.setTravelCom(rs.getString("TRAVELCOMPANY"));
				edb.setTravelComOwnr(rs.getString("TRAVELCOMPANYOWNER"));
				edb.setVehicleNo(rs.getString("VEHICLENUMBER"));
				edb.setCheckBy(rs.getString("CHECKEDBY"));
				edb.setDest(rs.getString("DESTINATIONADDRESS"));
				edb.setDate(rs.getString("DISPATCHDATE"));
				aldb.add(edb);
			}
			return aldb;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getCountOfProject() {
		
		String query = "select count(*) from dispatch";
		Connection conn = MySqlConnection.getInstance();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs =  stmt.executeQuery();
			if(rs.next())
			{
				String data = rs.getString(1);				
				return Integer.parseInt(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return 0;
	}
}
