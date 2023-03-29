package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import com.bean.EMSOffersBean;
import com.bean.ItemBean;
import com.bean.PrePurchaseBean;
import com.dao.EMSOffersDao;
import com.dao.ItemDao;
import com.dao.PrePurchaseDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.EMSOffersServices;

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
		EMSOffersBean offerCode = EMSOffersDao.getInstance().getOfferId();
		HttpSession session = request.getSession();
		if(offerCode != null) {
			session.setAttribute("OfferCode", offerCode.getOfferCode());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			EMSOffersServlet EOS = new EMSOffersServlet();
			EOS.doGet(request,response);
	    	ArrayList<EMSOffersBean> ibean = new ArrayList<EMSOffersBean>(); 
			ibean = EMSOffersServices.fetchDataFromXHRRequest(request.getReader(), request);
			
			for(EMSOffersBean EOB:ibean) {
				PrePurchaseDao.getInstance().addPrePurchaseInOffers(new PrePurchaseBean(EOB.getDrawingId(),EOB.getClientId()));
			}
			if(EMSOffersDao.getInstance().addOffer(ibean)) {
				System.out.println("Offers Inserted Successfully!");
			}else {
				System.out.println("Offers  Inserted not Successfully!");
			}
	    }
		
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	    String json = "";
		ArrayList<EMSOffersBean> arr1 = EMSOffersDao.getInstance().getAllData(jsonObject.get("ClientName").getAsString());
		
    	if(arr1 != null) {
    		 json = gson.toJson(arr1);
    	}
    	System.out.println(json);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}
	

}