package com.dao;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.bean.AccessBean;
import com.dbConnection.*;

public class AccessDao 
{
	public AccessBean getAllAccess(int userId) {
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select * from access where userId=?");
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AccessBean access = new AccessBean();
				access.setUserId(userId);
				access.setAccessManagement(rs.getInt("accessmanagement"));
				access.setAddProject(rs.getInt("addproject"));
				access.setAddOffer(rs.getInt("addoffer"));
				access.setAddClient(rs.getInt("addclient"));
				access.setAddGeneralStore(rs.getInt("addgeneralstore"));
				access.setAddDrawing(rs.getInt("adddrawing"));
				access.setAddNewStock(rs.getInt("addnewstock"));
				access.setAddOrder(rs.getInt("addorder"));
				access.setAddVendor(rs.getInt("addvendor"));
				access.setFinalQuotation(rs.getInt("finalquotation"));
				access.setGenerateGrn(rs.getInt("generategrn"));
				access.setGenerateIndent(rs.getInt("generateindent"));
				access.setGenerateIssueNote(rs.getInt("generateissuenote"));
				access.setGeneratePo(rs.getInt("generatepo"));
				access.setGrnList(rs.getInt("grnlist"));
				access.setIndentList(rs.getInt("indentlist"));
				access.setIssueNoteList(rs.getInt("issuenotelist"));
				access.setProjectStatus(rs.getInt("projectstatus"));
				access.setPoList(rs.getInt("polist"));
				access.setQuotationPerOffers(rs.getInt("quotationperoffer"));	
				access.setShowQuotationPerOffer(rs.getInt("showquotationperoffer"));
				access.setShowFinalQuotation(rs.getInt("showfinalquotation"));
				access.setStockList(rs.getInt("showstocklist"));
				access.setVendorList(rs.getInt("listvendor"));
				access.setDrawingList(rs.getInt("listdrawing"));
				access.setListOrder(rs.getInt("listorder"));
				access.setAddUser(rs.getInt("addUser"));
				access.setShowLogs(rs.getInt("logs"));
				access.setShowOrder(rs.getInt("orders"));
				access.setAddGraph(rs.getInt("graph"));
				access.setDpr(rs.getInt("dpr"));  
				 access.setDprList(rs.getInt("dprList"));
				 access.setDrawingRevision(rs.getInt("drawingRevision"));
				 access.setPhase(rs.getInt("phase"));
				 access.setPhaseUpdate(rs.getInt("phaseUpdate"));
				 access.setAddMachine(rs.getInt("addMachine"));
				 access.setShowMachine(rs.getInt("showMachine"));
				 access.setListOrder(rs.getInt("giveMntMachine"));
				 access.setGiveMntMachine(rs.getInt("giveMntMachine"));;
				 access.setReceiveMntMachine(rs.getInt("receiveMntMachine"));
				 //access.setListMntMachine(rs.getInt("listMntMachine"));
				 access.setGateOutWrd(rs.getInt("gateOutWrd"));
				 access.setGateInWrd(rs.getInt("gateInWrd"));
				 access.setGateList(rs.getInt("gateList"));
				 access.setPoPrint(rs.getInt("poPrint"));
				 access.setApprovelPending(rs.getInt("approvelPending"));
				 access.setApprovelPenList(rs.getInt("approvelPenList"));
				 access.setDispatch(rs.getInt("dispatch"));
				return access;
			}
		
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void changeStatus(int userId,int status,String field) {
		try {
			Connection con = MySqlConnection.getInstance();
			if(status==1) {
				status=0;
			}else {
				status=1;
			}
			PreparedStatement pstmt = con.prepareStatement("update access set "+field.toUpperCase()+" = "+status+" where userId = ?");
			pstmt.setInt(1, userId);
			System.out.println("access = "+field);
			System.out.println("status = "+status);
			System.out.println("usesr = "+userId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addAcess(int userId) {
		try {
			Connection con = MySqlConnection.getInstance();
			
			PreparedStatement pstmt = con.prepareStatement("insert into access (userId) values (?)");
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int getUserIdFromDatabase(String email) {
		// TODO Auto-generated method stub
		try {
			Connection con = MySqlConnection.getInstance();
			PreparedStatement pstmt = con.prepareStatement("select userId from user where email=?");
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			int userId=0;
			while(rs.next()) {
				userId=rs.getInt("userId");
			}
			return userId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}
}
