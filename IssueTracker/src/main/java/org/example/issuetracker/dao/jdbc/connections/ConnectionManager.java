package org.example.issuetracker.dao.jdbc.connections;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {
//	private static final String CONNECTION_POOL_IS_NOT_SET = "Connection pool is not set.";
//	private static JdbcConnectionPool jdbcConnectionPool;
	
//	public static void setConnectionsPool(JdbcConnectionPool jdbcConnectionPool) {
//		ConnectionManager.jdbcConnectionPool = jdbcConnectionPool;
//	}

	private static final String DATA_SOURCE_NAME = "java:/comp/env/jdbc/MyLocalDB";

	public static Connection getConnection() throws SQLException {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(DATA_SOURCE_NAME);
			return ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
//		if (jdbcConnectionPool != null) {
//			return jdbcConnectionPool.getConnection();
//		} else {
//			throw new SQLException(CONNECTION_POOL_IS_NOT_SET);
//		}
//		return null;
	}
//	public static void closeConnection(Connection cn) {
//		if (cn != null) {
//			try {
//				cn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	public static void disposeConnectionPool() {
//		if (jdbcConnectionPool != null) {
//			jdbcConnectionPool.dispose();
//		}
//	}
}
