package com.netbazaar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuccessServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7196033581541178003L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("header");
		PrintWriter pw = response.getWriter();
		
		pw.write("<html><head><title>Success</title></head><body>");
		rd.include(request, response);
		pw.write("<div align=center><h2>Products in Cart</h2></div>");
		pw.write("<a href=\"indexPage\">Home</a><br/>"
				+ "<span style=\"color:green;\">"
				+ "You have successfully purchased the following items:"
				+ "</span><br/>");
		pw.write((String)request.getAttribute("invoice"));
		pw.write("</body></html>");
	}
	
	

}
