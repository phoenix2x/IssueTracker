package org.example.issuetracker.controllers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IUserDao;
import org.example.issuetracker.model.dao.factories.DAOFactory;
import org.example.issuetracker.model.exceptions.DAOException;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(LoginAction.class);

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
		String login = request.getParameter(Constants.EMAIL_ADDRESS);
		String password = request.getParameter(Constants.PASSWORD);
		if (login == null || password == null || Constants.EMPTY_STRING.equals(login.trim())
				|| Constants.EMPTY_STRING.equals(password.trim())) {
			jump(Constants.ISSUES_URL, Constants.EMPTY_LOGIN, request, response);
			return;
		}
		try {
			IUserDao userDAO = DAOFactory.getUserDAOFromFactory();
			User user = userDAO.getUser(login, password);
			if (user != null) {
				request.getSession().setAttribute(Constants.USER, user);
				String referer = request.getHeader(Constants.REFERER);
				if (referer != null) {
					response.sendRedirect(response.encodeRedirectURL(referer));
				} else {
					response.sendRedirect(response.encodeRedirectURL(getServletContext().getContextPath()
							+ Constants.ISSUES_URL));
				}
			} else {
				LOG.info("Incorect login/pass " + login + "/" + password);
				jump(Constants.ISSUES_URL, Constants.INCORRECT_LOGIN, request, response);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
