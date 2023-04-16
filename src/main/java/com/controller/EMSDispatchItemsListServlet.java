package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import com.bean.EMSDispatchBean;
import com.dao.EMSDispatchDao;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/EMSDispatchItemsListServlet")
public class EMSDispatchItemsListServlet extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		EMSDispatchDao edd = EMSDispatchDao.getInstance();
		ArrayList<EMSDispatchBean> aledb = edd.getProjectFromDba();
		request.setAttribute("data", aledb);
		request.getRequestDispatcher("EMSDispatchItemsList.jsp").forward(request, response);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {

		    Gson gson = new Gson();
		    // access a property of the JSON object
		    EMSDispatchDao edd = EMSDispatchDao.getInstance();
		    System.err.println("in doput");
		   	String json = gson.toJson(edd.getCountOfProject());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}