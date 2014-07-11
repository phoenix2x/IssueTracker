package org.example.issuetracker.web.servlets;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.IssuePaginationParams;
import org.example.issuetracker.domain.PaginationParams;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.service.IIssueService;
import org.example.issuetracker.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootServlet {
	
	private static final Logger LOG = Logger.getLogger(RootServlet.class);

	@Inject
	private IIssueService issueService;
	
	@Inject
	private IUserService userService;
	
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
		model.addAttribute("pages", issueService.getIssuesPages(user));
		return "Issues";
	}
	
	@RequestMapping(value = "/IssuesPaginator", method = RequestMethod.GET)
	public @ResponseBody List<Issue> getIssues(Principal principal, @RequestParam("orderby") Integer orderBy, @RequestParam("order") Integer order, @RequestParam("page") Integer page) {
		User user = null;
		if (principal != null) {
			user = userService.getUserByEmail(principal.getName());
		}
		IssuePaginationParams params = new IssuePaginationParams(page, orderBy, order);
		return issueService.getSortedIssuesList(user, params);
	}
}
