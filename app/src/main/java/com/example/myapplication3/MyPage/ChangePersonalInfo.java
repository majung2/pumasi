package com.example.myapplication3.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.Entity.User;
import com.example.myapplication3.Login.MainActivity;
import com.example.myapplication3.R;

import java.util.ArrayList;

public class ChangePersonalInfo  extends AppCompatActivity implements  MyPageController.MyPageControlCallback{
    private Button changeButton;//완료 버튼
    private String userSex;//사용자 성별
    private Integer userAge;//사용자 나이
    private String userName;
    private String userId;
    private String userPw;
    private RadioButton femaleButton;//여자버튼
    private RadioButton maleButton;//남자버튼
    private RadioGroup userSexGroup;//사용자 성별 입력받는 라디오 그룹
    private Spinner ageSpinner;//나이 스피너
    public MyPageController mypagecontrol;
    private User currentUser;
    private ArrayList<String> preferBrands;
    private ArrayList<String> nonPreferBrands;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)//옵션 메뉴바
    {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.mainmenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)//메뉴 선택
    {
        //   Toast toast = Toast.makeText(getApplicationContext(),"", Toast.LENGTH_LONG);
        int state=3;
        switch(item.getItemId())
        {
            case R.id.menu1:
                state=0;
                break;
            case R.id.menu2:
                state=1;
                break;

        }
        if(state==0){
            /*Intent intent = new Intent(// 다음 화면으로 전환
                    MainActivity.this,
                    MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",currentUser.getId());
            intent.putExtra("pw",currentUser.getPw());

            startActivity(intent);*/
            Toast toast = Toast.makeText(getApplicationContext(),"현재 기능이 마이페이지입니다", Toast.LENGTH_LONG);
            toast.show();
        }
        else if(state==1){
            Toast.makeText(ChangePersonalInfo.this,"로그아웃되었습니다.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(// 다음 화면으로 전환
                    ChangePersonalInfo.this,
                    MainActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_personal_info);

        changeButton = (Button)findViewById(R.id.changeButton);
        femaleButton = (RadioButton)findViewById(R.id.femaleButton);
        maleButton = (RadioButton)findViewById(R.id.maleButton);
        userSexGroup = (RadioGroup)findViewById(R.id.userSex);
        ageSpinner = (Spinner)findViewById(R.id.spinner);
        changeButton = (Button)findViewById(R.id.changeButton);

     //   mypagecontrol= new MyPageController(this);//컨틀롤러 생성

        //받아온 유저 정보
        Intent intent = getIntent();
        userId = intent.getStringExtra("id");
        userPw = intent.getStringExtra("pw");

        //컨트롤러 초기화 및 유저 객체 생성
        mypagecontrol = new MyPageController();
        mypagecontrol.MyPageController(this);
        mypagecontrol.setMyPageControlUser(userId,userPw);

        //유저의 나이를 스피너를 통해 입력받음
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//문자열을 정수형으로 바꾸기
                if((parent.getItemAtPosition(position).toString().equals("10대"))){
                    userAge=10;
                }

                else if((parent.getItemAtPosition(position).toString().equals("20대"))){
                    userAge=20;
                }
                else if((parent.getItemAtPosition(position).toString().equals("30대"))){
                    userAge=30;
                }
                else if((parent.getItemAtPosition(position).toString().equals("40대"))){
                    userAge=40;
                }
                else if((parent.getItemAtPosition(position).toString().equals("50대"))){
                    userAge=50;
                }
                else if((parent.getItemAtPosition(position).toString().equals("60대"))){
                    userAge=60;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {//아무것도 고르지 않았을 때는 임의로 10대로 지정
                userAge=10;
            }
        });

        //유저의 성별 선택
        userSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {//유저의 성별을 라디오 그룹을 이용해 입력받음
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.femaleButton){
                    userSex="여";
                }
                else{
                    userSex="남";
                }
            }
        });
    }
    public void onClickComplete(View v){
        //데이터를 MyPageController.java로 넘겨서 저장하는 과정 필요
        //입력된 값이 없는 항목이있을 경우 예외처리하기
        if(femaleButton.isChecked()==false &&maleButton.isChecked()==false){//성별 체크를 하지 않은 경우 임의로 남성으로 처리
            userSex="남";
        }
        mypagecontrol.personalInfoChange(userSex,userAge);
    }

    public void finishPersonalInfo2(){
        Toast.makeText(this,"개인 정보 수정을 완료하였습니다.",Toast.LENGTH_LONG).show();
    }
}
