package com.example.ls.shoppingmall.community.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/3/12.
 */

public class ComMeTeamInforBean {


    /**
     * RESMSG : 查询成功
     * RESOBJ : [{"id":null,"dtmNo":"0180313165151485839","dtmName":"发送","docNo":"20180111105911139852","dtmType":"1","dtmDisc":"发","identity":null,"statu":"1","ishot":null,"pic":"images/upload/2018-01-13/1515844495135.jpg","balance":null,"createTime":"2018-03-13 16:51:51","lastTime":null,"members":[{"docId":"20180111105911139852","pwd":null,"cnName":"张文斌","imgID":{"headId":null,"docId":null,"usrId":null,"title":null,"url":"images/upload/2018-03-23/1521796666778.png","addTime":null,"lastTime":null},"idCard":"120111198809304016","sex":"","age":"","tel":"13642153391","mail":"","lincence":"111111111111111","level":"00","hospital":{"hospInfNo":"0000040021","hospLevel":null,"hospName":"天津河西华一中医门诊部","hospAddr":null,"hospType":null,"hospArea":null,"hospTel":null},"offices":{"offiInfNo":"005001","offilEVEL1":"005","offiName1":"皮肤科","offilEVEL2":"001","offiName2":"皮肤科"},"positional":{"postInfNo":"005","postInfName":"主任专家"},"article":"1221","pwdTimes":null,"status":"1","invcode":"966528","lastTime":null,"inquiryTime":"00001000000000010000000010010000100000000000010000000000","imgTxt":null,"longitude":"","latitude":"","lastLogin":null,"adviceAuth":"111","adviceScope":[{"asNo":"1","asName":"感冒"},{"asNo":"3","asName":"冠心病"}],"loginHost":null,"alipayAccount":"","feeStandar":"300.00","wechatNo":"","recipients":"","contactNum":"","deliveryAdd":"","feeWechat":"300","balance":"0.00","wdFlag":null,"signupTime":"2018-01-11 10:59:11","experience":"111","isCheck":"1","totalAdvice":null,"monthAdvice":null,"isHot":null,"o2oOrder":null,"user":null},{"docId":"20180117113359477005","pwd":null,"cnName":"刘文军","imgID":{"headId":null,"docId":null,"usrId":null,"title":null,"url":"images/upload/2018-01-17/1516160163249.png","addTime":null,"lastTime":null},"idCard":"150121198808153912","sex":"","age":"","tel":"18722132981","mail":"","lincence":"打发士大夫","level":"00","hospital":{"hospInfNo":"0000010001","hospLevel":null,"hospName":"天津市胸科医院","hospAddr":null,"hospType":null,"hospArea":null,"hospTel":null},"offices":{"offiInfNo":"001001","offilEVEL1":"001","offiName1":"内科","offilEVEL2":"001","offiName2":"心血管内科"},"positional":{"postInfNo":"006","postInfName":"国务院津贴专家"},"article":"首发式地方","pwdTimes":null,"status":"0","invcode":"696263","lastTime":null,"inquiryTime":"00000000011100000000000000000000000000000000000000000000","imgTxt":null,"longitude":"","latitude":"","lastLogin":null,"adviceAuth":"111","adviceScope":[{"asNo":"1","asName":"感冒"},{"asNo":"2","asName":"肺结核"},{"asNo":"3","asName":"冠心病"}],"loginHost":null,"alipayAccount":"","feeStandar":"80","wechatNo":"","recipients":"","contactNum":"","deliveryAdd":"","feeWechat":"0.00","balance":"0.00","wdFlag":null,"signupTime":"2018-01-17 11:33:59","experience":null,"isCheck":"0","totalAdvice":null,"monthAdvice":null,"isHot":null,"o2oOrder":null,"user":null},{"docId":"20180118160142096888","pwd":null,"cnName":"王磊","imgID":{"headId":null,"docId":null,"usrId":null,"title":null,"url":"images/upload/2018-01-18/1516262520480.png","addTime":null,"lastTime":null},"idCard":"120105198404094818","sex":"","age":"","tel":"13752725197","mail":"","lincence":"111111111111111","level":"00","hospital":{"hospInfNo":"0000050004","hospLevel":null,"hospName":"天津市南开医院","hospAddr":null,"hospType":null,"hospArea":null,"hospTel":null},"offices":{"offiInfNo":"004001","offilEVEL1":"004","offiName1":"妇产科\r\n\r\n","offilEVEL2":"001","offiName2":"妇科"},"positional":{"postInfNo":"005","postInfName":"主任专家"},"article":"fds","pwdTimes":null,"status":"0","invcode":"142316","lastTime":null,"inquiryTime":"11111111000000100000010001001000010100000010000001000000","imgTxt":null,"longitude":"","latitude":"","lastLogin":null,"adviceAuth":"111","adviceScope":[],"loginHost":null,"alipayAccount":"","feeStandar":"0.01","wechatNo":"","recipients":"","contactNum":"","deliveryAdd":"","feeWechat":"0.00","balance":"60.00","wdFlag":null,"signupTime":"2018-01-18 16:01:42","experience":"fdsaf","isCheck":"1","totalAdvice":null,"monthAdvice":null,"isHot":null,"o2oOrder":null,"user":null}],"sicks":[{"sicId":null,"sicNo":"0006","sicName":"白内障","symptoms":null},{"sicId":null,"sicNo":"0007","sicName":"动脉硬化","symptoms":null},{"sicId":null,"sicNo":"0008","sicName":"低血压","symptoms":null},{"sicId":null,"sicNo":"0011","sicName":"气管炎","symptoms":null},{"sicId":null,"sicNo":"0012","sicName":"哮喘","symptoms":null}],"totalAdvice":"200","monthAdvice":"15"}]
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
         * id : null
         * dtmNo : 0180313165151485839
         * dtmName : 发送
         * docNo : 20180111105911139852
         * dtmType : 1
         * dtmDisc : 发
         * identity : null
         * statu : 1
         * ishot : null
         * pic : images/upload/2018-01-13/1515844495135.jpg
         * balance : null
         * createTime : 2018-03-13 16:51:51
         * lastTime : null
         * members : [{"docId":"20180111105911139852","pwd":null,"cnName":"张文斌","imgID":{"headId":null,"docId":null,"usrId":null,"title":null,"url":"images/upload/2018-03-23/1521796666778.png","addTime":null,"lastTime":null},"idCard":"120111198809304016","sex":"","age":"","tel":"13642153391","mail":"","lincence":"111111111111111","level":"00","hospital":{"hospInfNo":"0000040021","hospLevel":null,"hospName":"天津河西华一中医门诊部","hospAddr":null,"hospType":null,"hospArea":null,"hospTel":null},"offices":{"offiInfNo":"005001","offilEVEL1":"005","offiName1":"皮肤科","offilEVEL2":"001","offiName2":"皮肤科"},"positional":{"postInfNo":"005","postInfName":"主任专家"},"article":"1221","pwdTimes":null,"status":"1","invcode":"966528","lastTime":null,"inquiryTime":"00001000000000010000000010010000100000000000010000000000","imgTxt":null,"longitude":"","latitude":"","lastLogin":null,"adviceAuth":"111","adviceScope":[{"asNo":"1","asName":"感冒"},{"asNo":"3","asName":"冠心病"}],"loginHost":null,"alipayAccount":"","feeStandar":"300.00","wechatNo":"","recipients":"","contactNum":"","deliveryAdd":"","feeWechat":"300","balance":"0.00","wdFlag":null,"signupTime":"2018-01-11 10:59:11","experience":"111","isCheck":"1","totalAdvice":null,"monthAdvice":null,"isHot":null,"o2oOrder":null,"user":null},{"docId":"20180117113359477005","pwd":null,"cnName":"刘文军","imgID":{"headId":null,"docId":null,"usrId":null,"title":null,"url":"images/upload/2018-01-17/1516160163249.png","addTime":null,"lastTime":null},"idCard":"150121198808153912","sex":"","age":"","tel":"18722132981","mail":"","lincence":"打发士大夫","level":"00","hospital":{"hospInfNo":"0000010001","hospLevel":null,"hospName":"天津市胸科医院","hospAddr":null,"hospType":null,"hospArea":null,"hospTel":null},"offices":{"offiInfNo":"001001","offilEVEL1":"001","offiName1":"内科","offilEVEL2":"001","offiName2":"心血管内科"},"positional":{"postInfNo":"006","postInfName":"国务院津贴专家"},"article":"首发式地方","pwdTimes":null,"status":"0","invcode":"696263","lastTime":null,"inquiryTime":"00000000011100000000000000000000000000000000000000000000","imgTxt":null,"longitude":"","latitude":"","lastLogin":null,"adviceAuth":"111","adviceScope":[{"asNo":"1","asName":"感冒"},{"asNo":"2","asName":"肺结核"},{"asNo":"3","asName":"冠心病"}],"loginHost":null,"alipayAccount":"","feeStandar":"80","wechatNo":"","recipients":"","contactNum":"","deliveryAdd":"","feeWechat":"0.00","balance":"0.00","wdFlag":null,"signupTime":"2018-01-17 11:33:59","experience":null,"isCheck":"0","totalAdvice":null,"monthAdvice":null,"isHot":null,"o2oOrder":null,"user":null},{"docId":"20180118160142096888","pwd":null,"cnName":"王磊","imgID":{"headId":null,"docId":null,"usrId":null,"title":null,"url":"images/upload/2018-01-18/1516262520480.png","addTime":null,"lastTime":null},"idCard":"120105198404094818","sex":"","age":"","tel":"13752725197","mail":"","lincence":"111111111111111","level":"00","hospital":{"hospInfNo":"0000050004","hospLevel":null,"hospName":"天津市南开医院","hospAddr":null,"hospType":null,"hospArea":null,"hospTel":null},"offices":{"offiInfNo":"004001","offilEVEL1":"004","offiName1":"妇产科\r\n\r\n","offilEVEL2":"001","offiName2":"妇科"},"positional":{"postInfNo":"005","postInfName":"主任专家"},"article":"fds","pwdTimes":null,"status":"0","invcode":"142316","lastTime":null,"inquiryTime":"11111111000000100000010001001000010100000010000001000000","imgTxt":null,"longitude":"","latitude":"","lastLogin":null,"adviceAuth":"111","adviceScope":[],"loginHost":null,"alipayAccount":"","feeStandar":"0.01","wechatNo":"","recipients":"","contactNum":"","deliveryAdd":"","feeWechat":"0.00","balance":"60.00","wdFlag":null,"signupTime":"2018-01-18 16:01:42","experience":"fdsaf","isCheck":"1","totalAdvice":null,"monthAdvice":null,"isHot":null,"o2oOrder":null,"user":null}]
         * sicks : [{"sicId":null,"sicNo":"0006","sicName":"白内障","symptoms":null},{"sicId":null,"sicNo":"0007","sicName":"动脉硬化","symptoms":null},{"sicId":null,"sicNo":"0008","sicName":"低血压","symptoms":null},{"sicId":null,"sicNo":"0011","sicName":"气管炎","symptoms":null},{"sicId":null,"sicNo":"0012","sicName":"哮喘","symptoms":null}]
         * totalAdvice : 200
         * monthAdvice : 15
         */

        private String id;
        private String dtmNo;
        private String dtmName;
        private String docNo;
        private String dtmType;
        private String dtmDisc;
        private String identity;
        private String statu;
        private String ishot;
        private String pic;
        private String balance;
        private String createTime;
        private String lastTime;
        private String totalAdvice;
        private String monthAdvice;
        private List<MembersEntity> members;
        private List<SicksEntity> sicks;

        public void setId(String id) {
            this.id = id;
        }

        public void setDtmNo(String dtmNo) {
            this.dtmNo = dtmNo;
        }

        public void setDtmName(String dtmName) {
            this.dtmName = dtmName;
        }

        public void setDocNo(String docNo) {
            this.docNo = docNo;
        }

        public void setDtmType(String dtmType) {
            this.dtmType = dtmType;
        }

        public void setDtmDisc(String dtmDisc) {
            this.dtmDisc = dtmDisc;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public void setStatu(String statu) {
            this.statu = statu;
        }

        public void setIshot(String ishot) {
            this.ishot = ishot;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public void setTotalAdvice(String totalAdvice) {
            this.totalAdvice = totalAdvice;
        }

        public void setMonthAdvice(String monthAdvice) {
            this.monthAdvice = monthAdvice;
        }

        public void setMembers(List<MembersEntity> members) {
            this.members = members;
        }

        public void setSicks(List<SicksEntity> sicks) {
            this.sicks = sicks;
        }

        public String getId() {
            return id;
        }

        public String getDtmNo() {
            return dtmNo;
        }

        public String getDtmName() {
            return dtmName;
        }

        public String getDocNo() {
            return docNo;
        }

        public String getDtmType() {
            return dtmType;
        }

        public String getDtmDisc() {
            return dtmDisc;
        }

        public String getIdentity() {
            return identity;
        }

        public String getStatu() {
            return statu;
        }

        public String getIshot() {
            return ishot;
        }

        public String getPic() {
            return pic;
        }

        public String getBalance() {
            return balance;
        }

        public String getCreateTime() {
            return createTime;
        }

        public String getLastTime() {
            return lastTime;
        }

        public String getTotalAdvice() {
            return totalAdvice;
        }

        public String getMonthAdvice() {
            return monthAdvice;
        }

        public List<MembersEntity> getMembers() {
            return members;
        }

        public List<SicksEntity> getSicks() {
            return sicks;
        }

        public static class MembersEntity {
            /**
             * docId : 20180111105911139852
             * pwd : null
             * cnName : 张文斌
             * imgID : {"headId":null,"docId":null,"usrId":null,"title":null,"url":"images/upload/2018-03-23/1521796666778.png","addTime":null,"lastTime":null}
             * idCard : 120111198809304016
             * sex : 
             * age : 
             * tel : 13642153391
             * mail : 
             * lincence : 111111111111111
             * level : 00
             * hospital : {"hospInfNo":"0000040021","hospLevel":null,"hospName":"天津河西华一中医门诊部","hospAddr":null,"hospType":null,"hospArea":null,"hospTel":null}
             * offices : {"offiInfNo":"005001","offilEVEL1":"005","offiName1":"皮肤科","offilEVEL2":"001","offiName2":"皮肤科"}
             * positional : {"postInfNo":"005","postInfName":"主任专家"}
             * article : 1221
             * pwdTimes : null
             * status : 1
             * invcode : 966528
             * lastTime : null
             * inquiryTime : 00001000000000010000000010010000100000000000010000000000
             * imgTxt : null
             * longitude : 
             * latitude : 
             * lastLogin : null
             * adviceAuth : 111
             * adviceScope : [{"asNo":"1","asName":"感冒"},{"asNo":"3","asName":"冠心病"}]
             * loginHost : null
             * alipayAccount : 
             * feeStandar : 300.00
             * wechatNo : 
             * recipients : 
             * contactNum : 
             * deliveryAdd : 
             * feeWechat : 300
             * balance : 0.00
             * wdFlag : null
             * signupTime : 2018-01-11 10:59:11
             * experience : 111
             * isCheck : 1
             * totalAdvice : null
             * monthAdvice : null
             * isHot : null
             * o2oOrder : null
             * user : null
             */

            private String docId;
            private String pwd;
            private String cnName;
            private ImgIDEntity imgID;
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
            private String isHot;
            private String o2oOrder;
            private String user;
            private List<AdviceScopeEntity> adviceScope;

            public void setDocId(String docId) {
                this.docId = docId;
            }

            public void setPwd(String pwd) {
                this.pwd = pwd;
            }

            public void setCnName(String cnName) {
                this.cnName = cnName;
            }

            public void setImgID(ImgIDEntity imgID) {
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

            public void setIsHot(String isHot) {
                this.isHot = isHot;
            }

            public void setO2oOrder(String o2oOrder) {
                this.o2oOrder = o2oOrder;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public void setAdviceScope(List<AdviceScopeEntity> adviceScope) {
                this.adviceScope = adviceScope;
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

            public ImgIDEntity getImgID() {
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

            public String getIsHot() {
                return isHot;
            }

            public String getO2oOrder() {
                return o2oOrder;
            }

            public String getUser() {
                return user;
            }

            public List<AdviceScopeEntity> getAdviceScope() {
                return adviceScope;
            }

            public static class ImgIDEntity {
                /**
                 * headId : null
                 * docId : null
                 * usrId : null
                 * title : null
                 * url : images/upload/2018-03-23/1521796666778.png
                 * addTime : null
                 * lastTime : null
                 */

                private String headId;
                private String docId;
                private String usrId;
                private String title;
                private String url;
                private String addTime;
                private String lastTime;

                public void setHeadId(String headId) {
                    this.headId = headId;
                }

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

                public void setAddTime(String addTime) {
                    this.addTime = addTime;
                }

                public void setLastTime(String lastTime) {
                    this.lastTime = lastTime;
                }

                public String getHeadId() {
                    return headId;
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

                public String getAddTime() {
                    return addTime;
                }

                public String getLastTime() {
                    return lastTime;
                }
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
                 * offilEVEL1 : 005
                 * offiName1 : 皮肤科
                 * offilEVEL2 : 001
                 * offiName2 : 皮肤科
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
                 * postInfNo : 005
                 * postInfName : 主任专家
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

            public static class AdviceScopeEntity {
                /**
                 * asNo : 1
                 * asName : 感冒
                 */

                private String asNo;
                private String asName;

                public void setAsNo(String asNo) {
                    this.asNo = asNo;
                }

                public void setAsName(String asName) {
                    this.asName = asName;
                }

                public String getAsNo() {
                    return asNo;
                }

                public String getAsName() {
                    return asName;
                }
            }
        }

        public static class SicksEntity {
            /**
             * sicId : null
             * sicNo : 0006
             * sicName : 白内障
             * symptoms : null
             */

            private String sicId;
            private String sicNo;
            private String sicName;
            private String symptoms;

            public void setSicId(String sicId) {
                this.sicId = sicId;
            }

            public void setSicNo(String sicNo) {
                this.sicNo = sicNo;
            }

            public void setSicName(String sicName) {
                this.sicName = sicName;
            }

            public void setSymptoms(String symptoms) {
                this.symptoms = symptoms;
            }

            public String getSicId() {
                return sicId;
            }

            public String getSicNo() {
                return sicNo;
            }

            public String getSicName() {
                return sicName;
            }

            public String getSymptoms() {
                return symptoms;
            }
        }
    }
}
