package com.whatever.nurseapp.nurseapp;

import com.whatever.nurseapp.nurseapp.entity.Nurse;
import com.whatever.nurseapp.nurseapp.entity.Order;
import com.whatever.nurseapp.nurseapp.entity.Patient;
import com.whatever.nurseapp.nurseapp.entity.User;

import java.util.ArrayList;

/**
 * Created by derrickJ on 2017/6/27.
 */

public class TestData {

    private ArrayList<Integer> s1 = new ArrayList<>();
    private Nurse nurse;
    private User user = new User();
    private Patient patient = new Patient();

    private Order order = new Order(12345, "250", "2017.06.17", "2017.06.17", 1, 1, 1, nurse, patient, user);

    private Order order_1 = new Order(12346, "250", "2017.06.17", "2017.06.17", 1, 1, 1, nurse, patient, user);
    private Order order_2 = new Order(12347, "250", "2017.06.17", "2017.06.17", 1, 1, 1, nurse, patient, user);
    private Order order_3 = new Order(12348, "250", "2017.06.17", "2017.06.17", 1, 1, 1, nurse, patient, user);
    private Order order_4 = new Order(12349, "250", "2017.06.17", "2017.06.17", 1, 1, 1, nurse, patient, user);
    private Order order_5 = new Order(12350, "250", "2017.06.17", "2017.06.17", 1, 1, 1, nurse, patient, user);
    private Order order_6 = new Order(12351, "250", "2017.06.17", "2017.06.17", 1, 0, 1, nurse, patient, user);
    private Order order_7 = new Order(12352, "250", "2017.06.17", "2017.06.17", 1, 0, 1, nurse, patient, user);
    private Order order_8 = new Order(12353, "250", "2017.06.17", "2017.06.17", 1, 0, 1, nurse, patient, user);
    private Order order_9 = new Order(12354, "250", "2017.06.17", "2017.06.17", 1, 0, 1, nurse, patient, user);
//    private Order order_10 = new Order("20170627100010", "1000.00", "2017.06.17", 3, 1, 1);
//    private Order order_11 = new Order("20170627100011", "300.00", "2017.06.17", 1, 2, 1);
//    private Order order_12 = new Order("20170627100012", "250.00", "2017.06.17", 2, 1, 1);
//    private Order order_13 = new Order("20170627100013", "300.00", "2017.06.17", 3, 0, 1);
//    private Order order_14 = new Order("20170627100014", "1000.00", "2017.06.17", 1, 1, 1);

    private ArrayList<Order> newOrderList = new ArrayList<>();
    private ArrayList<Order> oldOrderList = new ArrayList<>();
    private ArrayList<String> orderAccepted = new ArrayList<>();

    public TestData() {
        s1.add(1);
        nurse = new Nurse("牛大",123456,0,20,10, "广东", 90, 150, s1, 150, 100, "A", "汉族", "123identify", "双鱼座", "鼠", "fit", "7569");
        this.getNewOrderList().add(order_1);
        this.getNewOrderList().add(order_2);
        this.getNewOrderList().add(order_3);
        this.getOldOrderList().add(order_4);
        this.getOldOrderList().add(order_5);
        this.getOldOrderList().add(order_6);
        this.getOldOrderList().add(order_7);
        this.getNewOrderList().add(order_8);
        this.getNewOrderList().add(order_9);
//        this.getNewOrderList().add(order_10);
//        this.getOldOrderList().add(order_11);
//        this.getNewOrderList().add(order_12);
//        this.getNewOrderList().add(order_13);
//        this.getNewOrderList().add(order_14);
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
