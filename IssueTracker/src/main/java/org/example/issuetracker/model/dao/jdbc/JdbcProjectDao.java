package org.example.issuetracker.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.issuetracker.constants.SqlConstants;
import org.example.issuetracker.factories.DAOFactory;
import org.example.issuetracker.model.beans.Project;
import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IProjectDao;
import org.example.issuetracker.model.dao.jdbc.connections.ConnectionManager;
import org.example.issuetracker.model.exceptions.DAOException;

public class JdbcProjectDao implements IProjectDao {
	private static JdbcProjectDao instance;

	/**
	 * 
	 */
	private JdbcProjectDao() {
	}

	public synchronized static JdbcProjectDao getImpl() throws DAOException {
		if (instance == null) {
			instance = new JdbcProjectDao();
		}
		return instance;
	}

	@Override
	public Project getElementById(long id) throws DAOException {
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_PROJECT_BY_ID)) {
			ps.setLong(SqlConstants.SELECT_PROJECT_BY_ID_INDEX, id);
			return parseProject(ps);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	private Project parseProject(PreparedStatement ps) throws SQLException, DAOException {
		try (ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				long id = rs.getLong(SqlConstants.SELECT_PROJECT_RET_ID_INDEX);
				String name = rs.getString(SqlConstants.SELECT_PROJECT_RET_NAME_INDEX);
				String description = rs.getString(SqlConstants.SELECT_PROJECT_RET_DESCRIPTION_INDEX);
				long managerId = rs.getLong(SqlConstants.SELECT_PROJECT_RET_MANAGER_INDEX);
				User manager = DAOFactory.getUserDAOFromFactory().getElementById(managerId);
				List<String> builds = getBuildByProjectId(id);
				return new Project(id, name, description, builds, manager);
			} else {
				return null;
			}
		}
	}

	private List<String> getBuildByProjectId(long projectId) throws DAOException {
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_BUILD_BY_PROJECT_ID)) {
			ps.setLong(SqlConstants.SELECT_BUILDS_BY_PROJECT_ID_INDEX, projectId);
			return parseBuilds(ps);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	private List<String> parseBuilds(PreparedStatement ps) throws SQLException {
		List<String> builds = new ArrayList<>();
		try (ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				String name = rs.getString(SqlConstants.SELECT_BUILDS_RET_NAME_INDEX);
				builds.add(name);
			}
			return builds;
		}
	}

	@Override
	public List<Project> getAllElements() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}
