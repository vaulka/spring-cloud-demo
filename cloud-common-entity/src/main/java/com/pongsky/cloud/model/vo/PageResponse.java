package com.pongsky.cloud.model.vo;

import com.pongsky.cloud.model.dto.PageQuery;
import lombok.Data;

import java.util.List;

/**
 * 分页响应数据
 *
 * @author pengsenhao
 * @create 2021-02-11
 */
@Data
public class PageResponse<T> {

    private static final long serialVersionUID = -1474452277295643002L;

    public PageResponse(List<T> content, PageQuery pageQuery, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
        this.pageNumber = pageQuery.getPageNumber();
        this.pageSize = pageQuery.getPageSize();
        this.totalPages = this.totalElements % this.pageSize == 0
                ? this.totalElements / this.pageSize
                : this.totalElements / this.pageSize + 1;
    }

    /**
     * 数据集合
     */
    private List<T> content;

    /**
     * 总页数
     */
    private Long totalPages;

    /**
     * 总数量
     */
    private Long totalElements;

    /**
     * 当前页码
     */
    private Integer pageNumber;

    /**
     * 页数量
     */
    private Integer pageSize;

}
