package org.example.issuetracker.dao;

import java.util.List;
import java.util.Map;

import org.example.issuetracker.domain.GenericDomainObject;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.PaginationParams;
import org.example.issuetracker.domain.SearchIssue;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.User;

public interface IIssueDao extends IDao<Issue>{
	
	List<String> getProperties(String propName);

	Map<Integer, Status> getStatuses(int currentStatus);

	List<Status> getNewStatuses();

	List<Issue> getIssuesByUserId(User user, Integer count);

	List<Issue> getLastIssues(Integer count);

	List<Issue> getSortedIssuesList(User user, PaginationParams params);

	long getIssuesCount(User user);

	List<Issue> getFoundIssuesList(SearchIssue issue);

}
