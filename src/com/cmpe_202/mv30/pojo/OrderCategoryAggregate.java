package com.cmpe_202.mv30.pojo;

import com.cmpe_202.mv30.constants.CategoryType;

import java.util.List;

public class OrderCategoryAggregate extends OrderAggregate{

    private final CategoryType categoryType;

    public OrderCategoryAggregate(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public Boolean isValid() {
        return ( getOrderListSize() <= categoryType.getLimitCount()) && super.isValid();
    }

    @Override
    public List<String> getExcessAmountsLogs() {
        return super.getExcessAmountsLogs();
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }
}
