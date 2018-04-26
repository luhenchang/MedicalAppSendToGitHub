package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2018/3/30.
 */

public class DoctorPayBean {


    /**
     * month : 4
     * date : 28
     * doctor_id : 20180118160142096888
     * money : 0.10
     * time : 8:00~9:00
     * week : 星期六
     * year : 2018
     * isYuyue : true
     * {"month":"5","date":"1","doctor_id":"20180118160142096888","money":"0.10","time":"8:00~9:00","week":"星期二","year":"2018","isYuyue":true,"isNum":true}

     */

    private String month;
    private String date;
    private String doctor_id;
    private String money;
    private String time;
    private String week;
    private String year;
    private boolean isYuyue;
    public boolean isNum;
    public void setMonth(String month) {
        this.month = month;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setIsYuyue(boolean isYuyue) {
        this.isYuyue = isYuyue;
    }

    public String getMonth() {
        return month;
    }

    public String getDate() {
        return date;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public String getMoney() {
        return money;
    }

    public String getTime() {
        return time;
    }

    public String getWeek() {
        return week;
    }

    public String getYear() {
        return year;
    }

    public boolean getIsYuyue() {
        return isYuyue;
    }
}
