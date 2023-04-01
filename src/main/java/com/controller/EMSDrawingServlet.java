package com.controller;

import java.io.BufferedReader;
import java.io.IOException;

import com.bean.EMSDrawingBean;
import com.bean.EMSGRNBean;
import com.dao.EMSDrawingDao;
import com.dao.EMSGRNDao;
import com.dao.EMSPurchaseDao;
import com.dao.ItemDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.EMSDrawingServices;
import com.service.GRNServices;

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
public class EMSDrawingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EMSDrawingServlet instance = null;
	
	public static EMSDrawingServlet getInstance() {
		if(instance == null) {
			instance = new EMSDrawingServlet();
		}
		return instance;
	}
	
    public EMSDrawingServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("get Reached!!!!!!!11111");
    	
    	Gson gson = new Gson();
    	System.out.println(ItemDao.getInstance().getProjects().size());
    	String json = gson.toJson(ItemDao.getInstance().getProjects());
    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		EMSDrawingBean EGB =  EMSDrawingServices.uploadPic(request);
		if(EGB != null) {
			System.out.println("Project Id"+request.getParameter("projectId"));
			System.out.println("order Id"+request.getParameter("offerId"));
			
			System.out.println(EMSDrawingDao.getInstance().addDrawingDetails(EMSDrawingServices.getActualDrawingId(request.getParameter("offerId"), EMSDrawingDao.getInstance().getDrawingIdFromdatabase(request.getParameter("projectId"))),EGB) == true ? "Inserted Successfully!" : "not sucessfully!");        
//			System.out.println(EMSGRNDao.getInstance().addGRN(new EMSGRNBean(request.getParameter("VendorName"), request.getParameter("ReceivedDate"),EGB.getPath1().trim(),EGB.getPath2().trim())) ?  "GRN Added Successfully!!!" :  "GRN not added!");
			response.sendRedirect("EMSDrawingList.jsp");
		}
	}
	
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("Put Reached!!!!!!!");
    	
    	BufferedReader reader = request.getReader();
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    String requestBody = sb.toString();
	   
	    Gson gson = new Gson();
	    JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
    	String json = gson.toJson(EMSDrawingDao.getInstance().getOfferNameFromDatabase(jsonObject.get("ProjectId").getAsString()));
    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
	}
    

}
