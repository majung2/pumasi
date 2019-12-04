package com.example.myapplication3.SelectMallFloorLocation;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication3.R;

public class FloorSelectBoundary extends AppCompatActivity {


    private TextView text1;

    private Button floor1;
    private Button floor2;
    private Button floor3;

    private ImageView empty1;
    private ImageView empty2;
    private ImageView empty3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_currentfloor);

        text1 = (TextView) findViewById(R.id.textView1);

        floor1 = (Button) findViewById(R.id.floor1);
        floor2 = (Button) findViewById(R.id.floor2);
        floor3 = (Button) findViewById(R.id.floor3);


        floor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //1층 선택시

                Intent intent = new Intent(
                        FloorSelectBoundary.this,
                        F1CurrentLocationSelectBoundary.class); // 다음 넘어갈 클래스 지정
                startActivity(intent);

            }

        });

        floor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //2층 선택시

                Intent intent = new Intent(
                        FloorSelectBoundary.this,
                        F2CurrentLocationSelectBoundary.class); // 다음 넘어갈 클래스 지정
                startActivity(intent);

            }

        });

        floor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //3층 선택시

                Intent intent = new Intent(
                        FloorSelectBoundary.this,
                        F3CurrentLocationSelectBoundary.class); // 다음 넘어갈 클래스 지정
                startActivity(intent);

            }

        });

    }//end oncreate
}//end boundary class
