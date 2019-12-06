package com.example.myapplication3.Shopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FeedbackActivity extends AppCompatActivity {

    //---------------------- 레이아웃 관련 변수 ---------------------------------
    Button enter;
    Button close;
    ListView feedbackListView;
    FeedbackAdapter feedbackAdapter;


    // ---------------------- Firebase 관련 변수 -------------------------------
    FirebaseFirestore db;
    CollectionReference brandRateRef;
    CollectionReference pathRef;

    //---------------------- 변수 ----------------------------
    Float rate;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // --------------------- 화면 요소 세팅 ----------------------------
        enter = findViewById(R.id.enter);
        close = findViewById(R.id.close);
        feedbackListView = findViewById(R.id.brandList);
        feedbackAdapter = new FeedbackAdapter();
        feedbackListView.setAdapter(feedbackAdapter);

        // --------------------- Firebase 변수 세팅 ----------------------------------------
        db = FirebaseFirestore.getInstance();
        pathRef = db.collection("path");
        brandRateRef = db.collection("brandRate");

        // ------------------------- 작동 기능 정의 --------------------------------


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                /*Float rate = rating.getRating();
                intent.putExtra("rate", rate);*/

                // 결과를 성공 (내용을 정상적으로 담았음)으로 설정하고 소포를 부친다.
                setResult(RESULT_OK, intent);

                // 화면을 끝낸다.
                finish();
            }
        });

        pathRef.orderBy("timestamp").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                // 컬렉션의 모든 Document(문서)를 반복한다.
                // 이 때 queryDocumentSnapshots를 그대로 사용하면 새로 추가된 게 아닌 트윗도 계속 반복 추가되므로
                // .getDocumentChanges를 호출하여 변경된 내역이 있는 데이터만 뽑아내 반복한다.
                for(DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()){

                    // 변경 내역이 ADDED(새로 추가됨) 라면
                    if(doc.getType() == DocumentChange.Type.ADDED){

                        Brand brand = doc.getDocument().toObject(Brand.class);

                        //FeedbackAdapter.addItem(brand);

                    }
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

    public void checkRating(String brandId,Float rating){
        id = brandId;
        rate = rating;

        //brandRateRef.document("brandRate").update("rate", rating); //id별로 올리기
    }
}
