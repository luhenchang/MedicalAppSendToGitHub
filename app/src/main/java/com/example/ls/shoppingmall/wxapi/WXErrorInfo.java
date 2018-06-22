package com.example.ls.shoppingmall.wxapi;

/**
 * 作者:王飞
 * 邮箱:1276998208@qq.com
 * create on 2018/6/19 11:35
 */
class WXErrorInfo {

    /**
     * errcode : 40029
     * errmsg : invalid code
     */

    private int errcode;
    private String errmsg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }


    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "WXErrorInfo{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
