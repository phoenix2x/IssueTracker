package com.epam.issuetracker.factories;

import com.epam.issuetracker.exceptions.DAOException;
import com.epam.issuetracker.impl.XMLIssueDAO;
import com.epam.issuetracker.impl.XMLUserDao;
import com.epam.issuetracker.interfaces.IIssueDAO;
import com.epam.issuetracker.interfaces.IUserDAO;

public class DAOFactory {
	public static IUserDAO getUserDAOFromFactory() throws DAOException {
		return XMLUserDao.getImpl();
	}
	public static IIssueDAO getIssueDAOFromFactory() throws DAOException {
		return XMLIssueDAO.getImpl();
	}
}
