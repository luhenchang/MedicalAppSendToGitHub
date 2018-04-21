package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2017/12/15.
 */

public class LoginResultBean{


    /**
     * RESMSG : 登录成功
     * RESOBJ : {"id":"20180102143153410808","pwd":null,"cnName":"呵呵嘿嘿","niName":"王飞","imgID":{"docId":null,"usrId":null,"title":null,"url":"images/upload/2018-01-05/1515123211668.jpg"},"cardID":null,"sex":"1","age":"20","weight":"6666","height":null,"telNo":"13512219573","mail":null,"reDate":"2018-01-02","reTime":"14:31:53","errTimes":null,"identity":"1","statu":null,"invcode":null,"lastTime":null,"caseHistory":"走吧","imgTXT":null}
     * RESCOD : 000000
     */

    private String RESMSG;
    private RESOBJBean RESOBJ;
    private String RESCOD;

    public void setRESMSG(String RESMSG) {
        this.RESMSG = RESMSG;
    }

    public void setRESOBJ(RESOBJBean RESOBJ) {
        this.RESOBJ = RESOBJ;
    }

    public void setRESCOD(String RESCOD) {
        this.RESCOD = RESCOD;
    }

    public String getRESMSG() {
        return RESMSG;
    }

    public RESOBJBean getRESOBJ() {
        return RESOBJ;
    }

    public String getRESCOD() {
        return RESCOD;
    }

    public static class RESOBJBean {
        /**
         * id : 20180102143153410808
         * pwd : null
         * cnName : 呵呵嘿嘿
         * niName : 王飞
         * imgID : {"docId":null,"usrId":null,"title":null,"url":"images/upload/2018-01-05/1515123211668.jpg"}
         * cardID : null
         * sex : 1
         * age : 20
         * weight : 6666
         * height : null
         * telNo : 13512219573
         * mail : null
         * reDate : 2018-01-02
         * reTime : 14:31:53
         * errTimes : null
         * identity : 1
         * statu : null
         * invcode : null
         * lastTime : null
         * caseHistory : 走吧
         * imgTXT : null
         */

        private String id;
        private String pwd;
        private String cnName;
        private String niName;
        private ImgIDBean imgID;
        private String cardID;
        private String sex;
        private String age;
        private String weight;
        private String height;
        private String telNo;
        private String mail;
        private String reDate;
        private String reTime;
        private String errTimes;
        private String identity;
        private String statu;
        private String invcode;
        private String lastTime;
        private String caseHistory;
        private String imgTXT;

        public void setId(String id) {
            this.id = id;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public void setCnName(String cnName) {
            this.cnName = cnName;
        }

        public void setNiName(String niName) {
            this.niName = niName;
        }

        public void setImgID(ImgIDBean imgID) {
            this.imgID = imgID;
        }

        public void setCardID(String cardID) {
            this.cardID = cardID;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public void setTelNo(String telNo) {
            this.telNo = telNo;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public void setReDate(String reDate) {
            this.reDate = reDate;
        }

        public void setReTime(String reTime) {
            this.reTime = reTime;
        }

        public void setErrTimes(String errTimes) {
            this.errTimes = errTimes;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public void setStatu(String statu) {
            this.statu = statu;
        }

        public void setInvcode(String invcode) {
            this.invcode = invcode;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public void setCaseHistory(String caseHistory) {
            this.caseHistory = caseHistory;
        }

        public void setImgTXT(String imgTXT) {
            this.imgTXT = imgTXT;
        }

        public String getId() {
            return id;
        }

        public String getPwd() {
            return pwd;
        }

        public String getCnName() {
            return cnName;
        }

        public String getNiName() {
            return niName;
        }

        public ImgIDBean getImgID() {
            return imgID;
        }

        public String getCardID() {
            return cardID;
        }

        public String getSex() {
            return sex;
        }

        public String getAge() {
            return age;
        }

        public String getWeight() {
            return weight;
        }

        public String getHeight() {
            return height;
        }

        public String getTelNo() {
            return telNo;
        }

        public String getMail() {
            return mail;
        }

        public String getReDate() {
            return reDate;
        }

        public String getReTime() {
            return reTime;
        }

        public String getErrTimes() {
            return errTimes;
        }

        public String getIdentity() {
            return identity;
        }

        public String getStatu() {
            return statu;
        }

        public String getInvcode() {
            return invcode;
        }

        public String getLastTime() {
            return lastTime;
        }

        public String getCaseHistory() {
            return caseHistory;
        }

        public String getImgTXT() {
            return imgTXT;
        }

        public static class ImgIDBean {
            /**
             * docId : null
             * usrId : null
             * title : null
             * url : images/upload/2018-01-05/1515123211668.jpg
             */

            private String docId;
            private String usrId;
            private String title;
            private String url;

            public void setDocId(String docId) {
                this.docId = docId;
            }

            public void setUsrId(String usrId) {
                this.usrId = usrId;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDocId() {
                return docId;
            }

            public String getUsrId() {
                return usrId;
            }

            public String getTitle() {
                return title;
            }

            public String getUrl() {
                return url;
            }

            @Override
            public String toString() {
                return "ImgIDBean{" +
                        "docId='" + docId + '\'' +
                        ", usrId='" + usrId + '\'' +
                        ", title='" + title + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "RESOBJBean{" +
                    "id='" + id + '\'' +
                    ", pwd='" + pwd + '\'' +
                    ", cnName='" + cnName + '\'' +
                    ", niName='" + niName + '\'' +
                    ", imgID=" + imgID +
                    ", cardID='" + cardID + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age='" + age + '\'' +
                    ", weight='" + weight + '\'' +
                    ", height='" + height + '\'' +
                    ", telNo='" + telNo + '\'' +
                    ", mail='" + mail + '\'' +
                    ", reDate='" + reDate + '\'' +
                    ", reTime='" + reTime + '\'' +
                    ", errTimes='" + errTimes + '\'' +
                    ", identity='" + identity + '\'' +
                    ", statu='" + statu + '\'' +
                    ", invcode='" + invcode + '\'' +
                    ", lastTime='" + lastTime + '\'' +
                    ", caseHistory='" + caseHistory + '\'' +
                    ", imgTXT='" + imgTXT + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginResultBean{" +
                "RESMSG='" + RESMSG + '\'' +
                ", RESOBJ=" + RESOBJ +
                ", RESCOD='" + RESCOD + '\'' +
                '}';
    }
}
