package com.controller;

import java.io.BufferedReader;
import java.io.IOException;

import com.bean.EMSDrawingBean;
import com.bean.EMSGRNBean;
import com.bean.EMSLogsBean;
import com.dao.EMSDrawingDao;
import com.dao.EMSGRNDao;
import com.dao.EMSLogsDao;
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
			HttpSession session = request.getSession();
			System.out.println("offerId: "+request.getParameter("offerId"));
			System.out.println("ProjectId :"+request.getParameter("projectId"));
			String drawingId = EMSDrawingServices.getActualDrawingId(request.getParameter("offerId"), EMSDrawingDao.getInstance().getDrawingIdFromdatabase(request.getParameter("projectId")));
			System.out.println("Drawing Id :"+drawingId);
			if(EMSDrawingDao.getInstance().addDrawingDetails(drawingId,EGB)) {
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
