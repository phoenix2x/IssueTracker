package org.example.issuetracker.dao.factories;

import org.example.issuetracker.dao.IIssueDao;
import org.example.issuetracker.dao.IProjectDao;
import org.example.issuetracker.dao.IUserDao;
import org.example.issuetracker.dao.jdbc.JdbcIssueDao;
import org.example.issuetracker.dao.jdbc.JdbcProjectDao;
import org.example.issuetracker.dao.jdbc.JdbcUserDao;
import org.example.issuetracker.model.exceptions.DAOException;

public class DAOFactory {
	public static IUserDao getUserDAOFromFactory() throws DAOException {
		return JdbcUserDao.getImpl();
//		return XmlUserDao.getImpl();
	}
	public static IIssueDao getIssueDAOFromFactory() throws DAOException {
		return JdbcIssueDao.getImpl();
//		return XmlIssueDAO.getImpl();
	}
	public static IProjectDao getProjectDaoFromFactory() throws DAOException {
		return JdbcProjectDao.getImpl();
	}
}
