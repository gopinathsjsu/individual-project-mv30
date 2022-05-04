package com.cmpe_202.mv30.pojo;

import com.cmpe_202.mv30.constants.CategoryType;

import java.util.ArrayList;
import java.util.List;

public class OrderCategoryAggregate extends OrderAggregate{

    private final CategoryType categoryType;

    public OrderCategoryAggregate(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public Boolean isValid() {
        return ( this.getTotalCount() <= categoryType.getLimitCount()) && super.isValid();
    }

    @Override
    public List<String> getExcessAmountsLogs() {
        int totCount = super.getTotalCount();
        List<String> excessAmountLogs = new ArrayList<>();
        if(totCount>categoryType.getLimitCount()) {
            excessAmountLogs.add(String.format(" Category %s limited exceeded with total count of %s ",categoryType, totCount));
        }
        excessAmountLogs.addAll(super.getExcessAmountsLogs());
        return excessAmountLogs;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }
}
