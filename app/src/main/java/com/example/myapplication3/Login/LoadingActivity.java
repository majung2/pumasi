package com.example.myapplication3.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication3.Entity.Brand;
import com.example.myapplication3.Entity.Category;
import com.example.myapplication3.Entity.ShoppingMall;
import com.example.myapplication3.Entity.SpotsInMall;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.util.Listener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class LoadingActivity extends Activity {//앱을 실행했을 때의 로딩화면
    private FirebaseFirestore db;
    private ArrayList<ShoppingMall> mallList;//서비스를 제공하는 쇼핑몰의 리스트
    private ShoppingMall mall;
    private Category category;
    private ArrayList<Category> catList;
    private Integer check;
    private SpotsInMall spot;
    private Brand brand;
    private ArrayList<Brand> brandList;
    private ArrayList<SpotsInMall> spotList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("스플레시 화면 클래스 접근");

        try{//몇 초동안 로딩화면 띄워지는지 설정
            db = FirebaseFirestore.getInstance();
            FirebaseFirestore db2 = FirebaseFirestore.getInstance();
            check=1;



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


