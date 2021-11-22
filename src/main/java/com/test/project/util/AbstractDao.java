package com.test.project.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public abstract class AbstractDao<T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> clazz;

    @Override
    public void setClazz(Class<T> clazzToSet) {
        this.clazz=clazzToSet;
    }

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public T read(Long id) {
        return entityManager.find(clazz,id);
    }

    @Override
    public T delete(Long entityId) {
        final T entity = read(entityId);
        entityManager.remove(entity);
        return entity;
    }
}
