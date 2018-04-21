package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2018/1/5.
 */

public class SaveUserMessage {

    /**
     * RESMSG : 信息修改成功
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
        return "SaveUserMessage{" +
                "RESMSG='" + RESMSG + '\'' +
                ", RESCOD='" + RESCOD + '\'' +
                '}';
    }
}
