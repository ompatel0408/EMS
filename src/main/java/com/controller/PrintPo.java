package com.controller;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.dao.EMSVendorsDao;

public class PrintPo extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("vendor"));
		System.out.println(request.getParameter("project"));
		request.setAttribute("vendorDet", EMSVendorsDao.getInstance().getVendor(request.getParameter("vendor")));
		request.setAttribute("poDet", EMSVendorsDao.getInstance().getPoDet(request.getParameter("project")));
		request.setAttribute("povendorDet", EMSVendorsDao.getInstance().getPOVendorDet(request.getParameter("vendor"),request.getParameter("project")));
		request.getRequestDispatcher("PoPrint.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}