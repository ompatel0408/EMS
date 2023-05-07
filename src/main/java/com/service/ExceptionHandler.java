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
		 			 
	        // Set response status code to indicate an error
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        
	        String referer = request.getHeader("referer");
	        System.out.println("-------->"+referer);
	        if (referer != null) {
	            // Do something if the referer contains "example.com"
	        	request.setAttribute("referer",referer);	
	        }

	       
	        
	        // Forward the request to an error page
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Errors.jsp");
	        dispatcher.forward(request, response);

	  }
}
