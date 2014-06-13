package org.example.issuetracker.service.impl;

import javax.inject.Inject;

import org.example.issuetracker.dao.IDao;
import org.example.issuetracker.dao.IUserDao;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyUserService extends AbstractGenericService<User> implements IUserService {
	
	@Inject
	private IUserDao userDao;

	@Override
	@Transactional
	public User getUserByEmail(String emailAddress) {
		return userDao.getUserByEmail(emailAddress);
	}

	@Override
	protected IDao<User> getDao() {
		return userDao;
	}


}
