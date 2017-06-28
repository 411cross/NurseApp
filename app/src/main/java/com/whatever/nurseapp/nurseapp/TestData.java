package com.whatever.nurseapp.nurseapp;

import com.whatever.nurseapp.nurseapp.entity.Order;

import java.util.ArrayList;

/**
 * Created by derrickJ on 2017/6/27.
 */

public class TestData {

    private Order order_1 = new Order("20170627100001", "250.00", "2017.06.17", 1, 0);
    private Order order_2 = new Order("20170627100002", "300.00", "2017.06.17", 2, 1);
    private Order order_3 = new Order("20170627100003", "1000.00", "2017.06.17", 3, 1);
    private Order order_4 = new Order("20170627100004", "300.00", "2017.06.17", 3, 3);
    private Order order_5 = new Order("20170627100005", "250.00", "2017.06.17", 1, 2);
    private Order order_6 = new Order("20170627100006", "300.00", "2017.06.17", 2, 2);
    private Order order_7 = new Order("20170627100007", "1000.00", "2017.06.17", 2, 3);
    private Order order_8 = new Order("20170627100008", "250.00", "2017.06.17", 1, 0);
    private Order order_9 = new Order("20170627100009", "300.00", "2017.06.17", 2, 1);
    private Order order_10 = new Order("20170627100010", "1000.00", "2017.06.17", 3, 1);
    private Order order_11 = new Order("20170627100011", "300.00", "2017.06.17", 1, 2);
    private Order order_12 = new Order("20170627100012", "250.00", "2017.06.17", 2, 1);
    private Order order_13 = new Order("20170627100013", "300.00", "2017.06.17", 3, 0);
    private Order order_14 = new Order("20170627100014", "1000.00", "2017.06.17", 1, 1);

    private ArrayList<Order> newOrderList = new ArrayList<>();
    private ArrayList<Order> oldOrderList = new ArrayList<>();
    private ArrayList<String> orderAccepted = new ArrayList<>();

    public TestData() {
        this.getNewOrderList().add(order_1);
        this.getNewOrderList().add(order_2);
        this.getNewOrderList().add(order_3);
        this.getOldOrderList().add(order_4);
        this.getOldOrderList().add(order_5);
        this.getOldOrderList().add(order_6);
        this.getOldOrderList().add(order_7);
        this.getNewOrderList().add(order_8);
        this.getNewOrderList().add(order_9);
        this.getNewOrderList().add(order_10);
        this.getOldOrderList().add(order_11);
        this.getNewOrderList().add(order_12);
        this.getNewOrderList().add(order_13);
        this.getNewOrderList().add(order_14);
    }

    public ArrayList<Order> getNewOrderList() {
        return newOrderList;
    }

    public ArrayList<Order> getOldOrderList() {
        return oldOrderList;
    }

    public ArrayList<String> getOrderAccepted() {
        return orderAccepted;
    }

}
