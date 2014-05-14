package org.example.issuetracker.controllers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.constants.JSPConstants;
import org.example.issuetracker.factories.DAOFactory;
import org.example.issuetracker.model.beans.Issue;
import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IIssueDao;
import org.example.issuetracker.model.exceptions.DAOException;

/**
 * Servlet implementation class Issues
 */
@WebServlet("/Issues")
public class Issues extends AbstractServlet {
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
		User user = (User) request.getSession().getAttribute(Constants.USER);
		try {
			IIssueDao issueDAO = DAOFactory.getIssueDAOFromFactory();
			List<Issue> issuesList;
			if (user.equals(Constants.GUEST_USER)) {
				issuesList = issueDAO.getLastIssues(Constants.NUMBER_ISSUES);
			} else {
				issuesList = issueDAO.getIssuesByUserId(user.getId(), Constants.NUMBER_ISSUES);
			}
			request.setAttribute(JSPConstants.ISSUES, issuesList);
			jump(JSPConstants.INDEX_JSP, request, response);
		} catch (DAOException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
