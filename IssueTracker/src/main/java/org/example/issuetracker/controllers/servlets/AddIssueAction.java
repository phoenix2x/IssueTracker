package org.example.issuetracker.controllers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.constants.JSPConstants;
import org.example.issuetracker.factories.DAOFactory;
import org.example.issuetracker.model.dao.IIssueDao;
import org.example.issuetracker.model.enums.Status;
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
		String status = request.getParameter(JSPConstants.STATUS);
		String type = request.getParameter(JSPConstants.TYPE);
		String priority = request.getParameter(JSPConstants.PRIORITY);
		String stringProject = request.getParameter(JSPConstants.PROJECT);
		String build = request.getParameter(JSPConstants.BUILD);
		String stringAssignee = request.getParameter(JSPConstants.ASSIGNEE);
		String stringCreatedBy = request.getParameter(JSPConstants.CREATEDBY);
		if (summary == null || description == null || status == null || type == null || priority == null || stringProject == null || build == null || stringAssignee == null || stringCreatedBy == null) {
			jump(Constants.ADD_ISSUE_URL, JSPConstants.PARAM_NULL_ERROR, request, response);
			return;
		}
		if (summary.equals(Constants.EMPTY_STRING) || description.equals(Constants.EMPTY_STRING)) {
			jump(Constants.ADD_ISSUE_URL, JSPConstants.PARAM_EMPTY_ERROR, request, response);
			return;
		}
		long projectId;
		long createdById;
		long assigneeId;
		try {
			projectId = Long.parseLong(stringProject);
			createdById = Long.parseLong(stringCreatedBy);
			if (stringAssignee.equals(Constants.EMPTY_STRING)) {
				assigneeId = Constants.EMPTY_ID;
			} else {
				assigneeId = Long.parseLong(stringAssignee);
			}
		} catch(NumberFormatException e){
			jump(Constants.ADD_ISSUE_URL, JSPConstants.PARAM_ERROR, request, response);
			return;
		}
		if (!(Status.NEW.toString().toLowerCase().equals(status) || Status.ASSIGNED.toString().toLowerCase().equals(status))) {
			jump(Constants.ADD_ISSUE_URL, JSPConstants.PARAM_ERROR, request, response);
			return;
		}
		if (!((Status.NEW.toString().toLowerCase().equals(status) && assigneeId == Constants.EMPTY_ID) || ((Status.ASSIGNED.toString().toLowerCase().equals(status)) && assigneeId != Constants.EMPTY_ID))) {
			jump(Constants.ADD_ISSUE_URL, JSPConstants.PARAM_STATUS_ASSIGNED_ERROR, request, response);
			return;
		}
		try {
			IIssueDao issueDao = DAOFactory.getIssueDAOFromFactory();
		
			if (issueDao.addIssue(summary, description, status, type, priority, projectId, build, assigneeId, createdById)) {
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
