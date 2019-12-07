package com.example.myapplication3.Recommend;

import com.example.myapplication3.Entity.Brand;

import java.util.ArrayList;
import java.util.Map;

public class RecommendBrandController {
    private ArrayList<ArrayList<Brand>> brList;
    public ArrayList<ArrayList<Brand>> getBrList(){
        return this.brList;
    }
    public void getAllbrand(){
        Brand b = new Brand();
        brList = new ArrayList<>();
        brList=b.getAllBrand();
    }
//
  //  public Map<Integer,  >
}
