package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.bean.EMSStoreBean;
import com.dao.EMSStoreDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EMSStoreListServlet
 */

public class EMSStoreListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EMSStoreDao esd = new EMSStoreDao();
		ArrayList<EMSStoreBean> store =  esd.getAllData();
		ArrayList<EMSStoreBean> allStore =  esd.getAllStoreList();
		request.setAttribute("store", store);
		request.setAttribute("allstore", allStore);
		request.getRequestDispatcher("EMSStoreList.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}