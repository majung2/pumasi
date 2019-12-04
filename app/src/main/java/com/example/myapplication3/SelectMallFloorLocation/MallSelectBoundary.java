package com.example.myapplication3.SelectMallFloorLocation;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication3.R;

public class MallSelectBoundary extends AppCompatActivity {


    private Button sin_outlet_1; //신세계
    private Button sin_outlet_2;
    private Button sin_outlet_3;

    private Button hd_outlet_1; //현대
    private Button hd_outlet_2;

    private Button mm_outlet_1; //마리오

    private ImageView empty1; //여백
    private ImageView empty2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_shoppingmall);

        sin_outlet_1 = (Button) findViewById(R.id.sin_yeoju);
        sin_outlet_2 = (Button) findViewById(R.id.sin_busan);
        sin_outlet_3 = (Button) findViewById(R.id.sin_paju);

        sin_outlet_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //파주점이 아닌 매장을 클릭할 경우 '준비중' 토스트 메세지 띄움

                Toast.makeText(MallSelectBoundary.this,"준비중입니다.",Toast.LENGTH_LONG).show();

            }

        });
        sin_outlet_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MallSelectBoundary.this,"준비중입니다.",Toast.LENGTH_LONG).show();

            }

        });
        sin_outlet_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//파주 아울렛 클릭할 경우

                Toast.makeText(MallSelectBoundary.this,"신세계 파주점",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(
                            MallSelectBoundary.this,
                            FloorSelectBoundary.class); // 다음 넘어갈 클래스 지정
                    startActivity(intent); //층수 선택 클래스로 넘어감

            }

        });

        hd_outlet_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MallSelectBoundary.this,"준비중",Toast.LENGTH_LONG).show();

            }

        });
        hd_outlet_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MallSelectBoundary.this,"준비중",Toast.LENGTH_LONG).show();

            }

        });
        mm_outlet_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MallSelectBoundary.this,"준비중",Toast.LENGTH_LONG).show();

            }

        });

    }//end oncreate
}//end boundary class
