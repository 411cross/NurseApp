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

import com.whatever.nurseapp.nurseapp.entity.Nurse;
import com.whatever.nurseapp.nurseapp.R;

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
        TextView name = (TextView)view.findViewById(R.id.NurseNameD);//姓名
        TextView sex = (TextView)view.findViewById(R.id.NurseSex);//性别
        TextView age = (TextView)view.findViewById(R.id.NurseAge);//年龄
        TextView area = (TextView)view.findViewById(R.id.NurseArea);//地区
        TextView price = (TextView)view.findViewById(R.id.NursePrice);//价格
        TextView Evaluate = (TextView)view.findViewById(R.id.NurseEvaluateD);//评价
        TextView filed = (TextView)view.findViewById(R.id.filed);//护理范围
        String[] filed_List = nu.getNurseProtectArea();
        String set_filed = "";
        for(int i = 0;i<filed_List.length;i++){
            set_filed = set_filed+filed_List[i];
            if(i%2==0&&i!=filed_List.length-1){
                set_filed += "、";
            }
        }
        name.setText(nu.getNurseName());
        sex.setText(nu.getNurseSex());
        age.setText(nu.getNurseAge()+"");
        area.setText(nu.getNurseArea());
        price.setText(nu.getNursePrice()+"");
        Evaluate.setText(nu.getNurseEvaluate()+"");
        filed.setText(set_filed);
        return view;

    }

}
