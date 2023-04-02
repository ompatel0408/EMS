package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.bean.ClientBean;
import com.bean.EMSLogsBean;
import com.dao.ClientDao;
import com.dao.EMSLogsDao;

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
		HttpSession session = req.getSession();
		if(cd.deleteClient(clientId)) {
			if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A client record has been deleted successfully!",Integer.parseInt(session.getAttribute("userId").toString()),"UPDATED","CLIENTS"))) {
				System.out.println("clients delete Logs Inserted!");
			}else {
				System.out.println("client delete Logs not inserted!");
			}
		}
		
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
		HttpSession session = request.getSession();
		if(clientDao.addClient(clientBean)) {
			if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A new client has been added!",Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","CLIENTS"))) {
				System.out.println(" Client insert Logs Inserted!");
			}else {
				System.out.println("Client  insert Logs not inserted!");
			}
		}
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
		HttpSession session = request.getSession();
		if(cd.updateClient(clientId,editedColumn,newValue)) {
			if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A client information has been updated successfully!",Integer.parseInt(session.getAttribute("userId").toString()),"UPDATED","CLIENTS"))) {
				System.out.println("clients update Logs Inserted!");
			}else {
				System.out.println("client update Logs not inserted!");
			}
		}
		
//		response.sendRedirect("ClientServlet?clientId=0?update=notupdate");
	}
}