package com.whatever.nurseapp.nurseapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whatever.nurseapp.nurseapp.R;
import com.whatever.nurseapp.nurseapp.entity.Order;

import java.util.List;

/**
 * Created by derrickJ on 2017/6/27.
 */

public class OrderAdapter extends ArrayAdapter<Order>{

    private int resource;
    private int situation[];

    public OrderAdapter(Context context, int resourceID, List<Order> objects) {
        super(context, resourceID, objects);
        situation = new int[objects.size()];
        resource = resourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Order order = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        TextView orderIDTv = (TextView) view.findViewById(R.id.order_id);
        TextView priceTv = (TextView) view.findViewById(R.id.price);
        TextView timeTv = (TextView) view.findViewById(R.id.time);
        TextView situationTv = (TextView) view.findViewById(R.id.situation);
        TextView choseNurseTv = (TextView) view.findViewById(R.id.chose_nurse);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);

        orderIDTv.setText(order.getId());
        priceTv.setText(order.getTotalPrice());
        timeTv.setText(order.getCreateTime());

        Log.i("ttttttttttttt", "getView: ");
        //判断护理类型，选择相应的图标展示
        int type = order.getType();
        int image_id;
        switch (type) {
            case 1:
                image_id = R.mipmap.neike;
                break;
            case 2:
                image_id = R.mipmap.waike;
                break;
            case 3:
                image_id = R.mipmap.chanke;
                break;
            default:
                image_id = R.mipmap.ic_launcher_round;
                break;
        }
        icon.setImageResource(image_id);

        //判断订单情况，展示不同的TextView
        int situation = order.getSituation();
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

        //判断选择护工情况，展示不同的TextView
        if (order.getChoseNurse() == 1) {
            choseNurseTv.setText("已选择护工");
        } else {
            choseNurseTv.setText("未选择护工");
        }

        return view;

    }

    public class ViewHolder {
        public TextView orderIDTv;
        public TextView priceTv;
        public TextView timeTv;
        public TextView situationTv;
        public ImageView icon;
    }

}
