package com.example.myapplication3.Entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Integer> currentXY;
    private String id;
    private String pw;
    private String name;
    private String sex;
    private Integer age;
    private Integer currentFloor;
    private ArrayList<String> preferBrands;
    private ArrayList<String> nonPreferBrands;
    private ArrayList<String> totalBrandLIst;
    private FirebaseFirestore db;
    private boolean found;

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
        this.totalBrandLIst= new ArrayList<>();
        this.found=false;

        //선호비선호 브랜드 리스트는 테스트를 위하여 임의로 설정해노았습니다 디비연결되면 로그인시 아예 유저 객체에 브랜드 리스트를 저장시켜놓으면 될 것 같아요
        preferBrands.add("AbC MART");
        preferBrands.add("NEANPOLE");
        nonPreferBrands.add("BLACKYARK");
        nonPreferBrands.add("ADIDAS");
    }
    //회원가입- 사용자가 입력한 정보 디비에 저장하는 함수
    public void register(String name, String id, String pw, String sex, Integer age){
       this.name=name;
       this.id=id;
       this.pw=pw;
       this.age=age;
       this.sex=sex;
       this.preferBrands =null;
       this.nonPreferBrands = null;
       //디비에 저장하는 함수 호출(파이어베이스 이용해 추가할 예정)
    }
    public void login(String name, String id, String pw, String sex, Integer age){//로그인이 제대로 되었을 때, 사용자 정보를 해당 객체에 저장- 컨트롤러에서 디비 접근 후, 여기서 객체에 저장하는 방식인데 유저 클래스에서 디비 접근 자체를 하는게 나을까요?
        this.name=name;
        this.id=id;
        this.pw=pw;
        this.age=age;
        this.sex=sex;

    }
    public ArrayList<String> getPreferBrand(){
        return this.preferBrands;
    }
    public ArrayList<String> getNonPreferBrand(){
        return this.nonPreferBrands;
    }

    public void setId(String inputid){
        this.id=inputid;
    }
    public void setPw(String inputpw){
        this.pw= inputpw;
    }
    public void setPrefer(ArrayList<String> list){
        this.preferBrands=list;
    }
    public void setNonPrefer(ArrayList<String> list){
        this.nonPreferBrands=list;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public void setAge(Integer age){
        this.age=age;
    }

    public String getId(){
        return this.id;
    }
    public String getPw(){
        return this.pw;
    }
    public String getSex(){
        return this.sex;
    }
    public Integer getAge(){
        return this.age;
    }

    public ArrayList<String> getAllBrands(){
        db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("category").document("해외명품");
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
        return this.totalBrandLIst;
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

    public boolean verifyLogin(String inputId, String InputPw){
        this.id=inputId;
        this.pw=InputPw;
        db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("user").document(id);
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {//해당 아이디의 유저를 찾은 경우
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    if(pw.equals(doc.get("password"))){//해당 아이디, 비밀번호가 일치하는 유저를 찾은 경우
                        name =  doc.get("name").toString();
                        sex = doc.get("sex").toString();
                        pw= doc.get("password").toString();
                        age = Integer.parseInt(doc.get("age").toString());
                        System.out.println("찾음");
                        System.out.println(" "+id+name+sex+pw+age);
                        found=true;

                    }
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("없음");
                    }
                });
        return found;
    }
}
