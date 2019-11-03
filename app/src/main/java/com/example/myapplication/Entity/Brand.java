package com.example.myapplication.Entity;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.Databases;

import java.util.ArrayList;

public class Brand {
    private String BrName;
    private ArrayList<String> Category;
    private int Id;


    public Brand(String BrName, ArrayList<String> Category, int Id){
        this.BrName = BrName;
        this.Category = Category;
        this.Id = Id;
    }

    public String getBrName(){
        return BrName;
    }
    public void setBrName(String BrName){ this.BrName = BrName;}
    public ArrayList<String> getCategory(){
        return Category;
    }
    public void setCategory(ArrayList<String> Category){ this.Category = Category;}
    public int getId(){
        return Id;
    }
    public void setId(int Id){ this.Id = Id;}

}
