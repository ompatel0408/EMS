package com.Filter;

import java.io.IOException;

import com.service.Authentication;
import com.util.EMSLoginValidators;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class EMSLoginFilter implements Filter {

    public EMSLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		String email1 =request.getParameter("email");
		String password1 = request.getParameter("password");
		
		
		System.out.println(email1);

		System.out.println(password1);
		
		boolean isError = false;
		if(email1==null || email1.trim().length()==0) {
			isError=true;
			request.setAttribute("EmailError","email is required!");
		}else if(EMSLoginValidators.isValidEmail(email1)==false) {
			isError=true;
			request.setAttribute("EmailError", "Please,Enter valid email!");
			request.setAttribute("EmailValue", email1);
			
		}else {
			request.setAttribute("EmailValue", email1);
		}
		
		if(password1==null || password1.trim().length()==0) {
			isError=true;
			request.setAttribute("PasswordError","Password is required!");
		}else if(EMSLoginValidators.isValidPassword(password1)==false) {
			isError=true;
			request.setAttribute("PasswordError", "Please,Enter valid Password!");
			request.setAttribute("PasswordValue", password1);
			
		}else {
			request.setAttribute("PasswordValue", password1);
		}
		
		RequestDispatcher rd = null;
		if(isError) {
			rd = request.getRequestDispatcher("EMSLogin.jsp");
			rd.forward(request, response);
			
		}else {
			// pass the request along the filter chain
			if(Authentication.isValidCredentials(email1, password1)) {
				chain.doFilter(request, response);
			}else {
				request.setAttribute("InvalidCredentials", "Invalid username or paassword !");
				rd = request.getRequestDispatcher("EMSLogin.jsp");
				rd.forward(request, response);
			}
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
