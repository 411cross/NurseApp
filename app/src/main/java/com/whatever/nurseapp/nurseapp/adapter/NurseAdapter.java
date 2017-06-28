package com.whatever.nurseapp.nurseapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        TextView name = (TextView)view.findViewById(R.id.editText2);
        TextView price = (TextView)view.findViewById(R.id.textView7);
        TextView Evaluate = (TextView)view.findViewById(R.id.textView9);
        name.setText(nu.getNurseName());
        price.setText(nu.getNursePrice()+"");
        Evaluate.setText(nu.getNurseEvaluate()+"");
        return view;

    }

}
