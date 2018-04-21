package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2018/4/3.
 */

public class GotoShopBean {

    /**
     * errorCode : 10005
     * msg : 
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

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public String getData() {
        return data;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public String getPageSize() {
        return pageSize;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public boolean getSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "GotoShopBean{" +
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
