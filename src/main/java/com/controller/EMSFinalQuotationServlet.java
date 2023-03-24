package com.controller;

import java.io.BufferedReader;
import java.io.IOException;

import com.bean.EMSFinalQuotationBean;
import com.dao.EMSFinalQuotationDao;
import com.dao.ItemDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EMSFinalQuotationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EMSFinalQuotationServlet instance = null;
	
	
	public static EMSFinalQuotationServlet getInstacne() {
		
		if(instance == null) {
			instance = new EMSFinalQuotationServlet();
		}
		return instance;
	}
 
    public EMSFinalQuotationServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	    ItemDao Id = ItemDao.getInstance();
	    Gson gson = new Gson();
		
	    String json = gson.toJson(Id.getProjects());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		EMSFinalQuotationDao EQD = EMSFinalQuotationDao.getInstance();
		if(EQD.insertFinalQuotation(new EMSFinalQuotationBean(jsonObject.get("TotalAmount").getAsString(), jsonObject.get("finalDelivaryDate").getAsString(), Integer.parseInt(jsonObject.get("Quantity").getAsString()), jsonObject.get("discountPercentage").getAsString(), jsonObject.get("discountAmount").getAsString(),jsonObject.get("projectId").getAsString(),jsonObject.get("remark").getAsString()))) {
			System.out.println("Inserted successfully!");
		}else {
			System.out.println("Not inserted!");
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
		
		
		EMSFinalQuotationDao EFQD = EMSFinalQuotationDao.getInstance();
		String json = gson.toJson(EFQD.getSumOfAllItemCodeOfAProject(jsonObject.get("projectId").getAsString()));
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
		
	}
	

}
