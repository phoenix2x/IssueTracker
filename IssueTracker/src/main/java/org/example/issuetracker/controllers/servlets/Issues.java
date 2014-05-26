package org.example.issuetracker.controllers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.constants.JSPConstants;
import org.example.issuetracker.constants.SqlConstants;
import org.example.issuetracker.model.beans.Issue;
import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IIssueDao;
import org.example.issuetracker.model.dao.factories.DAOFactory;
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
		String pageNumberString = request.getParameter(Constants.PAGE);
		int pageNumber;
		if (pageNumberString != null) {
			pageNumber = Integer.valueOf(pageNumberString);
		} else {
			pageNumber = 0;
		}
		try {
			long rowsCount;
			IIssueDao issueDAO = DAOFactory.getIssueDAOFromFactory();
			List<Issue> issuesList;
			if (user.equals(Constants.GUEST_USER)) {
				rowsCount = issueDAO.getElementNumber();
				issuesList = issueDAO.getLastIssues(Constants.NUMBER_ISSUES, pageNumber * Constants.NUMBER_ISSUES);
			} else {
				rowsCount = issueDAO.getElementNumber(user.getId());
				issuesList = issueDAO.getIssuesByUserId(user.getId(), Constants.NUMBER_ISSUES, pageNumber * Constants.NUMBER_ISSUES, SqlConstants.SELECT_PART_PRIORITY, 1);
			}
			request.setAttribute(JSPConstants.ISSUES, issuesList);
			request.setAttribute(JSPConstants.ROWS_COUNT, rowsCount);
			jump(JSPConstants.INDEX_JSP, request, response);
		} catch (DAOException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
