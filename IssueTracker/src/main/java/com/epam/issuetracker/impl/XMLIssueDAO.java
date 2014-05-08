package com.epam.issuetracker.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.issuetracker.analyzer.sax.IssuesHandler;
import com.epam.issuetracker.beans.Issue;
import com.epam.issuetracker.exceptions.DAOException;
import com.epam.issuetracker.interfaces.IIssueDAO;

public class XMLIssueDAO implements IIssueDAO {
	private static final String FILE_NAME = "/issues.xml";
	private static XMLIssueDAO instance;
	private Map<Long, Issue> issues;

	public synchronized static XMLIssueDAO getImpl() throws DAOException {
		if (instance == null) {
			instance = new XMLIssueDAO();
		}
		return instance;
	}

	/**
	 * @throws DAOException
	 * 
	 */
	public XMLIssueDAO() throws DAOException {
		parse();
	}

	@Override
	public List<Issue> getIssuesByUserId(long userId, int numberIssues) throws DAOException {
		Collection<Issue> issuesCollection = issues.values();
		List<Issue> issueList = new ArrayList<>();
		for (Issue issue : issuesCollection) {
			if (issue.getAssignee() == userId) {
				issueList.add(issue);
				if (issueList.size() == numberIssues) {
					return issueList;
				}
			}
		}
		return issueList;
	}

	@Override
	public List<Issue> getLastIssues(int numberIssues) throws DAOException {
		Collection<Issue> issuesCollection = issues.values();
		List<Issue> issueList = new ArrayList<>();
		for (Issue issue : issuesCollection) {
			issueList.add(issue);
			if (issueList.size() == numberIssues) {
				return issueList;
			}
		}
		return issueList;
	}

	@Override
	public void addIssue(Issue issue) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateIssue(Issue issue) throws DAOException {
		// TODO Auto-generated method stub

	}

	private void parse() throws DAOException {
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			IssuesHandler handler = new IssuesHandler();
			reader.setContentHandler(handler);
			reader.parse(new InputSource(getClass().getResourceAsStream(FILE_NAME)));
			issues = handler.getIssues();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
}
