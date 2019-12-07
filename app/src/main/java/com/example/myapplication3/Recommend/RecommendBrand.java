package com.example.myapplication3.Recommend;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication3.R;
import com.example.myapplication3.MyPage.MyPageSelectMenu;


public class RecommendBrand extends AppCompatActivity {


    private ImageView bt_tab1, bt_tab2, bt_tab3, bt_tab4, bt_tab5,
            bt_tab6, bt_tab7, bt_tab8, bt_tab9, bt_tab10, bt_tab11;

    private boolean[] selectedCategory ;
    private String id;
    private String pw;
    private Integer currentX;
    private Integer currentY;


    //이전화면에서 받아오기
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
            Intent intent = new Intent(// 다음 화면으로 전환
                    RecommendBrand.this,
                    MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",id);
            intent.putExtra("pw",pw);

            startActivity(intent);
        }
        else if(state==1){

        }


        return super.onOptionsItemSelected(item);
    }//옵션메뉴
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brandrecommend);

        selectedCategory = new boolean[12]; //이전화면에서 셀렉한 카테고리 받아옴
        Intent intent=getIntent();
        selectedCategory = intent.getBooleanArrayExtra("selected");
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");
        currentX = intent.getIntExtra("X",0);
        currentY = intent.getIntExtra("Y",0);

        for(int i = 1; i<12;i++) {System.out.println(selectedCategory[i]);}
        bt_tab1 = (ImageView) findViewById(R.id.ImageView1);
        bt_tab2 = (ImageView) findViewById(R.id.ImageView2);
        bt_tab3 = (ImageView) findViewById(R.id.ImageView3);
        bt_tab4 = (ImageView) findViewById(R.id.ImageView4);
        bt_tab5 = (ImageView) findViewById(R.id.ImageView5);
        bt_tab6 = (ImageView) findViewById(R.id.ImageView6);
        bt_tab7 = (ImageView)findViewById(R.id.ImageView7);
        bt_tab8 = (ImageView)findViewById(R.id.ImageView8);
        bt_tab9 = (ImageView)findViewById(R.id.ImageView9);
        bt_tab10 = (ImageView)findViewById(R.id.ImageView10);
        bt_tab11 = (ImageView)findViewById(R.id.ImageView11);

        // 받아온 카테고리의 이미지만 띄움
        if(selectedCategory[1]==false) bt_tab1.setImageResource(R.drawable.category1_copy);
        if(selectedCategory[2]==false) bt_tab2.setImageResource(R.drawable.category2_copy);
        if(selectedCategory[3]==false) bt_tab3.setImageResource(R.drawable.category3_copy);
        if(selectedCategory[4]==false) bt_tab4.setImageResource(R.drawable.category4_copy);
        if(selectedCategory[5]==false) bt_tab5.setImageResource(R.drawable.category5_copy);
        if(selectedCategory[6]==false) bt_tab6.setImageResource(R.drawable.category6_copy);
        if(selectedCategory[7]==false) bt_tab7.setImageResource(R.drawable.category7_copy);
        if(selectedCategory[8]==false) bt_tab8.setImageResource(R.drawable.category8_copy);
        if(selectedCategory[9]==false) bt_tab9.setImageResource(R.drawable.category9_copy);
        if(selectedCategory[10]==false) bt_tab10.setImageResource(R.drawable.category10_copy);
        if(selectedCategory[11]==false) bt_tab11.setImageResource(R.drawable.category11_copy);



         //fragment (추천 브랜드 리스트) 연결

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        RecommendFragment fragment = new RecommendFragment();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

}

