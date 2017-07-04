package com.whatever.nurseapp.nurseapp.entity;

import android.os.Parcelable;

public class Nurse {
    private String nurseName; //姓名
    private int nurseId; //id
    private int nurseSex; //性别
    private int nurseAge;  //年龄
    private int nurseWorkAge; //护龄
    private String nurseArea; //地区
    private int nurseEvaluate; //评分
    private int nursePrice; //个人价格
    private int[] nurseProtectArea; //护理范围
    private int nurseHeigt; //身高
    private int nurseWeight; //体重
    private String nurseBloodType; //血型
    private String nurseNation;  //民族
    private String nurseIdentity; //身份证号码
    private String nurseConstellation; //星座
    private String nurseAnimal; //生肖
    private String nurseDescription; //个人介绍
    private String nursePhone;//电话

    public Nurse(){

    }

    public Nurse(String nurseName, int nurseId, int nurseSex, int nurseAge, int nurseWorkAge, String nurseArea, int nurseEvaluate, int nursePrice, int[] nurseProtectArea, int nurseHeigt, int nurseWeight, String nurseBloodType, String nurseNation, String nurseIdentity, String nurseConstellation, String nurseAnimal, String nurseDescription, String nursePhone) {
        this.setNurseName(nurseName);
        this.setNurseId(nurseId);
        this.setNurseSex(nurseSex);
        this.setNurseAge(nurseAge);
        this.setNurseWorkAge(nurseWorkAge);
        this.setNurseArea(nurseArea);
        this.setNurseEvaluate(nurseEvaluate);
        this.setNursePrice(nursePrice);
        this.setNurseProtectArea(nurseProtectArea);
        this.setNurseHeigt(nurseHeigt);
        this.setNurseWeight(nurseWeight);
        this.setNurseBloodType(nurseBloodType);
        this.setNurseNation(nurseNation);
        this.setNurseIdentity(nurseIdentity);
        this.setNurseConstellation(nurseConstellation);
        this.setNurseAnimal(nurseAnimal);
        this.setNurseDescription(nurseDescription);
        this.setNursePhone(nursePhone);
    }


    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getNurseSex() {
        return nurseSex;
    }

    public void setNurseSex(int nurseSex) {
        this.nurseSex = nurseSex;
    }

    public int getNurseAge() {
        return nurseAge;
    }

    public void setNurseAge(int nurseAge) {
        this.nurseAge = nurseAge;
    }

    public int getNurseWorkAge() {
        return nurseWorkAge;
    }

    public void setNurseWorkAge(int nurseWorkAge) {
        this.nurseWorkAge = nurseWorkAge;
    }

    public String getNurseArea() {
        return nurseArea;
    }

    public void setNurseArea(String nurseArea) {
        this.nurseArea = nurseArea;
    }

    public int getNurseEvaluate() {
        return nurseEvaluate;
    }

    public void setNurseEvaluate(int nurseEvaluate) {
        this.nurseEvaluate = nurseEvaluate;
    }

    public int getNursePrice() {
        return nursePrice;
    }

    public void setNursePrice(int nursePrice) {
        this.nursePrice = nursePrice;
    }

    public int[] getNurseProtectArea() {
        return nurseProtectArea;
    }

    public void setNurseProtectArea(int[] nurseProtectArea) {
        this.nurseProtectArea = nurseProtectArea;
    }

    public int getNurseHeigt() {
        return nurseHeigt;
    }

    public void setNurseHeigt(int nurseHeigt) {
        this.nurseHeigt = nurseHeigt;
    }

    public int getNurseWeight() {
        return nurseWeight;
    }

    public void setNurseWeight(int nurseWeight) {
        this.nurseWeight = nurseWeight;
    }

    public String getNurseBloodType() {
        return nurseBloodType;
    }

    public void setNurseBloodType(String nurseBloodType) {
        this.nurseBloodType = nurseBloodType;
    }

    public String getNurseNation() {
        return nurseNation;
    }

    public void setNurseNation(String nurseNation) {
        this.nurseNation = nurseNation;
    }

    public String getNurseIdentity() {
        return nurseIdentity;
    }

    public void setNurseIdentity(String nurseIdentity) {
        this.nurseIdentity = nurseIdentity;
    }

    public String getNurseConstellation() {
        return nurseConstellation;
    }

    public void setNurseConstellation(String nurseConstellation) {
        this.nurseConstellation = nurseConstellation;
    }

    public String getNurseAnimal() {
        return nurseAnimal;
    }

    public void setNurseAnimal(String nurseAnimal) {
        this.nurseAnimal = nurseAnimal;
    }

    public String getNurseDescription() {
        return nurseDescription;
    }

    public void setNurseDescription(String nurseDescription) {
        this.nurseDescription = nurseDescription;
    }

    public String getNursePhone() {
        return nursePhone;
    }

    public void setNursePhone(String nursePhone) {
        this.nursePhone = nursePhone;
    }
}