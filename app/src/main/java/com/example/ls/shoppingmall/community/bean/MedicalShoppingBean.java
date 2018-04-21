package com.example.ls.shoppingmall.community.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/3/9.
 */

public class MedicalShoppingBean {

    /**
     * errorCode : null
     * msg : null
     * data : [{"id":5,"pic":"04BILL-haibaoyou500/suolue/00.jpg","title":"谷类粗纤产品标题"},{"id":3,"pic":"03bill-niuchuru90/suolue/00.jpg","title":"美容产品标题"},{"id":1,"pic":"01Ellis-chengrenzhen/suolue/00.jpg","title":"自研方剂标题"}]
     * pageIndex : null
     * pageSize : null
     * totalPages : null
     * totalItems : null
     * success : true
     */

    private String errorCode;
    private String msg;
    private String pageIndex;
    private String pageSize;
    private String totalPages;
    private String totalItems;
    private boolean success;
    private List<DataEntity> data;

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
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

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * id : 5
         * pic : 04BILL-haibaoyou500/suolue/00.jpg
         * title : 谷类粗纤产品标题
         */

        private int id;
        private String pic;
        private String title;

        public void setId(int id) {
            this.id = id;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public String getPic() {
            return pic;
        }

        public String getTitle() {
            return title;
        }
    }
}
