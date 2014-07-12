package org.example.issuetracker.web.servlets;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.example.issuetracker.domain.Priority;
import org.example.issuetracker.domain.Project;
import org.example.issuetracker.domain.Resolution;
import org.example.issuetracker.domain.SearchIssue;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.Type;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.model.enums.UserRoles;
import org.example.issuetracker.service.IUserService;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/Admin/Users")
public class UsersServlet {
	
	private static final Logger LOG = Logger.getLogger(UsersServlet.class);
	
	@Inject
	private IUserService userService;
	
	@RequestMapping(value = "/Add", method = RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute(new User());
		model.addAttribute("roles", UserRoles.values());
		return "AddUser";
	}

	@RequestMapping(value = "/Add", method = RequestMethod.POST)
	public String addUserFromForm(@Valid @ModelAttribute("user") User user, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("roles", UserRoles.values());
			return "AddUser";
		}
		userService.create(user);
		return "redirect:/Issues";
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable("id") long userId, Model model) {
		model.addAttribute(userService.getById(userId));
		model.addAttribute("roles", UserRoles.values());
		return "EditUser";
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String editUserFromForm(SessionRegistryImpl sessionRegistry, @Valid @ModelAttribute("user") User user, Errors errors, Model model, @PathVariable("id") long userId) {
		if (errors.hasErrors()) {
			model.addAttribute("roles", UserRoles.values());
			return "EditUser";
		}
		LOG.info(user);
//		List<Object> loggedUsers = sessionRegistry.getAllPrincipals();
//		for (Object principal : loggedUsers) {
//		    if(principal instanceof User) {
//		        final User loggedUser = (User) principal;
//		        if(user.getEmailAddress().equals(loggedUser.getEmailAddress())) {
//		            List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
//		            if(null != sessionsInfo && sessionsInfo.size() > 0) {
//		                for (SessionInformation sessionInformation : sessionsInfo) {
//		                    LOG.info("Exprire now :" + sessionInformation.getSessionId());
//		                    sessionInformation.expireNow();
//		                }
//		            }
//		        }
//		    }
//		}
		user.setId(userId);
		userService.update(user);
		return "redirect:/Issues";
	}
	@RequestMapping(value = "/Search", method = RequestMethod.GET)
	public String searchIssues(Model model) {
		model.addAttribute(new User());
		return "SearchUsers";
	}
	
	@RequestMapping(value = "/Search", method = RequestMethod.POST)
	public String searchIssuesFromForm(@ModelAttribute("user") User user, Model model) {
		model.addAttribute(userService.getFoundUsersList(user));
		return "SearchUsers";
	}
}
