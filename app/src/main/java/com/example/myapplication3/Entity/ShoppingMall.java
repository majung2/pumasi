package com.example.myapplication3.Entity;

import java.util.ArrayList;

public class ShoppingMall {
    private String MName;// 쇼핑몰 이름
    private String MNr;//쇼핑몰 번호
    private ArrayList<Category> categoryLIst;//쇼핑몰에서 제공하는 카테고리의 리스트

    public ShoppingMall(){
        MName=null;
        MNr=null;
        categoryLIst= new ArrayList<Category>();
    }

    public void setMName(String MName){
        this.MName=MName;
    }
    public void setMNumber(String MNumber){
        this.MNr=MNumber;
    }
    public void setCategoryLIst(Category category){
        categoryLIst.add(category);
    }

    public String getMNumber(){
        return this.MNr;
    }
    public String getMName(){
        return this.MName;
    }
    public ArrayList<Category> getCategoryList(){
        return this.categoryLIst;
    }

}
