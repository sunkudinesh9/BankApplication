package com.controler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Model;

public class Transfer extends HttpServlet{
	/**
	 * this is an amount transfer phase
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request,HttpServletResponse response) {
		try {
			String thirdPartyAc_No=request.getParameter("tpa");
			String amount=request.getParameter("amt");
			HttpSession session = request.getSession();
			Integer accno1 = (Integer) session.getAttribute("ACCNO");
			int accno = accno1.intValue();
			
			Model m=new Model();
			m.setAccno(accno);
			boolean status=m.transfer(thirdPartyAc_No,amount);
			if(status==true) {
				response.sendRedirect("/BankApplication1/balanceupdate.jsp");
			}
			else {
				response.sendRedirect("/BankApplication1/balanceupdatefail.jsp");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}