package com.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import com.bean.EMSDispatchBean;
import com.dao.EMSDispatchDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("/EMSDispatchItemServlet")
public class EMSDispatchItemServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String clientId = req.getParameter("clientId");
		String offerId = req.getParameter("offerId");
		String vehicleNo = req.getParameter("vehicleNo");
		String travelComOwnr = req.getParameter("travelComOwnr");
		String travelCom = req.getParameter("travelCom");
		String dest = req.getParameter("destAddress");
		String checkBy = req.getParameter("checkBy");

		EMSDispatchBean edb = new EMSDispatchBean(clientId, offerId, vehicleNo, travelComOwnr, travelCom, checkBy,
				dest);
		EMSDispatchDao edd = EMSDispatchDao.getInstance();
		System.out.println(edd.insertDispatchItems(edb));
		req.getRequestDispatcher("EMSDirectorsDashboard.jsp").forward(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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

		    // access a property of the JSON object
		    EMSDispatchDao edd = EMSDispatchDao.getInstance();

		   	String json = gson.toJson(edd.getOfferFromDba(jsonObject.get("token").getAsString()));
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}