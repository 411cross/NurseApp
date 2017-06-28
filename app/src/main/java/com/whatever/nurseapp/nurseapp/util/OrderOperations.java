package com.whatever.nurseapp.nurseapp.util;

import com.whatever.nurseapp.nurseapp.entity.Order;

import java.util.ArrayList;

/**
 * Created by derrickJ on 2017/6/27.
 */

public class OrderOperations {

    public static ArrayList<Order> getNewOrderList(ArrayList<Order> orderList) {

        ArrayList<Order> newOrderList = new ArrayList<>();

        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getSituation() == 0 || orderList.get(i).getSituation() == 1 || orderList.get(i).getSituation() == 4) {
                newOrderList.add(orderList.get(i));
            }
        }

        return newOrderList;

    }

    public static ArrayList<Order> getOldOrderList(ArrayList<Order> orderList) {

        ArrayList<Order> oldOrderList = new ArrayList<>();

        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getSituation() == 3 || orderList.get(i).getSituation() == 2) {
                oldOrderList.add(orderList.get(i));
            }
        }

        return oldOrderList;

    }

}
