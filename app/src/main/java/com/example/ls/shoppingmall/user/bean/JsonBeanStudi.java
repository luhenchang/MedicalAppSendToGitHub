package com.example.ls.shoppingmall.user.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2017/12/6.
 */

public class JsonBeanStudi {

    /**
     * code : 200
     * msg :
     * data : [{"id":"1","pics":"['http://api.hm-space.yunkepai.net/resource/upload/20171205223640.png','http://api.hm-space.yunkepai.net/resource/upload/20171205223647.jpg']","type":"1","stype":"1","order":"0","status":"0","create_at":"2017-12-05 21:32:07","desc":"ioffice首页广告图"},{"id":"2","pics":"['http://api.hm-space.yunkepai.net/resource/upload/20171205223640.png']","type":"1","stype":"2","order":"0","status":"0","create_at":"2017-12-05 21:33:01","desc":"ioffice社区"},{"id":"3","pics":"['http://api.hm-space.yunkepai.net/resource/upload/20171205223640.png']","type":"1","stype":"3","order":"0","status":"0","create_at":"2017-12-05 21:33:42","desc":"ioffice活动"},{"id":"4","pics":"['http://api.hm-space.yunkepai.net/resource/upload/20171205223640.png']","type":"1","stype":"4","order":"0","status":"0","create_at":"2017-12-05 21:33:45","desc":"ioffice资讯"}]
     */

    private String code;
    private String msg;
    private List<DataEntity> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * id : 1
         * pics : ['http://api.hm-space.yunkepai.net/resource/upload/20171205223640.png','http://api.hm-space.yunkepai.net/resource/upload/20171205223647.jpg']
         * type : 1
         * stype : 1
         * order : 0
         * status : 0
         * create_at : 2017-12-05 21:32:07
         * desc : ioffice首页广告图
         */

        private String id;
        private String pics;
        private String type;
        private String stype;
        private String order;
        private String status;
        private String create_at;
        private String desc;

        public void setId(String id) {
            this.id = id;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setStype(String stype) {
            this.stype = stype;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getId() {
            return id;
        }

        public String getPics() {
            return pics;
        }

        public String getType() {
            return type;
        }

        public String getStype() {
            return stype;
        }

        public String getOrder() {
            return order;
        }

        public String getStatus() {
            return status;
        }

        public String getCreate_at() {
            return create_at;
        }

        public String getDesc() {
            return desc;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "id='" + id + '\'' +
                    ", pics='" + pics + '\'' +
                    ", type='" + type + '\'' +
                    ", stype='" + stype + '\'' +
                    ", order='" + order + '\'' +
                    ", status='" + status + '\'' +
                    ", create_at='" + create_at + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }
}
