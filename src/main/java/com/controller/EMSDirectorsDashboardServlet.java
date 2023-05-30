package com.controller;

import java.io.BufferedReader;
import java.io.IOException;

import com.bean.EMSDirectorsDashboardBean;
import com.dao.EMSDirectorsDashboardDao;
import com.dao.EMSFinalQuotationDao;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.service.ExceptionHandler;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EMSDirectorsDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static EMSDirectorsDashboardServlet instance = null;

	private Gson gson = new Gson();

	public static EMSDirectorsDashboardServlet getInstacne() {

		if (instance == null) {
			instance = new EMSDirectorsDashboardServlet();
		}
		return instance;
	}

	public EMSDirectorsDashboardServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {

			EMSDirectorsDashboardDao EDDD = EMSDirectorsDashboardDao.getInstacne();
			String json = gson.toJson(EDDD.getDataOfLiveProjects());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			HttpSession session = request.getSession();
			if (session.getAttribute("userId") != null) {
				String json = gson.toJson(session.getAttribute("userId").toString());
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}
		} catch (Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			String json = "";
			if (jsonObject.get("Token").getAsString().equals("Hii")) {
				json = gson.toJson(EMSDirectorsDashboardDao.getInstacne().getTotalUsers());
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} else if (jsonObject.get("Token").getAsString().equals("Notify1")) {
				System.out.println("Hii From Notify1");
				json = gson.toJson(EMSDirectorsDashboardDao.getInstacne().getAllNotificationsForPayment());
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} else {
				System.out.println("Hii From Notify");
				json = gson.toJson(EMSDirectorsDashboardDao.getInstacne().getAllNotifications());
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}
		} catch (Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

			JsonElement Token = jsonObject.get("Token");
			System.out.println("Token  :" + Token);
			if ((Token != null) && (Token.toString().equals("Hello1"))) {
				System.out.println("Helllllllllloooooo!!!!!!");
				EMSDirectorsDashboardDao.getInstacne().updateisPaidForPayment(jsonObject.get("name").getAsString(),request,response);
				String json = gson.toJson("Hii");
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}
			if (Token.toString().equals("Hello")) {
				EMSDirectorsDashboardDao.getInstacne().updateisPaid(jsonObject.get("name").getAsString(),request,response);
				String json = gson.toJson("Hii1");
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}
		} catch (Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}

}
