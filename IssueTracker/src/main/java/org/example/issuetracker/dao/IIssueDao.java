package org.example.issuetracker.dao;

import java.util.List;
import java.util.Map;

import org.example.issuetracker.domain.GenericDomainObject;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.User;

public interface IIssueDao extends IDao<Issue>{
	
	List<String> getProperties(String propName);

	Map<Integer, Status> getStatuses(int currentStatus);

	List<Status> getNewStatuses();

	List<Issue> getIssuesByUserId(User user);

	List<Issue> getLastIssues(int numberIssues, int offset, int orderBy, int order);

	<T extends GenericDomainObject> List<T> getProperties(Class<T> clazz);

	<T extends GenericDomainObject> void addProperty(Class<T> clazz);
}
