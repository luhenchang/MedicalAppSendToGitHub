package com.example.ls.shoppingmall.user.bean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/4/8.
 */

public class MessageBean {


    /**
     * RESMSG : 查询成功
     * RESOBJ : [{"mesId":"2cadf7f983e4b8ca306851d7bf44dde5","docId":null,"type":null,"usrId":"20180320164359015315","telNo":null,"tempID":null,"mesTitle":"巧医联盟","mesContent":"张医师约你看病","isRead":"0","status":null,"addTime":"2018-04-09 10:04:15","lastTime":null,"params":null},{"mesId":"6f1d324c12c3f353e9ced9ffd9367bc1","docId":null,"type":null,"usrId":"20180320164359015315","telNo":null,"tempID":null,"mesTitle":"巧医联盟","mesContent":"张医师约你看病","isRead":"0","status":null,"addTime":"2018-04-09 10:07:42","lastTime":null,"params":null},{"mesId":"85241b1439bd4afbcb867e8c2c2bdd28","docId":null,"type":null,"usrId":"20180320164359015315","telNo":null,"tempID":null,"mesTitle":"巧医联盟","mesContent":"张医师约你看病","isRead":"0","status":null,"addTime":"2018-04-08 14:09:24","lastTime":null,"params":null}]
     * RESCOD : 000000
     * RESRECORDS : 3
     */

    private String RESMSG;
    private String RESCOD;
    private int RESRECORDS;
    private List<RESOBJEntity> RESOBJ;

    public void setRESMSG(String RESMSG) {
        this.RESMSG = RESMSG;
    }

    public void setRESCOD(String RESCOD) {
        this.RESCOD = RESCOD;
    }

    public void setRESRECORDS(int RESRECORDS) {
        this.RESRECORDS = RESRECORDS;
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

    public int getRESRECORDS() {
        return RESRECORDS;
    }

    public List<RESOBJEntity> getRESOBJ() {
        return RESOBJ;
    }

    public static class RESOBJEntity {
        /**
         * mesId : 2cadf7f983e4b8ca306851d7bf44dde5
         * docId : null
         * type : null
         * usrId : 20180320164359015315
         * telNo : null
         * tempID : null
         * mesTitle : 巧医联盟
         * mesContent : 张医师约你看病
         * isRead : 0
         * status : null
         * addTime : 2018-04-09 10:04:15
         * lastTime : null
         * params : null
         */
        public boolean isSelect;
        private String mesId;
        private String docId;
        private String type;
        private String usrId;
        private String telNo;
        private String tempID;
        private String mesTitle;
        private String mesContent;
        private String isRead;
        private String status;
        private String addTime;
        private String lastTime;
        private String params;

        public void setMesId(String mesId) {
            this.mesId = mesId;
        }

        public void setDocId(String docId) {
            this.docId = docId;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUsrId(String usrId) {
            this.usrId = usrId;
        }

        public void setTelNo(String telNo) {
            this.telNo = telNo;
        }

        public void setTempID(String tempID) {
            this.tempID = tempID;
        }

        public void setMesTitle(String mesTitle) {
            this.mesTitle = mesTitle;
        }

        public void setMesContent(String mesContent) {
            this.mesContent = mesContent;
        }

        public void setIsRead(String isRead) {
            this.isRead = isRead;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public String getMesId() {
            return mesId;
        }

        public String getDocId() {
            return docId;
        }

        public String getType() {
            return type;
        }

        public String getUsrId() {
            return usrId;
        }

        public String getTelNo() {
            return telNo;
        }

        public String getTempID() {
            return tempID;
        }

        public String getMesTitle() {
            return mesTitle;
        }

        public String getMesContent() {
            return mesContent;
        }

        public String getIsRead() {
            return isRead;
        }

        public String getStatus() {
            return status;
        }

        public String getAddTime() {
            return addTime;
        }

        public String getLastTime() {
            return lastTime;
        }

        public String getParams() {
            return params;
        }
    }
}
