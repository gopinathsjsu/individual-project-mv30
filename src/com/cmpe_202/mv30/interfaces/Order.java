package com.cmpe_202.mv30.interfaces;

import com.cmpe_202.mv30.pojo.OrderItemEntry;

import java.util.List;

public abstract class Order {

    public abstract void addItem( Order order) throws Exception ;

    public abstract Integer getOrderListSize() throws Exception ;

    public abstract List<Order> getOrderList() throws Exception;

    public abstract Boolean isValid();

    public abstract Integer getTotal();

    public abstract List<String> getFulfillmentLogs();

    public abstract List<String> getExcessAmountsLogs();

    public abstract List<OrderItemEntry> getOrderItemEntries();
}
