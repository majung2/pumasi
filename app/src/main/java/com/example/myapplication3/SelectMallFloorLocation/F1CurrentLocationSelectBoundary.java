package com.example.myapplication3.SelectMallFloorLocation;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication3.MyPage.MyPageSelectMenu;
import com.example.myapplication3.R;
import com.example.myapplication3.Recommend.CategorySelectBoundary;
import com.example.myapplication3.Recommend.RecommendBrand;
import com.example.myapplication3.Shopping.ShoppingActivity;

public class F1CurrentLocationSelectBoundary extends AppCompatActivity {

    private String id;
    private String pw;

    private Button current1; //신세계
    private Button current2;
    private Button current3;
    private Button current4;
    private Button current5;

    private Integer currentX;
    private Integer currentY;


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
                    F1CurrentLocationSelectBoundary.this,
                    MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",id);
            intent.putExtra("pw",pw);

            startActivity(intent);
        }
        else if(state==1){

        }


        return super.onOptionsItemSelected(item);
    }//옵션메뉴

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_currentlocation);


        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");


        current1 = (Button) findViewById(R.id.button1);
        current2 = (Button) findViewById(R.id.button2);
        current3 = (Button) findViewById(R.id.button3);
        current4 = (Button) findViewById(R.id.button4);
        current5 = (Button) findViewById(R.id.button5);


    }

    public void onClick5(View v){

        currentX = 830;currentY = 116;
        System.out.println("currentX: "+currentX + "currentY: "+currentY );
        Next();
    }
    public void onClick4(View v){
        currentX = 848;currentY = 317;
        System.out.println("currentX: "+currentX + "currentY: "+currentY );
        Next();
    }
    public void onClick3(View v){
        currentX = 523;currentY = 290;
        System.out.println("currentX: "+currentX + "currentY: "+currentY );
        Next();
    }
    public void onClick2(View v){
        currentX = 172;currentY = 222;
        System.out.println("currentX: "+currentX + "currentY: "+currentY );
        Next();
    }
    public void onClick1(View v){
        currentX = 16;currentY = 221;
        System.out.println("currentX: "+currentX + "currentY: "+currentY );
        Next();
    }

    public void Next(){

        System.out.println("Next");
        Intent intent = new Intent(// 다음 화면으로 전환
                F1CurrentLocationSelectBoundary.this,
                CategorySelectBoundary.class); //쇼핑 액티비티로 이동
        intent.putExtra("id",id);
        intent.putExtra("pw",pw);
        intent.putExtra("X",currentX);
        intent.putExtra("Y",currentY);

        startActivity(intent);
    }


}
