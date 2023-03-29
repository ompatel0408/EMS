package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.QuotationPerItemBean;
import com.dbConnection.MySqlConnection;

public class QuotationPerItemDao {
	
	private static QuotationPerItemDao instance = null;
	private static int index = 0;
	
	public static QuotationPerItemDao getInstance() {
		
		if(instance == null) {
			instance = new  QuotationPerItemDao();
		}
		return instance;
	}
	
		public int getSizeCatagory() {
		String selectQuery = "SELECT count(*) FROM ".concat("EMSCatagory");
			
		
		Connection conn = MySqlConnection.getInstance();
		int rowCount = 0;
		if(conn!= null) {
			
			
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs =stmt.executeQuery(selectQuery);
				while(rs.next()) {
					rowCount = rs.getInt(1);
				}
				return rowCount;
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establied!");
		}
		
		return 0;
	}
	
	public String[] getCatagoryFromDataBase() {
		
		String selectQuery = "SELECT Catagory FROM EMSCatagory";
		Connection conn = MySqlConnection.getInstance();
		QuotationPerItemDao Qd = QuotationPerItemDao.getInstance();
		String[] catagory= new String[Qd.getSizeCatagory()];
		if(conn != null) {
			
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs =stmt.executeQuery(selectQuery);

				while(rs.next()) {
					catagory[index++] = rs.getString(1);
				}
				index = 0;
				return catagory;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection Not Esablised!");
		}
		
		return null;
	}
	
	public ArrayList<String> getGradeFromDatabase(String category) {
		
		String selectQuery = "SELECT grade FROM EMSCatagory INNER JOIN catagoryGrade ON EMSCatagory.catagoryId = catagoryGrade.catagoryId WHERE Catagory = ?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> ar  = new ArrayList<String>();
		if(conn != null) {
			
			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1,category);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					ar.add(rs.getString(1));
				}  
				return ar;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not Establised!");
		}
		
		return null;
	}
	
	public ArrayList<String> getSizeFromDatabase(String category,String grade){
		
		String selectQuery = "SELECT SIZE FROM EMSCatagory EC JOIN catagoryGrade CG ON EC.catagoryId = CG.catagoryId JOIN catagoryGradeSize CGS ON CG.GRADEid = CGS.GRADEid where catagory = ? and grade = ?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> ar  = new ArrayList<String>();
		if(conn != null) {
			System.out.println("Connection establised!");
			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, category);
				stmt.setString(2,grade);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					System.out.println(rs.getString(1));
					ar.add(rs.getString(1));
				}  
				return ar;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not Establised!");
		}
		return null;
		
	}
	
	public ArrayList<String> getItemCodeFromDatabase() {
		String selectQuery = "SELECT OfferCode FROM offer";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> a = new  ArrayList<String>();
		
		if(conn != null) {
			
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					a.add(rs.getString(1));
				}
				return a;
			}catch(SQLException E) {
				E.printStackTrace();
			}
			
		}else {
			System.out.println("Connection is not establised!");
		}
		
		return null;
	}
	
	public int getCategoryIdFromDatabase(String categoryName) {
		
		String selectQuery = "SELECT CatagoryId FROM EMSCatagory WHERE Catagory = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn!= null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1,categoryName);
				ResultSet rs=stmt.executeQuery();
				int rows = 0;
				if(rs.next()) {
					rows = rs.getInt(1);
				}
				return  rows;
				
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		return 0;
	}
	
	public boolean addQuotationPerItem(ArrayList<QuotationPerItemBean> AQPIB) {
		
		String insertQuery = "INSERT INTO quotationPerItem VALUES(?,?,?,?,?,?,?,?)";
		
		Connection conn = MySqlConnection.getInstance();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(insertQuery);
			
			for(QuotationPerItemBean qpib:AQPIB) {
				stmt.setString(1, qpib.getItemId());
				stmt.setInt(2, qpib.getCatagoryId());
				stmt.setDouble(3, qpib.getQuantity());
				stmt.setString(4, qpib.getWeight());
				stmt.setString(5, qpib.getUnits());
				stmt.setString(6, qpib.getpricePerItem());
				stmt.setString(7, qpib.getProfitPercentage());
				stmt.setString(8, qpib.getTotalPricePerItem());
				stmt.addBatch();
			}
			int[] result = stmt.executeBatch();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	public ArrayList<String> getAllItemCode(){
		
		String selectQuery = "SELECT offerCode FROM  ProfitInQuotationPerItem";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> ar = new ArrayList<String>();
		if(conn != null) {
			
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(selectQuery);
				
				while(rs.next()) {
					ar.add(rs.getString(1));
				}
				return ar;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
	
	public boolean addProfitForQuotationPerItem(ArrayList<QuotationPerItemBean> ar) {
		Connection conn = MySqlConnection.getInstance();
		if (QuotationPerItemDao.getInstance().getAllItemCode().contains(ar.get(0).getItemId())) {
			String updateQuery = "UPDATE ProfitInQuotationPerItem SET TotalAmountWithoutProfit = ?,TotalAmountWithProfit = ? WHERE offerCode = ?";

			if(conn != null) {
				try {
					PreparedStatement stmt = conn.prepareStatement(updateQuery);
					for(QuotationPerItemBean qpib :ar) {
						stmt.setString(1, qpib.getTotalAmountWithoutProfit());
						stmt.setString(2, qpib.getTotalAmountWithProfit());
						stmt.setString(3, qpib.getItemId());
						stmt.addBatch();
					}
					stmt.executeBatch();
					return true;
				}catch(SQLException E) {
					E.printStackTrace();
				}
			}else {
				System.out.println("Connection is not establised!");
			}
		}else {
			
			String insertQuery = "Insert into ProfitInQuotationPerItem Values(?,?,?)";
			
			if(conn != null) {
				try {
					PreparedStatement stmt = conn.prepareStatement(insertQuery);
					for(QuotationPerItemBean qpib :ar) {
						stmt.setString(1, qpib.getItemId());
						stmt.setString(2, qpib.getTotalAmountWithoutProfit());
						stmt.setString(3, qpib.getTotalAmountWithProfit());
						stmt.addBatch();
					}
					stmt.executeBatch();
					return true;
				}catch(SQLException E) {
					E.printStackTrace();
				}
			}else {
				System.out.println("Connection is not establised!");
			}
		}
		
		
		
		return false;
	}
	
	public boolean updateTotalPrice(ArrayList<QuotationPerItemBean> QAIB) {
		
		String updateQuery = "UPDATE OFFER SET TotalPrice = ? WHERE offerCode =?";
		Connection  conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {

				PreparedStatement stmt = conn.prepareStatement(updateQuery);
				for(QuotationPerItemBean IB :QAIB) {
					stmt.setString(1, IB.getTotalAmountWithProfit());
					stmt.setString(2, IB.getItemId());
					stmt.addBatch();
				}
				int[] updateCounts = stmt.executeBatch();
				// Clear the batch
				stmt.clearBatch();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return false;
	}
	
	public ArrayList<QuotationPerItemBean> performSum() {
		
		String sumQuery = "select offerCode,sum(TotalPricePerItem) from QuotationPerItem group by offerCode";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<QuotationPerItemBean> ar  = new ArrayList<QuotationPerItemBean>();
		if(conn != null) {
			
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sumQuery);
				
				while(rs.next()) {
					ar.add(new QuotationPerItemBean(rs.getString(1),rs.getString(2)));
				}
				return ar;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return null;
	}
	
	
}

