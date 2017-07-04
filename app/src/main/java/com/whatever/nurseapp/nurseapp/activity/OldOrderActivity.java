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

public class OldOrderActivity extends AppCompatActivity {

    private ArrayList<Order> orderList = new ArrayList<>();
    ListView orderListView;
    OrderAdapter orderAdapter;
    Order tempOrder = new Order();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_order);

        TextView noOrderTv = (TextView) findViewById(R.id.no_old_order);


        try {
            ArrayList list = OrderOperation.getOrder("old");
            if (Integer.parseInt((String) list.get(0)) == 200) {
                final JSONObject object = new JSONObject((String) list.get(1));
                String message = object.getString("data");
                System.out.println(message);
                JSONArray jsonArray = new JSONArray(message);
                JSONObject jsonObject;
                String jsonString = "";
                Gson gson = new Gson();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = (JSONObject) jsonArray.get(i);
                    jsonString = jsonObject.toString();
                    tempOrder = gson.fromJson(jsonString, Order.class);
                    orderList.add(tempOrder);
                }
                if (orderList.size() != 0) {
                    noOrderTv.setVisibility(View.INVISIBLE);
                    orderListView = (ListView) findViewById(R.id.old_order_list);
                    orderAdapter = new OrderAdapter(OldOrderActivity.this, R.layout.layout_order_item, orderList);
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


                            Intent intent = new Intent(OldOrderActivity.this, OrderDetailActivity.class);
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
                Toast.makeText(OldOrderActivity.this, message, Toast.LENGTH_SHORT).show();
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
