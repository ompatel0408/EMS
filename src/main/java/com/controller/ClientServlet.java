package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.bean.ClientBean;
import com.dao.ClientDao;

public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ClientServlet instance = null;
	
	public static ClientServlet getInstance()
	{
		if(instance == null) {
			instance = new ClientServlet();
		}
		return instance;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int clientId=Integer.parseInt(request.getParameter("clientId"));
		String update = request.getParameter("update");
		
		if(update.equals("update")) {
			doPut(request, response);
		}
		else if(clientId != 0){
			doDelete(request, response);
		}
		// Get client List		
		ArrayList<ClientBean> clients = new ArrayList<ClientBean>();
		ClientDao  clientDao = ClientDao.getInstance();
		clients = clientDao.getClientList();
		for (ClientBean clientBean : clients) {
			System.out.println(clientBean.getClientName());
		}
		request.setAttribute("clients", clients);
		
		RequestDispatcher rd = request.getRequestDispatcher("AddClient.jsp");
		rd.forward(request, response);
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doDelete method");
		int clientId=Integer.parseInt(req.getParameter("clientId"));
		System.out.println(clientId);
		ClientDao cd = ClientDao.getInstance();
		cd.deleteClient(clientId);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String clientName = request.getParameter("clientName");
		String gst = request.getParameter("gst");
		String phone = request.getParameter("phone");
		String  email = request.getParameter("email");
		String pan = request.getParameter("pan");
		String address = request.getParameter("address");
		System.out.println("Client Servlet Hit");
		
		ClientBean clientBean = new ClientBean();
		clientBean.setAddress(address);
		clientBean.setClientName(clientName);
		clientBean.setEmail(email);
		clientBean.setGstNo(gst);
		clientBean.setPhoneNumber(Long.parseLong(phone));
		clientBean.setPanNo(pan);
		
		ClientDao clientDao = ClientDao.getInstance();
		clientDao.addClient(clientBean);
		response.sendRedirect("ClientServlet?clientId=0&update=notupdate");
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("PUT reached!");
		String editedColumn=request.getParameter("editedColumn");
		String newValue=request.getParameter("changes");
		int clientId=Integer.parseInt(request.getParameter("clientId"));
		System.out.println(newValue);
		System.out.println(clientId);
		System.out.println(editedColumn);
		ClientDao cd = ClientDao.getInstance();
		cd.updateClient(clientId,editedColumn,newValue);
//		response.sendRedirect("ClientServlet?clientId=0?update=notupdate");
	}
}