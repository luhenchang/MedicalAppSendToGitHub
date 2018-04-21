package com.example.ls.shoppingmall.home.bean;

/**
 * Created by 路很长~ on 2018/3/21.
 */

public class ArticalColectedVisible {

    /**
     * errorCode : null
     * msg : null
     * data : {"id":1,"type":{"code":"news","name":"新闻","createTime":1513654308000,"valid":true,"sort":1},"pic":"/pic.jpg","title":"十九大后，习近平对中国经济给出8大论断","summary":"今年的中央经济工作会议，是十九大后的首次，明年又是贯彻党的十九大精神的开局之年，中国经济将如何踏上新征程？有哪些新作为？","author":"神笔马良","content":"坚持稳中求进工作总基调。","createTime":1513654263000,"open":true,"viewCount":1,"source":"转载","favorites":true}
     * pageIndex : null
     * pageSize : null
     * totalPages : null
     * totalItems : null
     * success : true
     */

    private String errorCode;
    private String msg;
    private DataEntity data;
    private String pageIndex;
    private String pageSize;
    private String totalPages;
    private String totalItems;
    private boolean success;

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
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

    public String getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
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

    public static class DataEntity {
        /**
         * id : 1
         * type : {"code":"news","name":"新闻","createTime":1513654308000,"valid":true,"sort":1}
         * pic : /pic.jpg
         * title : 十九大后，习近平对中国经济给出8大论断
         * summary : 今年的中央经济工作会议，是十九大后的首次，明年又是贯彻党的十九大精神的开局之年，中国经济将如何踏上新征程？有哪些新作为？
         * author : 神笔马良
         * content : 坚持稳中求进工作总基调。
         * createTime : 1513654263000
         * open : true
         * viewCount : 1
         * source : 转载
         * favorites : true
         */

        private int id;
        private TypeEntity type;
        private String pic;
        private String title;
        private String summary;
        private String author;
        private String content;
        private long createTime;
        private boolean open;
        private int viewCount;
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

        public void setContent(String content) {
            this.content = content;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
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

        public String getContent() {
            return content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public boolean getOpen() {
            return open;
        }

        public int getViewCount() {
            return viewCount;
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
