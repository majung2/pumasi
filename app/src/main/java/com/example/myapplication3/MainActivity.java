package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Login;
    private Button Register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login = (Button)findViewById(R.id.loginbutton);
        Register = (Button)findViewById(R.id.registerbutton);




    }
    public void onClickRegister(View v){
        Intent intent = new Intent(
                MainActivity.this,
                Register.class); // 다음 넘어갈 클래스 지정
        startActivity(intent);

    }
    public void onClickLogIn(View v){
        Intent intent = new Intent(
                MainActivity.this,
                BrancRecommendedBoundary.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
        startActivity(intent);


    }

}
