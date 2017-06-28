package com.whatever.nurseapp.nurseapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.adapter.NurseAdapter;
import com.whatever.nurseapp.nurseapp.entity.Nurse;

import java.util.ArrayList;

public class NurseManageActivity extends AppCompatActivity {
    private ArrayList<Nurse> nurseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_manage);

        nurseList.add(new Nurse("牛大春",20,"男","广东",10,150));
        nurseList.add(new Nurse("牛欢喜",25,"女","湖南",9,125));
        NurseAdapter NurseAdapter = new NurseAdapter(NurseManageActivity.this, R.layout.nurse_detail,nurseList);
        ListView listView1 = (ListView) findViewById(R.id.nurseList);
        listView1.setAdapter(NurseAdapter);
    }
}
