package com.netbazaar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netbazaar.beans.Item;

public class MobileServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8627037546355043754L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter pw = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("header");
		pw.write("<html><head><title>Mobiles</title></head><body>");
		rd.include(request, response);
		pw.write("<div align=center><h2>Mobiles</h2></div>");
		if(request.getAttribute("message")!=null) {
			pw.write("<span style=\"color:lime;\">"+request.getAttribute("message")+"</span><br/>");
			request.removeAttribute("message");
		}
		pw.write("<a href=\"indexPage\">Home </a>");
		pw.write("<a href=\"cart\">View Cart</a>");
		pw.write("<form action=\"cart\" method=\"GET\">");
		printMobileList(pw);
		pw.write("<input type=\"text\" name=\"source\" value=\"mobile\" hidden=\"hidden\"/>");
		pw.write("<input type=\"submit\" name=\"submit\" value=\"Add to Cart\"/>");
		pw.write("</form></body></html>");
	}
	
	public void printMobileList(PrintWriter pw) {
		pw.write("<table>");
		// TODO open connection to database
		// TODO fetch laptop inventory table
		// TODO populate ArrayList from database
		ArrayList<Item> laptops = new ArrayList<Item>();
		laptops.add(new Item("Samsung", 25000));
		laptops.add(new Item("Nokia", 15000));
		laptops.add(new Item("LG", 5000));
		
		
		for(Item item:laptops) {
			pw.write("<tr><td><input type=\"checkbox\" name=\"items\" value=\""+item.getName() +"\"/></td>"
				+ "<td><label>"+item.getName()+"</label></td><td>Price: "+item.getPrice()+"</td><input type=\"text\" name=\"prices\" value=\""+item.getPrice()+"\" hidden=\"hidden\"</tr>");
		}
		pw.write("</table>");
	}
}
