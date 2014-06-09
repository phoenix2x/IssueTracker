package org.example.issuetracker.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.issuetracker.constants.SqlConstants;
import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IUserDao;
import org.example.issuetracker.model.dao.jdbc.connections.ConnectionManager;
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
	public User getUser(String emailAddress, String password) throws DAOException {
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_USER_BY_LOGIN)) {
			ps.setString(SqlConstants.SELECT_USER_EMAIL_INDEX, emailAddress);
			User user = parseUser(ps); 
			if (user != null && user.getPassword().equals(password)) {
				return user;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public User getById(long id) throws DAOException {
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_USER_BY_ID)) {
			ps.setLong(SqlConstants.SELECT_USER_BY_ID_INDEX, id);
			return parseUser(ps);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void addUser(User user) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(User user) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getAll() throws DAOException {
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_ALL_USERS)) {
			return parseUsers(ps);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	private User parseUser(PreparedStatement ps) throws SQLException {
		try (ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				long id = rs.getLong(SqlConstants.SELECT_USER_RET_ID);
				String emailAddress = rs.getString(SqlConstants.SELECT_USER_RET_EMAIL);
				String firstName = rs.getString(SqlConstants.SELECT_USER_RET_FIRST_NAME);
				String lastName = rs.getString(SqlConstants.SELECT_USER_RET_LAST_NAME);
				String password = rs.getString(SqlConstants.SELECT_USER_RET_PASSWORD);
				String userRole = rs.getString(SqlConstants.SELECT_USER_RET_ROLE);
				return new User(id, emailAddress, firstName, lastName, password, userRole);
			} else {
				return null;
			}
		}
	}
	
	private List<User> parseUsers(PreparedStatement ps) throws SQLException {
		List<User> usersList = new ArrayList<>();
		try (ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				long id = rs.getLong(SqlConstants.SELECT_USER_RET_ID);
				String emailAddress = rs.getString(SqlConstants.SELECT_USER_RET_EMAIL);
				String firstName = rs.getString(SqlConstants.SELECT_USER_RET_FIRST_NAME);
				String lastName = rs.getString(SqlConstants.SELECT_USER_RET_LAST_NAME);
				String password = rs.getString(SqlConstants.SELECT_USER_RET_PASSWORD);
				String userRole = rs.getString(SqlConstants.SELECT_USER_RET_ROLE);
				usersList.add(new User(id, emailAddress, firstName, lastName, password, userRole));
			}
			return usersList;
		}
	}

	@Override
	public long getNumber() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}
}
