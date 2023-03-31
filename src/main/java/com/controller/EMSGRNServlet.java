package com.controller;




import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;

import com.bean.EMSGRNBean;
import com.dao.EMSFinalQuotationDao;
import com.dao.EMSGRNDao;
import com.dao.EMSPurchaseDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.GRNServices;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;


@MultipartConfig(
		 fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class EMSGRNServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EMSGRNServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Get Reached!!!!!!!");
		Gson gson = new Gson();
		String json = gson.toJson(EMSPurchaseDao.getInstance().getVendorNameFromDatabase());
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EMSGRNBean EGB =  GRNServices.uploadPic(request);
		if(EGB != null) {
			System.out.println(EMSGRNDao.getInstance().addGRN(new EMSGRNBean(request.getParameter("VendorName"), request.getParameter("ReceivedDate"),EGB.getPath1().trim(),EGB.getPath2().trim())) ?  "GRN Added Successfully!!!" :  "GRN not added!");
			response.sendRedirect("EMSGRNList.jsp");
		}
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Delete Called!");
		BufferedReader reader = request.getReader();
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    String requestBody = sb.toString();
	   
	    Gson gson = new Gson();
	    JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
		
		
		if(EMSGRNDao.getInstance().deleteGRN(Integer.parseInt(jsonObject.get("grnId").getAsString()))) {
			System.out.println("Deleted successfully!");
		}else {
			System.out.println("not Deleted successfully!");
		}
		String json = gson.toJson("Hello");
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}
	

}
