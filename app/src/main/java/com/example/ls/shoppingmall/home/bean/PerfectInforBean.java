package com.example.ls.shoppingmall.home.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/1/30.
 */

public class PerfectInforBean {

    /**
     * RESMSG : 申请成功
     * RESOBJ : [{"sypNo":"0001","sypName":"头晕","bodNo":null,"orgNo":null},{"sypNo":"0002","sypName":"头疼","bodNo":null,"orgNo":null},{"sypNo":"0003","sypName":"嗜睡","bodNo":null,"orgNo":null},{"sypNo":"0004","sypName":"发热","bodNo":null,"orgNo":null},{"sypNo":"0005","sypName":"头痛","bodNo":null,"orgNo":null}]
     * RESCOD : 000000
     */

    private String RESMSG;
    private String RESCOD;
    private List<RESOBJBean> RESOBJ;

    public void setRESMSG(String RESMSG) {
        this.RESMSG = RESMSG;
    }

    public void setRESCOD(String RESCOD) {
        this.RESCOD = RESCOD;
    }

    public void setRESOBJ(List<RESOBJBean> RESOBJ) {
        this.RESOBJ = RESOBJ;
    }

    public String getRESMSG() {
        return RESMSG;
    }

    public String getRESCOD() {
        return RESCOD;
    }

    public List<RESOBJBean> getRESOBJ() {
        return RESOBJ;
    }

    public static class RESOBJBean {
        /**
         * sypNo : 0001
         * sypName : 头晕
         * bodNo : null
         * orgNo : null
         */

        private String sypNo;
        private String sypName;
        private String bodNo;
        private String orgNo;

        public void setSypNo(String sypNo) {
            this.sypNo = sypNo;
        }

        public void setSypName(String sypName) {
            this.sypName = sypName;
        }

        public void setBodNo(String bodNo) {
            this.bodNo = bodNo;
        }

        public void setOrgNo(String orgNo) {
            this.orgNo = orgNo;
        }

        public String getSypNo() {
            return sypNo;
        }

        public String getSypName() {
            return sypName;
        }

        public String getBodNo() {
            return bodNo;
        }

        public String getOrgNo() {
            return orgNo;
        }
    }
}
