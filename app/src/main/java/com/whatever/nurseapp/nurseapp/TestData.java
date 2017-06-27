package com.whatever.nurseapp.nurseapp;

import com.whatever.nurseapp.nurseapp.entity.Order;

import java.util.ArrayList;

/**
 * Created by derrickJ on 2017/6/27.
 */

public class TestData {

    private Order order_1 = new Order("20170627100000", "250.00", "2017.06.17", 1, 0);
    private Order order_2 = new Order("20170627100000", "300.00", "2017.06.17", 2, 1);
    private Order order_3 = new Order("20170627100000", "1000.00", "2017.06.17", 3, 1);
    private Order order_4 = new Order("20170627100000", "300.00", "2017.06.17", 3, 3);
    private Order order_5 = new Order("20170627100000", "250.00", "2017.06.17", 2, 2);
    private Order order_6 = new Order("20170627100000", "300.00", "2017.06.17", 3, 2);
    private Order order_7 = new Order("20170627100000", "1000.00", "2017.06.17", 1, 3);

    private ArrayList<Order> orderList_1 = new ArrayList<>();
    private ArrayList<Order> orderList_2 = new ArrayList<>();

    public TestData() {
        this.getOrderList_1().add(order_1);
        this.getOrderList_1().add(order_2);
        this.getOrderList_1().add(order_3);
        this.getOrderList_2().add(order_4);
        this.getOrderList_2().add(order_5);
        this.getOrderList_2().add(order_6);
        this.getOrderList_2().add(order_7);
    }

    public ArrayList<Order> getOrderList_1() {
        return orderList_1;
    }

    public ArrayList<Order> getOrderList_2() {
        return orderList_2;
    }

}
