package com.example.myapplication3.Entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication3.Login.LoadingActivity;
import com.example.myapplication3.MyPage.MyPageController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.value.IntegerValue;

import java.io.Serializable;
import java.lang.reflect.Array;
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
    private Integer ppSize;
    private ArrayList<String> preferBrands;
    private ArrayList<String> nonPreferBrands;
    private ArrayList<String> totalBrandList;
    private ArrayList<String> previousPathList;
    private FirebaseFirestore db;
    private boolean found;

    private LoginCallback loginCallback ;
    private MyPageCallback myPageCallback;
   private AddBrandUser addback;
    //유저 초기화
    public User(){
        this.id=null;
        this.pw=null;
        this.name=null;
        this.sex=null;
        this.age=null;
        this.currentFloor=null;
        this.currentXY=null;
        this.ppSize = null;
        this.preferBrands= new ArrayList<>();
        this.nonPreferBrands= new ArrayList<>();
        this.totalBrandList= new ArrayList<>();
        this.previousPathList = new ArrayList<>();
        this.found=false;

    }

    //콜백 초기화 (call back initialize)

    public User(LoginCallback loginCallback){
        this.id=null;
        this.pw=null;
        this.name=null;
        this.sex=null;
        this.age=null;
        this.currentFloor=null;
        this.currentXY=null;
        this.ppSize = null;
        this.preferBrands= new ArrayList<>();
        this.nonPreferBrands= new ArrayList<>();
        this.totalBrandList= new ArrayList<>();
        this.previousPathList = new ArrayList<>();
        this.found=false;
        this.loginCallback = loginCallback;



    }
    public User(MyPageCallback myPageCallback){
        this.id=null;
        this.pw=null;
        this.name=null;
        this.sex=null;
        this.age=null;
        this.currentFloor=null;
        this.currentXY=null;
        this.ppSize = null;
        this.preferBrands= new ArrayList<>();
        this.nonPreferBrands= new ArrayList<>();
        this.totalBrandList= new ArrayList<>();
        this.previousPathList = new ArrayList<>();
        this.found=false;
        this.myPageCallback=myPageCallback;


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
    public void setName(String name){
        this.name=name;
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
    public String getName(){
        return this.name;
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
        return this.totalBrandList;
    }

    public boolean changePersonalInfo(String sex, Integer age){
        //객체 상 변경
        this.sex=sex;
        this.age=age;

        found=false;
        //디비에 변경 반영
        db = FirebaseFirestore.getInstance();
        DocumentReference contact = db.collection("user").document(id);
        contact.update("sex", this.sex);//성별 업데이트
        contact.update("age", this.age)//나이 업데이트
                .addOnSuccessListener(new OnSuccessListener< Void >() {
                    @Override
                    public void onSuccess(Void aVoid) {//업데이트 성공시
                            found = true;

                    }
                });myPageCallback.finishPersonalInfo();

        return found;
    }

    public boolean verifyLogin(String inputId, String InputPw){
       this.id = inputId;
       this.pw = InputPw;
        db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("user").document(id);
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {//디비 접근에 성공한 경우
                if (task.isSuccessful()) {//해당 아이디의 유저를 찾은 경우
                    DocumentSnapshot doc = task.getResult();
                    if(pw.equals(doc.get("password"))){//해당 아이디, 비밀번호가 일치하는 유저를 찾은 경우
                        name =  doc.get("name").toString();
                        sex = doc.get("sex").toString();
                        pw= doc.get("password").toString();
                        age = Integer.parseInt(doc.get("age").toString());
                        System.out.println("찾음");
                        System.out.println(" "+id+name+sex+pw+age);
                        //System.out.println(doc.get("preferbrand").split(","));
                        String[] prefer = doc.get("preferbrand").toString().split(",");
                        prefer[0]=prefer[0].split("\\[")[1];
                        prefer[prefer.length-1]= prefer[prefer.length-1].split("\\]")[0];

                        for(Integer i=0;i<prefer.length;i++){
                            if(i!=0){
                                prefer[i]=prefer[i].substring(1);
                            }
                            preferBrands.add(prefer[i]);

                        }
                        String[] nonprefer = doc.get("nonpreferbrand").toString().split(",");
                        nonprefer[0]=nonprefer[0].split("\\[")[1];
                        nonprefer[nonprefer.length-1]= nonprefer[nonprefer.length-1].split("\\]")[0];

                        for(Integer i=0;i<nonprefer.length;i++){
                            if(i!=0){
                                nonprefer[i]=nonprefer[i].substring(1);
                            }
                            nonPreferBrands.add(nonprefer[i]);

                        }
                        found = true;
                        loginCallback.finishLogin();

                    }
                    else{
                        System.out.println("user단 else문 로그인 실패222222222222");
                        loginCallback.failLogin();
                    }

                }
                else{//해당 아이디의 유저를 찾지 못한 경우

                    System.out.println("user단 else문 로그인 실패");
                    loginCallback.failLogin();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {//디비 접근에 실패한 경우
                        System.out.println("없음");
                        loginCallback.failLogin();//수정예정

                    }
                });
      return found;
    }

    public ArrayList<String> findPrefer() {//선호 브랜드를 디비에서 검색
        //ArrayList<String> gotPrefer = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("user").document(id);
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {//디비 접근에 성공한 경우
                if (task.isSuccessful()) {//해당 아이디의 유저를 찾은 경우
                    DocumentSnapshot doc = task.getResult();
                    if(pw.equals(doc.get("password"))){//해당 아이디, 비밀번호가 일치하는 유저를 찾은 경우
                        String[] prefer = doc.get("preferbrand").toString().split(",");
                        prefer[0]=prefer[0].split("\\[")[1];
                        prefer[prefer.length-1]= prefer[prefer.length-1].split("\\]")[0];

                        for(Integer i=0;i<prefer.length;i++){
                            if(i!=0){
                                prefer[i]=prefer[i].substring(1);
                            }
                            preferBrands.add(prefer[i]);
                            myPageCallback.getPrefer(prefer[i]);
                        }

                    }
                    else{
                        System.out.println("선호 브랜드 읽기 실패");
                        myPageCallback.failBrand();
                    }

                }
                else{//해당 아이디의 유저를 찾지 못한 경우

                    System.out.println("user 찾기 실패");
                    myPageCallback.failBrand();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {//디비 접근에 실패한 경우
                        System.out.println("없음");
                        myPageCallback.failBrand();

                    }
                });
        return this.preferBrands;
    }

    public ArrayList<String> findNonfPrefer() {
        db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("user").document(id);
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {//디비 접근에 성공한 경우
                if (task.isSuccessful()) {//해당 아이디의 유저를 찾은 경우
                    DocumentSnapshot doc = task.getResult();
                    if(pw.equals(doc.get("password"))){//해당 아이디, 비밀번호가 일치하는 유저를 찾은 경우
                        String[] nonprefer = doc.get("nonpreferbrand").toString().split(",");
                        nonprefer[0]=nonprefer[0].split("\\[")[1];
                        nonprefer[nonprefer.length-1]= nonprefer[nonprefer.length-1].split("\\]")[0];

                        for(Integer i=0;i<nonprefer.length;i++){
                            if(i!=0){
                                nonprefer[i]=nonprefer[i].substring(1);
                            }
                            nonPreferBrands.add(nonprefer[i]);
                            myPageCallback.getNonPrefer(nonprefer[i]);
                        }

                    }
                    else{
                        System.out.println("비선호 브랜드 실패");
                        myPageCallback.failBrand();
                    }

                }
                else{//해당 아이디의 유저를 찾지 못한 경우

                    System.out.println("비선호 브랜드 실패");
                    myPageCallback.failBrand();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {//디비 접근에 실패한 경우
                        System.out.println("비선호 브랜드 없음");
                        myPageCallback.failBrand();

                    }
                });
        return this.nonPreferBrands;
    }

    public void deleterSelectedUser(String itemAtPosition, String brandType) {
        DocumentReference Ref = db.collection("user").document(this.id);
        Ref.update(brandType, FieldValue.arrayRemove(itemAtPosition));
        myPageCallback.finishDeleteBrand();
        
    }

    public void findAddBrand(String itemAtPosition, String preferNon) {
      db = FirebaseFirestore.getInstance();
        System.out.println(id);
        System.out.println(preferNon);
        System.out.println(itemAtPosition);
        DocumentReference Ref = db.collection("user").document(this.id);

        Ref.update(preferNon, FieldValue.arrayUnion(itemAtPosition));
        addback.finishAddBrand();
        
    }

    public void setUserAddBrand(AddBrandUser adduser) {
        this.addback=adduser;
    }

    public ArrayList<String> findPreviousPathDB() {
        previousPathList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("user").document(id);
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {//디비 접근에 성공한 경우
                if (task.isSuccessful()) {//해당 아이디의 유저를 찾은 경우
                    DocumentSnapshot doc = task.getResult();
                    if (pw.equals(doc.get("password"))) {
                        System.out.println("DB접근 성공");

                        ppSize = Integer.parseInt(doc.get("pathsize").toString());
                        if (ppSize == 0) {
                           System.out.println("이전 방문한 패스 없음");
                        } else {// 방문한 패스가 있다면 pathlist추가
                            for (Integer i = 1; i < ppSize+1; i++) {
                                DocumentReference ref2 = db.collection("user").document(id);
                                ref2.collection("path").document(i.toString())
                                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) { //해당 패스를 찾은경우
                                            DocumentSnapshot doc2 = task.getResult();

                                                previousPathList.add(doc2.get("date").toString());
                                                System.out.println(previousPathList);

                                        } else {//해당 아이디의 유저를 찾지 못한 경우

                                            System.out.println("이전루트조회 실패");
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {//디비 접근에 실패한 경우
                        System.out.println("DB에 path 없음");

                    }
                    });
        return previousPathList;
    } //find previous method end

    public interface LoginCallback {

        public void finishLogin();
        public void failLogin();


    }
    public interface MyPageCallback {

        void finishPersonalInfo();
        void getPrefer(String brand);
        void failBrand();
        void getNonPrefer(String brand);
        void finishDeleteBrand();


    }
    public interface AddBrandUser{

    void finishAddBrand();
}

}
