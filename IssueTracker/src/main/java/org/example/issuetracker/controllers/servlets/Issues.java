package org.example.issuetracker.controllers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.constants.JSPConstants;
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
		String stringOrderBy = request.getParameter(Constants.ORDER_BY);
		String stringOrder = request.getParameter(Constants.ORDER);
		int orderBy = 0;
		if (stringOrderBy != null) {
			orderBy = Integer.parseInt(stringOrderBy);
		}
		int order = 0;
		if (stringOrder != null) {
			order = Integer.parseInt(stringOrder);
		}
		int pageNumber = 0;
		if (pageNumberString != null) {
			pageNumber = Integer.parseInt(pageNumberString);
		}
		try {
			long rowsCount;
			IIssueDao issueDAO = DAOFactory.getIssueDAOFromFactory();
			List<Issue> issuesList;
			if (user.equals(Constants.GUEST_USER)) {
				rowsCount = issueDAO.getElementNumber();
				issuesList = issueDAO.getLastIssues(Constants.NUMBER_ISSUES, pageNumber * Constants.NUMBER_ISSUES,
						orderBy, order);
			} else {
				rowsCount = issueDAO.getElementNumber(user.getId());
				issuesList = issueDAO.getIssuesByUserId(user.getId(), Constants.NUMBER_ISSUES, pageNumber
						* Constants.NUMBER_ISSUES, orderBy, order);
			}
			double pages = (double) rowsCount / Constants.NUMBER_ISSUES;
			request.setAttribute(JSPConstants.ISSUES, issuesList);
			request.setAttribute(JSPConstants.PAGES, pages);
			request.setAttribute(Constants.PAGE, pageNumber);
			request.setAttribute(Constants.ORDER_BY, orderBy);
			request.setAttribute(Constants.ORDER, order);
			jump(JSPConstants.INDEX_JSP, request, response);
		} catch (DAOException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
