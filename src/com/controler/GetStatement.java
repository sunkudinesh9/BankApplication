package com.controler;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Model;

public class GetStatement extends HttpServlet {
	/**
	 * hear we are getting the statement 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			String acno=request.getParameter("acno");
			System.out.println(acno);
			
			Model m = new Model();
			int Acno=Integer.parseInt(acno);
			m.setAccno(Acno);
			
			ArrayList al = m.getStatement();
			if (al == null) {
				response.sendRedirect("/BankApplication1/statementfail.jsp");
			} 
			else {
				HttpSession session = request.getSession(true);
				session.setAttribute("al", al);
				response.sendRedirect("/BankApplication1/statement.jsp");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}