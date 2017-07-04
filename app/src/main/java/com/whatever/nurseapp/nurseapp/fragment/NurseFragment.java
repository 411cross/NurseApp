package com.whatever.nurseapp.nurseapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.activity.AddNurseActivity;
import com.whatever.nurseapp.nurseapp.activity.NewOrderActivity;
import com.whatever.nurseapp.nurseapp.activity.NurseManageActivity;
import com.whatever.nurseapp.nurseapp.activity.OldOrderActivity;
import com.whatever.nurseapp.nurseapp.entity.Nurse;


/**
 * Created by derrickJ on 2017/5/28.
 */

public class NurseFragment extends android.support.v4.app.Fragment{

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static NurseFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        NurseFragment classFragment = new NurseFragment();
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
        View view = inflater.inflate(R.layout.layout_nurse_fragment, container, false);
        ImageView nurseListBtn = (ImageView) view.findViewById(R.id.nurse_list_btn);
        ImageView addNurseBtn = (ImageView) view.findViewById(R.id.add_nurse_btn);

        nurseListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NurseManageActivity.class);
                startActivity(intent);
            }
        });

        addNurseBtn
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddNurseActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}


