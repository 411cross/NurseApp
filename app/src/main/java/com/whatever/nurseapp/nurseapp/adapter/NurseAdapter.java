package com.whatever.nurseapp.nurseapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.whatever.nurseapp.nurseapp.Int_to_filed;
import com.whatever.nurseapp.nurseapp.entity.Nurse;
import com.whatever.nurseapp.nurseapp.R;

import java.util.ArrayList;
import java.util.List;

public class NurseAdapter extends ArrayAdapter<Nurse>{
    private int resource;
    public NurseAdapter(Context context, int resourceID, List<Nurse> objects) {
        super(context, resourceID, objects);
        resource = resourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Nurse nu = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        ImageView NurseIcon = (ImageView)view.findViewById(R.id.NurseIcon);//头像
        TextView name = (TextView)view.findViewById(R.id.NurseName);//姓名
        TextView sex = (TextView)view.findViewById(R.id.NurseSex);//性别
        TextView age = (TextView)view.findViewById(R.id.NurseAge);//年龄
        TextView area = (TextView)view.findViewById(R.id.NurseArea);//地区
        TextView price = (TextView)view.findViewById(R.id.NursePrice);//价格
        TextView Evaluate = (TextView)view.findViewById(R.id.NurseEvaluateD);//评价
        TextView filed = (TextView)view.findViewById(R.id.filed);//护理范围
        TextView phone = (TextView)view.findViewById(R.id.textView_tel);//电话
        TextView nation = (TextView)view.findViewById(R.id.nation);//民族
        ArrayList<Integer> filed_List = nu.getNurseProtectArea();
        String[] filed_String = new String[filed_List.size()];
        for(int i= 0;i<filed_List.size();i++){
            filed_String[i] = Int_to_filed.to_filed(filed_List.get(i));
        }

        String set_filed = "护理范围:";
        for(int i = 0;i<filed_List.size();i++){
            set_filed = set_filed+filed_String[i];
            if(i%2==0&&i!=filed_List.size()-1){
                set_filed += " ";
            }
        }
        name.setText(nu.getNurseName());
        if(nu.getNurseSex()==0)
            sex.setText("性别:男");
        else
            sex.setText("性别:女");
        age.setText("年龄:" + nu.getNurseAge());
        area.setText("籍贯:" + nu.getNurseArea());
        phone.setText("电话:"+nu.getNursePhone());
        price.setText("收费:" + nu.getNursePrice()+"/小时");
        Evaluate.setText("好评率:"+nu.getNurseEvaluate());
        nation.setText("民族:"+ nu.getNurseNation());
        filed.setText(set_filed);
        return view;

    }

}
