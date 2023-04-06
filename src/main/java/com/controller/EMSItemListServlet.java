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

/*
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
		}
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
		for(ItemBean o : items) {
			System.out.println(o.getOfferCode());
		}
		request.setAttribute("items", items);
		request.getRequestDispatcher("EMSOrderList.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
	}
	
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
*/

import java.io.BufferedReader;
import java.io.IOException;


import com.service.ItemServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.bean.ItemBean;
import com.bean.ClientBean;
import com.bean.EMSOffersBean;
import com.dao.ItemDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.dao.ClientDao;
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
		Integer clientId=Integer.parseInt(request.getParameter("clientId"));
		String itemCode = request.getParameter("itemCode");
		String update = request.getParameter("update");
		ItemDao itemDao = new ItemDao();
		String ClientName="";
		if(clientId>0)
		{
			ArrayList<ItemBean> items=new ArrayList<ItemBean>();
			items=ItemDao.getInstance().getAllItemsOfClient(clientId);
			String clientName = ClientDao.getInstance().getClientNameFormDatabase(clientId);
			request.setAttribute("items", items);
			request.setAttribute("clientName", clientName);
			request.getRequestDispatcher("EMSOrderList.jsp").forward(request, response);
		}
	}	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		BufferedReader reader = request.getReader();
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    String requestBody = sb.toString();
	    
	    ItemDao Id = ItemDao.getInstance();
	    Gson gson = new Gson();
	    JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
	    
	    if(jsonObject.get("token").getAsString().equals("orderSum")) {
	    	
			int items=ItemDao.getInstance().getSumOfItems();
			System.out.println(items);
	    	String json = gson.toJson(items);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
	    }
	    else {
	    	String itemCode = request.getParameter("itemCode");
			String changeField = request.getParameter("changeField");
			String newData = request.getParameter("newData");
			ItemDao itemDao = new ItemDao();
			itemDao.updateItem(newData, changeField, itemCode);
	    }
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String itemCode = req.getParameter("itemCode");
		ItemDao itemDao = new ItemDao();
		itemDao.deleteItem(itemCode);
	}

}

