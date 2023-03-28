package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.bean.IndentBean;
import com.bean.ItemBean;
import com.bean.ProjectBean;
import com.dao.IndentDao;
import com.dao.ItemDao;
import com.dao.ProjectDao;

/**
 * Servlet implementation class ListIndentServlet
 */
public class ListIndentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int token = Integer.parseInt(request.getParameter("token"));
		String projectId = request.getParameter("projectid");
		System.out.println(projectId);
		String itemname = request.getParameter("itemname");
		
		if(token == 1) {
			ProjectDao projectDao = new ProjectDao();
			ArrayList<ProjectBean> projects = projectDao.getAllProject();
			System.out.println("ProjectListSize:"+projects.size());
			
			
			request.setAttribute("projects", projects);
			request.getRequestDispatcher("ProjectListForIndent.jsp").forward(request, response);
			
		}
		else if(token ==  2) {
			ItemDao id = ItemDao.getInstance();
			ArrayList<ItemBean> items = new ArrayList<ItemBean>() ;
			items=id.getItemListForIndent(projectId);
			System.out.println(items);
			System.out.println(projectId);
			request.setAttribute("items", items);
			request.setAttribute("projectId", projectId);
			request.getRequestDispatcher("ItemListForIndent.jsp").forward(request, response);
		}	
		else if(token==3)
		{
			ArrayList<IndentBean> indentss = new ArrayList<IndentBean>();
			IndentDao id = IndentDao.getInstance();
			indentss = id.getParticularIndent(projectId,itemname);
			request.setAttribute("lists", indentss);
			request.setAttribute("projectId", projectId);
			request.getRequestDispatcher("ListParticularProjectIndentItem.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
