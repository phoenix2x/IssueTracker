package org.example.issuetracker.model.dao;

import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.exceptions.DAOException;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao {
	User getUser(String emailAddress, String password) throws DAOException;
}
