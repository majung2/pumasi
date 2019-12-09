package com.example.myapplication3.Recommend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication3.Login.MainActivity;
import com.example.myapplication3.MyPage.MyPageSelectMenu;
import com.example.myapplication3.R;


public class CategorySelectBoundary extends AppCompatActivity  {

//유저정보 넘겨 받는 기능 추가 필요

    private String id;
    private String pw;
    private Integer currentX;
    private Integer currentY;

    private TextView text1;

    private CheckBox category1;
    private CheckBox category2;
    private CheckBox category3;
    private CheckBox category4;
    private CheckBox category5;
    private CheckBox category6;
    private CheckBox category7;
    private CheckBox category8;
    private CheckBox category9;
    private CheckBox category10;
    private CheckBox category11;

    private Button finishSelect;

    //실질적으로 저장될 정보들에 해당하는 attributes

    private boolean[] selectedCategory = new boolean[12]; //category 11개 + selectedCategory[0]

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
                    CategorySelectBoundary.this,
                    MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",id);
            intent.putExtra("pw",pw);

            startActivity(intent);
        }
        else if(state==1){
            Toast.makeText(CategorySelectBoundary.this,"로그아웃되었습니다.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(// 다음 화면으로 전환
                    CategorySelectBoundary.this,
                    MainActivity.class);

            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }//옵션메뉴
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_selecting);


        //이전화면에서 유저정보 받아오기
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");
        currentX = intent.getIntExtra("X",0);
        currentY = intent.getIntExtra("Y",0);


        text1 = (TextView) findViewById(R.id.textView1);

        category1 = (CheckBox) findViewById(R.id.checkBox1);
        category2 = (CheckBox) findViewById(R.id.checkBox2);
        category3 = (CheckBox) findViewById(R.id.checkBox3);
        category4 = (CheckBox) findViewById(R.id.checkBox4);
        category5 = (CheckBox) findViewById(R.id.checkBox5);
        category6 = (CheckBox) findViewById(R.id.checkBox6);
        category7 = (CheckBox) findViewById(R.id.checkBox7);
        category8 = (CheckBox) findViewById(R.id.checkBox8);
        category9 = (CheckBox) findViewById(R.id.checkBox9);
        category10 = (CheckBox) findViewById(R.id.checkBox10);
        category11 = (CheckBox) findViewById(R.id.checkBox11);

        finishSelect = (Button) findViewById(R.id.finishSelectButton);


        finishSelect.setOnClickListener(new View.OnClickListener() { //finish select
            @Override
            public void onClick(View v) {
                String result = "";  // 결과를 출력할 문자열 초기화
                String selectedCategoryNum = "";

                if(category1.isChecked() == true)
                {result += category1.getText().toString() + " ";selectedCategoryNum +="1,";  selectedCategory[1]= true ;}
                if(category2.isChecked() == true)
                {result += category2.getText().toString()+ " "; selectedCategoryNum +="2,";selectedCategory[2]= true ;}
                if(category3.isChecked() == true)
                {result += category3.getText().toString()+ " "; selectedCategoryNum +="3,";selectedCategory[3]= true ;}
                if(category4.isChecked() == true)
                {result += category4.getText().toString() + " "; selectedCategoryNum +="4,";selectedCategory[4]= true ;}
                if(category5.isChecked() == true)
                {result += category5.getText().toString() + " "; selectedCategoryNum +="5,";selectedCategory[5]= true ;}
                if(category6.isChecked() == true)
                {result += category6.getText().toString() + " "; selectedCategoryNum +="6,";selectedCategory[6]= true ;}
                if(category7.isChecked() == true)
                {result += category7.getText().toString() + " "; selectedCategoryNum +="7,";selectedCategory[7]= true ;}
                if(category8.isChecked() == true)
                {result += category8.getText().toString() + " "; selectedCategoryNum +="8,";selectedCategory[8]= true ;}
                if(category9.isChecked() == true)
                {result += category9.getText().toString() + " "; selectedCategoryNum +="9,";selectedCategory[9]= true ;}
                if(category10.isChecked() == true)
                {result += category10.getText().toString() + " "; selectedCategoryNum +="10,";selectedCategory[10]= true ;}
                if(category11.isChecked() == true)
                {result += category11.getText().toString() + " "; selectedCategoryNum +="11,";selectedCategory[11]= true ;}

                System.out.println("selected = " +result);


                Intent intent = new Intent(
                        CategorySelectBoundary.this,
                        RecommendBrand.class); // 다음 넘어갈 클래스 지정

                intent.putExtra("id",id);
                intent.putExtra("pw",pw);
                intent.putExtra("X",currentX);
                intent.putExtra("Y",currentY);
                intent.putExtra("selected",selectedCategory);


                startActivity(intent);




            }
        });// end setOnClickListener

    }//end oncre
}//end class
