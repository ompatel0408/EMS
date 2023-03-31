package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.bean.EMSIssueNoteBean;
import com.dao.EMSIssueNoteDao;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EMSIssueNoteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String issueId = request.getParameter("issueId");
		String update = request.getParameter("update");
		EMSIssueNoteDao EMSIssueNoteDao = new EMSIssueNoteDao();
		if(update.equals("update")) {
			System.out.println("do put");
			doPut(request, response);
		}
		else if(!(issueId.equals("0"))){
			System.out.println("do delete");
			doDelete(request, response);
		}
		System.out.println("List");
		
		
		
		ArrayList<EMSIssueNoteBean> issue = EMSIssueNoteDao.getAllIssues();
		System.out.println(issue.size());
		request.setAttribute("issue", issue);
		request.getRequestDispatcher("EMSIssueList.jsp").forward(request, response);
		
	}

	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int issueId = Integer.parseInt(request.getParameter("issueId"));
		String changeField = request.getParameter("changeField");
		String newData = request.getParameter("newData");
		EMSIssueNoteDao EMSIssueNoteDao = new EMSIssueNoteDao();
		EMSIssueNoteDao.updateIssue(newData, changeField, issueId);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int issueId = Integer.parseInt(req.getParameter("issueId"));
		EMSIssueNoteDao EMSIssueNoteDao = new EMSIssueNoteDao();
		EMSIssueNoteDao.deleteIssue(issueId);
	}

}