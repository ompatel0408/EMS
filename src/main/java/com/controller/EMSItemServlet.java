package com.controller;

import java.io.BufferedReader;
import java.io.IOException;

import com.service.ExceptionHandler;
import com.service.ItemServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bean.ClientBean;
import com.bean.EMSLogsBean;
import com.bean.ItemBean;
import com.bean.PrePurchaseBean;
import com.dao.EMSLogsDao;
import com.dao.ItemDao;
import com.dao.PrePurchaseDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;


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
		
		try {
			// Set ItemCode to Session		
			ItemBean itemCode = ItemDao.getItemId();
			HttpSession session = request.getSession();
			if(itemCode != null) {
				session.setAttribute("ItemCode", itemCode.getItemCode());
			}
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			EMSItemServlet ESI = new EMSItemServlet();
			ESI.doGet(request,response);
			HttpSession session = request.getSession();
			ArrayList<ItemBean> AQb = ItemServices.fetchDataFromXHRRequest(request.getReader(),request);
			
			if(ItemDao.addItems(AQb,request,response)) {
				System.out.println("Item Added SuccessFully");
				
				for(ItemBean IB : AQb) {
					if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("We have just received an order from ".concat(IB.getClientName()),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","ORDERS"))) {
						System.out.println(" Orders insert Logs Inserted!");
					}else {
						System.out.println("Orders  insert Logs not inserted!");
					}
				}
			}else{
				System.out.println("Item Added not SuccessFully");
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
		    
		    ItemDao Id = ItemDao.getInstance();
		    Gson gson = new Gson();
		    JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
		    
		    if(jsonObject.get("token").getAsString().equals("Projects")) {
		    	
		    	ArrayList<String> arr = new ArrayList<String>();
		    	for(ClientBean c:Id.getClients()) {
		    		arr.add(c.getClientName());
		    	}
		    	String json = gson.toJson(arr);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
		    }else if(jsonObject.get("token").getAsString().equals("QuotationId")) {
		    	System.out.println("Reached at quotationId");
		    	String json = gson.toJson(ItemDao.getInstance().getQuotationIdFromdatabase(jsonObject.get("ClientName").getAsString()));
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
		    }
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
	}
	

}
