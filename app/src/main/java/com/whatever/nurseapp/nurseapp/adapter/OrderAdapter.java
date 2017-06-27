package com.whatever.nurseapp.nurseapp.adapter;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
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

    public OrderAdapter(Context context, int resourceID, List<Order> objects) {
        super(context, resourceID, objects);
        resource = resourceID;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        Order order = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        TextView order_idTv = (TextView) view.findViewById(R.id.order_id);
        TextView priceTv = (TextView) view.findViewById(R.id.price);
        TextView timeTv = (TextView) view.findViewById(R.id.time);
        TextView situationTv = (TextView) view.findViewById(R.id.situation);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);

        order_idTv.setText(order.getOrderID());
        priceTv.setText(order.getPrice());
        timeTv.setText(order.getTime());

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
        }

        return view;

    }

}
