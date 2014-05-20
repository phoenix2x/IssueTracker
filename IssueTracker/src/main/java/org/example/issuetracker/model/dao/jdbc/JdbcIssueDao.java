package org.example.issuetracker.model.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.constants.JSPConstants;
import org.example.issuetracker.constants.SqlConstants;
import org.example.issuetracker.factories.DAOFactory;
import org.example.issuetracker.model.beans.Issue;
import org.example.issuetracker.model.beans.Project;
import org.example.issuetracker.model.beans.Status;
import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IIssueDao;
import org.example.issuetracker.model.dao.IProjectDao;
import org.example.issuetracker.model.dao.IUserDao;
import org.example.issuetracker.model.dao.jdbc.connections.ConnectionManager;
import org.example.issuetracker.model.exceptions.DAOException;

public class JdbcIssueDao implements IIssueDao {
	private static JdbcIssueDao instance;

	/**
	 * 
	 */
	private JdbcIssueDao() {
	}

	public synchronized static JdbcIssueDao getImpl() throws DAOException {
		if (instance == null) {
			instance = new JdbcIssueDao();
		}
		return instance;
	}

	@Override
	public List<Issue> getIssuesByUserId(long userId, int numberIssues) throws DAOException {
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_ISSUE_BY_ASSIGNEE_ID)) {
			ps.setInt(SqlConstants.SELECT_ISSUE_BY_ASSIGNEE_N_INDEX, numberIssues);
			ps.setLong(SqlConstants.SELECT_ISSUE_BY_ASSIGNEE_ID_INDEX, userId);
			return parseIssues(ps);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<Issue> getLastIssues(int numberIssues) throws DAOException {
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_LAST_ISSUES)) {
			ps.setInt(SqlConstants.SELECT_LAST_ISSUES_N_INDEX, numberIssues);
			return parseIssues(ps);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<String> getProperties(String propName) throws DAOException {
		List<String> properties = new ArrayList<>();
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_ALL_PROPERTIES + propName)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				properties.add(rs.getString(SqlConstants.SELECT_ALL_PROPERTIES_RET_NAME_INDEX));
			}
			return properties;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<Issue> getAllElements() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addIssue(Issue issue) throws DAOException {
//		if (!isProjectAndBuildValid(projectId, build) || !isUserExist(createdBy)
//				|| !(assigneeId == Constants.EMPTY_ID || isUserExist(assigneeId))) {
//			return false;
//		}

		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.ADD_ISSUE)) {
			ps.setLong(SqlConstants.ADD_ISSUE_CREATEDBY_INDEX, issue.getCreatedBy().getId());
			ps.setString(SqlConstants.ADD_ISSUE_SUMMARY_INDEX, issue.getSummary());
			ps.setString(SqlConstants.ADD_ISSUE_DESCRIPTION_INDEX, issue.getDescription());
			ps.setInt(SqlConstants.ADD_ISSUE_STATUS_INDEX, issue.getStatus().getId());
			ps.setString(SqlConstants.ADD_ISSUE_TYPE_INDEX, issue.getType());
			ps.setString(SqlConstants.ADD_ISSUE_PRIORITY_INDEX, issue.getPriority());
			ps.setLong(SqlConstants.ADD_ISSUE_PROJECT_INDEX, issue.getProject().getId());
			ps.setString(SqlConstants.ADD_ISSUE_BUILD_INDEX, issue.getBuildFound());
			if (issue.getAssignee() != null) {
				ps.setLong(SqlConstants.ADD_ISSUE_ASSIGNEE_INDEX, issue.getAssignee().getId());
			} else {
				ps.setNull(SqlConstants.ADD_ISSUE_ASSIGNEE_INDEX, java.sql.Types.BIGINT);
			}
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean updateIssue(Issue issue) throws DAOException {
		
		return false;
	}

	@Override
	public Issue getElementById(long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Issue> parseIssues(PreparedStatement ps) throws SQLException, DAOException {
		List<Issue> issues = new ArrayList<>();
		try (ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				long id = rs.getLong(SqlConstants.SELECT_ISSUE_RET_ID_INDEX);
				Date createDate = rs.getDate(SqlConstants.SELECT_ISSUE_RET_CREATEDATE_INDEX);
				long createdById = rs.getLong(SqlConstants.SELECT_ISSUE_RET_CREATEDBY_INDEX);
				Date modifyDate = rs.getDate(SqlConstants.SELECT_ISSUE_RET_MODIFYDATE_INDEX);
				long modifiedById = rs.getLong(SqlConstants.SELECT_ISSUE_RET_MODIFIEDBY_INDEX);
				String summary = rs.getString(SqlConstants.SELECT_ISSUE_RET_SUMMARY_INDEX);
				String description = rs.getString(SqlConstants.SELECT_ISSUE_RET_DESCRIPTION_INDEX);
				int status = rs.getInt(SqlConstants.SELECT_ISSUE_RET_STATUS_INDEX);
				String statusName = rs.getString(SqlConstants.SELECT_ISSUE_RET_STATUS_NAME_INDEX);
				String resolution = rs.getString(SqlConstants.SELECT_ISSUE_RET_RESOLUTION_INDEX);
				String type = rs.getString(SqlConstants.SELECT_ISSUE_RET_TYPE_INDEX);
				String priority = rs.getString(SqlConstants.SELECT_ISSUE_RET_PRIORITY_INDEX);
				long projectId = rs.getLong(SqlConstants.SELECT_ISSUE_RET_PROJECT_INDEX);
				String buildFound = rs.getString(SqlConstants.SELECT_ISSUE_RET_BUILDFOUND_INDEX);
				long assigneeId = rs.getLong(SqlConstants.SELECT_ISSUE_RET_ASSIGNEE_INDEX);
				IUserDao userDao = DAOFactory.getUserDAOFromFactory();
				User createdBy = userDao.getElementById(createdById);
				User modifiedBy = userDao.getElementById(modifiedById);
				Project project = DAOFactory.getProjectDaoFromFactory().getElementById(projectId);
				User assignee = userDao.getElementById(assigneeId);
				issues.add(new Issue(id, createDate, createdBy, modifyDate, modifiedBy, summary, description, new Status(status, statusName),
						resolution, type, priority, project, buildFound, assignee));
			}
		}
		return issues;
	}

	private boolean isProjectAndBuildValid(long projectId, String build) throws DAOException {
		IProjectDao projectDao = DAOFactory.getProjectDaoFromFactory();
		Project project = projectDao.getElementById(projectId);
		if (project == null) {
			return false;
		}
		for (String currentBuild : project.getBuilds()) {
			if (currentBuild.equals(build)) {
				return true;
			}
		}
		return false;
	}

	private boolean isUserExist(long userId) throws DAOException {
		IUserDao userDao = DAOFactory.getUserDAOFromFactory();
		return userDao.getElementById(userId) != null;
	}

	@Override
	public List<Status> getStatuses(int...id) throws DAOException {
		if (id.length == 0) {
			throw new DAOException(Constants.EMPTY_PARAMS_ERROR);
		}
		List<Status> statuses = new ArrayList<>();
		String query = SqlConstants.SELECT_STATUSES + id[0];
		for (int i = 1; i < id.length; i++) {
			query += SqlConstants.COMMA + id[i];
		}
		query += SqlConstants.CLOSING_PARENTHESIS; 
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int statusId = rs.getInt(SqlConstants.SELECT_STATUSES_RET_ID_INDEX);
				String statusName = rs.getString(SqlConstants.SELECT_STATUSES_RET_NAME_INDEX);
				statuses.add(new Status(statusId,statusName));
			}
			return statuses;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
