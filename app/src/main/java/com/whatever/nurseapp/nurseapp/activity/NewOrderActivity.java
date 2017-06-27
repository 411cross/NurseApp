package com.whatever.nurseapp.nurseapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.TestData;
import com.whatever.nurseapp.nurseapp.adapter.OrderAdapter;
import com.whatever.nurseapp.nurseapp.entity.Order;

import java.util.ArrayList;

public class NewOrderActivity extends AppCompatActivity {

    private ArrayList<Order> orderList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        orderList = new TestData().getOrderList_1();
        OrderAdapter orderAdapter = new OrderAdapter(NewOrderActivity.this, R.layout.layout_order_item, orderList);
        ListView orderListView = (ListView) findViewById(R.id.new_order_list);
        orderListView.setAdapter(orderAdapter);

    }
}
