package com.example.myapplication3.Shopping;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class FeedbackActivity extends AppCompatActivity {

    //---------------------- 레이아웃 관련 변수 ---------------------------------
    Button enter;
    Button close;
    ListView feedbackListView;
    FeedbackAdapter feedbackAdapter;
    BrandAdapter brandAdapter;


    // ---------------------- Firebase 관련 변수 -------------------------------
    FirebaseFirestore db;
    CollectionReference brandRateRef;
    CollectionReference pathRef;
    CollectionReference brandRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // --------------------- 화면 요소 세팅 ----------------------------
        enter = findViewById(R.id.enter);
        close = findViewById(R.id.close);
        feedbackListView = findViewById(R.id.brandList);
        brandAdapter = new BrandAdapter();
        feedbackAdapter = new FeedbackAdapter();
        feedbackListView.setAdapter(feedbackAdapter);

        // --------------------- Firebase 변수 세팅 ----------------------------------------
        db = FirebaseFirestore.getInstance();
        brandRef = db.collection("user").document("abc123")
                .collection("path").document("1").collection("brand");
        pathRef = db.collection("user").document("abc123").collection("path");
        brandRateRef = db.collection("user").document("abc123").collection("brandRate");

        // ------------------------- 작동 기능 정의 --------------------------------


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);

                // 화면을 끝낸다.
                finish();
            }
        });

        brandRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Brand brand = new Brand();
                                brand.id = document.getId();
                                brand.name = document.getString("brandname");
                                brand.bought = document.getBoolean("bought");
                                feedbackAdapter.addItem(brand);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        // close (X 버튼)를 눌렀을 때 실행될 기능 정의
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 새로운 Intent (화면 간에 전달할 소포)를 만든다.
                Intent intent = new Intent();

                // 결과를 실패 (내용을 담지 않고 종료)으로 설정하고 소포를 부친다.
                setResult(RESULT_CANCELED, intent);

                // 화면을 끝낸다.
                finish();
            }
        });
        // -------------------------------------------------------------------------
    }
    // -------------------------------------------------------------------------
    // onCreate 함수 종료
    // =========================================================================

    public void checkRating(String brandId, String grade, String brandName){
        final String rate = grade;
        brandRef.document(brandId).update("grade", grade);
        final DocumentReference brandRef = brandRateRef.document(brandName);
        brandRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task)
                    {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.get("rate") != null) {//
                                brandRef.update("rate", Integer.valueOf(document.get("rate").toString()) + Integer.valueOf(rate));
                            } else {
                                System.out.println("해당 브랜드가 DB에 존재하지 않습니다.");
                            }
                        }
                        else {
                            System.out.println("해당 브랜드가 DB에 존재하지 않습니다.");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("brandRate DB접근 실패");
                    }
                });
    }


}
