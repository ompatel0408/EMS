package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.bean.EMSIssueNoteBean;
import com.dbConnection.MySqlConnection;

public class EMSIssueNoteDao {
	
	private static EMSIssueNoteDao instance = null;
	private static EMSStoreDao sdInstance = EMSStoreDao.getInstance();
	private static QuotationPerItemDao qpid = QuotationPerItemDao.getInstance();
	private static String formattedString;
	
	public static EMSIssueNoteDao getInstance() {
		
		if(instance == null) {
			instance = new EMSIssueNoteDao();
		}
		return instance;
	}
	public int getGradeId(String grade) {
		// ArrayList<CatagoryGradeSizeBean> grades = new
		// ArrayList<CatagoryGradeSizeBean>();
		String query1 = "select gradeid from catagorygrade where grade=?";
		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement(query1);
			pstmt.setString(1, grade);
			// now pstmt will not return single value , it will return all the records of
			// the relation

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// CatagoryGradeSizeBean grade = new CatagoryGradeSizeBean();
				return rs.getInt("gradeid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int getCatagoryId(String catagory) {
		// ArrayList<CatagoryGradeSizeBean> grades = new
		// ArrayList<CatagoryGradeSizeBean>();
		String query1 = "select catagoryid from emscatagory where catagory=?";
		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement(query1);
			pstmt.setString(1, catagory);
			// now pstmt will not return single value , it will return all the records of
			// the relation

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// CatagoryGradeSizeBean grade = new CatagoryGradeSizeBean();
				return rs.getInt("catagoryid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getSizeId(String size) {
		// ArrayList<CatagoryGradeSizeBean> grades = new
		// ArrayList<CatagoryGradeSizeBean>();
		String query1 = "select sizeid from catagorygradesize where size=?";
		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement(query1);
			pstmt.setString(1, size);
			// now pstmt will not return single value , it will return all the records of
			// the relation

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// CatagoryGradeSizeBean grade = new CatagoryGradeSizeBean();
				return rs.getInt("sizeid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public boolean addDataToDatabases(EMSIssueNoteBean Ed) {
		int catagoryId=getCatagoryId(Ed.getCatagory());
		int gradeId=getGradeId(Ed.getGrade());
		int sizeId=getSizeId(Ed.getSize());
		String insertQuery = "INSERT INTO (pid,issuedate,quantity,personname,contractorname,remark,catagoryid,gradeId,sizeId,uom) issue Values(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = MySqlConnection.getInstance();


		  final DateTimeFormatter formate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		  LocalDateTime ldt = LocalDateTime.now();
		  formattedString = ldt.format(formate);
		  //issueDate = LocalDateTime.now().toString();
		if(conn != null) {
			
			try {
		
				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				stmt.setString(1,Ed.getPid());
				stmt.setString(2, formattedString);
				stmt.setInt(3, Ed.getQuantity());
				stmt.setString(4, Ed.getIssuePerson());
				stmt.setString(5, Ed.getContractor());
				stmt.setString(6, Ed.getRemark());
				stmt.setString(7, Ed.getCatagory());
				stmt.setString(8, Ed.getGrade());
				stmt.setString(9, Ed.getSize());
				stmt.setString(10, Ed.getUom());
				stmt.executeUpdate();
				return true;
			}catch(SQLException E) {
				E.printStackTrace();
			}
		}else {
			System.out.println("Connection is not establised!");
		}
		
		return false;
	}
	
	public boolean addItems(ArrayList<EMSIssueNoteBean> inb) {
		String insertQuery = "INSERT INTO issue (pid, issueDate,CatagoryId, gradeId, sizeId, quantity, remark, uom, Personname, contractorname) VALUES(?,?,?,?,?,?,?,?,?,?)";

		Connection conn = MySqlConnection.getInstance();
		final DateTimeFormatter formate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		  LocalDateTime ldt = LocalDateTime.now();
		  formattedString = ldt.format(formate);

		if (conn == null) {
			System.out.println("connection is not connected..");
		} else {
			try {
				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				for (EMSIssueNoteBean s : inb) {
					stmt.setString(1, s.getPid());
					System.out.println(formattedString);
					stmt.setString(2, formattedString);
					int category=qpid.getCategoryIdFromDatabase(s.getCatagory());
					System.out.println(category);
					stmt.setInt(3, category);
					stmt.setInt(4, sdInstance.getGradeIdFromDatabase(qpid.getCategoryIdFromDatabase(s.getCatagory()), s.getGrade()));
					stmt.setInt(5, sdInstance.getSizeIdFromDatabase(qpid.getCategoryIdFromDatabase(s.getCatagory()), sdInstance.getGradeIdFromDatabase(qpid.getCategoryIdFromDatabase(s.getCatagory()), s.getGrade()), s.getSize()));	
					stmt.setInt(6, s.getQuantity());
					stmt.setString(7, s.getRemark());
					stmt.setString(8, s.getUom());
					stmt.setString(9, s.getIssuePerson());
					stmt.setString(10, s.getContractor());
					stmt.addBatch();
				}
				int[] result = stmt.executeBatch();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	public ArrayList<EMSIssueNoteBean> getAllIssues() {
		ArrayList<EMSIssueNoteBean> issues = new ArrayList<EMSIssueNoteBean>();
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select issueDate,quantity,personname,contractorname,issueId,pId,catagory,grade,size,uom from issue join emscatagory using (catagoryid) join catagorygrade using (gradeid) join catagorygradesize using (sizeid) join projects on issue.pid = projects.projectid;");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EMSIssueNoteBean issue = new EMSIssueNoteBean();
				issue.setIssueDate(rs.getString(1));
				issue.setQuantity(rs.getInt(2));
				issue.setIssuePerson(rs.getString(3));
				issue.setContractor(rs.getString(4));
				issue.setIssueId(rs.getInt(5));
				issue.setPid(rs.getString(6));
				issue.setCatagory(rs.getString(7));
				issue.setGrade(rs.getString(8));
				issue.setSize(rs.getString(9));
				issue.setUom(rs.getString(10));
				issues.add(issue);
			}
			return issues;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteIssue(int issueId) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("delete from issue where issueId = ?");
			pstmt.setInt(1, issueId);
			pstmt.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void updateIssue(String newData, String changeField, int issueId) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("update issue set " + changeField + "= ? where issueid = ? ");
			pstmt.setString(1, newData);
			pstmt.setInt(2, issueId);
			pstmt.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}