package com.controller;

import java.io.IOException;

import com.bean.EMSGetpassOutwordBean;
import com.dao.EMSGetpassOutwordDao;
import com.service.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class EMSGetpassOutwordServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
		String vendorName = request.getParameter("vendorId");
		String matName = request.getParameter("mname");
		int qty = Integer.parseInt(request.getParameter("qty"));
		String issueDate = request.getParameter("idate");
		String size = request.getParameter("size");
		String vehicleNo = request.getParameter("vehicleNo");
		String address = request.getParameter("address");
		String remark = request.getParameter("remark");
		int receive = Integer.parseInt(request.getParameter("receive"));
		EMSGetpassOutwordBean gob = null;
		if(size.isEmpty()) {
			gob = new EMSGetpassOutwordBean(vendorName, matName, qty, issueDate, null, vehicleNo, address, remark, receive);
		} else {
			 gob = new EMSGetpassOutwordBean(vendorName, matName, qty, issueDate, size, vehicleNo, address, remark, receive);
		}
		
		EMSGetpassOutwordDao gpd = EMSGetpassOutwordDao.getInstance();
		int output = gpd.insertGetpassOut(gob,request,response);
		if(output == -101)
		{
			request.setAttribute("error", "Please, Enter unique value.....");
			request.getRequestDispatcher("EMSGatepassOutword.jsp").forward(request, response);
		}
		else
		{
			System.out.println(output);
			response.sendRedirect("EMSGetpassListServlet");			
		}
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
}
