package com.example.ls.shoppingmall.community.bean;

/**
 * Created by 路很长~ on 2018/3/21.
 */

public class TestBean {


    /**
     * errorCode : null
     * msg : null
     * data : {"id":5,"type":{"code":"news","name":"新闻","createTime":1513654308000,"valid":true,"sort":1},"title":"图文混排测试标题","summary":"图文混排测试摘要","author":"summer","content":"<p>为了页面的健壮性，我们常常需要使用overflow:hidden。有时候是为了防止布局被撑开，有时候是为了配合其它规则实现文字截断，还有时候纯粹是为了创建块级上下文。但是，很多人对这个属性是存在着一定的误解的。<\/p >\n\n<p>网上很多入门的资料或文章都只提到用overflow:hidden加固定的宽度（或高度）可以强制隐藏内部的超出容器的内容。包括之前我们在使用overflow:hidden创建块级上下文的方式来实现图文混排时，都认为overflow:hidden造成了特殊情况下的麻烦。但如果认真阅读一下css规范，会发现overflow:hidden其实并不一定隐藏溢出内容。<\/p >\n\n<p>< img alt=\"\" src=\"http://resource.51ququ.com/QQ%E6%88%AA%E5%9B%BE20180321163044.jpg\" style=\"height:71px; width:69px\" /><\/p >\n\n<p><strong>overflow:hidden并不隐藏所有溢出的子元素<\/strong><\/p >\n\n<p>&nbsp;<\/p >\n\n<p>对于overflow:hidden的最大误解时：当一个具有高度和宽度中至少一项的容器应用了overflow:hidden时，其内部的任何溢出的内容都将被剪裁（隐藏）。 但事实确实如此吗？答案是&ldquo;未必&rdquo;！事实是拥有overflow:hidden样式的块元素内部的元素溢出并不总是被隐藏，具体来说，需要同时满足以下条件：<\/p >\n\n<ol>\n\t<li>拥有overflow:hidden样式的块元素不具有position:relative和position:absolute样式；<\/li>\n\t<li>内部溢出的元素是通过position:absolute绝对定位；<\/li>\n<\/ol>\n","createTime":1521621341000,"open":true,"viewCount":1,"source":"原创","favorites":false}
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
         * id : 5
         * type : {"code":"news","name":"新闻","createTime":1513654308000,"valid":true,"sort":1}
         * title : 图文混排测试标题
         * summary : 图文混排测试摘要
         * author : summer
         * content : <p>为了页面的健壮性，我们常常需要使用overflow:hidden。有时候是为了防止布局被撑开，有时候是为了配合其它规则实现文字截断，还有时候纯粹是为了创建块级上下文。但是，很多人对这个属性是存在着一定的误解的。</p >

         <p>网上很多入门的资料或文章都只提到用overflow:hidden加固定的宽度（或高度）可以强制隐藏内部的超出容器的内容。包括之前我们在使用overflow:hidden创建块级上下文的方式来实现图文混排时，都认为overflow:hidden造成了特殊情况下的麻烦。但如果认真阅读一下css规范，会发现overflow:hidden其实并不一定隐藏溢出内容。</p >

         <p>< img alt="" src="http://resource.51ququ.com/QQ%E6%88%AA%E5%9B%BE20180321163044.jpg" style="height:71px; width:69px" /></p >

         <p><strong>overflow:hidden并不隐藏所有溢出的子元素</strong></p >

         <p>&nbsp;</p >

         <p>对于overflow:hidden的最大误解时：当一个具有高度和宽度中至少一项的容器应用了overflow:hidden时，其内部的任何溢出的内容都将被剪裁（隐藏）。 但事实确实如此吗？答案是&ldquo;未必&rdquo;！事实是拥有overflow:hidden样式的块元素内部的元素溢出并不总是被隐藏，具体来说，需要同时满足以下条件：</p >

         <ol>
         <li>拥有overflow:hidden样式的块元素不具有position:relative和position:absolute样式；</li>
         <li>内部溢出的元素是通过position:absolute绝对定位；</li>
         </ol>

         * createTime : 1521621341000
         * open : true
         * viewCount : 1
         * source : 原创
         * favorites : false
         */

        private int id;
        private TypeEntity type;
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

        @Override
        public String toString() {
            return "DataEntity{" +
                    "id=" + id +
                    ", type=" + type +
                    ", title='" + title + '\'' +
                    ", summary='" + summary + '\'' +
                    ", author='" + author + '\'' +
                    ", content='" + content + '\'' +
                    ", createTime=" + createTime +
                    ", open=" + open +
                    ", viewCount=" + viewCount +
                    ", source='" + source + '\'' +
                    ", favorites=" + favorites +
                    '}';
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

            @Override
            public String toString() {
                return "TypeEntity{" +
                        "code='" + code + '\'' +
                        ", name='" + name + '\'' +
                        ", createTime=" + createTime +
                        ", valid=" + valid +
                        ", sort=" + sort +
                        '}';
            }

        }

    }

    @Override
    public String toString() {
        return "TestBean{" +
                "errorCode='" + errorCode + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", pageIndex='" + pageIndex + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", totalPages='" + totalPages + '\'' +
                ", totalItems='" + totalItems + '\'' +
                ", success=" + success +
                '}';
    }
}
