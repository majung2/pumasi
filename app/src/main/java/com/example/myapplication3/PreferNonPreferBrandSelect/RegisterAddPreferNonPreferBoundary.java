package com.example.myapplication3.PreferNonPreferBrandSelect;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.Login.LoginController;
import com.example.myapplication3.Login.MainActivity;
import com.example.myapplication3.MyPage.MyPageSelectMenu;
import com.example.myapplication3.R;

import java.util.ArrayList;

public class RegisterAddPreferNonPreferBoundary extends AppCompatActivity implements LoginController.AddBrandCallback,LoginController.userBrandCallback{
    String id;
    String pw;
    String name;
    String sex;
    Integer age;

    String selectedCategory;
    LoginController controller;
    ArrayList<String> selectedBrands;
    private ArrayAdapter<String> adapter;
    private String preferNon;
    private Button finishButoon;
    private ArrayList<String> preferBrandList ;
    private ArrayList<String> nonPreferBrandList ;
    private ListView listView;
    LoginController controller2;
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
                    RegisterAddPreferNonPreferBoundary.this,
                    MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",id);
            intent.putExtra("pw",pw);

            startActivity(intent);
        }
        else if(state==1){
            Toast.makeText(RegisterAddPreferNonPreferBoundary.this,"로그아웃되었습니다.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(// 다음 화면으로 전환
                    RegisterAddPreferNonPreferBoundary.this,
                    MainActivity.class);

            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }//옵션메뉴

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_prefer_nonprefer);

        finishButoon = (Button)findViewById(R.id.finish);
        Intent intent=getIntent();
        selectedCategory = intent.getStringExtra("category");
        preferNon = intent.getStringExtra("prefernonprefer");
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");
        name = intent.getStringExtra("name");
        age = intent.getIntExtra("age",0);
        sex = intent.getStringExtra("sex");

        controller = new LoginController();
        controller.LoginControllerBrand(this);
        controller.setAddControlUser(id,pw);
        controller.getCatBrands(selectedCategory);

        controller2 = new LoginController();
        controller2.LoginAddController(this);
        controller2.setAddControlUser(id,pw);



        selectedBrands= new ArrayList<>();
        preferBrandList = new ArrayList<>();
        nonPreferBrandList = new ArrayList<>();

    }

    @Override
    public void finishFindBrands2(String bName) {
        selectedBrands.add(bName);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,selectedBrands);
        listView = (ListView) findViewById(R.id.brand_list);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//사용자가 추가를 위해 브랜드를 클릭했을 때

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                controller2.addBrand((String)adapterView.getItemAtPosition(position),preferNon);

                if(preferNon.equals("preferbrand")){//preferBrandList에 추가
                    preferBrandList.add((String)adapterView.getItemAtPosition(position));
                    System.out.println(preferBrandList);}
                else if (preferNon.equals("nonpreferbrand") )
                {//NonPreferBrandList에 추가
                    nonPreferBrandList.add((String)adapterView.getItemAtPosition(position));
                    System.out.println(nonPreferBrandList);
                }

/*
                adapter.notifyDataSetChanged();

                Intent intent2 = new Intent(// 다음 화면으로 전환
                        RegisterAddPreferNonPreferBoundary.this,
                        PreferNonpreferBrandSelectBoundary.class);

                intent2.putExtra("preferlist", preferBrandList);
                intent2.putExtra("nonpreferlist", nonPreferBrandList);
                intent2.putExtra("name", name);
                intent2.putExtra("id", id);
                intent2.putExtra("pw", pw);
                intent2.putExtra("age", age);
                intent2.putExtra("sex", sex);

                startActivity(intent2);
            */
            }

        });

        finishButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(// 다음 화면으로 전환
                        RegisterAddPreferNonPreferBoundary.this,
                        PreferNonpreferBrandSelectBoundary.class);

                intent2.putExtra("preferlist", preferBrandList);
                intent2.putExtra("nonpreferlist", nonPreferBrandList);
                intent2.putExtra("name", name);
                intent2.putExtra("id", id);
                intent2.putExtra("pw", pw);
                intent2.putExtra("age", age);
                intent2.putExtra("sex", sex);

                startActivity(intent2);
            }
        });

    }


    @Override
    public void finishAddBrand2() {
        Toast.makeText(getApplicationContext(), "해당 브랜드를 추가하였습니다.", Toast.LENGTH_LONG).show();
    }
}
