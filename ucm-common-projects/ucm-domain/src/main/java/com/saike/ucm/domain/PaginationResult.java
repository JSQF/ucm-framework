package com.saike.ucm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by huawei on 12/26/15.
 */
public class PaginationResult<T> {
    private List<T> results;
    private int count;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
