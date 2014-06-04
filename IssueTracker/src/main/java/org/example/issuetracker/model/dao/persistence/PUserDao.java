package org.example.issuetracker.model.dao.persistence;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.example.issuetracker.model.beans.User;
import org.example.issuetracker.model.dao.IUserDao;
import org.example.issuetracker.model.exceptions.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PUserDao implements IUserDao {
	
	@Autowired
    private EntityManagerFactory entityManagerFactory;

	@Override
	public User getElementById(long id) throws DAOException {
		return entityManagerFactory.createEntityManager().find(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllElements() throws DAOException {
		return entityManagerFactory.createEntityManager().createQuery("FROM user").getResultList();
	}

	@Override
	public long getElementNumber() throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getUser(String emailAddress, String password) throws DAOException {
		entityManagerFactory.createEntityManager().createQuery("FROM user WHERE EMAILADDRESS=?");
		return null;
	}

	@Override
	public void addUser(User user) throws DAOException {
		entityManagerFactory.createEntityManager().persist(user);
	}

	@Override
	public void updateUser(User user) throws DAOException {
		entityManagerFactory.createEntityManager().merge(user);
	}

}
