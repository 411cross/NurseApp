package com.whatever.nurseapp.nurseapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.whatever.nurseapp.nurseapp.Operation.AdminOperation;
import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.adapter.NameListAdapter;
import com.whatever.nurseapp.nurseapp.widget.ClearEditText;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private ClearEditText et_search;
    private ListView name_list;

    private String[] data;
    private ArrayAdapter<String> mAdapter;

    private ArrayList<String> mDatas = AdminOperation.nurseNameList;
    private NameListAdapter mUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        initView();
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

        mUserAdapter = new NameListAdapter(SearchResultActivity.this,mDatas);
        name_list.setAdapter(mUserAdapter);

    }
}
