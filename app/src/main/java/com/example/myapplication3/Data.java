package com.example.myapplication3;

import java.util.ArrayList;

//!! test data !! for Previous Path specific
public class Data {

    private String brand_name;
    private String bought_date;
    private String bought_Category;

    public Data(){}

    public String getBrand_name() {
        return brand_name;
    }
    public String getBought_date() {
        return bought_date;
    }
    public String getBought_Category() {
        return bought_Category;
    }
    public Data(String brand_name)
    {
        this.brand_name=brand_name;
    }

    public Data(String brand_name, String bought_date, String bought_Category)
    {
        this.brand_name = brand_name;
        this.bought_date = bought_date;
        this.bought_Category = bought_Category;

    }

}
