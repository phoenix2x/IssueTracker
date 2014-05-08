package com.epam.issuetracker.interfaces;

import java.util.List;

import com.epam.issuetracker.beans.Issue;
import com.epam.issuetracker.exceptions.DAOException;

public interface IIssueDAO {

	List<Issue> getIssuesByUserId(long userId, int numberIssues) throws DAOException;

	List<Issue> getLastIssues(int numberIssues) throws DAOException;

	void addIssue(Issue issue) throws DAOException;

	void updateIssue(Issue issue) throws DAOException;
}
