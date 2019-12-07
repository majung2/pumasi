package com.example.myapplication3.Entity;

import java.util.ArrayList;

public class SpotsInMall {
    private ArrayList<Integer> SpotLocation;//해당 스팟의 x,y 좌표의 리스트
    private String SpotNr;//스팟의 번호
    private String SpotName;//스팟의 이름
    private Integer SpotFloor;//스팟이 위치한 층수
    private String SpotType;//스팟의 타입(브랜드/음식/에스컬레이터및엘리베이터)
    private Category InCategory;//어떤 카테고리에 속하는지


    public SpotsInMall(){}

    public void setSpotLocation(Integer x, Integer y){
        this.SpotLocation= new ArrayList<>();
        this.SpotLocation.add(x);
        this.SpotLocation.add(y);
    }
    public void setSpotNr(String nr){
        this.SpotNr=nr;
    }
    public void setSpotName(String name){
        this.SpotName=name;
    }
    public void setSpotFloor(Integer floor){
        this.SpotFloor=floor;
    }
    public void setSpotType(String tp){
        this.SpotType=tp;
    }
    public void setInCategory(Category ct){
        this.InCategory= ct;
    }

    public String getSpotName(){
        return this.SpotName;
    }
    public Integer getSpotFloor() { return this.SpotFloor; }
    public ArrayList<Integer> getSpotLocation() { return this.SpotLocation; }



}












