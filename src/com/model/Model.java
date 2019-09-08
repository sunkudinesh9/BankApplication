package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;

public class Model {
	int accno;
	String custid;
	String pwd;
	int balance;
	String email;
	String name;

	Connection con = null;
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "system";
	String password = "system";
	PreparedStatement pstmt = null;
	ResultSet res = null;

	public Model() {
		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("driver loaded succesfully");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("connectione established");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean login() {
		try {
			pstmt = con.prepareStatement("SELECT * FROM BANK WHERE CUSTID=? AND PWD=?");
			pstmt.setString(1, custid);
			pstmt.setString(2, pwd);

			res = pstmt.executeQuery();
			while (res.next() == true) {
				accno = res.getInt(1);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean checkBalance() {
		try {
			pstmt = con.prepareStatement("SELECT * FROM BANK WHERE ACCNO=?");
			pstmt.setLong(1, accno);
			res = pstmt.executeQuery();

			while (res.next() == true) {
				balance = res.getInt(4);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean changePassword(String npwd) {

		try {
			pstmt = con.prepareStatement("UPDATE BANK SET PWD=? WHERE PWD=?");
			pstmt.setString(1, npwd);
			pstmt.setString(2, pwd);
			int value = pstmt.executeUpdate();
			System.out.println(value);

			if (value == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	public boolean transfer(String amount,String thirdPartyAc_No) {
		try {
			pstmt=con.prepareStatement("UPDATE BANK SET BALANCE=BALANCE-? WHERE ACCNO=?");
			pstmt.setString(1,amount);
			pstmt.setLong(2, accno);
			pstmt.executeUpdate();
			pstmt=con.prepareStatement("INSERT INTO BANKSTMT VALUES(?,?,?)");
			pstmt.setLong(1, accno);
			pstmt.setString(2, thirdPartyAc_No);
			pstmt.setString(3, amount);
			
			int value=pstmt.executeUpdate();
			if(value==1) {
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public  ArrayList getStatement() {
		ArrayList al=new ArrayList();
		try {
			pstmt= con.prepareStatement("SELECT * FROM BANKSTMT WHERE ACCNO=?");
			pstmt.setLong(1, accno);
			res=pstmt.executeQuery();
			while(res.next()==true) {
				al.add(res.getString(1));
				al.add(res.getString(2));
				al.add(res.getString(3));
			}
			System.out.println(al);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return al;
	}

}
