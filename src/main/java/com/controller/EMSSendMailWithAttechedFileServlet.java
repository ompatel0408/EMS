package com.controller;

import java.io.IOException;
import com.dao.EMSVendorsDao;
import com.service.EMSDrawingServices;
import com.util.SendMail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(
		 fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class EMSSendMailWithAttechedFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EMSSendMailWithAttechedFileServlet instance = null;
	
	
	public static EMSSendMailWithAttechedFileServlet getInstance() {
		
		if(instance == null) {
			instance = new EMSSendMailWithAttechedFileServlet();
		}
		return instance;
	}
    
    public EMSSendMailWithAttechedFileServlet() {
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(SendMail.sendPurchaseOrder(EMSDrawingServices.uploadFileWithFile(request).getPath1(),EMSVendorsDao.getInstance().getVendorMailFromDatabase(request.getParameter("vendorName")),request,response)) {
			System.out.println("Mail is sent successfully!");
			response.sendRedirect("EMSDirectorsDashboard.jsp");
		}else {
			System.out.println("Mail not sent");
		}
	}

}
