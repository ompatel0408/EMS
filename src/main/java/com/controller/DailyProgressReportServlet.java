package com.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.bean.DailyProgressReportBean;
import com.bean.EMSLogsBean;
import com.bean.IndentBean;
import com.dao.DailyProgressReportDao;
import com.dao.EMSDrawingDao;
import com.dao.EMSLogsDao;
import com.dao.IndentDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.DailyProgressReportServices;
import com.service.ExceptionHandler;
import com.service.IndentServices;

public class DailyProgressReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DailyProgressReportDao dprDao = null;
		ArrayList<DailyProgressReportBean> dprs = dprDao.getInstance().getAllDpr();
		request.setAttribute("dprs", dprs);

		RequestDispatcher rd = request.getRequestDispatcher("DPRList.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("For DPR");
			if(request.getParameter("delete")==null)
			{
				ArrayList<DailyProgressReportBean> ibean = new ArrayList<DailyProgressReportBean>();
				ibean = DailyProgressReportServices.fetchDataFromXHRRequest(request.getReader(), request);

				HttpSession session = request.getSession();
				DailyProgressReportDao dprDao = DailyProgressReportDao.getInstance();
				if (dprDao.addDPR(ibean)) {
					System.out.println("Indent Added!");
				} else {
					System.out.println("Indent not Added!");
				}
				doGet(request, response);
			}
			else if (request.getParameter("delete").equals("delete")) {
				DailyProgressReportDao dprDao = null;
				String itemId = request.getParameter("itemId");
				String subItemId = request.getParameter("subItemId");
				String editedColumn = request.getParameter("phase");
				String projectId = request.getParameter("projectId");
				dprDao.getInstance().updatePhase(projectId, subItemId, editedColumn,itemId);
				response.sendRedirect("EMSDirectorsDashboard.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionHandler.handleException(request, response, e);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			if (jsonObject.get("token").getAsString().equals("phase")) {
				String json = gson.toJson(DailyProgressReportDao.getInstance()
						.getPhase(jsonObject.get("projectId").getAsString(), jsonObject.get("itemcode").getAsString()));
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} else if (jsonObject.get("token").getAsString().equals("subItem")) {
				System.out.println("456");
				String json = gson.toJson(
						EMSDrawingDao.getInstance().getsubItemFromDatabase(jsonObject.get("itemcode").getAsString()));
				System.out.println(json);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);

			}
		} catch (Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
	
}
