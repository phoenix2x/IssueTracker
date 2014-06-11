package org.example.issuetracker.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.issuetracker.constants.Constants;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.example.issuetracker.servlets.AbstractServlet#performTask(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		request.getSession().invalidate();
		response.sendRedirect(response.encodeRedirectURL(getServletContext().getContextPath() + Constants.ISSUES_URL));
	}
}
