package org.example.issuetracker.service;

import org.example.issuetracker.domain.User;

public interface IUserService extends IGenericService<User> {

	User getUserByEmail(String emailAddress);

}
