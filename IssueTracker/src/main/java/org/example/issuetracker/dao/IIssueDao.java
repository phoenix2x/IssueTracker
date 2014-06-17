package org.example.issuetracker.dao;

import java.util.List;
import java.util.Map;

import org.example.issuetracker.domain.GenericDomainObject;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.IssuePaginationParams;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.User;

public interface IIssueDao extends IDao<Issue>{
	
	List<String> getProperties(String propName);

	Map<Integer, Status> getStatuses(int currentStatus);

	List<Status> getNewStatuses();

	List<Issue> getIssuesByUserId(User user);

	List<Issue> getLastIssues();

	<T extends GenericDomainObject> List<T> getProperties(Class<T> clazz);
	
	<T extends GenericDomainObject> void createProperty(T entity);

	List<Issue> getSortedIssuesList(IssuePaginationParams params);
}
