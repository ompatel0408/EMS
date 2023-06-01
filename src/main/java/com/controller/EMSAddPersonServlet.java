package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import com.bean.PersonBean;
import com.dao.EMSPurchaseDao;
import com.dao.PersonsDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.ExceptionHandler;

@SuppressWarnings("serial")
public class EMSAddPersonServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setAttribute("person", PersonsDao.getInstance().getAllPersonsFromDba());
			request.getRequestDispatcher("EMSPersonsList.jsp").forward(request, response);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			String name = request.getParameter("pname");
			String email = request.getParameter("emailid");
			String number = request.getParameter("number");
			String desg = request.getParameter("desg");
			PersonBean person = new PersonBean(name, number, email, desg);
			System.out.println(PersonsDao.getInstance().addPersonInDba(person,request,response));
			response.sendRedirect("EMSAddPersonServlet");
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {

			BufferedReader reader = req.getReader();
		    StringBuilder sb = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        sb.append(line);
		    }
		    String requestBody = sb.toString();
		    
		    Gson gson = new Gson();
		    JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
		    	// access a property of the JSON object
		   
		    	String json = "";
				if(jsonObject.get("Token").getAsString().equals("Persons")) {
					
			    	json = gson.toJson(PersonsDao.getInstance().getAllPersons());
				    resp.setContentType("application/json");
				    resp.setCharacterEncoding("UTF-8");
				    resp.getWriter().write(json);
				}
		}catch(Exception e) {
			ExceptionHandler.handleException(req, resp, e);
		}
		
	}
	
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		try {
			BufferedReader reader = request.getReader();
		    StringBuilder sb = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        sb.append(line);
		    }
		    String requestBody = sb.toString();
		    Gson gson = new Gson();
		    JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
		    boolean res = PersonsDao.getInstance().deletePersonById(jsonObject.get("data").getAsInt());
		    response.setContentType("application/json");
	    	response.setCharacterEncoding("UTF-8");
	    	response.getWriter().write(gson.toJson(res)); 	 
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
			
		       
	 	}
}