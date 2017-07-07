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
import com.whatever.nurseapp.nurseapp.Operation.NurseOperation;
import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.TestData_nurse;
import com.whatever.nurseapp.nurseapp.adapter.NurseAdapter;
import com.whatever.nurseapp.nurseapp.entity.Nurse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by derrickJ on 2017/7/4.
 */

public class AddNurseActivity extends AppCompatActivity{
    private Button save_button;
    private EditText editText_name;
    private EditText editText_id;
    private RadioButton sex_male;
    private RadioButton sex_female;
    private EditText editText_age;
    private EditText editText_experience;
    private EditText editText_area;
    private EditText editText_price;
    private EditText editText_protectArea;
    private EditText editText_height;
    private EditText editText_weight;
    private EditText editText_bloodType;
    private EditText editText_nation;
    private EditText editText_identity;
    private EditText editText_constellation;
    private EditText editText_animal;
    private EditText editText_description;
    private EditText editText_phone;
    private int sex_int = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nurse);
        editText_name = (EditText)findViewById(R.id.editText_name);
        editText_id = (EditText)findViewById(R.id.editText_id);
        sex_male =(RadioButton)findViewById(R.id.male);
        sex_female = (RadioButton)findViewById(R.id.female);
        editText_age = (EditText)findViewById(R.id.editText_age);
        editText_experience = (EditText)findViewById(R.id.editText_experience);
        editText_area = (EditText)findViewById(R.id.editText_area);
        editText_protectArea = (EditText)findViewById(R.id.editText_protectArea);
        editText_height = (EditText)findViewById(R.id.editText_height);
        editText_weight = (EditText)findViewById(R.id.editText_weight);
        editText_bloodType = (EditText)findViewById(R.id.editText_bloodType);
        editText_nation = (EditText)findViewById(R.id.editText_nation);
        editText_identity = (EditText)findViewById(R.id.editText_identity);
        editText_constellation = (EditText)findViewById(R.id.editText_constellation);
        editText_animal = (EditText)findViewById(R.id.editText_animal);
        editText_description = (EditText)findViewById(R.id.editText_description);
        editText_phone = (EditText)findViewById(R.id.editText_phone);
        editText_price = (EditText)findViewById(R.id.editText_price);

        save_button = (Button)findViewById(R.id.button_save);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sex_female.isChecked()) {
                    sex_int = 1;
                }
                ArrayList<Integer> nurseProtectArea = new ArrayList<>();
                nurseProtectArea.add(1);
                nurseProtectArea.add(2);
                Nurse newNurse = new Nurse(editText_name.getText().toString(), Integer.valueOf(editText_id.getText().toString()), sex_int, Integer.valueOf(editText_age.getText().toString()),
                        Integer.valueOf(editText_experience.getText().toString()), editText_area.getText().toString(), 0,
                        Integer.valueOf(editText_price.getText().toString()), nurseProtectArea, Integer.valueOf(editText_height.getText().toString()), Integer.valueOf(editText_weight.getText().toString()), editText_bloodType.getText().toString(),
                        editText_nation.getText().toString(), editText_identity.getText().toString(), editText_constellation.getText().toString(), editText_animal.getText().toString(),
                        editText_description.getText().toString(), editText_phone.getText().toString());
                System.out.println(newNurse.getNurseId());
                try {
                    ArrayList list = NurseOperation.addNurse(newNurse);
                    if (Integer.parseInt((String) list.get(0)) == 200) {
                        JSONObject object = new JSONObject((String) list.get(1));
                        String message = object.getString("data");
                    }
                } catch(JSONException e){
                        e.printStackTrace();
                } catch(ExecutionException e){
                        e.printStackTrace();
                } catch(InterruptedException e){
                        e.printStackTrace();
                }

            }
        });

    }
}
