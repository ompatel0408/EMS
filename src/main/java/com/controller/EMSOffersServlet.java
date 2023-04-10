package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.bean.ClientBean;
import com.bean.EMSLogsBean;
import com.bean.EMSOffersBean;
import com.bean.ItemBean;
import com.bean.PrePurchaseBean;
import com.dao.ClientDao;
import com.dao.EMSLogsDao;
import com.dao.EMSOffersDao;
import com.dao.ItemDao;
import com.dao.PrePurchaseDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.EMSOffersServices;
import com.service.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class EMSOffersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EMSOffersServlet() {
        // TODO Auto-generated constructor stub
    }
    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			EMSOffersBean offerCode = EMSOffersDao.getInstance().getOfferId();
			HttpSession session = request.getSession();
			if(offerCode != null) {
				session.setAttribute("OfferCode", offerCode.getOfferCode());
			}
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {

			EMSOffersServlet EOS = new EMSOffersServlet();
			EOS.doGet(request,response);
	    	ArrayList<EMSOffersBean> ibean = new ArrayList<EMSOffersBean>(); 
			ibean = EMSOffersServices.fetchDataFromXHRRequest(request.getReader(), request);
			
			for(EMSOffersBean EOB:ibean) {
				PrePurchaseDao.getInstance().addPrePurchaseInOffers(new PrePurchaseBean(EOB.getDrawingId(),EOB.getClientId()));
			}
			HttpSession session = request.getSession();
			if(EMSOffersDao.getInstance().addOffer(ibean)) {
				System.out.println("Offers Inserted Successfully!");
				for(EMSOffersBean EOB:ibean) {
					if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A new offer has been added in Client Name ".concat(EOB.getClientName()),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","OFFERS"))) {
						System.out.println(" OFFERS insert Logs Inserted!");
					}else {
						System.out.println("OFFERS  insert Logs not inserted!");
					}
				}
			}else {
				System.out.println("Offers  Inserted not Successfully!");
			}
			Gson gson = new  Gson();
			String json = gson.toJson("Hello");
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	 }
		
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
		    String json = "";
			ArrayList<EMSOffersBean> arr1 = EMSOffersDao.getInstance().getAllData(jsonObject.get("ClientName").getAsString());
			
	    	if(arr1 != null) {
	    		 json = gson.toJson(arr1);
	    	}
	    	System.out.println(json);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
	

}