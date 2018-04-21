package com.example.ls.shoppingmall.community.bean;

/**
 * Created by 路很长~ on 2017/12/25.
 */

public class MessageResult {

    /**
     * RESMSG : 验证码发送成功
     * RESCOD : 000000
     */

    private String RESMSG;
    private String RESCOD;

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

    @Override
    public String toString() {
        return "MessageResult{" +
                "RESMSG='" + RESMSG + '\'' +
                ", RESCOD='" + RESCOD + '\'' +
                '}';
    }
}
