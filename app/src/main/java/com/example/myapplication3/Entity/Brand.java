package com.example.myapplication3.Entity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Brand extends SpotsInMall {
    private boolean sale;//세일 여부
    private String priceLevel;//가격대
    private Integer brTotalRate;// 유저가 이 브랜드에 매기는 총 점수
    public Brand(){}

    private static FirebaseFirestore db; //db 변수
    public void setPriceLevel(String pl){
        this.priceLevel=pl;
    }

    public String getPriceLevel(){
        return this.priceLevel;
    }
    public boolean getSale(){return this.sale;}

    public static ArrayList<ArrayList<Brand>> getAllBrand(){
        ArrayList<ArrayList<Brand>> allBrList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        for(int i = 1; i<=11; i++){
            String docID = "c"+i;
            CollectionReference tmp =  db.collection("ShoppingMall").document("M1")
                    .collection("category").document(docID).collection("brand");
            final ArrayList<Brand> tmpList = new ArrayList<>();
            for(int j = 1; j<=42; j++){
                String brID = "b"+j;
                DocumentReference bRef = tmp.document(brID);
                bRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot doc = task.getResult();
                            Brand tmpBr = new Brand();
                            tmpBr.setSpotName(doc.get("bName").toString());
                            tmpBr.setPriceLevel(doc.get("priceLevel").toString());
                            tmpBr.setSpotFloor(Integer.parseInt(doc.get("floor").toString()));
                            tmpBr.setSpotLocation(Integer.parseInt(doc.get("x").toString()),
                                    Integer.parseInt(doc.get("y").toString()));
                            tmpList.add(tmpBr);
                        } else {

                        }
                    }
                });
            }
            allBrList.add(tmpList);
        }

        return allBrList;
    }

    //브랜드명으로 브랜드 속성 모두 출력
    public static Brand findBrandInfoByBrandName(String BrandName){
        Brand BrandInfo = new Brand();
        BrandInfo.setSpotName(BrandName);
        ArrayList<ArrayList<Brand>> allBrands = getAllBrand();

        for(ArrayList<Brand> X:allBrands){
            for(Brand Y:X){
                if(BrandName.equals(Y.getSpotName())){
                    BrandInfo.setSpotLocation(Y.getSpotLocation().get(0),Y.getSpotLocation().get(1));
                    BrandInfo.setSpotFloor(Y.getSpotFloor());
                }
            }
        }



        return BrandInfo;
    }



}
