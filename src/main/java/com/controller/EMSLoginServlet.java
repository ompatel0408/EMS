package com.controller;

import java.io.IOException;

import com.dao.EMSLoginDao;
import com.google.gson.Gson;
import com.service.Authentication;
import com.service.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EMSLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EMSLoginServlet() {
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			
			HttpSession session = request.getSession();
			int userId = (Integer) session.getAttribute("userId");
			
			Gson gson = new Gson();
			
		    String json = gson.toJson(userId);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		try {
			if("get".equals((String)request.getAttribute("InvalidAuth"))) {
				response.sendRedirect("InvalidToken.jsp");
			}else {
				response.sendRedirect("EMSDirectorsDashboard.jsp");
			}
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}

}
