package org.example.issuetracker.service;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<E extends Serializable> {

	E getById(long id);
	
	List<E> getAll();
	
	void create(E entity);
	
	E update(E entity);
	
	void delete(E entity);
	
	void deleteById(long entityId);
	
	long getAllCount();
}
