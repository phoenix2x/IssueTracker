package org.example.issuetracker.model.dao.xml;

import java.util.Collection;
import java.util.Map;

import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IUserDao;
import org.example.issuetracker.model.exceptions.DAOException;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlUserDao implements IUserDao {
	private static final String FILE_NAME = "/users.xml";
	private static XmlUserDao instance;
	private Map<String, User> users;

	public synchronized static XmlUserDao getImpl() throws DAOException {
		if (instance == null) {
			instance = new XmlUserDao();
		}
		return instance;
	}

	private XmlUserDao() throws DAOException {
//		parse();
	}

	@Override
	public User getUser(String emailAddress, String password) throws DAOException {
		User user = users.get(emailAddress);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

//	@Override
//	public User getElementByID(Long id) throws DAOException {
//		Collection<User> userCollection = users.values();
//		for (User user : userCollection) {
//			if (user.getId() == id) {
//				return user;
//			}
//		}
//		return null;
//	}

	@Override
	public void addUser(User user) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(User user) throws DAOException {
		// TODO Auto-generated method stub

	}

//	private void parse() throws DAOException {
//		try {
//			XMLReader reader = XMLReaderFactory.createXMLReader();
//			UserHandler handler = new UserHandler();
//			reader.setContentHandler(handler);
//			reader.parse(new InputSource(getClass().getResourceAsStream(FILE_NAME)));
//			users = handler.getUsers();
//		} catch (Exception e) {
//			throw new DAOException(e);
//		}
//	}

	@Override
	public User getElementById(long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}
