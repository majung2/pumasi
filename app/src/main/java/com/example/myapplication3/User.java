package com.example.myapplication3;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
public class User {
    private ArrayList<Integer> currentXY;
    private String id;
    private String pw;
    private String name;
    private String sex;
    private Integer age;
    private Integer currentFloor;


    //유저 초기화
    public User(){
        this.id=null;
        this.pw=null;
        this.name=null;
        this.sex=null;
        this.age=null;
        this.currentFloor=null;
        this.currentXY=null;
    }
    //회원가입- 사용자가 입력한 정보 디비에 저장하는 함수
    public void register(String name, String id, String pw, String sex, Integer age){
       this.name=name;
       this.id=id;
       this.pw=pw;
       this.age=age;
       this.sex=sex;

       //디비에 저장하는 함수 호출(파이어베이스 이용해 추가할 예정)


    }

}
