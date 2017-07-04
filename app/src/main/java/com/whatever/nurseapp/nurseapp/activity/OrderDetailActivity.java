package com.whatever.nurseapp.nurseapp.activity;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.whatever.nurseapp.nurseapp.Operation.AdminOperation;
import com.whatever.nurseapp.nurseapp.Operation.OrderOperation;
import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.TestData;
import com.whatever.nurseapp.nurseapp.TestFather;
import com.whatever.nurseapp.nurseapp.entity.Order;
import com.whatever.nurseapp.nurseapp.util.OrderOperations;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class OrderDetailActivity extends AppCompatActivity {

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Bundle bundle = this.getIntent().getExtras();
        Order od = (Order)getIntent().getSerializableExtra("order");
        int parentActivity = bundle.getInt("parentActivity");
        position = bundle.getInt("position");
        final int orderID = bundle.getInt("orderID");
        String price = bundle.getString("price");
        String time = bundle.getString("time");
        int type = bundle.getInt("type");
        final int situation = bundle.getInt("situation");
        int notified = bundle.getInt("notified");
        int choseNurse = bundle.getInt("choseNurse");

        TextView orderIDTv = (TextView) findViewById(R.id.order_id);
        TextView timeTv = (TextView) findViewById(R.id.time);
        TextView priceTv = (TextView) findViewById(R.id.price);
        final TextView situationTv = (TextView) findViewById(R.id.situation);
        TextView patientTv = (TextView) findViewById(R.id.patient);
        TextView bedNumberTv = (TextView) findViewById(R.id.bed_number);
        TextView contactTv = (TextView) findViewById(R.id.contact);
        TextView phoneTv = (TextView) findViewById(R.id.phone);
        TextView typeTv = (TextView) findViewById(R.id.type);
        TextView serviceTimeTv = (TextView) findViewById(R.id.service_time);
        TextView nurseTv = (TextView) findViewById(R.id.nurse_name);
        TextView evaluationTv = (TextView) findViewById(R.id.evaluation);
        TextView heightTV = (TextView) findViewById(R.id.height);
        TextView weightTv = (TextView) findViewById(R.id.weight);
        TextView bloodType = (TextView) findViewById(R.id.blood_type);
        Button addBtn = (Button) findViewById(R.id.add_button);

        final Button finishBtn = (Button) findViewById(R.id.finish);
        final Button acceptBtn = (Button) findViewById(R.id.accept);
        final Button rejectBtn = (Button) findViewById(R.id.reject);
        final Button notifyBtn = (Button) findViewById(R.id.notify);
        RelativeLayout nurseInfo = (RelativeLayout) findViewById(R.id.nurseInfo_relativeLayout);
        final RelativeLayout addNurse = (RelativeLayout) findViewById(R.id.addNurse_relativeLayout);

        orderIDTv.setText(orderID);
        priceTv.setText(price);
        timeTv.setText(time);

        switch (type) {
            case 1:
                typeTv.setText("内科");
                break;
            case 2:
                typeTv.setText("外科");
                break;
            case 3:
                typeTv.setText("妇产科");
                break;
            default:
                break;
        }

        switch (situation) {
            case 0:
                situationTv.setText("未付款");
                break;
            case 1:
                situationTv.setText("已付款");
                break;
            case 2:
                situationTv.setText("已取消");
                break;
            case 3:
                situationTv.setText("已完成");
                break;
            case 4:
                situationTv.setText("进行中");
                break;
            case 5:
                situationTv.setText("已提醒付款");
                break;
            default:
                break;
        }

        if (situation == 0 || situation == 5) {
            notifyBtn.setVisibility(View.VISIBLE);
            if (notified == 0) {
                notifyBtn.setText("提醒");
            } else {
                notifyBtn.setText("再次提醒");
            }
        } else {
            notifyBtn.setVisibility(View.INVISIBLE);
        }

        if (situation == 1) {
            acceptBtn.setVisibility(View.VISIBLE);
            rejectBtn.setVisibility(View.VISIBLE);
        } else {
            acceptBtn.setVisibility(View.INVISIBLE);
            rejectBtn.setVisibility(View.INVISIBLE);
        }

        if (situation == 2 && choseNurse == 0) {
            nurseInfo.setVisibility(View.INVISIBLE);
            addNurse.setVisibility(View.INVISIBLE);
        }

        if (situation == 4) {
            finishBtn.setVisibility(View.VISIBLE);
        } else {
            finishBtn.setVisibility(View.INVISIBLE);
        }

        if (choseNurse == 1) {
            addNurse.setVisibility(View.INVISIBLE);
        } else {
            nurseInfo.setVisibility(View.INVISIBLE);
        }


        /**
         * 接单按钮
         * 实现确定接单之后刷新当前Activity的状态成"进行中"，并传送状态更改给上一层Activity
         * 同时将接单按钮和拒绝按钮设置为不可见
         */
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LayoutInflater layoutInflater = LayoutInflater.from(OrderDetailActivity.this);
//                final View myLoginView = layoutInflater.inflate(R.layout.user_address, null);
                AlertDialog alertDialog = new AlertDialog.Builder(OrderDetailActivity.this).
                        setMessage("确定接单？").
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
//                                TestFather.getTestData().getOrderAccepted().add(orderID);
//                                TestFather.getTestData().getNewOrderList().get(position).setSituation(4);
                                try {
                                    ArrayList list = OrderOperation.changeSituation(orderID, 4);
                                    if (Integer.parseInt((String) list.get(0)) == 200) {
                                        Toast.makeText(OrderDetailActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        JSONObject object = new JSONObject((String) list.get(1));
                                        String message = object.getString("data");
                                        Toast.makeText(OrderDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                                situationTv.setText("进行中");
                                acceptBtn.setVisibility(View.INVISIBLE);
                                rejectBtn.setVisibility(View.INVISIBLE);
                                finishBtn.setVisibility(View.VISIBLE);
                                Bundle bundle = new Bundle();
                                bundle.putInt("position", position);
                                bundle.putInt("situation", 4);
                                Intent intent = new Intent();
                                intent.putExtras(bundle);
                                // 设置返回结果为RESULT_OK, intent可以传入一些其他的参数, 在onActivityResult中的data中可以获取到
                                setResult(RESULT_OK, intent);
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

            }
        });

        /**
         * 拒绝按钮
         * 实现确定接单之后刷新当前Activity的状态成"已取消"，并传送状态更改给上一层Activity
         * 同时将接单按钮和拒绝按钮设置为不可见
         */
        rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LayoutInflater layoutInflater = LayoutInflater.from(OrderDetailActivity.this);
//                final View myLoginView = layoutInflater.inflate(R.layout.user_address, null);
                AlertDialog alertDialog = new AlertDialog.Builder(OrderDetailActivity.this).
                        setMessage("确定拒绝？").
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
//                                TestFather.getTestData().getOrderAccepted().add(orderID);
//                                TestFather.getTestData().getNewOrderList().get(position).setSituation(2);
//                                TestFather.getTestData().getOldOrderList().add(TestFather.getTestData().getNewOrderList().get(position));
//                                TestFather.getTestData().getNewOrderList().remove(position);
                                try {
                                    ArrayList list = OrderOperation.changeSituation(orderID, 2);
                                    if (Integer.parseInt((String) list.get(0)) == 200) {
                                        Toast.makeText(OrderDetailActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        JSONObject object = new JSONObject((String) list.get(1));
                                        String message = object.getString("data");
                                        Toast.makeText(OrderDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                                situationTv.setText("已取消");
                                acceptBtn.setVisibility(View.INVISIBLE);
                                rejectBtn.setVisibility(View.INVISIBLE);
                                addNurse.setVisibility(View.INVISIBLE);
                                Bundle bundle = new Bundle();
                                bundle.putInt("position", position);
                                bundle.putInt("situation", 2);
                                Intent intent = new Intent();
                                intent.putExtras(bundle);
                                // 设置返回结果为RESULT_OK, intent可以传入一些其他的参数, 在onActivityResult中的data中可以获取到
                                setResult(RESULT_OK, intent);
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

            }
        });

        /**
         * 提醒按钮
         * 实现确定提醒之后刷新当前Activity的状态成"已提醒付款"，并传送状态更改给上一层Activity
         * 同时将提醒按钮设置为"再次提醒"
         */
        notifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(OrderDetailActivity.this).
                        setMessage("提醒用户付款？").
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
//                                TestFather.getTestData().getOrderAccepted().add(orderID);
//                                TestFather.getTestData().getNewOrderList().get(position).setSituation(5);
                                try {
                                    ArrayList list = OrderOperation.changeSituation(orderID, 5);
                                    if (Integer.parseInt((String) list.get(0)) == 200) {
                                        Toast.makeText(OrderDetailActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        JSONObject object = new JSONObject((String) list.get(1));
                                        String message = object.getString("data");
                                        Toast.makeText(OrderDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }

                                situationTv.setText("已提醒付款");
                                notifyBtn.setText("再次提醒");
                                Bundle bundle = new Bundle();
                                bundle.putInt("position", position);
                                bundle.putInt("situation", 5);
                                Intent intent = new Intent();
                                intent.putExtras(bundle);
                                // 设置返回结果为RESULT_OK, intent可以传入一些其他的参数, 在onActivityResult中的data中可以获取到
                                setResult(RESULT_OK, intent);
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

            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LayoutInflater layoutInflater = LayoutInflater.from(OrderDetailActivity.this);
//                final View myLoginView = layoutInflater.inflate(R.layout.user_address, null);
                AlertDialog alertDialog = new AlertDialog.Builder(OrderDetailActivity.this).
                        setMessage("确认完成？").
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
//                                TestFather.getTestData().getOrderAccepted().add(orderID);
//                                TestFather.getTestData().getNewOrderList().get(position).setSituation(3);
//                                TestFather.getTestData().getOldOrderList().add(TestFather.getTestData().getNewOrderList().get(position));
//                                TestFather.getTestData().getNewOrderList().remove(position);
                                try {
                                    ArrayList list = OrderOperation.changeSituation(orderID, 3);
                                    if (Integer.parseInt((String) list.get(0)) == 200) {
                                        Toast.makeText(OrderDetailActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        JSONObject object = new JSONObject((String) list.get(1));
                                        String message = object.getString("data");
                                        Toast.makeText(OrderDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                                situationTv.setText("已完成");
                                finishBtn.setVisibility(View.INVISIBLE);
                                Bundle bundle = new Bundle();
                                bundle.putInt("position", position);
                                bundle.putInt("situation", 3);
                                Intent intent = new Intent();
                                intent.putExtras(bundle);
                                // 设置返回结果为RESULT_OK, intent可以传入一些其他的参数, 在onActivityResult中的data中可以获取到
                                setResult(RESULT_OK, intent);
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

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderDetailActivity.this, ChooseNurseActivity.class);
                startActivity(intent);
            }
        });


    }

}
