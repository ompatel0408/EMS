package com.controller;
import java.io.IOException;
import java.util.ArrayList;

import com.bean.EMSFinalQuotationBean;
import com.dao.EMSFinalQuotationDao;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EMSFinalQuotationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String quotationId = request.getParameter("quotationId");
		String update = request.getParameter("update");
		EMSFinalQuotationDao EMSFinalQuotationDao = new EMSFinalQuotationDao();
		if(update.equals("update")) {
			System.out.println("do put");
			doPut(request, response);
			
		}
		else if(!(quotationId.equals("0"))){
			System.out.println("do delete");
			doDelete(request, response);
		}
		System.out.println("List");
		
		
		
		ArrayList<EMSFinalQuotationBean> quotations = EMSFinalQuotationDao.getAllFinalQuotations();
		request.setAttribute("quotations", quotations);
		request.getRequestDispatcher("EMSFinalQuotationList.jsp").forward(request, response);
		
	}

	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int quotationId = Integer.parseInt(request.getParameter("quotationId"));
		String changeField = request.getParameter("changeField");
		String newData = request.getParameter("newData");
		EMSFinalQuotationDao EMSFinalQuotationDao = new EMSFinalQuotationDao();
		EMSFinalQuotationDao.updateQuotation(newData, changeField, quotationId);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int quotationId = Integer.parseInt(req.getParameter("quotationId"));
		EMSFinalQuotationDao EMSFinalQuotationDao = new EMSFinalQuotationDao();
		EMSFinalQuotationDao.deleteQuotation(quotationId);
	}

}