package org.example.issuetracker.controllers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.constants.JSPConstants;
import org.example.issuetracker.constants.SqlConstants;
import org.example.issuetracker.factories.DAOFactory;
import org.example.issuetracker.model.beans.Issue;
import org.example.issuetracker.model.beans.Project;
import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IIssueDao;
import org.example.issuetracker.model.beans.Status;
import org.example.issuetracker.model.exceptions.DAOException;

/**
 * Servlet implementation class AddIssueAction
 */
@WebServlet("/AddIssueAction")
public class AddIssueAction extends AbstractServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String summary = request.getParameter(JSPConstants.SUMMARY);
		String description = request.getParameter(JSPConstants.DESCRIPTION);
		String statusString = request.getParameter(JSPConstants.STATUS);
		String type = request.getParameter(JSPConstants.TYPE);
		String priority = request.getParameter(JSPConstants.PRIORITY);
		String stringProject = request.getParameter(JSPConstants.PROJECT);
		String build = request.getParameter(JSPConstants.BUILD);
		String stringAssignee = request.getParameter(JSPConstants.ASSIGNEE);
		String stringCreatedBy = request.getParameter(JSPConstants.CREATEDBY);
		if (summary == null || description == null || statusString == null || type == null || priority == null || stringProject == null || build == null || stringCreatedBy == null) {
			jump(Constants.ADD_ISSUE_URL, JSPConstants.PARAM_NULL_ERROR, request, response);
			return;
		}
		if (summary.equals(Constants.EMPTY_STRING) || description.equals(Constants.EMPTY_STRING)) {
			jump(Constants.ADD_ISSUE_URL, JSPConstants.PARAM_EMPTY_ERROR, request, response);
			return;
		}
		
		if (!((String.valueOf(SqlConstants.STATUS_NEW_INDEX).equals(statusString) && stringAssignee == null) || (String.valueOf(SqlConstants.STATUS_ASSIGEND_INDEX).equals(statusString) && stringAssignee != null))) {
			jump(Constants.ADD_ISSUE_URL, JSPConstants.PARAM_STATUS_ASSIGNED_ERROR, request, response);
			return;
		}
		long projectId;
		long createdById;
		long assigneeId;
		int status;
		try {
			status = Integer.parseInt(statusString);
			projectId = Long.parseLong(stringProject);
			createdById = Long.parseLong(stringCreatedBy);
			if (stringAssignee != null) {
				assigneeId = Long.parseLong(stringAssignee);
			} else {
				assigneeId = Constants.EMPTY_ID;
			}
		} catch(NumberFormatException e){
			jump(Constants.ADD_ISSUE_URL, JSPConstants.PARAM_ERROR, request, response);
			return;
		}
		
		
		Issue issue = new Issue(new User(createdById), summary, description, new Status(status), type, priority, new Project(projectId), build, new User(assigneeId));
		try {
			IIssueDao issueDao = DAOFactory.getIssueDAOFromFactory();
		
			if (issueDao.addIssue(issue)) {
				response.sendRedirect(response.encodeRedirectURL(getServletContext().getContextPath()
						+ Constants.ISSUES_URL));
				return;
			} else {
				jump(Constants.ADD_ISSUE_URL, JSPConstants.PARAM_ERROR, request, response);
				return;
			}
		} catch (DAOException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
