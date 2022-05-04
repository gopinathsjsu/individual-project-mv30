package com.cmpe_202.mv30.constants;

public enum CategoryType {

    ESSENTIALS(3, 0), LUXURY(4, 0), MISCELLANEOUS(6, 0);

    private final Integer limitCount;

    private final Integer priorityId;

    private CategoryType(Integer limitCount, Integer priorityId) {
        this.limitCount = limitCount;
        this.priorityId = priorityId;
    }

    public Integer getLimitCount() {
        return this.limitCount;
    }

    public Integer getPriorityId() {
        return  this.priorityId;
    }
}
