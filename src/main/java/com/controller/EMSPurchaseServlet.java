package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bean.EMSPurchaseBean;
import com.dao.EMSPurchaseDao;
import com.dao.ItemDao;
import com.dao.QuotationPerItemDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.PurchaseServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EMSPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EMSPurchaseServlet instance = null;
	private static String category = "";
	
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
		
		

		ArrayList<ArrayList<String>> ar = new ArrayList<ArrayList<String>>();
		EMSPurchaseDao EPD = EMSPurchaseDao.getInstance();
		ItemDao Id = ItemDao.getInstance();
		
		ar.add(Id.getProjects());
		ar.add(EPD.getCatagoryFromDataBase());
		
		Gson gson = new Gson();
	    String json = gson.toJson(ar);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Reached At Post!");
		
		ArrayList<EMSPurchaseBean> AEPB = PurchaseServices.fetchDataFromXHRRequest(request.getReader(), request);
		
		
		if(EMSPurchaseDao.getInstance().addPurchase(AEPB)) {
			System.out.println("Purchase Added successfully!");
		}else {
			System.out.println("Purchase not added!");
		}
		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	    QuotationPerItemDao QPd = QuotationPerItemDao.getInstance();
	    
	    if(jsonObject.get("token").getAsString().equals("category")) {
	    	category = jsonObject.get("category").getAsString();
	    	String json = gson.toJson(QPd.getGradeFromDatabase(jsonObject.get("category").getAsString()));
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
	    }
	    else if(jsonObject.get("token").getAsString().equals("grade")){
	    	String json = gson.toJson(QPd.getSizeFromDatabase(category,jsonObject.get("grade").getAsString()));
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);   
	    }else if(jsonObject.get("token").getAsString().equals("quotations")){
	    	System.out.println("quoat");
	    	String json = gson.toJson(EMSPurchaseDao.getInstance().getTotalQuotation(jsonObject.get("project").getAsString()));
	    	System.out.println(json);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);   
	    }else {
	    	String json = gson.toJson(EMSPurchaseDao.getInstance().getVendorNameFromDatabase());
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
			    
	    }
	}
		
}
