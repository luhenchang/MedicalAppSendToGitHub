package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2018/3/20.
 */

public class DelectGoodsBean {


    /**
     * errorCode : 0
     * msg : null
     * data : null
     * pageIndex : null
     * pageSize : null
     * totalPages : null
     * totalItems : null
     * success : false
     */

    private int errorCode;
    private String msg;
    private String data;
    private String pageIndex;
    private String pageSize;
    private String totalPages;
    private String totalItems;
    private boolean success;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "DelectGoodsBean{" +
                "errorCode=" + errorCode +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                ", pageIndex='" + pageIndex + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", totalPages='" + totalPages + '\'' +
                ", totalItems='" + totalItems + '\'' +
                ", success=" + success +
                '}';
    }
}
