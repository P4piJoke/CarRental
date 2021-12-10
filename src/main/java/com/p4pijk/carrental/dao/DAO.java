package com.p4pijk.carrental.dao;

import java.util.List;

public interface DAO<T> {

    T save(T t);

    void update(T t, String[] params);

    T get(long id);

    void delete(T t);

    List<T> getAll();
}
