package com.epam.issuetracker.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.issuetracker.constants.Constants;

/**
 * Servlet implementation class Issues
 */
@WebServlet("/Issues")
public class Issues extends AbstractServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Issues() {
        super();
        // TODO Auto-generated constructor stub
    }

	/* (non-Javadoc)
	 * @see com.epam.issuetracker.servlets.AbstractServlet#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.print("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF8\"><title>Issues</title></head><body>");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(response.encodeURL(Constants.HEADER_MENU_URL));
		requestDispatcher.include(request, response);
		writer.print("<hr>");
		String message = (String) request.getAttribute(Constants.MESSAGE);
		if (message != null) {
			writer.print("<h2>" + message + "</h2>");
		}
		writer.print("No issues found.");
		
		writer.print("</body></html>");
		writer.flush();
	}
}
