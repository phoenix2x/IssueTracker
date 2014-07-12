package org.example.issuetracker.dao.xml;

import java.util.List;
import java.util.Map;

import org.example.issuetracker.dao.IUserDao;
import org.example.issuetracker.domain.GenericDomainObject;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.model.exceptions.DAOException;

public class XmlUserDao implements IUserDao {
//	private static final String FILE_NAME = "/users.xml";
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

//	@Override
//	public User getUser(String emailAddress, String password) throws DAOException {
//		User user = users.get(emailAddress);
//		if (user != null && user.getPassword().equals(password)) {
//			return user;
//		} else {
//			return null;
//		}
//	}

	@Override
	public User getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getAllCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends GenericDomainObject> List<T> getProperties(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends GenericDomainObject> T getProperty(Class<T> clazz, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends GenericDomainObject> void createProperty(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends GenericDomainObject> void updateProperty(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(String emailAddress, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByEmail(String emailAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getFoundUsersList(User user) {
		// TODO Auto-generated method stub
		return null;
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

//	@Override
//	public void addUser(User user) throws DAOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void updateUser(User user) throws DAOException {
//		// TODO Auto-generated method stub
//
//	}
//
////	private void parse() throws DAOException {
////		try {
////			XMLReader reader = XMLReaderFactory.createXMLReader();
////			UserHandler handler = new UserHandler();
////			reader.setContentHandler(handler);
////			reader.parse(new InputSource(getClass().getResourceAsStream(FILE_NAME)));
////			users = handler.getUsers();
////		} catch (Exception e) {
////			throw new DAOException(e);
////		}
////	}
//
//	@Override
//	public User getById(long id) throws DAOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<User> getAll() throws DAOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long getNumber() throws DAOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}
