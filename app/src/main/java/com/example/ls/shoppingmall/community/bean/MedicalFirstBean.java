package com.example.ls.shoppingmall.community.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2017/12/26.
 */

public class MedicalFirstBean {

    /**
     * RESMSG : 信息修改成功
     * RESOBJ : [{"id":"20171221144642214592","pwd":null,"cnName":null,"imgID":null,"idCard":null,"sex":null,"age":null,"tel":"13512219573","mail":null,"lincence":null,"level":"10","hospital":null,"offices":null,"positional":null,"article":null,"pwdTimes":"6","status":"00000000","invcode":null,"lastTime":"2017-12-21 15:58:49","inquiryTime":null,"imgTxt":null,"longitude":null,"latitude":null,"lastLogin":null,"adviceAuth":null,"adviceScope":null,"loginHost":null,"alipayAccount":null,"feeStandar":null,"wechatNo":null,"recipients":null,"contactNum":null,"deliveryAdd":null,"feeWechat":""},{"id":"20171221154910431399","pwd":null,"cnName":"1","imgID":null,"idCard":null,"sex":null,"age":null,"tel":"13821538305","mail":null,"lincence":"1","level":"11","hospital":"0000010008","offices":"007","positional":"006","article":null,"pwdTimes":"0","status":"00001101","invcode":null,"lastTime":"2017-12-22 15:43:05","inquiryTime":"00000000000000001001000000000000010100000000000000000000","imgTxt":"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAYAAAA5ZDbSAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA+ZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdFJlZj0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlUmVmIyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ0MgKFdpbmRvd3MpIiB4bXA6Q3JlYXRlRGF0ZT0iMjAxNy0wMi0wNFQxNTo0ODowMSswODowMCIgeG1wOk1vZGlmeURhdGU9IjIwMTctMDItMDRUMTY6NDM6MTkrMDg6MDAiIHhtcDpNZXRhZGF0YURhdGU9IjIwMTctMDItMDRUMTY6NDM6MTkrMDg6MDAiIGRjOmZvcm1hdD0iaW1hZ2UvcG5nIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkZBNTQ1QTlGRUFCNTExRTY4OUFEOTI5QzAzNzVCMkQwIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkZBNTQ1QUEwRUFCNTExRTY4OUFEOTI5QzAzNzVCMkQwIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmlu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","longitude":"117.220186","latitude":"39.193204","lastLogin":"2017-12-25 16:17:35","adviceAuth":"110","adviceScope":"1|2|3|4|5|","loginHost":null,"alipayAccount":null,"feeStandar":null,"wechatNo":null,"recipients":null,"contactNum":null,"deliveryAdd":null,"feeWechat":"80元"},{"id":"20171221161048677129","pwd":null,"cnName":"刘文军","imgID":null,"idCard":null,"sex":null,"age":null,"tel":"17694969215","mail":null,"lincence":"312155455454","level":"10","hospital":"0000010001","offices":"001","positional":"001","article":null,"pwdTimes":"0","status":"00003100","invcode":null,"lastTime":"2017-12-22 17:24:39","inquiryTime":"01000000000000000100101001000000000000100000000000000000","imgTxt":null,"longitude":"117.240919","latitude":"39.073266","lastLogin":"2017-12-22 17:24:19","adviceAuth":"110","adviceScope":"1|2|3|4|5|","loginHost":null,"alipayAccount":null,"feeStandar":null,"wechatNo":null,"recipients":null,"contactNum":null,"deliveryAdd":null,"feeWechat":"80元"},{"id":"20171222100620543463","pwd":null,"cnName":"1","imgID":null,"idCard":null,"sex":null,"age":null,"tel":"13752725197","mail":null,"lincence":"1","level":"11","hospital":"0000010001","offices":"001","positional":"001","article":null,"pwdTimes":"2","status":"00013100","invcode":null,"lastTime":"2017-12-22 15:20:04","inquiryTime":"00000000000000000000000000000000000000000000000000000000","imgTxt":"data:image/jpeg;base64,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jjUs7HAUDJJrqYvBp3Dzr35e4ROf1Nb2naRZaaM20Xznq7ct+dJyRjWzSjBe5qyr4c0f+zLYyTAG5lHzf7I9K2aKKzbufP1asqs3OW7CvNvid/yFrP8A64f+zGvSa82+J3/IWs/+uH/sxoMzi6KKKACiiigD0n4Zf8ge7/6+P/ZRXaV47oXii+0K2kgtIrZ1kfeTKrE5xjsR6Vqf8LE1j/n2sP8Avh//AIqgD06ivMf+Fiax/wA+1h/3w/8A8VR/wsTWP+faw/74f/4qgD06ivMf+Fiax/z7WH/fD/8AxVH/AAsTWP8An2sP++H/APiqAPTqK8x/4WJrH/PtYf8AfD//ABVH/CxNY/59rD/vh/8A4qgD06ivMf8AhYmsf8+1h/3w/wD8VR/wsTWP+faw/wC+H/8AiqAPTqK8x/4WJrH/AD7WH/fD/wDxVH/CxNY/59rD/vh//iqAPTqK8x/4WJrH/PtYf98P/wDFUf8ACxNY/wCfaw/74f8A+KoA9OorzH/hYmsf8+1h/wB8P/8AFUf8LE1j/n2sP++H/wDiqAPTqK8x/wCFiax/z7WH/fD/APxVH/CxNY/59rD/AL4f/wCKoA9OorzH/hYmsf8APtYf98P/APFUf8LE1j/n2sP++H/+KoA9OorzH/hYmsf8+1h/3w//AMVR/wALE1j/AJ9rD/vh/wD4qgD06ivMf+Fiax/z7WH/AHw//wAVR/wsTWP+faw/74f/AOKoA9OorzH/AIWJrH/PtYf98P8A/FUf8LE1j/n2sP8Avh//AIqgD06ivMf+Fiax/wA+1h/3w/8A8VR/wsTWP+faw/74f/4qgD06ivMf+Fiax/z7WH/fD/8AxVH/AAsTWP8An2sP++H/APiqAPTqK8x/4WJrH/PtYf8AfD//ABVH/CxNY/59rD/vh/8A4qgD06ivMf8AhYmsf8+1h/3w/wD8VR/wsTWP+faw/wC+H/8AiqAPTqK8x/4WJrH/AD7WH/fD/wDxVH/CxNY/59rD/vh//iqAPTqK8x/4WJrH/PtYf98P/wDFUf8ACxNY/wCfaw/74f8A+KoA9OorzH/hYmsf8+1h/wB8P/8AFUf8LE1j/n2sP++H/wDiqAPTqK8x/wCFiax/z7WH/fD/APxVH/CxNY/59rD/AL4f/wCKoA9OorzH/hYmsf8APtYf98P/APFUf8LE1j/n2sP++H/+KoA9Orzb4nf8haz/AOuH/sxqL/hYmsf8+1h/3w//AMVWHruuXWu3Ec93HCjRpsAiBAxnPcmgDMooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKAP//Z","longitude":"117.341179","latitude":"39.116514","lastLogin":"2017-12-22 15:18:32","adviceAuth":"111","adviceScope":"1|2|3|4|5|","loginHost":null,"alipayAccount":null,"feeStandar":null,"wechatNo":null,"recipients":null,"contactNum":null,"deliveryAdd":null,"feeWechat":""}]
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
         * tel : 13512219573
         * mail : null
         * lincence : null
         * level : 10
         * hospital : null
         * offices : null
         * positional : null
         * article : null
         * pwdTimes : 6
         * status : 00000000
         * invcode : null
         * lastTime : 2017-12-21 15:58:49
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
         * feeWechat : 
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
    }
}
