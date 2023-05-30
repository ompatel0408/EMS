package com.controller;


import java.io.IOException;
import java.util.ArrayList;

import com.bean.CatagoryGradeSizeBean;
import com.bean.ClientBean;
import com.bean.EMSLogsBean;
import com.dao.CatagoryGradeSizeDao;
import com.dao.ClientDao;
import com.dao.EMSLogsDao;
import com.google.gson.Gson;
import com.service.ExceptionHandler;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CatagoryGradeSizeServlet")
public class CatagoryGradeSizeServlet extends HttpServlet {
	
	private static String catagoryName  = "";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ArrayList<CatagoryGradeSizeBean> catagories = new ArrayList<CatagoryGradeSizeBean>();
			CatagoryGradeSizeDao catagory = CatagoryGradeSizeDao.getInstance();
			catagories = catagory.getCatagoryList();
			request.setAttribute("data", catagories);
			RequestDispatcher rd = request.getRequestDispatcher("AddCatagoryGradeSize.jsp");
			rd.forward(request, response);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
			
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			catagoryName = request.getParameter("catagoryName");
			String SelectedCatName = request.getParameter("selectField");
			String GradeName = request.getParameter("GradeName");
			String selectFieldForGrade=request.getParameter("selectFieldForGrade");
			String GradeId = request.getParameter("GradeId");
			String size = request.getParameter("size");
			String GradeFieldName=request.getParameter("GradeFieldName");
			
			HttpSession session = request.getSession();
			if(catagoryName!=null && catagoryName!="")
			{
				
				CatagoryGradeSizeBean cBean = new CatagoryGradeSizeBean();
				cBean.setCatagoryName(catagoryName);
				CatagoryGradeSizeDao catagory=CatagoryGradeSizeDao.getInstance();
				if(catagory.addCatagory(cBean,request,response)) {
					System.out.println("Category Added successfully!");
					if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A new category ".concat(catagoryName).concat(" has been added!"),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","CATEGORY"))) {
						init();
						System.out.println("CATEGORY insert Logs Inserted!");
					}else {
						System.out.println("CATEGORY insert Logs not inserted!");
					}
				}else {
					System.out.println("Category not Added successfully!");
				}
			}
			else {
				System.out.println("Enter Catagory Name");
			}

			if(SelectedCatName!=null && SelectedCatName!="") {
				
				if(GradeName!="")
				{		
						CatagoryGradeSizeDao grade=CatagoryGradeSizeDao.getInstance();
						if(grade.addGrade(SelectedCatName,GradeName,request,response)) {
							if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A new garde ".concat(GradeName).concat(" of category ").concat(SelectedCatName).concat(" has been added!"),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","GRADE"))) {
								init();
								System.out.println("GRADE insert Logs Inserted!");
							}else {
								System.out.println("GRADE insert Logs not inserted!");
							}
						}else {
							System.out.println("grade not added!");
						}
				}
				else {
					System.out.println("Please Enter Grade");
				}
				
			}
			else {
				System.out.println("Please Enter Category");
			}
			if((selectFieldForGrade!="null"))
			{
				if(((selectFieldForGrade!="null" && GradeFieldName!="null") && (selectFieldForGrade!="null") && (GradeFieldName!="null")))
				{				
					CatagoryGradeSizeDao grade=CatagoryGradeSizeDao.getInstance();
					if(grade.addSize(selectFieldForGrade,GradeFieldName,size,request,response)) {
						System.out.println("size  added");
						if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A new size ".concat(size).concat(" of grade ").concat(GradeFieldName).concat(" and category ").concat(selectFieldForGrade).concat(" has been added!"),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","SIZE"))) {
							init();
							System.out.println("SIZE insert Logs Inserted!");
						}else {
							System.out.println("SIZE insert Logs not inserted!");
						}
					}else {
						System.out.println("size not added");
					}
				}
				else {
					System.out.println("Please Select Grade and Give Size");
				}
			}
			
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
		response.sendRedirect("CatagoryGradeSizeServlet");
	}
}