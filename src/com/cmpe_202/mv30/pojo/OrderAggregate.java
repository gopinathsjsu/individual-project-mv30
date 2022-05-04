package com.cmpe_202.mv30.pojo;

import com.cmpe_202.mv30.interfaces.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAggregate extends Order {

    private final List<Order> orderList;

    public OrderAggregate() {
        orderList = new ArrayList<>();
    }

    @Override
    public void addItem(Order order)  {
        orderList.add(order);
    }

    @Override
    public Integer getOrderListSize() {
        return orderList.size();
    }

    @Override
    public List<Order> getOrderList() throws Exception {
        return this.orderList;
    }

    @Override
    public Boolean isValid() {
        Boolean isValid = true;
        for(Order order: orderList) {
            isValid = isValid && order.isValid();
        }
        return isValid;
    }

    @Override
    public Integer getTotalCount() {
        Integer totCount = 0;
        for(Order order: orderList) {
            totCount = totCount + order.getTotalCount();
        }
        return totCount;
    }

    @Override
    public Integer getTotalAmount() {
        Integer totAmount = 0;
        for(Order order: orderList) {
            totAmount = totAmount + order.getTotalAmount();
        }
        return totAmount;
    }

    @Override
    public List<String> getFulfillmentLogs() {
        List<String> fulfillmentLogs = new ArrayList<>();
        for(Order order: orderList) {
            fulfillmentLogs.addAll(order.getFulfillmentLogs());
        }
        return fulfillmentLogs;
    }

    @Override
    public List<String> getExcessAmountsLogs() {
        List<String> excessAmountLogs = new ArrayList<>();
        for(Order order: orderList) {
            excessAmountLogs.addAll(order.getExcessAmountsLogs());
        }
        return excessAmountLogs;
    }

    @Override
    public List<OrderItemEntry> getOrderItemEntries() {
        List<OrderItemEntry> orderItemEntries = new ArrayList<>();
        for(Order order: orderList) {
            orderItemEntries.addAll(order.getOrderItemEntries());
        }
        return orderItemEntries;
    }
}

