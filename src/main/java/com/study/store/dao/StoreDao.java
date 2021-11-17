package com.study.store.dao;

import java.sql.SQLException;
import java.util.List;

public interface StoreDao<T> {
    void delete(T entity) throws SQLException;

    void update(T entity) throws SQLException;

    List<T> getAll(String storeId) throws SQLException;

//    Store getStoreById(String productId) throws SQLException;
}
