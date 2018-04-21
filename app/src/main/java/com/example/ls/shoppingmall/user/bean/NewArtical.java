package com.example.ls.shoppingmall.user.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/3/26.
 */

public class NewArtical {

    /**
     * errorCode : null
     * msg : null
     * data : {"content":[{"id":5,"type":{"code":"news","name":"新闻","createTime":1513654308000,"valid":true,"sort":1},"pic":"article/1521623939312_文章缩略图.png","title":"图文混排测试标题","summary":"图文混排测试摘要","author":"summer","createTime":1521623960000,"open":true,"source":"原创","favorites":false},{"id":4,"type":{"code":"health","name":"养生","createTime":1513654400000,"valid":true,"sort":2},"pic":"article/1521623939312_文章缩略图.png","title":"标题3","summary":"摘要3","author":"作者3","createTime":1521444936000,"open":true,"source":"转载","favorites":false},{"id":3,"type":{"code":"health","name":"养生","createTime":1513654400000,"valid":true,"sort":2},"pic":"article/1521623939312_文章缩略图.png","title":"标题2","summary":"摘要2","author":"作者2","createTime":1521444853000,"open":true,"source":"转载","favorites":false}],"last":false,"totalPages":2,"totalElements":5,"sort":null,"first":true,"numberOfElements":3,"size":3,"number":0}
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

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
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

    public static class DataEntity {
        /**
         * content : [{"id":5,"type":{"code":"news","name":"新闻","createTime":1513654308000,"valid":true,"sort":1},"pic":"article/1521623939312_文章缩略图.png","title":"图文混排测试标题","summary":"图文混排测试摘要","author":"summer","createTime":1521623960000,"open":true,"source":"原创","favorites":false},{"id":4,"type":{"code":"health","name":"养生","createTime":1513654400000,"valid":true,"sort":2},"pic":"article/1521623939312_文章缩略图.png","title":"标题3","summary":"摘要3","author":"作者3","createTime":1521444936000,"open":true,"source":"转载","favorites":false},{"id":3,"type":{"code":"health","name":"养生","createTime":1513654400000,"valid":true,"sort":2},"pic":"article/1521623939312_文章缩略图.png","title":"标题2","summary":"摘要2","author":"作者2","createTime":1521444853000,"open":true,"source":"转载","favorites":false}]
         * last : false
         * totalPages : 2
         * totalElements : 5
         * sort : null
         * first : true
         * numberOfElements : 3
         * size : 3
         * number : 0
         */

        private boolean last;
        private int totalPages;
        private int totalElements;
        private String sort;
        private boolean first;
        private int numberOfElements;
        private int size;
        private int number;
        private List<ContentEntity> content;

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<ContentEntity> getContent() {
            return content;
        }

        public void setContent(List<ContentEntity> content) {
            this.content = content;
        }

        public static class ContentEntity {
            /**
             * id : 5
             * type : {"code":"news","name":"新闻","createTime":1513654308000,"valid":true,"sort":1}
             * pic : article/1521623939312_文章缩略图.png
             * title : 图文混排测试标题
             * summary : 图文混排测试摘要
             * author : summer
             * createTime : 1521623960000
             * open : true
             * source : 原创
             * favorites : false
             */
            public boolean flag=false;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public TypeEntity getType() {
                return type;
            }

            public void setType(TypeEntity type) {
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

            public boolean isFavorites() {
                return favorites;
            }

            public void setFavorites(boolean favorites) {
                this.favorites = favorites;
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
            }
        }
    }
}
