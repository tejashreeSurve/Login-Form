package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.User;
import com.model.*;

public class Controller extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		// id == null for first time which set attributes as "" blank so that null value
		// should not seen to user
		if (id == null) {
			request.setAttribute("msg", "");
			request.setAttribute("email", "");
			request.setAttribute("password", "");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			doPost(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		UserDetail detail = new UserDetail();
		User user = new User();
		// if id = sign_up then send to (sign_up.jsp) page 
		// this is for fist time while user click on link of sign_up
		if (id.equals("sign_up")) {
			request.setAttribute("msg", "");
			request.setAttribute("name", "");
			request.setAttribute("email", "");
			request.setAttribute("password", "");
			request.getRequestDispatcher("sign_up.jsp").forward(request, response); // here id=sign_up_form
		}
		// id = sign_up_form 
		if (id.equals("sign_up_form")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			boolean status = user.validate(name, email, password); // check if email or password follow the regex format
			if (status == true) {
				try {
					detail.insertData(name, email, password); // if true then add to database
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("email", "");
				request.setAttribute("password", "");
				request.setAttribute("msg", "Successfully sign_up!!!Please login _in");
				request.getRequestDispatcher("index.jsp").forward(request, response); // id = login_form
			} else {
				request.setAttribute("msg", "Invalid email-id/password"); 
				request.setAttribute("name", name);
				request.setAttribute("email", email);
				request.setAttribute("password", password);
				request.getRequestDispatcher("sign_up.jsp").forward(request, response); // id = sign_up_form
			}
		}
		// id = login_form 
		if (id.equals("login_form")) {
			request.setAttribute("email", "");
			request.setAttribute("password", "");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			boolean status = false;
			try {
				status = user.isPresent(email, password); // check if email or password is present i database
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			if (status == true) {
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				request.getRequestDispatcher("welcome.jsp").forward(request, response); // if present then send to welcome page
			} else {
				request.setAttribute("msg", "Please First Sign_up");
				request.setAttribute("email", email);
				request.setAttribute("password", password);
				request.getRequestDispatcher("index.jsp").forward(request, response); // else tell to sign_up again
			}
		}
	}
}
