package com.netbazaar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexPageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5942712957007563787L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter pw = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("header");
		pw.write("<html><head><title>Home</title></head><body>");
		rd.include(request, response);
		pw.write("<a href=\"mobile\">Mobiles</a><br/>");
		pw.write("<a href=\"laptop\">Laptops</a><br/>");
		pw.write("<a href=\"watch\">Watches</a><br/>");
		pw.write("</body></html>");
	}
	
}
