package com.controller;


import java.io.IOException;
import java.util.ArrayList;

import com.bean.CatagoryGradeSizeBean;
import com.bean.ClientBean;
import com.dao.CatagoryGradeSizeDao;
import com.dao.ClientDao;
import com.google.gson.Gson;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CatagoryGradeSizeServlet")
public class CatagoryGradeSizeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CatagoryGradeSizeBean> catagories = new ArrayList<CatagoryGradeSizeBean>();
		CatagoryGradeSizeDao catagory = CatagoryGradeSizeDao.getInstance();
		catagories = catagory.getCatagoryList();
		request.setAttribute("data", catagories);
		RequestDispatcher rd = request.getRequestDispatcher("AddCatagoryGradeSize.jsp");
		rd.forward(request, response);
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String catagoryName = request.getParameter("catagoryName");
		String SelectedCatName = request.getParameter("selectField");
		String GradeName = request.getParameter("GradeName");
		String selectFieldForGrade=request.getParameter("selectFieldForGrade");
		String GradeId = request.getParameter("GradeId");
		String size = request.getParameter("size");
		String GradeFieldName=request.getParameter("GradeFieldName");
		System.out.println("Catogoryname->"+catagoryName);
		System.out.println("SelectCatName->"+SelectedCatName);
		System.out.println("GradeName->"+GradeName);
		System.out.println("selectFiedforGrade->"+selectFieldForGrade);
		System.out.println("GradeFieldName->"+GradeFieldName);
		System.out.println("size->"+size);
		
		
		if(catagoryName!=null && catagoryName!="")
		{
			System.out.println("Reached For catgory");
			System.out.println(catagoryName);
			CatagoryGradeSizeBean cBean = new CatagoryGradeSizeBean();
			cBean.setCatagoryName(catagoryName);
			CatagoryGradeSizeDao catagory=CatagoryGradeSizeDao.getInstance();
			catagory.addCatagory(cBean);
		}
		else {
			System.out.println("Enter Catagory Name");
		}
//		System.out.println(SelectedCatName!=null || SelectedCatName!="");
		if(SelectedCatName!=null && SelectedCatName!="") {
			System.out.println("Reached For Grade");
			if(GradeName!="")
			{
					System.out.println(SelectedCatName);
					System.out.println(GradeName);
					CatagoryGradeSizeDao grade=CatagoryGradeSizeDao.getInstance();
					grade.addGrade(SelectedCatName,GradeName);
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
				System.out.println("Reached For size");
				System.out.println(selectFieldForGrade);
				System.out.println(GradeFieldName);
				System.out.println(size);
				CatagoryGradeSizeDao grade=CatagoryGradeSizeDao.getInstance();
				grade.addSize(selectFieldForGrade,GradeFieldName,size);
			}
			else {
				System.out.println("Please Select Grade and Give Size");
			}
		}
		response.sendRedirect("CatagoryGradeSizeServlet");
	}
}