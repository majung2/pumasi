package com.example.myapplication3.Entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
public class User {
    private ArrayList<Integer> currentXY;
    private String id;
    private String pw;
    private String name;
    private String sex;
    private Integer age;
    private Integer currentFloor;
    private ArrayList<String> preferBrands;
    private ArrayList<String> nonPreferBrands;
    private FirebaseFirestore db;

    //유저 초기화
    public User(){
        this.id=null;
        this.pw=null;
        this.name=null;
        this.sex=null;
        this.age=null;
        this.currentFloor=null;
        this.currentXY=null;
        this.preferBrands= new ArrayList<>();
        this.nonPreferBrands= new ArrayList<>();
    }
    //회원가입- 사용자가 입력한 정보 디비에 저장하는 함수
    public void register(String name, String id, String pw, String sex, Integer age,ArrayList preferBrands, ArrayList nonPreferBrands){
       this.name=name;
       this.id=id;
       this.pw=pw;
       this.age=age;
       this.sex=sex;
       this.preferBrands = preferBrands;
       this.nonPreferBrands = nonPreferBrands;
       //디비에 저장하는 함수 호출(파이어베이스 이용해 추가할 예정)
    }
    public void login(String name, String id, String pw, String sex, Integer age){//로그인이 제대로 되었을 때, 사용자 정보를 해당 객체에 저장- 컨트롤러에서 디비 접근 후, 여기서 객체에 저장하는 방식인데 유저 클래스에서 디비 접근 자체를 하는게 나을까요?
        this.name=name;
        this.id=id;
        this.pw=pw;
        this.age=age;
        this.sex=sex;
    }
    public void getBrand(){
        //사용자가 선호비선호하는 브랜드 디비에서 읽어오기
    }

    public void changePersonalInfo(String sex, Integer age){
        //객체 상 변경
        this.sex=sex;
        this.age=age;

        //디비에 변경 반영
        db = FirebaseFirestore.getInstance();
        DocumentReference contact = db.collection("user").document(id);
        contact.update("sex", this.sex);//성별 업데이트
        contact.update("age", this.age)//나이 업데이트
                .addOnSuccessListener(new OnSuccessListener< Void >() {
                    @Override
                    public void onSuccess(Void aVoid) {//업데이트 성공시

                    }
                });
    }

}
