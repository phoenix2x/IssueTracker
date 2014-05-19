package org.example.issuetracker.model.dao;

import java.util.List;

import org.example.issuetracker.model.beans.Issue;
import org.example.issuetracker.model.exceptions.DAOException;

public interface IIssueDao extends IDao<Issue>{

	List<Issue> getIssuesByUserId(long userId, int numberIssues) throws DAOException;

	List<Issue> getLastIssues(int numberIssues) throws DAOException;

	void addIssue(Issue issue) throws DAOException;

	void updateIssue(Issue issue) throws DAOException;
	
	List<String> getProperties(String propName) throws DAOException;
	
	boolean addIssue(String summary, String description, String status, String type, String priority, long projectId, String build, long assigneeId, long createdBy) throws DAOException;
}
