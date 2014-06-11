package org.example.issuetracker.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.constants.JSPConstants;
import org.example.issuetracker.dao.IIssueDao;
import org.example.issuetracker.dao.IProjectDao;
import org.example.issuetracker.dao.IUserDao;
import org.example.issuetracker.dao.factories.DAOFactory;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.Priority;
import org.example.issuetracker.domain.Project;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.Type;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.model.exceptions.DAOException;
import org.example.issuetracker.service.IIssueService;
import org.example.issuetracker.service.IssueService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Servlet implementation class Issues
 */
@Controller
@RequestMapping("/Issues")
public class Issues {

	@Autowired
	private IIssueService issueService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showIssuesList(HttpSession session, Model model) {
		
		System.out.println();
		User user = (User) session.getAttribute(Constants.USER);
		model.addAttribute(issueService.getIssuesList(user));
		return "Issues";
	}
	@RequestMapping(method = RequestMethod.GET, params = "new")
	public String createIssue(Model model) {
		model.addAttribute(new Issue());
		model.addAttribute(issueService.getProperties(Status.class));
		model.addAttribute(issueService.getProperties(Type.class));
		model.addAttribute(issueService.getProperties(Priority.class));
		model.addAttribute(issueService.getProperties(Project.class));
		model.addAttribute(issueService.getProperties(User.class));
		return "AddIssue";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String createIssueFromForm(@ModelAttribute("issue") Issue issue,
			BindingResult result, @RequestParam("test") Status status) {
		System.out.println(issue);
		System.out.println(issue.getStatus().getId() + "id" + issue.getStatus().getName());
		System.out.println("statusC" + status);
//		issueService.create(issue);
		return "redirect:/Issues";
	}
	
	@RequestMapping(value = "/Add", method = RequestMethod.POST)
	public String addIssue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			return "";
		} catch (DAOException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return null;
	}
	
//	public String showIssuesList(HttpServletRequest request, HttpServletResponse response) {
//		User user = (User) request.getSession().getAttribute(Constants.USER);
//		String pageNumberString = request.getParameter(Constants.PAGE);
//		String stringOrderBy = request.getParameter(Constants.ORDER_BY);
//		String stringOrder = request.getParameter(Constants.ORDER);
//		int orderBy = 0;
//		if (stringOrderBy != null) {
//			orderBy = Integer.parseInt(stringOrderBy);
//		}
//		int order = 0;
//		if (stringOrder != null) {
//			order = Integer.parseInt(stringOrder);
//		}
//		int pageNumber = 0;
//		if (pageNumberString != null) {
//			pageNumber = Integer.parseInt(pageNumberString);
//		}
//		try {
//			long rowsCount;
//			IIssueDao issueDAO = DAOFactory.getIssueDAOFromFactory();
//			List<Issue> issuesList;
//			if (user.equals(Constants.GUEST_USER)) {
//				rowsCount = issueDAO.getAllCount();
//				issuesList = issueDAO.getLastIssues(Constants.NUMBER_ISSUES, pageNumber * Constants.NUMBER_ISSUES,
//						orderBy, order);
//			} else {
//				rowsCount = issueDAO.getElementNumber(user.getId());
//				issuesList = issueDAO.getIssuesByUserId(user.getId(), Constants.NUMBER_ISSUES, pageNumber
//						* Constants.NUMBER_ISSUES, orderBy, order);
//			}
//			double pages = (double) rowsCount / Constants.NUMBER_ISSUES;
//			request.setAttribute(JSPConstants.ISSUES, issuesList);
//			request.setAttribute(JSPConstants.PAGES, pages);
//			request.setAttribute(Constants.PAGE, pageNumber);
//			request.setAttribute(Constants.ORDER_BY, orderBy);
//			request.setAttribute(Constants.ORDER, order);
//			jump(JSPConstants.INDEX_JSP, request, response);
//		} catch (DAOException e) {
//			e.printStackTrace();
//			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//		}
//	}
}
