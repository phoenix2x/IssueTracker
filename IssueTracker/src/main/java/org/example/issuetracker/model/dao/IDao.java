package org.example.issuetracker.model.dao;

import java.util.List;

import org.example.issuetracker.model.exceptions.DAOException;

public interface IDao<E> {
	E getById(long id) throws DAOException;
	
	List<E> getAll() throws DAOException;
	
	void create(E entity) throws DAOException;
	
	E update(E entity) throws DAOException;
	
	void delete(E entity) throws DAOException;
	
	void deleteById(long entityId) throws DAOException;
	
	long getAllCount() throws DAOException;
}
