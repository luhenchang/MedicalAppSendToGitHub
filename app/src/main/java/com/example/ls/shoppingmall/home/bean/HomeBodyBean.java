package com.example.ls.shoppingmall.home.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/1/29.
 */

public class HomeBodyBean {


    /**
     * RESMSG : 申请成功
     * RESOBJ : [{"bodNo":"001","bodName":"头部（前）","bodType":"01"},{"bodNo":"002","bodName":"头部（后）","bodType":"02"},{"bodNo":"003","bodName":"颈部（前）","bodType":"03"},{"bodNo":"004","bodName":"颈部（后）","bodType":"04"},{"bodNo":"005","bodName":"胸部","bodType":"05"},{"bodNo":"006","bodName":"腹部","bodType":"06"},{"bodNo":"007","bodName":"背部","bodType":"07"},{"bodNo":"008","bodName":"臀部","bodType":"08"},{"bodNo":"009","bodName":"腰部","bodType":"09"},{"bodNo":"010","bodName":"髋部","bodType":"10"},{"bodNo":"011","bodName":"手部","bodType":"11"},{"bodNo":"012","bodName":"脚部","bodType":"12"},{"bodNo":"013","bodName":"四肢","bodType":"13"},{"bodNo":"014","bodName":"肩部","bodType":"14"},{"bodNo":"015","bodName":"四肢（后）","bodType":"15"}]
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
         * bodNo : 001
         * bodName : 头部（前）
         * bodType : 01
         */

        private String bodNo;
        private String bodName;
        private String bodType;

        public String getBodNo() {
            return bodNo;
        }

        public void setBodNo(String bodNo) {
            this.bodNo = bodNo;
        }

        public String getBodName() {
            return bodName;
        }

        public void setBodName(String bodName) {
            this.bodName = bodName;
        }

        public String getBodType() {
            return bodType;
        }

        public void setBodType(String bodType) {
            this.bodType = bodType;
        }
    }
}
