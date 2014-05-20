package org.example.issuetracker.model.dao;

import java.util.List;

import org.example.issuetracker.model.beans.Issue;
import org.example.issuetracker.model.beans.Status;
import org.example.issuetracker.model.exceptions.DAOException;

public interface IIssueDao extends IDao<Issue>{

	List<Issue> getIssuesByUserId(long userId, int numberIssues) throws DAOException;

	List<Issue> getLastIssues(int numberIssues) throws DAOException;

	boolean addIssue(Issue issue) throws DAOException;

	boolean updateIssue(Issue issue) throws DAOException;
	
	List<Status> getStatuses(int...id) throws DAOException;
	
	List<String> getProperties(String propName) throws DAOException;
	
}
