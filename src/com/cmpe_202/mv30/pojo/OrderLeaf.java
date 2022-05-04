package com.cmpe_202.mv30.pojo;

import com.cmpe_202.mv30.dao.InventoryTable;
import com.cmpe_202.mv30.interfaces.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderLeaf extends Order {

    private final String itemName;

    private final Integer itemCount;

    private final InventoryTable inventoryTable;

    public OrderLeaf( String itemName, Integer itemCount) {
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.inventoryTable = InventoryTable.getInstance();
    }

    @Override
    public void addItem(Order order) throws Exception {
        throw new Exception(" add item operation not supported ");
    }

    @Override
    public Integer getOrderListSize() throws Exception {
        throw new Exception(" get order list size operation not supported ");
    }

    @Override
    public List<Order> getOrderList() throws Exception {
        throw new Exception(" get order list operation not supported ");
    }

    @Override
    public Boolean isValid() {
        InventoryItemEntry inventoryItemEntry = inventoryTable.findByName(itemName);
        return inventoryItemEntry.getCount()>=itemCount;
    }

    @Override
    public Integer getTotal() {
        InventoryItemEntry inventoryItemEntry = inventoryTable.findByName(itemName);
        return itemCount*inventoryItemEntry.getCostPerUnit();
    }

    @Override
    public List<String> getFulfillmentLogs() {
        InventoryItemEntry inventoryItemEntry = inventoryTable.findByName(itemName);
        String message = String.format("%s,%s,$s,",itemName, itemCount, itemCount*inventoryItemEntry.getCostPerUnit());
        return Arrays.asList(message);
    }

    @Override
    public List<String> getExcessAmountsLogs() {
        List<String> excessAmountLogs = new ArrayList<>();
        InventoryItemEntry inventoryItemEntry = inventoryTable.findByName(itemName);
        excessAmountLogs.add(String.format(" Required count of %s is %s but inventory has only %s ",itemName, itemCount, inventoryItemEntry.getCount()));
        return excessAmountLogs;
    }

    @Override
    public List<OrderItemEntry> getOrderItemEntries() {
        List<OrderItemEntry> orderItemEntries = new ArrayList<>();
        orderItemEntries.add(new OrderItemEntry( itemName, itemCount));
        return orderItemEntries;
    }
}
