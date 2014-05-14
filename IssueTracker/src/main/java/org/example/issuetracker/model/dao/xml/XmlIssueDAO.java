package org.example.issuetracker.model.dao.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.example.issuetracker.model.beans.Issue;
import org.example.issuetracker.model.dao.IIssueDao;
import org.example.issuetracker.model.exceptions.DAOException;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlIssueDAO implements IIssueDao {
	private static final String FILE_NAME = "/issues.xml";
	private static XmlIssueDAO instance;
	private Map<Long, Issue> issues;

	public synchronized static XmlIssueDAO getImpl() throws DAOException {
		if (instance == null) {
			instance = new XmlIssueDAO();
		}
		return instance;
	}

	/**
	 * @throws DAOException
	 * 
	 */
	private XmlIssueDAO() throws DAOException {
//		parse();
	}

	@Override
	public List<Issue> getIssuesByUserId(long userId, int numberIssues) throws DAOException {
		Collection<Issue> issuesCollection = issues.values();
		List<Issue> issueList = new ArrayList<>();
		for (Issue issue : issuesCollection) {
//			if (issue.getAssignee() == userId) {
//				issueList.add(issue);
//				if (issueList.size() == numberIssues) {
//					return issueList;
//				}
//			}
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

//	private void parse() throws DAOException {
//		try {
//			XMLReader reader = XMLReaderFactory.createXMLReader();
//			IssuesHandler handler = new IssuesHandler();
//			reader.setContentHandler(handler);
//			reader.parse(new InputSource(getClass().getResourceAsStream(FILE_NAME)));
//			issues = handler.getIssues();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new DAOException(e);
//		}
//	}

	@Override
	public Issue getElementById(long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}
