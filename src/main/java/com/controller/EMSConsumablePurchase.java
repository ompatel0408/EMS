package com.controller;

import java.io.IOException;

import com.bean.EMSConsumablePurchaseBean;
import com.dao.EMSConsumablePurchaseDao;
import com.service.EMSConsumablePurchaseService;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/EMSConsumablePurchase")
public class EMSConsumablePurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public EMSConsumablePurchase() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<EMSConsumablePurchaseBean> AECPB = EMSConsumablePurchaseService.fetchDataFromXHRRequest(request.getReader(), request);
		
		if(EMSConsumablePurchaseDao.getInstance().addPurchase(AECPB, request, response)) {
			
			System.out.println("Purchase added successfully!!!!");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("Hii");
			
		}else {
			System.out.println("Purchase not added successfully!!!!");
		}
	}

}
