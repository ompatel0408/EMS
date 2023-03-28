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
				access.setQuotationPerOffers(rs.getInt("quotationperoffer"));
				access.setFinalQuotation(rs.getInt("finalquotation"));
				access.setGenerateIndent(rs.getInt("generateindent"));
				access.setIndentList(rs.getInt("indentlist"));
				access.setApprovalPending(rs.getInt("approvalpending"));
				access.setGeneratePo(rs.getInt("generatepo"));
				access.setPoList(rs.getInt("polist"));
				access.setGenerateGrn(rs.getInt("generategrn"));
				access.setGrnList(rs.getInt("grnlist"));
				access.setSellItemList(rs.getInt("sellitemlist"));
				access.setSellIssuedList(rs.getInt("sellissuedlist"));
				access.setShowProject(rs.getInt("showProject"));
				access.setShowClient(rs.getInt("showclient"));
				access.setAddClient(rs.getInt("addclient"));
				access.setShowOffer(rs.getInt("showoffer"));
				access.setShowQuotationPerOffer(rs.getInt("showquotationperoffer"));
				access.setShowFinalQuotation(rs.getInt("showfinalquotation"));
				access.setAddStock(rs.getInt("stocklist"));
				access.setShowStockList(rs.getInt("showstocklist"));
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
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
