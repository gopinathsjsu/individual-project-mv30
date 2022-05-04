package com.cmpe_202.mv30.dao;

import com.cmpe_202.mv30.constants.CategoryType;
import com.cmpe_202.mv30.pojo.InventoryItemEntry;
import com.cmpe_202.mv30.pojo.OrderItemEntry;

import java.util.ArrayList;
import java.util.List;

public class InventoryTable {

    private final List<InventoryItemEntry> inventoryItemEntries;

    private static InventoryTable inventoryTable;

    private InventoryTable() {
        inventoryItemEntries = new ArrayList<>();
    }

    public void addItem(CategoryType categoryType, String name, Integer count, Integer costPerUnit) {
        inventoryItemEntries.add(new InventoryItemEntry( inventoryItemEntries.size(), categoryType, name, count, costPerUnit));
    }

    public InventoryItemEntry findByName( String name) {
        return inventoryItemEntries.stream().filter(inventoryItemEntry -> inventoryItemEntry.getName().equalsIgnoreCase(name)).findAny().get();
    }

    public void fulfill( List<OrderItemEntry> orderEntries) {
        for (OrderItemEntry orderItemEntry : orderEntries) {
            InventoryItemEntry inventoryItemEntry = findByName(orderItemEntry.getName());
            InventoryItemEntry inventoryItemEntryUpdated = new InventoryItemEntry( inventoryItemEntry.getId(),
                    inventoryItemEntry.getCategoryType(), inventoryItemEntry.getName(), inventoryItemEntry.getCount()- orderItemEntry.getCount(), inventoryItemEntry.getCostPerUnit());
            inventoryItemEntries.set( inventoryItemEntryUpdated.getId(), inventoryItemEntryUpdated);
        }
    }

    public static InventoryTable getInstance() {
        if(inventoryTable==null) {
            inventoryTable = new InventoryTable();
        }
        return inventoryTable;
    }
}
