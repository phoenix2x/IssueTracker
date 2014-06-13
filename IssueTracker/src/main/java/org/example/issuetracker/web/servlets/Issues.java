package org.example.issuetracker.web.servlets;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.Priority;
import org.example.issuetracker.domain.Project;
import org.example.issuetracker.domain.Resolution;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.Type;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.service.IIssueService;
import org.example.issuetracker.service.IProjectService;
import org.example.issuetracker.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Servlet implementation class Issues
 */
@Controller
public class Issues {

	@Inject
	private IIssueService issueService;

	@Inject
	private IUserService userService;

	@Inject
	private IProjectService projectService;

	@RequestMapping("/")
	public String home() {
		return "redirect:/Issues";
	}

	@RequestMapping(value = "/Issues", method = RequestMethod.GET)
	public String showIssuesList(Principal principal, Model model) {
		User user = null;
		if (principal != null) {
			user = userService.getUserByEmail(principal.getName());
		}
		model.addAttribute(issueService.getIssuesList(user));
		return "Issues";
	}

	@RequestMapping(value = "/Issues/Add", method = RequestMethod.GET)
	public String createIssue(Model model) {
		model.addAttribute(new Issue());
		model.addAttribute(issueService.getNewStatuses());
		model.addAttribute(issueService.getProperties(Type.class));
		model.addAttribute(issueService.getProperties(Priority.class));
		model.addAttribute(issueService.getProperties(Project.class));
		model.addAttribute(issueService.getProperties(User.class));
		return "AddIssue";
	}

	@RequestMapping(value = "/Issues/Add", method = RequestMethod.POST)
	public String createIssueFromForm(Principal principal, @ModelAttribute("issue") Issue issue, BindingResult result) {
		issue.setCreatedBy(userService.getUserByEmail(principal.getName()));
		issueService.create(issue);
		return "redirect:/Issues";
	}

	@RequestMapping(value = "/Issues/{id}", method = RequestMethod.GET)
	public String editIssue(@PathVariable("id") long issueId, Model model) {
		model.addAttribute(issueService.getById(issueId));
		model.addAttribute(issueService.getProperties(Status.class));
		model.addAttribute(issueService.getProperties(Type.class));
		model.addAttribute(issueService.getProperties(Priority.class));
		model.addAttribute(issueService.getProperties(Resolution.class));
		model.addAttribute(issueService.getProperties(Project.class));
		model.addAttribute(issueService.getProperties(User.class));
		return "EditIssue";
	}

	@RequestMapping(value = "/Issues/{id}", method = RequestMethod.POST)
	public String editIssueFromForm(@PathVariable("id") long issueId, Principal principal,
			@ModelAttribute("issue") Issue issue, BindingResult result) {
		issue.setModifiedBy(userService.getUserByEmail(principal.getName()));
		issue.setId(issueId);
		System.out.println(issue);
		issueService.update(issue);
		return "redirect:/Issues";
	}

	@RequestMapping(value = "/Builds/{id}", method = RequestMethod.GET)
	public @ResponseBody
	List<Build> getBuilds(@PathVariable("id") long projectId) {
		return projectService.getBuildsByProjectId(projectId);
	}

	@RequestMapping(value = "/Priorities/Add", method = RequestMethod.GET)
	public String addPriority(Model model) {
		model.addAttribute(new Priority());
		return "AddPriority";
	}

	@RequestMapping(value = "/Priorities/Add", method = RequestMethod.POST)
	public String addPriorityFromForm(@ModelAttribute("priority") Priority priority, BindingResult result) {
		issueService.create(priority);
		return "redirect:/Issues";
	}

	@RequestMapping(value = "/Types/Add", method = RequestMethod.GET)
	public String addType(Model model) {
		model.addAttribute(new Type());
		return "AddType";
	}

	@RequestMapping(value = "/Types/Add", method = RequestMethod.POST)
	public String addTypeFromForm(@ModelAttribute("type") Type type, BindingResult result) {
		issueService.create(type);
		return "redirect:/Issues";
	}

	@RequestMapping(value = "/Resolutions/Add", method = RequestMethod.GET)
	public String addResolution(Model model) {
		model.addAttribute(new Resolution());
		return "AddResolution";
	}

	@RequestMapping(value = "/Resolutions/Add", method = RequestMethod.POST)
	public String addResolutionFromForm(@ModelAttribute("resolution") Resolution resolution, BindingResult result) {
		issueService.create(resolution);
		return "redirect:/Issues";
	}

	@RequestMapping(value = "/Projects/Add", method = RequestMethod.GET)
	public String addProject(Model model) {
		model.addAttribute(new Project());
		model.addAttribute(issueService.getProperties(User.class));
		return "AddProject";
	}

	@RequestMapping(value = "/Projects/Add", method = RequestMethod.POST)
	public String addProjectFromForm(@ModelAttribute("project") Project project, BindingResult result) {
		issueService.create(project);
		return "redirect:/Issues";
	}
	// @RequestMapping(value = "/Add", method = RequestMethod.POST)
	// public String addIssue(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	// try {
	// IIssueDao issueDAO = DAOFactory.getIssueDAOFromFactory();
	// IProjectDao projectDAO = DAOFactory.getProjectDaoFromFactory();
	// IUserDao userDAO = DAOFactory.getUserDAOFromFactory();
	// List<Status> statusesList = issueDAO.getNewStatuses();
	// List<String> typesList = issueDAO.getProperties(JSPConstants.TYPES);
	// List<String> prioritiesList =
	// issueDAO.getProperties(JSPConstants.PRIORITIES);
	// List<Project> projectsList = projectDAO.getAll();
	// List<User> assigneesList = userDAO.getAll();
	//
	// request.setAttribute(JSPConstants.STATUSES, statusesList);
	// request.setAttribute(JSPConstants.TYPES, typesList);
	// request.setAttribute(JSPConstants.PRIORITIES, prioritiesList);
	// request.setAttribute(JSPConstants.PROJECTS, projectsList);
	// request.setAttribute(JSPConstants.ASSIGNEES, assigneesList);
	// return "";
	// } catch (DAOException e) {
	// e.printStackTrace();
	// response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	// }
	// return null;
	// }
	//
	// public String showIssuesList(HttpServletRequest request,
	// HttpServletResponse response) {
	// User user = (User) request.getSession().getAttribute(Constants.USER);
	// String pageNumberString = request.getParameter(Constants.PAGE);
	// String stringOrderBy = request.getParameter(Constants.ORDER_BY);
	// String stringOrder = request.getParameter(Constants.ORDER);
	// int orderBy = 0;
	// if (stringOrderBy != null) {
	// orderBy = Integer.parseInt(stringOrderBy);
	// }
	// int order = 0;
	// if (stringOrder != null) {
	// order = Integer.parseInt(stringOrder);
	// }
	// int pageNumber = 0;
	// if (pageNumberString != null) {
	// pageNumber = Integer.parseInt(pageNumberString);
	// }
	// try {
	// long rowsCount;
	// IIssueDao issueDAO = DAOFactory.getIssueDAOFromFactory();
	// List<Issue> issuesList;
	// if (user.equals(Constants.GUEST_USER)) {
	// rowsCount = issueDAO.getAllCount();
	// issuesList = issueDAO.getLastIssues(Constants.NUMBER_ISSUES, pageNumber *
	// Constants.NUMBER_ISSUES,
	// orderBy, order);
	// } else {
	// rowsCount = issueDAO.getElementNumber(user.getId());
	// issuesList = issueDAO.getIssuesByUserId(user.getId(),
	// Constants.NUMBER_ISSUES, pageNumber
	// * Constants.NUMBER_ISSUES, orderBy, order);
	// }
	// double pages = (double) rowsCount / Constants.NUMBER_ISSUES;
	// request.setAttribute(JSPConstants.ISSUES, issuesList);
	// request.setAttribute(JSPConstants.PAGES, pages);
	// request.setAttribute(Constants.PAGE, pageNumber);
	// request.setAttribute(Constants.ORDER_BY, orderBy);
	// request.setAttribute(Constants.ORDER, order);
	// jump(JSPConstants.INDEX_JSP, request, response);
	// } catch (DAOException e) {
	// e.printStackTrace();
	// response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	// }
	// }
}
