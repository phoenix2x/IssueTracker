package org.example.issuetracker.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.issuetracker.constants.JSPConstants;
import org.example.issuetracker.dao.IIssueDao;
import org.example.issuetracker.dao.IProjectDao;
import org.example.issuetracker.dao.IUserDao;
import org.example.issuetracker.dao.factories.DAOFactory;
import org.example.issuetracker.domain.Project;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.model.exceptions.DAOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Servlet implementation class AddIssues
 */
@Controller
@RequestMapping("/Issue")
public class AddIssue {

	@RequestMapping(value = "/Add", method = RequestMethod.POST)
	protected String addIssue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
//			return (JSPConstants.ADD_ISSUE_JSP, request, response);
		} catch (DAOException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return null;
	}
}
