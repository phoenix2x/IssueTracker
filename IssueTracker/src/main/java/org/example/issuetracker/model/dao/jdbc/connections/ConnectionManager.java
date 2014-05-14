package org.example.issuetracker.model.dao.jdbc.connections;

import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectionManager {
	private static final String CONNECTION_POOL_IS_NOT_SET = "Connection pool is not set.";
	private static JdbcConnectionPool jdbcConnectionPool;
	
	public static void setConnectionsPool(JdbcConnectionPool jdbcConnectionPool) {
		ConnectionManager.jdbcConnectionPool = jdbcConnectionPool;
	}
	public static Connection getConnection() throws SQLException {
		if (jdbcConnectionPool != null) {
			return jdbcConnectionPool.getConnection();
		} else {
			throw new SQLException(CONNECTION_POOL_IS_NOT_SET);
		}
	}
	public static void closeConnection(Connection cn) {
		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
