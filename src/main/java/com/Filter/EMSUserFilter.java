package com.Filter;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class EMSUserFilter implements Filter
{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String name = request.getParameter("userName");
		String phoneNum = request.getParameter("mobile");
		String departnemt = request.getParameter("departmantName");
		int role = Integer.parseInt(request.getParameter("role"));
		
		boolean isError = false;
		
		if(name == null || name.trim().length() == 0)
		{
			isError = true;
			request.setAttribute("nameErr", "Enter the valid name.");
		}
		if(phoneNum.trim().length() <= 9)
		{
			isError = true;
			request.setAttribute("phoneErr", "Enter the valid Phone number.");
		}
		if(departnemt == null  && departnemt.trim().length() == 0)
		{
			isError = true;
			request.setAttribute("dpErr", "Select valid department.");
		}
		if(role == 0)
		{
			isError = true;
			request.setAttribute("roleErr", "Select valid Role.");
		}
		
		if(isError)
		{
			request.getRequestDispatcher("AddUser.jsp").forward(request, response);
		}
		else
		{
			chain.doFilter(request, response);
		}
	}
	
}