package com.example.myapplication3.MyPage;



import com.example.myapplication3.Entity.User;
import com.example.myapplication3.Data;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyPageController {//마이페이지 컨트롤러 클래스
    private String id;
    private String sex;
    private Integer age;
    private ArrayList<String> preferbrands;
    private ArrayList<String> nonpreferbrands;
    private ArrayList<String> totalBrandList;
    private User myPageControlUser;
    //path controll
    private String path;
    //bought list
    private ArrayList<Data> bought;



    public void MyPageController(){
        this.id="abc123";
        this.sex=null;
        this.age=null;
        this.preferbrands= new ArrayList<String>();
        this.nonpreferbrands = new ArrayList<String>();
        this.path = null;

    }
    public void personalInfoChange(String sex, Integer age, User currentUser){//개인정보 수정관련 정보 넘겨 받음-> currentUser의 changePersonalInfo 메소드 호출하여 변경사항 객체 및 디비에 저장하기- 수정 예정
        this.sex=sex;
        this.age=age;
        this.myPageControlUser=currentUser;

        currentUser.setSex(this.sex);
        currentUser.setAge(this.age);
    }

    public ArrayList<String> getPreferbrands(){// 사용자의 선호 브랜드 리스트 얻기-> User 클래스에서 디비 접근하고 이를 다시 컨트롤러로 넘겨줌
        this.myPageControlUser= new User();
        this.myPageControlUser.setId(this.id);
        this.preferbrands=myPageControlUser.getPreferBrand();
        return this.preferbrands;
    }
    public ArrayList<String> getNonPreferbrands(){// 사용자의 선호 브랜드 리스트 얻기-> User 클래스에서 디비 접근하고 이를 다시 컨트롤러로 넘겨줌
        this.nonpreferbrands=myPageControlUser.getNonPreferBrand();
        return this.nonpreferbrands;
    }
    public void getAllBrands(){
        this.totalBrandList=this.myPageControlUser.getAllBrands();
    }

   /* public void setCurrentUserOnControl(User user){
        myPageControlUser = new User();
        myPageControlUser.setId(user.getId());
        myPageControlUser.setPw(user.getPw());
    }*/

   //path controller
    public void loadPath(String selectedPath){
        path = selectedPath;
    }

    public String getPath() {
        return path;
    }

    //path controller

    //boughtlist controller

    public ArrayList<Data> getBought() {

        //entity modify

        this.bought = new ArrayList<Data>();
        Data data1,data2,data3;
        data1= new Data("나이키","2019.11.25","슈즈");
        data2= new Data("아디다스","2019.11.25","슈즈");
        data3= new Data("ABC마트","2019.11.25","슈즈");


        bought.add(data1);
        bought.add(data2);
        bought.add(data3);

        return bought;
        //entity modify

        }
    //boughtlist controller

}
