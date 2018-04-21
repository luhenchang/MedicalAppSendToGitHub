package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2018/2/28.
 */

public class SlefInforBean {

    /**
     * RESMSG : 查询成功
     * RESOBJ : {"famNo":"2","userId":"20180123151256558621","famRelation":"复习","famName":"哦哦模糊","famSex":"30","famAge":null,"famSickHis":"哥哥"}
     * RESCOD : 000000
     */

    private String RESMSG;
    private RESOBJEntity RESOBJ;
    private String RESCOD;

    public void setRESMSG(String RESMSG) {
        this.RESMSG = RESMSG;
    }

    public void setRESOBJ(RESOBJEntity RESOBJ) {
        this.RESOBJ = RESOBJ;
    }

    public void setRESCOD(String RESCOD) {
        this.RESCOD = RESCOD;
    }

    public String getRESMSG() {
        return RESMSG;
    }

    public RESOBJEntity getRESOBJ() {
        return RESOBJ;
    }

    public String getRESCOD() {
        return RESCOD;
    }

    public static class RESOBJEntity {
        /**
         * famNo : 2
         * userId : 20180123151256558621
         * famRelation : 复习
         * famName : 哦哦模糊
         * famSex : 30
         * famAge : null
         * famSickHis : 哥哥
         */

        private String famNo;
        private String userId;
        private String famRelation;
        private String famName;
        private String famSex;
        private String famAge;
        private String famSickHis;

        public void setFamNo(String famNo) {
            this.famNo = famNo;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setFamRelation(String famRelation) {
            this.famRelation = famRelation;
        }

        public void setFamName(String famName) {
            this.famName = famName;
        }

        public void setFamSex(String famSex) {
            this.famSex = famSex;
        }

        public void setFamAge(String famAge) {
            this.famAge = famAge;
        }

        public void setFamSickHis(String famSickHis) {
            this.famSickHis = famSickHis;
        }

        public String getFamNo() {
            return famNo;
        }

        public String getUserId() {
            return userId;
        }

        public String getFamRelation() {
            return famRelation;
        }

        public String getFamName() {
            return famName;
        }

        public String getFamSex() {
            return famSex;
        }

        public String getFamAge() {
            return famAge;
        }

        public String getFamSickHis() {
            return famSickHis;
        }
    }
}
