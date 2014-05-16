package org.example.issuetracker.model.dao;

import java.util.List;

import org.example.issuetracker.model.exceptions.DAOException;

public interface IDao<E> {
	E getElementById(long id) throws DAOException;
	List<E> getAllElements() throws DAOException;
}
