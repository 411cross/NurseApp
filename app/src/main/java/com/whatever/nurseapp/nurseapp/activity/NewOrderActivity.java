package com.whatever.nurseapp.nurseapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        ListView orderListView = (ListView) findViewById(R.id.new_order_list);
        OrderAdapter orderAdapter = new OrderAdapter(NewOrderActivity.this, R.layout.layout_order_item, orderList);
        orderListView.setAdapter(orderAdapter);
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Order order = orderList.get(i);
                Bundle bundle = new Bundle();
                bundle.putString("orderID", order.getOrderID());
                bundle.putString("price", order.getPrice());
                bundle.putString("time", order.getTime());
                bundle.putInt("type", order.getType());
                bundle.putInt("situation", order.getSituation());
                Intent intent = new Intent(NewOrderActivity.this, OrderDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}