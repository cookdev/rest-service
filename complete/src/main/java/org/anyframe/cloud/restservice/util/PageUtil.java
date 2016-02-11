package org.anyframe.cloud.restservice.util;

import org.anyframe.cloud.data.domain.AnyframePageable;
import org.springframework.data.domain.Pageable;

/**
 * Created by Hahn on 2016-02-11.
 */
public class PageUtil {

    public static Pageable convertToSpringDataPageRequest(AnyframePageable anyframePage){
        return new org.springframework.data.domain.PageRequest(anyframePage.getPageNumber(), anyframePage.getPageSize(), anyframePage.getSort());
    }
}
