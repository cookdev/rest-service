package org.anyframe.cloud.data.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageRequest extends AbstractPageRequest {
    private static final long serialVersionUID = -4541509938956089563L;
    private final Sort sort;

    public PageRequest(int offset, int limit) {
        this(offset, limit, (Sort)null);
    }

    public PageRequest(int offset, int limit, Direction direction, String... properties) {
        this(offset, limit, new Sort(direction, properties));
    }

    public PageRequest(int offset, int limit, Sort sort) {
        super(offset, limit);
        this.sort = sort;
    }

    public Sort getSort() {
        return this.sort;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(!(obj instanceof PageRequest)) {
            return false;
        } else {
            PageRequest that = (PageRequest)obj;
            boolean sortEqual = this.sort == null?that.sort == null:this.sort.equals(that.sort);
            return super.equals(that) && sortEqual;
        }
    }

    public int hashCode() {
        return 31 * super.hashCode() + (null == this.sort?0:this.sort.hashCode());
    }

    public String toString() {
        return String.format("PageRequest request [number: %d, size %d, sort: %s]", new Object[]{Integer.valueOf(this.getPageNumber()), Integer.valueOf(this.getLimit()), this.sort == null?null:this.sort.toString()});
    }
}
