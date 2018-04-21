package com.example.ls.shoppingmall.user.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/1/2.
 */

public class GoodsListBean {

    /**
     * errorCode : 10005
     * msg : 
     * data : [{"id":1,"commodity":{"commodityId":1,"sellingPrice":"133.00","title":"Ellis新西兰薰衣草枕头安神助眠护颈枕芯","thumbnailPicUrls":"01Ellis-chengrenzhen/suolue/00.jpg","stock":898,"open":true,"startTime":0,"endTime":0,"favorites":false}},{"id":2,"commodity":{"commodityId":3,"sellingPrice":"122.00","title":"加拿大BILL儿童牛初乳咀嚼片90粒","thumbnailPicUrls":"03bill-niuchuru90/suolue/00.jpg","stock":987,"open":true,"startTime":0,"endTime":0,"favorites":false}}]
     * pageIndex : null
     * pageSize : null
     * totalPages : null
     * totalItems : null
     * success : false
     */

    private int errorCode;
    private String msg;
    private String pageIndex;
    private String pageSize;
    private String totalPages;
    private String totalItems;
    private boolean success;
    private List<DataEntity> data;

    public void setErrorCode(int errorCode) {
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

    public int getErrorCode() {
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
         * id : 1
         * commodity : {"commodityId":1,"sellingPrice":"133.00","title":"Ellis新西兰薰衣草枕头安神助眠护颈枕芯","thumbnailPicUrls":"01Ellis-chengrenzhen/suolue/00.jpg","stock":898,"open":true,"startTime":0,"endTime":0,"favorites":false}
         */
        public boolean flag=false;
        private int id;
        private CommodityEntity commodity;

        public void setId(int id) {
            this.id = id;
        }

        public void setCommodity(CommodityEntity commodity) {
            this.commodity = commodity;
        }

        public int getId() {
            return id;
        }

        public CommodityEntity getCommodity() {
            return commodity;
        }

        public static class CommodityEntity {
            /**
             * commodityId : 1
             * sellingPrice : 133.00
             * title : Ellis新西兰薰衣草枕头安神助眠护颈枕芯
             * thumbnailPicUrls : 01Ellis-chengrenzhen/suolue/00.jpg
             * stock : 898
             * open : true
             * startTime : 0
             * endTime : 0
             * favorites : false
             */

            private int commodityId;
            private String sellingPrice;
            private String title;
            private String thumbnailPicUrls;
            private int stock;
            private boolean open;
            private int startTime;
            private int endTime;
            private boolean favorites;

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public void setSellingPrice(String sellingPrice) {
                this.sellingPrice = sellingPrice;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setThumbnailPicUrls(String thumbnailPicUrls) {
                this.thumbnailPicUrls = thumbnailPicUrls;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public void setOpen(boolean open) {
                this.open = open;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public void setEndTime(int endTime) {
                this.endTime = endTime;
            }

            public void setFavorites(boolean favorites) {
                this.favorites = favorites;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public String getSellingPrice() {
                return sellingPrice;
            }

            public String getTitle() {
                return title;
            }

            public String getThumbnailPicUrls() {
                return thumbnailPicUrls;
            }

            public int getStock() {
                return stock;
            }

            public boolean getOpen() {
                return open;
            }

            public int getStartTime() {
                return startTime;
            }

            public int getEndTime() {
                return endTime;
            }

            public boolean getFavorites() {
                return favorites;
            }
        }
    }
}
