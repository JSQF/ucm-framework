package com.saike.ucm.domain.dao;

/**
 * Created by huawei on 12/26/15.
 */
public class Pagination<T> {

    private T condition;
    private int start;
    private int length;

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
