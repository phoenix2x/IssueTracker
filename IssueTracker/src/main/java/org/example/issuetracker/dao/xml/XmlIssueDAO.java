package org.example.issuetracker.dao.xml;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.example.issuetracker.dao.IIssueDao;
import org.example.issuetracker.domain.GenericDomainObject;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.PaginationParams;
import org.example.issuetracker.domain.SearchIssue;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.model.exceptions.DAOException;

public class XmlIssueDAO implements IIssueDao {
//	private static final String FILE_NAME = "/issues.xml";
	private static XmlIssueDAO instance;
//	private Map<Long, Issue> issues;

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
	public Issue getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Issue> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Issue entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Issue update(Issue entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Issue entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getAllCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getProperties(String propName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, Status> getStatuses(int currentStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Status> getNewStatuses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends GenericDomainObject> List<T> getProperties(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends GenericDomainObject> T getProperty(Class<T> clazz, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends GenericDomainObject> void createProperty(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends GenericDomainObject> void updateProperty(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Issue> getIssuesByUserId(User user, Integer count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Issue> getLastIssues(Integer count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Issue> getSortedIssuesList(User user, PaginationParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getIssuesCount(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Issue> getFoundIssuesList(SearchIssue issue) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Issue> getIssuesByUserId(long userId, int numberIssues) throws DAOException {
//		Collection<Issue> issuesCollection = issues.values();
//		List<Issue> issueList = new ArrayList<>();
//		for (Issue issue : issuesCollection) {
////			if (issue.getAssignee() == userId) {
////				issueList.add(issue);
////				if (issueList.size() == numberIssues) {
////					return issueList;
////				}
////			}
//		}
//		return issueList;
//	}
//
//	@Override
//	public List<Issue> getLastIssues(int numberIssues) throws DAOException {
//		Collection<Issue> issuesCollection = issues.values();
//		List<Issue> issueList = new ArrayList<>();
//		for (Issue issue : issuesCollection) {
//			issueList.add(issue);
//			if (issueList.size() == numberIssues) {
//				return issueList;
//			}
//		}
//		return issueList;
//	}

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

//	@Override
//	public Issue getById(long id) throws DAOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Issue> getAll() throws DAOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<String> getProperties(String propName) throws DAOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Map<Integer, Status> getStatuses(int currentStatus) throws DAOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Status> getNewStatuses() throws DAOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean addIssue(Issue issue) throws DAOException {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean updateIssue(Issue issue) throws DAOException {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public long getAllCount() throws DAOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public long getElementNumber(long assigneeId) throws DAOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<Issue> getIssuesByUserId(long userId, int numberIssues, int offset, int orderBy, int order)
//			throws DAOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Issue> getLastIssues(int numberIssues, int offset, int orderBy, int order) throws DAOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
