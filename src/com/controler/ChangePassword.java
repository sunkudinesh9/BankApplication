package com.controler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Model;

public class ChangePassword extends HttpServlet {
	/**
	 * Pass word is changing hear
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			String opwd = request.getParameter("opwd");
			String npwd = request.getParameter("npwd");
			Model m = new Model();
			m.setPwd(opwd);
			boolean statement = m.changePassword(npwd);

			if (statement == true) {
				response.sendRedirect("/BankApplication1/passwordchangesuccess.jsp");
			}
			else {
				response.sendRedirect("/BankApplication1/passwordchangegfail.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}