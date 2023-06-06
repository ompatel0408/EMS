package com.Filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AllRequestFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest request = (HttpServletRequest)(req);
		HttpServletResponse response = (HttpServletResponse)(res);
		
		try {
			
			
			System.out.println("Hii reached!!!!");
			if((request.getSession().getAttribute("userId") == null) && (request.getRequestURL().toString().contains(".jsp"))) {
				System.out.println("Hii reached!!!!1");
				request.getRequestDispatcher("Unauthorised.jsp").forward(request, response);
				
			}else {
				 chain.doFilter(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Hii reached!!!!2");
			response.sendRedirect("EMSLogin.jsp");
		}
		
		
	}
}
