package cdu.lj.entity;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/26 4:11 下午
 * @description： 分页对象
 * @modified By：
 * @version:
 */
public class Page {

    /**
     * 总数据量
     */
    private Integer total;

    /**
     * 当前页号
     */
    private Integer pageNum;

    /**
     * 当前分页大小
     */
    private Integer pageSize;

    /**
     * 当前最大页数
     */
    private Integer maxPage;

    /**
     * 数据
     */
    private Object data;

    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }
}
