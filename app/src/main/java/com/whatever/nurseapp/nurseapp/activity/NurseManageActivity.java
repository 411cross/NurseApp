package com.whatever.nurseapp.nurseapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.BuddhistCalendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.whatever.nurseapp.nurseapp.Int_to_filed;
import com.whatever.nurseapp.nurseapp.Operation.AdminOperation;
import com.whatever.nurseapp.nurseapp.Operation.NurseOperation;
import com.whatever.nurseapp.nurseapp.Operation.OrderOperation;
import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.TestData_nurse;
import com.whatever.nurseapp.nurseapp.adapter.NameListAdapter;
import com.whatever.nurseapp.nurseapp.adapter.NurseAdapter;
import com.whatever.nurseapp.nurseapp.adapter.OrderAdapter;
import com.whatever.nurseapp.nurseapp.entity.Nurse;
import com.whatever.nurseapp.nurseapp.entity.Order;
import com.whatever.nurseapp.nurseapp.widget.ClearEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NurseManageActivity extends AppCompatActivity {
    private ArrayList<Nurse> nurseList = new ArrayList<>();
    private Nurse tempNurse = new Nurse();

    private Button search_btn;
    private ListView nurse_list;
    private TestData_nurse t;
    private NurseAdapter nurseAdapter;
    private ClearEditText et_search;
    private ListView name_list;
    private View whiteView;
    private RelativeLayout searchRoot;
//    private TextView noNurse;

    private String[] data;
    private ArrayAdapter<String> mAdapter;

    private ArrayList<String> nameList = AdminOperation.nurseNameList;
    private NameListAdapter mUserAdapter;
    private ArrayList<Nurse> tempNurseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_manage);
        searchRoot = (RelativeLayout) findViewById(R.id.search_root);
        nurse_list = (ListView) findViewById(R.id.nurse_list);
//        noNurse = (TextView) findViewById(R.id.no_nurse);
        whiteView = (View) findViewById(R.id.white_view);
//        noNurse.setVisibility(View.INVISIBLE);
        whiteView.setVisibility(View.INVISIBLE);
        initView();

        name_list.setVisibility(View.INVISIBLE);

//        search_btn = (Button)findViewById(R.id.button_search);

        try {
            ArrayList list = NurseOperation.getNurseList();
            if (Integer.parseInt((String) list.get(0)) == 200) {
                JSONObject object = new JSONObject((String) list.get(1));
                String message = object.getString("data");
                JSONArray jsonArray = new JSONArray(message);
                JSONObject jsonObject;
                String jsonString = "";
                Gson gson = new Gson();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = (JSONObject) jsonArray.get(i);
                    jsonString = jsonObject.toString();
                    tempNurse = gson.fromJson(jsonString, Nurse.class);
                    nurseList.add(tempNurse);
                }
                if (nurseList.size() != 0) {
                    nurseAdapter = new NurseAdapter(NurseManageActivity.this, R.layout.nurse_detail,nurseList);
                    nurse_list.setAdapter(nurseAdapter);
                    nurse_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Nurse nurse = nurseList.get(position);

                            Bundle bundle = new Bundle();
                            bundle.putInt("index", nurseList.indexOf(nurse));
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
//                Toast.makeText(NewOrderActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(NewOrderActivity.this, HomeActivity.class);
//                startActivity(intent);
            } else {
                JSONObject object = new JSONObject((String) list.get(1));
                String message = object.getString("data");
                Toast.makeText(NurseManageActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        et_search.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    name_list.setVisibility(View.VISIBLE);
                    whiteView.setVisibility(View.VISIBLE);
                    nurse_list.setVisibility(View.INVISIBLE);
                } else {
                    name_list.setVisibility(View.INVISIBLE);
                    whiteView.setVisibility(View.INVISIBLE);
                    nurse_list.setVisibility(View.VISIBLE);
                }
            }
        });

        searchRoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                searchRoot.setFocusable(true);
                searchRoot.setFocusableInTouchMode(true);
                searchRoot.requestFocus();
                return false;
            }
        });

        name_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = nameList.get(i);
                try {
                    ArrayList list = NurseOperation.getSpecificNurse(name);
                    if (Integer.parseInt((String) list.get(0)) == 200) {
                        JSONObject object = new JSONObject((String) list.get(1));
                        String message = object.getString("data");
                        JSONArray jsonArray = new JSONArray(message);
                        JSONObject jsonObject;
                        String jsonString = "";
                        Gson gson = new Gson();
                        AdminOperation.tempNurseList = new ArrayList<>();
                        for (int j = 0; j < jsonArray.length(); j++) {
                            jsonObject = (JSONObject) jsonArray.get(j);
                            jsonString = jsonObject.toString();
                            tempNurse = gson.fromJson(jsonString, Nurse.class);
                            AdminOperation.tempNurseList.add(tempNurse);
                        }
                        System.out.println(AdminOperation.tempNurseList.size());
                        if (AdminOperation.tempNurseList.size() == 1) {
//                            noOrderTv.setVisibility(View.INVISIBLE);
                            System.out.println(AdminOperation.tempNurseList.get(0));
                            tempNurse = AdminOperation.tempNurseList.get(0);

                            Bundle bundle = new Bundle();
                            bundle.putInt("index", 0);
                            bundle.putString("Nurse_name", tempNurse.getNurseName());
                            bundle.putInt("Nurse_Id",tempNurse.getNurseId());
                            bundle.putString("Nurse_sex", tempNurse.getNurseSex()+"");
                            bundle.putInt("Nurse_age", tempNurse.getNurseAge());
                            bundle.putInt("Nurse_workage",tempNurse.getNurseWorkAge());
                            bundle.putString("Nurse_Area", tempNurse.getNurseArea());
                            bundle.putInt("Nurse_evaluate", tempNurse.getNurseEvaluate());
                            bundle.putInt("Nurse_price", tempNurse.getNursePrice());
                            bundle.putIntegerArrayList("Nurse_filed",tempNurse.getNurseProtectArea());
                            bundle.putInt("Nurse_height",tempNurse.getNurseHeigt());
                            bundle.putInt("Nurse_weight",tempNurse.getNurseWeight());
                            bundle.putString("BloodType",tempNurse.getNurseBloodType());
                            bundle.putString("Nurse_nation",tempNurse.getNurseNation());
                            bundle.putString("Nurse_identify",tempNurse.getNurseIdentity());
                            bundle.putString("Nurse_constellation",tempNurse.getNurseConstellation());
                            bundle.putString("Nurse_animal",tempNurse.getNurseAnimal());
                            bundle.putString("Nurse_description",tempNurse.getNurseDescription());
                            bundle.putString("Nurse_phone",tempNurse.getNursePhone());

                            Intent intent = new Intent(NurseManageActivity.this,UpdateNurse.class);
                            intent.putExtras(bundle);
                            startActivityForResult(intent,1);
                        } else if (AdminOperation.tempNurseList.size() > 1) {
                            Intent intent = new Intent(NurseManageActivity.this,MultiResultActivity.class);
                            startActivityForResult(intent,1);
                        }
//                        else if (AdminOperation.tempNurseList.size() == 0) {
//                            noNurse.setVisibility(View.VISIBLE);
//                        }
//                Toast.makeText(NewOrderActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(NewOrderActivity.this, HomeActivity.class);
//                startActivity(intent);
                    } else {
                        JSONObject object = new JSONObject((String) list.get(1));
                        String message = object.getString("data");
                        Toast.makeText(NurseManageActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });


//        nurseAdapter = new NurseAdapter(NurseManageActivity.this, R.layout.nurse_detail,nurseList);
//        nurse_list = (ListView) findViewById(R.id.nurseList);
//        nurse_list.setAdapter(nurseAdapter);

//        search_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(NurseManageActivity.this,SearchResultActivity.class);
//                startActivityForResult(intent,1);
//                AlertDialog.Builder builder = new AlertDialog.Builder(NurseManageActivity.this);
//                builder.setTitle("输入护工姓名");
//                final EditText editText = new EditText(NurseManageActivity.this);
//                builder.setView(editText);
//                builder.setPositiveButton("确定",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // TODO Auto-generated method stub
//                                Toast.makeText(NurseManageActivity.this,
//                                        "输入的姓名是：" + editText.getText(),
//                                        Toast.LENGTH_SHORT).show();
//                                ArrayList<Nurse> TestList = new ArrayList<>();
//                                //修改绑定数据源
//                                ArrayList<Integer> s3 = new ArrayList<>();
//                                s3.add(3);
//                                ArrayList<Integer> s4 = new ArrayList<>();
//                                s4.add(3);
//                                s4.add(4);
//                                TestList.add(new Nurse("牛大逼",123456,0,20,10, "广东", 90, 150, s3, 150, 100, "A", "汉族", "123identify", "双鱼座", "鼠", "fit", "7569"));
//                                TestList.add(new Nurse("牛鬼蛇",987654321,1,25,5, "湖南", 80, 250, s4, 160, 120, "AB", "回族", "456identify", "处女座", "牛", "fitshai", "10010"));
//                                NurseAdapter TestAdapter = new NurseAdapter(NurseManageActivity.this, R.layout.nurse_detail,TestList);
//                                nurse_list.setAdapter(TestAdapter);
//                            }
//                        });
//                builder.setNegativeButton("取消",
//                        new DialogInterface.OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // TODO Auto-generated method stub
//                                Toast.makeText(NurseManageActivity.this, "取消",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                builder.show();
//            }
//        });
//        nurse_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Nurse nurse = nurseList.get(position);
//
//                Bundle bundle = new Bundle();
//                bundle.putInt("position",position);
//                bundle.putString("Nurse_name", nurse.getNurseName());
//                bundle.putInt("Nurse_Id",nurse.getNurseId());
//                bundle.putString("Nurse_sex", nurse.getNurseSex()+"");
//                bundle.putInt("Nurse_age", nurse.getNurseAge());
//                bundle.putInt("Nurse_workage",nurse.getNurseWorkAge());
//                bundle.putString("Nurse_Area", nurse.getNurseArea());
//                bundle.putInt("Nurse_evaluate", nurse.getNurseEvaluate());
//                bundle.putInt("Nurse_price", nurse.getNursePrice());
//                bundle.putIntegerArrayList("Nurse_filed",nurse.getNurseProtectArea());
//                bundle.putInt("Nurse_height",nurse.getNurseHeigt());
//                bundle.putInt("Nurse_weight",nurse.getNurseWeight());
//                bundle.putString("BloodType",nurse.getNurseBloodType());
//                bundle.putString("Nurse_nation",nurse.getNurseNation());
//                bundle.putString("Nurse_identify",nurse.getNurseIdentity());
//                bundle.putString("Nurse_constellation",nurse.getNurseConstellation());
//                bundle.putString("Nurse_animal",nurse.getNurseAnimal());
//                bundle.putString("Nurse_description",nurse.getNurseDescription());
//                bundle.putString("Nurse_phone",nurse.getNursePhone());
//
//                Intent intent = new Intent(NurseManageActivity.this,UpdateNurse.class);
//                intent.putExtras(bundle);
//                startActivityForResult(intent,1);
//            }
//        });
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


                if (nurse_list != null) {
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
//                    int start = nurse_list.getFirstVisiblePosition();
//                    View view = nurse_list.getChildAt(position - start);
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
            if(resultCode == 3) {
                Bundle bundle = data.getExtras();
                int index = Integer.valueOf(bundle.getInt("index"));

                nurseList.remove(index);
                nurseAdapter = new NurseAdapter(NurseManageActivity.this, R.layout.nurse_detail,nurseList);
                nurse_list.setAdapter(nurseAdapter);
            }
        }


    private void initView() {
        et_search = (ClearEditText) findViewById(R.id.et_search);
        name_list = (ListView) findViewById(R.id.name_list);

        initListView();
        intiEditView();
    }

    private void intiEditView() {

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mAdapter.getFilter().filter(s);

                mUserAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initListView() {
//        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
//        name_list.setAdapter(mAdapter);

        mUserAdapter = new NameListAdapter(NurseManageActivity.this,nameList);
        name_list.setAdapter(mUserAdapter);

    }
}