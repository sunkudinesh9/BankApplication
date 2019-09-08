package com.controler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Model;

public class Balance extends HttpServlet {
	/**
	 * hear balance is taking
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			Integer accno1 = (Integer) session.getAttribute("ACCNO");
			int accno = accno1.intValue();
			Model m = new Model();

			m.setAccno(accno);
			boolean status = m.checkBalance();
			if (status == true) {
				int balance = m.getBalance();
				session.setAttribute("BALANCE", new Integer(balance));
				response.sendRedirect("/BankApplication1/balance.jsp");
			} else {
				response.sendRedirect("/BankAppliaction1/balancefail.jsp");
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}