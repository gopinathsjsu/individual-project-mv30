package com.cmpe_202.mv30.interfaces;

import com.cmpe_202.mv30.pojo.OrderItemEntry;

import java.util.List;

public abstract class Order {

    private String cardNumber;

    public void setCardNumber( String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public abstract void addItem( Order order) throws Exception ;

    public abstract List<Order> getOrderList() throws Exception;

    public abstract Boolean isValid();

    public abstract Integer getTotalCount();

    public abstract Integer getTotalAmount();

    public abstract List<String> getFulfillmentLogs();

    public abstract List<String> getExcessAmountsLogs();

    public abstract List<OrderItemEntry> getOrderItemEntries();
}
