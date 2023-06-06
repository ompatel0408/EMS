package com.controller;

//import java.io.BufferedReader;
//import java.io.IOException;
//
//import com.bean.EMSDrawingBean;
//import com.bean.EMSGRNBean;
//import com.bean.EMSLogsBean;
//import com.dao.EMSDrawingDao;
//import com.dao.EMSGRNDao;
//import com.dao.EMSLogsDao;
//import com.dao.EMSPurchaseDao;
//import com.dao.ItemDao;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.service.EMSDrawingServices;
//import com.service.ExceptionHandler;
//import com.service.GRNServices;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@MultipartConfig(
//		 fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
//		 maxFileSize = 1024 * 1024 * 10,      // 10 MB
//		 maxRequestSize = 1024 * 1024 * 100   // 100 MB
//	)
//public class EMSDrawingServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private static EMSDrawingServlet instance = null;
//	
//	public static EMSDrawingServlet getInstance() {
//		if(instance == null) {
//			instance = new EMSDrawingServlet();
//		}
//		return instance;
//	}
//	
//    public EMSDrawingServlet() {
//        // TODO Auto-generated constructor stub
//    }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//    
//		try {
//			System.out.println("inPOst");
//	    	Gson gson = new Gson();
//	    	String json = gson.toJson(ItemDao.getInstance().getProjects());
//	    	response.setContentType("application/json");
//	        response.setCharacterEncoding("UTF-8");
//	        response.getWriter().write(json);
//	    
//		}catch(Exception e) {
//			ExceptionHandler.handleException(request, response, e);
//		}
//			
//	}
//
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//		try{
//
//			
//			EMSDrawingBean EGB =  EMSDrawingServices.uploadPic(request,request.getParameter("offerId"),request.getParameter("projectId"),request.getParameter("SubItem"));
//			if(EGB != null) {
//				HttpSession session = request.getSession();
//				
//				String drawingId = EMSDrawingServices.getActualDrawingId(request.getParameter("offerId"), EMSDrawingDao.getInstance().getDrawingIdFromdatabase(request.getParameter("projectId")));
//				
//				if(EMSDrawingDao.getInstance().addDrawingDetails(drawingId,EGB)) {
//					System.out.println("Inserted Successfully!");
//					
//					if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A new drawing of ".concat(request.getParameter("offerId")).concat(" has been added in ").concat(request.getParameter("projectId")),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","DRAWING"))) {
//						System.out.println("DRAWING insert Logs Inserted!");
//					}else {
//						System.out.println("DRAWING  insert Logs not inserted!");
//					}
//				}else {
//					System.out.println("not sucessfully!");
//				}
//				response.sendRedirect("EMSDrawingList.jsp");
//			}
//		}catch(Exception e) {
//			ExceptionHandler.handleException(request, response, e);
//		}
//		
//	}
//	
//    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//    	
//    	try {
//    		
//
//        	BufferedReader reader = request.getReader();
//    	    StringBuilder sb = new StringBuilder();
//    	    String line;
//    	    while ((line = reader.readLine()) != null) {
//    	        sb.append(line);
//    	    }
//    	    String requestBody = sb.toString();
//    	   
//    	    Gson gson = new Gson();
//    	    JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
//    	    if(jsonObject.get("token").getAsString().equals("offer"))
//    	    {    	    	
//    	    	String json = gson.toJson(EMSDrawingDao.getInstance().getOfferNameFromDatabase(jsonObject.get("ProjectId").getAsString()));
//    	    	response.setContentType("application/json");
//    	    	response.setCharacterEncoding("UTF-8");
//    	    	response.getWriter().write(json);
//    	    }
//    	    else if(jsonObject.get("token").getAsString().equals("subItem"))
//    	    {
//    	    	System.out.println("456");
//    	    	String json = gson.toJson(EMSDrawingDao.getInstance().getsubItemFromDatabase(jsonObject.get("itemcode").getAsString()));
//    	    	System.out.println(json);
//    	    	response.setContentType("application/json");
//    	    	response.setCharacterEncoding("UTF-8");
//    	    	response.getWriter().write(json);
//    	    	
//    	    }
//    	}catch(Exception e) {
//    		ExceptionHandler.handleException(request, response, e);
//    	}
//	}
//    
//}



import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bean.EMSDrawingBean;
import com.bean.EMSLogsBean;

import com.dao.EMSDrawingDao;
import com.dao.EMSGRNDao;
import com.dao.EMSLogsDao;
import com.dao.EMSPurchaseDao;
import com.dao.ItemDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.EMSDrawingServices;
import com.service.ExceptionHandler;
import com.service.GRNServices;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
    
		try {
			
			String drawing="no";
			drawing=request.getParameter("drawing");
			if(drawing.equals("yes"))
			{
				String projectId=request.getParameter("projectId");
				String itemCode=request.getParameter("itemCode");
				String subItemCode=request.getParameter("subItemCode");
				System.out.println(projectId+itemCode+subItemCode);
				ArrayList<EMSDrawingBean> sub=new ArrayList<EMSDrawingBean>();
				sub=EMSDrawingDao.getInstance().getDrawingHistory(projectId,itemCode,subItemCode);
				request.setAttribute("drawings", sub);
				RequestDispatcher rd = request.getRequestDispatcher("AllRevisions.jsp");
				rd.forward(request, response);
				
			}
	    
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{

			
			EMSDrawingBean EGB =  EMSDrawingServices.uploadPic(request,request.getParameter("offerId"),request.getParameter("projectId"),request.getParameter("SubItem"),response);
			if(EGB != null) {
				HttpSession session = request.getSession();
				
				String drawingId = EMSDrawingServices.getActualDrawingId(request.getParameter("offerId"), EMSDrawingDao.getInstance().getDrawingIdFromdatabase(request.getParameter("projectId")));
				
				if(EMSDrawingDao.getInstance().addDrawingDetails(drawingId,EGB,request,response)) {
					System.out.println("Inserted Successfully!");
					
					if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A new drawing of ".concat(request.getParameter("offerId")).concat(" has been added in ").concat(request.getParameter("projectId")),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","DRAWING"))) {
						System.out.println("DRAWING insert Logs Inserted!");
					}else {
						System.out.println("DRAWING  insert Logs not inserted!");
					}
				}else {
					System.out.println("not sucessfully!");
				}
				response.sendRedirect("EMSDrawingList.jsp");
			}
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
	}
	
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	
    	try {
    		

        	BufferedReader reader = request.getReader();
    	    StringBuilder sb = new StringBuilder();
    	    String line;
    	    while ((line = reader.readLine()) != null) {
    	        sb.append(line);
    	    }
    	    String requestBody = sb.toString();
    	   
    	    Gson gson = new Gson();
    	    JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
    	    System.out.println(jsonObject);
    	    if(jsonObject.get("token").getAsString().equals("offer"))
    	    {    	    	
    	    	String json = gson.toJson(EMSDrawingDao.getInstance().getOfferNameFromDatabase(jsonObject.get("ProjectId").getAsString()));
    	    	response.setContentType("application/json");
    	    	response.setCharacterEncoding("UTF-8");
    	    	response.getWriter().write(json);
    	    }
    	    else if(jsonObject.get("token").getAsString().equals("subItem"))
    	    {
    	    	System.out.println("456");
    	    	String json = gson.toJson(EMSDrawingDao.getInstance().getsubItemFromDatabase(jsonObject.get("itemcode").getAsString()));
    	    	System.out.println(json);
    	    	response.setContentType("application/json");
    	    	response.setCharacterEncoding("UTF-8");
    	    	response.getWriter().write(json);
    	    	
    	    }
    	    else if(jsonObject.get("token").getAsString().equals("projects")){
    	    	System.out.println("inPOst");
    	    	String json = gson.toJson(ItemDao.getInstance().getProjects());
    	    	response.setContentType("application/json");
    	        response.setCharacterEncoding("UTF-8");
    	        response.getWriter().write(json);
    	    }
    	}catch(Exception e) {
    		ExceptionHandler.handleException(request, response, e);
    	}
	}
    
}