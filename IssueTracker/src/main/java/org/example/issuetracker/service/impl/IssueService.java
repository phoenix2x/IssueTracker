package org.example.issuetracker.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.example.issuetracker.dao.IDao;
import org.example.issuetracker.dao.IIssueDao;
import org.example.issuetracker.domain.GenericDomainObject;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.Status;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.service.IIssueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IssueService extends AbstractGenericService<Issue> implements IIssueService{
	
	@Inject
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
	@Transactional
	public List<Issue> getIssuesList(User user) {
		if (user == null) {
			return issueDao.getAll();
		} else {
			return issueDao.getIssuesByUserId(user);
		}
	}
	
	@Override
	@Transactional
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
