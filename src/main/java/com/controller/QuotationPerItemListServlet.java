package com.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.ArrayList;

import com.bean.EMSOffersBean;
import com.bean.QuotationPerItemBean;
import com.dao.EMSOffersDao;
import com.dao.QuotationPerItemDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QuotationPerItemListServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In add quotation per item servlet");
		int offer = Integer.parseInt(request.getParameter("offer"));
		if(offer == 1) {
			ArrayList<EMSOffersBean> offers = new ArrayList<EMSOffersBean>();
			EMSOffersDao odao = EMSOffersDao.getInstance();
			offers=odao.getAllOffer();
			request.setAttribute("offers", offers);
			RequestDispatcher rd = request.getRequestDispatcher("OfferList.jsp");
			rd.forward(request, response);
		}
		else if(offer == 0) {
			String offerCode = request.getParameter("offerCode");
			ArrayList<QuotationPerItemBean> lists = new ArrayList<QuotationPerItemBean>();
			QuotationPerItemDao idao = QuotationPerItemDao.getInstance();
			lists = idao.getList(offerCode);
			request.setAttribute("offerCode", offerCode);
			request.setAttribute("lists", lists);
			RequestDispatcher rd = request.getRequestDispatcher("QuotationPerItemList.jsp");
			rd.forward(request, response);
		}
//		else if(offer == 2) {
//			System.out.println("delete aaya");
//			String offerCode = request.getParameter("offerCode");
//			int cid = Integer.parseInt(request.getParameter("cid"));
//			int gid = Integer.parseInt(request.getParameter("gid"));
//			int sid = Integer.parseInt(request.getParameter("sid"));
//			
//			QuotationPerItemDao qdao = QuotationPerItemDao.getInstance();
//			qdao.deleteFromQuotationPerItem(cid, gid, sid);
//			ArrayList<QuotationPerItemBean> lists = new ArrayList<QuotationPerItemBean>();
//			lists=qdao.getList(offerCode);
//			request.setAttribute("lists", lists);
//			RequestDispatcher rd = request.getRequestDispatcher("QuotationPerItemList.jsp");
//			rd.forward(request, response);
//		}
	}
}