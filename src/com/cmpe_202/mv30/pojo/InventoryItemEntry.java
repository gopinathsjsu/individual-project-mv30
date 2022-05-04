package com.cmpe_202.mv30.pojo;

import com.cmpe_202.mv30.constants.CategoryType;

public class InventoryItemEntry {

    private final Integer id;

    private final CategoryType categoryType;

    private final String name;

    private final Integer count;

    private final Integer costPerUnit;

    public InventoryItemEntry( Integer id, CategoryType categoryType, String name, Integer count, Integer costPerUnit) {
        this.id = id;
        this.categoryType = categoryType;
        this.name = name;
        this.count = count;
        this.costPerUnit = costPerUnit;
    }

    public Integer getId() {
        return this.id;
    }

    public CategoryType getCategoryType() {
        return this.categoryType;
    }

    public String getName() {
        return this.name;
    }

    public Integer getCount() {
        return this.count;
    }

    public Integer getCostPerUnit() { return this.costPerUnit;}
}
