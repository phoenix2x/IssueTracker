package org.example.issuetracker.service;

import java.io.Serializable;
import java.util.List;

import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.User;

public interface IIssueService extends IGenericService<Issue>{
	
	List<Issue> getIssuesList(User user);
	
	<T extends Serializable> List<T> getProperties(Class<T> clazz);
}
