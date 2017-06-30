package com.whatever.nurseapp.nurseapp.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whatever.nurseapp.nurseapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpdateNurse extends AppCompatActivity {
    private TextView name;
    private TextView age;
    private TextView tel;
    private TextView sex;
    private TextView area;
    private TextView price;
    private TextView filed;
    private Button delete;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nurse);

        Intent intent = new Intent();
        Bundle bundle =this.getIntent().getExtras();
        String set_filed_bundle = "";
        String name_bundle = bundle.getString("Nurse_name");
//        Toast.makeText(UpdateNurse.this, name_bundle, Toast.LENGTH_SHORT).show();
        String age_bundle = bundle.getInt("Nurse_age")+"";
        String tel_bundle = bundle.getString("Nurse_tel");
        String sex_bundle = bundle.getString("Nurse_sex");
        String area_bundle = bundle.getString("Nurse_Area");
        String price_bundle = bundle.getInt("Nurse_price")+"";
        String[] filed_bundle =bundle.getStringArray("Nurse_filed");

        name = (TextView)findViewById(R.id.editText_name);
        age = (TextView)findViewById(R.id.editText_age);
        tel = (TextView)findViewById(R.id.editText_tel);
        sex = (TextView)findViewById(R.id.editText_sex);
        area = (TextView)findViewById(R.id.editText_area);
        price = (TextView)findViewById(R.id.editText_price);
        filed = (TextView)findViewById(R.id.editText_filed);
        delete = (Button)findViewById(R.id.button_delete);
        update = (Button)findViewById(R.id.button_update);

        name.setText(name_bundle);
        age.setText(age_bundle);
        tel.setText(tel_bundle);
        sex.setText(sex_bundle);
        area.setText(area_bundle);
        price.setText(price_bundle);
        for(int i = 0;i<filed_bundle.length;i++){
            set_filed_bundle = set_filed_bundle+filed_bundle[i];
            if(i%2==0&&i!=filed_bundle.length-1){
                set_filed_bundle += " ";
            }
        }
        filed.setText(set_filed_bundle);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateNurse.this);
                builder.setTitle("确认删除吗？");
                // 相当于确定
                builder.setPositiveButton("确认",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age_comfirm = age.getText().toString();
                String name_comfirm= name.getText().toString();
                String tel_comfirm= tel.getText().toString();
                String sex_comfirm= sex.getText().toString();
                String area_comfirm= area.getText().toString();
                String price_comfirm= price.getText().toString();
                String filed_comfirm = filed.getText().toString();
//                Toast.makeText(UpdateNurse.this, name_comfirm, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
