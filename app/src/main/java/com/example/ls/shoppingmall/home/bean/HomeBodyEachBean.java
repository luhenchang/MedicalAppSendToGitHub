package com.example.ls.shoppingmall.home.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/1/29.
 */

public class HomeBodyEachBean {

    /**
     * RESMSG : 请求成功
     * RESOBJ : [{"orgNo":"001001","orgName":"眼","bodNo":"001"},{"orgNo":"001002","orgName":"头","bodNo":"001"}]
     * RESCOD : 000000
     */

    private String RESMSG;
    private String RESCOD;
    private List<RESOBJBean> RESOBJ;

    public String getRESMSG() {
        return RESMSG;
    }

    public void setRESMSG(String RESMSG) {
        this.RESMSG = RESMSG;
    }

    public String getRESCOD() {
        return RESCOD;
    }

    public void setRESCOD(String RESCOD) {
        this.RESCOD = RESCOD;
    }

    public List<RESOBJBean> getRESOBJ() {
        return RESOBJ;
    }

    public void setRESOBJ(List<RESOBJBean> RESOBJ) {
        this.RESOBJ = RESOBJ;
    }

    public static class RESOBJBean {
        /**
         * orgNo : 001001
         * orgName : 眼
         * bodNo : 001
         */

        private String orgNo;
        private String orgName;
        private String bodNo;

        public String getOrgNo() {
            return orgNo;
        }

        public void setOrgNo(String orgNo) {
            this.orgNo = orgNo;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getBodNo() {
            return bodNo;
        }

        public void setBodNo(String bodNo) {
            this.bodNo = bodNo;
        }

        @Override
        public String toString() {
            return "RESOBJBean{" +
                    "orgNo='" + orgNo + '\'' +
                    ", orgName='" + orgName + '\'' +
                    ", bodNo='" + bodNo + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HomeBodyEachBean{" +
                "RESMSG='" + RESMSG + '\'' +
                ", RESCOD='" + RESCOD + '\'' +
                ", RESOBJ=" + RESOBJ +
                '}';
    }
}
