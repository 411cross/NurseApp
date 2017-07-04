package com.whatever.nurseapp.nurseapp;

import com.whatever.nurseapp.nurseapp.entity.Nurse;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by chf on 2017/7/1.
 */

public class TestData_nurse {
    private ArrayList<Nurse>  a;
    int[] s1 = {1};
    int[] s2 = {1,2};
    Nurse n1 = new Nurse("牛大",123456,0,20,10, "广东", 90, 150, s1, 150, 100, "A", "汉族", "123identify", "双鱼座", "鼠", "fit", "7569");
    Nurse n2 = new Nurse("牛欢喜",98761,1,25,5, "湖南", 80, 250, s2, 160, 120, "AB", "回族", "456identify", "处女座", "牛", "fitshai", "10010");
    Nurse n3 = new Nurse("熊大",12345,1,30,2, "胡建", 80, 250, s2, 160, 120, "AB", "回族", "456identify", "处女座", "牛", "fitshai", "10010");
    Nurse n4 = new Nurse("熊二",12346,0,28,3, "四川", 80, 250, s2, 160, 120, "A", "回族", "456identify", "处女座", "牛", "fitshai", "10010");
    public ArrayList<Nurse> getA() {
        return a;
    }

    public void setA(ArrayList<Nurse> a) {
        this.a = a;
    }

    public TestData_nurse(){
        a = new ArrayList<>();
        a.add(n1);
        a.add(n2);

    }
    public ArrayList<Nurse> delete_Nurse(int n){
        for(int i = 0;i<a.size();i++){
            if(n == a.get(i).getNurseId()){
                a.remove(i);
                break;
            }
        }
        return a;
    }
}
