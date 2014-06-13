package org.example.issuetracker.model.dao.jdbc.triggers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.h2.api.Trigger;

public class BuildsCheckTrigger implements Trigger {
	public static final String CREATE_TRRIGER = "create trigger IF NOT EXISTS checkbuilds before insert, update on ISSUES for each row call \"org.example.issuetracker.model.dao.jdbc.triggers.BuildsCheckTrigger\"";
	private static final String BUILD_CHECK_ERROR = "Build check trigger fail";
	private static final String SELECT_FROM_BUILDS = "SELECT projectid FROM builds WHERE id=?";
	private static final int PROJECT_INDEX = 11;
	private static final int BUILD_INDEX = 12;
	private static final int BUILD_ID_INDEX = 1;
	private static final int BUILD_RET_PROJECT_INDEX = 1;
	
	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void fire(Connection cn, Object[] oldRow, Object[] newRow) throws SQLException {
		long projectId;
		long buildId;
		try {
			projectId = Long.parseLong(newRow[PROJECT_INDEX].toString());
			buildId = Long.parseLong(newRow[BUILD_INDEX].toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
		try (PreparedStatement ps = cn.prepareStatement(SELECT_FROM_BUILDS)) {
			ps.setLong(BUILD_ID_INDEX, buildId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					if (rs.getLong(BUILD_RET_PROJECT_INDEX) == projectId) {
						return;
					}
				}
				throw new SQLException(BUILD_CHECK_ERROR);
			}
		}
	}

	@Override
	public void init(Connection arg0, String arg1, String arg2, String arg3, boolean arg4, int arg5)
			throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() throws SQLException {
		// TODO Auto-generated method stub

	}

}
