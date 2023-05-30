package com.controller;


import java.io.IOException;
import java.util.ArrayList;

import com.bean.EMSPurchaseBean;
import com.dao.EMSPurchaseDao;
import com.service.ExceptionHandler;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSPurchaseListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String delete = req.getParameter("delete");
			if(delete.equals("yes")) {
				doDelete(req, resp);
			}
			ArrayList<EMSPurchaseBean> pos = new ArrayList<EMSPurchaseBean>();
			EMSPurchaseDao edao = EMSPurchaseDao.getInstance();
			
			pos = edao.getAllPurchaseOrder();
			req.setAttribute("pos", pos);
			RequestDispatcher rd = req.getRequestDispatcher("EMSPurchaseOrder.jsp");
			rd.forward(req, resp);
		}catch(Exception e) {
			ExceptionHandler.handleException(req, resp, e);
		}
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {

			EMSPurchaseDao odao = EMSPurchaseDao.getInstance();
			int poid = Integer.parseInt(req.getParameter("poid"));
			odao.deleteParticularPO(poid,req,resp);
			ArrayList<EMSPurchaseBean> pos = new ArrayList<EMSPurchaseBean>();
			pos = odao.getAllPurchaseOrder();
			
			req.setAttribute("pos", pos);
		}catch(Exception e) {
			ExceptionHandler.handleException(req, resp, e);
		}
	}
}