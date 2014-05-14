package org.example.issuetracker.model.dao;

import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.exceptions.DAOException;

public interface IUserDao extends IDao<User>{
	User getUser(String emailAddress, String password) throws DAOException;

	void addUser(User user) throws DAOException;

	void updateUser(User user) throws DAOException;
}
