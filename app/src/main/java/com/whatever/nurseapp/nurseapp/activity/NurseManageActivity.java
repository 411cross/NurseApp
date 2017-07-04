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
import android.widget.TextView;
import android.widget.Toast;

import com.whatever.nurseapp.nurseapp.Int_to_filed;
import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.TestData_nurse;
import com.whatever.nurseapp.nurseapp.adapter.NurseAdapter;
import com.whatever.nurseapp.nurseapp.entity.Nurse;

import java.util.ArrayList;

public class NurseManageActivity extends AppCompatActivity {
    private ArrayList<Nurse> nurseList = new ArrayList<>();

    private Button search_btn;
    private ListView listView1;
    private TestData_nurse t;
    private NurseAdapter nurseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_manage);

        search_btn = (Button)findViewById(R.id.button_search);


        t = new TestData_nurse();
        nurseList = t.getA();
        nurseAdapter = new NurseAdapter(NurseManageActivity.this, R.layout.nurse_detail,nurseList);
        listView1 = (ListView) findViewById(R.id.nurseList);
        listView1.setAdapter(nurseAdapter);

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
                                //修改绑定数据源
                                ArrayList<Integer> s3 = new ArrayList<>();
                                s3.add(3);
                                ArrayList<Integer> s4 = new ArrayList<>();
                                s4.add(3);
                                s4.add(4);
                                TestList.add(new Nurse("牛大逼",123456,0,20,10, "广东", 90, 150, s3, 150, 100, "A", "汉族", "123identify", "双鱼座", "鼠", "fit", "7569"));
                                TestList.add(new Nurse("牛鬼蛇",987654321,1,25,5, "湖南", 80, 250, s4, 160, 120, "AB", "回族", "456identify", "处女座", "牛", "fitshai", "10010"));
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
                bundle.putInt("position",position);
                bundle.putString("Nurse_name", nurse.getNurseName());
                bundle.putInt("Nurse_Id",nurse.getNurseId());
                bundle.putString("Nurse_sex", nurse.getNurseSex()+"");
                bundle.putInt("Nurse_age", nurse.getNurseAge());
                bundle.putInt("Nurse_workage",nurse.getNurseWorkAge());
                bundle.putString("Nurse_Area", nurse.getNurseArea());
                bundle.putInt("Nurse_evaluate", nurse.getNurseEvaluate());
                bundle.putInt("Nurse_price", nurse.getNursePrice());
                bundle.putIntegerArrayList("Nurse_filed",nurse.getNurseProtectArea());
                bundle.putInt("Nurse_height",nurse.getNurseHeigt());
                bundle.putInt("Nurse_weight",nurse.getNurseWeight());
                bundle.putString("BloodType",nurse.getNurseBloodType());
                bundle.putString("Nurse_nation",nurse.getNurseNation());
                bundle.putString("Nurse_identify",nurse.getNurseIdentity());
                bundle.putString("Nurse_constellation",nurse.getNurseConstellation());
                bundle.putString("Nurse_animal",nurse.getNurseAnimal());
                bundle.putString("Nurse_description",nurse.getNurseDescription());
                bundle.putString("Nurse_phone",nurse.getNursePhone());

                Intent intent = new Intent(NurseManageActivity.this,UpdateNurse.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
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

                String name = bundle.getString("name");
                String id = bundle.getString("id");
                String work_age = bundle.getString("work_age");
                String evaluate = bundle.getString("evaluate");
                String age = bundle.getString("age");
                String phone = bundle.getString("phone");
                String area = bundle.getString("area");
                String price = bundle.getString("price");
                String height = bundle.getString("height");
                String weight = bundle.getString("weight");
                String animal = bundle.getString("animal");
                String bloodType = bundle.getString("bloodType");
                String identify = bundle.getString("identify");
                String nation = bundle.getString("nation");
                String constellation = bundle.getString("constellation");
                String filed = bundle.getString("filed");
                String description = bundle.getString("description");

                String sex = "";


                if (listView1 != null) {
                    Nurse nurse_temp = nurseList.get(position);
                    if(bundle.getInt("sex") == 0){
                        sex = "男";
                        nurse_temp.setNurseSex(0);
                    }
                    else {
                        sex = "女";
                        nurse_temp.setNurseSex(1);
                    }
                    nurse_temp.setNurseName(name);
                    nurse_temp.setNurseId(Integer.valueOf(id));
                    nurse_temp.setNurseWorkAge(Integer.valueOf(work_age));
                    nurse_temp.setNurseEvaluate(Integer.valueOf(evaluate));
                    nurse_temp.setNurseAge(Integer.valueOf(age));
                    nurse_temp.setNursePhone(phone);
                    nurse_temp.setNurseArea(area);
                    nurse_temp.setNursePrice(Integer.valueOf(price));
                    nurse_temp.setNurseHeigt(Integer.valueOf(height));
                    nurse_temp.setNurseWeight(Integer.valueOf(weight));
                    nurse_temp.setNurseAnimal(animal);
                    nurse_temp.setNurseBloodType(bloodType);
                    nurse_temp.setNurseIdentity(identify);
                    nurse_temp.setNurseNation(nation);
                    nurse_temp.setNurseConstellation(constellation);
                    nurse_temp.setNurseDescription(description);
                    String[] filed_array = filed.split(" ");
                    ArrayList<Integer> filed_intarray = new ArrayList<>(filed_array.length);
                    for(int i = 0;i<filed_array.length;i++){
                        for(int n = 0;n< Int_to_filed.filed_length();n++){
                            if(filed_array[i].equals(Int_to_filed.to_filed(n))) {
                                filed_intarray.add(n);
                            }
                        }
                    }
                    nurse_temp.setNurseProtectArea(filed_intarray);
                    //更新本地缓存
//                    int start = listView1.getFirstVisiblePosition();
//                    View view = listView1.getChildAt(position - start);
//                    TextView refresh_age = (TextView) view.findViewById(R.id.NurseAge);
//                    TextView refresh_name = (TextView) view.findViewById(R.id.NurseName);
//                    TextView refresh_area = (TextView) view.findViewById(R.id.NurseArea);
//                    TextView refresh_price = (TextView) view.findViewById(R.id.NursePrice);
//                    TextView refresh_filed = (TextView) view.findViewById(R.id.filed);
//                    TextView refresh_sex = (TextView) view.findViewById(R.id.NurseSex);
//
//                    Toast.makeText(NurseManageActivity.this, "年龄"+ age, Toast.LENGTH_SHORT).show();
//                    refresh_age.setText(age);
//                    refresh_name.setText(name);
//                    Toast.makeText(NurseManageActivity.this,refresh_name.getText().toString(),Toast.LENGTH_SHORT).show();
//                    refresh_area.setText(area);
//                    refresh_price.setText(price);
//                    refresh_filed.setText(filed);
//                    refresh_sex.setText(sex);

                    }
                }

            }
            if(resultCode == 0) {
            Bundle bundle = data.getExtras();
            int id = Integer.valueOf(bundle.getString("id")) ;
            t.delete_Nurse(id);
            nurseAdapter = new NurseAdapter(NurseManageActivity.this, R.layout.nurse_detail,t.getA());
            listView1.setAdapter(nurseAdapter);
            }
        }
}