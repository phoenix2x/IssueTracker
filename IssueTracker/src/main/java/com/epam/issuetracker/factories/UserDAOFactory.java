package com.epam.issuetracker.factories;

import com.epam.issuetracker.exceptions.DAOException;
import com.epam.issuetracker.impl.XMLUserDao;
import com.epam.issuetracker.interfaces.IUserDAO;

public class UserDAOFactory {
	public static IUserDAO getClassFromFactory() throws DAOException {
		return XMLUserDao.getImpl();
	}
}
