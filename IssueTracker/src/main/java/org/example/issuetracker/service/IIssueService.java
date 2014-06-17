package org.example.issuetracker.service;

import java.util.List;

import org.example.issuetracker.domain.GenericDomainObject;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.IssuePaginationParams;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.User;

public interface IIssueService extends IGenericService<Issue>{
	
	List<Issue> getIssuesList(User user);
	
	<T extends GenericDomainObject> List<T> getProperties(Class<T> clazz);
	
	<T extends GenericDomainObject> void createProperty(T entity);

	List<Status> getNewStatuses();

	List<Issue> getSortedIssuesList(IssuePaginationParams params);
}
