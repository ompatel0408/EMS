package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.dao.EMSProductionDao;
import com.service.ExceptionHandler;

/**
 * Servlet implementation class ProductionPhaseServlet
 */
public class ProductionPhaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductionPhaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String projectId = request.getParameter("projectId");
		System.out.println(projectId);
		String itemCode = request.getParameter("ItemCode");
		System.out.println(itemCode);
		Integer phaseCount = Integer.parseInt(request.getParameter("phaseCount"));
		ArrayList<String> phaseNames=new ArrayList<String>();
		
		for(int i=1;i<=phaseCount;i++)
		{
			String phaseName = request.getParameter("phase"+i);
			System.out.println(phaseName);
			phaseNames.add(phaseName);
		}
		EMSProductionDao.getInstance().addPhase(projectId,itemCode,phaseNames,request,response);
		response.sendRedirect("EMSDirectorsDashboard.jsp");
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}

}

