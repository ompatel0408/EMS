package com.controller;


import java.io.IOException;
import java.util.ArrayList;

import com.bean.EMSVendorsBean;
import com.dao.EMSVendorsDao;
import com.service.EMSVendorServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSVendorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EMSVendorsServlet instance = null;
	
	public static EMSVendorsServlet getInstance() {
		if(instance == null) {
			instance = new EMSVendorsServlet();
		}
		return instance;
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Do Post Of Vendor Servlet");
		System.out.println("reached!");
		ArrayList<EMSVendorsBean> ibean = new ArrayList<EMSVendorsBean>(); 
		ibean = EMSVendorServices.fetchDataFromXHRRequest(request.getReader(), request);
		System.out.println(ibean);
		EMSVendorsDao vendorDao = EMSVendorsDao.getInstance();
		vendorDao.addVendor(ibean);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int vendorId = Integer.parseInt(request.getParameter("vendorId"));
		String update = request.getParameter("update");
		EMSVendorsDao vendorDao = EMSVendorsDao.getInstance();
		if(update.equals("update")) {
			System.out.println("do put");
			doPut(request, response);
			
		}
		else if(!(vendorId==0)){
			System.out.println("do delete");
			doDelete(request, response);
		}
		System.out.println("List");
		
		
		
		ArrayList<EMSVendorsBean> vendors = vendorDao.getAllVendor();	
		request.setAttribute("vendors", vendors);
		request.getRequestDispatcher("EMSVendorsList.jsp").forward(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int vendorId = Integer.parseInt(request.getParameter("vendorId"));
		String changeField = request.getParameter("changeField");
		String newData = request.getParameter("newData");
		EMSVendorsDao vendorDao = EMSVendorsDao.getInstance();
		vendorDao.updateVendor(newData, changeField, vendorId);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int vendorId = Integer.parseInt(req.getParameter("vendorId"));
		EMSVendorsDao vendorDao = EMSVendorsDao.getInstance();
		EMSVendorsDao.getInstance().deleteVendor(vendorId);
	}

}
