package com.controller;
import java.io.IOException;


import com.service.ItemServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.bean.ItemBean;
import com.bean.EMSOffersBean;
import com.dao.ItemDao;
import com.dao.EMSOffersDao;

import java.util.ArrayList;


public class EMSItemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EMSItemServlet instance = null;
	
	public static EMSItemServlet getInstance() {
		
		if(instance == null) {
			instance = new EMSItemServlet();
		}
		return instance;
	}

    public EMSItemListServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Set ItemCode to Session		
		/*ItemBean itemCode = ItemDao.getItemId();
		HttpSession session = request.getSession();
		if(itemCode != null) {
			session.setAttribute("ItemCode", itemCode.getItemCode());
		}*/
		String itemCode = request.getParameter("itemCode");
		String update = request.getParameter("update");
		ItemDao itemDao = new ItemDao();
		if(update.equals("update")) {
			System.out.println("do put");
			doPut(request, response);
			
		}
		else if(!(itemCode.equals("0"))){
			System.out.println("do delete");
			doDelete(request, response);
		}
		System.out.println("List");
		
		
		
		ArrayList<ItemBean> items = itemDao.getAllItems();
		/*for(ItemBean o : items) {
			System.out.println(o.getOfferCode());
		}*/
		request.setAttribute("items", items);
		request.getRequestDispatcher("EMSOrderList.jsp").forward(request, response);
		
		
	}

	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EMSItemServlet ESI = new EMSItemServlet();
		ESI.doGet(request,response);
		System.out.println("Reached AT Do post");
		ArrayList<ItemBean> AQb = ItemServices.fetchDataFromXHRRequest(request.getReader(),request);
		
		for(ItemBean ib:AQb) {
			PrePurchaseDao.addPrePurchase(new PrePurchaseBean(ib.getProjectId(),ib.getDrawingId()));
		}
		if(ItemDao.addItems(AQb)) {
			
			System.out.println("Item Added SuccessFully");
		}else{
			System.out.println("Item Added not SuccessFully");
		}
		
	}*/
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String itemCode = request.getParameter("itemCode");
		String changeField = request.getParameter("changeField");
		String newData = request.getParameter("newData");
		ItemDao itemDao = new ItemDao();
		itemDao.updateItem(newData, changeField, itemCode);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String itemCode = req.getParameter("itemCode");
		ItemDao itemDao = new ItemDao();
		itemDao.deleteItem(itemCode);
	}

}