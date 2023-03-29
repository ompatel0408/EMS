package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.bean.ClientBean;
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
	private static HashMap<String, Integer> map = new HashMap<>();
	
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
	    
		Gson gson = new Gson();
	    
	    ItemDao Id = ItemDao.getInstance();
	    
	    ArrayList<String> arr = new ArrayList<String>();
	    for(ClientBean c:Id.getClients()) {
	    	arr.add(c.getClientName());
	    	if(!map.containsKey(c.getClientName())) {
	    		map.put(c.getClientName(), c.getClientId());
	    	}
	    }
	    String json = gson.toJson(arr);
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
		String clientName = jsonObject.get("clientId").getAsString();
		if(clientName != null) {
			if(EQD.insertFinalQuotation(new EMSFinalQuotationBean(jsonObject.get("TotalAmount").getAsString(), jsonObject.get("finalDelivaryDate").getAsString(), Integer.parseInt(jsonObject.get("Quantity").getAsString()), jsonObject.get("discountPercentage").getAsString(), jsonObject.get("discountAmount").getAsString(),map.get(clientName),jsonObject.get("remark").getAsString()))) {
				System.out.println("Inserted successfully!");
			}else {
				System.out.println("Not inserted!");
			}
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
		String clientName = jsonObject.get("ClientId").getAsString();
		String json = "";
		if(clientName != null) {
			json = gson.toJson(EFQD.getSumOfAllItemCodeOfAProject(map.get(clientName)));
		}
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
		
	}
	

}
