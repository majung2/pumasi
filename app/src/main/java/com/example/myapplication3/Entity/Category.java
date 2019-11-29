package com.example.myapplication3.Entity;

public class Category {
    private String CName;//카테고리 이름
    private String CNr;//카테고리 번호
    private ShoppingMall inMall;//카테고리가 속하는 쇼핑몰
    public void Category(){};

    public void setCName(String CName){
        this.CName=CName;
    }
    public void setCNr(String CNr){
        this.CNr=CNr;
    }
    public void setInMall(ShoppingMall inMall){this.inMall= inMall;}

    public String getCName(){
        return this.CName;
    }
    public String getCNr(){
        return this.CNr;
    }
    public ShoppingMall getInMall(){return this.inMall;}
}
