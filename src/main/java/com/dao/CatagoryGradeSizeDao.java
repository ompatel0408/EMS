package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.bean.CatagoryGradeSizeBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CatagoryGradeSizeDao {
	private static CatagoryGradeSizeDao instance = null;

	private CatagoryGradeSizeDao() {
	}

	public static CatagoryGradeSizeDao getInstance() {
		if (instance == null) {
			instance = new CatagoryGradeSizeDao();
		}
		return instance;
	}

	public ArrayList<CatagoryGradeSizeBean> getCatagoryList() {
		ArrayList<CatagoryGradeSizeBean> catagories = new ArrayList<CatagoryGradeSizeBean>();

		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement("select * from emscatagory");
			// now pstmt will not return single value , it will return all the records of
			// the relation

			ResultSet rs = pstmt.executeQuery(); // easy concept hi hai

			while (rs.next()) {
				CatagoryGradeSizeBean catagory = new CatagoryGradeSizeBean();
				catagory.setCatagoryId(rs.getInt("catagoryId"));
				catagory.setCatagoryName(rs.getString("catagory"));
				catagories.add(catagory);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return catagories;
	}

	public boolean addCatagory(CatagoryGradeSizeBean cBean,HttpServletRequest request,HttpServletResponse response) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("insert into emscatagory(catagory) values (?)");
			pstmt.setString(1, cBean.getCatagoryName());
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			try {
				ExceptionHandler.handleException(request, response, e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}

	public boolean addGrade(String selectedId, String gradeName,HttpServletRequest request,HttpServletResponse response) {
		String updateQuery = "insert into catagorygrade(Grade,catagoryid) values (?,?)";
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement(updateQuery);
			pstmt.setString(1, gradeName);
			pstmt.setString(2, selectedId);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addSize(String CatagoryId, String GradeName, String size,HttpServletRequest request,HttpServletResponse response) {
		int id = 0;
		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement("select gradeid from catagorygrade where grade=?");
			pstmt.setString(1, GradeName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}

			if(addFinalSize(id, size)) {
				return true;
			}
		} catch (Exception e) {
			try {
				ExceptionHandler.handleException(request, response, e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

		}
		return false;
	}

	private boolean addFinalSize(int id, String size) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("insert into catagorygradesize(Gradeid,size) values (?,?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, size);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<CatagoryGradeSizeBean> getGradeList(String catId) {
		ArrayList<CatagoryGradeSizeBean> grades = new ArrayList<CatagoryGradeSizeBean>();
		String query1 = "select * from catagorygrade where catagoryId = ?";
		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement(query1);
			pstmt.setString(1, catId);
			// now pstmt will not return single value , it will return all the records of
			// the relation

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				CatagoryGradeSizeBean grade = new CatagoryGradeSizeBean();
				grade.setGradeId(rs.getInt("gradeId"));
				grade.setGrade(rs.getString("grade"));
				grades.add(grade);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grades;
	}

	public ArrayList<CatagoryGradeSizeBean> getSizeList(String grade) {
		ArrayList<CatagoryGradeSizeBean> sizes = new ArrayList<CatagoryGradeSizeBean>();
		String query = "select * from catagorygrade cg join catagorygradesize cgs on cg.gradeId = cgs.gradeId where cg.grade = ?";
		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, grade);
			// now pstmt will not return single value , it will return all the records of
			// the relation

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				CatagoryGradeSizeBean size = new CatagoryGradeSizeBean();
				size.setSizeId(rs.getInt("SizeId"));
				size.setSize(rs.getString("size"));
				sizes.add(size);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sizes;
	}

	public int getQuantity(String category, String grade, String size) {
		// TODO Auto-generated method stub
		String query = " select * from Emscatagory c join catagorygrade cg on cg.catagoryId = c.catagoryId join catagorygradesize cgs on cgs.gradeid = cg.gradeid join store s on s.sizeid = cgs.sizeid where c.catagoryid = ? and cg.grade=? and cgs.size=?";
		int count = 0;
		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, category);
			pstmt.setString(2, grade);
			pstmt.setString(3, size);
			// now pstmt will not return single value , it will return all the records of
			// the relation

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// System.out.println(rs.getInt("QUANTITY"));
				count = rs.getInt("Quantity");
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int getGivenQuantity(String category, String grade, String size, String itemName) {
		String query = "select sum(QuotationPerItemQuantity) from offer join quotationperitem using(offercode) join emscatagory using(catagoryid) join catagorygrade using(gradeid) join catagorygradesize using(sizeid) where quotationperitem.catagoryid = ? and grade=? and size=? and offername=?";
		int count=0;
		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, category);
			pstmt.setString(2, grade);
			pstmt.setString(3, size);
			pstmt.setString(4, itemName);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(pstmt.toString());

			while (rs.next()) {
				// System.out.println(rs.getInt("QUANTITY"));
				System.out.println(rs.getString(1));
				count = rs.getInt(1);
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
}
}
