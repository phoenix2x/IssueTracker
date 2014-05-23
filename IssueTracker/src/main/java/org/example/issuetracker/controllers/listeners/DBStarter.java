package org.example.issuetracker.controllers.listeners;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.example.issuetracker.model.dao.jdbc.connections.ConnectionManager;
import org.h2.jdbcx.JdbcConnectionPool;

/**
 * Application Lifecycle Listener implementation class DBStarter
 * 
 */
@WebListener
public class DBStarter implements ServletContextListener {

//	private static final String OLD_SEPARATOR = "\\";
//	private static final String JDBC_H2_PREFIX = "jdbc:h2:";
//	private static final String PATH = "WEB-INF/database/";
//	private static final String SEPARATOR = "/";
//	private static final String TRUE = "true";
//	private static final String PARAMETER_DB_URL_CONTEXTDEPENDING = "db.url.contextdepending";
//	private static final String PARAMETER_DB_PASSWORD = "db.password";
//	private static final String PARAMETER_DB_USER = "db.user";
//	private static final String PARAMETER_DB_URL = "db.url";
//	private static final String DEFAULT_DB_USER = "sa";
//	private static final String DEFAULT_DB_PASSWORD = "sa";
//	private static final String DEFAULT_DB_URL = "jdbc:h2:~/test";
//	private static final String DEFAULT_DB_URL_CONTEXT_DEPENDING = "false";


	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
//		ServletContext servletContext = servletContextEvent.getServletContext();
//		String urlContextDepending = getParameter(servletContext, PARAMETER_DB_URL_CONTEXTDEPENDING, DEFAULT_DB_URL_CONTEXT_DEPENDING);
//		String url = getParameter(servletContext, PARAMETER_DB_URL, DEFAULT_DB_URL);
//		String user = getParameter(servletContext, PARAMETER_DB_USER, DEFAULT_DB_USER);
//		String password = getParameter(servletContext, PARAMETER_DB_PASSWORD, DEFAULT_DB_PASSWORD);
//		if (urlContextDepending != null && urlContextDepending.equals(TRUE)) {
//			url = servletContext.getRealPath(SEPARATOR) + PATH + url;
//			url = url.replace(OLD_SEPARATOR, SEPARATOR);
//			url = JDBC_H2_PREFIX + url;
//		}
//		JdbcConnectionPool jdbcConnectionPool = JdbcConnectionPool.create(url, user, password);
//		ConnectionManager.setConnectionsPool(jdbcConnectionPool);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
//		ConnectionManager.disposeConnectionPool();
//		Enumeration<Driver> drivers = DriverManager.getDrivers();
//        while (drivers.hasMoreElements()) {
//            Driver driver = drivers.nextElement();
//            try {
//                DriverManager.deregisterDriver(driver);
//            } catch (SQLException e) {
//               e.printStackTrace();
//            }
//        }
	}

	private static String getParameter(ServletContext servletContext, String key, String defaultValue) {
		String value = servletContext.getInitParameter(key);
		return value == null ? defaultValue : value;
	}
}
