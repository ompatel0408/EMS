package com.controller;

import java.io.BufferedReader;
import java.io.IOException;

import com.bean.EMSLogsBean;
import com.bean.EMSProductionBean;
import com.dao.EMSLogsDao;
import com.dao.EMSProductionDao;
import com.dao.ItemDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		try {

		     String projectId= request.getParameter("projectId");
		     String remark=request.getParameter("Remark");
		     String workDonePer=request.getParameter("workDonePer");
		     String qualityCheck = request.getParameter("qualityCheck");
		    
		     
		     EMSProductionDao ED = EMSProductionDao.getInstance();
		     
		     
		     HttpSession session = request.getSession();
		     if(ED.addDataToDatabase(new EMSProductionBean(projectId, remark, Integer.parseInt(workDonePer),qualityCheck))) {
		    	 
		    	 System.out.println("inserted successfully!");
		    	 
		    	 
		    	 if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean(workDonePer.concat(" work of the project ").concat(projectId).concat(" is completed!"),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","PRODUCTION"))) {
						init();
						System.out.println(" Client insert Logs Inserted!");
					}else {
						System.out.println("Client insert Logs not inserted!");
					}
		    	 
		    	 response.sendRedirect("EMSDirectorsDashboard.jsp");
		     }else {
		    	 System.out.println("Not inserted!");
		    	 response.sendRedirect("EMSProduction.jsp");
		     }    
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		 
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {

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
		    	String json = gson.toJson(Id.getProjects());
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
		    }
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
	}
}
