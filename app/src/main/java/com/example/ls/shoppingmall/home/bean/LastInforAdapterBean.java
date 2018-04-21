package com.example.ls.shoppingmall.home.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/2/5.
 */

public class LastInforAdapterBean {

    /**
     * errorCode : null
     * msg : null
     * data : [{"commodityId":1,"originalPrice":"155.00","sellingPrice":"133.00","minusPrice":"22.00","title":"自研方剂标题","subtitle":"自研方副标题","description":"采用新西兰本土无污染无危害天然薰衣草，填充物绝不含废旧回收及有毒害材料；蓬松柔软不易变形，高度适中可以保护颈椎，缓解疲劳,能改善睡眠,同时还有驱蚊以及提高免疫力作用,适合各个年龄阶层的人使用哦~","thumbnailPicUrls":"01Ellis-chengrenzhen/suolue/00.jpg","remarks":"您值得拥有~","favorites":false},{"commodityId":2,"originalPrice":"137.00","sellingPrice":"115.00","minusPrice":"22.00","title":"进口保健品标题","subtitle":"进口保健副标题","description":"蓝莓含有丰富的营养成份，对改善人体健康有极大益处。其中抗氧化物（主要为花青素和紫檀芪）的含量超过其他任何一种水果或蔬菜。自由基是在人体活动过程中产生的，是引起多种疾病的有害物质。蓝莓中的抗氧化物可以清除这些自由基。紫檀芪还能调节血脂和血糖浓度，使它们保持在健康水平。蓝莓对保持良好的视力具有重要作用, 还可以帮助预防黄斑变性和记忆力衰退。另外，蓝莓含有丰富的维生素C和锰，能够改善免疫系统，促进骨骼发育。康加美护眼咀嚼片采用野生蓝莓为原料，花青素含量尤其高，时时刻刻保护您的健康。","thumbnailPicUrls":"02BIlllanmei90/suolue/00.jpg","remarks":"您值得拥有~","favorites":false}]
     * pageIndex : 0
     * pageSize : 3
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
    private List<DataEntity> data;

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalItems(int totalItems) {
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

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalItems() {
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
         * commodityId : 1
         * originalPrice : 155.00
         * sellingPrice : 133.00
         * minusPrice : 22.00
         * title : 自研方剂标题
         * subtitle : 自研方副标题
         * description : 采用新西兰本土无污染无危害天然薰衣草，填充物绝不含废旧回收及有毒害材料；蓬松柔软不易变形，高度适中可以保护颈椎，缓解疲劳,能改善睡眠,同时还有驱蚊以及提高免疫力作用,适合各个年龄阶层的人使用哦~
         * thumbnailPicUrls : 01Ellis-chengrenzhen/suolue/00.jpg
         * remarks : 您值得拥有~
         * favorites : false
         */
/*
* http://resource.51ququ.com/01Ellis-chengrenzhen/suolue/00.jpg
* */
        private int commodityId;
        private String originalPrice;
        private String sellingPrice;
        private String minusPrice;
        private String title;
        private String subtitle;
        private String description;
        private String thumbnailPicUrls;
        private String remarks;
        private boolean favorites;

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public void setOriginalPrice(String originalPrice) {
            this.originalPrice = originalPrice;
        }

        public void setSellingPrice(String sellingPrice) {
            this.sellingPrice = sellingPrice;
        }

        public void setMinusPrice(String minusPrice) {
            this.minusPrice = minusPrice;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setThumbnailPicUrls(String thumbnailPicUrls) {
            this.thumbnailPicUrls = thumbnailPicUrls;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public void setFavorites(boolean favorites) {
            this.favorites = favorites;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public String getOriginalPrice() {
            return originalPrice;
        }

        public String getSellingPrice() {
            return sellingPrice;
        }

        public String getMinusPrice() {
            return minusPrice;
        }

        public String getTitle() {
            return title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getDescription() {
            return description;
        }

        public String getThumbnailPicUrls() {
            return thumbnailPicUrls;
        }

        public String getRemarks() {
            return remarks;
        }

        public boolean getFavorites() {
            return favorites;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "commodityId=" + commodityId +
                    ", originalPrice='" + originalPrice + '\'' +
                    ", sellingPrice='" + sellingPrice + '\'' +
                    ", minusPrice='" + minusPrice + '\'' +
                    ", title='" + title + '\'' +
                    ", subtitle='" + subtitle + '\'' +
                    ", description='" + description + '\'' +
                    ", thumbnailPicUrls='" + thumbnailPicUrls + '\'' +
                    ", remarks='" + remarks + '\'' +
                    ", favorites=" + favorites +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LastInforAdapterBean{" +
                "errorCode='" + errorCode + '\'' +
                ", msg='" + msg + '\'' +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", totalPages=" + totalPages +
                ", totalItems=" + totalItems +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
