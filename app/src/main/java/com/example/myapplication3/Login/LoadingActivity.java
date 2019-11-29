package com.example.myapplication3.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

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

import static android.content.ContentValues.TAG;

public class LoadingActivity extends Activity {//앱을 실행했을 때의 로딩화면
    private FirebaseFirestore db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("스플레시 화면 클래스 접근");
        try{//몇 초동안 로딩화면 띄워지는지 설정
            db = FirebaseFirestore.getInstance();
           DocumentReference docRef = db.collection("shoppingMall").document("pajuPremiumOutlet").collection("category").document("컨템포러리");
            db.collection("shoppingMall").document("pajuPremiumOutlet").collection("category").document("컨템포러리").collection("33")

                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    System.out.println("섭컬렉션 성공");
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });


            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            // totalBrandLIst.add(document.getData().[0]);-> firebase는 배열 가져올 수 없음
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

            Thread.sleep(2000);
            System.out.println("로딩 화면 띄워지는 시간");

        } catch (InterruptedException e) {//로딩 실패했을 때
            e.printStackTrace();
            Toast.makeText(this,"앱을 실행할 수 없습니다.",Toast.LENGTH_LONG).show();
        }
        startActivity(new Intent(this,MainActivity.class));//로그인 화면으로 전환
        finish();
    }
}
