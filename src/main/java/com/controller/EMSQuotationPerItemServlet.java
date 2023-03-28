package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.bean.QuotationPerItemBean;
import com.dao.QuotationPerItemDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.QuotationPerItemServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EMSQuotationPerItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EMSQuotationPerItemServlet instance = null;
	QuotationPerItemDao QPd  = QuotationPerItemDao.getInstance();
	private static String category = "";
	private static HashMap<String,String> map = new HashMap<String,String>();
	private static ArrayList<String> arr = new ArrayList<String>();
	private static boolean isFound = false;
	
	
	public static EMSQuotationPerItemServlet getInstance() {
		
		if(instance == null) {
			instance = new EMSQuotationPerItemServlet();
		}
		return instance;
	}
	
	
    public EMSQuotationPerItemServlet() {
        // TODO Auto-generated constructor stub
    }

    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String[] catagory= QPd.getCatagoryFromDataBase();
		Gson gson = new Gson();
	    String json = gson.toJson(catagory);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

	
		ArrayList<QuotationPerItemBean> AQPIB = QuotationPerItemServices.fetchDataFromXHRRequestInQuotaionPerItem(request.getReader(),request);
		
		if(QPd.addQuotationPerItem(AQPIB)) {
			System.out.println("QuotataionPerItem Added SuccessFully");
		}else{
			System.out.println("QuotataionPerItem Added not SuccessFully");
		}
		
		for(QuotationPerItemBean QPIB:AQPIB) {
			if(!map.containsKey(QPIB.getItemId())) {
				map.put(QPIB.getItemId(),QPIB.getProfitPercentage());
			}
		}
		
		ArrayList<QuotationPerItemBean> ar = new ArrayList<QuotationPerItemBean>();
		
		for(QuotationPerItemBean QPIB:QPd.performSum()){
			if(!arr.contains(QPIB.getItemId())) {
				if(map.get(QPIB.getItemId()) != null) {
					String profitAmount  = String.valueOf((Double.parseDouble(QPIB.getTotalAmountWithoutProfit()) * (Double.parseDouble(map.get(QPIB.getItemId()))/100.0)) + (Double.parseDouble(QPIB.getTotalAmountWithoutProfit())));
					QuotationPerItemBean QPIB1 = new QuotationPerItemBean(QPIB.getItemId(),QPIB.getTotalAmountWithoutProfit() , profitAmount);
					ar.add(QPIB1);
				}
			}
		}
		
		for(QuotationPerItemBean QPIB:AQPIB) {
			if(!arr.contains(QPIB.getItemId())) {
				arr.add(QPIB.getItemId());
			}
		}
		
		
		if(QPd.addProfitForQuotationPerItem(ar)) {
			System.out.println("Profit added successfully!");
		}else {
			System.out.println("Profit not added");
		}
		
		for(QuotationPerItemBean QPIB :ar) {
			System.out.println("getTotalAmountWithProfit :"+QPIB.getTotalAmountWithProfit());
		}		
				
		if(QPd.updateTotalPrice(ar)) {
			System.out.println("totalPrice added successfully!");
		}else {

			System.out.println("totalPrice not added");
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
	    
	    if(jsonObject.get("token").getAsString().equals("category")) {
	    	category = jsonObject.get("category").getAsString();
	    	String json = gson.toJson(QPd.getGradeFromDatabase(jsonObject.get("category").getAsString()));
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
	    }else if(jsonObject.get("token").getAsString().equals("grade")){
	    	String json = gson.toJson(QPd.getSizeFromDatabase(category,jsonObject.get("grade").getAsString()));
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		    
	    }else if(jsonObject.get("token").getAsString().equals("Offers")) {
	    	ArrayList<String> a = QPd.getItemCodeFromDatabase();
	    	String[] offers = new String[a.size()];
	    	
	    	for(int i=0;i<a.size();i++) {
	    		offers[i] = a.get(i);
	    	}
	    	
	    	String json = gson.toJson(offers);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
	    }
		
	}
	

}
