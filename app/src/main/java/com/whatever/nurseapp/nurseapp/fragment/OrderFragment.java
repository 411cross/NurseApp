package com.whatever.nurseapp.nurseapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.activity.NewOrderActivity;
import com.whatever.nurseapp.nurseapp.activity.OldOrderActivity;

/**
 * Created by derrickJ on 2017/5/28.
 */

public class OrderFragment extends android.support.v4.app.Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static OrderFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        OrderFragment classFragment = new OrderFragment();
        classFragment.setArguments(args);
        return classFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_order_fragment, container, false);
        ImageView newOrderBtn = (ImageView) view.findViewById(R.id.new_order_btn);
        ImageView oldOrderBtn = (ImageView) view.findViewById(R.id.old_order_btn);

        newOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewOrderActivity.class);
                startActivity(intent);
            }
        });

        oldOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OldOrderActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
