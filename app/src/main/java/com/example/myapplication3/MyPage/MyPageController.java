package com.example.myapplication3.MyPage;



import android.widget.AdapterView;

import com.example.myapplication3.Entity.Category;
import com.example.myapplication3.Entity.User;
import com.example.myapplication3.Data;


import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyPageController implements User.MyPageCallback, Category.catCallback,User.AddBrandUser{//마이페이지 컨트롤러 클래스
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
    private AddBrandCallback addBrandCallback;
    private Category controlCategory;
   private userBrancCallback addBrandUser;
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
    public void MyPageControllerBrand(PreferNonpreferBrandBoundary brandBoundary){//선호비선호 바운더리 위한 것

        this.id=null;
        this.sex=null;
        this.age=null;
        this.preferbrands= new ArrayList<String>();
        this.nonpreferbrands = new ArrayList<String>();
        this.path = null;
        this.brandCallback = brandBoundary;


    }public void MyPageControllerBrand(AddPreferNonPreferBoundary addbrand){//선호 비선호 추가를 위한 조회시 사용

        this.id=null;
        this.sex=null;
        this.age=null;
        this.preferbrands= new ArrayList<String>();
        this.nonpreferbrands = new ArrayList<String>();
        this.path = null;
       this.addBrandCallback = addbrand;
        //this.addBrandUser=addbrand;

    }
    public void MyPageAddController(AddPreferNonPreferBoundary addbrand){
        this.id=null;
        this.sex=null;
        this.age=null;
        this.preferbrands= new ArrayList<String>();
        this.nonpreferbrands = new ArrayList<String>();
        this.path = null;
       // this.addBrandCallback = addbrand;
        this.addBrandUser=addbrand;
    }
    public void setMyPageControlUser(String id, String pw){// 마이페이지 컨트롤러에서 접근할 유저 초기화
        myPageControlUser = new User( this);
        myPageControlUser.setId(id);
        myPageControlUser.setPw(pw);

        System.out.println("mypage control finished");
    }
public void setAddControlUser(String id, String pw){
    myPageControlUser = new User( this);
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
        this.preferbrands=myPageControlUser.findPrefer();
        return this.preferbrands;
    }
    public ArrayList<String> getNonPreferbrands(){// 사용자의 선호 브랜드 리스트 얻기-> User 클래스에서 디비 접근하고 이를 다시 컨트롤러로 넘겨줌
        this.nonpreferbrands = myPageControlUser.findNonfPrefer();
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
    public void finishPersonalInfo() {//개인 정보 수정
        System.out.println("콜백: 유저 정보 업데이트 함");
        myPageControlCallback.finishPersonalInfo2();
    }

    @Override
    public void getPrefer(String brand) {//선호 브랜드 조회
        System.out.println("콜백: 선호 브랜드 디비에서 찾음");
        brandCallback.getPrefer2(brand);
    }

    @Override
    public void failBrand() {//브랜드 조회 실패

    }

    @Override
    public void getNonPrefer(String brand) {//비선호 브랜드 조회
        System.out.println("콜백: 비선호 브랜드 디비에서 찾음");
        brandCallback.getNonPrefer2(brand);
    }

    @Override
    public void finishDeleteBrand() {//브랜드 삭제
        System.out.println("콜백: 브랜드 디비에서 삭제 완료");
        brandCallback.finishDeleteBrand2();
    }



    public void deletBrand(String itemAtPosition, String nonpreferbrand) {
        myPageControlUser.deleterSelectedUser(itemAtPosition,nonpreferbrand);
    }

    public void getCatBrands(String selectedCategory) {//해당 카테고리에 있는 브랜드들을 디비에서 가져오기
         controlCategory = new Category();
         controlCategory.setCategory(this);
         controlCategory.setCNr(selectedCategory);
         controlCategory.findBrands();
    }

    @Override
    public void finishFindBrands(String bName) {
        System.out.println("콜백: 카테고리 브랜드 찾음");
        addBrandCallback.finishFindBrands2(bName);
    }

    public void addBrand(String itemAtPosition, String preferNon) {
        myPageControlUser.setUserAddBrand(this);
        myPageControlUser.findAddBrand(itemAtPosition,preferNon);
    }

    @Override
    public void finishAddBrand() {
        addBrandUser.finishAddBrand2();
    }


    //boughtlist controller
    public interface MyPageControlCallback extends Serializable {//개인정보 수정 관련 콜백
        public void finishPersonalInfo2();


    }
    public interface MyPageControlBrandCallback extends Serializable{//선호 비선호 브랜드 목록 조회 관련 콜백


        void getPrefer2(String brand);

        void getNonPrefer2(String brand);

        void finishDeleteBrand2();


    }

    public interface AddBrandCallback extends  Serializable{//선택된 카테고리에 해당되는 브랜드 조회 관련 콜백

        void finishFindBrands2(String brand);
      
    }
    public interface userBrancCallback {


        void finishAddBrand2();
    }


}
