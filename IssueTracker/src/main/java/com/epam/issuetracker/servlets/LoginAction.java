package com.epam.issuetracker.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.issuetracker.beans.User;
import com.epam.issuetracker.constants.Constants;
import com.epam.issuetracker.exceptions.DAOException;
import com.epam.issuetracker.factories.DAOFactory;
import com.epam.issuetracker.interfaces.IUserDAO;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.issuetracker.servlets.AbstractServlet#performTask(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String login = request.getParameter(Constants.LOGIN_NAME);
		String password = request.getParameter(Constants.PASSWORD);
		if (login == null || password == null || Constants.EMPTY_STRING.equals(login.trim())
				|| Constants.EMPTY_STRING.equals(password.trim())) {
			request.setAttribute(Constants.MESSAGE, Constants.EMPTY_LOGIN);
			jump(Constants.ISSUES_URL, request, response);
			return;
		}
		try {
			IUserDAO userDAO = DAOFactory.getUserDAOFromFactory();
			User user = userDAO.getUser(login, password);
			if (user != null) {
				request.getSession().setAttribute(Constants.USER, user);
				response.sendRedirect(response.encodeRedirectURL(getServletContext().getContextPath()
						+ Constants.ISSUES_URL));
				return;
			} else {
				request.setAttribute(Constants.MESSAGE, Constants.INCORRECT_LOGIN);
				jump(Constants.ISSUES_URL, request, response);
				return;
			}
		} catch (DAOException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
