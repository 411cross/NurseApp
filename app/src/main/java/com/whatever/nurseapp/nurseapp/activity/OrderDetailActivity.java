package com.whatever.nurseapp.nurseapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.whatever.nurseapp.nurseapp.R;

public class OrderDetailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Intent intent = getIntent();
        Bundle bundle = this.getIntent().getExtras();
        String orderID = bundle.getString("orderID");
        String price = bundle.getString("price");
        String time = bundle.getString("time");
        int type = bundle.getInt("type");
        int situation = bundle.getInt("situation");
        System.out.println(situation);

        TextView orderIDTv = (TextView) findViewById(R.id.order_id);
        TextView timeTv = (TextView) findViewById(R.id.time);
        TextView priceTv = (TextView) findViewById(R.id.price);
        TextView situationTv = (TextView) findViewById(R.id.situation);
        TextView patientTv = (TextView) findViewById(R.id.patient);
        TextView bedNumberTv = (TextView) findViewById(R.id.bed_number);
        TextView contactTv = (TextView) findViewById(R.id.contact);
        TextView phoneTv = (TextView) findViewById(R.id.phone);
        TextView typeTv = (TextView) findViewById(R.id.type);
        TextView serviceTimeTv = (TextView) findViewById(R.id.service_time);
        TextView nurseTv = (TextView) findViewById(R.id.nurse_name);
        TextView evaluationTv = (TextView) findViewById(R.id.evaluation);
        TextView heightTV = (TextView) findViewById(R.id.height);
        TextView weightTv = (TextView) findViewById(R.id.weight);
        TextView bloodType = (TextView) findViewById(R.id.blood_type);
        Button acceptBtn = (Button) findViewById(R.id.accept);
        Button rejectBtn = (Button) findViewById(R.id.reject);

        orderIDTv.setText(orderID);
        priceTv.setText(price);
        timeTv.setText(time);

        switch (type) {
            case 1:
                typeTv.setText("内科");
                break;
            case 2:
                typeTv.setText("外科");
                break;
            case 3:
                typeTv.setText("妇产科");
                break;
            default:
                break;
        }
        switch (situation) {
            case 0:
                situationTv.setText("未付款");
                break;
            case 1:
                situationTv.setText("已付款");
                break;
            case 2:
                situationTv.setText("已取消");
                break;
            case 3:
                situationTv.setText("已完成");
                break;
            default:
                break;
        }



        if (situation == 0) {
            acceptBtn.setVisibility(View.VISIBLE);
            rejectBtn.setVisibility(View.VISIBLE);
        } else {
            acceptBtn.setVisibility(View.INVISIBLE);
            rejectBtn.setVisibility(View.INVISIBLE);
        }

    }
}
