package com.example.myapplication3.Entity;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import io.opencensus.tags.Tag;

import static androidx.constraintlayout.widget.Constraints.TAG;

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












