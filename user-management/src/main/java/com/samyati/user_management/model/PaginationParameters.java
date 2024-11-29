package com.samyati.user_management.model;

public class PaginationParameters {
    private Integer pageIndex;
    private Integer pageTotal;
    private Long recordTotal;

    public PaginationParameters() {
    }

    public PaginationParameters(Integer pageIndex, Integer pageTotal, Long recordTotal) {
        this.pageIndex = pageIndex;
        this.pageTotal = pageTotal;
        this.recordTotal = recordTotal;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Long getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(Long recordTotal) {
        this.recordTotal = recordTotal;
    }

}
