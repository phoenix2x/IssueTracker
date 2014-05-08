package com.epam.issuetracker.constants;

import com.epam.issuetracker.beans.User;

public class Constants {
	public static final int NUMBER_ISSUES = 10;
	public static final String EMPTY_STRING = "";
	public static final String USER = "user";
	public static final String NAME = "name";
	public static final String ROLE = "role";
	public static final String LOGIN_NAME = "loginname";
	public static final String PASSWORD = "password";
	public static final String MESSAGE = "message";
	public static final String INCORRECT_LOGIN = "Login/pass incorrect";
	public static final String EMPTY_LOGIN = "Login/pass empty";
	public static final String HEADER_MENU_URL = "/HeaderMenu";
	public static final String LOGIN_ACTION_URL = "/LoginAction";
	public static final String ISSUES_URL = "/Issues";
	public static final String LOGOUT_URL = "/Logout";
	public static final User GUEST_USER = new User(0, "Guest", "", "GUEST");
}
