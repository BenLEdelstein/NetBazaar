package com.netbazaar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2389821868255854165L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userName = req.getParameter("userName"),
				password = req.getParameter("password");
		if(validate(userName, password)) {
			resp.sendRedirect("indexPage");
		}
		else {
			PrintWriter writer = resp.getWriter();
			writer.print("<html><head><title>Error:Login</title></head><body>");
			writer.print("<span style=\"color:red;\">Invalid Login</span>");
			writer.print("<form action=\"Login\" method=\"post\">\r\n" + 
					"<label for=\"userName\">User Name: </label>\r\n" + 
					"<input type=\"text\" name=\"userName\"/><br/>\r\n" + 
					"<label for=\"password\">Password: </label>\r\n" + 
					"<input type=\"password\" name=\"password\"/>\r\n" + 
					"<input type=\"submit\" name=\"submit\" value=\"Login\"/>\r\n" + 
					"</form></body></html>");
		}
	}

	private boolean validate(String userName, String password) {
		
		if(!userName.isEmpty() && !password.isEmpty()) {
		// TODO Open Connection to database
		// TODO Fetch User from database where userName matches to parameter userName
		// TODO check that passwords match
			return true;
		}
		return false;
	}
	
	
}
