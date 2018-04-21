package com.example.ls.shoppingmall.user.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/1/2.
 */

public class DoctorColletListBean {

    /**
     * RESMSG : 查询成功
     * RESOBJ : [{"docId":"20180111105911139852","pwd":null,"cnName":"张文斌","imgID":null,"idCard":"120111198809304016","sex":"","age":"","tel":"13642153391","mail":"","lincence":"111111111111111","level":"10","hospital":{"hospInfNo":"0000040021","hospLevel":null,"hospName":"天津河西华一中医门诊部","hospAddr":null,"hospType":null,"hospArea":null,"hospTel":null},"offices":{"offiInfNo":"005001","offilEVEL1":null,"offiName1":null,"offilEVEL2":null,"offiName2":null},"positional":{"postInfNo":"004","postInfName":"主任医师"},"article":"1221","pwdTimes":null,"status":"00011000","invcode":"966528","lastTime":null,"inquiryTime":"00001000000000010000000010010000100000000000010000000000","imgTxt":null,"longitude":"","latitude":"","lastLogin":null,"adviceAuth":"111","adviceScope":null,"loginHost":null,"alipayAccount":null,"feeStandar":null,"wechatNo":null,"recipients":null,"contactNum":null,"deliveryAdd":null,"feeWechat":null,"balance":null,"wdFlag":null,"signupTime":null,"experience":null,"isCheck":null,"totalAdvice":null,"monthAdvice":null,"o2oOrder":null,"user":null},{"docId":"20171221161048677129","pwd":null,"cnName":"张翠山","imgID":null,"idCard":"123456678888","sex":"","age":"","tel":"17694969215","mail":"","lincence":"12323213","level":"10","hospital":{"hospInfNo":"0000010001","hospLevel":null,"hospName":"天津市胸科医院","hospAddr":null,"hospType":null,"hospArea":null,"hospTel":null},"offices":{"offiInfNo":"002006","offilEVEL1":null,"offiName1":null,"offilEVEL2":null,"offiName2":null},"positional":{"postInfNo":"001","postInfName":"医师"},"article":"","pwdTimes":null,"status":"00011100","invcode":"375536","lastTime":null,"inquiryTime":"01010000000000000100101001000000000000101000000000000010","imgTxt":null,"longitude":"","latitude":"","lastLogin":null,"adviceAuth":"110","adviceScope":null,"loginHost":null,"alipayAccount":null,"feeStandar":null,"wechatNo":null,"recipients":null,"contactNum":null,"deliveryAdd":null,"feeWechat":null,"balance":null,"wdFlag":null,"signupTime":null,"experience":null,"isCheck":null,"totalAdvice":null,"monthAdvice":null,"o2oOrder":null,"user":null},{"docId":"20180115183006679592","pwd":null,"cnName":"王磊","imgID":null,"idCard":"120105198404094818","sex":"","age":"","tel":"13512219574","mail":"","lincence":"fdsa","level":"10","hospital":{"hospInfNo":"0000050004","hospLevel":null,"hospName":"天津市南开医院","hospAddr":null,"hospType":null,"hospArea":null,"hospTel":null},"offices":{"offiInfNo":"001001","offilEVEL1":null,"offiName1":null,"offilEVEL2":null,"offiName2":null},"positional":{"postInfNo":"001","postInfName":"医师"},"article":"fdsafdsa","pwdTimes":null,"status":"00011100","invcode":"778322","lastTime":null,"inquiryTime":"","imgTxt":null,"longitude":"117.09569","latitude":"39.124576","lastLogin":null,"adviceAuth":"100","adviceScope":null,"loginHost":null,"alipayAccount":null,"feeStandar":null,"wechatNo":null,"recipients":null,"contactNum":null,"deliveryAdd":null,"feeWechat":null,"balance":null,"wdFlag":null,"signupTime":null,"experience":null,"isCheck":null,"totalAdvice":null,"monthAdvice":null,"o2oOrder":null,"user":null}]
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
         * docId : 20180111105911139852
         * pwd : null
         * cnName : 张文斌
         * imgID : null
         * idCard : 120111198809304016
         * sex : 
         * age : 
         * tel : 13642153391
         * mail : 
         * lincence : 111111111111111
         * level : 10
         * hospital : {"hospInfNo":"0000040021","hospLevel":null,"hospName":"天津河西华一中医门诊部","hospAddr":null,"hospType":null,"hospArea":null,"hospTel":null}
         * offices : {"offiInfNo":"005001","offilEVEL1":null,"offiName1":null,"offilEVEL2":null,"offiName2":null}
         * positional : {"postInfNo":"004","postInfName":"主任医师"}
         * article : 1221
         * pwdTimes : null
         * status : 00011000
         * invcode : 966528
         * lastTime : null
         * inquiryTime : 00001000000000010000000010010000100000000000010000000000
         * imgTxt : null
         * longitude : 
         * latitude : 
         * lastLogin : null
         * adviceAuth : 111
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
         * experience : null
         * isCheck : null
         * totalAdvice : null
         * monthAdvice : null
         * o2oOrder : null
         * user : null
         */
        public boolean flag=false;
        private String docId;
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
        private HospitalEntity hospital;
        private OfficesEntity offices;
        private PositionalEntity positional;
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
        private String experience;
        private String isCheck;
        private String totalAdvice;
        private String monthAdvice;
        private String o2oOrder;
        private String user;

        public void setDocId(String docId) {
            this.docId = docId;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public void setCnName(String cnName) {
            this.cnName = cnName;
        }

        public void setImgID(String imgID) {
            this.imgID = imgID;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public void setLincence(String lincence) {
            this.lincence = lincence;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public void setHospital(HospitalEntity hospital) {
            this.hospital = hospital;
        }

        public void setOffices(OfficesEntity offices) {
            this.offices = offices;
        }

        public void setPositional(PositionalEntity positional) {
            this.positional = positional;
        }

        public void setArticle(String article) {
            this.article = article;
        }

        public void setPwdTimes(String pwdTimes) {
            this.pwdTimes = pwdTimes;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setInvcode(String invcode) {
            this.invcode = invcode;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public void setInquiryTime(String inquiryTime) {
            this.inquiryTime = inquiryTime;
        }

        public void setImgTxt(String imgTxt) {
            this.imgTxt = imgTxt;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setLastLogin(String lastLogin) {
            this.lastLogin = lastLogin;
        }

        public void setAdviceAuth(String adviceAuth) {
            this.adviceAuth = adviceAuth;
        }

        public void setAdviceScope(String adviceScope) {
            this.adviceScope = adviceScope;
        }

        public void setLoginHost(String loginHost) {
            this.loginHost = loginHost;
        }

        public void setAlipayAccount(String alipayAccount) {
            this.alipayAccount = alipayAccount;
        }

        public void setFeeStandar(String feeStandar) {
            this.feeStandar = feeStandar;
        }

        public void setWechatNo(String wechatNo) {
            this.wechatNo = wechatNo;
        }

        public void setRecipients(String recipients) {
            this.recipients = recipients;
        }

        public void setContactNum(String contactNum) {
            this.contactNum = contactNum;
        }

        public void setDeliveryAdd(String deliveryAdd) {
            this.deliveryAdd = deliveryAdd;
        }

        public void setFeeWechat(String feeWechat) {
            this.feeWechat = feeWechat;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public void setWdFlag(String wdFlag) {
            this.wdFlag = wdFlag;
        }

        public void setSignupTime(String signupTime) {
            this.signupTime = signupTime;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public void setIsCheck(String isCheck) {
            this.isCheck = isCheck;
        }

        public void setTotalAdvice(String totalAdvice) {
            this.totalAdvice = totalAdvice;
        }

        public void setMonthAdvice(String monthAdvice) {
            this.monthAdvice = monthAdvice;
        }

        public void setO2oOrder(String o2oOrder) {
            this.o2oOrder = o2oOrder;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getDocId() {
            return docId;
        }

        public String getPwd() {
            return pwd;
        }

        public String getCnName() {
            return cnName;
        }

        public String getImgID() {
            return imgID;
        }

        public String getIdCard() {
            return idCard;
        }

        public String getSex() {
            return sex;
        }

        public String getAge() {
            return age;
        }

        public String getTel() {
            return tel;
        }

        public String getMail() {
            return mail;
        }

        public String getLincence() {
            return lincence;
        }

        public String getLevel() {
            return level;
        }

        public HospitalEntity getHospital() {
            return hospital;
        }

        public OfficesEntity getOffices() {
            return offices;
        }

        public PositionalEntity getPositional() {
            return positional;
        }

        public String getArticle() {
            return article;
        }

        public String getPwdTimes() {
            return pwdTimes;
        }

        public String getStatus() {
            return status;
        }

        public String getInvcode() {
            return invcode;
        }

        public String getLastTime() {
            return lastTime;
        }

        public String getInquiryTime() {
            return inquiryTime;
        }

        public String getImgTxt() {
            return imgTxt;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLastLogin() {
            return lastLogin;
        }

        public String getAdviceAuth() {
            return adviceAuth;
        }

        public String getAdviceScope() {
            return adviceScope;
        }

        public String getLoginHost() {
            return loginHost;
        }

        public String getAlipayAccount() {
            return alipayAccount;
        }

        public String getFeeStandar() {
            return feeStandar;
        }

        public String getWechatNo() {
            return wechatNo;
        }

        public String getRecipients() {
            return recipients;
        }

        public String getContactNum() {
            return contactNum;
        }

        public String getDeliveryAdd() {
            return deliveryAdd;
        }

        public String getFeeWechat() {
            return feeWechat;
        }

        public String getBalance() {
            return balance;
        }

        public String getWdFlag() {
            return wdFlag;
        }

        public String getSignupTime() {
            return signupTime;
        }

        public String getExperience() {
            return experience;
        }

        public String getIsCheck() {
            return isCheck;
        }

        public String getTotalAdvice() {
            return totalAdvice;
        }

        public String getMonthAdvice() {
            return monthAdvice;
        }

        public String getO2oOrder() {
            return o2oOrder;
        }

        public String getUser() {
            return user;
        }

        public static class HospitalEntity {
            /**
             * hospInfNo : 0000040021
             * hospLevel : null
             * hospName : 天津河西华一中医门诊部
             * hospAddr : null
             * hospType : null
             * hospArea : null
             * hospTel : null
             */

            private String hospInfNo;
            private String hospLevel;
            private String hospName;
            private String hospAddr;
            private String hospType;
            private String hospArea;
            private String hospTel;

            public void setHospInfNo(String hospInfNo) {
                this.hospInfNo = hospInfNo;
            }

            public void setHospLevel(String hospLevel) {
                this.hospLevel = hospLevel;
            }

            public void setHospName(String hospName) {
                this.hospName = hospName;
            }

            public void setHospAddr(String hospAddr) {
                this.hospAddr = hospAddr;
            }

            public void setHospType(String hospType) {
                this.hospType = hospType;
            }

            public void setHospArea(String hospArea) {
                this.hospArea = hospArea;
            }

            public void setHospTel(String hospTel) {
                this.hospTel = hospTel;
            }

            public String getHospInfNo() {
                return hospInfNo;
            }

            public String getHospLevel() {
                return hospLevel;
            }

            public String getHospName() {
                return hospName;
            }

            public String getHospAddr() {
                return hospAddr;
            }

            public String getHospType() {
                return hospType;
            }

            public String getHospArea() {
                return hospArea;
            }

            public String getHospTel() {
                return hospTel;
            }
        }

        public static class OfficesEntity {
            /**
             * offiInfNo : 005001
             * offilEVEL1 : null
             * offiName1 : null
             * offilEVEL2 : null
             * offiName2 : null
             */

            private String offiInfNo;
            private String offilEVEL1;
            private String offiName1;
            private String offilEVEL2;
            private String offiName2;

            public void setOffiInfNo(String offiInfNo) {
                this.offiInfNo = offiInfNo;
            }

            public void setOffilEVEL1(String offilEVEL1) {
                this.offilEVEL1 = offilEVEL1;
            }

            public void setOffiName1(String offiName1) {
                this.offiName1 = offiName1;
            }

            public void setOffilEVEL2(String offilEVEL2) {
                this.offilEVEL2 = offilEVEL2;
            }

            public void setOffiName2(String offiName2) {
                this.offiName2 = offiName2;
            }

            public String getOffiInfNo() {
                return offiInfNo;
            }

            public String getOffilEVEL1() {
                return offilEVEL1;
            }

            public String getOffiName1() {
                return offiName1;
            }

            public String getOffilEVEL2() {
                return offilEVEL2;
            }

            public String getOffiName2() {
                return offiName2;
            }
        }

        public static class PositionalEntity {
            /**
             * postInfNo : 004
             * postInfName : 主任医师
             */

            private String postInfNo;
            private String postInfName;

            public void setPostInfNo(String postInfNo) {
                this.postInfNo = postInfNo;
            }

            public void setPostInfName(String postInfName) {
                this.postInfName = postInfName;
            }

            public String getPostInfNo() {
                return postInfNo;
            }

            public String getPostInfName() {
                return postInfName;
            }
        }
    }
}
