package com.Filter;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.HashMap;

import com.bean.EMSLoginBean;
import com.dao.EMSLoginDao;
import com.service.Authentication;
import com.service.EMSLoginServices;
import com.util.EMSLoginValidators;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EMSLoginFilter implements Filter {

    public EMSLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		 
		EMSLoginDao ELD = EMSLoginDao.getInstance();
		String password = request.getParameter("password");
		System.out.println(password);
		password = password.trim();
		EMSLoginBean ELB =   ELD.getAllDetails(password);
		
		if(ELB != null) {
				EMSLoginBean ELB1 = EMSLoginServices.geDataFromJWTToken(password,ELB.getSecretKey());
				HttpServletResponse res = (HttpServletResponse)response;
				if(ELB1 == null) {
					res.sendRedirect("InvalidToken.jsp");
				}
				HttpServletRequest req = (HttpServletRequest)request;
				HttpSession session = req.getSession();
				session.setAttribute("userId", ELB.getUserId());
				chain.doFilter(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("EMSLogin.jsp");
			rd.forward(request, response);
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
