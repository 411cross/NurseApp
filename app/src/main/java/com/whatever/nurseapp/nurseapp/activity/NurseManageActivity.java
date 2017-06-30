package com.whatever.nurseapp.nurseapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.adapter.NurseAdapter;
import com.whatever.nurseapp.nurseapp.entity.Nurse;

import java.util.ArrayList;

public class NurseManageActivity extends AppCompatActivity {
    private ArrayList<Nurse> nurseList = new ArrayList<>();
    private Button add_nurse_btn;
    private Button search_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_manage);

        add_nurse_btn = (Button)findViewById(R.id.add_nurse_btn);
        search_btn = (Button)findViewById(R.id.button_search);

        String[] s1 = {"恶心"};
        String[] s2 = {"感冒","植物人"};
        nurseList.add(new Nurse("牛大春",20,"男","广东",10,150,s1,"10010"));
        nurseList.add(new Nurse("牛欢喜",25,"女","湖南",9,125,s2,"10086"));
        NurseAdapter NurseAdapter = new NurseAdapter(NurseManageActivity.this, R.layout.nurse_detail,nurseList);
        final ListView listView1 = (ListView) findViewById(R.id.nurseList);
        listView1.setAdapter(NurseAdapter);

        add_nurse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(NurseManageActivity.this);
                final View myLoginView = layoutInflater.inflate(R.layout.add_nurse_detail, null);
                AlertDialog alertDialog = new AlertDialog.Builder(NurseManageActivity.this).
                        setTitle("添加护工").setView(myLoginView).
                        setPositiveButton("提交", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String sex;
                                EditText ed_name = (EditText) myLoginView.findViewById(R.id.editText_name);
                                //护工姓名
                                EditText ed_age = (EditText)myLoginView.findViewById(R.id.editText_age);
                                //护工年龄
                                EditText ed_tel = (EditText)myLoginView.findViewById(R.id.editText_tel);
                                //护工电话
                                EditText ed_area =(EditText)myLoginView.findViewById(R.id.editText_area);
                                //护工地址
                                EditText ed_pricce =(EditText)myLoginView.findViewById(R.id.editText_price);
                                //护工收费
                                RadioButton male_btn = (RadioButton)myLoginView.findViewById(R.id.sex_male);
                                RadioButton female_btn = (RadioButton)myLoginView.findViewById(R.id.sex_female);
                                if (male_btn.isChecked())
                                    sex = male_btn.getText().toString();
                                else
                                    sex = female_btn.getText().toString();

                                Toast.makeText(NurseManageActivity.this, sex, Toast.LENGTH_SHORT).show();

                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).
                        create();
                alertDialog.show();
            }
        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NurseManageActivity.this);
                builder.setTitle("输入护工姓名");
                final EditText editText = new EditText(NurseManageActivity.this);
                builder.setView(editText);
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(NurseManageActivity.this,
                                        "输入的姓名是：" + editText.getText(),
                                        Toast.LENGTH_SHORT).show();
                                ArrayList<Nurse> TestList = new ArrayList<>();
                                String[] s3 = {"恶心"};
                                String[] s4 = {"感冒","眼花"};
                                TestList.add(new Nurse("牛大逼",20,"男","广东",10,150,s3,"10010"));
                                TestList.add(new Nurse("牛小春",90,"女","湖南",9,125,s4,"10086"));
                                NurseAdapter TestAdapter = new NurseAdapter(NurseManageActivity.this, R.layout.nurse_detail,TestList);
                                listView1.setAdapter(TestAdapter);
                            }
                        });
                builder.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(NurseManageActivity.this, "取消",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.show();
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nurse nurse = nurseList.get(position);

                Bundle bundle = new Bundle();
                bundle.putString("Nurse_name", nurse.getNurseName());
                bundle.putInt("Nurse_age", nurse.getNurseAge());
                bundle.putString("Nurse_Area", nurse.getNurseArea());
                bundle.putString("Nurse_sex", nurse.getNurseSex());
                bundle.putInt("Nurse_price", nurse.getNursePrice());
                bundle.putInt("Nurse_evaluate", nurse.getNurseEvaluate());
                bundle.putString("Nurse_tel",nurse.getTelephone());
                bundle.putStringArray("Nurse_filed",nurse.getNurseProtectArea());
                Intent intent = new Intent(NurseManageActivity.this,UpdateNurse.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
