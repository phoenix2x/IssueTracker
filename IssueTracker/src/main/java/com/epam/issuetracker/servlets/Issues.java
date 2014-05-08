package com.epam.issuetracker.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.issuetracker.beans.Issue;
import com.epam.issuetracker.beans.User;
import com.epam.issuetracker.constants.Constants;
import com.epam.issuetracker.exceptions.DAOException;
import com.epam.issuetracker.factories.DAOFactory;
import com.epam.issuetracker.interfaces.IIssueDAO;
import com.epam.issuetracker.interfaces.IUserDAO;

/**
 * Servlet implementation class Issues
 */
@WebServlet("/Issues")
public class Issues extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Issues() {
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
		PrintWriter writer = response.getWriter();
		writer.print("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF8\"><title>Issues</title></head><body>");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(response
				.encodeURL(Constants.HEADER_MENU_URL));
		requestDispatcher.include(request, response);
		writer.print("<hr>");
		String message = (String) request.getAttribute(Constants.MESSAGE);
		if (message != null) {
			writer.print("<h2>" + message + "</h2>");
		}
		User user = (User) request.getSession().getAttribute(Constants.USER);
		try {
			IIssueDAO issueDAO = DAOFactory.getIssueDAOFromFactory();
			List<Issue> issuesList;
			if (user.equals(Constants.GUEST_USER)) {
				issuesList = issueDAO.getLastIssues(Constants.NUMBER_ISSUES);
			} else {
				issuesList = issueDAO.getIssuesByUserId(user.getId(), Constants.NUMBER_ISSUES);
			}
			if (issuesList.size() == 0) {
				writer.print("No issues found.");
			} else {
				printIssuesTable(issuesList, writer);
			}
		} catch (DAOException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		writer.print("</body></html>");
		writer.flush();
	}

	private void printIssuesTable(List<Issue> issuesList, PrintWriter writer) throws DAOException {
		IUserDAO userDAO = DAOFactory.getUserDAOFromFactory();
		writer.print("<table border=\"1\">");
		writer.print("<tr><td>ID</td><td>Priority</td><td>Assigny</td><td>Type</td><td>Status</td><td>Summary</td></tr>");
		for (Issue issue : issuesList) {
			writer.print("<tr><td>");
			writer.print(issue.getId());
			writer.print("</td><td>");
			writer.print(issue.getPriority());
			writer.print("</td><td>");
			writer.print(userDAO.getUserByID(issue.getAssignee()).getLoginName());
			writer.print("</td><td>");
			writer.print(issue.getType());
			writer.print("</td><td>");
			writer.print(issue.getStatus());
			writer.print("</td><td>");
			writer.print(issue.getSummary());
			writer.print("</td></tr>");
		}
		writer.print("</table>");
	}
}
