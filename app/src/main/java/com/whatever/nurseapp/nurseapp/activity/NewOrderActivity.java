package com.whatever.nurseapp.nurseapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.whatever.nurseapp.nurseapp.Operation.AdminOperation;
import com.whatever.nurseapp.nurseapp.Operation.OrderOperation;
import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.TestData;
import com.whatever.nurseapp.nurseapp.TestFather;
import com.whatever.nurseapp.nurseapp.adapter.OrderAdapter;
import com.whatever.nurseapp.nurseapp.entity.Order;
import com.whatever.nurseapp.nurseapp.util.OrderOperations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NewOrderActivity extends AppCompatActivity {

    private ArrayList<Order> orderList = new ArrayList<>();
    ListView orderListView;
    OrderAdapter orderAdapter;
    Order tempOrder = new Order();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        TextView noOrderTv = (TextView) findViewById(R.id.no_new_order);

        try {
            ArrayList list = OrderOperation.getOrder("new");
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
                    tempOrder = gson.fromJson(jsonString, Order.class);
                    System.out.println(tempOrder.getId());
                    orderList.add(tempOrder);
                }
                if (orderList.size() != 0) {
                    noOrderTv.setVisibility(View.INVISIBLE);
                    orderListView = (ListView) findViewById(R.id.new_order_list);
                    orderAdapter = new OrderAdapter(NewOrderActivity.this, R.layout.layout_order_item, orderList);
                    orderListView.setAdapter(orderAdapter);
                    orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Order order = orderList.get(i);
                            Bundle bundle = new Bundle();
                            bundle.putInt("parentActivity", 0);
                            bundle.putInt("position", i);
                            bundle.putInt("orderID", order.getId());
                            bundle.putString("price", order.getTotalPrice());
                            bundle.putString("time", order.getCreateTime());
                            bundle.putInt("type", order.getType());
                            bundle.putInt("situation", order.getSituation());
                            bundle.putInt("choseNurse", order.getChoseNurse());

                            bundle.putString("patient", order.getPatient().getName());
                            bundle.putString("bed_number", order.getPatient().getBedNumber());
                            bundle.putString("contact", order.getUser().getName());
                            bundle.putString("phone", order.getUser().getId());
                            bundle.putString("service_time", order.getServiceTime());

                            bundle.putString("nurse_name", order.getNurse().getNurseName());
                            bundle.putInt("evaluation", order.getNurse().getNurseEvaluate());
                            bundle.putInt("height", order.getNurse().getNurseHeigt());
                            bundle.putInt("weight", order.getNurse().getNurseWeight());
                            bundle.putString("blood_type", order.getNurse().getNurseBloodType());

                            Intent intent = new Intent(NewOrderActivity.this, OrderDetailActivity.class);
                            intent.putExtras(bundle);
                            startActivityForResult(intent, 1);
                        }
                    });
                }
//                Toast.makeText(NewOrderActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(NewOrderActivity.this, HomeActivity.class);
//                startActivity(intent);
            } else {
                JSONObject object = new JSONObject((String) list.get(1));
                String message = object.getString("data");
                Toast.makeText(NewOrderActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Bundle bundle = data.getExtras();
                int position = bundle.getInt("position");
                int situation = bundle.getInt("situation");
                int newChoseNurse = bundle.getInt("chose_nurse");
                System.out.println("situation"+situation);
                System.out.println("chose"+newChoseNurse);
                if (orderListView != null) {
                    int start = orderListView.getFirstVisiblePosition();
                    View view = orderListView.getChildAt(position - start);
                    TextView refresh = (TextView) view.findViewById(R.id.situation);
                    TextView choiceRefresh = (TextView) view.findViewById(R.id.chose_nurse);
                    switch (situation) {
                        case 1:
                            break;
                        case 2:
                            refresh.setText("已取消");
                            break;
                        case 3:
                            refresh.setText("已完成");
                            break;
                        case 4:
                            refresh.setText("进行中");
                            break;
                        case 5:
                            refresh.setText("已提醒付款");
                            break;
                        default:
                            break;
                    }

                    if (newChoseNurse == 1) {
                        choiceRefresh.setText("已选择护工");
                    }

                }

            }
        }
    }

}
