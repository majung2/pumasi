package com.example.myapplication3.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;

import java.util.ArrayList;

public class PreferNonpreferBrandBoundary extends AppCompatActivity {//선호,비선호 브랜드 리스트 보는 화면
    private ArrayList<String> preferBrands=new ArrayList<>();//선호 브랜드 리스트
    private ArrayList<String> nonPreferBrands=new ArrayList<>();// 비선호 브랜드 리스트
    private Button changePrefer;
    private Button changeNonPrefer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefer_nonprefer_brand_info);

        //버튼 연결
        changePrefer = (Button) findViewById(R.id.changePreferButton);
        changeNonPrefer = (Button) findViewById(R.id.changenonPrefer);

        //리스트뷰 확인 위해 임의로 리스트에 데이터 추가, 디비 읽어들이는 과정 끝나면 최종 수정 예정
        preferBrands.add("AbC MART");
        preferBrands.add("NEANPOLE");
        nonPreferBrands.add("BLACKYARK");
        nonPreferBrands.add("ADIDAS");

        //선호 브랜드 어댑터 연결
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, preferBrands) ;
        ListView listview = (ListView) findViewById(R.id.preferListView) ;
        listview.setAdapter(adapter);

        //비선호 브랜드 어댑터 연결
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_2,  nonPreferBrands) ;
        ListView listview2 = (ListView) findViewById(R.id.nonpreferListView) ;
        listview.setAdapter(adapter2);
    }

    public void onClickChangeBrand(View v){//사용자가 선호브랜드 또는 비선호 브랜드 수정하기 버튼을 눌렀을 때-> 선호비선호 브랜드 수정 화면으로 전환 + 사용자의 선호/비선호 브랜드 리스트 가져가기
        Intent intent = new Intent(// 다음 화면으로 전환
                PreferNonpreferBrandBoundary.this,
                ChangePreferNonPreferBoundary.class); //
        startActivity(intent);
    }
}
