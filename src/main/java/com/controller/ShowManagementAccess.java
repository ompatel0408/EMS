package com.controller;

import java.io.IOException;

import com.bean.AccessBean;
import com.dao.AccessDao;
import com.google.gson.*;
import com.service.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ShowManagementAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {

			int change=Integer.parseInt(request.getParameter("change"));
			int userId=Integer.parseInt(request.getParameter("userId"));
			if(change==1) {
				System.out.println("in if");
				doPut(request,response);
			}else if(change==0) {
				AccessDao accessDao = new AccessDao();   
				AccessBean access = accessDao.getAllAccess(userId);
				request.setAttribute("access", access);
				request.getRequestDispatcher("AccessManagementList.jsp").forward(request, response);
			}
			else {
				AccessDao accessDao = new AccessDao();
				AccessBean access = accessDao.getAllAccess(userId);
				Gson gson = new Gson();
				String json = gson.toJson(access);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
					
			}
		}catch(Exception e) {
			ExceptionHandler.handleException(request,response, e);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			int userId=Integer.parseInt(req.getParameter("userId"));
			int status=Integer.parseInt(req.getParameter("status"));
			String field=req.getParameter("field");
			AccessDao access = new AccessDao();
			
			access.changeStatus(userId,status,field);
			AccessBean accessBean = access.getAllAccess(userId);
			req.setAttribute("access", accessBean);
			req.getRequestDispatcher("AccessManagementList.jsp").forward(req, resp);
		}catch(Exception e) {
			ExceptionHandler.handleException(req,resp, e);
		}
		
	}

}