package org.example.issuetracker.dao.jdbc;

import java.util.List;

import org.example.issuetracker.dao.IUserDao;
import org.example.issuetracker.domain.GenericDomainObject;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.model.exceptions.DAOException;

public class JdbcUserDao implements IUserDao {
	private static JdbcUserDao instance;

	/**
	 * 
	 */
	private JdbcUserDao() {
	}

	public synchronized static JdbcUserDao getImpl() throws DAOException {
		if (instance == null) {
			instance = new JdbcUserDao();
		}
		return instance;
	}

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
//	public User getUser(String emailAddress, String password) throws DAOException {
//		try (Connection cn = ConnectionManager.getConnection();
//				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_USER_BY_LOGIN)) {
//			ps.setString(SqlConstants.SELECT_USER_EMAIL_INDEX, emailAddress);
//			User user = parseUser(ps); 
//			if (user != null && user.getPassword().equals(password)) {
//				return user;
//			} else {
//				return null;
//			}
//		} catch (SQLException e) {
//			throw new DAOException(e);
//		}
//	}
//
//	@Override
//	public User getById(long id) throws DAOException {
//		try (Connection cn = ConnectionManager.getConnection();
//				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_USER_BY_ID)) {
//			ps.setLong(SqlConstants.SELECT_USER_BY_ID_INDEX, id);
//			return parseUser(ps);
//		} catch (SQLException e) {
//			throw new DAOException(e);
//		}
//	}
//
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
//	@Override
//	public List<User> getAll() throws DAOException {
//		try (Connection cn = ConnectionManager.getConnection();
//				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_ALL_USERS)) {
//			return parseUsers(ps);
//		} catch (SQLException e) {
//			throw new DAOException(e);
//		}
//	}
//	
//	private User parseUser(PreparedStatement ps) throws SQLException {
//		try (ResultSet rs = ps.executeQuery()) {
//			if (rs.next()) {
//				long id = rs.getLong(SqlConstants.SELECT_USER_RET_ID);
//				String emailAddress = rs.getString(SqlConstants.SELECT_USER_RET_EMAIL);
//				String firstName = rs.getString(SqlConstants.SELECT_USER_RET_FIRST_NAME);
//				String lastName = rs.getString(SqlConstants.SELECT_USER_RET_LAST_NAME);
//				String password = rs.getString(SqlConstants.SELECT_USER_RET_PASSWORD);
//				String userRole = rs.getString(SqlConstants.SELECT_USER_RET_ROLE);
//				return new User(id, emailAddress, firstName, lastName, password, userRole);
//			} else {
//				return null;
//			}
//		}
//	}
//	
//	private List<User> parseUsers(PreparedStatement ps) throws SQLException {
//		List<User> usersList = new ArrayList<>();
//		try (ResultSet rs = ps.executeQuery()) {
//			while (rs.next()) {
//				long id = rs.getLong(SqlConstants.SELECT_USER_RET_ID);
//				String emailAddress = rs.getString(SqlConstants.SELECT_USER_RET_EMAIL);
//				String firstName = rs.getString(SqlConstants.SELECT_USER_RET_FIRST_NAME);
//				String lastName = rs.getString(SqlConstants.SELECT_USER_RET_LAST_NAME);
//				String password = rs.getString(SqlConstants.SELECT_USER_RET_PASSWORD);
//				String userRole = rs.getString(SqlConstants.SELECT_USER_RET_ROLE);
//				usersList.add(new User(id, emailAddress, firstName, lastName, password, userRole));
//			}
//			return usersList;
//		}
//	}
//
//	@Override
//	public long getNumber() throws DAOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}
