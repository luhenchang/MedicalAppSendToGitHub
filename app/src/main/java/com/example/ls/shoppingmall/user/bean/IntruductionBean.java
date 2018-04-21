package com.example.ls.shoppingmall.user.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/3/22.
 */

public class IntruductionBean {

    /**
     * RESMSG : 申请成功
     * RESOBJ : [{"carId":"1","carType":"W","carName":"周卡","carDisc":"持续7天","carPrice":"20.00","carvalid":"7","lastTime":null},{"carId":"2","carType":"M","carName":"月卡","carDisc":"持续30天，立省5%","carPrice":"78.00","carvalid":"30","lastTime":null},{"carId":"3","carType":"B","carName":"半年卡","carDisc":"持续180天，立省10%","carPrice":"150.00","carvalid":"180","lastTime":null},{"carId":"4","carType":"Y","carName":"年卡","carDisc":"持续365天，立省20%","carPrice":"280.00","carvalid":"365","lastTime":null}]
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
         * carId : 1
         * carType : W
         * carName : 周卡
         * carDisc : 持续7天
         * carPrice : 20.00
         * carvalid : 7
         * lastTime : null
         */
        public boolean isChecked=false;
        private String carId;
        private String carType;
        private String carName;
        private String carDisc;
        private String carPrice;
        private String carvalid;
        private String lastTime;

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public void setCarType(String carType) {
            this.carType = carType;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public void setCarDisc(String carDisc) {
            this.carDisc = carDisc;
        }

        public void setCarPrice(String carPrice) {
            this.carPrice = carPrice;
        }

        public void setCarvalid(String carvalid) {
            this.carvalid = carvalid;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public String getCarId() {
            return carId;
        }

        public String getCarType() {
            return carType;
        }

        public String getCarName() {
            return carName;
        }

        public String getCarDisc() {
            return carDisc;
        }

        public String getCarPrice() {
            return carPrice;
        }

        public String getCarvalid() {
            return carvalid;
        }

        public String getLastTime() {
            return lastTime;
        }
    }
}
