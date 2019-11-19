package com.example.myapplication3;
import android.util.Log;

import java.util.ArrayList;
public class LoginController {
    private String name;
    private String id;
    private String pw;
    private String sex;
    private Integer age;
    public User currentUser;

    public void LoginController() {//초기화
        this.name=null;
        this.id=null;
        this.pw=null;
        this.sex=null;
        this.age=null;


    }

    public void register(String UserName, String UserId, String UserPW,  String UserSex, Integer UserAge) {//회원가입시 입력된 정보를 유저 클래스로 넘김(유저 클래스에서 최종 데이터 저장)
    this.name=UserName;
    this.id=UserId;
    this.pw = UserPW;
    this.sex = UserSex;
    this.age= UserAge;
        final String TAG = "컨트롤러: ";
        Log.d(TAG,"완성");
    currentUser = new User();
    currentUser.register(name,id,pw,sex,age);

    }
    public void login(){//로그인(디비에서 읽어오는 코드 추가할 예정)

    }

}