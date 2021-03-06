package com.whatever.nurseapp.nurseapp.Operation;

import com.whatever.nurseapp.nurseapp.Okhttp_tools.okHttpTools;
import com.whatever.nurseapp.nurseapp.entity.Nurse;
import com.whatever.nurseapp.nurseapp.entity.Order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by derrickJ on 2017/7/3.
 */

public class AdminOperation {

    public static ArrayList<String> nurseNameList = new ArrayList<>();
    public static ArrayList<Nurse> nurseListAll = new ArrayList<>();
    public static ArrayList<Nurse> areaNurseList = new ArrayList<>();
    public static ArrayList<Order> orderList = new ArrayList<>();
    public static ArrayList<Nurse> tempNurseList = new ArrayList<>();

    /**
     * 用户登录
     * 传入 id 和 pass
     * 输出 状态码和返回信息
     */

    public static ArrayList adminLogin(int id, String pass) throws JSONException, ExecutionException, InterruptedException {

        okHttpTools okht = new okHttpTools();
        String URL = "http://139.199.226.190:8888/NurseApp/adminlogin";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("password", pass);
        String json = jsonObject.toString();
        okht.postTools(URL, json);


        ArrayList responseList = okht.getResponse();
        if (Integer.parseInt((String) okht.getResponse().get(0)) == 200) {
            String data = (String) responseList.get(1);
            JSONObject object = new JSONObject(data);
            JSONArray jsonArray = object.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                String name = (String) jsonArray.get(i);
                System.out.println(name);
                nurseNameList.add(name);
            }
        }

        return responseList;
    }

}
