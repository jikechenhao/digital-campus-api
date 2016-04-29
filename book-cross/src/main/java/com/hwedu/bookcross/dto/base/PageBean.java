package com.hwedu.bookcross.dto.base;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //总页数
    private Integer Totalpage;
    //每页显示数量
    private Integer pageSize = 10;
    //总记录数
    private Integer totalCount;
    //当前页,默认值第一页
    private Integer currentPage = 1;
    //用于封装结果集
    private List<T> list;

    public Integer getTotalpage() {
        return Totalpage;
    }

    public void setTotalpage(Integer totalpage) {
        Totalpage = totalpage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
