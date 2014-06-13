package org.example.issuetracker.dao.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.example.issuetracker.dao.IDao;
import org.example.issuetracker.domain.GenericDomainObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class AbstractJpaDAO<T extends GenericDomainObject> implements IDao<T>{

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
    @Transactional
    public void create(GenericDomainObject entity) {
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
    public long getAllCount() {
		return getAll().size();
    }
}