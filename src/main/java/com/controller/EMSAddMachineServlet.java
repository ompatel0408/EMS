package com.controller;

import java.io.IOException;
import com.bean.EMSAddMachineBean;
import com.dao.EMSAddMachineDao;
import com.service.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSAddMachineServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
		String MachineName = request.getParameter("mName");
		String modelNo = request.getParameter("modelNo");
		String invoice = request.getParameter("invoice");
		String pDate = request.getParameter("purchaseDate");
		String MachineCompany = request.getParameter("mcname");
		String mntDueDate = request.getParameter("mntDueDate");
		String remark = request.getParameter("remark");

		EMSAddMachineBean emb = new EMSAddMachineBean(MachineName,modelNo,invoice,pDate,MachineCompany,mntDueDate,remark);
		EMSAddMachineDao md =  EMSAddMachineDao.getInstance();
		System.out.println("insert is : " + md.insertItemInDba(emb,request,response));
		response.sendRedirect("EMSAddMachineListServlet");
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
}
