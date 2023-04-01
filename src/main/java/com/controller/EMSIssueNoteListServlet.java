package com.controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.bean.ClientBean;
import com.bean.EMSIssueNoteBean;
import com.dao.ClientDao;
import com.dao.EMSIssueNoteDao;

/**
 * Servlet implementation class EMSIssueNoteList
 */
public class EMSIssueNoteListServlet extends HttpServlet {
private static EMSIssueNoteListServlet instance = null;
	
	public static EMSIssueNoteListServlet getInstance()
	{
		if(instance == null) {
			instance = new EMSIssueNoteListServlet();
		}
		return instance;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<EMSIssueNoteBean> clients = new ArrayList<EMSIssueNoteBean>();
		EMSIssueNoteDao  clientDao = EMSIssueNoteDao.getInstance();
		clients = clientDao.getAllList();
		for (EMSIssueNoteBean clientBean : clients) {
			System.out.println(clientBean.getIssueId());
		}
		request.setAttribute("issue", clients);
		
		RequestDispatcher rd = request.getRequestDispatcher("EMSIssueList.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}