package com.controller;

import java.io.BufferedReader;
import java.io.IOException;


import com.service.ItemServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.bean.ItemBean;
import com.bean.PrePurchaseBean;
import com.dao.ItemDao;
import com.dao.PrePurchaseDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;


public class EMSItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EMSItemServlet instance = null;
	public static EMSItemServlet getInstance() {
		
		if(instance == null) {
			instance = new EMSItemServlet();
		}
		return instance;
	}

    public EMSItemServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Set ItemCode to Session		
		ItemBean itemCode = ItemDao.getItemId();
		HttpSession session = request.getSession();
		if(itemCode != null) {
			session.setAttribute("ItemCode", itemCode.getItemCode());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EMSItemServlet ESI = new EMSItemServlet();
		ESI.doGet(request,response);
		System.out.println("Reached AT Do post");
		ArrayList<ItemBean> AQb = ItemServices.fetchDataFromXHRRequest(request.getReader(),request);
		
		for(ItemBean ib:AQb) {
			PrePurchaseDao.addPrePurchase(new PrePurchaseBean(ib.getProjectId(),ib.getDrawingId()));
		}
		if(ItemDao.addItems(AQb)) {
			
			System.out.println("Item Added SuccessFully");
		}else{
			System.out.println("Item Added not SuccessFully");
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
