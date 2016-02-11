package org.anyframe.cloud.data.domain;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

public abstract class AbstractPageRequest implements AnyframePageable, Serializable {
    private static final long serialVersionUID = 1232825578694716872L;

    private final int page;
    private final int size;
    @Value(value = "${}")
    private final int limit;
    @Value(value = "${}")
    private final int offset;

    public AbstractPageRequest(int offset, int limit) {
        if(offset < 1) {
            throw new IllegalArgumentException("PageRequest offset must not be less than one!");
        } else if(limit < 1) {
            throw new IllegalArgumentException("PageRequest limit must not be less than one!");
        } else {
            this.offset = offset;
            this.page = offset / limit;
            this.limit = limit;
            this.size = limit;
        }
    }

    public int getPageSize() {
        return this.size;
    }

    public int getPageNumber() {
        return this.page;
    }

    @Override
    public int getOffset() {
        return this.offset;
    }

    @Override
    public int getLimit() {
        return this.limit;
    }

    public int hashCode() {
        boolean prime = true;
        byte result = 1;
        int result1 = 31 * result + this.page;
        result1 = 31 * result1 + this.limit;
        return result1;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj != null && this.getClass() == obj.getClass()) {
            AbstractPageRequest other = (AbstractPageRequest)obj;
            return this.page == other.page && this.limit == other.limit;
        } else {
            return false;
        }
    }
}
