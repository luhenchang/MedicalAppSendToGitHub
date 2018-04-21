package com.example.ls.shoppingmall.user.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/3/26.
 */

public class MyCoalsBean {

    /**
     * RESMSG : 请求成功
     * RESOBJ : [{"usrId":null,"orderNo":null,"money":"10.00","imgPath":null,"tradeTime":1521527242000,"startTime":null,"endTime":null,"name1":"王磊","name2":null,"name3":null,"note1":null,"note2":null,"note3":null},{"usrId":null,"orderNo":null,"money":"10.00","imgPath":null,"tradeTime":1521533276000,"startTime":null,"endTime":null,"name1":"王磊","name2":null,"name3":null,"note1":null,"note2":null,"note3":null},{"usrId":null,"orderNo":null,"money":"10.00","imgPath":null,"tradeTime":1521535439000,"startTime":null,"endTime":null,"name1":"冯其凡","name2":null,"name3":null,"note1":null,"note2":null,"note3":null},{"usrId":null,"orderNo":null,"money":"10.00","imgPath":null,"tradeTime":1521535541000,"startTime":null,"endTime":null,"name1":"冯其凡","name2":null,"name3":null,"note1":null,"note2":null,"note3":null}]
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
         * usrId : null
         * orderNo : null
         * money : 10.00
         * imgPath : null
         * tradeTime : 1521527242000
         * startTime : null
         * endTime : null
         * name1 : 王磊
         * name2 : null
         * name3 : null
         * note1 : null
         * note2 : null
         * note3 : null
         */

        private String usrId;
        private String orderNo;
        private String money;
        private String imgPath;
        private long tradeTime;
        private String startTime;
        private String endTime;
        private String name1;
        private String name2;
        private String name3;
        private String note1;
        private String note2;
        private String note3;

        public void setUsrId(String usrId) {
            this.usrId = usrId;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public void setTradeTime(long tradeTime) {
            this.tradeTime = tradeTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }

        public void setName3(String name3) {
            this.name3 = name3;
        }

        public void setNote1(String note1) {
            this.note1 = note1;
        }

        public void setNote2(String note2) {
            this.note2 = note2;
        }

        public void setNote3(String note3) {
            this.note3 = note3;
        }

        public String getUsrId() {
            return usrId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public String getMoney() {
            return money;
        }

        public String getImgPath() {
            return imgPath;
        }

        public long getTradeTime() {
            return tradeTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public String getName1() {
            return name1;
        }

        public String getName2() {
            return name2;
        }

        public String getName3() {
            return name3;
        }

        public String getNote1() {
            return note1;
        }

        public String getNote2() {
            return note2;
        }

        public String getNote3() {
            return note3;
        }
    }
}
