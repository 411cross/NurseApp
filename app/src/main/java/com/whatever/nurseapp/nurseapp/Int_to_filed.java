package com.whatever.nurseapp.nurseapp;

/**
 * Created by chf on 2017/7/3.
 */

public class Int_to_filed {
    public static String to_filed(int i){
        String[] filed ={"头晕","老年痴呆","术后护理","植物人护理"};
        return filed[i];
    }
    public static int filed_length(){
        return 4;
    }
}
