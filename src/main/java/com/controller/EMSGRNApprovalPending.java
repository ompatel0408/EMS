package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bean.EMSGRNPendingBean;
import com.bean.EMSPurchaseBean;
import com.dao.EMSGRNApprovalPendingDao;
import com.dao.EMSGRNDao;
import com.dao.ItemDao;
import com.service.EMSGRNPendingServices;
import com.service.ExceptionHandler;
import com.service.PurchaseServices;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.ExceptionHandler;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSGRNApprovalPending extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public EMSGRNApprovalPending() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Gson gson = new Gson();
		    String json = gson.toJson(ItemDao.getInstance().getProjects());
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Gson gson = new Gson();
		
	    ArrayList<EMSGRNPendingBean> AEGPB = EMSGRNPendingServices.fetchDataFromXHRRequest(request.getReader(), request);
	    
	    if(EMSGRNApprovalPendingDao.getInstance().addGRNApprovalPending(AEGPB)) {
	    	System.out.println("inserted successfully!");
	    }else {
	    	System.out.println("not inserted!");
	    }
	    
	    String json = gson.toJson("Hello");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	    
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader reader = request.getReader();
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    String requestBody = sb.toString();
	   
	    Gson gson = new Gson();
	    JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
	    
	    if(jsonObject.get("Token").getAsString().equals("AllDetails")) {
	    	String json = gson.toJson(EMSGRNDao.getInstance().getAllPurchaseDetails(jsonObject.get("projectId").getAsString()));
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
	    }else {
	    	String json = gson.toJson(EMSGRNDao.getInstance().getAllDrtailsFromGRNApprovalPending(jsonObject.get("projectId").getAsString()));
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
	    }
		
		
	}
}
