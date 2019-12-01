package com.example.myapplication3.Entity;

import androidx.annotation.NonNull;

import com.example.myapplication3.MyPage.MyPageController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Category {
    private String CName;//카테고리 이름
    private String CNr;//카테고리 번호
    private ShoppingMall inMall;//카테고리가 속하는 쇼핑몰
    private FirebaseFirestore db;
   private catCallback catcallback;
    private ArrayList<String> catBrand;

    public Category() {
    }


    public void setCategory(catCallback catCallback){
        this.CNr=null;
        this.CName=null;
        this.inMall=null;
        this.catBrand = new ArrayList<>();
        this.catcallback = catCallback;
    };
    public void setCName(String CName){
        this.CName=CName;
    }
    public void setCNr(String CNr){
        this.CNr=CNr;
    }
    public void setInMall(ShoppingMall inMall){this.inMall= inMall;}

    public String getCName(){
        return this.CName;
    }
    public String getCNr(){
        return this.CNr;
    }
    public ShoppingMall getInMall(){return this.inMall;}

    public void findBrands() {//해당 카테고리의 브랜드 검색
System.out.println(CNr);
        db = FirebaseFirestore.getInstance();
        CollectionReference ref = db.collection("shoppingMall").document("M1").collection("category").document(CNr).collection("brand");
        ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    System.out.println(doc.getId());
                    System.out.println(doc.getData().get("bName").toString());
                    catBrand.add(doc.getData().get("bName").toString());
                    catcallback.finishFindBrands(doc.getData().get("bName").toString());

                }
            }
        });

    }
public interface catCallback{

    void finishFindBrands(String bName);
}
}
