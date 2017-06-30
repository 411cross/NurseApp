package com.whatever.nurseapp.nurseapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.TestData;
import com.whatever.nurseapp.nurseapp.TestFather;
import com.whatever.nurseapp.nurseapp.adapter.OrderAdapter;
import com.whatever.nurseapp.nurseapp.entity.Order;
import com.whatever.nurseapp.nurseapp.util.OrderOperations;

import java.util.ArrayList;

public class NewOrderActivity extends AppCompatActivity {

    private ArrayList<Order> orderList = new ArrayList<>();
    ListView orderListView;
    OrderAdapter orderAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        orderList = TestFather.getTestData().getNewOrderList();
        orderListView = (ListView) findViewById(R.id.new_order_list);
        orderAdapter = new OrderAdapter(NewOrderActivity.this, R.layout.layout_order_item, orderList);
        orderListView.setAdapter(orderAdapter);
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Order order = orderList.get(i);
                Bundle bundle = new Bundle();
                bundle.putInt("parentActivity", 0);
                bundle.putInt("position", i);
                bundle.putString("orderID", order.getOrderID());
                bundle.putString("price", order.getPrice());
                bundle.putString("time", order.getTime());
                bundle.putInt("type", order.getType());
                bundle.putInt("situation", order.getSituation());
                bundle.putInt("notified", order.getNotified());
                bundle.putInt("choseNurse", order.getChoosedNurse());
                Intent intent = new Intent(NewOrderActivity.this, OrderDetailActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Bundle bundle = data.getExtras();
                int position = bundle.getInt("position");
                int situation = bundle.getInt("situation");
                if (orderListView != null) {
                    int start = orderListView.getFirstVisiblePosition();
                    View view = orderListView.getChildAt(position - start);
                    TextView refresh = (TextView) view.findViewById(R.id.situation);

                    switch (situation) {
                        case 1:
                            break;
                        case 2:
                            refresh.setText("已取消");
                            break;
                        case 3:
                            refresh.setText("已完成");
                            break;
                        case 4:
                            refresh.setText("进行中");
                            break;
                        case 5:
                            refresh.setText("已提醒付款");
                            break;
                        default:
                            break;
                    }
                }

            }
        }
    }

}
