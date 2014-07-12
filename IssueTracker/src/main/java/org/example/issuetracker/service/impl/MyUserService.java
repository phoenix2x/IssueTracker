package org.example.issuetracker.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.example.issuetracker.dao.IDao;
import org.example.issuetracker.dao.IUserDao;
import org.example.issuetracker.domain.Issue;
import org.example.issuetracker.domain.SearchIssue;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyUserService extends AbstractGenericService<User> implements IUserService {
	
	@Inject
	private IUserDao userDao;


	@Override
	protected IDao<User> getDao() {
		return userDao;
	}

	@Override
	@Transactional(readOnly=true)
	public User getUserByEmail(String emailAddress) {
		return userDao.getUserByEmail(emailAddress);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<User> getFoundUsersList(User user) {
		return userDao.getFoundUsersList(user);
	}

}
