package com.example.ls.shoppingmall.home.bean;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Comparator;
import java.util.List;

/**
 * Created by 路很长~ on 2018/1/31.
 */

public class LastInformationCircleBean implements Comparator<LastInformationCircleBean.RESOBJBean> {

    /**
     * RESMSG : 申请成功
     * RESOBJ : [{"sick":{"sicNo":"0001","sicName":"高血压"},"point":"40.00"}]
     * RESCOD : 000000
     */

    private String RESMSG;
    private String RESCOD;
    private List<RESOBJBean> RESOBJ;

    public void setRESMSG(String RESMSG) {
        this.RESMSG = RESMSG;
    }

    public void setRESCOD(String RESCOD) {
        this.RESCOD = RESCOD;
    }

    public void setRESOBJ(List<RESOBJBean> RESOBJ) {
        this.RESOBJ = RESOBJ;
    }

    public String getRESMSG() {
        return RESMSG;
    }

    public String getRESCOD() {
        return RESCOD;
    }

    public List<RESOBJBean> getRESOBJ() {
        return RESOBJ;
    }

    @Override
    public int compare(RESOBJBean o1, RESOBJBean o2) {
        Log.e("ooo", o1.getPoint().compareTo(o2.getPoint()) + "");
        return o1.getPoint().compareTo(o2.getPoint());
    }

    public static class RESOBJBean implements Comparator<RESOBJBean> {
        /**
         * sick : {"sicNo":"0001","sicName":"高血压"}
         * point : 40.00
         */

        private SickBean sick;
        private String point;

        public void setSick(SickBean sick) {
            this.sick = sick;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public SickBean getSick() {
            return sick;
        }

        public String getPoint() {
            return point;
        }

        @Override
        public int compare(RESOBJBean o1, RESOBJBean o2) {
            Log.e("o1-o2", o1.getPoint().compareTo(o2.getPoint()) + "");
            return o1.getPoint().compareTo(o2.getPoint());
        }


        public static class SickBean {
            /**
             * sicNo : 0001
             * sicName : 高血压
             */

            private String sicNo;
            private String sicName;
            public String sicId;

            public void setSicNo(String sicNo) {
                this.sicNo = sicNo;
            }

            public void setSicName(String sicName) {
                this.sicName = sicName;
            }

            public String getSicNo() {
                return sicNo;
            }

            public String getSicName() {
                return sicName;
            }

            @Override
            public String toString() {
                return "SickBean{" +
                        "sicNo='" + sicNo + '\'' +
                        ", sicName='" + sicName + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "RESOBJBean{" +
                    "sick=" + sick +
                    ", point='" + point + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LastInformationCircleBean{" +
                "RESMSG='" + RESMSG + '\'' +
                ", RESCOD='" + RESCOD + '\'' +
                ", RESOBJ=" + RESOBJ +
                '}';
    }
}
