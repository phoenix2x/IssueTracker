package org.example.issuetracker.model.dao.persistence;

import javax.persistence.Query;

import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IUserDao;
import org.example.issuetracker.model.exceptions.DAOException;
import org.springframework.stereotype.Repository;

@Repository
public class PUserDao extends AbstractJpaDAO<User> implements IUserDao {
	
	public PUserDao() {
		super(User.class);
	}

	@Override
	public User getUser(String emailAddress, String password) throws DAOException {
		Query query = entityManager.createQuery("FROM user WHERE emailaddress = :emailaddress");
		query.setParameter("emailaddress", emailAddress);
		User user = (User) query.getSingleResult();
		if (user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

}
