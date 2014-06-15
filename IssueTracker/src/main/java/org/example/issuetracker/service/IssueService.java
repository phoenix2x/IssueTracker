package org.example.issuetracker.service;

import java.util.List;

import org.example.issuetracker.dao.IDao;
import org.example.issuetracker.dao.IIssueDao;
import org.example.issuetracker.domain.GenericDomainObject;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IssueService extends AbstractGenericService<Issue> implements IIssueService{
	
	@Autowired
	private IIssueDao issueDao;
	
	/**
	 * @param abstractJpaDAOtoSet
	 */
	private IssueService() {
		super();
	}

	@Override
	protected IDao<Issue> getDao() {
		return issueDao;
	}
	
	@Override
	public List<Issue> getIssuesList(User user) {
		if (user == null) {
			return issueDao.getAll();
		} else {
			return issueDao.getIssuesByUserId(user);
		}
	}
	
	@Override
	public List<Status> getNewStatuses() {
		return issueDao.getProperties(Status.class).subList(0, 2);
	}
	
	@Override
	@Transactional
	public <T extends GenericDomainObject> List<T> getProperties(Class<T> clazz) {
		return issueDao.getProperties(clazz);
	}
	
	@Override
	@Transactional
	public <T extends GenericDomainObject> void addProperty(Class<T> clazz) {
		issueDao.getProperties(clazz);
	}

}
