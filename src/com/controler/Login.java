package com.controler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Model;

public class Login extends HttpServlet{
	/**
	 * This the login phase
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request ,HttpServletResponse response) {
		try {
			String cid=request.getParameter("cid");
			String pwd=request.getParameter("pwd");
		
			Model m=new Model();
			m.setCustid(cid);
			m.setPwd(pwd);
		
			boolean statement=m.login();
		
			if(statement==true) {
				int AcNo=m.getAccno();
				HttpSession session=request.getSession(true);
				session.setAttribute("ACCNO", new Integer(AcNo));
				response.sendRedirect("/BankApplication1/home.jsp");
				
				}
			else {
				response.sendRedirect("/BankApplication1/loginfail.jsp");
			}
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
			
			
		}
		
	}

	
