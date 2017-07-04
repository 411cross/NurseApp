package com.whatever.nurseapp.nurseapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.TestFather;
import com.whatever.nurseapp.nurseapp.adapter.OrderAdapter;
import com.whatever.nurseapp.nurseapp.entity.Order;
import com.whatever.nurseapp.nurseapp.util.LoadListView;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MoreDetailActivity extends AppCompatActivity implements LoadListView.ILoadMoreDateListener {

    private LoadListView listView;
//    private TextView textView;
    private OrderAdapter adapter;
    private ArrayList<Order> date_list;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDate();
        showListViewData();
    }

    /**
     * 初始化模拟数据
     */
    private void initDate(){
//        date_list=new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
//            date_list.add("listItem "+i);
            date_list.add(TestFather.getTestData().getNewOrderList().get(i));
            count++;
        }
    }
    /**
     * 加载模拟数据
     */
    private void getDate(){
        for (int i = 0; i < 2; i++) {
//            date_list.add("listItem again "+i);
            date_list.add(TestFather.getTestData().getNewOrderList().get(i + count));
            count++;

        }
    }

    private void showListViewData(){
//        if (adapter==null) {
            listView=(LoadListView) findViewById(R.id.lv);
            listView.setOnILoadMoreDateListener(this);
            adapter = new OrderAdapter(MoreDetailActivity.this, R.layout.layout_order_item, date_list);
            listView.setAdapter(adapter);
//        }else {
//            adapter.onDateChange(date_list);
//        }
    }

    @Override
    public void onLoad() {
        //handler在实际项目中是不需要的，这里是为了演示请求数据的过程
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                getDate();
                showListViewData();
                listView.loadComplete();
            }
        }, 2000);

    }

//    private  LoadListView listView;
//    private OrderAdapter adapter;
//    private ArrayList<Order> arrayList = new ArrayList<>();
//    int count = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        addDate();
//        System.out.println(arrayList.get(1));
//        View view = this.getLayoutInflater().inflate(R.layout.activity_more_detail,null);
//        listView = (LoadListView) view.findViewById(R.id.lv);
//        listView.setInterface(this);    //传入接口
//        adapter = new OrderAdapter(MoreDetailActivity.this,android.R.layout.simple_list_item_1,arrayList);
//        listView.setAdapter(adapter);
//
//    }
//
//    private void addDate(){
//        for (int i = 0; i <3 ; i++){
////            arrayList.add(i+"");
//            arrayList.add(TestFather.getTestData().getNewOrderList().get(i));
//            count++;
//        }
//
//    }
//
//    private void loadMoreDate(){
//        for (int i = 0; i <2 ; i++){
////            arrayList.add( i*100+"");
//            arrayList.add(TestFather.getTestData().getNewOrderList().get(i + count));
//            count++;
//        }
//
//    }
//
//
//    @Override
//    public void load() {
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //获取更多数据
//                loadMoreDate();
//                //更新界面
//                adapter.notifyDataSetChanged();
//                //加载完毕隐藏loading布局
//                listView.loadingComplete();
//            }
//        },5000);
//    }
}


