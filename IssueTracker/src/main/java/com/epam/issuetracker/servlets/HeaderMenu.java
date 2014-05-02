package com.epam.issuetracker.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.issuetracker.beans.User;
import com.epam.issuetracker.constants.Constants;
import com.epam.issuetracker.enums.UserRoles;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/HeaderMenu")
public class HeaderMenu extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public HeaderMenu() {
        // TODO Auto-generated constructor stub
    }

	/* (non-Javadoc)
	 * @see com.epam.issuetracker.servlets.AbstractServlet#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		User user = (User) request.getSession().getAttribute(Constants.USER);
		if (Constants.GUEST_USER.equals(user)) {
			writer.print("<form name=\"login\" method=\"post\" action=\"" + getServletContext().getContextPath() + response.encodeURL(Constants.LOGIN_ACTION_URL) + "\">");
			writer.print("Login:<input type=\"text\" name=\"loginname\">Password:<input type=\"password\" name=\"password\"><br><input type=\"submit\" value=\"Login\">");
			writer.print("</form>");
			writer.print("<button>Search</button>");
		} else {
			writer.print("Hello " + user.getLoginName() + "<br>");
			writer.print("<a href=\"#\">Edit profile</a>");
			writer.print("<button>Submit Issue</button>");
			writer.print("<button>Search</button>");
			writer.print("<form name=\"logout\" method=\"post\" action=\"" + getServletContext().getContextPath() + response.encodeURL(Constants.LOGOUT_URL) + "\">");
			writer.print("<input type=\"submit\" value=\"Logout\">");
			writer.print("</form>");
			if (user.getUserRole().equals(UserRoles.ADMINISTRATOR)){
				writer.print("<hr>");
				writer.print("<a href=\"#\">Projects</a>");
				writer.print("<a href=\"#\">Statuses</a>");
				writer.print("<a href=\"#\">Resolutions</a>");
				writer.print("<a href=\"#\">Priorities</a>");
				writer.print("<a href=\"#\">Types</a>");
				writer.print("<br>");
				writer.print("<a href=\"#\">Add project</a>");
				writer.print("<a href=\"#\">Add resolution</a>");
				writer.print("<a href=\"#\">Add priority</a>");
				writer.print("<a href=\"#\">Add type</a>");
				writer.print("<br>");
				writer.print("<a href=\"#\">Search user</a>");
				writer.print("<a href=\"#\">Add user</a>");
			}
		}
	}
}
