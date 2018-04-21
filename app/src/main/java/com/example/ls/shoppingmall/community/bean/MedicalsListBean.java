package com.example.ls.shoppingmall.community.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2017/12/18.
 * 热门专家
 */

public class MedicalsListBean {

    /**
     * RESMSG : 信息修改成功
     * RESOBJ : [{"id":"etsk81ahvy93gqhaqqr1","pwd":null,"cnName":null,"imgID":null,"idCard":null,"sex":null,"age":null,"tel":"13694013762","mail":null,"lincence":null,"level":"00","hospital":null,"offices":null,"positional":null,"article":null,"pwdTimes":"0","status":"00000000","invcode":null,"lastTime":null,"inquiryTime":null,"imgTxt":null,"longitude":null,"latitude":null,"lastLogin":null,"adviceAuth":null,"adviceScope":null},{"id":"0w8rb7u8zu7sll5nk9jm","pwd":null,"cnName":"123","imgID":null,"idCard":null,"sex":null,"age":null,"tel":"13696325874","mail":null,"lincence":"213","level":"00","hospital":"1","offices":"2","positional":null,"article":null,"pwdTimes":"0","status":"00000101","invcode":null,"lastTime":"2017-12-15 15:58:59","inquiryTime":null,"imgTxt":"","longitude":null,"latitude":null,"lastLogin":null,"adviceAuth":null,"adviceScope":null}]
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
         * id : etsk81ahvy93gqhaqqr1
         * pwd : null
         * cnName : null
         * imgID : null
         * idCard : null
         * sex : null
         * age : null
         * tel : 13694013762
         * mail : null
         * lincence : null
         * level : 00
         * hospital : null
         * offices : null
         * positional : null
         * article : null
         * pwdTimes : 0
         * status : 00000000
         * invcode : null
         * lastTime : null
         * inquiryTime : null
         * imgTxt : null
         * longitude : null
         * latitude : null
         * lastLogin : null
         * adviceAuth : null
         * adviceScope : null
         */

        private String id;
        private String pwd;
        private String cnName;
        private String imgID;
        private String idCard;
        private String sex;
        private String age;
        private String tel;
        private String mail;
        private String lincence;
        private String level;
        private String hospital;
        private String offices;
        private String positional;
        private String article;
        private String pwdTimes;
        private String status;
        private String invcode;
        private String lastTime;
        private String inquiryTime;
        private String imgTxt;
        private String longitude;
        private String latitude;
        private String lastLogin;
        private String adviceAuth;
        private String adviceScope;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getCnName() {
            return cnName;
        }

        public void setCnName(String cnName) {
            this.cnName = cnName;
        }

        public String getImgID() {
            return imgID;
        }

        public void setImgID(String imgID) {
            this.imgID = imgID;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getLincence() {
            return lincence;
        }

        public void setLincence(String lincence) {
            this.lincence = lincence;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public String getOffices() {
            return offices;
        }

        public void setOffices(String offices) {
            this.offices = offices;
        }

        public String getPositional() {
            return positional;
        }

        public void setPositional(String positional) {
            this.positional = positional;
        }

        public String getArticle() {
            return article;
        }

        public void setArticle(String article) {
            this.article = article;
        }

        public String getPwdTimes() {
            return pwdTimes;
        }

        public void setPwdTimes(String pwdTimes) {
            this.pwdTimes = pwdTimes;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getInvcode() {
            return invcode;
        }

        public void setInvcode(String invcode) {
            this.invcode = invcode;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public String getInquiryTime() {
            return inquiryTime;
        }

        public void setInquiryTime(String inquiryTime) {
            this.inquiryTime = inquiryTime;
        }

        public String getImgTxt() {
            return imgTxt;
        }

        public void setImgTxt(String imgTxt) {
            this.imgTxt = imgTxt;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(String lastLogin) {
            this.lastLogin = lastLogin;
        }

        public String getAdviceAuth() {
            return adviceAuth;
        }

        public void setAdviceAuth(String adviceAuth) {
            this.adviceAuth = adviceAuth;
        }

        public String getAdviceScope() {
            return adviceScope;
        }

        public void setAdviceScope(String adviceScope) {
            this.adviceScope = adviceScope;
        }
    }
}
