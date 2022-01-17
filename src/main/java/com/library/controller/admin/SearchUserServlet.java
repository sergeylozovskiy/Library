package com.library.controller.admin;

import com.library.service.BookService;
import com.library.util.DatabaseFunctions;
import com.library.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;


@WebServlet(name = "searchUser", urlPatterns = "/admin/searchUser")
public class SearchUserServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String login = req.getParameter("login");
		String role = req.getParameter("role");
		String conditionTotal;
		String conditionLogin = null;
		String conditionRole = null;
		if (login != null && login.length() > 0) {
			conditionLogin = "(login like '%" + login + "%')";
		}
		if (role != null && role.length() >0) {
			conditionRole = "(role like '%" + role + "%')";
		}
		if (conditionLogin != null && conditionRole != null) {
			conditionTotal = conditionRole + " and " + conditionLogin;
		} else if (conditionLogin != null && conditionRole == null) {
			conditionTotal = conditionLogin;
		} else {
			conditionTotal = conditionRole;
		}

		Connection connection = DatabaseFunctions.getConnection();
		UserService userService = new UserService(connection);
		req.getSession().setAttribute("users", userService.searchUsers(conditionTotal, null));
		PrintWriter printWriter = resp.getWriter();
		resp.sendRedirect("users.jsp");
		printWriter.close();
	}

}
