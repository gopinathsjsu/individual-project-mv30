package com.cmpe_202.mv30.pojo;

import com.cmpe_202.mv30.dao.InventoryTable;
import com.cmpe_202.mv30.interfaces.Order;

import java.util.Optional;

public class OrderBuilder {

    private final Order order;

    private final InventoryTable inventoryTable;

    public OrderBuilder() {
        this.order = new OrderAggregate();
        this.inventoryTable = InventoryTable.getInstance();
    }

    public void withOrderItem( OrderItemEntry orderItemEntry) throws Exception{
        Order targetOrder = null;
        InventoryItemEntry inventoryItemEntry = inventoryTable.findByName(orderItemEntry.getName());
        Optional<Order> existingOrder = order.getOrderList().stream().filter(currentOrder -> ((OrderCategoryAggregate)currentOrder).getCategoryType().equals(inventoryItemEntry.getCategoryType())).findAny();
        if(existingOrder.isPresent()) {
            targetOrder = existingOrder.get();
        } else {
            targetOrder = new OrderCategoryAggregate(inventoryItemEntry.getCategoryType());
            order.addItem(targetOrder);
        }
        targetOrder.addItem(new OrderLeaf( orderItemEntry.getName(), orderItemEntry.getCount()));
    }

    public Order build() {
        return order;
    }
}
