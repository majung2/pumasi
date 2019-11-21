package com.example.myapplication3.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;

//리스트뷰에 띄울 내용 및 리스트뷰 클릭시 삭제 안내문구 띄우기는 디비 확정되는데로 할 예정
public class ChangePreferNonPreferBoundary extends AppCompatActivity {//선호, 비선호 브랜드 수정하는 화면
    private Button addBrandButton;// 추가하기 버튼
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_prefer_nonprefer);
        addBrandButton = (Button) findViewById(R.id.addBrandButton);

    }

    public void onClickAddBrand(View v){
        //브랜드 고를 수 있게 하는 알림 띄움(디비에서 브랜드 정보 가져와야함)
    }




}
