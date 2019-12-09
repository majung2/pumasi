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

import com.example.myapplication3.Login.MainActivity;
import com.example.myapplication3.MyPage.MyPageSelectMenu;
import com.example.myapplication3.R;
import com.example.myapplication3.Shopping.ShoppingActivity;

public class MallSelectBoundary extends AppCompatActivity {


    private String id ;
    private String pw ;

    private Button sin_outlet_1; //신세계
    private Button sin_outlet_2;
    private Button sin_outlet_3;

    private Button hd_outlet_1; //현대
    private Button hd_outlet_2;

    private Button mm_outlet_1; //마리오

    private ImageView empty1; //여백
    private ImageView empty2;

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
                    MallSelectBoundary.this,
                    ShoppingActivity.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",id);
            intent.putExtra("pw",pw);

            startActivity(intent);
            /*Intent intent = new Intent(// 다음 화면으로 전환
                    MallSelectBoundary.this,
                    MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",id);
            intent.putExtra("pw",pw);

            startActivity(intent);*/
        }
        else if(state==1){

        }


        return super.onOptionsItemSelected(item);
    }//옵션메뉴

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_shoppingmall);

        Intent intent=getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");


        sin_outlet_1 = (Button) findViewById(R.id.sin_yeoju);
        sin_outlet_2 = (Button) findViewById(R.id.sin_busan);
        sin_outlet_3 = (Button) findViewById(R.id.sin_paju);

        hd_outlet_1 = (Button) findViewById(R.id.hd_kimpo);
        hd_outlet_2 = (Button) findViewById(R.id.hd_songdo);

        mm_outlet_1 = (Button) findViewById(R.id.mm_gasan);

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
                        F1CurrentLocationSelectBoundary.class); // 다음 넘어갈 클래스 지정
                intent.putExtra("id",id);
                intent.putExtra("pw",pw);

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
