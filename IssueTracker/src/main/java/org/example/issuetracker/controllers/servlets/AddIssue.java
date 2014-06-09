package org.example.issuetracker.controllers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.issuetracker.constants.JSPConstants;
import org.example.issuetracker.model.beans.Project;
import org.example.issuetracker.model.beans.Status;
import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IIssueDao;
import org.example.issuetracker.model.dao.IProjectDao;
import org.example.issuetracker.model.dao.IUserDao;
import org.example.issuetracker.model.dao.factories.DAOFactory;
import org.example.issuetracker.model.exceptions.DAOException;
/**
 * Servlet implementation class AddIssues
 */
@WebServlet("/AddIssue")
public class AddIssue extends AbstractServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			IIssueDao issueDAO = DAOFactory.getIssueDAOFromFactory();
			IProjectDao projectDAO = DAOFactory.getProjectDaoFromFactory();
			IUserDao userDAO = DAOFactory.getUserDAOFromFactory();
			List<Status> statusesList = issueDAO.getNewStatuses();
			List<String> typesList = issueDAO.getProperties(JSPConstants.TYPES);
			List<String> prioritiesList = issueDAO.getProperties(JSPConstants.PRIORITIES);
			List<Project> projectsList = projectDAO.getAll();
			List<User> assigneesList = userDAO.getAll();
			
			request.setAttribute(JSPConstants.STATUSES, statusesList);
			request.setAttribute(JSPConstants.TYPES, typesList);
			request.setAttribute(JSPConstants.PRIORITIES, prioritiesList);
			request.setAttribute(JSPConstants.PROJECTS, projectsList);
			request.setAttribute(JSPConstants.ASSIGNEES, assigneesList);
			jump(JSPConstants.ADD_ISSUE_JSP, request, response);
		} catch (DAOException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
