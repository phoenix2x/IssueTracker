package org.example.issuetracker.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.issuetracker.constants.SqlConstants;
import org.example.issuetracker.dao.IProjectDao;
import org.example.issuetracker.dao.factories.DAOFactory;
import org.example.issuetracker.dao.jdbc.connections.ConnectionManager;
import org.example.issuetracker.domain.Build;
import org.example.issuetracker.domain.Project;
import org.example.issuetracker.domain.User;
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
	public Project getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Project entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Project update(Project entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Project entity) {
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
	public List<Build> getBuildsByProjectId(long projectId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Project getById(long id) throws DAOException {
//		try (Connection cn = ConnectionManager.getConnection();
//				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_PROJECT_BY_ID)) {
//			ps.setLong(SqlConstants.SELECT_PROJECT_BY_ID_INDEX, id);
//			List<Project> projects = parseProjects(ps);
//			if (projects.size() == 0) {
//				return null;
//			} else {
//				return projects.get(0);
//			}
//		} catch (SQLException e) {
//			throw new DAOException(e);
//		}
//	}
//
//	public List<Build> getBuildsByProjectId(long projectId) throws DAOException {
//		try (Connection cn = ConnectionManager.getConnection();
//				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_BUILD_BY_PROJECT_ID)) {
//			ps.setLong(SqlConstants.SELECT_BUILDS_BY_PROJECT_ID_INDEX, projectId);
//			return parseBuilds(ps);
//		} catch (SQLException e) {
//			throw new DAOException(e);
//		}
//	}
//
//	@Override
//	public List<Project> getAll() throws DAOException {
//		try (Connection cn = ConnectionManager.getConnection();
//				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_ALL_PROJECTS)) {
//			return parseProjects(ps);
//		} catch (SQLException e) {
//			throw new DAOException(e);
//		}
//	}
//	
//	private List<Project> parseProjects(PreparedStatement ps) throws SQLException, DAOException {
//		List<Project> projects = new ArrayList<>();
//		try (ResultSet rs = ps.executeQuery()) {
//			while (rs.next()) {
//				long id = rs.getLong(SqlConstants.SELECT_PROJECT_RET_ID_INDEX);
//				String name = rs.getString(SqlConstants.SELECT_PROJECT_RET_NAME_INDEX);
//				String description = rs.getString(SqlConstants.SELECT_PROJECT_RET_DESCRIPTION_INDEX);
//				long managerId = rs.getLong(SqlConstants.SELECT_PROJECT_RET_MANAGER_INDEX);
//				User manager = DAOFactory.getUserDAOFromFactory().getById(managerId);
//				List<Build> builds = getBuildsByProjectId(id);
//				projects.add(new Project(id, name, description, builds, manager));
//			}
//			return projects;
//		}
//	}
//	
//	private List<Build> parseBuilds(PreparedStatement ps) throws SQLException {
//		List<Build> builds = new ArrayList<>();
//		try (ResultSet rs = ps.executeQuery()) {
//			while (rs.next()) {
//				long id = rs.getLong(SqlConstants.SELECT_BUILDS_RET_ID_INDEX);
//				String name = rs.getString(SqlConstants.SELECT_BUILDS_RET_NAME_INDEX);
//				long prId = rs.getLong(SqlConstants.SELECT_BUILDS_RET_PRID_INDEX);
//				builds.add(new Build(id, name, prId));
//			}
//			return builds;
//		}
//	}
//
//	@Override
//	public long getAllCount() throws DAOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}