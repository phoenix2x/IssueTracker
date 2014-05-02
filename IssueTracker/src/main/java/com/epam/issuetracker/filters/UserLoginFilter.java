package com.epam.issuetracker.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.epam.issuetracker.beans.User;
import com.epam.issuetracker.constants.Constants;

/**
 * Servlet Filter implementation class UserLoginFilter
 */
@WebFilter(filterName = "/UserLoginFilter", urlPatterns = {"/*"})
public class UserLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserLoginFilter() {
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
		if (request instanceof HttpServletRequest){
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			User user = (User) httpRequest.getSession().getAttribute(Constants.USER);
			if (user == null) {
				httpRequest.getSession().setAttribute(Constants.USER, Constants.GUEST_USER);
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
