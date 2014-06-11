package org.example.issuetracker.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao<E extends Serializable> {
	E getById(long id);
	
	List<E> getAll();
	
	void create(E entity);
	
	E update(E entity);
	
	void delete(E entity);
	
	void deleteById(long entityId);
	
	long getAllCount();
}
