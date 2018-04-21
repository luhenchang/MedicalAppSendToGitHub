package com.example.ls.shoppingmall.home.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/2/6.
 */

public class LastInforAdatperBean1 {


    /**
     * errorCode : null
     * msg : null
     * data : [{"id":1,"type":{"code":"news","name":"新闻","createTime":1513654308000,"valid":true,"sort":1},"pic":"/pic.jpg","title":"十九大后，习近平对中国经济给出8大论断","summary":"今年的中央经济工作会议，是十九大后的首次，明年又是贯彻党的十九大精神的开局之年，中国经济将如何踏上新征程？有哪些新作为？","author":"神笔马良","createTime":1513654263000,"open":true,"source":"转载","favorites":false}]
     * pageIndex : 0
     * pageSize : 3
     * totalPages : 1
     * totalItems : 1
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
         * id : 1
         * type : {"code":"news","name":"新闻","createTime":1513654308000,"valid":true,"sort":1}
         * pic : /pic.jpg
         * title : 十九大后，习近平对中国经济给出8大论断
         * summary : 今年的中央经济工作会议，是十九大后的首次，明年又是贯彻党的十九大精神的开局之年，中国经济将如何踏上新征程？有哪些新作为？
         * author : 神笔马良
         * createTime : 1513654263000
         * open : true
         * source : 转载
         * favorites : false
         */

        private int id;
        private TypeEntity type;
        private String pic;
        private String title;
        private String summary;
        private String author;
        private long createTime;
        private boolean open;
        private String source;
        private boolean favorites;

        public void setId(int id) {
            this.id = id;
        }

        public void setType(TypeEntity type) {
            this.type = type;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setFavorites(boolean favorites) {
            this.favorites = favorites;
        }

        public int getId() {
            return id;
        }

        public TypeEntity getType() {
            return type;
        }

        public String getPic() {
            return pic;
        }

        public String getTitle() {
            return title;
        }

        public String getSummary() {
            return summary;
        }

        public String getAuthor() {
            return author;
        }

        public long getCreateTime() {
            return createTime;
        }

        public boolean getOpen() {
            return open;
        }

        public String getSource() {
            return source;
        }

        public boolean getFavorites() {
            return favorites;
        }

        public static class TypeEntity {
            /**
             * code : news
             * name : 新闻
             * createTime : 1513654308000
             * valid : true
             * sort : 1
             */

            private String code;
            private String name;
            private long createTime;
            private boolean valid;
            private int sort;

            public void setCode(String code) {
                this.code = code;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public void setValid(boolean valid) {
                this.valid = valid;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getCode() {
                return code;
            }

            public String getName() {
                return name;
            }

            public long getCreateTime() {
                return createTime;
            }

            public boolean getValid() {
                return valid;
            }

            public int getSort() {
                return sort;
            }
        }
    }
}
