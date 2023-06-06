package com.controller;




import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import com.bean.EMSGRNBean;
import com.bean.EMSLogsBean;
import com.dao.EMSGRNDao;
import com.dao.EMSLogsDao;
import com.dao.EMSPurchaseDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.service.ExceptionHandler;
import com.service.GRNServices;
import jakarta.servlet.annotation.MultipartConfig;


@MultipartConfig(
		 fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class EMSGRNServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String vendorName = "";
	
    public EMSGRNServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			
			Gson gson = new Gson();
			String json = gson.toJson(EMSPurchaseDao.getInstance().getVendorNameFromDatabase());
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		    
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			EMSGRNBean EGB =  GRNServices.uploadPic(request);
			vendorName = request.getParameter("VendorName");
			HttpSession session = request.getSession();
			if(EGB != null) {
				if(EMSGRNDao.getInstance().addGRN(new EMSGRNBean(vendorName, request.getParameter("ReceivedDate"),EGB.getPath1().trim(),EGB.getPath2().trim(),request.getParameter("InvoiceNumber")),request,response)) {
					System.out.println("GRN added successfully!");
					if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A new GRN from vendor ".concat(vendorName).concat(" has been reached At gate!"),Integer.parseInt(session.getAttribute("userId").toString()),"INSERTED","GRN"))) {
						System.out.println("purchase insert Logs Inserted!");
					}else {
						System.out.println("purchase insert Logs not inserted!");
					}
				}else {
					System.out.println("GRN added successfully!");
				}
				response.sendRedirect("EMSGRNList.jsp");
			}
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			
			HttpSession session = request.getSession();
			if(EMSGRNDao.getInstance().deleteGRN(Integer.parseInt(jsonObject.get("grnId").getAsString()),request,response)) {
			
				System.out.println("Deleted successfully!");
				

				if(EMSLogsDao.getInstance().insertLogs(new EMSLogsBean("A GRN record of ".concat(vendorName).concat(" has been deleted successfully!"),Integer.parseInt(session.getAttribute("userId").toString()),"DELETED","GRN"))) {
				
					System.out.println("GRN delete Logs Inserted!");
				}else{
					System.out.println("GRN delete Logs not inserted!");
				}
				
			}else {
				System.out.println("not Deleted successfully!");
			}
			String json = gson.toJson("Hello");
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}catch(Exception e) {
			ExceptionHandler.handleException(request, response, e);
		}
	}
	

}
