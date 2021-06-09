package com.vlad.my_own_web_app.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {

    boolean save(T entity);
    Optional<T> findById(K id);
    List<T> findAll();
    void update(T entity);
    boolean deleteById(K id);
}
