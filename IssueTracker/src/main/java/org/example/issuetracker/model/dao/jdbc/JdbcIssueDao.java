package org.example.issuetracker.model.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.constants.JSPConstants;
import org.example.issuetracker.constants.SqlConstants;
import org.example.issuetracker.model.beans.Build;
import org.example.issuetracker.model.beans.Issue;
import org.example.issuetracker.model.beans.Project;
import org.example.issuetracker.model.beans.Status;
import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IIssueDao;
import org.example.issuetracker.model.dao.IUserDao;
import org.example.issuetracker.model.dao.factories.DAOFactory;
import org.example.issuetracker.model.dao.jdbc.connections.ConnectionManager;
import org.example.issuetracker.model.enums.StatusId;
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
	public List<Issue> getIssuesByUserId(long userId, int numberIssues, int offset, int orderBy, int order) throws DAOException {
		String query = SqlConstants.SELECT_ISSUE_BY_ASSIGNEE_ID_PREFIX;
		query = buildQuery(query, orderBy, order);
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(query)) {
			ps.setLong(SqlConstants.SELECT_ISSUE_BY_ASSIGNEE_ID_INDEX, userId);
			ps.setInt(SqlConstants.SELECT_ISSUE_BY_ASSIGNEE_N_INDEX, numberIssues);
			ps.setInt(SqlConstants.SELECT_ISSUE_BY_ASSIGNEE_OFFSET_INDEX, offset);
			return parseIssues(ps);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	private String buildQuery(String query, int orderBy, int order) {
		switch (orderBy) {
		case 0: query += SqlConstants.SELECT_PART_ISSUES;
			break;
		case 1: query += SqlConstants.SELECT_PART_PRIORITY;
			break;
		case 2: query += SqlConstants.SELECT_PART_ASSIGNEE;
			break;
		case 3: query += SqlConstants.SELECT_PART_TYPE;
			break;
		case 4: query += SqlConstants.SELECT_PART_STATUS;
			break;
		case 5: query += SqlConstants.SELECT_PART_SUMMARY;
			break;
		}
		if (order == 0) {
			query += SqlConstants.DESC;
		} else {
			query += SqlConstants.ASC;
		}
		query += SqlConstants.SELECT_ISSUE_SUFFIX;
		return query;
	}

	@Override
	public List<Issue> getLastIssues(int numberIssues, int offset, int orderBy, int order) throws DAOException {
		String query = SqlConstants.SELECT_LAST_ISSUES_PREFIX;
		query = buildQuery(query, orderBy, order);
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(query)) {
			ps.setInt(SqlConstants.SELECT_LAST_ISSUES_N_INDEX, numberIssues);
			ps.setInt(SqlConstants.SELECT_LAST_ISSUES_OFFSET_INDEX, offset);
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
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.ADD_ISSUE)) {
			ps.setLong(SqlConstants.ADD_ISSUE_CREATEDBY_INDEX, issue.getCreatedBy().getId());
			ps.setString(SqlConstants.ADD_ISSUE_SUMMARY_INDEX, issue.getSummary());
			ps.setString(SqlConstants.ADD_ISSUE_DESCRIPTION_INDEX, issue.getDescription());
			ps.setInt(SqlConstants.ADD_ISSUE_STATUS_INDEX, issue.getStatus().getId());
			ps.setString(SqlConstants.ADD_ISSUE_TYPE_INDEX, issue.getType());
			ps.setString(SqlConstants.ADD_ISSUE_PRIORITY_INDEX, issue.getPriority());
			ps.setLong(SqlConstants.ADD_ISSUE_PROJECT_INDEX, issue.getProject().getId());
			ps.setLong(SqlConstants.ADD_ISSUE_BUILD_INDEX, issue.getBuildFound().getId());
			if (issue.getAssignee().getId() != Constants.EMPTY_ID) {
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
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.UPDATE_ISSUE)) {
			ps.setLong(SqlConstants.UPDATE_ISSUE_MODIFIEDBY_INDEX, issue.getModifiedBy().getId());
			ps.setString(SqlConstants.UPDATE_ISSUE_SUMMARY_INDEX, issue.getSummary());
			ps.setString(SqlConstants.UPDATE_ISSUE_DESCRIPTION_INDEX, issue.getDescription());
			ps.setInt(SqlConstants.UPDATE_ISSUE_STATUS_INDEX, issue.getStatus().getId());
			if (issue.getResolution() != null) {
				ps.setString(SqlConstants.UPDATE_ISSUE_RESOLUTION_INDEX, issue.getResolution());
			} else {
				ps.setNull(SqlConstants.UPDATE_ISSUE_RESOLUTION_INDEX, java.sql.Types.VARCHAR);
			}
			ps.setString(SqlConstants.UPDATE_ISSUE_TYPE_INDEX, issue.getType());
			ps.setString(SqlConstants.UPDATE_ISSUE_PRIORITY_INDEX, issue.getPriority());
			ps.setLong(SqlConstants.UPDATE_ISSUE_PROJECT_INDEX, issue.getProject().getId());
			ps.setLong(SqlConstants.UPDATE_ISSUE_BUILD_INDEX, issue.getBuildFound().getId());
			if (issue.getAssignee().getId() != Constants.EMPTY_ID) {
				ps.setLong(SqlConstants.UPDATE_ISSUE_ASSIGNEE_INDEX, issue.getAssignee().getId());
			} else {
				ps.setNull(SqlConstants.UPDATE_ISSUE_ASSIGNEE_INDEX, java.sql.Types.BIGINT);
			}
			ps.setLong(SqlConstants.UPDATE_ISSUE_ID_INDEX, issue.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Issue getElementById(long id) throws DAOException {
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_ISSUE_BY_ID)) {
			ps.setLong(SqlConstants.SELECT_ISSUE_BY_ID_INDEX, id);
			List<Issue> issuesList = parseIssues(ps);
			if (issuesList.size() != 0) {
				return issuesList.get(0);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
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
				long buildId = rs.getLong(SqlConstants.SELECT_ISSUE_RET_BUILDID_INDEX);
				String buildName = rs.getString(SqlConstants.SELECT_ISSUE_RET_BUILDNAME_INDEX);
				long buildPrId = rs.getLong(SqlConstants.SELECT_ISSUE_RET_BUILDPRID_INDEX);
				long assigneeId = rs.getLong(SqlConstants.SELECT_ISSUE_RET_ASSIGNEE_INDEX);
				IUserDao userDao = DAOFactory.getUserDAOFromFactory();
				User createdBy = userDao.getElementById(createdById);
				User modifiedBy = userDao.getElementById(modifiedById);
				Project project = DAOFactory.getProjectDaoFromFactory().getElementById(projectId);
				User assignee = userDao.getElementById(assigneeId);
				issues.add(new Issue(id, createDate, createdBy, modifyDate, modifiedBy, summary, description, new Status(status, statusName),
						resolution, type, priority, project, new Build(buildId, buildName, buildPrId), assignee));
			}
		}
		return issues;
	}

//	private boolean isProjectAndBuildValid(long projectId, long buildId) throws DAOException {
//		IProjectDao projectDao = DAOFactory.getProjectDaoFromFactory();
//		Project project = projectDao.getElementById(projectId);
//		if (project == null) {
//			return false;
//		}
//		for (Build currentBuild : project.getBuilds()) {
//			if (currentBuild.getId() == buildId) {
//				return true;
//			}
//		}
//		return false;
//	}

	@Override
	public List<Status> getNewStatuses() throws DAOException {
		List<Status> statuses = new ArrayList<>();
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_NEW_STATUSES)) {
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

	@Override
	public Map<Integer, Status> getStatuses(int currentStatus) throws DAOException {
		Map<Integer, Status> statuses = new HashMap<>();
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_ALL_STATUSES)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int statusId = rs.getInt(SqlConstants.SELECT_STATUSES_RET_ID_INDEX);
				String statusName = rs.getString(SqlConstants.SELECT_STATUSES_RET_NAME_INDEX);
				statuses.put(statusId, new Status(statusId,statusName));
			}
			if (currentStatus != StatusId.NEW.getId()) {
				statuses.remove(StatusId.NEW.getId());
			}
			if (currentStatus != StatusId.ASSIGNED.getId() && currentStatus != StatusId.NEW.getId()) {
				statuses.remove(StatusId.ASSIGNED.getId());
			}
			if (currentStatus != StatusId.CLOSED.getId() && currentStatus != StatusId.REOPENED.getId()) {
				statuses.remove(StatusId.REOPENED.getId());
			} 
			if (currentStatus == StatusId.CLOSED.getId()) {
				statuses.remove(StatusId.INPROGRESS.getId());
				statuses.remove(StatusId.RESOLVED.getId());
			}
			return statuses;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public long getElementNumber() throws DAOException {
		String query = SqlConstants.SELECT_ROW_COUNT;
		query += JSPConstants.ISSUES;
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(query)) {
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getLong(SqlConstants.SELECT_ROW_COUNT_RET_COUNT_INDEX);
				} else {
					return 0;
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public long getElementNumber(long assigneeId) throws DAOException {
		try (Connection cn = ConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlConstants.SELECT_ISSUES_COUNT_BY_ASSEGNEE)) {
			ps.setLong(SqlConstants.SELECT_ISSUES_COUNT_ASSEGNEE_INDEX, assigneeId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getLong(SqlConstants.SELECT_ISSES_COUNT_RET_COUNT_INDEX);
				} else {
					return 0;
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
