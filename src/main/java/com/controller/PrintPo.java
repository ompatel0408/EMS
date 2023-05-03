package com.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

import com.dao.EMSPurchaseDao;
import com.dao.EMSVendorsDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.ExceptionHandler;

public class PrintPo extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("vendor"));
		System.out.println(request.getParameter("project"));
		System.out.println(request.getParameter("transport"));
		System.out.println(EMSVendorsDao.getInstance().updatePrice(request.getParameter("vendor"),request.getParameter("project"),request.getParameter("transport")));
		request.setAttribute("vendorDet", EMSVendorsDao.getInstance().getVendor(request.getParameter("vendor")));
		request.setAttribute("poDet", EMSVendorsDao.getInstance().getPoDet(request.getParameter("project")));
		request.setAttribute("povendorDet", EMSVendorsDao.getInstance().getPOVendorDet(request.getParameter("vendor"),request.getParameter("project")));
		request.getRequestDispatcher("PoPrint.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("vendor"));
		System.out.println(request.getParameter("project"));
		System.out.println(request.getParameter("transport"));
		EMSVendorsDao.getInstance().updatePrice(request.getParameter("vendor"),request.getParameter("project"),request.getParameter("transport"));
		doGet(request, response);
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
				}
				else if(jsonObject.getAsJsonObject().get("Token").toString().replace("\"", "").equals("transportPrice"))
				{
					System.out.println("totalPrice");
			    	json = gson.toJson(EMSPurchaseDao.getInstance().getTotalPrice(jsonObject.get("project").getAsString(),jsonObject.get("vendor").getAsString()));
			    	System.out.println(json);
				    response.setContentType("application/json");
				    response.setCharacterEncoding("UTF-8");
				    response.getWriter().write(json);   
				}
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
	}

}