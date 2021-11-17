package com.study.store.dao;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao<T> {
    void delete(T entity) throws SQLException;

    void update(T entity) throws SQLException;

    List<T> getAll(String depID) throws SQLException;

}
