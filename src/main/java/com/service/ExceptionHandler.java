package com.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import com.bean.EMSLoginBean;
import com.dao.EMSLoginDao;
import com.util.SendMail;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionHandler {

	 public static void handleException(HttpServletRequest request, HttpServletResponse response, Exception e)
	            throws ServletException, IOException {

		 	System.out.print("Exception reached!");
		 
	        // Log the exception (optional)
		 	try (PrintWriter writer = new PrintWriter(new FileWriter("/Users/ompatel/Desktop/Java Eclipse/workspace/EMS/error.log",true))) {
		        e.printStackTrace(writer);
		    } catch (IOException ioEx) {
		        ioEx.printStackTrace();
		    }
		 			 
//		 	for(EMSLoginBean ELB:EMSLoginDao.getInstance().getData()) {
//		 		SendMail.sendErrorLogs(ELB.getEmail(),e.printStackTrace());
//		 	}

	        // Set response status code to indicate an error
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        
	        String referer = request.getHeader("referer");
	        System.out.println("-------->"+referer);
	        if (referer != null) {
	            // Do something if the referer contains "example.com"
	        	request.setAttribute("referer",referer);	
	        }

	        
//	        Enumeration<String> headerNames = request.getHeaderNames();
//	        while (headerNames.hasMoreElements()) {
//	            String headerName = headerNames.nextElement();
//	            String headerValue = request.getHeader(headerName);
//	            
//	            System.out.println(headerName + ": " + headerValue);
//	        }

	        
	        
	        
//	        String referer = request.getHeader("referer");
//	        if (referer != null && referer.contains("example.com")) {
//	            // Do something if the referer contains "example.com"
//	        } else {
//	            // Do something else if the referer is not valid
//	        }

	        
	        
	        // Forward the request to an error page
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Errors.jsp");
	        dispatcher.forward(request, response);

	  }
}
