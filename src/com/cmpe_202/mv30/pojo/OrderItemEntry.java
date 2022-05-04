package com.cmpe_202.mv30.pojo;

public class OrderItemEntry {

    private String name;

    private Integer count;

    public OrderItemEntry(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public void setName( String name) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return this.name;
    }

    public void setCount( Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return this.count;
    }
}
