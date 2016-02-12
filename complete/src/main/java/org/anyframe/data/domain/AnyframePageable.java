package org.anyframe.data.domain;

import org.springframework.data.domain.Sort;

public interface AnyframePageable {

    int getOffset();

    int getLimit();

    int getPageNumber();

    int getPageSize();

    Sort getSort();
}
