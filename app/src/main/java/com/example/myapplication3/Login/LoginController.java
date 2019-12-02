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

import java.io.Serializable;


public class LoginController implements User.LoginCallback,Serializable {
    private String name;
    private String id;
    private String pw;
    private String sex;
    private Integer age;
    private LoginControllCallback logincontrollcallback;

    public  User controlUser;//로그인 후에 유저 정보를 담는 객체
    private FirebaseFirestore db;
    private boolean exists;



    //콜백용 초기화 (call back initialize)
    public LoginController(MainActivity mainActivity) {//초기화
        this.name=null;
        this.id=null;
        this.pw=null;
        this.sex=null;
        this.age=null;
        this.exists=false;
        this.logincontrollcallback = mainActivity;

    }


    public LoginController() {//초기화
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
    public boolean login(String currentid, String currentpassword){//로그인
        Log.d("결과:", "돌아감");

        id=currentid;
        pw=currentpassword;
        this.controlUser= new User(this);
        System.out.println("유저생성");
        exists=controlUser.verifyLogin(id,pw);
        return exists;


    }
    public User getUserFromLoginControll(){
        return controlUser;
    }

public void finishLogin(){

    System.out.println("콜백: 유저 존재함");

    logincontrollcallback.finishLogin2();


}

public void failLogin(){
        System.out.println("콜백: 유저 존재안함");
        logincontrollcallback.failLogin2();
}

public interface LoginControllCallback extends Serializable {
        public void finishLogin2();
        public void failLogin2();
    }
}