package org.example.issuetracker.controllers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.constants.JSPConstants;
import org.example.issuetracker.constants.SqlConstants;
import org.example.issuetracker.model.beans.Build;
import org.example.issuetracker.model.beans.Issue;
import org.example.issuetracker.model.beans.Project;
import org.example.issuetracker.model.beans.Status;
import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IIssueDao;
import org.example.issuetracker.model.dao.factories.DAOFactory;
import org.example.issuetracker.model.exceptions.DAOException;

/**
 * Servlet implementation class EditIssueAction
 */
@WebServlet("/EditIssueAction")
public class EditIssueAction extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public EditIssueAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String stringIssueId = request.getParameter(JSPConstants.ISSUE_ID);
		String summary = request.getParameter(JSPConstants.SUMMARY);
		String description = request.getParameter(JSPConstants.DESCRIPTION);
		String statusString = request.getParameter(JSPConstants.STATUS);
		String resolution = request.getParameter(JSPConstants.RESOLUTION);
		String type = request.getParameter(JSPConstants.TYPE);
		String priority = request.getParameter(JSPConstants.PRIORITY);
		String stringProject = request.getParameter(JSPConstants.PROJECT);
		String stringBuild = request.getParameter(JSPConstants.BUILD);
		String stringAssignee = request.getParameter(JSPConstants.ASSIGNEE);
		long modifiedBy = ((User) request.getSession().getAttribute(Constants.USER)).getId();
		if (stringIssueId == null || summary == null || description == null || statusString == null || type == null
				|| priority == null || stringProject == null || stringBuild == null) {
			jump(Constants.EDIT_ISSUE_URL, JSPConstants.PARAM_NULL_ERROR, request, response);
			return;
		}
		if (summary.equals(Constants.EMPTY_STRING) || description.equals(Constants.EMPTY_STRING)) {
			jump(Constants.EDIT_ISSUE_URL, JSPConstants.PARAM_EMPTY_ERROR, request, response);
			return;
		}
		String statusNewString = String.valueOf(SqlConstants.STATUS_NEW_INDEX);
		if (!((statusNewString.equals(statusString) && stringAssignee == null) || (!statusNewString
				.equals(statusString) && stringAssignee != null))) {
			jump(Constants.EDIT_ISSUE_URL, JSPConstants.PARAM_STATUS_ASSIGNED_ERROR, request, response);
			return;
		}
		String statusResolvedString = String.valueOf(SqlConstants.STATUS_RESOLVED_INDEX);
		String statusClosedString = String.valueOf(SqlConstants.STATUS_CLOSED_INDEX);
		if (!(((statusResolvedString.equals(statusString) || statusClosedString.equals(statusString)) && resolution != null) || (!statusResolvedString
				.equals(statusString) || !statusClosedString.equals(statusString)) && resolution == null)) {
			jump(Constants.EDIT_ISSUE_URL, JSPConstants.PARAM_STATUS_RESOLUTION_ERROR, request, response);
			return;
		}
		long issueId;
		long projectId;
		long assigneeId;
		long buildId;
		int status;
		try {
			issueId = Long.parseLong(stringIssueId);
			status = Integer.parseInt(statusString);
			projectId = Long.parseLong(stringProject);
			buildId = Long.parseLong(stringBuild);
			if (stringAssignee != null) {
				assigneeId = Long.parseLong(stringAssignee);
			} else {
				assigneeId = Constants.EMPTY_ID;
			}
		} catch (NumberFormatException e) {
			jump(Constants.EDIT_ISSUE_URL, JSPConstants.PARAM_ERROR, request, response);
			return;
		}

		Issue issue = new Issue(issueId, summary, description, new Status(status), resolution, type, priority,
				new Project(projectId), new Build(buildId), new User(assigneeId), new User(modifiedBy));
		try {
			IIssueDao issueDao = DAOFactory.getIssueDAOFromFactory();

			if (issueDao.updateIssue(issue)) {
				response.sendRedirect(response.encodeRedirectURL(getServletContext().getContextPath()
						+ Constants.ISSUES_URL));
				return;
			} else {
				jump(Constants.EDIT_ISSUE_URL, JSPConstants.PARAM_ERROR, request, response);
				return;
			}
		} catch (DAOException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
