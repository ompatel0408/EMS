package com.controller;
import java.io.IOException;

import com.bean.UserBean;
import com.dao.UserDao;
import com.service.ExceptionHandler;
import com.dao.AccessDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSUsersSevlet extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		try {

			String name = request.getParameter("userName");
			String email = request.getParameter("email");
			long phoneNum = Long.parseLong(request.getParameter("mobile"));
			String departnemt = request.getParameter("departmantName");
			int role = Integer.parseInt(request.getParameter("role"));
			
			UserBean ub = new UserBean(role, phoneNum, name, email, departnemt);
			System.out.println(ub.getUserName());
			System.out.println(ub.getEmail());
			System.out.println(ub.getPhNum());
			System.out.println(ub.getDepartmentName());
			System.out.println(ub.getRole());
			
			UserBean ubBean = new UserBean(role, phoneNum, name, email, departnemt);
			UserDao ud = new UserDao();
			AccessDao ad = new AccessDao();
			int success = ud.addUser(ubBean);
			System.out.println(success);
			int userId = ad.getUserIdFromDatabase(email);
			if(success==1) {
				ad.addAcess(userId);
			}
			request.getRequestDispatcher("EMSUser.jsp").forward(request, response);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
}