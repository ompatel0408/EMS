package com.controller;
 
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bean.CatagoryGradeSizeBean;
import com.bean.IndentBean;
import com.dao.CatagoryGradeSizeDao;
import com.dao.IndentDao;
import com.dao.ItemDao;
import com.dao.ProjectDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.IndentServices;

public class IndentServlet extends HttpServlet {  
	public IndentServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("AAGye");
		ProjectDao pd = ProjectDao.getInstance();
		ArrayList<String> projectIds = new ArrayList<String>();
		projectIds = pd.getOnlyProjectId();
		request.setAttribute("projectIds", projectIds);
		ArrayList<CatagoryGradeSizeBean> cgl = new ArrayList<CatagoryGradeSizeBean>();
		CatagoryGradeSizeDao cddao = CatagoryGradeSizeDao.getInstance();
		cgl = cddao.getCatagoryList();
		request.setAttribute("categoryIds", cgl);
		RequestDispatcher rd = request.getRequestDispatcher("Indent.jsp");
		rd.forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("reached!");
		ArrayList<IndentBean> ibean = new ArrayList<IndentBean>(); 
		ibean = IndentServices.fetchDataFromXHRRequest(request.getReader(), request);
		System.out.println(ibean);
		for (IndentBean indentBean : ibean) {
			System.out.println(indentBean.getCategoryId());
		}
		IndentDao indentDao = IndentDao.getInstance();
		indentDao.addIndent(ibean);
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("put of indent");
		BufferedReader reader = request.getReader();
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    String requestBody = sb.toString();
	    
	    Gson gson = new Gson();
	    JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
	    if(jsonObject.get("token").getAsString().equals("category")) {
	    	String category = jsonObject.get("category").getAsString();
		    System.out.println(category);
		    System.out.println("cat");
		    ArrayList<CatagoryGradeSizeBean> cgl = new ArrayList<CatagoryGradeSizeBean>();
		    CatagoryGradeSizeDao cddao = CatagoryGradeSizeDao.getInstance();
			cgl = cddao.getGradeList(category);
			String json = gson.toJson(cgl);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
	    }
	    else if(jsonObject.get("token").getAsString().equals("grade")) {
	    	String grade = jsonObject.get("grade").getAsString();
		    System.out.println(grade);
		    System.out.println("gra");
		    ArrayList<CatagoryGradeSizeBean> cgl = new ArrayList<CatagoryGradeSizeBean>();
		    CatagoryGradeSizeDao cddao = CatagoryGradeSizeDao.getInstance();
			cgl = cddao.getSizeList(grade);
			String json = gson.toJson(cgl);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
	    }
	    else if(jsonObject.get("token").getAsString().equals("quantity")) {
	    	String category = jsonObject.get("category").getAsString();
	    	String grade = jsonObject.get("grade").getAsString();
	    	String size = jsonObject.get("size").getAsString();
		    System.out.println("Quantity");
		    CatagoryGradeSizeDao cddao = CatagoryGradeSizeDao.getInstance();
			int cgl = cddao.getQuantity(category,grade,size);
			System.out.println(cgl);
			String json = gson.toJson(cgl);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
	    }
	    else if(jsonObject.get("token").getAsString().equals("indent")) {
	    	System.out.println("in indent item name");
	    	
	    	String projectId = jsonObject.get("category").getAsString();
	    	ArrayList<String> itemNames = new ArrayList<String>();
	    	ItemDao id = ItemDao.getInstance();
	    	itemNames = id.getItemNames(projectId);
	    	
	    	System.out.println("----->"+projectId);

	    	System.out.println("----->"+itemNames.size());
	    	
	    	for(String x:itemNames) {
	    		System.out.println(x);
	    	}
	    	
	    	String json = gson.toJson(itemNames);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
	    }
	}
}

