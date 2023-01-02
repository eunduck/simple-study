package com.duck.core.wrapper;

import com.duck.study.dto.PageRequest;
import lombok.Data;

import java.util.List;

/**
 * Created by eunduck on 2022/11/03.
 */
@Data
public class PageContents<T> {
    private long offset;// current page
    private int limit; // unit per page
    private long total; // total size
    private List<T> contents;

    public PageContents(List<T> contents, PageRequest pageRequest, long total) {
        this.contents = contents;
        this.offset = pageRequest.getOffset();
        this.limit = pageRequest.getPageSize();
        this.total = total;
    }
}
