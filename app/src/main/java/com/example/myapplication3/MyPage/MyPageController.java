package com.example.myapplication3.MyPage;



import com.example.myapplication3.Entity.User;
import com.example.myapplication3.Data;


import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyPageController implements User.MyPageCallback{//마이페이지 컨트롤러 클래스
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
    private boolean changed=false;
    private MyPageControlCallback myPageControlCallback;
    private MyPageControlBrandCallback brandCallback;

    public MyPageController() {
        this.id=null;
        this.sex=null;
        this.age=null;
        this.preferbrands= new ArrayList<String>();
        this.nonpreferbrands = new ArrayList<String>();
        this.path = null;
    }


    public void MyPageController(ChangePersonalInfo changePersonalInfo){//개인정보 수정 위한 것

        this.id=null;
        this.sex=null;
        this.age=null;
        this.preferbrands= new ArrayList<String>();
        this.nonpreferbrands = new ArrayList<String>();
        this.path = null;
        this.myPageControlCallback = changePersonalInfo;

    }
    public void MyPageControllerBrand(PreferNonpreferBrandBoundary brandBoundary){//개인정보 수정 위한 것

        this.id=null;
        this.sex=null;
        this.age=null;
        this.preferbrands= new ArrayList<String>();
        this.nonpreferbrands = new ArrayList<String>();
        this.path = null;
        this.brandCallback = brandBoundary;

    }
    public void setMyPageControlUser(String id, String pw){// 마이페이지 컨트롤러에서 접근할 유저 초기화
        myPageControlUser = new User(this);
        myPageControlUser.setId(id);
        myPageControlUser.setPw(pw);

        System.out.println("mypage control finished");
    }


    public boolean personalInfoChange(String sex, Integer age){//개인정보 수정관련 정보 넘겨 받음-> currentUser의 changePersonalInfo 메소드 호출하여 변경사항 객체 및 디비에 저장하기- 수정 예정
        this.sex=sex;
        this.age=age;

        changed= myPageControlUser.changePersonalInfo(this.sex,this.age);
        return changed;
    }

    public ArrayList<String> getPreferbrands(){// 사용자의 선호 브랜드 리스트 얻기-> User 클래스에서 디비 접근하고 이를 다시 컨트롤러로 넘겨줌
        preferbrands=myPageControlUser.findPrefer();
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

    @Override
    public void finishPersonalInfo() {
        System.out.println("콜백: 유저 정보 업데이트 함");
        myPageControlCallback.finishPersonalInfo2();
    }

    @Override
    public void getPrefer(String brand) {
        System.out.println("콜백: 선호 브랜드 디비에서 찾음");
        brandCallback.getPrefer2(brand);
    }

    @Override
    public void failBrand() {

    }



    //boughtlist controller
    public interface MyPageControlCallback extends Serializable {
        public void finishPersonalInfo2();


    }
    public interface MyPageControlBrandCallback extends Serializable{


        void getPrefer2(String brand);
    }

}
