package org.example.issuetracker.factories;

import org.example.issuetracker.model.dao.IIssueDao;
import org.example.issuetracker.model.dao.IProjectDao;
import org.example.issuetracker.model.dao.IUserDao;
import org.example.issuetracker.model.dao.jdbc.JdbcIssueDao;
import org.example.issuetracker.model.dao.jdbc.JdbcProjectDao;
import org.example.issuetracker.model.dao.jdbc.JdbcUserDao;
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
