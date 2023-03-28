package com.controller;

import java.io.BufferedReader;
import java.io.IOException;

import com.bean.EMSProductionBean;
import com.dao.EMSProductionDao;
import com.dao.ItemDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class EMSProductionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EMSProductionServlet instance = null;
	
	public static EMSProductionServlet getInstance() {
		
		if(instance == null) {
			instance = new  EMSProductionServlet();
		}
		return instance;
	}

    public EMSProductionServlet() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		     String projectId= request.getParameter("projectId");
		     String remark=request.getParameter("Remark");
		     String workDonePer=request.getParameter("workDonePer");
		     String qualityCheck = request.getParameter("qualityCheck");
		    
		     
		     
		     
		     EMSProductionDao ED = EMSProductionDao.getInstance();
		     
		     
		     
		     if(ED.addDataToDatabase(new EMSProductionBean(projectId, remark, Integer.parseInt(workDonePer),qualityCheck))) {
		    	 
		    	 System.out.println("inserted successfully!");
		    	 response.sendRedirect("EMSDirectorsDashboard.jsp");
		     }else {
		    	 System.out.println("Not inserted!");
		    	 response.sendRedirect("EMSProduction.jsp");
		     }    
		 
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		BufferedReader reader = request.getReader();
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    String requestBody = sb.toString();
	    
	    ItemDao Id = ItemDao.getInstance();
	    Gson gson = new Gson();
	    JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
	    
	    if(jsonObject.get("token").getAsString().equals("Projects")) {  
	    	System.out.println("Hello");
	    	String json = gson.toJson(Id.getProjects());
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
	    }
	}
	

}
