package com.pongsky.cloud.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * 分页请求参数
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@Data
@NoArgsConstructor
public class PageQuery {

    public PageQuery(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    /**
     * 当前页号
     */
    @Range(min = 0)
    private Integer pageNumber;

    public Integer getPageNumber() {
        return pageNumber != null ? pageNumber : 0;
    }

    /**
     * 一页数量
     */
    @Range(min = 1, max = 200)
    private Integer pageSize;

    public Integer getPageSize() {
        return pageSize != null ? pageSize : 20;
    }

    /**
     * 总数量
     */
    private Long pageCount;

    /**
     * 总页数
     */
    private Long pageLine;

    /**
     * 偏移量
     */
    @JsonIgnore
    public Long getOffset() {
        return (long) getPageNumber() * getPageSize();
    }

    /**
     * 赋值总数量、总页数
     *
     * @param count 总数量
     */
    public void setPageCount(Long count) {
        if (count == null) {
            count = 0L;
        }
        this.pageCount = count;
        this.pageLine = this.pageCount % getPageSize() == 0
                ? this.pageCount / getPageSize()
                : this.pageCount / getPageSize() + 1;
    }

}
