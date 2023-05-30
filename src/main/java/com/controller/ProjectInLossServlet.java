package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.bean.ProjectBean;
import com.dao.ProjectDao;
import com.service.ExceptionHandler;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ProjectInLossServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		System.out.println("In Do get of Project in Loss Servlet");
		ArrayList<ProjectBean> projects = new ArrayList<ProjectBean>();
		ProjectDao pdao = ProjectDao.getInstance();
		projects = pdao.getProjectInLoss();
		request.setAttribute("projects", projects);
		RequestDispatcher rd = request.getRequestDispatcher("ListProjectInLoss.jsp");
		rd.forward(request, response);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
}
