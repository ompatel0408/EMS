package com.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EMSLogoutServlet")
public class EMSLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public EMSLogoutServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try {
			request.getSession().invalidate();
			request.getRequestDispatcher("EMSLogin.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	

}
