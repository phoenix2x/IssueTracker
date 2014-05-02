package com.epam.issuetracker.impl;

import java.util.Map;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.issuetracker.analyzer.sax.UserHandler;
import com.epam.issuetracker.beans.User;
import com.epam.issuetracker.exceptions.DAOException;
import com.epam.issuetracker.interfaces.IUserDAO;

public class XMLUserDao implements IUserDAO {
	private static final String FILE_NAME = "/users.xml";
	private static XMLUserDao instance;
	private Map<String, User> users;
	public synchronized static XMLUserDao getImpl() throws DAOException {
		if (instance == null) {
			instance = new XMLUserDao();
		}
		return instance;
	}
	public XMLUserDao() throws DAOException {
		parse();
	}
	private void parse() throws DAOException {
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			UserHandler handler = new UserHandler();
			reader.setContentHandler(handler);
			reader.parse(new InputSource(getClass().getResourceAsStream(FILE_NAME)));
			users = handler.getUsers();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	@Override
	public User getUser(String loginName, String password) throws DAOException{
		User user = users.get(loginName);
		if (user != null && user.getPassword().equals(password)){
			return user;
		} else {
			return null;
		}
	}
}
