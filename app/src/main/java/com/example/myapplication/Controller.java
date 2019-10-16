package com.example.myapplication;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Entity.Brand;
import com.example.myapplication.Entity.Path;

import java.util.ArrayList;

public class Controller {
    Brand spor_b1 = new Brand("나이키", "spor", 0);
    Brand spor_b2 = new Brand("아디다스", "spor", 1);
    Brand spor_b3 = new Brand("언더아머", "spor", 2);
    Brand spor_b4 = new Brand("퓨마", "spor", 3);
    Brand[] spor = {spor_b1, spor_b2, spor_b3, spor_b4 };
    Brand clot_b1 = new Brand("TOPTEN", "clot", 0);
    Brand clot_b2 = new Brand("H&M", "clot", 1);
    Brand clot_b3 = new Brand("자라", "clot", 2);
    Brand clot_b4 = new Brand("폴로", "clot", 3);
    Brand[] clot = {clot_b1, clot_b2, clot_b3, clot_b4};
    Brand cosme_b1 = new Brand("에뛰드", "cosme", 0);
    Brand cosme_b2 = new Brand("네이쳐 리퍼블릭", "cosme", 1);
    Brand cosme_b3 = new Brand("이니스프리", "cosme", 2);
    Brand cosme_b4 = new Brand("더 페이스샵", "cosme", 3);
    Brand[] cosme = {cosme_b1, cosme_b2, cosme_b3, cosme_b4};
    Path p = new Path();

    public Path getPath(){

        return p;
    }
    public ArrayList<String> sporControl(){
        ArrayList<String> sporName = new ArrayList<>();
        for(int i = 0; i<spor.length; i++){
            sporName.add(spor[i].getBrName());
        }
        return sporName;
    }
    public ArrayList<String> clotControl(){
        ArrayList<String> clotName = new ArrayList<>();
        for(int i = 0; i<clot.length; i++){
            clotName.add(clot[i].getBrName());
        }
        return clotName;
    }
    public ArrayList<String> cosmeControl(){
        ArrayList<String> cosmeName = new ArrayList<>();
        for(int i = 0; i<cosme.length; i++){
            cosmeName.add(cosme[i].getBrName());
        }
        return cosmeName;
    }

}
