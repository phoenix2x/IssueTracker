package org.example.issuetracker.service;

import java.io.Serializable;
import java.util.List;

import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.dao.IIssueDao;
import org.example.issuetracker.dao.persistence.AbstractJpaDAO;
import org.example.issuetracker.dao.persistence.PIssueDao;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService extends AbstractGenericService<Issue> implements IIssueService{
	
	@Autowired
	private IIssueDao issueDao;
	
	

	/**
	 * @param abstractJpaDAOtoSet
	 */
	private IssueService() {
		super(new PIssueDao());
	}

	@Override
	public List<Issue> getIssuesList(User user) {
		if (user.equals(Constants.GUEST_USER)) {
			return issueDao.getAll();
		} else {
			return issueDao.getIssuesByUserId(user);
		}
	}

	@Override
	public <T extends Serializable> List<T> getProperties(Class<T> clazz) {
		return issueDao.getProperties(clazz);
	}
}
