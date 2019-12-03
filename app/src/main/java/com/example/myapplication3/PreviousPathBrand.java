package com.example.myapplication3;

import java.util.ArrayList;

public class PreviousPathBrand {

    private String bought_date;
    private String brand_name;
    private String bought_Category;
    private Integer grade;
    private Boolean visited;
    private Boolean bought;


    //initialize
    public PreviousPathBrand(){}

    public PreviousPathBrand(String brand_name)
    {
        this.brand_name=brand_name;
    }

    public PreviousPathBrand(String brand_name, String bought_date, String bought_Category)
    {
        this.brand_name = brand_name;
        this.bought_date = bought_date;
        this.bought_Category = bought_Category;
    }

    //get method
    public String getBrand_name() {
        return brand_name;
    }
    public String getBought_date() {
        return bought_date;
    }
    public String getBought_Category() {
        return bought_Category;
    }
    public Boolean getBought() {
        return bought;
    }
    public Integer getGrade() {
        return grade;
    }
    public Boolean getVisited() {
        return visited;
    }

    //set method
    public void setBought(Boolean bought) {
        this.bought = bought;
    }

    public void setBought_Category(String bought_Category) {
        this.bought_Category = bought_Category;
    }

    public void setBought_date(String bought_date) {
        this.bought_date = bought_date;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

}//end class
