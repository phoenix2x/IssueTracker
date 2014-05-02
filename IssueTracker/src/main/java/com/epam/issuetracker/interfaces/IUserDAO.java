package com.epam.issuetracker.interfaces;

import com.epam.issuetracker.beans.User;
import com.epam.issuetracker.exceptions.DAOException;

public interface IUserDAO {
	User getUser(String loginName, String password) throws DAOException;

	void addUser(User user) throws DAOException;

	void updateUser(User user) throws DAOException;
}
