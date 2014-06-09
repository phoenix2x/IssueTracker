package org.example.issuetracker.model.dao.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.example.issuetracker.model.dao.IDao;
import org.example.issuetracker.model.exceptions.DAOException;

public abstract class AbstractJpaDAO<T extends Serializable> implements IDao<T>{

    private Class<T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractJpaDAO(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }
    
    @Override
    public T getById(long id) {
        return entityManager.find(clazz, id);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }
    
    @Override
    public void create(T entity) {
        entityManager.persist(entity);
    }
    
    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }
    
    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }
    
    @Override
    public void deleteById(long entityId) {
    	T entity = getById(entityId);
        delete(entity);
    }
    
    @Override
    public long getAllCount() throws DAOException {
		return 0;
    	//TODO
    }

}