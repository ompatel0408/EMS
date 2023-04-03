package com.controller;
import java.io.IOException;

import com.bean.UserBean;
import com.dao.UserDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSUsersSevlet extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
		int success = ud.addUser(ubBean);
		System.out.println(success);
		request.getRequestDispatcher("AddUser.jsp").forward(request, response);
	}
}