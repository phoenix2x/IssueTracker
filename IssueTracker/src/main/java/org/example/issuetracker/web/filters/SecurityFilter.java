package org.example.issuetracker.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.example.issuetracker.constants.Constants;
import org.example.issuetracker.domain.User;

/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter(filterName = "/SecurityFilter", urlPatterns = { "/AddIssue", "/AddIssueAction", "/BuildsAjaxServlet", "/EditIssueAction" })
public class SecurityFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(SecurityFilter.class);

    /**
     * Default constructor. 
     */
    public SecurityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			User user = (User) httpRequest.getSession().getAttribute(Constants.USER);
			if (user == null || user.equals(Constants.GUEST_USER)) {
				httpResponse.sendRedirect(httpResponse.encodeRedirectURL(httpRequest.getServletContext().getContextPath()
						+ Constants.ISSUES_URL));
				LOG.info("Redirect form " + httpRequest.getRequestURI());
				return;
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
