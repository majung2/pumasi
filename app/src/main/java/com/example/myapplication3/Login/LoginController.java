package com.example.myapplication3.Login;
import android.util.Log;

import com.example.myapplication3.Entity.Category;
import com.example.myapplication3.Entity.User;
import com.example.myapplication3.PreferNonPreferBrandSelect.PreferNonpreferBrandSelectBoundary;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;


public class LoginController implements User.LoginCallback, Serializable, Category.catCallback ,User.AddBrandUser{
    private String name;
    private String id;
    private String pw;
    private String sex;
    private Integer age;
    private ArrayList<String> preferbrands;
    private ArrayList<String> nonpreferbrands; //회원가입시 선호 비선호 브랜드 담음

    private LoginControllCallback logincontrollcallback;


    private Category controlCategory;

    public User controlRegisterUser;
    public User controlUser;//로그인 후에 유저 정보를 담는 객체

    private FirebaseFirestore db;
    private boolean exists;

    private AddBrandCallback addBrandCallback;
    private userBrandCallback addBrandUser;
    private PreferNonpreferBrandSelectBoundary brandCallback;


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
    public void LoginControllerBrand(AddBrandCallback addbrand){//선호 비선호 추가를 위한 조회시 사용

        this.id=null;
        this.sex=null;
        this.age=null;
        this.preferbrands= new ArrayList<String>();
        this.nonpreferbrands = new ArrayList<String>();
        this.addBrandCallback = addbrand;
        //this.addBrandUser=addbrand;

    }

    public void LoginAddController(userBrandCallback addbrand){
        this.id=null;
        this.sex=null;
        this.age=null;
        this.preferbrands= new ArrayList<String>();
        this.nonpreferbrands = new ArrayList<String>();
        this.addBrandUser=addbrand;
    }

    public void LoginControllerBrand(PreferNonpreferBrandSelectBoundary brandBoundary){//선호비선호 바운더리 위한 것

        this.id=null;
        this.sex=null;
        this.age=null;
        this.preferbrands= new ArrayList<String>();
        this.nonpreferbrands = new ArrayList<String>();
        this.brandCallback = brandBoundary;


    }

    public void setLoginControlUser(String id, String pw){// 마이페이지 컨트롤러에서 접근할 유저 초기화
        controlRegisterUser = new User(this);
        controlRegisterUser.setId(id);
        controlRegisterUser.setPw(pw);

        System.out.println("mypage control finished");
    }
    public void setAddControlUser(String id, String pw){
        controlRegisterUser = new User( this);
        controlRegisterUser.setId(id);
        controlRegisterUser.setPw(pw);

        System.out.println("Registr/Login control finished");

    }

    //기본 초기화
    public LoginController() {//초기화
        this.name=null;
        this.id=null;
        this.pw=null;
        this.sex=null;
        this.age=null;

        this.preferbrands =new ArrayList<>();
        this.nonpreferbrands = new ArrayList<>();



    }


    //회원가입 method
    public void register(String UserName, String UserId, String UserPW,  String UserSex, Integer UserAge, ArrayList<String> preferbrands, ArrayList<String> nonpreferbrands) {//회원가입시 입력된 정보를 유저 클래스로 넘김(유저 클래스에서 최종 데이터 저장)
        this.name=UserName;
        this.id=UserId;
        this.pw = UserPW;
        this.sex = UserSex;
        this.age= UserAge;

        this.preferbrands = preferbrands;
        this.nonpreferbrands = nonpreferbrands; //회원가입시 선호 비선호 브랜드 담음

        final String TAG = "컨트롤러: ";
        Log.d(TAG,"완성");
        System.out.println("새로운 유저 추가하기 - LoginController");


        controlRegisterUser.register(name,id,pw,sex,age,preferbrands,nonpreferbrands);



    }
    public void getCatBrands(String selectedCategory) {//해당 카테고리에 있는 브랜드들을 디비에서 가져오기
        controlCategory = new Category();
        controlCategory.setCategory(this);
        controlCategory.setCNr(selectedCategory);
        controlCategory.findBrands();
    }

    //로그인 method
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


    //callback Method
    public void finishLogin(){

        System.out.println("콜백: 유저 존재함");

        logincontrollcallback.finishLogin2();


    } //login callback method
    public void failLogin(){
        System.out.println("콜백: 유저 존재안함");
        logincontrollcallback.failLogin2();
    } // login callback method

    @Override
    public void finishFindBrands(String bName) {
        System.out.println("콜백: 카테고리 브랜드 찾음");
        addBrandCallback.finishFindBrands2(bName);
    }

    public void finishAddBrand() {
        addBrandUser.finishAddBrand2();
    }

    public void addBrand(String itemAtPosition, String preferNon) {
        controlRegisterUser.setUserAddBrand(this);
        controlRegisterUser.findAddBrandRegister(itemAtPosition,preferNon);
    }
    //callback interface
    public interface LoginControllCallback extends Serializable {
        public void finishLogin2();
        public void failLogin2();
    } //login callback interface

    public interface AddBrandCallback extends  Serializable{//선택된 카테고리에 해당되는 브랜드 조회 관련 콜백

        void finishFindBrands2(String brand);

    }

    public interface userBrandCallback {


        void finishAddBrand2();
    }
    public interface LoginControlBrandCallback extends Serializable{//선호 비선호 브랜드 목록 조회 관련 콜백


        void finishDeleteBrand2();


    }

}