package com.example.myapplication.Controller;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Entity.Brand;
import com.example.myapplication.Entity.Path;

import java.util.ArrayList;

public class BrandController {
    ArrayList<String> catespor = new ArrayList<String>();
    ArrayList<String> cateclot = new ArrayList<String>();
    ArrayList<String> catecosme = new ArrayList<String>();
    Brand spor_b1 = new Brand("나이키", catespor, 0);
    Brand spor_b2 = new Brand("아디다스", catespor, 1);
    Brand spor_b3 = new Brand("언더아머", catespor, 2);
    Brand spor_b4 = new Brand("퓨마", catespor, 3);
    Brand[] spor = {spor_b1, spor_b2, spor_b3, spor_b4 };
    Brand clot_b1 = new Brand("TOPTEN", cateclot, 0);
    Brand clot_b2 = new Brand("H&M", cateclot, 1);
    Brand clot_b3 = new Brand("자라", cateclot, 2);
    Brand clot_b4 = new Brand("폴로", cateclot, 3);
    Brand[] clot = {clot_b1, clot_b2, clot_b3, clot_b4};
    Brand cosme_b1 = new Brand("에뛰드", catecosme, 0);
    Brand cosme_b2 = new Brand("네이쳐 리퍼블릭", catecosme, 1);
    Brand cosme_b3 = new Brand("이니스프리", catecosme, 2);
    Brand cosme_b4 = new Brand("더 페이스샵", catecosme, 3);
    Brand[] cosme = {cosme_b1, cosme_b2, cosme_b3, cosme_b4};

    public ArrayList<String> getSporBrNames(){
        ArrayList<String> sporName = new ArrayList<>();
        for(int i = 0; i<spor.length; i++){
            sporName.add(spor[i].getBrName());
        }
        return sporName;
    }
    public ArrayList<String> getClotBrNames(){
        ArrayList<String> clotName = new ArrayList<>();
        for(int i = 0; i<clot.length; i++){
            clotName.add(clot[i].getBrName());
        }
        return clotName;
    }
    public ArrayList<String> getCosmeBrNames(){
        ArrayList<String> cosmeName = new ArrayList<>();
        for(int i = 0; i<cosme.length; i++){
            cosmeName.add(cosme[i].getBrName());
        }
        return cosmeName;
    }

}
