package com.example.ls.shoppingmall.home.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/2/2.
 */

public class ArticalBean {


    /**
     * RESMSG : 请求成功
     * RESOBJ : [{"artNo":"2018020116045347300","artType":"00","artTitle":"文章标题1","artBrief":"文章简介1文章简介1文章简介1文章简介1文章简介1","artContent":null,"artPrice":"0.10","ispay":"1","artSick":null,"usrId":"20180123151256558621","pubTime":null},{"artNo":"2018020116045343900","artType":"00","artTitle":"文章标题2","artBrief":"文章简介2","artContent":null,"artPrice":"0.30","ispay":"0","artSick":null,"usrId":null,"pubTime":null}]
     * RESCOD : 000000
     */

    private String RESMSG;
    private String RESCOD;
    private List<RESOBJEntity> RESOBJ;

    public void setRESMSG(String RESMSG) {
        this.RESMSG = RESMSG;
    }

    public void setRESCOD(String RESCOD) {
        this.RESCOD = RESCOD;
    }

    public void setRESOBJ(List<RESOBJEntity> RESOBJ) {
        this.RESOBJ = RESOBJ;
    }

    public String getRESMSG() {
        return RESMSG;
    }

    public String getRESCOD() {
        return RESCOD;
    }

    public List<RESOBJEntity> getRESOBJ() {
        return RESOBJ;
    }

    public static class RESOBJEntity {
        /**
         * artNo : 2018020116045347300
         * artType : 00
         * artTitle : 文章标题1
         * artBrief : 文章简介1文章简介1文章简介1文章简介1文章简介1
         * artContent : null
         * artPrice : 0.10
         * ispay : 1
         * artSick : null
         * usrId : 20180123151256558621
         * pubTime : null
         */

        private String artNo;
        private String artType;
        private String artTitle;
        private String artBrief;
        private String artContent;
        private String artPrice;
        private String ispay;
        private String artSick;
        private String usrId;
        private String pubTime;

        public void setArtNo(String artNo) {
            this.artNo = artNo;
        }

        public void setArtType(String artType) {
            this.artType = artType;
        }

        public void setArtTitle(String artTitle) {
            this.artTitle = artTitle;
        }

        public void setArtBrief(String artBrief) {
            this.artBrief = artBrief;
        }

        public void setArtContent(String artContent) {
            this.artContent = artContent;
        }

        public void setArtPrice(String artPrice) {
            this.artPrice = artPrice;
        }

        public void setIspay(String ispay) {
            this.ispay = ispay;
        }

        public void setArtSick(String artSick) {
            this.artSick = artSick;
        }

        public void setUsrId(String usrId) {
            this.usrId = usrId;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public String getArtNo() {
            return artNo;
        }

        public String getArtType() {
            return artType;
        }

        public String getArtTitle() {
            return artTitle;
        }

        public String getArtBrief() {
            return artBrief;
        }

        public String getArtContent() {
            return artContent;
        }

        public String getArtPrice() {
            return artPrice;
        }

        public String getIspay() {
            return ispay;
        }

        public String getArtSick() {
            return artSick;
        }

        public String getUsrId() {
            return usrId;
        }

        public String getPubTime() {
            return pubTime;
        }

        @Override
        public String toString() {
            return "RESOBJEntity{" +
                    "artNo='" + artNo + '\'' +
                    ", artType='" + artType + '\'' +
                    ", artTitle='" + artTitle + '\'' +
                    ", artBrief='" + artBrief + '\'' +
                    ", artContent='" + artContent + '\'' +
                    ", artPrice='" + artPrice + '\'' +
                    ", ispay='" + ispay + '\'' +
                    ", artSick='" + artSick + '\'' +
                    ", usrId='" + usrId + '\'' +
                    ", pubTime='" + pubTime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ArticalBean{" +
                "RESMSG='" + RESMSG + '\'' +
                ", RESCOD='" + RESCOD + '\'' +
                ", RESOBJ=" + RESOBJ +
                '}';
    }
}
