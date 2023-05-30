package com.controller;


import java.io.IOException;
import com.service.ExceptionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.bean.ItemBean;
import com.dao.ItemDao;
import java.util.ArrayList;
import java.io.BufferedReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.dao.ClientDao;


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
		
		try {
			Integer clientId=Integer.parseInt(request.getParameter("clientId"));
			if(clientId>0)
			{
				ArrayList<ItemBean> items=new ArrayList<ItemBean>();
				items=ItemDao.getInstance().getAllItemsOfClient(clientId);
				String clientName = ClientDao.getInstance().getClientNameFormDatabase(clientId);
				request.setAttribute("items", items);
				request.setAttribute("clientName", clientName);
				request.getRequestDispatcher("EMSOrderList.jsp").forward(request, response);
			}
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
	}	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
				itemDao.updateItem(newData, changeField, itemCode,request,response);
		    }
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			String itemCode = req.getParameter("itemCode");
			ItemDao itemDao = new ItemDao();
			itemDao.deleteItem(itemCode,req,resp);
		}catch(Exception e) {
			ExceptionHandler.handleException(req, resp, e);
		}
		
	}

}

