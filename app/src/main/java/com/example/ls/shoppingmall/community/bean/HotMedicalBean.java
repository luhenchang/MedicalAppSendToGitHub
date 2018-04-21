package com.example.ls.shoppingmall.community.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/1/3.
 */

public class HotMedicalBean {

    /**
     * RESMSG : 信息修改成功
     * RESOBJ : [{"id":"20171221144642214592","pwd":null,"cnName":null,"imgID":null,"idCard":null,"sex":null,"age":null,"tel":null,"mail":null,"lincence":null,"level":null,"hospital":null,"offices":null,"positional":null,"article":null,"pwdTimes":null,"status":"00000000","invcode":null,"lastTime":null,"inquiryTime":null,"imgTxt":null,"longitude":null,"latitude":null,"lastLogin":null,"adviceAuth":null,"adviceScope":null,"loginHost":null,"alipayAccount":null,"feeStandar":null,"wechatNo":null,"recipients":null,"contactNum":null,"deliveryAdd":null,"feeWechat":null,"balance":null,"wdFlag":null,"signupTime":null},{"id":"20171221161048677129","pwd":null,"cnName":"刘文军","imgID":null,"idCard":null,"sex":null,"age":null,"tel":null,"mail":null,"lincence":null,"level":null,"hospital":{"hospInfNo":"0000010001","hospLevel":null,"hospName":"天津市胸科医院","hospAddr":null,"hospType":null,"hospArea":null,"hospTel":null},"offices":{"offiInfNo":"001","offiName":"内科"},"positional":{"postInfNo":"001","postInfName":"医师"},"article":null,"pwdTimes":null,"status":"00003100","invcode":null,"lastTime":null,"inquiryTime":null,"imgTxt":null,"longitude":"117.240919","latitude":"39.073266","lastLogin":null,"adviceAuth":null,"adviceScope":null,"loginHost":null,"alipayAccount":null,"feeStandar":null,"wechatNo":null,"recipients":null,"contactNum":null,"deliveryAdd":null,"feeWechat":null,"balance":null,"wdFlag":null,"signupTime":null}]
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
         * id : 20171221144642214592
         * pwd : null
         * cnName : null
         * imgID : null
         * idCard : null
         * sex : null
         * age : null
         * tel : null
         * mail : null
         * lincence : null
         * level : null
         * hospital : null
         * offices : null
         * positional : null
         * article : null
         * pwdTimes : null
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
         * loginHost : null
         * alipayAccount : null
         * feeStandar : null
         * wechatNo : null
         * recipients : null
         * contactNum : null
         * deliveryAdd : null
         * feeWechat : null
         * balance : null
         * wdFlag : null
         * signupTime : null
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
        private String loginHost;
        private String alipayAccount;
        private String feeStandar;
        private String wechatNo;
        private String recipients;
        private String contactNum;
        private String deliveryAdd;
        private String feeWechat;
        private String balance;
        private String wdFlag;
        private String signupTime;

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

        public String getLoginHost() {
            return loginHost;
        }

        public void setLoginHost(String loginHost) {
            this.loginHost = loginHost;
        }

        public String getAlipayAccount() {
            return alipayAccount;
        }

        public void setAlipayAccount(String alipayAccount) {
            this.alipayAccount = alipayAccount;
        }

        public String getFeeStandar() {
            return feeStandar;
        }

        public void setFeeStandar(String feeStandar) {
            this.feeStandar = feeStandar;
        }

        public String getWechatNo() {
            return wechatNo;
        }

        public void setWechatNo(String wechatNo) {
            this.wechatNo = wechatNo;
        }

        public String getRecipients() {
            return recipients;
        }

        public void setRecipients(String recipients) {
            this.recipients = recipients;
        }

        public String getContactNum() {
            return contactNum;
        }

        public void setContactNum(String contactNum) {
            this.contactNum = contactNum;
        }

        public String getDeliveryAdd() {
            return deliveryAdd;
        }

        public void setDeliveryAdd(String deliveryAdd) {
            this.deliveryAdd = deliveryAdd;
        }

        public String getFeeWechat() {
            return feeWechat;
        }

        public void setFeeWechat(String feeWechat) {
            this.feeWechat = feeWechat;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getWdFlag() {
            return wdFlag;
        }

        public void setWdFlag(String wdFlag) {
            this.wdFlag = wdFlag;
        }

        public String getSignupTime() {
            return signupTime;
        }

        public void setSignupTime(String signupTime) {
            this.signupTime = signupTime;
        }
    }
}
