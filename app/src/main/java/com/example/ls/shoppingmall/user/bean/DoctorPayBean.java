package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2018/3/30.
 */

public class DoctorPayBean {

    /**
     * month : 4
     * date : 5
     * doctor_id : 20171221161048677129
     * money : 150.00
     * time : 2:00~3:00
     * week : 星期四
     */
    public String year;
    private String month;
    private String date;
    private String doctor_id;
    private String money;
    private String time;
    private String week;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return "DoctorPayBean{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", date='" + date + '\'' +
                ", doctor_id='" + doctor_id + '\'' +
                ", money='" + money + '\'' +
                ", time='" + time + '\'' +
                ", week='" + week + '\'' +
                '}';
    }
}
