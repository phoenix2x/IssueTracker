package org.example.issuetracker.service;

import java.util.List;

import org.example.issuetracker.dao.IDao;
import org.example.issuetracker.domain.GenericDomainObject;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractGenericService<T extends GenericDomainObject> implements IGenericService<T> {

	public AbstractGenericService() {
	}
	
	protected abstract IDao<T> getDao();
	
	@Override
	@Transactional
	public T getById(long id) {
		return getDao().getById(id);
	}

	@Override
	@Transactional
	public List<T> getAll() {
		return getDao().getAll();
	}

	@Override
	@Transactional
	public void create(GenericDomainObject entity) {
		getDao().create(entity);
		
	}

	@Override
	@Transactional
	public T update(T entity) {
		return getDao().update(entity);
	}

	@Override
	@Transactional
	public void delete(T entity) {
		getDao().delete(entity);
		
	}

	@Override
	@Transactional
	public void deleteById(long entityId) {
		getDao().deleteById(entityId);
		
	}

	@Override
	@Transactional
	public long getAllCount() {
		return getDao().getAllCount();
	}
}
