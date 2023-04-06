package com.controller;

import java.io.IOException;

import com.bean.EMSDirectorsDashboardBean;
import com.dao.EMSDirectorsDashboardDao;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class EMSDirectorsDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EMSDirectorsDashboardServlet instance = null;
	
	private Gson gson = new Gson();
	public static EMSDirectorsDashboardServlet getInstacne() {
		
		if(instance == null) {
			instance = new EMSDirectorsDashboardServlet();
		}
		return instance;
	}
    
	
    public EMSDirectorsDashboardServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GET");
		EMSDirectorsDashboardDao EDDD = EMSDirectorsDashboardDao.getInstacne();
		String json = gson.toJson(EDDD.getDataOfLiveProjects());
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) {
			String json = gson.toJson(session.getAttribute("userId").toString());
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
	}
	

}
