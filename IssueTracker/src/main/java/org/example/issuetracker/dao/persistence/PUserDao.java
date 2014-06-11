package org.example.issuetracker.dao.persistence;

import javax.persistence.Query;

import org.example.issuetracker.dao.IUserDao;
import org.example.issuetracker.domain.User;
import org.example.issuetracker.model.exceptions.DAOException;
import org.springframework.stereotype.Repository;

@Repository
public class PUserDao extends AbstractJpaDAO<User> implements IUserDao {
	
	public PUserDao() {
		super(User.class);
	}

	@Override
	public User getUser(String emailAddress, String password) throws DAOException {
		Query query = entityManager.createNamedQuery("User.getUserByEmail");
		query.setParameter("emailaddress", emailAddress);
		User user = (User) query.getSingleResult();
		if (user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}
}
