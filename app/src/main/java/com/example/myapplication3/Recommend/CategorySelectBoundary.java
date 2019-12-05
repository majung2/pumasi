package com.example.myapplication3.Recommend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.myapplication3.R;


public class CategorySelectBoundary extends AppCompatActivity  {

//유저정보 넘겨 받는 기능 추가 필요

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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_selecting);

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
                        com.example.myapplication3.Recommend.RecommendBrand.class); // 다음 넘어갈 클래스 지정
                startActivity(intent);
                intent.putExtra("selected",selectedCategory);



            }
        });// end setOnClickListener

    }//end oncre
}//end class
