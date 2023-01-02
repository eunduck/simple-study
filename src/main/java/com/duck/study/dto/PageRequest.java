package com.duck.study.dto;

import com.duck.core.code.SearchCondition;
import lombok.Data;

/**
 * Created by eunduck on 2022/11/03.
 */
@Data
public class PageRequest {
    private int offset;
    private int pageSize;
    private SearchCondition condition;
    private String word;

    public int getOffset() {
        return this.offset <= 0 ? 0 : this.offset - 1;
    }

    public int getPageSize() {
        return this.pageSize <= 0 ? 10 : this.pageSize;
    }

    public void setCondition(SearchCondition condition) {
        this.condition = condition;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
