package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.bean.EMSPerMachineMntBean;
import com.dao.EMSAddMachineDao;
import com.service.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSPerMachineMntServlet extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
		String mId = request.getParameter("mcId");
		System.out.println(mId);
		ArrayList<EMSPerMachineMntBean> arlt = EMSAddMachineDao.getInstance().getMachineMntData(Integer.parseInt(mId));
		request.setAttribute("data", arlt);
		request.getRequestDispatcher("EMSPerMachineMnt.jsp").forward(request, response);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
}
