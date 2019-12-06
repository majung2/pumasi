package com.example.myapplication3.MyPage;
//마이페이지의 메뉴 화면 관련 클래스
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.Login.MainActivity;
import com.example.myapplication3.Recommend.CategorySelectBoundary;
import com.example.myapplication3.Entity.User;
import com.example.myapplication3.R;

import java.util.ArrayList;

public class MyPageSelectMenu extends AppCompatActivity  {
    private Button personalInfo;
    private Button previousPath;
    private Button preferNonPrefer;
    private Button bought;
    private MyPageController myControl;
    private User currentUser;
    private String id;
    private String name;
    private String sex;
    private Integer age;
    private String pw;
    private ArrayList<String> preferList;
    private ArrayList<String> nonpreferList;
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
            Intent intent = new Intent(// 다음 화면으로 전환
                    MyPageSelectMenu.this,
                    MainActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_select_menu);
        personalInfo = (Button)findViewById(R.id.selectChangeInfo);
        previousPath = (Button)findViewById(R.id.selectPreviousPath);
        preferNonPrefer = (Button)findViewById(R.id.selectPrefer);
        preferList= new ArrayList<>();
        nonpreferList = new ArrayList<>();

        //받아온 유저 클래스--> 계속 오류 뜨는 상태
      Intent intent=getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");



      /*  Toast.makeText(this,id,Toast.LENGTH_LONG).show();
        Toast.makeText(this,pw,Toast.LENGTH_LONG).show();
        Toast.makeText(this,sex,Toast.LENGTH_LONG).show();
        Toast.makeText(this,name,Toast.LENGTH_LONG).show();*/


    }
    public void onClickChangeInfo(View v){// 개인정보 수정 버튼을 눌렀을 때, 개인정보 수정하는 화면으로 전환

        Intent intent = new Intent(// 다음 화면으로 전환
                MyPageSelectMenu.this,
                CategorySelectBoundary.class); // 개인정보 수정관련 클래스
        intent.putExtra("id",id);
        intent.putExtra("pw",pw);


        startActivity(intent);

    }
    public void onClickPreviousPath(View v){//이전 방문 루트를 조회하는 버튼을 클릭했을 때

        Intent intent = new Intent(// 다음 화면으로 전환
                MyPageSelectMenu.this,
                PreviousPathBoundary.class); //

        intent.putExtra("id",id);
        intent.putExtra("pw",pw);


        startActivity(intent);

    }
    public void onClickPreferNonPreferInfo(View v){// 선호비선호 브랜드 버튼을 눌렀을 때, 선호비선호 브랜드 조회 화면으로 전환


        Intent intent = new Intent(// 다음 화면으로 전환
                MyPageSelectMenu.this,
               PreferNonpreferBrandBoundary.class); // 선호비선호 브랜드 조회 관련 클래스

        intent.putExtra("id",id);
        intent.putExtra("pw",pw);


        startActivity(intent);

    }


   /* @Override
    public void finishPersonalInfo2() {
        Toast.makeText(this,"개인정보 수정이 완료되었습니다.",Toast.LENGTH_LONG).show();
    }*/
}
