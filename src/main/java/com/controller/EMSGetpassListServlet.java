package com.controller;

import java.io.IOException;
import com.dao.EMSGetpassOutwordDao;
import com.dao.EMSGetpassinwordDao;
import com.service.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class EMSGetpassListServlet extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
		EMSGetpassOutwordDao gpd = EMSGetpassOutwordDao.getInstance();
		EMSGetpassinwordDao egd  = EMSGetpassinwordDao.getInstace();
		request.setAttribute("data1", egd.getItems());
		request.setAttribute("data", gpd.getItems());
		request.getRequestDispatcher("EMSGatepassList.jsp").forward(request, response);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
}
