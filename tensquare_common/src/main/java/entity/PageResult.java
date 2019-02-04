package entity;

import java.util.List;

/**
 * @description: 返回分页实体类
 * @author: Young
 * @create: 2019-02-04 19:53
 **/

public class PageResult <T>{
    private long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
