package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import com.bean.EMSAddMachineBean;
import com.dao.EMSAddMachineDao;
import com.service.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSAddMachineListServlet extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
		EMSAddMachineDao eamd = EMSAddMachineDao.getInstance();
		ArrayList<EMSAddMachineBean> alab = eamd.getItems();
		request.setAttribute("data", alab);
		request.getRequestDispatcher("EMSAllMachineList.jsp").forward(request, response);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
}