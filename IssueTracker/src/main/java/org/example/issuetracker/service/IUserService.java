package org.example.issuetracker.service;

import java.util.List;

import org.example.issuetracker.domain.User;

public interface IUserService extends IGenericService<User> {

	User getUserByEmail(String emailAddress);

	List<User> getFoundUsersList(User user);

}
