package com.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bean.CatagoryGradeSizeBean;
import com.dbConnection.*;

public class GetGradeListDao {
	private static GetGradeListDao instance1 = null;
	
	public static GetGradeListDao getInstance1() {
		if (instance1 == null) {
			instance1 = new GetGradeListDao();
		}
		return instance1;
	}
	public ArrayList<CatagoryGradeSizeBean> getGradeList(String query){
		ArrayList<CatagoryGradeSizeBean> catagories = new ArrayList<CatagoryGradeSizeBean>();
		
		try {
			Connection con = MySqlConnection.getInstance();
			
			
			PreparedStatement pstmt = con.prepareStatement(query);
			// now pstmt will not return single value , it will return all the records of the relation
			
			ResultSet rs = pstmt.executeQuery(); // easy concept hi hai
			
			while(rs.next()) {
				CatagoryGradeSizeBean catagory = new CatagoryGradeSizeBean();
				catagory.setCatagoryId(rs.getInt("gradeId"));
				catagory.setCatagoryName(rs.getString("grade"));
				catagories.add(catagory);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return catagories;
	}
}