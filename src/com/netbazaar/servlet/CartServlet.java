package com.netbazaar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.netbazaar.beans.Item;

public class CartServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7468509036337929879L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("submit")!=null) {
			
			String message ="Items: ";
			String[] items = request.getParameterValues("items");
			String[] prices = request.getParameterValues("prices");
			HttpSession s = request.getSession();
			LinkedList<Item> itemList = new LinkedList<Item>();
			
			for (int i = 0; i < items.length; i++) {
				String item = items[i];
				int price = Integer.parseInt(prices[i]);
				
				message+= item+" ";
				
				if(s.getAttribute("cart")!=null) {
					itemList = (LinkedList<Item>)s.getAttribute("cart");
				}
				itemList.add(new Item(item, price));
			}
			s.setAttribute("cart", itemList);
			message+= " added to cart.";
			
			String source = request.getParameter("source");
			RequestDispatcher rd = request.getRequestDispatcher(source);
			request.setAttribute("message", message);
			rd.forward(request, response);
		}
		
		
		else {
			
			HttpSession s = request.getSession();
			LinkedList<Item> items = (LinkedList<Item>)s.getAttribute("cart");
			String message = null;
			
			if(request.getParameter("remove")!=null) {
				message = "<span style=\"color:red;\">Items: ";
				String[] indices = request.getParameterValues("selected");
				int position = 0;
				for(int i = 0; i < indices.length; i++) {
					int index = Integer.parseInt(indices[i])+position;
					message+=items.get(index).getName()+" ";
					items.remove(index);
					position--;
				}
				s.setAttribute("cart", items);
				message+=" removed.</span><br/>";
			}
			
			else if(request.getParameter("purchase")!=null) {
				String invoice = "<ul>";
				for(Item itm:items) {
					invoice+="<li>"+itm.getName()+"</li><br/>";
				}
				invoice+="</ul>";
				s.removeAttribute("cart");
				RequestDispatcher rd = request.getRequestDispatcher("success");
				request.setAttribute("invoice", invoice);
				rd.forward(request, response);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("header");
			PrintWriter pw = response.getWriter();
			
			pw.write("<html><head><title>Cart</title></head><body>");
			rd.include(request, response);
			pw.write("<div align=center><h2>Products in Cart</h2></div>");
			
			if(message!=null) {
				pw.write(message);
			}
			pw.write("<a href=\"indexPage\">Home</a><br/>");
			if(items==null || items.isEmpty()) {
				pw.write("<h3>Your cart is empty.</h3>");
			}
			else {
				pw.write("<form action=\"cart\" method=\"GET\"><table>");
				int counter = 0;
				for(Item itm:items) {
					pw.write("<tr><td><input type=\"checkbox\" name=\"selected\" value=\""+counter
					+"\"</td><td>"+itm.getName()+"</td><td>Price: "+itm.getPrice()+"</td></tr>");
					counter++;
				}
				pw.write("</table><input type=\"submit\" name=\"remove\" value=\"Remove From Cart\"/>"
						+ "<input type=\"submit\" name=\"purchase\" value=\"Purchase\">"
						+ "</form>");
			}
			pw.write("</body></html>");
		}
	}

	
}
