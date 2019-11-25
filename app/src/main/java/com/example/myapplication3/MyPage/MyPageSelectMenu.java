package com.example.myapplication3.MyPage;
//마이페이지의 메뉴 화면 관련 클래스
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.Entity.User;
import com.example.myapplication3.R;

import java.io.Serializable;

public class MyPageSelectMenu extends AppCompatActivity  {
    private Button personalInfo;
    private Button previousPath;
    private Button preferNonPrefer;
    private Button bought;
    private MyPageController myControl;
    private User currentUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_select_menu);
        personalInfo = (Button)findViewById(R.id.selectChangeInfo);
        previousPath = (Button)findViewById(R.id.selectPreviousPath);
        preferNonPrefer = (Button)findViewById(R.id.selectPrefer);
        bought = (Button)findViewById(R.id.selectBought);

        //받아온 유저 클래스
        Intent intent = getIntent();
       currentUser = (User)intent.getSerializableExtra("currentUser");
    }
    public void onClickChangeInfo(View v){// 개인정보 수정 버튼을 눌렀을 때, 개인정보 수정하는 화면으로 전환

        Intent intent = new Intent(// 다음 화면으로 전환
                MyPageSelectMenu.this,
                ChangePersonalInfo.class); // 개인정보 수정관련 클래스
        intent.putExtra("currentUser", currentUser);
        startActivity(intent);

    }
    public void onClickPreviousPath(View v){//이전 방문 루트를 조회하는 버튼을 클릭했을 때

        Intent intent = new Intent(// 다음 화면으로 전환
                MyPageSelectMenu.this,
                PreviousPathBoundary.class); //
        startActivity(intent);

    }
    public void onClickPreferNonPreferInfo(View v){// 선호비선호 브랜드 버튼을 눌렀을 때, 선호비선호 브랜드 조회 화면으로 전환


        Intent intent = new Intent(// 다음 화면으로 전환
                MyPageSelectMenu.this,
               PreferNonpreferBrandBoundary.class); // 선호비선호 브랜드 조회 관련 클래스

        startActivity(intent);

    }
    public void onClickBought(View v){// 이전 구매 목록 조회를 클릭했을 때

        Intent intent = new Intent(// 다음 화면으로 전환
                MyPageSelectMenu.this,
                BoughtListBoundary.class); // 구매내역 조회 관련 클래스

        startActivity(intent);

    }


}
