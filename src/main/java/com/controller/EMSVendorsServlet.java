package com.controller;


import java.io.IOException;
import java.util.ArrayList;

import com.bean.EMSLogsBean;
import com.bean.EMSVendorsBean;
import com.dao.EMSLogsDao;
import com.dao.EMSVendorsDao;
import com.service.EMSVendorServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		
		ArrayList<EMSVendorsBean> ibean = new ArrayList<EMSVendorsBean>(); 
		ibean = EMSVendorServices.fetchDataFromXHRRequest(request.getReader(), request);
		
		HttpSession session = request.getSession();
		EMSVendorsDao vendorDao = EMSVendorsDao.getInstance();
		if(vendorDao.addVendor(ibean)) {
			System.out.println("vendor added successfully!");
			for(EMSVendorsBean EVB:ibean) {
				if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A new vendor ".concat(EVB.getVendorName()).concat(" has been added!"),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","VENDOR"))) {
					init();
					System.out.println(" VENDOR insert Logs Inserted!");
				}else {
					System.out.println("VENDOR insert Logs not inserted!");
				}
			}
			
		}else {
			System.out.println("vendor not added successfully!");
		}
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
		HttpSession session = request.getSession();
		
		EMSVendorsDao vendorDao = EMSVendorsDao.getInstance();
		if(vendorDao.updateVendor(newData, changeField, vendorId)){
			System.out.println("Vendor update");
			if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A vendor information of ".concat(request.getParameter("vendorName")).concat( " has been updated successfully!"),Integer.parseInt(session.getAttribute("userId").toString()),"UPDATED","VENDOR"))) {
				System.out.println("vendor update Logs Inserted!");
			}else {
				System.out.println("vendor update Logs not inserted!");
			}
			
		}else {
			System.out.println("Vendor not update");
		}
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int vendorId = Integer.parseInt(req.getParameter("vendorId"));
		EMSVendorsDao vendorDao = EMSVendorsDao.getInstance();
		HttpSession session = req.getSession();
		if(EMSVendorsDao.getInstance().deleteVendor(vendorId)) {
			System.out.println("Vendor deleted!");
			if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A client record of ".concat(req.getParameter("vendorName")).concat(" has been deleted successfully!"),Integer.parseInt(session.getAttribute("userId").toString()),"DELETED","CLIENTS"))) {
				
				System.out.println("vendor delete Logs Inserted!");
			}else{
				System.out.println("vendor delete Logs not inserted!");
			}
			
		}else {
			System.out.println("Vendor not deleted!");
		}
	}

}
