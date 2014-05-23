package org.example.issuetracker.controllers.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.example.issuetracker.constants.JSPConstants;
import org.example.issuetracker.factories.DAOFactory;
import org.example.issuetracker.model.beans.Build;
import org.example.issuetracker.model.dao.IProjectDao;
import org.example.issuetracker.model.exceptions.DAOException;

/**
 * Servlet implementation class BuildsAjaxServlet
 */
@WebServlet("/BuildsAjaxServlet")
public class BuildsAjaxServlet extends AbstractServlet implements Servlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public BuildsAjaxServlet() {
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String projectString = request.getParameter(JSPConstants.PROJECT_ID);
		long projectId = Long.valueOf(projectString);
		try {
			IProjectDao projectDao = DAOFactory.getProjectDaoFromFactory();
			List<Build> builds = projectDao.getBuildsByProjectId(projectId);
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			for (Build build: builds) {
				jsonObject.put(build.getId(), build.getName());
			}
			out.print(jsonObject);
			out.flush();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
