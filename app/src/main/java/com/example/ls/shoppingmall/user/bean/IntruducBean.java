package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2018/2/3.
 */

public class IntruducBean {
    public String intruducName;
    public String intruducMssage;
    public boolean isChecked;
    public String intruduMoney;

    public String getIntruducName() {
        return intruducName;
    }

    public void setIntruducName(String intruducName) {
        this.intruducName = intruducName;
    }

    public String getIntruducMssage() {
        return intruducMssage;
    }

    public void setIntruducMssage(String intruducMssage) {
        this.intruducMssage = intruducMssage;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getIntruduMoney() {
        return intruduMoney;
    }

    public void setIntruduMoney(String intruduMoney) {
        this.intruduMoney = intruduMoney;
    }

    @Override
    public String toString() {
        return "IntruducBean{" +
                "intruducName='" + intruducName + '\'' +
                ", intruducMssage='" + intruducMssage + '\'' +
                ", isChecked=" + isChecked +
                ", intruduMoney='" + intruduMoney + '\'' +
                '}';
    }
}
