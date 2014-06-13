package org.example.issuetracker.dao;

import org.example.issuetracker.domain.User;
import org.example.issuetracker.model.exceptions.DAOException;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends IDao<User> {
	User getUser(String emailAddress, String password);

	User getUserByEmail(String emailAddress);
}
