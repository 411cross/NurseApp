package com.whatever.nurseapp.nurseapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.whatever.nurseapp.nurseapp.Int_to_filed;
import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.TestData_nurse;
import com.whatever.nurseapp.nurseapp.adapter.NurseAdapter;
import com.whatever.nurseapp.nurseapp.entity.Nurse;

import java.util.ArrayList;

/**
 * Created by derrickJ on 2017/7/4.
 */

public class AddNurseActivity extends AppCompatActivity{
    private Button save_button;
    private EditText editText_name;
    private EditText editText_id;
    private RadioGroup sex;
    private EditText editText_age;
    private EditText editText_experience;
    private EditText editText_area;
    private EditText editText_price;
    private EditText editText_protectArea;
    private EditText editText_heigt;
    private EditText editText_weight;
    private EditText editText_bloodType;
    private EditText editText_nation;
    private EditText editText_identity;
    private EditText editText_constellation;
    private EditText editText_animal;
    private EditText editText_description;
    private EditText editText_phone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nurse);
        editText_name = (EditText)findViewById(R.id.editText_name);
        editText_id = (EditText)findViewById(R.id.editText_id);
        sex = (RadioGroup)findViewById(R.id.sex);
        editText_age = (EditText)findViewById(R.id.editText_age);
        editText_experience = (EditText)findViewById(R.id.editText_experience);
        editText_area = (EditText)findViewById(R.id.editText_area);
        editText_protectArea = (EditText)findViewById(R.id.editText_protectArea);
        editText_heigt = (EditText)findViewById(R.id.editText_heigt);
        editText_weight = (EditText)findViewById(R.id.editText_weight);
        editText_bloodType = (EditText)findViewById(R.id.editText_bloodType);
        editText_nation = (EditText)findViewById(R.id.editText_nation);
        editText_identity = (EditText)findViewById(R.id.editText_identity);
        editText_constellation = (EditText)findViewById(R.id.editText_constellation);
        editText_animal = (EditText)findViewById(R.id.editText_animal);
        editText_description = (EditText)findViewById(R.id.editText_description);
        editText_phone = (EditText)findViewById(R.id.editText_phone);

        save_button = (Button)findViewById(R.id.button_save);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                }
        });

    }


}
