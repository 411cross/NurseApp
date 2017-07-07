package com.whatever.nurseapp.nurseapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.whatever.nurseapp.nurseapp.Operation.NurseOperation;
import com.whatever.nurseapp.nurseapp.Operation.OrderOperation;
import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.adapter.NurseAdapter;
import com.whatever.nurseapp.nurseapp.entity.Nurse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ChooseNurseActivity extends AppCompatActivity {

    private ArrayList<Nurse> nurseList = new ArrayList<>();
    private Nurse tempNurse = new Nurse();
    private ListView nurseListView;
    private NurseAdapter nurseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_nurse);
        nurseListView = (ListView) findViewById(R.id.nurse_list);

        Bundle bundle = getIntent().getExtras();
//        Order od = (Order)getIntent().getSerializableExtra("order");
        int area = bundle.getInt("area");
        final int orderId = bundle.getInt("order_id");

        try {
            ArrayList list = NurseOperation.getAeraNurse(area);
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
                    nurseAdapter = new NurseAdapter(ChooseNurseActivity.this, R.layout.nurse_detail,nurseList);
                    nurseListView.setAdapter(nurseAdapter);
                    nurseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Nurse nurse = nurseList.get(position);
                            final int nurseId = nurse.getNurseId();
                            AlertDialog alertDialog = new AlertDialog.Builder(ChooseNurseActivity.this).
                                    setMessage("确定选择该护工？").
                                    setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // TODO Auto-generated method stub
//                                TestFather.getTestData().getOrderAccepted().add(orderID);
//                                TestFather.getTestData().getNewOrderList().get(position).setSituation(4);
                                            try {
                                                ArrayList list = OrderOperation.chooseNurseForPatient(orderId, nurseId);
                                                System.out.println(Integer.parseInt((String) list.get(0)));
                                                if (Integer.parseInt((String) list.get(0)) == 200) {
                                                    Toast.makeText(ChooseNurseActivity.this, "选择成功", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    JSONObject object = new JSONObject((String) list.get(1));
                                                    String message = object.getString("data");
                                                    Toast.makeText(ChooseNurseActivity.this, message, Toast.LENGTH_SHORT).show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            } catch (ExecutionException e) {
                                                e.printStackTrace();
                                            }
                                            // 设置返回结果为RESULT_OK, intent可以传入一些其他的参数, 在onActivityResult中的data中可以获取到
                                        }
                                    }).
                                    setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // TODO Auto-generated method stub
                                        }
                                    }).
                                    create();
                            alertDialog.show();
                            Bundle bundle = new Bundle();
                            bundle.putString("nurse_name", nurse.getNurseName());
                            bundle.putInt("nurse_evaluation", nurse.getNurseEvaluate());
                            bundle.putInt("nurse_height",nurse.getNurseHeigt());
                            bundle.putInt("nurse_weight",nurse.getNurseWeight());
                            bundle.putString("nurse_blood_type",nurse.getNurseBloodType());

                            Intent intent = new Intent(ChooseNurseActivity.this,UpdateNurse.class);
                            intent.putExtras(bundle);
                            setResult(RESULT_OK, intent);
                        }
                    });
                }
//                Toast.makeText(NewOrderActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(NewOrderActivity.this, HomeActivity.class);
//                startActivity(intent);
            } else {
                JSONObject object = new JSONObject((String) list.get(1));
                String message = object.getString("data");
                Toast.makeText(ChooseNurseActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }
}
