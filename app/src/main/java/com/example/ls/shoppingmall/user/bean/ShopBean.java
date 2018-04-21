package com.example.ls.shoppingmall.user.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/1/30.
 */

public class ShopBean {

    /**
     * errorCode : null
     * msg : null
     * data : [{"id":3,"commodity":{"commodityId":1,"sellingPrice":"133.00","title":"自研方剂标题","thumbnailPicUrls":"01Ellis-chengrenzhen/suolue/00.jpg","stock":898,"open":true,"favorites":false}},{"id":5,"commodity":{"commodityId":3,"sellingPrice":"122.00","title":"美容产品标题","thumbnailPicUrls":"03bill-niuchuru90/suolue/00.jpg","stock":987,"open":true,"favorites":false}}]
     * pageIndex : 0
     * pageSize : 4
     * totalPages : 1
     * totalItems : 2
     * success : true
     */

    private String errorCode;
    private String msg;
    private int pageIndex;
    private int pageSize;
    private int totalPages;
    private int totalItems;
    private boolean success;
    private List<DataBean> data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3
         * commodity : {"commodityId":1,"sellingPrice":"133.00","title":"自研方剂标题","thumbnailPicUrls":"01Ellis-chengrenzhen/suolue/00.jpg","stock":898,"open":true,"favorites":false}
         */

        private int id;
        private CommodityBean commodity;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public CommodityBean getCommodity() {
            return commodity;
        }

        public void setCommodity(CommodityBean commodity) {
            this.commodity = commodity;
        }

        public static class CommodityBean {
            /**
             * commodityId : 1
             * sellingPrice : 133.00
             * title : 自研方剂标题
             * thumbnailPicUrls : 01Ellis-chengrenzhen/suolue/00.jpg
             * stock : 898
             * open : true
             * favorites : false
             */

            private int commodityId;
            private String sellingPrice;
            private String title;
            private String thumbnailPicUrls;
            private int stock;
            private boolean open;
            private boolean favorites;

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getSellingPrice() {
                return sellingPrice;
            }

            public void setSellingPrice(String sellingPrice) {
                this.sellingPrice = sellingPrice;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumbnailPicUrls() {
                return thumbnailPicUrls;
            }

            public void setThumbnailPicUrls(String thumbnailPicUrls) {
                this.thumbnailPicUrls = thumbnailPicUrls;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public boolean isOpen() {
                return open;
            }

            public void setOpen(boolean open) {
                this.open = open;
            }

            public boolean isFavorites() {
                return favorites;
            }

            public void setFavorites(boolean favorites) {
                this.favorites = favorites;
            }
        }
    }
}
