package com.example.myapplication3.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication3.Entity.User;
import com.example.myapplication3.MyPage.MyPageSelectMenu;
import com.example.myapplication3.R;
import com.example.myapplication3.Recommend.PathSelectBoundary;
import com.example.myapplication3.SelectMallFloorLocation.MallSelectBoundary;
import com.example.myapplication3.Shopping.ShoppingActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LoginController.LoginControllCallback {
    private User currentUser;
    private Button Login;
    private Button Register;
    private EditText id;
    private EditText pw;
    public LoginController logincontrol;
    private String userId;
    private String userPw;
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
            Intent intent = new Intent(// 다음 화면으로 전환
                    MainActivity.this,
                    ShoppingActivity.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",1);
            intent.putExtra("pw",1);

            startActivity(intent);
            /*Toast toast = Toast.makeText(getApplicationContext(),"로그인 해주세요", Toast.LENGTH_LONG);
            toast.show();*/
        }
        else if(state==1){

        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login = (Button)findViewById(R.id.loginbutton);
        Register = (Button)findViewById(R.id.registerbutton);
        id = (EditText) findViewById(R.id.idtext);
        pw= (EditText) findViewById(R.id.pwtext);

        preferList= new ArrayList<>();
        nonpreferList = new ArrayList<>();

    }
    public void onClickRegister(View v){
        Intent intent = new Intent(
                MainActivity.this,
                com.example.myapplication3.Register.Register.class); // 다음 넘어갈 클래스 지정
        startActivity(intent);

    }
    public void onClickLogIn(View v){//사용자가  로그인 버튼을 눌렀을 때, 사용자 정보가 있는 경우는 정보를 유저 클래스에 담고, 없는 경우는 다시 입력하라는 창을 띄운다.
        userId=id.getText().toString();//입력된 id값 가져오기
        userPw=pw.getText().toString();//입력된 pw값 가져오기

        if(id.length()==0 || pw.length()==0){//아이디나 비밀번호가 입력되지 않은 경우
            Toast.makeText(this,"아이디, 비밀번호를 입력해주세요.",Toast.LENGTH_LONG).show();//아이디, 비밀번호를 입력하라는 토스트 메시지를 띄운다.
        }

        else{//사용자가 아이디와 비밀번호를 모두 입력한 경우
            logincontrol= new LoginController(this);
            logincontrol.login(userId,userPw);

        }


    }


    public void finishLogin2(){

        Toast.makeText(this,"로그인되었습니다.",Toast.LENGTH_LONG).show();
        currentUser = logincontrol.getUserFromLoginControll();
      //  System.out.println(currentUser.getAge());
        Intent intent = new Intent(// 다음 화면으로 전환
                MainActivity.this,
                PathSelectBoundary.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
       intent.putExtra("id",currentUser.getId());
       intent.putExtra("pw",currentUser.getPw());
       intent.putExtra("x",100);
       intent.putExtra("y",100);
       ArrayList<String> brands= new ArrayList<>();
       brands.add("COACH");
       brands.add("LANVIN COLLECTION");
       intent.putStringArrayListExtra("selectedBrands",brands);

        startActivity(intent);

    }

    public void failLogin2(){
        Toast.makeText(this,"존재하지 않는 회원입니다.",Toast.LENGTH_LONG).show();
    }
}
