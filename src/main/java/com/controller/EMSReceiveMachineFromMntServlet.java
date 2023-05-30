package com.controller;

import java.io.IOException;
import com.bean.EMSAddMachineInMntBean;
import com.dao.EMSAddMachineDao;
import com.service.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class EMSReceiveMachineFromMntServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
		String machine = request.getParameter("machineId").trim();
		Long price = Long.parseLong(request.getParameter("mntPrice").trim());
		String modelNo = request.getParameter("modelId").trim();
		String receiveDate = request.getParameter("rcvDate").trim();
		
		EMSAddMachineInMntBean bean = new EMSAddMachineInMntBean(machine, modelNo, receiveDate, price);
		System.out.println(EMSAddMachineDao.getInstance().updateReceiveFromMnt(bean,request,response));
		response.sendRedirect("EMSAddMachineListServlet");
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
}
