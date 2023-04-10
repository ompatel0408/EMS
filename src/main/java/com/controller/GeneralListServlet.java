package com.controller;
import java.io.IOException;
import java.util.ArrayList;

import com.bean.ClientBean;
import com.bean.ItemBean;
import com.bean.ProjectBean;
import com.dao.ClientDao;
import com.dao.ItemDao;
import com.dao.ProjectDao;
import com.service.ExceptionHandler;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class GeneralListServlet extends HttpServlet {
	private static GeneralListServlet instance = null;
	public static GeneralListServlet getInstance()
	{
		if(instance == null) {
			instance = new GeneralListServlet();
		} 
		return instance;
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
	
			String clientId = req.getParameter("clientId");
			
			if(clientId.equals("null")) {
				ArrayList<ClientBean> clients = new ArrayList<ClientBean>();
				ClientDao  clientDao = ClientDao.getInstance();
				clients = clientDao.getClientList();
				for (ClientBean clientBean : clients) {
					System.out.println(clientBean.getClientName());
				}
				req.setAttribute("clients", clients);
				RequestDispatcher rd = req.getRequestDispatcher("GeneralInfo.jsp");
				rd.forward(req, resp);
			}
			else {
				ClientDao  clientDao = ClientDao.getInstance();
				ProjectDao projectDao = ProjectDao.getInstance();
				ItemDao idao  = ItemDao.getInstance();
				ArrayList<String> items = idao.getItemNamesForProject(clientId);
				String clientName = clientDao.getClientNameFormDatabase(Integer.parseInt(clientId));
				ArrayList<ProjectBean> projects = projectDao.getAllProjectUsingClientId(clientId);
				req.setAttribute("clientId", clientId);
				req.setAttribute("clientName", clientName);
				req.setAttribute("projects", projects);
				req.setAttribute("items", items);
				req.getRequestDispatcher("GeneralInfoOfProject.jsp").forward(req, resp);
			}
		}catch(Exception e) {
			ExceptionHandler.handleException(req, resp, e);
		}
		
	}
}