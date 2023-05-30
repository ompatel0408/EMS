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
import com.service.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
	
	public ArrayList<String> getCatagorytFromDatabase() {
		String selectQuery = "SELECT catagory FROM EMSCatagory ec join store s on ec.catagoryid = s.categoryid";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> ar = new ArrayList<String>();
		if (conn != null) {

			try {
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
	public ArrayList<String> getGradeFromDatabase(String catagory) {
		String selectQuery = "SELECT grade FROM Catagorygrade join store using(gradeid) join emscatagory ec on ec.catagoryid = store.categoryid where catagory=? ";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> ar = new ArrayList<String>();
		if (conn != null) {

			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, catagory);
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
	public ArrayList<String> getSizeFromDatabase(String catagory,String grade) {
		String selectQuery = "SELECT size FROM Catagorygrade join store using(gradeid) join emscatagory ec on ec.catagoryid = store.categoryid join catagorygradesize using(sizeid) where catagory=? and grade=?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> ar = new ArrayList<String>();
		if (conn != null) {

			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, catagory);
				stmt.setString(2, grade);
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
	public ArrayList<String> getQuantityFromDatabase(String catagory,String grade,String size) {
		String selectQuery = "SELECT quantity FROM Catagorygrade join store using(gradeid) join emscatagory ec on ec.catagoryid = store.categoryid join catagorygradesize using(sizeid) where catagory=? and grade=? and size=?";
		Connection conn = MySqlConnection.getInstance();
		ArrayList<String> ar = new ArrayList<String>();
		if (conn != null) {

			try {
				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setString(1, catagory);
				stmt.setString(2, grade);
				stmt.setString(3, size);
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
	public int getSizeIdFromDatabase(int category,int grade, String size) {

		System.out.println("in getSizeId = category : " + category + ", grade : " + grade + ", size : " + size);
		String selectQuery = "select sizeId from EMScatagory EC join catagoryGrade cg ON EC.catagoryId = cg.catagoryId join catagorygradesize cs on cg.gradeId = cs.gradeId where EC.catagoryid = ? and cg.gradeId = ? and cs.size = ?";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setInt(1, category);
				stmt.setInt(2, grade);
				stmt.setString(3, size);
				ResultSet rs = stmt.executeQuery();
				int rows = 0;
				if (rs.next()) {
					rows = rs.getInt(1);
				}
				System.out.println("Row : " + rows);
				return rows;

			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
		}
		return 0;
	}
	public int getGradeIdFromDatabase(int category, String grade) {

		String selectQuery = "SELECT gradeId FROM catagorygrade WHERE CatagoryId = ? && grade = ?";
		Connection conn = MySqlConnection.getInstance();

		if (conn != null) {

			try {

				PreparedStatement stmt = conn.prepareStatement(selectQuery);
				stmt.setInt(1, category);
				stmt.setString(2, grade);
				ResultSet rs = stmt.executeQuery();
				int rows = 0;
				if (rs.next()) {
					rows = rs.getInt(1);
				}
				return rows;

			} catch (SQLException E) {
				E.printStackTrace();
			}
		} else {
			System.out.println("Connection is not establised!");
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
	public ArrayList<String> getProjectId() {
		// ArrayList<CatagoryGradeSizeBean> grades = new
		// ArrayList<CatagoryGradeSizeBean>();
		ArrayList<String> sd=new ArrayList<String>();
		String query1 = "select projectid from projects";
		try {
			Connection con = MySqlConnection.getInstance();

			PreparedStatement pstmt = con.prepareStatement(query1);

			// now pstmt will not return single value , it will return all the records of
			// the relation

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// CatagoryGradeSizeBean grade = new CatagoryGradeSizeBean();
				sd.add(rs.getString(1));
			}
			return sd;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
	
	private void delete(int storeid) {
		Connection conn = MySqlConnection.getInstance();
		if (conn == null) {
			System.out.println("connection is not connected..");
		}
		else {
			try {
				PreparedStatement stmt1 = conn.prepareStatement("delete from store where storeid=?");
				stmt1.setInt(1, storeid);
				stmt1.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void update(int q,int catagory,int gid,int sid)
	{
		System.out.println("qu"+q);
		System.out.println("qu"+catagory);
		System.out.println("qu"+gid);
		System.out.println("qu"+sid);
		String uq="update store set quantity=quantity-? where categoryId=? and gradeId=? and sizeId=?";
		String check="select storeid,quantity from store where categoryId=? and gradeId=? and sizeId=?";
Connection conn = MySqlConnection.getInstance();
		
		if (conn == null) {
			System.out.println("connection is not connected..");
		} else {
			try {
				PreparedStatement stmt1 = conn.prepareStatement(uq);
				stmt1.setInt(1, q);
				stmt1.setInt(2, catagory);
				stmt1.setInt(3, gid);
				stmt1.setInt(4, sid);
				stmt1.executeUpdate();
				PreparedStatement stmt = conn.prepareStatement(check);
				stmt.setInt(1, catagory);
				stmt.setInt(2, gid);
				stmt.setInt(3, sid);
				ResultSet rs= stmt.executeQuery();
				while(rs.next())
				{
					if(rs.getInt("quantity")==0)
					{
						System.out.println("Delete");
						delete(rs.getInt("storeid"));
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean addItems(ArrayList<EMSIssueNoteBean> inb,HttpServletRequest request,HttpServletResponse response) {
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
					int gid=sdInstance.getGradeIdFromDatabase(qpid.getCategoryIdFromDatabase(s.getCatagory()), s.getGrade());
					stmt.setInt(4,gid );
					int sid=sdInstance.getSizeIdFromDatabase(qpid.getCategoryIdFromDatabase(s.getCatagory()), sdInstance.getGradeIdFromDatabase(qpid.getCategoryIdFromDatabase(s.getCatagory()), s.getGrade()), s.getSize());
					stmt.setInt(5, sid);	
					stmt.setInt(6, s.getQuantity());
					stmt.setString(7, s.getRemark());
					stmt.setString(8, s.getUom());
					stmt.setString(9, s.getIssuePerson());
					stmt.setString(10, s.getContractor());
					stmt.addBatch();
					update(s.getQuantity(),category,gid,sid);
				}
				int[] result = stmt.executeBatch();
				return true;
			} catch (SQLException e) {
				try {
					ExceptionHandler.handleException(request, response, e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

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
	
	
	public ArrayList<EMSIssueNoteBean> getAllList()
	{
		ArrayList<EMSIssueNoteBean> issues = new ArrayList<EMSIssueNoteBean>();
		
		try {
			Connection con = MySqlConnection.getInstance();
			
			PreparedStatement pstmt = con.prepareStatement("select issueid,PID,Catagory,grade,size,quantity,UOM,personname,contractorname from issue join emscatagory using(catagoryid) join catagorygrade using(gradeid) join catagorygradesize using(sizeid) order by issueid  desc");
			// now pstmt will not return single value , it will return all the records of the relation
			
			ResultSet rs = pstmt.executeQuery(); // easy concept hi hai
			
			while(rs.next()) {
				EMSIssueNoteBean issue=new EMSIssueNoteBean(); 
				issue.setIssueId(rs.getInt("issueid"));
				issue.setPid(rs.getString("PID"));
				issue.setCatagory(rs.getString("Catagory"));
				issue.setGrade(rs.getString("grade"));
				issue.setSize(rs.getString("size"));
				issue.setQuantity(rs.getInt("quantity"));
				issue.setUom(rs.getString("UOM"));
				issue.setIssuePerson(rs.getString("personname"));
				issue.setContractor(rs.getString("contractorname"));
				issues.add(issue);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return issues;
	}
	
	
}