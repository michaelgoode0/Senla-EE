package com.test.project.util;

import java.util.List;

public interface GenericDao<T> {

    T create(final T entity);

    T update(final T entity);

    T delete(final Long id);

    T read(final Long id);

    void setClazz(Class<T> clazzToSet);

}
