package com.example.myapplication.Entity;

public class Brand {
    private String BrName;
    private String Category;
    private int Id;
    public Brand(String BrName, String Category, int Id){
        this.BrName = BrName;
        this.Category = Category;
        this.Id = Id;
    }

    public String getBrName(){
        return BrName;
    }
    public String getCategory(){
        return Category;
    }
    public int getId(){
        return Id;
    }

}
