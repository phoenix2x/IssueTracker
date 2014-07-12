package org.example.issuetracker.web.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.domain.Attachment;
import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.Comment;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.PaginationParams;
import org.example.issuetracker.domain.Priority;
import org.example.issuetracker.domain.Project;
import org.example.issuetracker.domain.Resolution;
import org.example.issuetracker.domain.SearchIssue;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.Type;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.service.IIssueService;
import org.example.issuetracker.service.IProjectService;
import org.example.issuetracker.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * Servlet implementation class IssuesServlet
 */
@Controller
@RequestMapping(value = "/Issues")
public class IssuesServlet {
	
	private static final String DEF_MIME_TYPE = "application/octet-stream";
	private static final Logger LOG = Logger.getLogger(IssuesServlet.class);

	@Inject
	private IIssueService issueService;

	@Inject
	private IUserService userService;

	@Inject
	private IProjectService projectService;

//	@RequestMapping("/")
//	public String home() {
//		return "redirect:/IssuesServlet";
//	}

//	@RequestMapping(method = RequestMethod.GET)
//	public String showIssuesList(Principal principal, Model model) {
//		User user = null;
//		if (principal != null) {
//			user = userService.getUserByEmail(principal.getName());
//		}
//		model.addAttribute(issueService.getIssuesList(user));
//		model.addAttribute("pages", issueService.getIssuesPages(user));
//		return "IssuesServlet";
//	}

	@RequestMapping(value = "/Add", method = RequestMethod.GET)
	public String createIssue(Model model) {
		model.addAttribute(new Issue());
		model.addAttribute(issueService.getNewStatuses());
		model.addAttribute(issueService.getProperties(Type.class));
		model.addAttribute(issueService.getProperties(Priority.class));
		model.addAttribute(issueService.getProperties(Project.class));
		model.addAttribute(issueService.getProperties(User.class));
		return "AddIssue";
	}

	@RequestMapping(value = "/Add", method = RequestMethod.POST)
	public String createIssueFromForm(Principal principal, @Valid @ModelAttribute("issue") Issue issue, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute(issueService.getNewStatuses());
			model.addAttribute(issueService.getProperties(Type.class));
			model.addAttribute(issueService.getProperties(Priority.class));
			model.addAttribute(issueService.getProperties(Project.class));
			model.addAttribute(issueService.getProperties(User.class));
			return "AddIssue";
		}
		issue.setCreatedBy(userService.getUserByEmail(principal.getName()));
		issueService.createWithNotification(issue);
		return "redirect:/Issues";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editIssue(@PathVariable("id") long issueId, Model model) {
		model.addAttribute(issueService.getById(issueId));
		model.addAttribute(new Comment());
		model.addAttribute(issueService.getProperties(Status.class));
		model.addAttribute(issueService.getProperties(Type.class));
		model.addAttribute(issueService.getProperties(Priority.class));
		model.addAttribute(issueService.getProperties(Resolution.class));
		model.addAttribute(issueService.getProperties(Project.class));
		model.addAttribute(issueService.getProperties(User.class));
		return "EditIssue";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String editIssueFromForm(@PathVariable("id") long issueId, Model model, Principal principal,
			@Valid @ModelAttribute("issue") Issue issue, Errors errors) {
		if (errors.hasErrors()) {
			model.addAttribute(issueService.getById(issueId));
			model.addAttribute(issueService.getProperties(Status.class));
			model.addAttribute(issueService.getProperties(Type.class));
			model.addAttribute(issueService.getProperties(Priority.class));
			model.addAttribute(issueService.getProperties(Resolution.class));
			model.addAttribute(issueService.getProperties(Project.class));
			model.addAttribute(issueService.getProperties(User.class));
			return "EditIssue";
		}
		issue.setModifiedBy(userService.getUserByEmail(principal.getName()));
		issue.setId(issueId);
		issueService.updateWithNotification(issue);
		return "redirect:/Issues";
	}
	@RequestMapping(value = "/{id}/AddComment", method = RequestMethod.POST)
	public String createCommentFromForm(@PathVariable("id") long issueId, Principal principal, @Valid @ModelAttribute("comment") Comment comment, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute(issueService.getById(issueId));
			model.addAttribute(issueService.getProperties(Status.class));
			model.addAttribute(issueService.getProperties(Type.class));
			model.addAttribute(issueService.getProperties(Priority.class));
			model.addAttribute(issueService.getProperties(Resolution.class));
			model.addAttribute(issueService.getProperties(Project.class));
			model.addAttribute(issueService.getProperties(User.class));
			return "EditIssue";
		}
		comment.setAddedBy(userService.getUserByEmail(principal.getName()));
		comment.setId(0);
		comment.setIssue(issueService.getById(issueId));
		issueService.createProperty(comment);
		return "redirect:/Issues/" + issueId;
	}
	@RequestMapping(value = "/{id}/AddFile", method = RequestMethod.POST)
	public String createFileFromForm(@PathVariable("id") long issueId, Principal principal, @RequestParam(value="file", required=false) MultipartFile file, Model model, HttpServletRequest request) {
		if (file.isEmpty()) {
			model.addAttribute(new Comment());
			model.addAttribute(issueService.getById(issueId));
			model.addAttribute(issueService.getProperties(Status.class));
			model.addAttribute(issueService.getProperties(Type.class));
			model.addAttribute(issueService.getProperties(Priority.class));
			model.addAttribute(issueService.getProperties(Resolution.class));
			model.addAttribute(issueService.getProperties(Project.class));
			model.addAttribute(issueService.getProperties(User.class));
			return "EditIssue";
		}
		Attachment attachment = new Attachment();
		User user = userService.getUserByEmail(principal.getName());
		attachment.setAddedBy(user);
		attachment.setIssue(issueService.getById(issueId));
		attachment.setFileName(file.getOriginalFilename());
		attachment.setFilePath(request.getServletContext().getRealPath(Constants.USER_FILES_PATH) + Constants.PATH_SEPARATOR + issueId + Constants.PATH_SEPARATOR);
		issueService.createAttachment(attachment, file);
		return "redirect:/Issues/" + issueId;
	}
	
	@RequestMapping(value = "/{id}/{attachmentId}")
	public void downloadFile(@PathVariable("id") long issueId, @PathVariable("attachmentId") long attachmentId, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		Attachment attachment = issueService.getProperty(Attachment.class, attachmentId);
		String filePath = attachment.getFilePath() + attachmentId;
	    String mimetype = request.getServletContext().getMimeType(filePath);
	    if(mimetype == null){
            mimetype = DEF_MIME_TYPE;
        }
		File file = new File(filePath);
		 
		response.setContentType(mimetype);
        response.setContentLength((int)file.length());
        response.setHeader("Content-Disposition","attachment; filename=\"" + attachment.getFileName() +"\"");
        
		FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
	}
	
	@RequestMapping(value = "/Builds/{id}", method = RequestMethod.GET)
	public @ResponseBody
	List<Build> getBuilds(@PathVariable("id") long projectId) {
		return projectService.getBuildsByProjectId(projectId);
	}
	
	@RequestMapping(value = "/Search", method = RequestMethod.GET)
	public String searchIssues(Model model) {
		model.addAttribute(new SearchIssue());
		model.addAttribute(issueService.getProperties(Status.class));
		model.addAttribute(issueService.getProperties(Type.class));
		model.addAttribute(issueService.getProperties(Priority.class));
		model.addAttribute(issueService.getProperties(Resolution.class));
		model.addAttribute(issueService.getProperties(Project.class));
		model.addAttribute(issueService.getProperties(User.class));
		return "SearchIssues";
	}
	
	@RequestMapping(value = "/Search", method = RequestMethod.POST)
	public String searchIssuesFromForm(@ModelAttribute("searchIssue") SearchIssue searchIssue, Model model) {
		model.addAttribute(issueService.getFoundIssuesList(searchIssue));
		model.addAttribute(issueService.getProperties(Status.class));
		model.addAttribute(issueService.getProperties(Type.class));
		model.addAttribute(issueService.getProperties(Priority.class));
		model.addAttribute(issueService.getProperties(Resolution.class));
		model.addAttribute(issueService.getProperties(Project.class));
		model.addAttribute(issueService.getProperties(User.class));
		return "SearchIssues";
	}
	
//	@RequestMapping(value = "/editSelfUser", method = RequestMethod.GET)
//	public String editSelfUser(Principal principal, Model model) {
//		User user = null;
//		if (principal != null) {
//			user = userService.getUserByEmail(principal.getName());
//		}
//		model.addAttribute(user);
//		return "EditSelfUser";
//	}
//	
//	@RequestMapping(value = "/editSelfUser", method = RequestMethod.POST)
//	public String editSelfUserFromForm(@Valid @ModelAttribute("user") User user, Errors errors, Principal principal, Model model) {
//		if (errors.hasErrors()) {
//			return "EditSelfUser";
//		}
//		User currentUser = null;
//		if (principal != null) {
//			LOG.info(principal);
//			currentUser = userService.getUserByEmail(principal.getName());
//		}
//		user.setId(currentUser.getId());
//		userService.update(user);
//		return "redirect:/logout";
//	}
	
	

//	@RequestMapping(value = "/Admin/Priorities/Add", method = RequestMethod.GET)
//	public String addPriority(Model model) {
//		model.addAttribute(new Priority());
//		return "AddPriority";
//	}
//
//	@RequestMapping(value = "/Admin/Priorities/Add", method = RequestMethod.POST)
//	public String addPriorityFromForm(@Valid @ModelAttribute("priority") Priority priority, Errors errors) {
//		if (errors.hasErrors()) {
//			return "AddPriority";
//		}
//		issueService.createProperty(priority);
//		return "redirect:/IssuesServlet";
//	}
//	
//	@RequestMapping(value = "/Admin/Priorities/List", method = RequestMethod.GET)
//	public String prioritiesList(Model model) {
//		model.addAttribute(issueService.getProperties(Priority.class));
//		return "PrioritiesList";
//	}
//	
//	@RequestMapping(value = "/Admin/Priorities/{id}", method = RequestMethod.GET)
//	public String editPriority(@PathVariable("id") long priorityId, Model model) {
//		model.addAttribute(issueService.getProperty(Priority.class, priorityId));
//		return "EditPriority";
//	}
//	
//	@RequestMapping(value = "/Admin/Priorities/{id}", method = RequestMethod.POST)
//	public String editPriorityFromForm(@Valid @ModelAttribute("priority") Priority priority, Errors errors) {
//		if (errors.hasErrors()) {
//			return "EditPriority";
//		}
//		issueService.updateProperty(priority);
//		return "redirect:/IssuesServlet";
//	}
//
//	@RequestMapping(value = "/Admin/Types/Add", method = RequestMethod.GET)
//	public String addType(Model model) {
//		model.addAttribute(new Type());
//		return "AddType";
//	}
//
//	@RequestMapping(value = "/Admin/Types/Add", method = RequestMethod.POST)
//	public String addTypeFromForm(@Valid @ModelAttribute("type") Type type, Errors errors) {
//		if (errors.hasErrors()) {
//			return "AddType";
//		}
//		issueService.createProperty(type);
//		return "redirect:/IssuesServlet";
//	}
//	@RequestMapping(value = "/Admin/Types/List", method = RequestMethod.GET)
//	public String typesList(Model model) {
//		model.addAttribute(issueService.getProperties(Type.class));
//		return "TypesList";
//	}
//	@RequestMapping(value = "/Admin/Types/{id}", method = RequestMethod.GET)
//	public String editType(@PathVariable("id") long typeId, Model model) {
//		model.addAttribute(issueService.getProperty(Type.class, typeId));
//		return "EditType";
//	}
//	
//	@RequestMapping(value = "/Admin/Types/{id}", method = RequestMethod.POST)
//	public String editTypeFromForm(@Valid @ModelAttribute("type") Type type, Errors errors) {
//		if (errors.hasErrors()) {
//			return "EditType";
//		}
//		issueService.updateProperty(type);
//		return "redirect:/IssuesServlet";
//	}
//	@RequestMapping(value = "/Admin/Resolutions/Add", method = RequestMethod.GET)
//	public String addResolution(Model model) {
//		model.addAttribute(new Resolution());
//		return "AddResolution";
//	}
//
//	@RequestMapping(value = "/Admin/Resolutions/Add", method = RequestMethod.POST)
//	public String addResolutionFromForm(@Valid @ModelAttribute("resolution") Resolution resolution, Errors errors) {
//		if (errors.hasErrors()) {
//			return "AddResolution";
//		}
//		issueService.createProperty(resolution);
//		return "redirect:/IssuesServlet";
//	}
//	@RequestMapping(value = "/Admin/Resolutions/List", method = RequestMethod.GET)
//	public String resolutionsList(Model model) {
//		model.addAttribute(issueService.getProperties(Resolution.class));
//		return "ResolutionsList";
//	}
//	@RequestMapping(value = "/Admin/Resolutions/{id}", method = RequestMethod.GET)
//	public String editResolution(@PathVariable("id") long resolutionId, Model model) {
//		model.addAttribute(issueService.getProperty(Resolution.class, resolutionId));
//		return "EditResolution";
//	}
//	
//	@RequestMapping(value = "/Admin/Resolutions/{id}", method = RequestMethod.POST)
//	public String editResolutionFromForm(@Valid @ModelAttribute("resolution") Resolution resolution, Errors errors) {
//		if (errors.hasErrors()) {
//			return "EditResolution";
//		}
//		issueService.updateProperty(resolution);
//		return "redirect:/IssuesServlet";
//	}
//	@RequestMapping(value = "/Admin/Statuses/List", method = RequestMethod.GET)
//	public String statusesList(Model model) {
//		model.addAttribute(issueService.getProperties(Status.class));
//		return "StatusesList";
//	}
//	@RequestMapping(value = "/Admin/Statuses/{id}", method = RequestMethod.GET)
//	public String editStatus(@PathVariable("id") long statusId, Model model) {
//		model.addAttribute(issueService.getProperty(Status.class, statusId));
//		return "EditStatus";
//	}
//	
//	@RequestMapping(value = "/Admin/Statuses/{id}", method = RequestMethod.POST)
//	public String editStatusFromForm(@Valid @ModelAttribute("status") Status status, Errors errors) {
//		if (errors.hasErrors()) {
//			return "EditStatus";
//		}
//		issueService.updateProperty(status);
//		return "redirect:/IssuesServlet";
//	}

//	@RequestMapping(value = "/Admin/Projects/Add", method = RequestMethod.GET)
//	public String addProject(Model model) {
//		model.addAttribute(new Project());
//		model.addAttribute(issueService.getProperties(User.class));
//		return "AddProject";
//	}
//
//	@RequestMapping(value = "/Admin/Projects/Add", method = RequestMethod.POST)
//	public String addProjectFromForm(@Valid @ModelAttribute("project") Project project, BindingResult result, Model model) {
//		if (result.hasErrors() || project.getBuilds().get(0).getName().length() == 0) {
//			model.addAttribute(issueService.getProperties(User.class));
//			return "AddProject";
//		}
//		projectService.create(project);
//		return "redirect:/IssuesServlet";
//	}
//	@RequestMapping(value = "/Admin/Projects/List", method = RequestMethod.GET)
//	public String projectsList(Model model) {
//		model.addAttribute(projectService.getAll());
//		return "ProjectsList";
//	}
//	@RequestMapping(value = "/Admin/Projects/{id}", method = RequestMethod.GET)
//	public String editProject(@PathVariable("id") long projectId, Model model) {
//		model.addAttribute(projectService.getById(projectId));
//		model.addAttribute(issueService.getProperties(User.class));
//		model.addAttribute(projectService.getBuildsByProjectId(projectId));
//		model.addAttribute(new Build());
//		return "EditProject";
//	}
//	
//	@RequestMapping(value = "/Admin/Projects/{id}", method = RequestMethod.POST)
//	public String editProjectFromForm(@Valid @ModelAttribute("project") Project project, Errors errors, Model model, @PathVariable("id") long projectId) {
//		LOG.debug("Saving project: " + project);
//		if (errors.hasErrors()) {
//			model.addAttribute(issueService.getProperties(User.class));
//			model.addAttribute(projectService.getBuildsByProjectId(projectId));
//			model.addAttribute(new Build());
//			return "EditProject";
//		}
//		projectService.update(project);
//		return "redirect:/IssuesServlet";
//	}
//	
//	
//	@RequestMapping(value = "/Admin/Projects/{id}/AddBuild", method = RequestMethod.POST)
//	public String addBuildFromForm(@Valid @ModelAttribute("build") Build build, Errors errors, @PathVariable("id") long projectId, Model model) {
//		LOG.debug("Saving build: " + build);
//		if (errors.hasErrors()) {
//			model.addAttribute(projectService.getById(projectId));
//			model.addAttribute(issueService.getProperties(User.class));
//			model.addAttribute(projectService.getBuildsByProjectId(projectId));
//			return "EditProject";
//		}
//		build.setId(0);
//		projectService.createBuild(build, projectId);
//		return "redirect:/Admin/Projects/{id}";
//	}
	
	
//	@RequestMapping(value = "/Admin/Users/Add", method = RequestMethod.GET)
//	public String addUser(Model model) {
//		model.addAttribute(new User());
//		model.addAttribute("roles", UserRoles.values());
//		return "AddUser";
//	}
//
//	@RequestMapping(value = "/Admin/Users/Add", method = RequestMethod.POST)
//	public String addUserFromForm(@Valid @ModelAttribute("user") User user, Errors errors, Model model) {
//		if (errors.hasErrors()) {
//			model.addAttribute("roles", UserRoles.values());
//			return "AddUser";
//		}
//		userService.create(user);
//		return "redirect:/IssuesServlet";
//	}
//	@RequestMapping(value = "/Admin/Users/{id}", method = RequestMethod.GET)
//	public String editUser(@PathVariable("id") long userId, Model model) {
//		model.addAttribute(userService.getById(userId));
//		model.addAttribute("roles", UserRoles.values());
//		return "EditUser";
//	}
//	@RequestMapping(value = "/Admin/Users/{id}", method = RequestMethod.POST)
//	public String editUserFromForm(SessionRegistryImpl sessionRegistry, @Valid @ModelAttribute("user") User user, Errors errors, Model model, @PathVariable("id") long userId) {
//		if (errors.hasErrors()) {
//			model.addAttribute("roles", UserRoles.values());
//			return "EditUser";
//		}
//		LOG.info(user);
////		List<Object> loggedUsers = sessionRegistry.getAllPrincipals();
////		for (Object principal : loggedUsers) {
////		    if(principal instanceof User) {
////		        final User loggedUser = (User) principal;
////		        if(user.getEmailAddress().equals(loggedUser.getEmailAddress())) {
////		            List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
////		            if(null != sessionsInfo && sessionsInfo.size() > 0) {
////		                for (SessionInformation sessionInformation : sessionsInfo) {
////		                    LOG.info("Exprire now :" + sessionInformation.getSessionId());
////		                    sessionInformation.expireNow();
////		                }
////		            }
////		        }
////		    }
////		}
//		user.setId(userId);
//		userService.update(user);
//		return "redirect:/IssuesServlet";
//	}
	
	
	
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
