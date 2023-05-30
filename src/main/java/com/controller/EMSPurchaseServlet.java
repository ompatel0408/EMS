package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bean.EMSLogsBean;
import com.bean.EMSOffersBean;
import com.bean.EMSPurchaseBean;
import com.dao.EMSLogsDao;
import com.dao.EMSOffersDao;
import com.dao.EMSPurchaseDao;
import com.dao.IndentDao;
import com.dao.ItemDao;
import com.dao.QuotationPerItemDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.ExceptionHandler;
import com.service.PurchaseServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class EMSPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EMSPurchaseServlet instance = null;
	
	
	public static EMSPurchaseServlet getInstance() {
		
		if(instance == null) {
			instance = new EMSPurchaseServlet();
		}
		return instance;
	}
    
    public EMSPurchaseServlet() {
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
		
		try {

			ArrayList<EMSPurchaseBean> AEPB = PurchaseServices.fetchDataFromXHRRequest(request.getReader(), request);
			
			HttpSession session = request.getSession();
			if(EMSPurchaseDao.getInstance().addPurchase(AEPB,request,response)) {
				System.out.println("Purchase Added successfully!");
				if(IndentDao.getInstance().setIsPurchased(AEPB.get(0).getProjectName(),request,response)) {
					System.out.println("Updated successfully!");
				}else {
					System.out.println("not Updated");
				}
				for(EMSPurchaseBean EPB:AEPB) {
					if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A new purchase order for project ".concat(EPB.getProjectName()).concat(" has been created!"),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","PURCHASE"))) {
						System.out.println("purchase insert Logs Inserted!");
					}else {
						System.out.println("purchase insert Logs not inserted!");
					}
				}
			}else {
				System.out.println("Purchase not added!");
			}
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
		    	// access a property of the JSON object
		   
		    	String json = "";
				if(jsonObject.get("Token").getAsString().equals("Vendors")) {
			    	json = gson.toJson(EMSPurchaseDao.getInstance().getVendorNameFromDatabase());
				    response.setContentType("application/json");
				    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(json);
				}else if(jsonObject.getAsJsonObject().get("Token").toString().replace("\"", "").equals("quotations")){
			    	System.out.println("quoat");
			    	json = gson.toJson(EMSPurchaseDao.getInstance().getTotalQuotation(jsonObject.get("project").getAsString()));
			    	System.out.println(json);
				    response.setContentType("application/json");
				    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(json);   
			    }
			    else {
			    	json=gson.toJson(EMSPurchaseDao.getInstance().getAllPurchaseOrderByUsingProjectId(jsonObject.get("projectId").getAsString()));
				    response.setContentType("application/json");
				    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(json);
			    }
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
	}
		
}
