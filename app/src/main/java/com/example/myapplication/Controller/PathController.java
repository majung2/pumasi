package com.example.myapplication.Controller;

import com.example.myapplication.Entity.Path;

import java.util.ArrayList;

public class PathController {

    public static void savePath(ArrayList<String> path){
        Path p = new Path();
        p.setPath(path);

        p.save();
    }
}
