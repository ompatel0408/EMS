package com.controller;

import java.io.BufferedReader;
import java.io.IOException;

import com.bean.EMSGetpassinwordBean;
import com.dao.EMSGetpassinwordDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSGetpassinwordServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String vendor = request.getParameter("vendorId");
		int qty = Integer.parseInt(request.getParameter("qty"));
		String vehicleNo = request.getParameter("vehicleNo");
		String itemId = request.getParameter("itemId");
		String idate = request.getParameter("idate");
		String remark = request.getParameter("remark");
		EMSGetpassinwordBean egib = new EMSGetpassinwordBean(vendor, itemId, qty, vehicleNo, idate, remark);
		EMSGetpassinwordDao egid = EMSGetpassinwordDao.getInstace();
		System.out.println(egid.isnertItems(egib));
		response.sendRedirect("EMSDirectorsDashboard.jsp");
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		BufferedReader reader = request.getReader();
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    String requestBody = sb.toString();
	    
	    Gson gson = new Gson();
		EMSGetpassinwordDao egp = EMSGetpassinwordDao.getInstace();	    
		
	    JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);

	    // access a property of the JSON object
	    
	    if(jsonObject.get("Token").getAsString().equals("Vendors")) {
	    	String json = gson.toJson(egp.getVendorsFromDba());
	    	response.setContentType("application/json");
	    	response.setCharacterEncoding("UTF-8");
	    	response.getWriter().write(json);
	    }
	    else if(jsonObject.get("Token").getAsString().equals("Items"))
	    {
	    	String json = gson.toJson(egp.getItemsVendorwiseFromDba(jsonObject.get("Vendor").getAsString()));
	    	response.setContentType("application/json");
	    	response.setCharacterEncoding("UTF-8");
	    	response.getWriter().write(json);
	    }
	}
}
