package com.study.store;

import java.sql.Date;
import java.time.LocalDate;

public class ProductModel {

    public ProductModel () {
        this.name = "";
        this.price = 0;
        //this.date = new Date();
    }

    public ProductModel(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    private Integer id;

    private String name;

    private Integer price;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


}
