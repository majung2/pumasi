package com.example.myapplication3.Login;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication3.Entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginController {
    private String name;
    private String id;
    private String pw;
    private String sex;
    private Integer age;

    public  User controlUser;//로그인 후에 유저 정보를 담는 객체
    private FirebaseFirestore db;
    private boolean exists;
    public void LoginController() {//초기화
        this.name=null;
        this.id=null;
        this.pw=null;
        this.sex=null;
        this.age=null;
        this.exists=false;



    }

    public void register(String UserName, String UserId, String UserPW,  String UserSex, Integer UserAge) {//회원가입시 입력된 정보를 유저 클래스로 넘김(유저 클래스에서 최종 데이터 저장)
    this.name=UserName;
    this.id=UserId;
    this.pw = UserPW;
    this.sex = UserSex;
    this.age= UserAge;
    this.exists=false;
        final String TAG = "컨트롤러: ";
        Log.d(TAG,"완성");
    controlUser.register(name,id,pw,sex,age);


    }
    public boolean login(String currentid, String currentpassword){//로그인(디비에서 읽어오는 코드 추가할 예정)
        Log.d("결과:", "돌아감");
        exists=false;
        id=currentid;
        pw=currentpassword;
        this.controlUser= new User();
        System.out.println("유저생성");
        exists=controlUser.verifyLogin(id,pw);

        return exists;
    }
    public User getUserFromLoginControll(){
        return controlUser;
    }



}