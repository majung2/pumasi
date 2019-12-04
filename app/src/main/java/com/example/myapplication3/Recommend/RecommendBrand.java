package com.example.myapplication3.Recommend;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication3.PreferNonPreferBrandSelect.Fragment1;
import com.example.myapplication3.R;


public class RecommendBrand extends AppCompatActivity {


    private ImageView bt_tab1, bt_tab2, bt_tab3, bt_tab4, bt_tab5,
            bt_tab6, bt_tab7, bt_tab8, bt_tab9, bt_tab10, bt_tab11;

    private String selectedCategoryNum = "";
    private boolean[] selectedCategory ;

    //이전화면에서 받아오기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brandrecommend);

        selectedCategory = new boolean[12]; //이전화면에서 셀렉한 카테고리 받아옴
        Intent intent=getIntent();
        selectedCategory = intent.getBooleanArrayExtra("selected");


        // 받아온 카테고리의 이미지만 띄움
        if(selectedCategory[1]==true) bt_tab1 = (ImageView) findViewById(R.id.ImageView1);
        if(selectedCategory[2]==true) bt_tab2 = (ImageView) findViewById(R.id.ImageView2);
        if(selectedCategory[3]==true) bt_tab3 = (ImageView) findViewById(R.id.ImageView3);
        if(selectedCategory[4]==true)  bt_tab4 = (ImageView) findViewById(R.id.ImageView4);
        if(selectedCategory[5]==true) bt_tab5 = (ImageView) findViewById(R.id.ImageView5);
        if(selectedCategory[6]==true) bt_tab6 = (ImageView) findViewById(R.id.ImageView6);
        if(selectedCategory[7]==true) bt_tab7 = (ImageView)findViewById(R.id.ImageView7);
        if(selectedCategory[8]==true) bt_tab8 = (ImageView)findViewById(R.id.ImageView8);
        if(selectedCategory[9]==true) bt_tab9 = (ImageView)findViewById(R.id.ImageView9);
        if(selectedCategory[10]==true) bt_tab10 = (ImageView)findViewById(R.id.ImageView10);
        if(selectedCategory[11]==true) bt_tab11 = (ImageView)findViewById(R.id.ImageView11);

         //fragment (추천 브랜드 리스트) 연결

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        RecommendFragment fragment = new RecommendFragment();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

}

