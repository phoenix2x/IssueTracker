package org.example.issuetracker.service;

import java.util.List;

import org.example.issuetracker.domain.GenericDomainObject;

public interface IGenericService<E extends GenericDomainObject> {

	E getById(long id);
	
	List<E> getAll();
	
	void create(GenericDomainObject entity);
	
	E update(E entity);
	
	void delete(E entity);
	
	void deleteById(long entityId);
	
	long getAllCount();
}
