package org.example.issuetracker.controllers.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.constants.JSPConstants;
import org.example.issuetracker.model.beans.Issue;
import org.example.issuetracker.model.beans.Project;
import org.example.issuetracker.model.beans.Status;
import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IIssueDao;
import org.example.issuetracker.model.dao.IProjectDao;
import org.example.issuetracker.model.dao.IUserDao;
import org.example.issuetracker.model.dao.factories.DAOFactory;
import org.example.issuetracker.model.exceptions.DAOException;

/**
 * Servlet implementation class EditIssue
 */
@WebServlet("/EditIssue")
public class EditIssue extends AbstractServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public EditIssue() {
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String issueIdString = request.getParameter(JSPConstants.ISSUE_ID);
		long issueId;
		try {
			issueId = Long.valueOf(issueIdString);
		} catch(Exception e) {
			jump(Constants.ISSUES_URL, request, response);
			return;
		}
		try {
			IIssueDao issueDAO = DAOFactory.getIssueDAOFromFactory();
			IProjectDao projectDAO = DAOFactory.getProjectDaoFromFactory();
			IUserDao userDAO = DAOFactory.getUserDAOFromFactory();
			Issue issue = issueDAO.getById(issueId);
			if (issue == null) {
				jump(Constants.ISSUES_URL, request, response);
				return;
			}
			Map<Integer, Status> statuses = issueDAO.getStatuses(issue.getStatus().getId());
			List<String> resolutionsList = issueDAO.getProperties(JSPConstants.RESOLUTIONS);
			List<String> typesList = issueDAO.getProperties(JSPConstants.TYPES);
			List<String> prioritiesList = issueDAO.getProperties(JSPConstants.PRIORITIES);
			List<Project> projectsList = projectDAO.getAll();
			List<User> assigneesList = userDAO.getAll();
			
			request.setAttribute(JSPConstants.ISSUE, issue);
			request.setAttribute(JSPConstants.STATUSES, statuses);
			request.setAttribute(JSPConstants.RESOLUTIONS, resolutionsList);
			request.setAttribute(JSPConstants.TYPES, typesList);
			request.setAttribute(JSPConstants.PRIORITIES, prioritiesList);
			request.setAttribute(JSPConstants.PROJECTS, projectsList);
			request.setAttribute(JSPConstants.ASSIGNEES, assigneesList);
			jump(JSPConstants.EDIT_ISSUE_JSP, request, response);
		} catch (DAOException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
	}
}
