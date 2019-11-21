package com.example.myapplication3.MyPage;



import com.example.myapplication3.Entity.User;

public class MyPageController {//마이페이지 컨트롤러 클래스
    private String sex;
    private Integer age;


    public void MyPageController(){
        this.sex=null;
        this.age=null;
    }
    public void personalInfoChange(String sex, String age, User currentUser){//개인정보 수정관련 정보 넘겨 받음-> currentUser의 changePersonalInfo 메소드 호출하여 변경사항 객체 및 디비에 저장하기

    }
}
