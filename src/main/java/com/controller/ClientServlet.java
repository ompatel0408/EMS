package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.bean.ClientBean;
import com.bean.EMSLogsBean;
import com.dao.ClientDao;
import com.dao.EMSLogsDao;
import com.service.ExceptionHandler;

public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ClientServlet instance = null;
	private static String clientName = "";
	private static HashMap<Integer, String> map = new HashMap<Integer,String>();
	
	public static ClientServlet getInstance()
	{
		if(instance == null) {
			instance = new ClientServlet();
		}
		return instance;
	}
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		for(ClientBean Cb:ClientDao.getInstance().getClientList()) {
			map.put(Cb.getClientId(), Cb.getClientName());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			
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
			
			request.setAttribute("clients", clients);
			
			RequestDispatcher rd = request.getRequestDispatcher("AddClient.jsp");
			rd.forward(request, response);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			

			int clientId=Integer.parseInt(req.getParameter("clientId"));
			
			
			ClientDao cd = ClientDao.getInstance();
			
			HttpSession session = req.getSession();
			
			if(cd.deleteClient(clientId)) {
				
				if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A client record of ".concat(map.get(clientId)).concat(" has been deleted successfully!"),Integer.parseInt(session.getAttribute("userId").toString()),"DELETED","CLIENTS"))) {
				
					System.out.println("clients delete Logs Inserted!");
				}else{
					System.out.println("client delete Logs not inserted!");
				}
				
			}
		}catch(Exception e) {
			ExceptionHandler.handleException(req, resp, e);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			
			clientName = request.getParameter("clientName");
			String gst = request.getParameter("gst");
			String phone = request.getParameter("phone");
			String  email = request.getParameter("email");
			String pan = request.getParameter("pan");
			String address = request.getParameter("address");
			String email1 = request.getParameter("email1");
			String phone1 = request.getParameter("phone1");
			
			ClientBean clientBean = new ClientBean();
			clientBean.setAddress(address);
			clientBean.setClientName(clientName);
			clientBean.setEmail(email);
			clientBean.setGstNo(gst);
			clientBean.setPhoneNumber(Long.parseLong(phone));
			clientBean.setPanNo(pan);
			clientBean.setPhoneNumber1(Long.parseLong(phone1));
			clientBean.setEmail1(email1);
			ClientDao clientDao = ClientDao.getInstance();
			HttpSession session = request.getSession();
			if(clientDao.addClient(clientBean)) {
				if(session.getAttribute("userId") != null) {
					if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A new client ".concat(clientName).concat(" has been added!"),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","CLIENTS"))) {
						init();
						System.out.println(" Client insert Logs Inserted!");
					}else {
						System.out.println("Client insert Logs not inserted!");
					}
				}
			}
			response.sendRedirect("ClientServlet?clientId=0&update=notupdate");
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String editedColumn=request.getParameter("editedColumn");
			String newValue=request.getParameter("changes");
			int clientId=Integer.parseInt(request.getParameter("clientId"));
			
			ClientDao cd = ClientDao.getInstance();
			HttpSession session = request.getSession();
			if(cd.updateClient(clientId,editedColumn,newValue)) {
		
				if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A client information of ".concat(map.get(clientId)).concat( " has been updated successfully!"),Integer.parseInt(session.getAttribute("userId").toString()),"UPDATED","CLIENTS"))) {
					System.out.println("clients update Logs Inserted!");
				}else {
					System.out.println("client update Logs not inserted!");
				}
			}
			
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
	}
		
}