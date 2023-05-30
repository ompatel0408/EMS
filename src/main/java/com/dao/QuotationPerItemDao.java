package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.EMSOffersBean;
import com.bean.QuotationPerItemBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
		String selectQuery = "SELECT OfferCode FROM offer WHERE STATUS = 'FALSE' order by 1 DESC";
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
	
	public int getgradeIdFromDatabase(String gardeName) {
		
		String selectQuery = "SELECT gradeId FROM CatagoryGrade WHERE grade = ?";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn!= null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1,gardeName);
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
	public int getsizeIdFromDatabase(String sizeName) {
	
	String selectQuery = "SELECT sizeId FROM CatagoryGradeSize WHERE size = ?";
	Connection conn = MySqlConnection.getInstance();
	
	if(conn!= null) {
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(selectQuery);
			stmt.setString(1,sizeName);
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
	
	
	public boolean addQuotationPerItem(ArrayList<QuotationPerItemBean> AQPIB,HttpServletRequest request,HttpServletResponse response) {
		
		String insertQuery = "INSERT INTO quotationPerItem VALUES(?,?,?,?,?,?,?,?,?,?)";
		
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
				stmt.setInt(9, qpib.getGradeId());
				stmt.setInt(10, qpib.getSize());
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
	
	public boolean addProfitForQuotationPerItem(ArrayList<QuotationPerItemBean> ar,HttpServletRequest request,HttpServletResponse response) {
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
	
	public boolean updateTotalPrice(ArrayList<QuotationPerItemBean> QAIB,HttpServletRequest request,HttpServletResponse response) {
		
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
				try {
					ExceptionHandler.handleException(request, response, e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
		}else {
			System.out.println("Connection is not establised!");
		}
		return false;
	}
	
	public ArrayList<QuotationPerItemBean> performSum() {
		
		//String sumQuery = "select offerCode,sum(QuotationPerItemQuantity*TotalPricePerItem) from QuotationPerItem group by offerCode";
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

	
	public ArrayList<QuotationPerItemBean> getList(String offerCode) {
		
		String Query = "select * from Quotationperitem where offerCode = " + "'" + offerCode+ "'";
		Connection conn = MySqlConnection.getInstance();
		QuotationPerItemDao qdao = QuotationPerItemDao.getInstance();
		ArrayList<QuotationPerItemBean> ar  = new ArrayList<QuotationPerItemBean>();
		if(conn != null) {
			
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(Query);
				
				while(rs.next()) {
					QuotationPerItemBean ibean = new QuotationPerItemBean();
					System.out.println(rs.getInt("catagoryid"));
					ibean.setCatagory(qdao.getCatagoryName(rs.getInt("catagoryid")));
					ibean.setGrade(qdao.getGradeName(rs.getInt("gradeid")));
					ibean.setSizeName(qdao.getSizeName(rs.getInt("sizeid")));
					ibean.setCatagoryId(rs.getInt("catagoryid"));
					ibean.setGradeId(rs.getInt("gradeid"));
					ibean.setSize(rs.getInt("sizeid"));
					ibean.setQuantity(rs.getInt("quotationperitemQuantity"));
					ibean.setWeight(rs.getString("Weights"));
					ibean.setUnits(rs.getString("units"));
					ibean.setTotalPricePerItem(rs.getString("totalpriceperitem"));
					ibean.setprofitPercentage(rs.getString("profitpercentage"));
					ibean.setpricePerItem(rs.getString("price"));
					ar.add(ibean);
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
	
	public String getCatagoryName(int catagoryId) { 
		try {
			String catagory="";
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select * from emscatagory where catagoryId = ?");
			pstmt.setInt(1, catagoryId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				catagory = rs.getString(2);
			}
			return catagory;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getGradeName(int gradeId) {
		try {
			String grade="";
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select * from catagorygrade where gradeId = ?");
			pstmt.setInt(1, gradeId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				grade = rs.getString("grade");
			}
			return grade;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getSizeName(int sizeId) {
		try {
			String size="";
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select * from catagorygradesize where sizeId = ?");
			pstmt.setInt(1, sizeId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				size = rs.getString("size");
			}
			return size;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void deleteFromQuotationPerItem(int catagoryId,int gradeId,int sizeId) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("delete from quotationperitem where catagoryId = ? and gradeId =? and sizeid = ?");
			pstmt.setInt(1, catagoryId);
			pstmt.setInt(2, gradeId);
			pstmt.setInt(3, sizeId);
			int result = pstmt.executeUpdate();
			if(result == 1)
				System.out.println("deleted");
			else {
				System.out.println("not deleted");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

}

