package com.whatever.nurseapp.nurseapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.whatever.nurseapp.nurseapp.R;

public class MainActivity extends AppCompatActivity {

    private Button newOrderBtn;
    private Button oldOrderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newOrderBtn = (Button) findViewById(R.id.new_order);
        oldOrderBtn = (Button) findViewById(R.id.old_order);

        newOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toNewOrder = new Intent(MainActivity.this, NewOrderActivity.class);
                startActivity(toNewOrder);
            }
        });

        oldOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toOldOrder = new Intent(MainActivity.this, OldOrderActivity.class);
                startActivity(toOldOrder);
            }
        });

    }

}
