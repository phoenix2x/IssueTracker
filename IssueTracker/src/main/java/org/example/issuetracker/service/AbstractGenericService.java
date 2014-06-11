package org.example.issuetracker.service;

import java.io.Serializable;
import java.util.List;

import org.example.issuetracker.dao.persistence.AbstractJpaDAO;

public class AbstractGenericService<T extends Serializable> implements IGenericService<T> {

	private AbstractJpaDAO<T> abstractJpaDAO;
	
	public AbstractGenericService(AbstractJpaDAO<T> abstractJpaDAOtoSet) {
		this.abstractJpaDAO = abstractJpaDAOtoSet;
	}
	
	@Override
	public T getById(long id) {
		return abstractJpaDAO.getById(id);
	}

	@Override
	public List<T> getAll() {
		return abstractJpaDAO.getAll();
	}

	@Override
	public void create(T entity) {
		abstractJpaDAO.create(entity);
		
	}

	@Override
	public T update(T entity) {
		return abstractJpaDAO.update(entity);
	}

	@Override
	public void delete(T entity) {
		abstractJpaDAO.delete(entity);
		
	}

	@Override
	public void deleteById(long entityId) {
		abstractJpaDAO.deleteById(entityId);
		
	}

	@Override
	public long getAllCount() {
		return abstractJpaDAO.getAllCount();
	}
}
