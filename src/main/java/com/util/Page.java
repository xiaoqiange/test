package com.util;

import java.util.List;
import java.util.Map;

public class Page {
    //起始条数
    private Integer start;
    //总页数
    private Long totalPage;

    //总记录数
    private Long records;

    //每页总行数
    private Integer rows;

    //当前页码
    private Integer page;

    //存放Map分页数据
    private List<Map<String, Object>> gridMapData;
    
    /**
     * 分页数据,存放Object对象数据
     */
    private List<Object> gridObjectData;
    
    public Page() {
        this(1L, 20L, 20, 1);
    }


    public Page(Long totalPage, Long records, Integer rows, Integer page) {
        super();
        this.totalPage = totalPage;
        this.records = records;
        this.rows = rows;
        this.page = page;
    }


    public Integer getStart() {
        if(page!=null && rows!=null)
            this.start=(page-1)*rows;
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Long getTotalPage() {
        totalPage = records % rows == 0 ? records / rows : records / rows + 1;
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Map<String, Object>> getGridMapData() {
        return gridMapData;
    }

    public void setGridMapData(List<Map<String, Object>> gridMapData) {
        this.gridMapData = gridMapData;
    }


    public List<Object> getGridObjectData() {
        return gridObjectData;
    }


    public void setGridObjectData(List<Object> gridObjectData) {
        this.gridObjectData = gridObjectData;
    }
    
}
