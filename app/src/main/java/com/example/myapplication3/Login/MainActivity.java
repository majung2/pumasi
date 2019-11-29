package com.example.myapplication3.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication3.CategorySelectBoundary;
import com.example.myapplication3.Entity.User;
import com.example.myapplication3.MyPage.MyPageSelectMenu;
import com.example.myapplication3.R;


public class MainActivity extends AppCompatActivity {
    private User currentUser;
    private Button Login;
    private Button Register;
    private EditText id;
    private EditText pw;
    public LoginController logincontrol;
    private String userId;
    private String userPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login = (Button)findViewById(R.id.loginbutton);
        Register = (Button)findViewById(R.id.registerbutton);
        id = (EditText) findViewById(R.id.idtext);
        pw= (EditText) findViewById(R.id.pwtext);


    }
    public void onClickRegister(View v){
        Intent intent = new Intent(
                MainActivity.this,
                com.example.myapplication3.Login.Register.class); // 다음 넘어갈 클래스 지정
        startActivity(intent);

    }
    public void onClickLogIn(View v){//사용자가  로그인 버튼을 눌렀을 때, 사용자 정보가 있는 경우는 정보를 유저 클래스에 담고, 없는 경우는 다시 입력하라는 창을 띄운다.
        userId=id.getText().toString();//입력된 id값 가져오기
        userPw=pw.getText().toString();//입력된 pw값 가져오기

        if(id.length()==0 || pw.length()==0){//아이디나 비밀번호가 입력되지 않은 경우
            Toast.makeText(this,"아이디, 비밀번호를 입력해주세요.",Toast.LENGTH_LONG).show();//아이디, 비밀번호를 입력하라는 토스트 메시지를 띄운다.
        }

        else{//사용자가 아이디와 비밀번호를 모두 입력한 경우
            logincontrol= new LoginController();
            if(logincontrol.login(userId,userPw)== true){//로그인 컨트롤러로 아이디, 비밀번호 보내 사용자 존재 여부 체크-> 존재하는 경우, 유저 정보 초기화 완료(지근은 아이디랑 패스워드만 코드상으로 초기화 시켜논 상태)
                Toast.makeText(this,"로그인되었습니다.",Toast.LENGTH_LONG).show();
                currentUser = logincontrol.getUserFromLoginControll();

                Intent intent = new Intent(// 다음 화면으로 전환
                        MainActivity.this,
                        MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
               intent.putExtra("currentUser", currentUser);
                startActivity(intent);
            }
            else{//해당 사용자가 존재하지 않는 경우-> 토스트 띄우기
                Toast.makeText(this,"존재하지 않는 회원입니다.",Toast.LENGTH_LONG).show();
            }

            ///임시
           Intent intent = new Intent(// 다음 화면으로 전환
                    MainActivity.this,
                    MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
          //  intent.putExtra("currentUser", currentUser);
            startActivity(intent);

        }


    }

}
