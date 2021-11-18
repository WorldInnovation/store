package com.study.store;

public final class ServletConstants {

    public static final String SQL_SELECT_ALL = "select * from store.products";
    public static final String SQL_CONNECTION = "jdbc:mysql://localhost:3306/store?user=root&password=root";
    public static final String SQL_DELET_PRODUCT_STATMENT = "delete from store.products where id=";

    public static final String CONNECTION_ERROR = "Connection error";
    public static final String PRODUCT_NOT_FOUND = "Product not found";

}
