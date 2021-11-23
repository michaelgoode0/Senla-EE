package com.test.project.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;


public abstract class AbstractDao<T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> clazz;

    public AbstractDao(){
        setClazz(getGenericEntityClass());
    }

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
        if(entity!=null) {
            entityManager.remove(entity);
        }
        return entity;
    }
    private Class<T> getGenericEntityClass() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass()
                .getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

}
