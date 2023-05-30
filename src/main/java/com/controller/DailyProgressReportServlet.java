package com.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import com.bean.DailyProgressReportBean;
import com.dao.DailyProgressReportDao;
import com.dao.EMSDrawingDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.DailyProgressReportServices;
import com.service.ExceptionHandler;

public class DailyProgressReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Hello");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("For DPR");
			if(request.getParameter("delete")==null)
			{
				ArrayList<DailyProgressReportBean> ibean = new ArrayList<DailyProgressReportBean>();
				ibean = DailyProgressReportServices.fetchDataFromXHRRequest(request.getReader(), request);

				if (DailyProgressReportDao.getInstance().addDPR(ibean,request,response)) {
					System.out.println("DPR Added!");
				} else {
					System.out.println("DPR not Added!");
				}
//				request.getRequestDispatcher("DPRList.jsp").forward(request, response);
				doGet(request, response);
			}
			else if (request.getParameter("delete").equals("delete")) {
				String itemId = request.getParameter("itemId");
				String subItemId = request.getParameter("subItemId");
				String editedColumn = request.getParameter("phase");
				String projectId = request.getParameter("projectId");
				DailyProgressReportDao.getInstance().updatePhase(projectId, subItemId, editedColumn,itemId,request,response);
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
