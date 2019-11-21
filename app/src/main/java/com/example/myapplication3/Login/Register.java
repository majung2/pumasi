package com.example.myapplication3.Login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.Login.LoginController;
import com.example.myapplication3.R;

public class Register extends AppCompatActivity {// 회원가입을 위한 기본적 정보를 입력하는 창
    private Button registerNext;// 다음 버튼
    private EditText textName; //이름
    private EditText textId;// 아이디
    private EditText textPass;//비밀번호
    private RadioButton buttonMale;//남자
    private RadioButton buttonFemale;//여자
    private RadioButton buttonAge10;//10대
    private RadioButton buttonAge20;//20대
    private RadioButton buttonAge30;//30대
    private RadioButton buttonAge40;//40대
    private RadioButton buttonAge50;//50대
    private RadioButton buttonAge60;//60대
    private RadioGroup radiogroup1;//성별 라디오 버튼의 그룹
    private RadioGroup radiogroup2; // 연령 라디오 버튼의 그룹
    private RadioButton checkedSex;//체크된 성별 버튼
    private RadioButton checkedAge;//체므된 연령 버튼
    //실질적으로 저장될 유저의 정보들에 해당하는 attributes
    private String name;
    private String id;
    private String pw;
    private String sex;
    private Integer age;

    //LoginController
    public LoginController lgController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);  // register.xml 과 자바파일을 연결
        //기본 버튼 및 텍스트창 연결
        registerNext = (Button)findViewById(R.id.registernext);
        textName =(EditText)findViewById(R.id.textName);
        textId =(EditText)findViewById(R.id.textId);
        textPass =(EditText)findViewById(R.id.textPass);
        buttonMale =(RadioButton) findViewById(R.id.buttonMale);
        buttonFemale =(RadioButton) findViewById(R.id.buttonFemale);
        buttonAge10 =(RadioButton) findViewById(R.id.buttonAge10);
        buttonAge20 =(RadioButton) findViewById(R.id.buttonAge20);
        buttonAge30 =(RadioButton) findViewById(R.id.buttonAge30);
        buttonAge40 =(RadioButton) findViewById(R.id.buttonAge40);
        buttonAge50 =(RadioButton) findViewById(R.id.buttonAge50);
        buttonAge60 =(RadioButton) findViewById(R.id.buttonAge60);
        radiogroup1= (RadioGroup) findViewById(R.id.radiogroup1);
        radiogroup2 = (RadioGroup) findViewById(R.id.radiogroup2);

        //LoginController 클래스 생성
        lgController = new LoginController();

        radiogroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {//성별 라디오 그룹 리스너
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                checkedSex =(RadioButton) findViewById(checkedId);
                if(checkedSex==null){

                }


            }
        });
        radiogroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {//연령 라디오 그룹 리스너
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                checkedAge =(RadioButton) findViewById(checkedId);
                if(checkedAge==null){

                }

            }
        });
        registerNext.setOnClickListener(new View.OnClickListener() {//다음 버튼을 눌렀을 시, 사용자의 정보를 저장하여 회원가입시킨(추후에는 이 버튼을 누르면 선호/비선호 브랜드 선택후 회원가입되도록 수정하기)
            @Override
            public void onClick(View view) {
                final String TAG = "레지스터: ";
                Log.d(TAG,"할로할로");
                name = textName.getText().toString();
                id = textId.getText().toString();
                pw = textPass.getText().toString();
                int rb1 = radiogroup1.getCheckedRadioButtonId();
                switch(rb1){//성별 처리
                    case R.id.buttonMale:
                        sex = "남성";
                        break;

                    case R.id.buttonFemale:
                        sex="여성";
                        break;
                    default:

                }
                int rb2 = radiogroup1.getCheckedRadioButtonId();
                switch(rb2){//연령 처리
                    case R.id.buttonAge10:
                        age= 10;break;

                    case R.id.buttonAge20:
                        age= 20;break;

                    case R.id.buttonAge30:
                        age= 30;break;

                    case R.id.buttonAge40:
                        age= 40;break;

                    case R.id.buttonAge50:
                        age= 50;break;

                    case R.id.buttonAge60:
                        age= 60;break;

                    default:

                }
                lgController.register(name,id,pw,sex,age);
                final String TTAG = "레지스터: ";
                Log.d(TTAG,"완성");
            }
        }

        );
    }


}