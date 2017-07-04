package com.whatever.nurseapp.nurseapp.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.whatever.nurseapp.nurseapp.Int_to_filed;
import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.entity.Nurse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpdateNurse extends AppCompatActivity {
    private EditText name;
    private EditText id;
    private RadioGroup sex;
    private EditText age;
    private EditText workAge;
    private EditText area;
    private TextView evaluate;
    private EditText price;
    private EditText filed;
    private EditText heigt;
    private EditText weight;
    private EditText bloodType;
    private EditText nation;
    private EditText identity;
    private EditText constellation;
    private EditText animal;
    private EditText description;
    private EditText phone;
    private Button delete;
    private Button update;
    private RadioButton male;
    private RadioButton female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nurse);

        final Intent intent = new Intent();
        Bundle bundle = this.getIntent().getExtras();
        String set_filed_bundle = "";
        final int position = bundle.getInt("position");
        String name_bundle = bundle.getString("Nurse_name");
        String id_bundle = bundle.getInt("Nurse_Id") + "";
        String workage_bundle = bundle.getInt("Nurse_workage") + "";
        String age_bundle = bundle.getInt("Nurse_age") + "";
        String phone_bundle = bundle.getString("Nurse_phone");
        String sex_bundle = bundle.getString("Nurse_sex");
        String area_bundle = bundle.getString("Nurse_Area");
        String price_bundle = bundle.getInt("Nurse_price") + "";
        ArrayList<Integer> filed_bundle = bundle.getIntegerArrayList("Nurse_filed");
        String evaluate_bundle = bundle.getInt("Nurse_evaluate") + "";
        String height_bundle = bundle.getInt("Nurse_height") + "";
        String weight_bundle = bundle.getInt("Nurse_weight") + "";
        String bloodType_bundle = bundle.getString("BloodType");
        String nation_bundle = bundle.getString("Nurse_nation");
        String identify_bundle = bundle.getString("Nurse_identify");
        String constellation_bundle = bundle.getString("Nurse_constellation");
        String animal_bundle = bundle.getString("Nurse_animal");
        String description_bundle = bundle.getString("Nurse_description");

        name = (EditText) findViewById(R.id.editText_name);
        id = (EditText) findViewById(R.id.editText_id);
        sex = (RadioGroup) findViewById(R.id.sex);
        age = (EditText) findViewById(R.id.editText_age);
        workAge = (EditText) findViewById(R.id.editText_experience);
        area = (EditText) findViewById(R.id.editText_area);
        evaluate = (TextView) findViewById(R.id.final_evaluate);
        price = (EditText) findViewById(R.id.editText_price);
        filed = (EditText) findViewById(R.id.editText_protectArea);
        heigt = (EditText) findViewById(R.id.editText_heigt);
        weight = (EditText) findViewById(R.id.editText_weight);
        bloodType = (EditText) findViewById(R.id.editText_bloodType);
        nation = (EditText) findViewById(R.id.editText_nation);
        identity = (EditText) findViewById(R.id.editText_identity);
        constellation = (EditText) findViewById(R.id.editText_constellation);
        animal = (EditText) findViewById(R.id.editText_animal);
        description = (EditText) findViewById(R.id.editText_description);
        phone = (EditText) findViewById(R.id.editText_phone);
        delete = (Button) findViewById(R.id.button_delete);
        update = (Button) findViewById(R.id.button_update);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);

        if (sex_bundle.equals("0"))
            male.setChecked(true);
        else
            female.setChecked(true);
        name.setText(name_bundle);
        id.setText(id_bundle);
        workAge.setText(workage_bundle);
        evaluate.setText(evaluate_bundle);
        age.setText(age_bundle);
        phone.setText(phone_bundle);
        area.setText(area_bundle);
        price.setText(price_bundle);
        heigt.setText(height_bundle);
        weight.setText(weight_bundle);
        animal.setText(animal_bundle);
        bloodType.setText(bloodType_bundle);
        identity.setText(identify_bundle);
        nation.setText(nation_bundle);
        constellation.setText(constellation_bundle);
        description.setText(description_bundle);
        for (int i = 0; i < filed_bundle.size(); i++) {
            set_filed_bundle = set_filed_bundle + Int_to_filed.to_filed(filed_bundle.get(i));
            if (i % 2 == 0 && i != filed_bundle.size() - 1) {
                set_filed_bundle += " ";
            }
        }
        filed.setText(set_filed_bundle);

        sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == male.getId())
                    Toast.makeText(UpdateNurse.this, male.getText().toString(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UpdateNurse.this, female.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
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
                                Intent intent = new Intent();
                                intent.putExtra("id",id.getText().toString());
                                setResult(3,intent);
                                finish();
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

                String name_commit = name.getText().toString();
                String id_commit = id.getText().toString();
                String workage_commit = workAge.getText().toString();
                String evaluate_commit = evaluate.getText().toString();
                String age_commit = age.getText().toString();
                String phone_commit = phone.getText().toString();
                String area_commit = area.getText().toString();
                String price_commit = price.getText().toString();
                String height_commit = heigt.getText().toString();
                String weight_commit = weight.getText().toString();
                String animal_commit = animal.getText().toString();
                String bloodType_commit = bloodType.getText().toString();
                String identify_commit = identity.getText().toString();
                String nation_commit = nation.getText().toString();
                String constellation_commit = constellation.getText().toString();
                String filed_commit = filed.getText().toString();
                String description_commit = description.getText().toString();

                int sex_commit = 0;
                int position_commit = position;

                for (int i = 0; i < sex.getChildCount(); i++) {
                    RadioButton r = (RadioButton) sex.getChildAt(i);
                    if (r.isChecked()) {
                        //获取性别选项
                        if (r.getText().toString().equals("男")) {
                            sex_commit = 0;
                        } else sex_commit = 1;
                        break;
//                        Toast.makeText(UpdateNurse.this, r.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                //上传数据库逻辑
                //更新本地缓存


                Bundle bundle = new Bundle();

                bundle.putString("name", name_commit);
                bundle.putString("id", id_commit);
                bundle.putString("work_age", workage_commit);
                bundle.putString("evaluate", evaluate_commit);
                bundle.putString("age", age_commit);
                bundle.putString("phone", phone_commit);
                bundle.putString("area", area_commit);
                bundle.putString("price", price_commit);
                bundle.putString("height", height_commit);
                bundle.putString("weight", weight_commit);
                bundle.putString("animal", animal_commit);
                bundle.putString("bloodType", bloodType_commit);
                bundle.putString("identify", identify_commit);
                bundle.putString("nation", nation_commit);
                bundle.putString("constellation", constellation_commit);
                bundle.putString("filed", filed_commit);
                bundle.putString("description", description_commit);
                bundle.putInt("sex", sex_commit);
                bundle.putInt("position", position_commit);

                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
