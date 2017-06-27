package com.whatever.nurseapp.nurseapp.entity;

/**
 * Created by derrickJ on 2017/6/27.
 */

public class Order {

    private String orderID;
    private String price;
    private String time;
    private int type; // 1:内科 2:外科 3:妇产科
    private int situation; //付款状态

    public Order() {}

    public Order(String orderID, String price, String time, int type, int situation) {
        setOrderID(orderID);
        setPrice(price);
        setTime(time);
        setType(type);
        setSituation(situation);

    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSituation() {
        return situation;
    }

    public void setSituation(int situation) {
        this.situation = situation;
    }
}
