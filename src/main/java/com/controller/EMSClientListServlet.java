package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import com.bean.ClientBean;
import com.dao.ClientDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSClientListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ClientDao cd = ClientDao.getInstance();
		ArrayList<ClientBean> listClients = new ArrayList<ClientBean>();
		listClients = cd.getClientList();
		req.setAttribute("clients", listClients);
		RequestDispatcher rd = req.getRequestDispatcher("EMSOffers.jsp");
		rd.forward(req, resp);
	}
}