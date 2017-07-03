package com.whatever.nurseapp.nurseapp.Operation;

import com.whatever.nurseapp.nurseapp.Okhttp_tools.okHttpTools;
import com.whatever.nurseapp.nurseapp.entity.Nurse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by derrickJ on 2017/7/3.
 */

public class NurseOperation {

    /**
     * 获取护工列表
     * 输入 无
     * 输出 状态码和返回信息
     */
    public static ArrayList getNurseList() throws JSONException, ExecutionException, InterruptedException {
        ArrayList<Nurse> list = new ArrayList<Nurse>();
        okHttpTools okhttpT = new okHttpTools();
        String URL = "http://139.199.226.190:8080/api/v1/register";
        okhttpT.postTools(URL, "");
        String data = (String) okhttpT.getResponse().get(1);
        JSONObject object = new JSONObject(data);
        JSONArray jsonArray = object.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject nursesData = (JSONObject) jsonArray.get(i);
            String nurseName = nursesData.getString("nurseName");
            int nurseSex = nursesData.getInt("nurseSex");
            int nurseAge = nursesData.getInt("nurseAge");
            int nurseWorkAge = nursesData.getInt("nurseWorkAge");
            String nurseArea = nursesData.getString("nurseArea");
            int nurseEvaluate = nursesData.getInt("nurseWorkAge");
            int nursePrice = nursesData.getInt("nurseWorkAge");
            ArrayList<Integer> nurseProtectArea = new ArrayList<Integer>();
            JSONArray nurseProtectAreaList = nursesData.getJSONArray("nurseProtectArea");
            for (int j = 0; j < nurseProtectAreaList.length(); j++) {
                nurseProtectArea.add(nurseProtectAreaList.getInt(i));
            }
            int nurseHeight = nursesData.getInt("nurseHeight");
            int nurseWeight = nursesData.getInt("nurseWeight");
            String nurseBloodType = nursesData.getString("nurseBloodType");
            String nurseNation = nursesData.getString("nurseNation");
            String nurseIdentity = nursesData.getString("nurseIdentity");
            String nurseConstellation = nursesData.getString("nurseConstellation");
            String nurseAnimal = nursesData.getString("nurseAnimal");
            String nurseDescription = nursesData.getString("nurseDescription");
            String nursePhone = nursesData.getString("nursePhone");
            Nurse nurse1 = new Nurse(nurseName, nurseSex, nurseAge, nurseWorkAge, nurseArea, nurseEvaluate, nursePrice, nurseProtectArea, nurseHeight, nurseWeight, nurseBloodType, nurseNation, nurseIdentity, nurseConstellation, nurseAnimal, nurseDescription, nursePhone);
            list.add(nurse1);

        }
        AdminOperation.nurseListAll = list;

        return okhttpT.getResponse();
    }

    /**
     * 获取特定护工
     * 输入 无
     * 输出 状态码和返回信息
     */
    public static ArrayList getSpecificNurse(String name) throws JSONException, ExecutionException, InterruptedException {
        ArrayList<Nurse> list = new ArrayList<Nurse>();
        okHttpTools okhttpT = new okHttpTools();
        String URL = "http://139.199.226.190:8080/api/v1/register";
        okhttpT.postTools(URL, "");
        String data = (String) okhttpT.getResponse().get(1);
        JSONObject object = new JSONObject(data);
        JSONArray jsonArray = object.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject nursesData = (JSONObject) jsonArray.get(i);
            String nurseName = nursesData.getString("nurseName");
            int nurseSex = nursesData.getInt("nurseSex");
            int nurseAge = nursesData.getInt("nurseAge");
            int nurseWorkAge = nursesData.getInt("nurseWorkAge");
            String nurseArea = nursesData.getString("nurseArea");
            int nurseEvaluate = nursesData.getInt("nurseWorkAge");
            int nursePrice = nursesData.getInt("nurseWorkAge");
            ArrayList<Integer> nurseProtectArea = new ArrayList<Integer>();
            JSONArray nurseProtectAreaList = nursesData.getJSONArray("nurseProtectArea");
            for (int j = 0; j < nurseProtectAreaList.length(); j++) {
                nurseProtectArea.add(nurseProtectAreaList.getInt(i));
            }
            int nurseHeight = nursesData.getInt("nurseHeight");
            int nurseWeight = nursesData.getInt("nurseWeight");
            String nurseBloodType = nursesData.getString("nurseBloodType");
            String nurseNation = nursesData.getString("nurseNation");
            String nurseIdentity = nursesData.getString("nurseIdentity");
            String nurseConstellation = nursesData.getString("nurseConstellation");
            String nurseAnimal = nursesData.getString("nurseAnimal");
            String nurseDescription = nursesData.getString("nurseDescription");
            String nursePhone = nursesData.getString("nursePhone");
            Nurse nurse1 = new Nurse(nurseName, nurseSex, nurseAge, nurseWorkAge, nurseArea, nurseEvaluate, nursePrice, nurseProtectArea, nurseHeight, nurseWeight, nurseBloodType, nurseNation, nurseIdentity, nurseConstellation, nurseAnimal, nurseDescription, nursePhone);
            list.add(nurse1);

        }
        AdminOperation.nurseListAll = list;

        return okhttpT.getResponse();
    }

}
