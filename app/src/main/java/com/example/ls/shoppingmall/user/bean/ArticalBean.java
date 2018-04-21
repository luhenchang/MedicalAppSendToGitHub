package com.example.ls.shoppingmall.user.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/1/30.
 */

public class ArticalBean {


    /**
     * errorCode : null
     * msg : null
     * data : [{"id":1,"article":{"id":1,"type":{"code":"news","name":"新闻","createTime":1513654308000,"valid":true,"sort":1},"pic":"/pic.jpg","title":"十九大后，习近平对中国经济给出8大论断","summary":"今年的中央经济工作会议，是十九大后的首次，明年又是贯彻党的十九大精神的开局之年，中国经济将如何踏上新征程？有哪些新作为？","author":"神笔马良","createTime":1513654263000,"open":true,"source":"转载"}}]
     * pageIndex : 0
     * pageSize : 10
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
         * id : 1
         * article : {"id":1,"type":{"code":"news","name":"新闻","createTime":1513654308000,"valid":true,"sort":1},"pic":"/pic.jpg","title":"十九大后，习近平对中国经济给出8大论断","summary":"今年的中央经济工作会议，是十九大后的首次，明年又是贯彻党的十九大精神的开局之年，中国经济将如何踏上新征程？有哪些新作为？","author":"神笔马良","createTime":1513654263000,"open":true,"source":"转载"}
         */
        public  boolean flag=false;
        private int id;
        private ArticleBean article;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ArticleBean getArticle() {
            return article;
        }

        public void setArticle(ArticleBean article) {
            this.article = article;
        }

        public static class ArticleBean {
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
             */

            private int id;
            private TypeBean type;
            private String pic;
            private String title;
            private String summary;
            private String author;
            private long createTime;
            private boolean open;
            private String source;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public TypeBean getType() {
                return type;
            }

            public void setType(TypeBean type) {
                this.type = type;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public boolean isOpen() {
                return open;
            }

            public void setOpen(boolean open) {
                this.open = open;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public static class TypeBean {
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

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public boolean isValid() {
                    return valid;
                }

                public void setValid(boolean valid) {
                    this.valid = valid;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                @Override
                public String toString() {
                    return "TypeBean{" +
                            "code='" + code + '\'' +
                            ", name='" + name + '\'' +
                            ", createTime=" + createTime +
                            ", valid=" + valid +
                            ", sort=" + sort +
                            '}';
                }

            }

            @Override
            public String toString() {
                return "ArticleBean{" +
                        "id=" + id +
                        ", type=" + type +
                        ", pic='" + pic + '\'' +
                        ", title='" + title + '\'' +
                        ", summary='" + summary + '\'' +
                        ", author='" + author + '\'' +
                        ", createTime=" + createTime +
                        ", open=" + open +
                        ", source='" + source + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", article=" + article +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ArticalBean{" +
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
