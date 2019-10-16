package com.example.myapplication.Entity;

import android.content.*;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.example.myapplication.Entity.Brand;
import com.example.myapplication.Controller;
public class Path {
    private ArrayList<String> pathArr;

    public void setPath(ArrayList<String> pathArr){
        this.pathArr = pathArr;
    }
    public ArrayList<String> getPathArr(){
        return pathArr;
    }
    /*public void save(String text, String saveFile) {
        File file = new File(Context.getFilesDir(), saveFile);
        try{
            FileOutputStream fileOut = openFileOutput(saveFile, Context.MODE_PRIVATE);
            PrintWriter out = new PrintWriter(fileOut);
            out.print(text);
            fileOut.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }*/
}
