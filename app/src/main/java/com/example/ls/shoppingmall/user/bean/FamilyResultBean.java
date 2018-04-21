package com.example.ls.shoppingmall.user.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2017/12/29.
 */

public class FamilyResultBean {
    /**
     * RESMSG : 查询成功
     * RESOBJ : [{"famNo":null,"userId":"20171225190937077965","famRelation":"父子","famName":"父子","famSex":"父子","famAge":null,"famSickHis":"父子"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父亲","famName":"王","famSex":"23","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父亲","famName":"王","famSex":"23","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父亲","famName":"王","famSex":"23","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父亲","famName":"王","famSex":"23","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父亲","famName":"王","famSex":"23","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父亲","famName":"王","famSex":"23","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父亲","famName":"王","famSex":"23","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父亲","famName":"王","famSex":"23","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父亲","famName":"王","famSex":"23","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父亲","famName":"王","famSex":"23","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父亲","famName":"王","famSex":"23","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父亲","famName":"王","famSex":"23","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父亲","famName":"王","famSex":"23","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"父子","famName":"王","famSex":"51","famAge":null,"famSickHis":"别跟"},{"famNo":null,"userId":"20171225190937077965","famRelation":"港囧","famName":"你摸","famSex":"51","famAge":null,"famSickHis":"OMG民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"爸爸","famName":"王","famSex":"33","famAge":null,"famSickHis":"哦民工"},{"famNo":null,"userId":"20171225190937077965","famRelation":"你好呀","famName":"王震","famSex":"51","famAge":null,"famSickHis":"没您额定"}]
     * RESCOD : 000000
     */
    private String RESMSG;
    private String RESCOD;
    private List<RESOBJEntity> RESOBJ;

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

    public List<RESOBJEntity> getRESOBJ() {
        return RESOBJ;
    }

    public void setRESOBJ(List<RESOBJEntity> RESOBJ) {
        this.RESOBJ = RESOBJ;
    }

    public static class RESOBJEntity {
        /**
         * famNo : null
         * userId : 20171225190937077965
         * famRelation : 父子
         * famName : 父子
         * famSex : 父子
         * famAge : null
         * famSickHis : 父子
         */

        private String famNo;
        private String userId;
        private String famRelation;
        private String famName;
        private String famSex;
        private String famAge;
        private String famSickHis;

        public String getFamNo() {
            return famNo;
        }

        public void setFamNo(String famNo) {
            this.famNo = famNo;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getFamRelation() {
            return famRelation;
        }

        public void setFamRelation(String famRelation) {
            this.famRelation = famRelation;
        }

        public String getFamName() {
            return famName;
        }

        public void setFamName(String famName) {
            this.famName = famName;
        }

        public String getFamSex() {
            return famSex;
        }

        public void setFamSex(String famSex) {
            this.famSex = famSex;
        }

        public String getFamAge() {
            return famAge;
        }

        public void setFamAge(String famAge) {
            this.famAge = famAge;
        }

        public String getFamSickHis() {
            return famSickHis;
        }

        public void setFamSickHis(String famSickHis) {
            this.famSickHis = famSickHis;
        }
    }
}
