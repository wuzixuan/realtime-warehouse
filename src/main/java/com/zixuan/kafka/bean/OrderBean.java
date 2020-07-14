package com.zixuan.kafka.bean;

import java.io.Serializable;

public class OrderBean implements Serializable {
    private int orderID;
    private String user;
    private double amount;
    private long creatTime;

    public OrderBean(){

    }

    public OrderBean(int orderID,String user,double amount,long creatTime){
        this.orderID=orderID;
        this.user=user;
        this.amount=amount;
        this.creatTime=creatTime;
    }

    @Override
    public String toString() {
        return "Order [orderID=" + orderID + ", user=" + user + ", amount=" + amount + ", creatTime=" + creatTime + "]";
    }


    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }


}
