package org.example.issuetracker.model.dao;

import java.util.List;
import java.util.Map;

import org.example.issuetracker.model.beans.Issue;
import org.example.issuetracker.model.beans.Status;
import org.example.issuetracker.model.exceptions.DAOException;

public interface IIssueDao extends IDao<Issue>{

	boolean addIssue(Issue issue) throws DAOException;

	boolean updateIssue(Issue issue) throws DAOException;
	
	List<String> getProperties(String propName) throws DAOException;

	Map<Integer, Status> getStatuses(int currentStatus) throws DAOException;

	List<Status> getNewStatuses() throws DAOException;

	List<Issue> getLastIssues(int numberIssues, int offset) throws DAOException;

	List<Issue> getIssuesByUserId(long userId, int numberIssues, int offset) throws DAOException;
	
	long getElementNumber(long assigneeId) throws DAOException;
}
