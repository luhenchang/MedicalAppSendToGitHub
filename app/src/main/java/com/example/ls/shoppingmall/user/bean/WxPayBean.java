package com.example.ls.shoppingmall.user.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 路很长~ on 2018/1/26.
 */

public class WxPayBean {

    /**
     * appid : wxb1021cbd0975214a
     * partnerid : 1497471422
     * package : Sign=WXPay
     * noncestr : IV8ZCvEdcyc35pwe
     * timestamp : 1516952123
     * prepayid : wx20180126153523e05e46cabb0109995049
     * sign : 03613C4D79CF64364BFC597F3ED52A6C
     */

    private String appid;
    private String partnerid;
    @SerializedName("package")
    private String packageX;
    private String noncestr;
    private int timestamp;
    private String prepayid;
    private String sign;

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAppid() {
        return appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public String getPackageX() {
        return packageX;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public String getSign() {
        return sign;
    }

    @Override
    public String toString() {
        return "WxPayBean{" +
                "appid='" + appid + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", packageX='" + packageX + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", timestamp=" + timestamp +
                ", prepayid='" + prepayid + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
