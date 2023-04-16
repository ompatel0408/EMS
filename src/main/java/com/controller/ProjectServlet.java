package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.bean.EMSLogsBean;
import com.bean.ProjectBean;
import com.dao.EMSLogsDao;
import com.dao.ProjectDao;
import com.service.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ProjectServlet extends HttpServlet {
	private static  long serialVersionUID = 1L;
	private static ProjectServlet instance = null;
	
	public static ProjectServlet getInstance() {
		if(instance == null) {
			instance = new ProjectServlet();
		}
		return instance;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			String projectId = request.getParameter("projectId");
			String update = request.getParameter("update");
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
		}catch(Exception e) {
			ExceptionHandler.handleException(request,response, e);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String projectId = request.getParameter("projectId");
		
		try {

			String projectId="";
			String clientName="";
			String clientPoId = request.getParameter("clientPoId");
			
			Integer advancePayPercent = Integer.parseInt(request.getParameter("advancePayPercent"));
			Integer afterPayPercent = Integer.parseInt(request.getParameter("afterPayPercent"));
			String finalDeliveryDate = request.getParameter("finalDeliveryDate");
			Integer clientId = Integer.parseInt(request.getParameter("clientId"));
			ProjectBean projectbean= new ProjectBean();
			
			projectbean.setClientPoId(clientPoId);
			projectbean.setPoDate(LocalDate.now().toString());
			projectbean.setAdvancePayPercent(advancePayPercent);
			projectbean.setAfterPayPercent(afterPayPercent);
			projectbean.setFinalDeliveryDate(finalDeliveryDate);
			projectbean.setClientId(clientId);
			clientName = ProjectDao.getInstance().getClientName(clientId);
			ProjectServices ps = new ProjectServices();
			projectId = ps.projectGenerate(clientName);
			projectbean.setProjectId(projectId);
			
			HttpSession session = request.getSession();
			if(ProjectDao.getInstance().addProject(projectbean)) {
				System.out.println("Project Added successfully!");

				if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A new project for client ".concat(clientName).concat(" has been added!"),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","PROJECTS"))) {
					System.out.println("projects insert Logs Inserted!");
				}else {
					System.out.println("projects insert Logs not inserted!");
				}
			}else {
				System.out.println("Project not added!");
			}
			ProjectDao.getInstance().updatePrPurchase(clientId,projectId);
			response.sendRedirect("ProjectServlet?projectId=0&update=notupdate");
		}catch(Exception e) {
			ExceptionHandler.handleException(request,response, e);
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			String projectId = request.getParameter("projectId");
			String changeField = request.getParameter("changeField");
			String newData = request.getParameter("newData");
			ProjectDao projectDao = ProjectDao.getInstance();
			projectDao.updateProject(newData, changeField, projectId);
		}catch(Exception e) {
			ExceptionHandler.handleException(request,response, e);
		}
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			String projectId = req.getParameter("projectId");
			ProjectDao projectDao = ProjectDao.getInstance();
			projectDao.deleteProject(projectId);
		}catch(Exception e) {
			ExceptionHandler.handleException(req,resp, e);
		}
	}

}