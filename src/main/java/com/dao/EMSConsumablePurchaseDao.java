package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import com.bean.EMSConsumablePurchaseBean;
import com.dbConnection.MySqlConnection;
import com.service.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSConsumablePurchaseDao {
	
	private static EMSConsumablePurchaseDao instance = null;
	
	public static EMSConsumablePurchaseDao getInstance() {
		
		if(instance == null) {
			instance = new EMSConsumablePurchaseDao();
		}
		return instance;
	}
	
	public boolean addPurchase(ArrayList<EMSConsumablePurchaseBean> AEPB,HttpServletRequest request,HttpServletResponse response) {
		
		String insertQuery = "INSERT INTO ConsumableItemPurchase(CategoryName,ITEMNAME,Quantity,VendorId,RATE,DISCOUNT,SGST,CGST,CURRENTDATE,REMARKS,totalAmount) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = MySqlConnection.getInstance();
		
		if(conn != null) {
			
			try {
				
				PreparedStatement stmt = conn.prepareStatement(insertQuery);
				for(EMSConsumablePurchaseBean EPB:AEPB) {
					stmt.setString(1,EPB.getCategoryName());
					stmt.setString(2, EPB.getItemName());
					stmt.setInt(3, EPB.getQuantity());
					stmt.setInt(4, EPB.getVendor());
					stmt.setDouble(5, EPB.getRate());
					stmt.setDouble(6, EPB.getDiscount());
					stmt.setFloat(7, EPB.getSgst());
					stmt.setFloat(8, EPB.getCgst());
					stmt.setString(9, EPB.getCurrentDate());
					stmt.setString(10, EPB.getRemarks());
					stmt.setDouble(11, EPB.getTotalAmount());
					stmt.addBatch();
				}
				stmt.executeBatch();
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
			System.out.println("Connction is not establised!");
		}
		
		return false;
	}

	
}
