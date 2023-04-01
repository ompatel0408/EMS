package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bean.ProjectBean;
import com.dao.ProjectDao;
import com.service.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ProjectServlet extends HttpServlet {
	private static  long serialVersionUID = 1L;
	private static ProjectServlet instance = null;
	
	public static ProjectServlet getInstance() {
		if(instance == null) {
			instance = new ProjectServlet();
		}
		return instance;
	}
	
	Date d = new Date();
	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	String today = df.format(d);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectId = request.getParameter("projectId");
		String update = request.getParameter("update");
		String view = request.getParameter("view");
		System.out.println("ProjectId:"+projectId);
		System.out.println("Update:"+update);
		ProjectDao projectDao = ProjectDao.getInstance();
		if(update.equals("update")) {
			doPut(request, response);
		}
		else if(!(projectId.equals("0"))){
			doDelete(request, response);
		}
		System.out.println("List");
		
		
		
		
		ArrayList<ProjectBean> projects = projectDao.getAllProject();
		request.setAttribute("projects", projects);
		request.getRequestDispatcher("ListProject.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String projectId = request.getParameter("projectId");
		String projectId="";
		String clientName="";
		String clientPoId = request.getParameter("clientPoId");
		System.out.println("clIENT po id"+clientPoId);
		Integer advancePayPercent = Integer.parseInt(request.getParameter("advancePayPercent"));
		Integer afterPayPercent = Integer.parseInt(request.getParameter("afterPayPercent"));
		String finalDeliveryDate = request.getParameter("finalDeliveryDate");
		Integer clientId = Integer.parseInt(request.getParameter("clientId"));
		ProjectBean projectbean= new ProjectBean();
		
		projectbean.setClientPoId(clientPoId);
		projectbean.setPoDate(today);
		projectbean.setAdvancePayPercent(advancePayPercent);
		projectbean.setAfterPayPercent(afterPayPercent);
		projectbean.setFinalDeliveryDate(finalDeliveryDate);
		projectbean.setClientId(clientId);
		clientName = ProjectDao.getInstance().getClientName(clientId);
		ProjectServices ps = new ProjectServices();
		projectId = ps.projectGenerate(clientName);
		projectbean.setProjectId(projectId);
		ProjectDao.getInstance().addProject(projectbean);
		ProjectDao.getInstance().updatePrPurchase(clientId,projectId);
		response.sendRedirect("ProjectServlet?projectId=0&update=notupdate");
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String projectId = request.getParameter("projectId");
		System.out.println("update:"+projectId);
		String changeField = request.getParameter("changeField");
		String newData = request.getParameter("newData");
		ProjectDao projectDao = ProjectDao.getInstance();
		projectDao.updateProject(newData, changeField, projectId);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String projectId = req.getParameter("projectId");
		System.out.println("DeleteProjectId:"+projectId);
		ProjectDao projectDao = ProjectDao.getInstance();
		projectDao.deleteProject(projectId);
	}

}