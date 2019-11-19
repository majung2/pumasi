package com.example.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MyPageSelectBoundary extends AppCompatActivity {//마이페이지 첫화면(어떤 서비스를 받을 건지 사용자가 선택하도록 하는 화면)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_select_menu);

    }
    public void onClickChangeInfo(View v){//개인정보 수정 버튼을 눌렀을 경우
        Intent intent = new Intent(
                MyPageSelectBoundary.this,
                ChangePersonalInfo.class); // 다음 넘어갈 클래스 지정
        startActivity(intent);
    }
    public void onClickPreferNonPreferInfo(View v){//개인정보 수정 버튼을 눌렀을 경우
        Intent intent = new Intent(
                MyPageSelectBoundary.this,
                ChangePreferNonPreferBoundary.class); // 다음 넘어갈 클래스 지정
        startActivity(intent);
    }

}
