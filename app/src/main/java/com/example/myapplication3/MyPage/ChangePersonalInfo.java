package com.example.myapplication3.MyPage;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;

public class ChangePersonalInfo  extends AppCompatActivity {
    private Button changeButton;//완료 버튼
    private String userSex;//사용자 성별
    private Integer userAge;//사용자 나이
    private RadioButton femaleButton;//여자버튼
    private RadioButton maleButton;//남자버튼
    private RadioGroup userSexGroup;//사용자 성별 입력받는 라디오 그룹
    private Spinner ageSpinner;//나이 스피너
    public MyPageController mypagecontrol;

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

        mypagecontrol= new MyPageController();

        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//유저의 나이를 스피너를 통해 입력받음
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

    }
}
