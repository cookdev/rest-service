package org.anyframe.cloud.data.domain;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Hahn on 2016-02-11.
 */
public class AnyframePageImpl extends PageImpl {



    public AnyframePageImpl(List content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public AnyframePageImpl(List content) {
        super(content);
    }
}
