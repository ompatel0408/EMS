package com.controller;

import java.io.BufferedReader;
import java.io.IOException;

import com.bean.EMSAddMachineInMntBean;
import com.dao.EMSAddMachineDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.ExceptionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EMSAddMachineInMntServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String machine = request.getParameter("machineId").trim();
		String compnyName = request.getParameter("comName").trim();
		String model = request.getParameter("modelId").trim();
		String dueDate = request.getParameter("dueDate").trim();
		String invoice = request.getParameter("invoice").trim();
		String mntGvnDate = request.getParameter("mntGvnDate").trim();
		String remark = request.getParameter("remark").trim();
		
		EMSAddMachineInMntBean bean = new EMSAddMachineInMntBean(machine, model, compnyName, dueDate, invoice, mntGvnDate, remark);
		System.out.println("data  : " + EMSAddMachineDao.getInstance().addItemInMnt(bean,request,response));
		response.sendRedirect("EMSAddMachineListServlet");
	}

	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String requestBody = sb.toString();

			EMSAddMachineDao amd = EMSAddMachineDao.getInstance();

			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);

			String jsonType = jsonObject.getAsJsonObject().get("token").toString();

			System.out.println(jsonType);

			if (jsonType.equals("\"machines\"")) {

				String json = gson.toJson(amd.getMachineNamefromDba());
				System.out.println(json);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} else if (jsonType.equals("\"modelNo\"")) {
				String ctgry = jsonObject.getAsJsonObject().get("data").toString().replace("\"", "");
				String json = gson.toJson(amd.getModelNumberFromDba(ctgry));
				System.out.println(json);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
				
			} else if (jsonType.equals("\"machinesCome\"")) {

				String json = gson.toJson(amd.getMachineNameMntfromDba());
				System.out.println(json);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}else if (jsonType.equals("\"modelNoCome\"")) {
				String ctgry = jsonObject.getAsJsonObject().get("data").toString().replace("\"", "");
				String json = gson.toJson(amd.getModelNumberMntFromDba(ctgry));
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
