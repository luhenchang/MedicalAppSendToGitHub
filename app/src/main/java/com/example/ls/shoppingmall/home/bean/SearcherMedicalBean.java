package com.example.ls.shoppingmall.home.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/4/18.
 */

public class SearcherMedicalBean {

    /**
     * RESMSG : 查询成功
     * RESOBJ : [{"searchId":"20180117113359477005","searchName1":"刘文军","searchName2":"天津市胸科医院","imgPath":"images/upload/2018-01-17/1516160163249.png","searchType":"1"},{"searchId":"20180111105911139852","searchName1":"张文斌","searchName2":"天津河西华一中医门诊部","imgPath":"images/upload/2018-03-23/1521796666778.png","searchType":"1"},{"searchId":"20180322163653231236","searchName1":"lalala","searchName2":"","imgPath":"images/upload/2018-01-13/1515844176496.jpg","searchType":"2"},{"searchId":"20180326144631221640","searchName1":"学校","searchName2":"","imgPath":"images/upload/2018-01-13/1515844495135.jpg","searchType":"2"},{"searchId":"20180410172009288210","searchName1":"121221","searchName2":"","imgPath":"images/upload/2018-01-13/1515844495135.jpg","searchType":"2"},{"searchId":"20180411174259632855","searchName1":"2342342134","searchName2":"","imgPath":"images/upload/2018-01-13/1515844495135.jpg","searchType":"2"},{"searchId":"20180414144745638456","searchName1":"内科团","searchName2":"","imgPath":"images/upload/2018-01-13/1515844495135.jpg","searchType":"2"}]
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
         * searchId : 20180117113359477005
         * searchName1 : 刘文军
         * searchName2 : 天津市胸科医院
         * imgPath : images/upload/2018-01-17/1516160163249.png
         * searchType : 1
         */

        private String searchId;
        private String searchName1;
        private String searchName2;
        private String imgPath;
        private String searchType;

        public void setSearchId(String searchId) {
            this.searchId = searchId;
        }

        public void setSearchName1(String searchName1) {
            this.searchName1 = searchName1;
        }

        public void setSearchName2(String searchName2) {
            this.searchName2 = searchName2;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public void setSearchType(String searchType) {
            this.searchType = searchType;
        }

        public String getSearchId() {
            return searchId;
        }

        public String getSearchName1() {
            return searchName1;
        }

        public String getSearchName2() {
            return searchName2;
        }

        public String getImgPath() {
            return imgPath;
        }

        public String getSearchType() {
            return searchType;
        }
    }
}
